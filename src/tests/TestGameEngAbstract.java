package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;

public abstract class TestGameEngAbstract extends AssertionTests{

	private GameEng gameEng;
	private Level level;

	protected TestGameEngAbstract() {
		level = null;
		gameEng = null;
	}

	protected final Level getLevel(){
		return level;
	}

	protected final GameEng getGameEng() {
		return gameEng;

	}

	protected final void setLevel(Level level) {
		this.level = level;
	}

	protected final void setGameEng(GameEng gameEng) {
		this.gameEng = gameEng;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		level = null;
		gameEng = null;
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_0: init: positif
	 * 
	 * Condition initial:
	 * getLevel().goPlay()
	 * 
	 * Operation:
	 *  init(50,5)
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getSizeColony == 50 &&
	 * getSpawnSpeed() == 5 &&
	 * getNombreSauves() = 0;
	 * getNombreMorts() = 0;
	 * getNombreActifs() = 0;
	 * getNombreCrees() = 0;
	 * getNombreTours() = 0
	 * isAnnihilation = false;
	 */
	@Test
	public void testInit1_0() {
		String test = "Test GameEng Objectif 1.0";
		try {
			//condition initiale
			gameEng.getLevel().goPlay();
			try {		
				//operation
				int sizeColony = 50;
				int spawnSpeed = 5;

				gameEng.init(sizeColony,spawnSpeed);
				//oracle
				assertion(test,getGameEng().getSpawnSpeed() == spawnSpeed);
				assertion(test,getGameEng().getSizeColony() == sizeColony);
				assertion(test,getGameEng().getNombreSauves() == 0);
				assertion(test,getGameEng().getNombreMorts() == 0);
				assertion(test,getGameEng().getNombreActifs() == 0);
				assertion(test,getGameEng().getNombreCrees() == 0);
				assertion(test,getGameEng().getNombreTours() == 0);
				assertion(test,getGameEng().isAnnihilation() == false);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error sizeColony <= 0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(0,5)
	 *  
	 * Oracle:
	 * ContractError pour init
	 */
	@Test
	public void testInit1_1() {
		String test = "Test GameEng Objectif 1.1";
		try {
			//operation
			int sizeColony = 0;
			int spawnSpeed = 5;
			gameEng.init(sizeColony,spawnSpeed);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			//oracle
			assertion(test+": "+e.getMessage(), true);
		}
	}


	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2 init: error spawnSpeed <= 0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(10,-2)
	 *  
	 * Oracle:
	 * ContractError pour init
	 */
	@Test
	public void testInit1_2() {
		String test = "Test GameEng Objectif 1.2";
		try {
			//operation
			int sizeColony = 10;
			int spawnSpeed = -2;
			gameEng.init(sizeColony,spawnSpeed);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			//oracle
			assertion(test+": "+e.getMessage(), true);
		}
	}

	/**
	 * Objectif 2: isObstacle
	 * 
	 * Cas 2_0 isObstacle positif
	 * 
	 * Condition initial:
	 * beforeTests() //level init(10,10)
	 * init(10,5)
	 * gameEng.getLevel().setNature(2, 2, Nature.DIRT)
	 * 
	 * Operation:
	 *  isObstacle(2,2)
	 *  
	 * Oracle:
	 * Pas de ContractError && isObstacle(2,2) == true
	 */
	@Test
	public void testIsObstacle2_0() {
		String test = "Test GameEng Objectif 2.0";
		try {
			//condition initiale
			gameEng.init(10, 5);
			gameEng.getLevel().setNature(2, 2, Nature.DIRT);
			try {
				//Operation
				boolean isObstacle = gameEng.isObstacle(2, 2);
				//oracle
				assertion(test, isObstacle);
			} catch (ContractError e) {
				assertion(test+" : "+e.getMessage(), false);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: isObstacle
	 * 
	 * Cas 2_1 isObstacle positif
	 * 
	 * Condition initial:
	 * beforeTests() //level init(10,10)
	 * init(10,5)
	 * 
	 * Operation:
	 *  isObstacle(2,2)
	 *  
	 * Oracle:
	 * Pas de ContractError && isObstacle(2,2) == false
	 */
	@Test
	public void testIsObstacle2_1() {
		String test = "Test GameEng Objectif 2.1";
		try {
			//condition initiale
			gameEng.init(10, 5);
			try {
				//Operation
				boolean isObstacle = gameEng.isObstacle(2, 2);
				//oracle
				assertion(test, isObstacle == false);
			} catch (ContractError e) {
				assertion(test+" : "+e.getMessage(), false);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: isObstacle
	 * 
	 * Cas 2_2 isObstacle error x > getLevel().getWidth()
	 * 
	 * Condition initial:
	 * beforeTests() //level init(10,10)
	 * init(10,5)
	 * 
	 * Operation:
	 *  isObstacle(15,2)
	 *  
	 * Oracle:
	 * ContractError pour isObstacle
	 */
	@Test
	public void testIsObstacle2_2() {
		String test = "Test GameEng Objectif 2.2";
		try {
			//condition initiale
			gameEng.init(15, 5);
			try {
				//Operation
				gameEng.isObstacle(15, 2);
				assertion(test+" doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test+" : "+e.getMessage(), true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * /**
	 * Objectif 3: step
	 * 
	 * Cas 3_0 step positif
	 * 
	 * Condition initial:
	 * beforeTests() //level init(10,10)
	 * init(10,5)
	 * getLevel().goPlay()
	 * for (int i = 0; i < 4; i++)
	 * 	step();
	 * 
	 * Operation:
	 * step()
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getNombreCrees() == getNombreCrees()@pre + 1 &&
	 * getNombresCrees() \in getLemmingsActifs() &&
	 * getLemming(getNombresCrees()).getX() == 8 &&
	 * getLemming(getNombresCrees()).getY() == 5 &&
	 * getLevel().isEntrance(8,5) == true
	 *  
	 */
	@Test
	 public void testStep3_0() {
		 String test = "Test Step Objectif 3.0";
		 try {
			 //Condition initiale
			 gameEng.init(10,5);
			 gameEng.getLevel().goPlay();
			 for(int i= 0; i<4; i++)
				 gameEng.step();
			 try {
				 final int nbCrees = gameEng.getNombreCrees();
				 //operation
				 gameEng.step();
				 // oracle
				 assertion(test, gameEng.getNombreCrees() == nbCrees + 1);
				 assertion(test, gameEng.getLemmingsActifs().contains(gameEng.getNombreCrees()));
				 assertion(test, gameEng.getLemming(gameEng.getNombreCrees()).getX() == 8);
				 assertion(test, gameEng.getLemming(gameEng.getNombreCrees()).getY() == 5);
				 assertion(test, gameEng.getLevel().isEntrance(8, 5));
			 }catch (ContractError e) {
				 assertion(test+": "+e.getMessage(), false);
			 }
		 } catch (ContractError e) {
				assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		 }
	 }
	
	/**
	 * /**
	 * Objectif 3: step
	 * 
	 * Cas 3_0 step error la partie est finie
	 * 
	 * Condition initial:
	 * beforeTests() //level init(10,10)
	 * init(1,5)
	 * getLevel().goPlay()
	 * for (int i = 0; i < 6; i++)
	 * 	step();
	 * tuerLemming(1)
	 * Operation:
	 * step()
	 *  
	 * Oracle:
	 * ContractError pour step
	 *  
	 */
	@Test
	 public void testStep3_1() {
		 String test = "Test Step Objectif 3.1";
		 try {
			 //Condition initiale
			 gameEng.init(1,5);
			 gameEng.getLevel().goPlay();
			 for(int i= 0; i<6; i++)
				 gameEng.step();
			 gameEng.tuerLemming(1);
			 try {
				 //operation
				 gameEng.step();
				 assertion(test+" : step doit echouer",false);
			 }catch (ContractError e) {
				 //oracle
				 assertion(test, true);
			 }
		 } catch (ContractError e) {
				assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		 }
	 }

	




}
