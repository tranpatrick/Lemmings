package model.lemmings.services;

import java.awt.event.MouseEvent;


public interface Joueur {
	
	/** Observators **/
	/* \pre type = "DIGGER"
	 * || type = "CLIMBER"
	 * || type = "BUILDER"
	 * || type = "FLOATER"
	 * || type = "BOMBER"
	 * || type = "STOPPER"
	 * || type = "BASHER"
	 * || type = "MINERR"
	*/
	public int getNbJetons(String type);
	public GameEng getGameEng();
	
	/** Constructors **/ 
	// \post getNbJetons("DIGGER") = 10
	// \post getNbJetons("CLIMBER") = 10
	// \post getNbJetons("BUILDER") = 10
	// \post getNbJetons("FLOATER") = 10
	// \post getNbJetons("BOMBER") = 10
	// \post getNbJetons("STOPPER") = 10
	// \post getNbJetons("BASHER") = 10
	// \post getNbJetons("MINER") = 10
	public void init();
	
	/** Operators **/
	// \pre !getGameEng().gameOver()
	// \pre x >= 0 && x < getGameEng().getLevel().getHeight()
	// \pre y >= 0 && y < getGameEng().getLevel().getWidth()
	public Lemming select(int x, int y);
	
	/* \pre type = "DIGGER"
	 * || type = "CLIMBER"
	 * || type = "BUILDER"
	 * || type = "FLOATER"
	 * || type = "BOMBER"
	 * || type = "STOPPER"
	 * || type = "BASHER"
	 * || type = "MINER"
	 */
	// \pre getNbJetons(type) > 0
	// \post getNbJetons(t) = getNbJetons(t)@pre - 1
	/* \post type = "CLIMBER" \implies l.isGrimpeur() = true
	 * || type = "FLOATER" \implies l.isFlotteur() = true
	 * || type = "BUILDER" \implies l.isBuilder() = true
	 * || type = "BOMBER" \implies l.isExploseur() = true
	 * || l.getType() = type
	 */
	public void changeClasse(Lemming l, String type);
	
	// \pre s > 0 AND getGameEng.gameOver() == false
	// \post getGameEng().getSpawnSpeed() = s;
	public void changeSpawnSpeed(int s);
	
	// \pre getGameEng.gameOver() == false
	// \post forall i in getGameEng().getLEmmingsActifs(), getGameEng().getLemming(i).isExploseur() = true
	public void annihilation();
	
	// \pre getGameEng.gameOver() == false
	// \post getGameEng.gameOver() == true
	// \post getGameEng().getSpawnSpeed() = spawnSpeed@init;
	// \post getGameEng().getSizeColony() = sizeColony@init;
	// \post getGameEng().getNombreSauves() = 0;
	// \post getGameEng().getNombreMorts() = 0;
	// \post getGameEng().getNombreActifs() = 0;
	// \post getGameEng().getNombreCrees() = 0;
	public void reset(GameEng g);
	
}
