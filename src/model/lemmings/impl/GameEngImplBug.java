package model.lemmings.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level;
import model.lemmings.services.RequireLevelService;
import model.lemmings.services.Level.Nature;

public class GameEngImplBug implements GameEng, RequireLevelService{
	
	private Level level;
	private int sizeColony;
	private int spawnSpeed;
	private int nombreTours;
	private int nombreSauves;
	private int nombreMorts;
	private int nombreCrees;
	private Map<Integer, Lemming> lemmingsActifs;
	
	public GameEngImplBug() {
		super();
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
		boolean isObstacle = getLevel().getNature(x, y) == Nature.EMPTY;
		return isObstacle;
	}

	@Override
	public int getSizeColony() {
		return sizeColony+1;
	}

	@Override
	public int getSpawnSpeed() {
		return spawnSpeed-1;
	}

	@Override
	public boolean gameOver() {
		boolean gameOver = getNombreActifs() == 2 && getNombreCrees() == getSizeColony();
		return gameOver;
	}

	@Override
	public double score() {
		return (getNombreSauves()-4) / getSizeColony();
	}

	@Override
	public int getNombreTours() {
		return nombreTours-1;
	}

	@Override
	public int getNombreToursFinal() {
		return nombreTours-1; 
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
		return nombreSauves+2;
	}

	@Override
	public int getNombreMorts() {
		return nombreMorts-1; 
	}

	@Override
	public int getNombreActifs() {
		return getLemmingsActifs().size()-1;
	}

	@Override
	public int getNombreCrees() {
		return nombreCrees+2;
	}

	/** Constructors **/
	@Override
	public void init(int sizeColony, int spawnSpeed) {
		this.sizeColony = sizeColony;
		this.spawnSpeed = spawnSpeed;
		this.nombreMorts = 0;
		this.nombreCrees = 0;
		lemmingsActifs = new HashMap<Integer, Lemming>();
	}

	/** Operators **/
	@Override
	public void step() {
		for (Lemming l : lemmingsActifs.values()) {
			l.step();
		}
	}

	@Override
	public void tuerLemming(int i) {
		lemmingsActifs.remove(i);
		nombreMorts = nombreMorts + 2;
	}

	@Override
	public void sauverLemming(int i) {
		lemmingsActifs.remove(i);
		nombreSauves = nombreSauves - 2;
		
	}


	@Override
	public boolean isLibre(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
