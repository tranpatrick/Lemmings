package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Level;

public abstract class TestGameEngAbstract {

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
	
	@Test
	public void testInit() {
		try {
			int sizeColony = 50;
			int spawnSpeed = 5;
			gameEng.init(sizeColony,spawnSpeed);
			// TODO ca coince juste apres le init, ptet test mal fait
			// msg d'erreur Invariant failed: getNombreActifs() == | getLemmingActifs() | not satisfied
			assertTrue(getGameEng().getSpawnSpeed() == spawnSpeed);
			assertTrue(getGameEng().getSizeColony() == sizeColony);
			assertTrue(getGameEng().getNombreSauves() == 0);
			assertTrue(getGameEng().getNombreMorts() == 0);
			assertTrue(getGameEng().getNombreActifs() == 0);
			assertTrue(getGameEng().getNombreCrees() == 0);
			assertTrue(getGameEng().getNombreTours() == 0);
			assertTrue(getGameEng().isAnnihilation() == false);
			
			assertTrue(true);
		} catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void testInitFail1() {
		try {
			int sizeColony = 0;
			int spawnSpeed = 5;
			gameEng.init(sizeColony,spawnSpeed);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void testInitFail2() {
		try {
			int sizeColony = 5;
			int spawnSpeed = 0;
			gameEng.init(sizeColony,spawnSpeed);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail3() {
		try {
			int sizeColony = -5;
			int spawnSpeed = 5;
			gameEng.init(sizeColony,spawnSpeed);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail4() {
		try {
			int sizeColony = 10;
			int spawnSpeed = -5;
			gameEng.init(sizeColony,spawnSpeed);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}
	
	
}
