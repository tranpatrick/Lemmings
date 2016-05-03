package model.lemmings.contract;

import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;
import model.lemmings.services.Lemming.Type;

public class JoueurContract extends JoueurDecorator implements Joueur {

	public JoueurContract(Joueur joueur) {
		super(joueur);
	}

	/** Observators **/
	public int getNbJetons(String type){
		int res;
		/* \pre type = "DIGGER"
		 * || type = "CLIMBER"
		 * || type = "BUILDER"
		 * || type = "FLOATER"
		 * || type = "BOMBER"
		 * || type = "STOPPER"
		 * || type = "BASHER"
		 * || type = "MINER"
		 */
		if(!type.equals("DIGGER") && !type.equals("CLIMBER") 
				&& !type.equals("BUILDER") && !type.equals("MINER")
				&& !type.equals("BOMBER") && !type.equals("FLOATER")
				&& !type.equals("STOPPER") && !type.equals("BASHER"))
			throw new PreConditionError("getNbJetons : type = \"DIGGER\" "+
					"|| type = \"CLIMBER\" || type = \"BUILDER\" || type = \"FLOATER\""+
					"|| type = \"BOMBER\" || type = \"STOPPER\""+
					"|| type = \"BASHER\" || type = \"MINER\"");
		checkInvariant();
		res = super.getNbJetons(type);
		checkInvariant();
		return res;
	}

