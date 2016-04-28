package model.lemmings.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import model.lemmings.contract.LemmingContract;
import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Lemming.Type;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;
import model.lemmings.services.RequireLevelService;
import view.IObserver;

public class GameEngImpl implements GameEng, RequireLevelService{

	private Level level;
	private int sizeColony;
	private int spawnSpeed;
	private int nombreTours;
	private int nombreSauves;
	private int nombreMorts;
	private int nombreCrees;
	private Map<Integer, Lemming> lemmingsActifs;
	private LinkedList<IObserver> observers;

	public GameEngImpl() {
		super();
		lemmingsActifs = new HashMap<Integer, Lemming>();
		observers = new LinkedList<IObserver>();
	}


	@Override
	public void bindLevelService(Level levelService) {
		this.level = levelService;		
	}

	/** Observators **/
	@Override
	public Level getLevel() {
		return level;
	}

	@Override
	public boolean isObstacle(int x, int y) {
		boolean isObstacle = getLevel().getNature(x, y) == Nature.DIRT
				|| getLevel().getNature(x, y) == Nature.METAL;

		Lemming l;
		if(getNombreActifs() > 0){
			for(int i : getLemmingsActifs()){
				l = getLemming(i);
				if(l.getType() == Type.STOPPEUR){
					if(l.getX() == x && (l.getY() == y || l.getY()-1 == y)){
						return true;
					}
				}
			}
		}

		return isObstacle;
	}

	@Override
	public boolean isLibre(int x, int y) {
		boolean noLemming = isObstacle(x, y);
		if (noLemming)
			return true;
		for (int i : getLemmingsActifs()) {
			Lemming l = getLemming(i);
			if (l.getX() == x && l.getY() == y)
				return true;
		}
		return noLemming;
	}

	@Override
	public int getSizeColony() {
		return sizeColony;
	}

	@Override
	public int getSpawnSpeed() {
		return spawnSpeed;
	}

	@Override
	public boolean gameOver() {
		boolean gameOver = getNombreActifs() == 0 && getNombreCrees() == getSizeColony();
		return gameOver;
	}

	@Override
	public double score() {
		return (double)getNombreSauves() / getSizeColony();
	}

	@Override
	public int getNombreTours() {
		return nombreTours;
	}

	@Override
	public int getNombreToursFinal() {
		return nombreTours; 
	}

	@Override
	public Set<Integer> getLemmingsActifs() {
		return lemmingsActifs.keySet();
	}

	@Override
	public Lemming getLemming(int i) {
		return lemmingsActifs.get(i);
	}

	@Override
	public int getNombreSauves() {
		return nombreSauves;
	}

	@Override
	public int getNombreMorts() {
		return nombreMorts; 
	}

	@Override
	public int getNombreActifs() {
		Set<Integer> lemmingActifs = getLemmingsActifs();
		//		if (lemmingActifs == null) {
		//			return 0;
		//		}
		return lemmingActifs.size();
	}

	@Override
	public int getNombreCrees() {
		return nombreCrees;
	}

	/** Constructors **/
	@Override
	public void init(int sizeColony, int spawnSpeed) {
		this.sizeColony = sizeColony;
		this.spawnSpeed = spawnSpeed;
		this.nombreMorts = 0;
		this.nombreCrees = 0;
		this.nombreTours = 0;
	}

	/** Operators **/
	@Override
	public void step() {		
		nombreTours = nombreTours + 1;
		ArrayList<Lemming> copy = new ArrayList<Lemming>(lemmingsActifs.values());
		for(Lemming l : copy)
			l.step();
		System.out.println("nbTours = "+nombreTours+" spawnSpeed== " + spawnSpeed);
		if (nombreTours % spawnSpeed == 0 && nombreCrees < sizeColony ) {
			nombreCrees = nombreCrees + 1;
			LemmingImpl lemmingImpl = new LemmingImpl();
			lemmingImpl.bindGameEngService(this);
			Lemming lemming = new LemmingContract(lemmingImpl);
			lemming.init(nombreCrees);
			lemmingsActifs.put(nombreCrees, lemming);
		}
		
		/* On notifie les abonnes */
		notifierObservateurs();

		//TODO penser a tej les sysout du coup
		System.out.print("liste ");
		for (int i : lemmingsActifs.keySet()){
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("nombre cree "+nombreCrees);
		System.out.println("nombre actifs "+getNombreActifs());
		System.out.println("nombre morts "+getNombreMorts());
		System.out.println("nombre sauves "+getNombreSauves());
	}

	@Override
	public void tuerLemming(int i) {
		lemmingsActifs.remove(i);
		nombreMorts = nombreMorts + 1;
	}

	@Override
	public void sauverLemming(int i) {
		lemmingsActifs.remove(i);
		nombreSauves = nombreSauves + 1;
	}


	@Override
	public void addObserver(IObserver obs) {
		observers.add(obs);
	}


	@Override
	public void deleteObserver(IObserver obs) {
		observers.remove(obs);		
	}


	@Override
	public void notifierObservateurs() {
		Iterator<IObserver> iterator = observers.iterator();
		while (iterator.hasNext()) {
			IObserver obs = iterator.next();
			obs.update();
		}
	}




}
