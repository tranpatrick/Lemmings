package model.lemmings.services;

import java.awt.event.MouseEvent;


public interface Joueur {
	
	/* interface de selection : clic avec Souris (MouseEvent) */
	/* definir une interface MouseEvent ?? */
	public enum Type {
		MARCHEUR, TOMBEUR;
	}
	
	/** Observators **/
	public int getNbJetons(Type classe);
	public GameEng getGameEng();
	
	/** Constructors **/ 
	/* penser a ajouter les autres classes de lemming lors de leur ajout dans la spec */
	// \post getNbJetons(TOMBEUR) = + l'infini
	// \post getNbJetons(MARCHEUR) = + l'infini
	public void init();
	
	/** Operators **/
	// \pre getGameEng().gameOver()
	// \post select(g,e).getX() = e.getX() AND select(g,e).getY() = e.getY() 
	public Lemming select(GameEng g, MouseEvent e);
	
	// \pre getNbJetons(t) > 0 AND t != MARCHEUR AND t != TOMBEUR
	// \post changeClasse(l,t).getType() = t
	// \post getNbJetons(t) = getNbJetons(t)@pre - 1
	public Lemming changeClasse(Lemming l, Type t);
	
	// \pre s > 0 AND getGameEng.gameOver() == false
	// \post getSpawnSpeed() = s;
	public GameEng changeSpawnSpeed(GameEng g, int s);
	
	// \pre getGameEng.gameOver() == false
	// \post getGameEng.gameOver() == true
	// \post getGameEng.getSpawnSpeed() = + l'infini
	public GameEng killThemAll(GameEng g);
	
	// \pre getGameEng.gameOver() == false
	// \post getGameEng.gameOver() == true
	// \post getGameEng().getSpawnSpeed() = spawnSpeed@init;
	// \post getGameEng().getSizeColony() = sizeColony@init;
	// \post getGameEng().getNombreSauves() = 0;
	// \post getGameEng().getNombreMorts() = 0;
	// \post getGameEng().getNombreActifs() = 0;
	// \post getGameEng().getNombreCrees() = 0;
	public GameEng reset(GameEng g);
	
}