	public GameEng getGameEng(){
		GameEng res;
		checkInvariant();
		res = super.getGameEng();
		checkInvariant();
		return res;
	}

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
	public void init(int nbJetons){
		super.init(nbJetons);
		checkInvariant();
		if(super.getNbJetons("DIGGER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"DIGGER\") = nbJetons not satisfied");
		if(super.getNbJetons("CLIMBER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"CLIMBER\") = nbJetons not satisfied");
		if(super.getNbJetons("BUILDER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"BUILDER\") = nbJetons not satisfied");
		if(super.getNbJetons("FLOATER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"FLOATER\") = nbJetons not satisfied");
		if(super.getNbJetons("BOMBER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"BOMBER\") = nbJetons not satisfied");
		if(super.getNbJetons("STOPPER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"STOPPER\") = nbJetons not satisfied");
		if(super.getNbJetons("BASHER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"BASHER\") = nbJetons not satisfied");
		if(super.getNbJetons("MINER") != nbJetons)
			throw new PostConditionError("init : getNbJetons(\"MINER\") = nbJetons not satisfied");
	}

	/** Invariants **/
	public void checkInvariant(){
		// \inv getNbJetons("DIGGER") >= 0;
		if(super.getNbJetons("DIGGER") < 0)
			throw new InvariantError("getNbJetons(\"DIGGER\") >= 0 not satisfied");
		// \inv getNbJetons("CLIMBER") >= 0;
		if(super.getNbJetons("CLIMBER") < 0)
			throw new InvariantError("getNbJetons(\"CLIMBER\") >= 0 not satisfied");
		// \inv getNbJetons("BUILDER") >= 0;
		if(super.getNbJetons("BUILDER") < 0)
			throw new InvariantError("getNbJetons(\"BUILDER\") >= 0 not satisfied");
		// \inv getNbJetons("FLOATER") >= 0;
		if(super.getNbJetons("FLOATER") < 0)
			throw new InvariantError("getNbJetons(\"FLOATER\") >= 0 not satisfied");
		// \inv getNbJetons("BOMBER") >= 0;
		if(super.getNbJetons("BOMBER") < 0)
			throw new InvariantError("getNbJetons(\"BOMBER\") >= 0 not satisfied");
		// \inv getNbJetons("STOPPER") >= 0;
		if(super.getNbJetons("STOPPER") < 0)
			throw new InvariantError("getNbJetons(\"STOPPER\") >= 0 not satisfied");
		// \inv getNbJetons("BASHER") >= 0;
		if(super.getNbJetons("BASHER") < 0)
			throw new InvariantError("getNbJetons(\"BASHER\") >= 0 not satisfied");
		// \inv getNbJetons("MINER") > 0;
		if(super.getNbJetons("MINER") < 0)
			throw new InvariantError("getNbJetons(\"MINER\") >= 0 not satisfied");
	}

	/** Operators **/
	// \pre !getGameEng().gameOver()
	// \pre x >= 0 && x < getGameEng().getLevel().getHeight()
	// \pre y >= 0 && y < getGameEng().getLevel().getWidth()
	public Lemming select(int x, int y){
		Lemming res;
		if(super.getGameEng().gameOver())
			throw new PreConditionError("select : !getGameEng().gameOver() not satisfied");
		if(!(x >= 0 && x < super.getGameEng().getLevel().getHeight()))
			throw new PreConditionError("select : x >= 0 && x < getGameEng().getLevel().getHeight() not satisfied");
		if(!(y >= 0 && y < super.getGameEng().getLevel().getWidth()))
			throw new PreConditionError("select : y >= 0 && y < getGameEng().getLevel().getWidth() not satisfied");
		checkInvariant();
		res = super.select(x, y);
		checkInvariant();
		return res;
	}

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
	// \post getNbJetons(type) = getNbJetons(type)@pre-1
	/* \post type = "CLIMBER" \implies l.isGrimpeur() = true
	 * || type = "FLOATER" \implies l.isFlotteur() = true
	 * || type = "BUILDER" \implies l.isBuilder() = true
	 * || type = "BOMBER" \implies l.isExploseur() = true
	 * || type = "DIGGER" \implies l.getType() = CREUSEUR
	 * || type = "STOPPEUR" \implies l.getType() = STOPPEUR
	 * || type = "BASHER" \implies l.getType() = BASHER
	 * || type = "MINER" \implies l.getType() = MINER
	 */
	public void changeClasse(Lemming l, String type){
		if(!type.equals("DIGGER") && !type.equals("CLIMBER") 
				&& !type.equals("BUILDER") && !type.equals("MINER")
				&& !type.equals("BOMBER") && !type.equals("FLOATER")
				&& !type.equals("STOPPER") && !type.equals("BASHER"))
			throw new PreConditionError("getNbJetons : type = \"DIGGER\" "+
					"|| type = \"CLIMBER\" || type = \"BUILDER\" || type = \"FLOATER\""+
					"|| type = \"BOMBER\" || type = \"STOPPER\""+
					"|| type = \"BASHER\" || type = \"MINER\"");
		if(super.getNbJetons(type) <= 0)
			throw new PreConditionError("changeClasse : getNbJetons(type) > 0 not satisfied");

		int nbJetonsPre = super.getNbJetons(type);

		checkInvariant();
		super.changeClasse(l, type);
		checkInvariant();

		if(super.getNbJetons(type) != nbJetonsPre-1)
			throw new PostConditionError("changeClasse : getNbJetons(type) = getNbJetons(type)@pre-1 not satisfied");

		if(!((type.equals("CLIMBER") && !l.isGrimpeur())
				|| (type.equals("FLOATER") && !l.isFlotteur())
				|| (type.equals("BUILDER") && !l.isBuilder())
				|| (type.equals("BOMBER") && !l.isExploseur())
				|| !(l.getType() == Type.CREUSEUR && type.equals("DIGGER"))
				|| !(l.getType() == Type.STOPPEUR && type.equals("STOPPER"))
				|| !(l.getType().toString().equals(type))
				)){
			throw new PostConditionError("changeClasse : type = \"CLIMBER\" implies l.isGrimpeur() = true "+
					"|| type = \"FLOATER\" implies l.isFlotteur() = true "+
					"|| type = \"BUILDER\" implies l.isBuilder() = true "+
					"|| type = \"BOMBER\" implies l.isExploseur() = true "+
					"|| l.getType() = type not satisfied");
		}
	}

	// \pre s > 0 AND getGameEng.gameOver() == false
	// \post getGameEng().getSpawnSpeed() = s;
	public void changeSpawnSpeed(int s){
		// \pre s > 0 AND getGameEng.gameOver() == false
		if(s<=0 || super.getGameEng().gameOver())
			throw new PreConditionError("changeSpawnSpeed : s > 0 AND getGameEng.gameOver() == false not satisfied");
		checkInvariant();
		super.changeSpawnSpeed(s);
		checkInvariant();
		// \post getGameEng().getSpawnSpeed() = s;
		if(super.getGameEng().getSpawnSpeed() != s)
			throw new PostConditionError("changeSpawnSpeed : getGameEng().getSpawnSpeed() = s not satisfied");
	}

	// \pre getGameEng.gameOver() == false
	// \post forall i in getGameEng().getLEmmingsActifs(), getGameEng().getLemming(i).isExploseur() = true
	public void annihilation(){
		if(super.getGameEng().gameOver())
			throw new PreConditionError("annihilation : getGameEng.gameOver() = false not satisfied");

		checkInvariant();
		super.annihilation();
		checkInvariant();

		GameEng g = super.getGameEng();
		Set<Integer> set = g.getLemmingsActifs();
		for(int i : set){
			if(g.getLemming(i).isExploseur() == false)
				throw new PostConditionError("annihilation : forall i in getGameEng().getLEmmingsActifs(), "+
						"getGameEng().getLemming(i).isExploseur() = true not satisfied");
		}
	}

}
