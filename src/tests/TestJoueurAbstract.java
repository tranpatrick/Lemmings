package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming.Type;
import model.lemmings.services.Level;

public abstract class TestJoueurAbstract extends AssertionTests {

	private Joueur joueur;
	private GameEng gameEng;
	private Level level;

	protected TestJoueurAbstract(){
		joueur = null;
		gameEng = null;
		level = null;
	}

	protected final Joueur getJoueur(){
		return joueur;
	}

	protected final GameEng getGameEng(){
		return gameEng;
	}

	protected final Level getLevel(){
		return level;
	}

	protected final void setJoueur(Joueur joueur){
		this.joueur = joueur;
	}

	protected final void setGameEng(GameEng gameEng){
		this.gameEng = gameEng;
	}

	protected final void setLevel(Level level){
		this.level = level;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests(){
		joueur = null;
		gameEng = null;
		level = null;
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_0: init: positif
	 * 
	 * Condition initiale:
	 * aucune
	 * 
	 * Operation:
	 * init(10)
	 *  
	 * Oracle:
	 *  Pas de ContractError &&
	 *  getNbJetons("DIGGER") = 10 &&
	 *  getNbJetons("CLIMBER") = 10 &&
	 *  getNbJetons("BUILDER") = 10 &&
	 *  getNbJetons("FLOATER") = 10 &&
	 *  getNbJetons("BOMBER") = 10 && 
	 *  getNbJetons("STOPPER") = 10 &&
	 *  getNbJetons("BASHER") = 10 &&
	 *  getNbJetons("MINER") = 10
	 */
	@Test
	public void testInit1_0() {
		String test = "Joueur Test Objectif 1.0";
		try {
			joueur.init(10);
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

	/**
	 * Objectif 2: select
	 * 
	 * Cas 2_0: select positif
	 * 
	 * Condition initiale:
	 * joueur.init(10)
	 * gameEng.init(10,5) 
	 * level.init(25,25)
	 * 
	 * Operation:
	 * select(2,3)
	 *  
	 *  Oracle:
	 *  Pas de ContractError
	 */
	@Test
	public void testSelect2_0(){
		String test = "Joueur Test Objectif 2.0";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			//level.init(25, 25);
			try{
				//Operation select
				joueur.select(2, 3);

				//Oracle
				assertion(test, true);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), false);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: select
	 * 
	 * Cas 2_1: error x < 0
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * 
	 * 
	 * Operation:
	 * select(-2,3)
	 *  
	 * Oracle:
	 * ContractError à l'exécution de select
	 */
	@Test
	public void testSelect2_1(){
		String test = "Joueur Test Objectif 2.1";

		//Condition initiale
		try{
			joueur.init(10);
			/* faits dans beforeTests */
			//gameEng.init(10, 5);
			//level.init(25, 25);
			try{
				//Operation select
				joueur.select(-2, 3);

				//Oracle
				assertion(test+" : select doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: select
	 * 
	 * Cas 2_2: error x >= getGameEng().getLevel().getHeight()
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * 
	 * Operation:
	 * select(26,3)
	 *  
	 * Oracle:
	 * ContractError à l'exécution de select
	 */
	@Test
	public void testSelect2_2(){
		String test = "Joueur Test Objectif 2.2";

		//Condition initiale
		try{
			joueur.init(10);
			/* faits dans beforeTests */
			//gameEng.init(10, 5);
			//level.init(25, 25);
			try{
				//Operation select
				joueur.select(26, 3);

				//Oracle
				assertion(test+" : select doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: select
	 * 
	 * Cas 2_3: error y < 0
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * 
	 * Operation:
	 * select(2,-3)
	 *  
	 * Oracle:
	 * ContractError à l'exécution de select
	 */
	@Test
	public void testSelect2_3(){
		String test = "Joueur Test Objectif 2.3";

		//Condition initiale
		try{
			joueur.init(10);
			/* faits dans beforeTests */
			//gameEng.init(10, 5);
			//level.init(25, 25);
			try{
				//Operation select
				joueur.select(2, -3);

				//Oracle
				assertion(test+" : select doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: select
	 * 
	 * Cas 2_4: error y >= getGameEng().getLevel().getWidth()
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * 
	 * Operation:
	 * select(2,26)
	 *  
	 * Oracle:
	 * ContractError à l'exécution de select
	 */
	@Test
	public void testSelect2_4(){
		String test = "Joueur Test Objectif 2.4";

		//Condition initiale
		try{
			joueur.init(10);
			/* faits dans beforeTests */
			//gameEng.init(10, 5);
			//level.init(25, 25);
			try{
				//Operation select
				joueur.select(2, 26);

				//Oracle
				assertion(test+" : select doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: changeClasse
	 * 
	 * Cas 3_0: changeClasse valide
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * getGameEng().step() (6 fois)
	 * 
	 * Operation:
	 * changeClasse(getGameEng().getLemming(1), "DIGGER");
	 *  
	 * Oracle:
	 * Pas de ContractError && 
	 * getNbJetons("DIGGER") == 9 &&
	 * getGameEng().getLemming(1).getType() == Type.CREUSEUR
	 */
	@Test
	public void testChangeClasse3_0(){
		String test = "Joueur Test Objectif 3.0";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			//level.init(25, 25);
			for(int i=0; i<6; i++)
				gameEng.step();
			try{
				//Operation select
				joueur.changeClasse(gameEng.getLemming(1), "DIGGER");

				//Oracle
				assertion("\\post getNbJetons(t) = getNbJetons(t)@pre-1", joueur.getNbJetons("DIGGER") == 9);
				assertion("\\post getGameEng().getLemming(1).getType() = CREUSEUR", gameEng.getLemming(1).getType() == Type.CREUSEUR);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), false);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: changeClasse
	 * 
	 * Cas 3_1: erreur type != "DIGGER"
	 * 				|| type = "CLIMBER"
	 * 				|| type = "BUILDER"
	 * 				|| type = "FLOATER"
	 * 				|| type = "BOMBER"
	 * 				|| type = "STOPPER"
	 * 				|| type = "BASHER"
	 * 				|| type = "MINER"
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * getGameEng().step() (6 fois)
	 * 
	 * Operation:
	 * changeClasse(getGameEng().getLemming(1), "NIMPORTEQUOI");
	 *  
	 * Oracle:
	 * ContractError pour changeClasse
	 */
	@Test
	public void testChangeClasse3_1(){
		String test = "Joueur Test Objectif 3.1";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			//level.init(25, 25);
			for(int i=0; i<6; i++)
				gameEng.step();
			try{
				//Operation select
				joueur.changeClasse(gameEng.getLemming(1), "NIMPORTEQUOI");

				//Oracle
				assertion(test+" : changeClasse doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: changeClasse
	 * 
	 * Cas 3_2: erreur joueur.getNbJetons("type") <= 0
	 * 
	 * Condition initiale:
	 * init(1)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * getGameEng().step() (6 fois)
	 * changeClasse(getGameEng().getLemming(1), "DIGGER");
	 * getGameEng().step() (6 fois)

	 * Operation:
	 * changeClasse(getGameEng().getLemming(2), "DIGGER");
	 *  
	 * Oracle:
	 * ContractError pour changeClasse
	 */
	@Test
	public void testChangeClasse3_2(){
		String test = "Joueur Test Objectif 3.2";

		//Condition initiale
		try{
			joueur.init(1);
			//gameEng.init(10, 5);
			//level.init(25, 25);
			for(int i=0; i<6; i++)
				gameEng.step();
			joueur.changeClasse(gameEng.getLemming(1), "DIGGER");
			try{
				//Operation select
				joueur.changeClasse(gameEng.getLemming(2), "DIGGER");

				//Oracle
				assertion(test+" : changeClasse doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 4: changeSpawnSpeed
	 * 
	 * Cas 4_0: changeSpawnSpeed valide
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * 
	 * 
	 * Operation:
	 * changeSpawnSpeed(10)
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getGameEng().getSpawnSpeed() == 10
	 */
	@Test
	public void testChangeSpawnSpeed4_0(){
		String test = "Joueur Test Objectif 4.0";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			try{
				//Operation select
				joueur.changeSpawnSpeed(5);

				//Oracle
				assertion("\\post getGameEng().getSpawnSpeed() == s", gameEng.getSpawnSpeed() == 5);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), false);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 4: changeSpawnSpeed
	 * 
	 * Cas 4_1: erreur x <= 0
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * 
	 * 
	 * Operation:
	 * changeSpawnSpeed(-1)
	 *  
	 * Oracle:
	 * ContractError pour changeSpawnSpeed 
	 */
	@Test
	public void testChangeSpawnSpeed4_1(){
		String test = "Joueur Test Objectif 4.1";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			try{
				//Operation select
				joueur.changeSpawnSpeed(-1);

				//Oracle
				assertion(test+": changeSpawnSpeed doit echouer", false);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), true);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 5: Annihilition 
	 * 
	 * Cas 5_0: annihilation valide
	 * 
	 * Condition initiale:
	 * init(10)
	 * getGameEng().init(10,5) 
	 * getGameEng().getLevel().init(25,25)
	 * getGameEng().step() (6 fois)
	 * 
	 * 
	 * Operation:
	 * annihilation()
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * forall i in getGameEng().getLemmingsActifs(), getGameEng().getLemming(i).isExploseur() == true
	 */
	@Test
	public void testAnihilation5_0(){
		String test = "Joueur Test Objectif 5.0";

		//Condition initiale
		try{
			joueur.init(10);
			//gameEng.init(10, 5);
			//level.init(25, 25);
			for(int i=0; i<6; i++)
				gameEng.step();
			try{
				//Operation select
				joueur.annihilation();

				//Oracle
				for(int i : gameEng.getLemmingsActifs())
					assertion("\\post forall i in getGameEng().getLemmingsActifs(), getGameEng().getLemming(i).isExploseur() == true",
							gameEng.getLemming(i).isExploseur() == true);
			}catch(ContractError e){
				assertion(test+": "+e.getMessage(), false);
			}
		}catch(ContractError e){
			assertion(test+": erreur à l'initialisation du test: "+e.getMessage(), false);
		}
	}

}
