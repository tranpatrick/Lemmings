package model.lemmings.contract;

import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;
import view.IObserver;

public class GameEngContract extends GameEngDecorator{

	public GameEngContract(GameEng gameEng) {
		super(gameEng);
	}


	public void checkInvariant() {
		// inv: gameOver() == ( getNombreActifs() == 0 AND getNombreCrees() == getSizeColony() )
		if (!(super.gameOver() == (super.getNombreActifs() == 0 && 
				super.getNombreCrees() == super.getSizeColony()))) {
			throw new InvariantError("gameOver() == ( getNombreActifs() == 0 AND"
					+ " getNombreCrees() == getSizeColony() not satisfied");
		}

		// \invMin getNombreActifs() == | getLemmingActifs() |
		if (!(super.getNombreActifs() == super.getLemmingsActifs().size())) {
			throw new InvariantError("getNombreActifs() == "
					+ "| getLemmingActifs() | not satisfied");
		}

		// \invMin score() == getNombreSauves / getSizeColony()
		if (super.gameOver()) {
			if (!(super.score() == ((double)super.getNombreSauves() / super.getSizeColony()))) {
				throw new InvariantError("score() == getNombreSauves / getSizeColony() not satisfied");
			}
		}

		// \invMin getNombreCrees() == getNombreActifs() + getNombreSauves() + getNombreMorts()
		if (!(super.getNombreCrees() == (super.getNombreActifs() + 
				super.getNombreSauves() + super.getNombreMorts()))) {
			throw new InvariantError("getNombreCrees() == getNombreActifs() + "
					+ "getNombreSauves() + getNombreMorts() not satisfied");
		}

		//TODO A voir si on doit vraiment l'enlever
		// \invMin isObstacle(x,y) == getLevel().getNature(x,y) != EMPTY
		//		for (int x = 0; x < super.getLevel().getWidth() ; x++) {
		//			for (int y = 0; y < super.getLevel().getHeight() ; y++) {
		//				if (!(super.isObstacle(x, y) == (super.getLevel().getNature(x, y) != Nature.EMPTY))) {
		//					throw new InvariantError("isObstacle(x,y) == "
		//							+ "getLevel().getNature(x,y) != EMPTY not satisfied");
		//				}
		//			}
		//		}

		// \inv getNombreTours() >= 0
		if (!(super.getNombreTours() >= 0)) {
			throw new InvariantError("getNombreTours() >= 0 not satisfied");
		}

		// \inv getNombreSauves() >= 0 AND getNombreSauves() <= getNombreCrees() 
		if (!(super.getNombreSauves() >= 0 && super.getNombreSauves() <= super.getNombreCrees())) {
			throw new InvariantError("getNombreSauves() >= 0 AND "
					+ "getNombreSauves() <= getNombreCrees() not satisfied");
		}

		// \inv getNombreMorts() >= 0 AND getNombreMorts() <= getNombreCrees() 
		if (!(super.getNombreMorts() >= 0 && super.getNombreMorts() <= super.getNombreCrees())) {
			throw new InvariantError("getNombreMorts() >= 0 AND "
					+ "getNombreMorts() <= getNombreCrees() not satisfied");
		}

		// \inv getNombreActifs() >= 0 AND getNombreActifs() <= getNombreCrees() 
		if (!(super.getNombreActifs() >= 0 && super.getNombreActifs() <= super.getNombreCrees())) {
			throw new InvariantError("getNombreActifs() >= 0 AND "
					+ "getNombreActifs() <= getNombreCrees() not satisfied");
		}

		// \inv getNombreCrees() >= 0 AND getNombreCrees() <= getSizeColony() 
		if (!(super.getNombreCrees() >= 0 && super.getNombreCrees() <= super.getSizeColony())) {
			throw new InvariantError("getNombreCrees() >= 0 AND "
					+ "getNombreCrees() <= getSizeColony() not satisfied");
		}

	}


	/** Observateurs **/
	@Override
	public Level getLevel() {
		return super.getLevel();
	}

