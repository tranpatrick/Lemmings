package model.lemmings.contract;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;

public class LemmingDecorator implements Lemming{

	private Lemming lemming;

	public LemmingDecorator(Lemming lemming){
		this.lemming = lemming;
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getDirection()
	 */
	public Direction getDirection() {
		return lemming.getDirection();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getType()
	 */
	public Type getType() {
		return lemming.getType();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getX()
	 */
	public int getX() {
		return lemming.getX();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getY()
	 */
	public int getY() {
		return lemming.getY();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#tombeDepuis()
	 */
	public int tombeDepuis() {
		return lemming.tombeDepuis();
	}
	
	public int exploseurDepuis(){
		return lemming.exploseurDepuis();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getId()
	 */
	public int getId() {
		return lemming.getId();
	}

	@Override
	public boolean isFlotteur() {
		return lemming.isFlotteur();
	}
	
	@Override
	public boolean isGrimpeur() {
		return lemming.isGrimpeur();
	}
	
	@Override
	public boolean isExploseur() {
		return lemming.isExploseur();
	}
	
	@Override
	public boolean isBuilder() {
		return lemming.isBuilder();
	}
	
	/**
	 * @return
	 * @see model.lemmings.services.Lemming#getGameEng()
	 */
	public GameEng getGameEng() {
		return lemming.getGameEng();
	}
	
	@Override
	public boolean isCurrentlyBuilding() {
		return lemming.isCurrentlyBuilding();
	}
	
	@Override
	public int getNombreToursBuilder() {
		return lemming.getNombreToursBuilder();
	}
	
	@Override
	public int getNombreDallesPosees() {
		return lemming.getNombreDallesPosees();
	}
	
	@Override
	public int nbCreuseTunnel() {
		return lemming.nbCreuseTunnel();
	}

	/**
	 * @param id
	 * @see model.lemmings.services.Lemming#init(int)
	 */
	public void init(int id) {
		lemming.init(id);
	}

	/**
	 * 
	 * @see model.lemmings.services.Lemming#devientTombeur()
	 */
	public void devientTombeur() {
		lemming.devientTombeur();
	}

	/**
	 * 
	 * @see model.lemmings.services.Lemming#devientMarcheur()
	 */
	public void devientMarcheur() {
		lemming.devientMarcheur();
	}

	/**
	 * 
	 * @see model.lemmings.services.Lemming#devientGaucher()
	 */
	public void devientGaucher() {
		lemming.devientGaucher();
	}

	/**
	 * 
	 * @see model.lemmings.services.Lemming#devientDroitier()
	 */
	public void devientDroitier() {
		lemming.devientDroitier();
	}

	@Override
	public void devientCreuseur() {
		lemming.devientCreuseur();

	}

	@Override
	public void devientGrimpeur() {
		lemming.devientGrimpeur();
	}

	@Override
	public void devientExploseur() {
		lemming.devientExploseur();
	}

	@Override
	public void devientFlotteur() {
		lemming.devientFlotteur();
	}
	
	@Override
	public void devientBuilder() {
		lemming.devientBuilder();
	}
	
	@Override
	public void devientMiner() {
		lemming.devientMiner();
	}
	
	@Override
	public void devientStoppeur(){
		lemming.devientStoppeur();
	}
	
	@Override
	public void devientBasher() {
		lemming.devientBasher();
	}
	
	/**
	 * 
	 * @see model.lemmings.services.Lemming#step()
	 */
	public void step() {
		lemming.step();
	}
}
