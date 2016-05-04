package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.lemmings.contract.ContractError;
import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Lemming.Direction;
import model.lemmings.services.Lemming.Type;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;

public abstract class TestLemmingAbstract extends AssertionTests{

	private Lemming lemming;
	private Level level;
	private GameEng gameEng;

	protected TestLemmingAbstract(){
		lemming = null;
		level = null;
		gameEng = null;
	}

	protected final Lemming getLemming(){
		return lemming;
	}
	protected final Level getLevel(){
		return level;
	}

	protected final GameEng getGameEng(){
		return gameEng;
	}

	protected final void setLemming(Lemming lemming){
		this.lemming = lemming; 
	}

	protected final void setLevel(Level level){
		this.level = level; 
	}

	protected final void setGameEng(GameEng gameEng){
		this.gameEng = gameEng; 
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests(){
		lemming = null;
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
	 *  init(1)
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getDirection() = DROITIER &&
	 * getType() = MARCHEUR &&
	 * getId() = 1 &&
	 * isGrimpeur = false &&
	 * isExploseur = false &&
	 * isFlotteur = false &&
	 * tombeDepuis() = 0 &&
	 * isBuilder() = false &&
	 * iCurrentlyBuilding() = false &&
	 * isCurrentlyClimbing() = false &&
	 * nbCreuseTunnel() = 0 &&
	 * getNombreToursBuilder() = 0 &&
	 * getNombreDallesPosees() = 0 
	 */
	@Test
	public void testInit1_0(){
		String test = "Test Lemming Objectif 1.0";
		try{
			lemming.init(1);
			assertion(test+" :\\post getDirection() == DROITIER", lemming.getDirection() == Direction.DROITIER);
			assertion(test+" :\\post getType() == TOMBEUR", lemming.getType() == Type.TOMBEUR);
			assertion(test+" :\\post getId() == 1", lemming.getId() == 1);
			assertion(test+" :\\post isGrimpeur() == false", lemming.isGrimpeur() == false);
			assertion(test+" :\\post isExploseur() == false", lemming.isExploseur() == false);
			assertion(test+" :\\post isFlotteur() == false", lemming.isFlotteur() == false);
			assertion(test+" :\\post tombeDepuis() == 0", lemming.tombeDepuis() == 0);
			assertion(test+" :\\post isBuilder() == false", lemming.isBuilder() == false);
			assertion(test+" :\\post isCurrentlyBuilding() == false", lemming.isCurrentlyBuilding() == false);
			assertion(test+" :\\post isCurrentlyClimbing() == false", lemming.isCurrentlyClimbing() == false);
			assertion(test+" :\\post nbCreuseTunnel() == 0", lemming.nbCreuseTunnel() == 0);
			assertion(test+" :\\post getNombreToursBuilder() == 0", lemming.getNombreToursBuilder() == 0);
			assertion(test+" :\\post getNombreDallesPosees() == 0", lemming.getNombreDallesPosees() == 0);
		} catch (ContractError e) {
			assertion(test+": "+e.getMessage(), false);
		}
	}


	/* Le test qui suit sera le même pour devientMarcheur, 
	 * devientTombeur (là il faudra attendre que le lemming soit marcheur donc sur terre), 
	 * devientBasher, devientMiner, devientStopper */
	/**
	 * Objectif 2: devientCreuseur
	 * 
	 * Cas 2_0: devientCreuseur valide
	 * 
	 * Condition initiale:
	 * init(1)
	 * 
	 * Operation:
	 * devientCreuseur()
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getType() == CREUSEUR
	 */
	@Test
	public void testDevientCreuseur2_0(){
		String test = "Level Test Objectif 2.0";
		//Condition initiale
		try{
			lemming.init(1);
			try{
				//operation
				lemming.devientCreuseur();
				assertion(test+": \\post getType() == CREUSEUR", lemming.getType() == Type.CREUSEUR);
			}catch (ContractError e) {
				//oracle
				assertion(test+": "+e.getMessage(), false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/* Le test qui suit sera le même pour devientGrimpeur, devientFlotteur, devientBuilder */
	/**
	 * Objectif 3: devientExploseur
	 * 
	 * Cas 3_0: devientExploseur valide
	 * 
	 * Condition initial:
	 * init(1)
	 * 
	 * Operation:
	 * devientExploseur()
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * isExploseur() == true
	 */
	@Test
	public void testDevientExploseur3_0(){
		String test = "Level Test Objectif 3.0";
		//Condition initiale
		try{
			lemming.init(1);
			try{
				//operation
				lemming.devientExploseur();
				assertion(test+": \\post isExploseur() == true", lemming.isExploseur() == true);
			}catch (ContractError e) {
				//oracle
				assertion(test+": "+e.getMessage(), false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: step
	 * 
	 * Cas 4_0: step valide
	 * 
	 * Condition initial:
	 * gameEng.init(10,1);
	 * gameEng.getLevel().init(25,25)
	 * gameEng.getLevel().setEntrance(5,5)
	 * gameEng.getLevel().setNature(5,9,METAL)
	 * gameEng.getLevel().setExit(5,8)
	 * gameEng.step()
	 * lemming = gameEng.getLemming(1);
	 * 
	 * Operation:
	 * step()
	 *  
	 * Oracle:
	 * Pas de ContractError &&
	 * getX() == getX()@pre &&
	 * getY() == getY()@pre+1
	 */
	@Test
	public void testStep4_0(){
		String test = "Level Test Objectif 4.0";
		//Condition initiale
		try{
			gameEng.init(10,1);
			gameEng.getLevel().init(25,25);
			gameEng.getLevel().setEntrance(5,5);
			gameEng.getLevel().setNature(5,9,Nature.METAL);
			gameEng.getLevel().setExit(5,8);
			gameEng.step();
			lemming = gameEng.getLemming(1);
			try{
				//operation
				lemming.step();
				assertion(test+": \\post getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre + 1 "+
						"AND tombeDepuis() = tombeDepuis()@pre+1", lemming.getType() == Type.TOMBEUR && lemming.getX() == 5 && lemming.getY() == 6);
			}catch (ContractError e) {
				//oracle
				assertion(test+": "+e.getMessage(), false);
			}
		}catch (ContractError e) {
			assertion(test+": erreur a l'initialisation du test: "+e.getMessage(), false);
		}
	}

}