	@Override
	public boolean isObstacle(int x, int y) {
		// \pre 0 <= x <= getLevel().getWidth() AND 0 <= y <= getLevel().getHeight()
		if (!(x >= 0 && x < super.getLevel().getWidth())) {
			throw new PreConditionError("isObstacle : "
					+ "0 <= x <= getLevel().getWidth() not satisfied");
		}
		if (!(y >= 0 && y < super.getLevel().getHeight())) {
			throw new PreConditionError("isObstacle : "
					+ "0 <= y <= getLevel().getHeight() not satisfied");
		}
		checkInvariant();
		boolean isObstacle = super.isObstacle(x, y);
		checkInvariant();
		return isObstacle;
	}

	@Override
	public boolean isLibre(int x, int y) {
		return super.isLibre(x, y);
		//TODO rajouter contrat si besoin
	}

	@Override
	public int getSizeColony(){
		checkInvariant();
		int sizeColony = super.getSizeColony();
		checkInvariant();
		return sizeColony;
	}

	@Override
	public int getSpawnSpeed() {
		checkInvariant();
		int spawnSpeed = super.getSpawnSpeed();
		checkInvariant();
		return spawnSpeed;
	}

	@Override
	public boolean gameOver() {
		checkInvariant();
		boolean gameOver = super.gameOver();
		checkInvariant();
		return gameOver;
	}

	@Override
	public double score() {
		// \pre gameOver()
		if (!super.gameOver()) {
			throw new PreConditionError("score : gameOver() not satisfied");
		}
		checkInvariant();
		double score = super.score();
		checkInvariant();
		return score;
	}

