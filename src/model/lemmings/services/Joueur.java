package model.lemmings.services;

public interface Joueur extends RequireGameEngService {
	
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
	// \pre nbJetons > 0
	// \post getNbJetons("DIGGER") = nbJetons
	// \post getNbJetons("CLIMBER") = nbJetons
	// \post getNbJetons("BUILDER") = nbJetons
	// \post getNbJetons("FLOATER") = nbJetons
	// \post getNbJetons("BOMBER") = nbJetons
	// \post getNbJetons("STOPPER") = nbJetons
	// \post getNbJetons("BASHER") = nbJetons
	// \post getNbJetons("MINER") = nbJetons
	public void init(int nbJetons);
	
	/** Operators **/
	
	// \pre nbJetons > 0
	// \post getNbJetons("DIGGER") = nbJetons
	// \post getNbJetons("CLIMBER") = nbJetons
	// \post getNbJetons("BUILDER") = nbJetons
	// \post getNbJetons("FLOATER") = nbJetons
	// \post getNbJetons("BOMBER") = nbJetons
	// \post getNbJetons("STOPPER") = nbJetons
	// \post getNbJetons("BASHER") = nbJetons
	// \post getNbJetons("MINER") = nbJetons
	
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
	
}
