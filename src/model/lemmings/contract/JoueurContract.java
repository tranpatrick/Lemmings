package model.lemmings.contract;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;

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
	// \post getNbJetons("DIGGER") = 10
	// \post getNbJetons("CLIMBER") = 10
	// \post getNbJetons("BUILDER") = 10
	// \post getNbJetons("FLOATER") = 10
	// \post getNbJetons("BOMBER") = 10
	// \post getNbJetons("STOPPER") = 10
	// \post getNbJetons("BASHER") = 10
	// \post getNbJetons("MINER") = 10
	public void init(){
		super.init();
		checkInvariant();
		if(super.getNbJetons("DIGGER") != 10)
			throw new PostConditionError("init : getNbJetons(\"DIGGER\") = 10 not satisfied");
		if(super.getNbJetons("CLIMBER") != 10)
			throw new PostConditionError("init : getNbJetons(\"CLIMBER\") = 10 not satisfied");
		if(super.getNbJetons("BUILDER") != 10)
			throw new PostConditionError("init : getNbJetons(\"BUILDER\") = 10 not satisfied");
		if(super.getNbJetons("FLOATER") != 10)
			throw new PostConditionError("init : getNbJetons(\"FLOATER\") = 10 not satisfied");
		if(super.getNbJetons("BOMBER") != 10)
			throw new PostConditionError("init : getNbJetons(\"BOMBER\") = 10 not satisfied");
		if(super.getNbJetons("STOPPER") != 10)
			throw new PostConditionError("init : getNbJetons(\"STOPPER\") = 10 not satisfied");
		if(super.getNbJetons("BASHER") != 10)
			throw new PostConditionError("init : getNbJetons(\"BASHER\") = 10 not satisfied");
		if(super.getNbJetons("MINER") != 10)
			throw new PostConditionError("init : getNbJetons(\"MINER\") = 10 not satisfied");
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
	// \post l.getType() = type
	// \post getNbJetons(type) = getNbJetons(type)@pre-1
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
		
		if(!l.getType().toString().equals(type))
			throw new PostConditionError("changeClasse : l.getType() = type not satisfied");
	}

	// \pre s > 0 AND getGameEng.gameOver() == false
	// \post getSpawnSpeed() = s;
	public void changeSpawnSpeed(GameEng g, int s){
		
	}

	// \pre getGameEng.gameOver() == false
	// \post getGameEng.gameOver() == true
	// \post getGameEng.getSpawnSpeed() = + l'infini
	public void killThemAll(GameEng g){

	}

	// \pre getGameEng.gameOver() == false
	// \post getGameEng.gameOver() == true
	// \post getGameEng().getSpawnSpeed() = spawnSpeed@init;
	// \post getGameEng().getSizeColony() = sizeColony@init;
	// \post getGameEng().getNombreSauves() = 0;
	// \post getGameEng().getNombreMorts() = 0;
	// \post getGameEng().getNombreActifs() = 0;
	// \post getGameEng().getNombreCrees() = 0;
	public void reset(GameEng g){

	}

}
