package model.lemmings.contract;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;

public class JoueurDecorator implements Joueur{

	private Joueur joueur;

	public JoueurDecorator(Joueur joueur){
		this.joueur = joueur;
	}

	/**
	 * @param type
	 * @return
	 * @see model.lemmings.services.Joueur#getNbJetons(java.lang.String)
	 */
	public int getNbJetons(String type) {
		return joueur.getNbJetons(type);
	}

	/**
	 * @return
	 * @see model.lemmings.services.Joueur#getGameEng()
	 */
	public GameEng getGameEng() {
		return joueur.getGameEng();
	}

	/**
	 * 
	 * @see model.lemmings.services.Joueur#init()
	 */
	public void init() {
		joueur.init();
	}

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @return
	 * @see model.lemmings.services.Joueur#select(model.lemmings.services.GameEng, int, int)
	 */
	public Lemming select(int x, int y) {
		return joueur.select(x, y);
	}

	/**
	 * @param l
	 * @param type
	 * @see model.lemmings.services.Joueur#changeClasse(model.lemmings.services.Lemming, java.lang.String)
	 */
	public void changeClasse(Lemming l, String type) {
		joueur.changeClasse(l, type);
	}

	/**
	 * @param g
	 * @param s
	 * @see model.lemmings.services.Joueur#changeSpawnSpeed(model.lemmings.services.GameEng, int)
	 */
	public void changeSpawnSpeed(int s) {
		joueur.changeSpawnSpeed(s);
	}

	/**
	 * @param g
	 * @see model.lemmings.services.Joueur#killThemAll(model.lemmings.services.GameEng)
	 */
	public void annihilation() {
		joueur.annihilation();
	}

	/**
	 * @param g
	 * @see model.lemmings.services.Joueur#reset(model.lemmings.services.GameEng)
	 */
	public void reset(GameEng g) {
		joueur.reset(g);
	}

	@Override
	public void bindGameEngService(GameEng service) {
		joueur.bindGameEngService(service);
		
	}
	
}
