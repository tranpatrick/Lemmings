package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;



public abstract class TestLevelAbstract extends AssertionTests{

	private Level level;

	protected TestLevelAbstract() {
		level = null;
	}

	protected final Level getLevel() {
		return level;
	}

	protected final void setLevel(Level level) {
		this.level = level;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		level= null;
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
	 *  init(25,20)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  getWidth() == 20 &&
	 *  getHeight() == 25 &&
	 *  isEditing() == true &&
	 *  \forall (x,y) getNature(x,y) == Nature.EMPTY
	 */
	@Test
	public void testInit1_0() {
		String test = "Level Test Objectif 1.0";
		try {
			int height = 20;
			int width = 25;
			level.init(width, height);
			assertion(test+" :\\post level.getHeight() == height", level.getHeight() == height);
			assertion(test+" :\\post level.getWidth()  == width", level.getWidth()  == width);
			assertion(test+" :\\post level.isEditing() == true", level.isEditing() == true);
			for (int x = 0; x < level.getWidth() ; x++)
				for (int y = 0; y < level.getHeight() ; y++)
					assertion(test+" :\\post level.getNature("+x+", "+y+") == Nature.EMPTY", level.getNature(x, y) == Nature.EMPTY);
		} catch (ContractError e) {
			assertion(test+" : "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error width <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(0,25)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_1() {
		String test = "Level Test Objectif 1.1";
		try {
			level.init(0, 25);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			assertion(test, true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error height <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(25,0)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_2() {
		String test = "Level Test Objectif 1.2";
		try {
			level.init(25, 0);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			assertion(test, true);
		}
	}



	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_0: getNature positif
	 * 
	 * Condition initial:
	 * init(20,20)
	 * 
	 * Operation:
	 * getNature(2,3)
	 * getNature(3,3)
	 *  
	 *  Oracle:
	 *  Pas d'exception
	 */
	@Test
	public void testGetNature2_0() {
		String test = "Level Test Objectif 2.0";

		//Condition initiale
		try {
			level.init(10, 10);

			try {
				//Operation
				level.getNature(2, 3);
				level.getNature(3, 3);
				
				//oracle
				assertion(test, true);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	@Test
	public void testGetNatureFail1() {
		try {
			level.init(10, 10);
			level.getNature(-2, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetNatureFail2() {
		try {
			level.init(10, 10);
			level.getNature(15, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}


	@Test
	public void testGetNatureFail3() {
		try {
			level.init(10, 10);
			level.getNature(0, 15);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetNatureFail4() {
		try {
			level.init(10, 10);
			level.getNature(15, 15);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetNatureFail5() {
		try {
			level.init(10, 10);
			level.getNature(-5, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetNatureFail6() {
		try {
			level.init(10, 10);
			level.getNature(0, -5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetNatureFail7() {
		try {
			level.init(10, 10);
			level.getNature(-5, -5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExit() {
		try {
			level.init(10, 10);
			level.isExit(2, 3);
			level.isExit(3, 3);
			level.isExit(7, 3);
			assertTrue(true);
		} catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testIsExitFail1() {
		try {
			level.init(10, 10);
			level.isExit(-2, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExitFail2() {
		try {
			level.init(10, 10);
			level.isExit(15, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}


	@Test
	public void testIsExitFail3() {
		try {
			level.init(10, 10);
			level.isExit(0, 15);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExitFail4() {
		try {
			level.init(10, 10);
			level.isExit(15, 15);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExitFail5() {
		try {
			level.init(10, 10);
			level.isExit(-5, 0);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExitFail6() {
		try {
			level.init(10, 10);
			level.isExit(0, -5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIsExitFail7() {
		try {
			level.init(10, 10);
			level.isExit(-5, -5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetNature() {
		try {
			level.init(10, 10);
			level.setNature(5, 5, Nature.DIRT);
			assertTrue(level.getNature(5, 5) == Nature.DIRT);
			level.setNature(5, 5, Nature.METAL);
			assertTrue(level.getNature(5, 5) == Nature.METAL);
			level.setNature(5, 5, Nature.EMPTY);
			assertTrue(level.getNature(5, 5) == Nature.EMPTY);
			level.setNature(4, 2, Nature.METAL);
			assertTrue(level.getNature(4, 2) == Nature.METAL);
			assertTrue(true);
		} catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testSetNatureFail1() {
		try {
			level.init(10, 10);
			level.setNature(5, 15, Nature.DIRT);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetNatureFail2() {
		try {
			level.init(10, 10);
			level.setNature(5, -5, Nature.DIRT);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetEntrance() {
		try {
			level.init(10, 10);
			level.setEntrance(5, 5);
			assertTrue(level.isEntrance(5, 5) == true);
			assertTrue(true);
		} catch (ContractError e) {
			assertTrue(false);
		}
	}

	@Test
	public void testSetEntranceFail1() {
		try {
			level.init(10, 10);
			level.setNature(5, 4, Nature.DIRT);
			level.setEntrance(5, 5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetEntranceFail2() {
		try {
			level.init(10, 10);
			level.setNature(5, 6, Nature.METAL);
			level.setEntrance(5, 5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetExit() {
		try {
			level.init(10, 10);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			assertTrue(level.isExit(5, 5) == true);
			assertTrue(true);
		} catch (ContractError e) {
			assertTrue(false);
		}
	}

	@Test
	public void testSetExitFail1() {
		try {
			level.init(10, 10);
			level.setNature(5, 6, Nature.DIRT);
			level.setExit(5, 5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetExitFail2() {
		try {
			level.init(10, 10);
			level.setExit(5, 5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGoPlay() {
		try {
			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(8, 5);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.goPlay();
			assertTrue(level.isEditing() == false);
		}
		catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testGoPlayFail1() {
		try {
			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(5, 5);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.goPlay();
			assertTrue(false);
		}
		catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGoPlayFail2() {
		try {
			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.goPlay();
			assertTrue(false);
		}
		catch (ContractError e) {
			assertTrue(true);
		}
	}


	@Test
	public void testGoPlayFail3() {
		try {
			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(5, 5);
			level.goPlay();
			assertTrue(false);
		}
		catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemove() {
		try {

			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(8, 5);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.setNature(7, 7, Nature.DIRT);
			level.goPlay();
			level.remove(7, 7);
			assertTrue(level.getNature(7, 7) == Nature.EMPTY);
			assertTrue(true);
		} catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testRemoveFail1() {
		try {
			level.setNature(7, 7, Nature.DIRT);
			level.remove(7, 7);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveFail2() {
		try {
			level.setNature(7, 7, Nature.DIRT);
			level.remove(15, 7);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testBuild() {
		try {

			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(8, 5);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.setNature(7, 7, Nature.EMPTY);
			level.goPlay();
			level.build(7, 7);
			assertTrue(level.getNature(7, 7) == Nature.DIRT);
			assertTrue(true);
		} catch (ContractError e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}

	@Test
	public void testBuildFail1() {
		try {
			testInit1_0();
			level.setNature(7, 7, Nature.DIRT);
			level.build(7, 7);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testBuildFail2() {
		try {
			level.init(10, 10);
			for (int x = 0; x<level.getWidth(); x++) {
				for (int y = 0; y<level.getHeight(); y++) {
					if (x == 0 || x == level.getWidth()-1 
							|| y == 0 || y == level.getHeight()-1) {
						level.setNature(x, y, Nature.METAL);
					}
				}
			}
			level.setEntrance(8, 5);
			level.setNature(5, 6, Nature.METAL);
			level.setExit(5, 5);
			level.setNature(7, 7, Nature.EMPTY);
			level.goPlay();
			level.build(8,5);
			assertTrue(false);
		} catch (ContractError e) {
			assertTrue(true);
		}
	}





}