	@Override
	public int getNombreTours() {
		// \pre getLevel().isEditing() == false
		if (!(super.getLevel().isEditing() == false)) {
			throw new PreConditionError("getNombreTours : "
					+ "getLevel().isEditing() == false not satisfied");
		}
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("getNombreTours : !gameOver() not satisfied");
		}
		checkInvariant();
		int nombreTours = super.getNombreTours();
		checkInvariant();
		return nombreTours;
	}

	@Override
	public int getNombreToursFinal() {
		// \pre gameOver()
		if (!(super.gameOver())) {
			throw new PreConditionError("getNombreToursFinal : "
					+ "gameOver() not satisfied");
		}
		checkInvariant();
		int nombreToursFinal = super.getNombreToursFinal();
		checkInvariant();
		return nombreToursFinal;
	}

	@Override
	public Set<Integer> getLemmingsActifs() {
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("getLemmingsActifs : "
					+ "!gameOver() not satisfied");
		}
		checkInvariant();
		Set<Integer> lemmingsActifs = super.getLemmingsActifs();
		checkInvariant();
		return lemmingsActifs;
	}

	@Override
	public Lemming getLemming(int i) {
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("getLemming : "
					+ "!gameOver() not satisfied");
		}
		// \pre i \in getLemmingsActifs()
		if (!(super.getLemmingsActifs().contains(i))) {
			throw new PreConditionError("getLemming : "
					+ "i \\in getLemmingsActifs not satisfied");
		}
		checkInvariant();
		Lemming getLemming = super.getLemming(i);
		checkInvariant();
		return getLemming;
	}

	@Override
	public int getNombreSauves() {
		checkInvariant();
		int nombreSauves = super.getNombreSauves();
		checkInvariant();
		return nombreSauves;
	}

	@Override
	public int getNombreMorts() {
		checkInvariant();
		int nombreMorts = super.getNombreMorts();
		checkInvariant();
		return nombreMorts;
	}

	@Override
	public int getNombreActifs() {
		checkInvariant();
		int nombreActifs = super.getNombreActifs();
		checkInvariant();
		return nombreActifs;
	}

	@Override
	public int getNombreCrees() {
		checkInvariant();
		int nombreCrees = super.getNombreCrees();
		checkInvariant();
		return nombreCrees;
	}

	/** Constructors **/
	public void init(int sizeColony, int spawnSpeed) {
		// \pre sizeColony > 0
		if (!(sizeColony > 0)) {
			throw new PreConditionError("init : sizeColony > 0 not satisfied");
		}
		// \pre spawnSpeed > 0
		if (!(spawnSpeed> 0)) {
			throw new PreConditionError("init : spawnSpeed > 0 not satisfied");
		}

		//checkInvariant(); //TODO ptet decocher
		super.init(sizeColony, spawnSpeed);
		checkInvariant();

		// \post getSpawnSpeed() = spawnSpeed;
		if (!(super.getSpawnSpeed() == spawnSpeed)) {
			throw new PostConditionError("init : getSpawnSpeed() = spawnSpeed not satisfied");
		}
		// \post getSizeColony() = sizeColony;
		if (!(super.getSizeColony() == sizeColony)) {
			throw new PostConditionError("init : getSizeColony() = sizeColony not satisfied");
		}
		// \post getNombreSauves() = 0;
		if (!(super.getNombreSauves() == 0)) {
			throw new PostConditionError("init : getNombreSauves() = 0 not satisfied");
		}
		// \post getNombreMorts() = 0;
		if (!(super.getNombreMorts() == 0)) {
			throw new PostConditionError("init : getNombreMorts() = 0 not satisfied");
		}
		// \post getNombreActifs() = 0;
		if (!(super.getNombreActifs() == 0)) {
			throw new PostConditionError("init : getNombreActifs() = 0 not satisfied");
		}
		// \post getNombreCrees() = 0;
		if (!(super.getNombreCrees() == 0)) {
			throw new PostConditionError("init : getNombreCrees() = 0 not satisfied");
		}	
		// \post getNombreTours() = 0
		if (!(super.getNombreTours() == 0)) {
			throw new PostConditionError("init : getNombreTours() = 0 not satisfied");
		}
	}

	/** Operateurs **/
	public void step() {
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("step : !gameOver() not satisfied");
		}

		/* Capture */
		final int nombreCreePre = super.getNombreCrees();
		final int nombreActifsPre = super.getNombreActifs();
		final int nombreToursPre = super.getNombreTours();

		checkInvariant();
		super.step();
		checkInvariant();

		// \post getNombreTours() = getNombreTours()@pre + 1
		if (!(super.getNombreTours() == nombreToursPre + 1)) {
			throw new PostConditionError("step : getNombreTours() = "
					+ "getNombreTours()@pre + 1 not satisfied");
		}

		/* \post getNombresTours mod getSpawnSpeed() = 0 AND getNombresCrees() < getSizeColony()
		 * 		\implies getNombreCrees() = getNombreCrees()@pre+1
		 * 				 AND getNombreCrees() \in getLemmingsActifs()
		 * 				 AND getLemming(getNombreCrees()).getX() = x
		 * 				 AND getLemming(getNombreCrees()).getY() = y
		 * 				 AND isEntrance(x,y)
		 */
		if (super.getNombreTours() % super.getSpawnSpeed() == 0 && 
				super.getNombreCrees() < super.getSizeColony()) {

			System.out.println("size@pre "+nombreActifsPre);
			System.out.println("super size "+super.getNombreActifs());
			if (!(super.getLemmingsActifs().contains(super.getNombreCrees()))) {
				throw new PostConditionError("step : "
						+ "getNombreCrees() \\in getLemmingsActifs() not satisfied");
			}

			if (!(super.getNombreCrees() == nombreCreePre + 1)) {
				throw new PostConditionError("step : getNombreCrees() = "
						+ "getNombreCrees()@pre+1 not satisfied");
			}

			Lemming newLemming = super.getLemming(super.getNombreCrees());
			final int x = newLemming.getX();
			final int y = newLemming.getY();

			if (!(getLevel().isEntrance(x, y))) {
				throw new PostConditionError("step : new Lemming did not "
						+ "appear from the entrance");
			}
		}
	}

	@Override
	public void tuerLemming(int i) {
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("tuerLemming : !gameOver() not satisfied");
		}
		// \pre i \in getLemmingsActifs()
		if (!(super.getLemmingsActifs().contains(i))) {
			throw new PreConditionError("tuerLemming : i \\in getLemmingsActifs not satisfied");
		}


		/* Capture */
		final int nombreMortsPre = super.getNombreMorts();
		final int nombreActifsPre = super.getNombreActifs();
		final Set<Integer> lemmingsActifsPre = super.getLemmingsActifs();

		checkInvariant();
		super.tuerLemming(i);
		checkInvariant();

		/* \post tuerLemming(i) \implies getNombreActifs() = getNombreActifs()@pre-1
		 *						AND getNombreMorts() = getNombreMorts()@pre+1
		 *						AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}	
		 */
		if (!(super.getNombreActifs() == nombreActifsPre - 1)) {
			throw new PostConditionError("step : getNombreActifs() = "
					+ "getNombreActifs()@pre-1 not satisfied");
		}
		if (!(super.getNombreMorts() == nombreMortsPre + 1)) {
			throw new PostConditionError("step : getNombreMorts() = "
					+ "getNombreMorts()@pre+1 not satisfied");
		}

		lemmingsActifsPre.remove(i);
		if (!(super.getLemmingsActifs().containsAll(lemmingsActifsPre) 
				&& lemmingsActifsPre.containsAll(super.getLemmingsActifs()))) {
			throw new PostConditionError("step : getLemmingsActifs() = "
					+ "getLemmingsActifs()@pre - {i} not satisfied");		
		}		
	}

	@Override
	public void sauverLemming(int i) {
		// \pre !gameOver()
		if (super.gameOver()) {
			throw new PreConditionError("sauverLemming : !gameOver() not satisfied");
		}
		// \pre i \in getLemmingsActifs()
		if (!(super.getLemmingsActifs().contains(i))) {
			throw new PreConditionError("sauverLemming : i \\in getLemmingsActifs not satisfied");
		}
		// \pre getLemming(i).getX() = x AND getLemming(i).getY() = y AND getLevel().isExit(x, y)
		Lemming lemming = super.getLemming(i);
		final int x = lemming.getX();
		final int y = lemming.getY();
		if (!(super.getLevel().isExit(x, y))) {
			throw new PreConditionError("sauverLemming : "
					+ "getLemming(i).getX() = x AND getLemming(i).getY() = y "
					+ "AND getLevel().isExit(x, y) not satisfied");
		}

		/* Captures */
		final int nombreSauvesPre = super.getNombreSauves();
		final int nombreActifsPre = super.getNombreActifs();
		final Set<Integer> lemmingsActifsPre = super.getLemmingsActifs();

		checkInvariant();
		super.sauverLemming(i);
		checkInvariant();


		if (!(super.getNombreActifs() == nombreActifsPre - 1)) {
			throw new PostConditionError("step : getNombreActifs() = "
					+ "getNombreActifs()@pre-1 not satisfied");
		}
		if (!(super.getNombreSauves() == nombreSauvesPre+ 1)) {
			throw new PostConditionError("step : getNombreSauves() = "
					+ "getNombreSauves()@pre+1 not satisfied");
		}

		/* \post sauverLemming(i) \implies getNombreActifs() = getNombreActifs()@pre-1
		 *						  AND getNombreSauves() = getNombreSauves()@pre+1
		 *						  AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}					  
		 */
		lemmingsActifsPre.remove(i);
		if (!(super.getLemmingsActifs().containsAll(lemmingsActifsPre) 
				&& lemmingsActifsPre.containsAll(super.getLemmingsActifs()))) {
			throw new PostConditionError("step : getLemmingsActifs() = "
					+ "getLemmingsActifs()@pre - {i} not satisfied");		
		}		

	}

	// \pre !gameOver()
	// \post setSpawnSpeed(s) \implies getSpawnSpeed() = s
	public void setSpawnSpeed(int s){
		if(super.gameOver())
			throw new PreConditionError("setSpawnSpeed : !gameOver() not satisfied");
		checkInvariant();
		super.setSpawnSpeed(s);
		checkInvariant();
		if(super.getSpawnSpeed() != s)
			throw new PostConditionError("setSpawnSpeed : post setSpawnSpeed(s) implies getSpawnSpeed() = s not satisfied");
	}

	//TODO voir si il faut contrat pour l'ihm et observer
	/* perso flemme je fais juste checkinvariant au pire */
	@Override
	public void addObserver(IObserver obs) {
		super.addObserver(obs);
	}

	@Override
	public void deleteObserver(IObserver obs) {
		super.deleteObserver(obs);
	}

	@Override
	public void notifierObservateurs() {
		super.notifierObservateurs();
	}

}
