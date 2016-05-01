package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Level.Nature;

public abstract class TestJoueurAbstract extends AssertionTests {

	private Joueur joueur;
	private GameEng gameEng;

	protected TestJoueurAbstract(){
		joueur = null;
		gameEng = null;
	}

	protected final Joueur getJoueur(){
		return joueur;
	}

	protected final GameEng getGameEng(){
		return gameEng;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests(){
		joueur = null;
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
	 *  init()
	 *  
	 * Oracle:
	 *  Pas d'exception &&
	 *  getNbJetons("DIGGER") = 10
	 *  getNbJetons("CLIMBER") = 10
	 *  getNbJetons("BUILDER") = 10
	 *  getNbJetons("FLOATER") = 10
	 *  getNbJetons("BOMBER") = 10
	 *  getNbJetons("STOPPER") = 10
	 *  getNbJetons("BASHER") = 10
	 *  getNbJetons("MINER") = 10
	 */
	@Test
	public void testInit1_0() {
		String test = "Joueur Test Objectif 1.0";
		try {
			joueur.init();
			assertion(test+" :\\post getNbJetons(\"DIGGER\") == 10", joueur.getNbJetons("DIGGER") == 10);
			assertion(test+" :\\post getNbJetons(\"CLIMBER\") == 10", joueur.getNbJetons("CLIMBER") == 10);
			assertion(test+" :\\post getNbJetons(\"BUILDER\") == 10", joueur.getNbJetons("BUILDER") == 10);
			assertion(test+" :\\post getNbJetons(\"FLOATER\") == 10", joueur.getNbJetons("FLOATER") == 10);
			assertion(test+" :\\post getNbJetons(\"BOMBER\") == 10", joueur.getNbJetons("BOMBER") == 10);
			assertion(test+" :\\post getNbJetons(\"STOPPER\") == 10", joueur.getNbJetons("STOPPER") == 10);
			assertion(test+" :\\post getNbJetons(\"BASHER\") == 10", joueur.getNbJetons("BASHER") == 10);
			assertion(test+" :\\post getNbJetons(\"MINER\") == 10", joueur.getNbJetons("MINER") == 10);
		} catch (ContractError e) {
			assertion(test+" : "+e.getMessage(), false);
		}
	}


}
