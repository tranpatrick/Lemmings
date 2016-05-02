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
	 *  Pas de ContractError &&
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
			// operation
			level.init(width, height);

			// oracle
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
	 * Cas 1_1: init: error w <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(0,25)
	 *  
	 *  Oracle:
	 *  ContractError pour init
	 */
	@Test
	public void testInit1_1() {
		String test = "Level Test Objectif 1.1";
		try {
			//operation
			level.init(0, 25);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			//oracle
			assertion(test, true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error h <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(25,0)
	 *  
	 *  Oracle:
	 *  ContractError pour init
	 */
	@Test
	public void testInit1_2() {
		String test = "Level Test Objectif 1.2";
		try {
			//operation
			level.init(25, 0);
			assertion(test+" : init doit echouer", false);
		} catch (ContractError e) {
			//oracle
			assertion(test, true);
		}
	}



	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_0: getNature positif
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * getNature(3,3)
	 *  
	 *  Oracle:
	 *  Pas de ContractError
	 */
	@Test
	public void testGetNature2_0() {
		String test = "Level Test Objectif 2.0";

		//Condition initiale
		try {
			level.init(10, 10);

			try {
				//Operation
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


	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_1: getNature error x < 0
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * getNature(-2,5)
	 *  
	 *  Oracle:
	 *  ContractError pour getNature
	 */
	@Test
	public void testGetNature2_1() {
		String test = "Level Test Objectif 2.1";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.getNature(-2, 5);
				assertion(test+": getNature doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_2: getNature error x >= getWidth()
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * getNature(15,5)
	 *  
	 *  Oracle:
	 *  ContractError pour getNature
	 */
	@Test
	public void testGetNature2_2() {
		String test = "Level Test Objectif 2.2";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.getNature(15, 5);
				assertion(test+": getNature doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}


	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_3: getNature error y < 0 
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * getNature(3,-2)
	 *  
	 *  Oracle:
	 *  ContractError pour getNature
	 */
	@Test
	public void testGetNature2_3() {
		String test = "Level Test Objectif 2.3";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.getNature(3, -2);
				assertion(test+": getNature doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: getNature
	 * 
	 * Cas 2_4: getNature error y >= getHeight() 
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * getNature(3,12)
	 *  
	 *  Oracle:
	 *  ContractError pour getNature
	 */
	@Test
	public void testGetNature2_4() {
		String test = "Level Test Objectif 2.4";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.getNature(3, 12);
				assertion(test+": getNature doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}



	/**
	 * Objectif 3: isExit
	 * 
	 * Cas 3_0: isExit positif
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * isExit(3,3)
	 *  
	 *  Oracle:
	 *  Pas de ContractError
	 */
	@Test
	public void testIsExit3_0() {
		String test = "Level Test Objectif 3.0";

		//Condition initiale
		try {
			level.init(10, 10);

			try {
				//Operation
				level.isExit(3, 3);

				//oracle
				assertion(test, true);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}


	/**
	 * Objectif 3: isExit
	 * 
	 * Cas 3_1: isExit error x < 0
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * isExit(-2,5)
	 *  
	 *  Oracle:
	 *  ContractError pour isExit
	 */
	@Test
	public void testIsExit3_1() {
		String test = "Level Test Objectif 3.1";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.isExit(-2, 5);
				assertion(test+": isExit doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: isExit
	 * 
	 * Cas 3_2: isExit error x >= getWidth()
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * isExit(15,5)
	 *  
	 *  Oracle:
	 *  ContractError pour isExit
	 */
	@Test
	public void testIsExit3_2() {
		String test = "Level Test Objectif 3.2";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.isExit(15, 5);
				assertion(test+": isExit doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}


	/**
	 * Objectif 3: isExit
	 * 
	 * Cas 3_3: isExit error y < 0 
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * isExit(3,-2)
	 *  
	 *  Oracle:
	 *  ContractError pour isExit
	 */
	@Test
	public void testIsExit3_3() {
		String test = "Level Test Objectif 3.3";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.isExit(3, -2);
				assertion(test+": isExit doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: isExit
	 * 
	 * Cas 3_4: isExit error y >= getHeight() 
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * isExit(3,12)
	 *  
	 *  Oracle:
	 *  ContractError pour isExit
	 */
	@Test
	public void testIsExit3_4() {
		String test = "Level Test Objectif 3.4";

		//Condition initiale
		try {
			level.init(10, 10);
			try {
				//operation
				level.isExit(3, 12);
				assertion(test+": isExit doit echouer", false);
			} catch (ContractError e) {
				//oracle
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	//TODO faire isEntrance

	/**
	 * Objectif 6: setNature
	 * 
	 * Cas 6_0: setNature positif 
	 * 
	 * Condition initial:
	 * init(10,10)
	 * 
	 * Operation:
	 * setNature(5, 5, Nature.DIRT)
	 *  
	 *  Oracle:
	 *  Pas de ContractError && getNature(5, 5) == Nature.DIRT
	 */
	@Test
	public void testSetNature6_0() {
		String test = "Level Test Objectif 6.0";
		try {
			// condition initiale
			level.init(10, 10);
			try {
				//operation
				level.setNature(5, 5, Nature.DIRT);
				// oracle
				assertion(test, level.getNature(5, 5) == Nature.DIRT);
				assertion(test, true);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			} 
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 6: setNature
	 * 
	 * Cas 6_1: setNature negatif x < 0 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * 
	 * Operation:
	 * setNature(-3, 5, Nature.DIRT)
	 *  
	 *  Oracle:
	 *  ContractError pour setNature
	 */
	@Test
	public void testSetNature6_1() {
		String test = "Level Test Objectif 6.1";
		try {
			// condition initiale
			level.init(10, 10);
			try {
				//operation
				level.setNature(-2, 5, Nature.DIRT);
				// oracle
				assertion(test+": setNature doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			} 
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 6: setNature
	 * 
	 * Cas 6_2: setNature error x > getWidth() 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * 
	 * Operation:
	 * setNature(15, 5, Nature.DIRT)
	 *  
	 *  Oracle:
	 *  ContractError pour setNature
	 */
	@Test
	public void testSetNature6_2() {
		String test = "Level Test Objectif 6.2";
		try {
			// condition initiale
			level.init(10, 10);
			try {
				//operation
				level.setNature(15, 5, Nature.DIRT);
				// oracle
				assertion(test+": setNature doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			} 
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 6: setNature
	 * 
	 * Cas 6_3: setNature error y < 0 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * 
	 * Operation:
	 * setNature(5, -5, Nature.DIRT)
	 *  
	 *  Oracle:
	 *  ContractError pour setNature
	 */
	@Test
	public void testSetNature6_3() {
		String test = "Level Test Objectif 6.3";
		try {
			// condition initiale
			level.init(10, 10);
			try {
				//operation
				level.setNature(5, -5, Nature.DIRT);
				// oracle
				assertion(test+": setNature doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			} 
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 6: setNature
	 * 
	 * Cas 6_4: setNature error y > getHeight() 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * 
	 * Operation:
	 * setNature(5, 15, Nature.DIRT)
	 *  
	 *  Oracle:
	 *  ContractError pour setNature
	 */
	@Test
	public void testSetNature6_4() {
		String test = "Level Test Objectif 6.4";
		try {
			// condition initiale
			level.init(10, 10);
			try {
				//operation
				level.setNature(5, 15, Nature.DIRT);
				// oracle
				assertion(test+": setNature doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			} 
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 7: setEntrance
	 * 
	 * Cas 7_0: setEntrance positif 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * 
	 * Operation:
	 * setEntrance(5, 5)
	 *  
	 *  Oracle:
	 *  Pas de ContractError && isEntrance(5, 5) == true
	 */
	@Test
	public void testSetEntrance7_0() {
		String test = "Level Test Objectif 7.0";
		try {
			//Condition initiale 
			level.init(10, 10);
			try {
				// Operation
				level.setEntrance(5, 5);

				//oracle
				assertion(test, level.isEntrance(5, 5) == true);
				assertion(test, true);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 7: setEntrance
	 * 
	 * Cas 7_1: setEntrance error la case au dessus n'est pas vide 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * setNature(5,4,Nature.DIRT)
	 * 
	 * Operation:
	 * setEntrance(5, 5)
	 *  
	 *  Oracle:
	 *  ContractError pour setEntrance
	 */
	@Test
	public void testSetEntrance7_1() {
		String test = "Level Test Objectif 7.1";
		try {
			//Condition initiale
			level.init(10, 10);
			level.setNature(5, 4, Nature.DIRT);
			try {
				// Operation
				level.setEntrance(5, 5);

				//oracle
				assertion(test+": setEntrance doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}



	/**
	 * Objectif 7: setEntrance
	 * 
	 * Cas 7_2: setEntrance errorla case en dessous n'est pas vide 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * setNature(5,6,Nature.METAL)
	 * 
	 * Operation:
	 * setEntrance(5, 5)
	 *  
	 *  Oracle:
	 *  ContractError pour setEntrance
	 */
	@Test
	public void testSetEntrance7_2() {
		String test = "Level Test Objectif 7.2";
		try {
			//Condition initiale
			level.init(10, 10);
			level.setNature(5, 6, Nature.METAL);
			try {
				// Operation
				level.setEntrance(5, 5);
				//oracle
				assertion(test+": setEntrance doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 8: setExit
	 * 
	 * Cas 8_0: setExit positif 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * setNature(5, 6, Nature.METAL)
	 * 
	 * Operation:
	 * setExit(5, 5)
	 *  
	 *  Oracle:
	 *  Pas de ContractError && isExit(5, 5) == true
	 */
	@Test
	public void testSetExit8_0() {
		String test = "Level Test Objectif 8.0";
		try {
			//Condition initiale 
			level.init(10, 10);
			level.setNature(5, 5, Nature.METAL);
			try {
				// Operation
				level.setExit(5, 5);

				//oracle
				assertion(test, level.isExit(5, 5) == true);
				assertion(test, true);
			} catch (ContractError e) {
				assertion(test+": "+e.getMessage(), false);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 8: setExit
	 * 
	 * Cas 8_1: setExit error la case en dessous n'est pas METAL 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * setNature(5,6,Nature.DIRT)
	 * 
	 * Operation:
	 * setExit(5, 5)
	 *  
	 *  Oracle:
	 *  ContractError pour setExit
	 */
	@Test
	public void testSetExit8_1() {
		String test = "Level Test Objectif 8.1";
		try {
			//Condition initiale
			level.init(10, 10);
			level.setNature(5,6,Nature.DIRT);
			try {
				// Operation
				level.setExit(5, 5);
				//oracle
				assertion(test+": setExit doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}



	/**
	 * Objectif 8: setExit
	 * 
	 * Cas 8_2: setExit error la case au dessus n'est pas vide 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * setNature(5,4,Nature.METAL)
	 * 
	 * Operation:
	 * setExit(5, 5)
	 *  
	 *  Oracle:
	 *  ContractError pour setExit
	 */
	@Test
	public void testSetExit8_2() {
		String test = "Level Test Objectif 8.2";
		try {
			//Condition initiale
			level.init(10, 10);
			level.setNature(5, 4, Nature.METAL);
			try {
				// Operation
				level.setExit(5, 5);
				//oracle
				assertion(test+": setExit doit echouer", false);
			} catch (ContractError e) {
				assertion(test, true);
			}
		} catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}


	/**
	 * Objectif 9: goPlay
	 * 
	 * Cas 9_0: goPlay positif 
	 * 
	 * Condition initiale:
	 * init(10,10)
	 * for (int x = 0; x<level.getWidth(); x++) {
	 * 		for (int y = 0; y<level.getHeight(); y++) {
	 *			if (x == 0 || x == level.getWidth()-1 
	 *						|| y == 0 || y == level.getHeight()-1) {
	 *					level.setNature(x, y, Nature.METAL);
	 *			}
	 *		}
	 *	}
	 *	level.setEntrance(8, 5);
	 *	level.setNature(5, 6, Nature.METAL);
	 *	level.setExit(5, 5);
	 * 
	 * Operation:
	 * goPlay()
	 *  
	 *  Oracle:
	 *  Pas de ContractError && isEditing() == false
	 */
	@Test
	public void testGoPlay() {
		String test = "Test Level Objectif 9.0";
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
			try {
				level.goPlay();
				assertion(test, level.isEditing() == false);
			}
			catch (ContractError e) {
				assertion(test+" :"+e.getMessage(),false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
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