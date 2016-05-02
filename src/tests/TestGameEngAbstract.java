package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Level;

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
	 * aucune
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
	 * 
	 * Operation:
	 *  isObstacle(2,2)
	 *  
	 * Oracle:
	 * Pas de ContractError
	 */
	@Test
	public void testIsObstacle2_0() {
		String test = "Test GameEng Objectif 2.0";
		try {
			//condition initiale
			try {
				
			} catch (ContractError e) {
				
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}
	




}
