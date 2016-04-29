package model.lemmings.contract;


import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level.Nature;

public class LemmingContract extends LemmingDecorator implements Lemming {

	public LemmingContract(Lemming lemming) {
		super(lemming);
	}

	/** Observateurs **/

	public Direction getDirection(){
		Direction res;
		checkInvariant();
		res = super.getDirection();
		checkInvariant();
		return res;
	}

	public Type getType(){
		Type res;
		checkInvariant();
		res = super.getType();
		checkInvariant();
		return res;
	}

	public int getX(){
		int res;
		checkInvariant();
		res = super.getX();
		checkInvariant();
		return res;
	}

	public int getY(){
		int res;
		checkInvariant();
		res = super.getY();
		checkInvariant();
		return res;
	}

	public int tombeDepuis(){
		int res;
		// \pre getType() = TOMBEUR
		if(super.getType() != Type.TOMBEUR)
			throw new PreConditionError("tombeDepuis : getType() = TOMBEUR not satisfied");
		checkInvariant();
		res = super.tombeDepuis();
		checkInvariant();
		return res;
	}

	public int exploseurDepuis(){
		int res;
		// \pre isExploseur() = true
		if(super.isExploseur() != true)
			throw new PreConditionError("exploseurDepuis : isExploseur = true not satisfied");
		checkInvariant();
		res = super.exploseurDepuis();
		checkInvariant();
		return res;
	}

	public int getId(){
		int res;
		checkInvariant();
		res = super.getId();
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

	public boolean isGrimpeur(){
		boolean res;
		checkInvariant();
		res = super.isGrimpeur();
		checkInvariant();
		return res;
	}

	public boolean isFlotteur() {
		checkInvariant();
		boolean answer = super.isFlotteur();
		checkInvariant();
		return answer;
	}

	public boolean isExploseur() {
		checkInvariant();
		boolean answer = super.isExploseur();
		checkInvariant();
		return answer;
	}

	@Override
	public boolean isBuilder() {
		checkInvariant();
		boolean isBuilder = super.isBuilder();
		checkInvariant();
		return isBuilder;
	}

	@Override
	public int getNombreToursBuilder() {
		checkInvariant();
		int nombreToursBuilder = super.getNombreToursBuilder();
		checkInvariant();
		return nombreToursBuilder;
	}

	@Override
	public boolean isCurrentlyBuilding() {
		checkInvariant();
		boolean isCurrentlyBuilding = super.isCurrentlyBuilding();
		checkInvariant();
		return isCurrentlyBuilding;
	}

	@Override
	public boolean isCurrentlyClimbing() {
		checkInvariant();
		boolean isCurrentlyClimbing = super.isCurrentlyClimbing();
		checkInvariant();
		return isCurrentlyClimbing;
	}

	@Override 
	public int getNombreDallesPosees() {
		checkInvariant();
		int nombreDallesPosees = super.getNombreDallesPosees();
		checkInvariant();
		return nombreDallesPosees;
	}

	@Override
	public int nbCreuseTunnel() {
		checkInvariant();
		int nbCreuseTunnel = super.nbCreuseTunnel();
		checkInvariant();
		return nbCreuseTunnel;
	}


	/** Constructeur **/

	public void init(int id){
		/* Pas de checkInvariant avant init ca bug */
		super.init(id);
		checkInvariant();

		// \post getDirection() = DROITIER
		if(super.getDirection() != Direction.DROITIER)
			throw new PostConditionError("init : getDirection() = DROITIER not statisfied");
		// \post getType() = MARCHEUR 
		if(super.getType() != Type.TOMBEUR) 
			throw new PostConditionError("init : getType() = MARCHEUR not statisfied");
		// \post getId() = id		
		if(super.getId() != id)
			throw new PostConditionError("init : getId() = id not statisfied");
		// \post tombeDepuis() = 0
		if(super.tombeDepuis() != 0)
			throw new PostConditionError("init : tombeDepuis() = 0 not statisfied");
		// \post isGrimpeur = false
		if(super.isGrimpeur())
			throw new PostConditionError("init : isGrimpeur() = false not statisfied"); 
		// \post isExploseur = false
		if(super.isExploseur())
			throw new PostConditionError("init : isExploseur() = false not statisfied");
		// \post isFlotteur = false
		if(super.isFlotteur())
			throw new PostConditionError("init : isFlotteur() = false not statisfied");
		// \post exploseurDepuis = 0
		if(super.exploseurDepuis() != 0)
			throw new PostConditionError("init : exploseurDepuis() = 0 not statisfied");
	}



	/** Invariants **/
	// \inv 1 <= getX() <= getGameEng().getLevel().getWidth()-1 
	// \inv 1 <= getY() <= getGameEng().getLevel().getHeight()-1
	// \inv getId() > 0
	// \inv getType() = TOMBEUR \implies tombeDepuis() >= 0
	// \inv getType() = MARCHEUR \implies tombeDepuis() = 0
	public void checkInvariant(){
		if(!(1 <= super.getX() && super.getX() < super.getGameEng().getLevel().getWidth()))
			throw new InvariantError("1 <= getX() <= getGameEng().getLevel().getWidth()-1 not satisfied");

		if(!(1 <= super.getY() && super.getY() < super.getGameEng().getLevel().getHeight()))
			throw new InvariantError("1 <= getY() <= getGameEng().getLevel().getHeight()-1 not satisfied");

		if(!(super.getId() > 0))
			throw new InvariantError("getId() > 0 not satisfied");

		if(super.getType() == Type.TOMBEUR && !(super.tombeDepuis() >= 0))
			throw new InvariantError("getType() = TOMBEUR implies tombeDepuis() >= 0 not satisfied");

		if(super.getType() == Type.MARCHEUR && !(super.tombeDepuis() == 0))
			throw new InvariantError("getType() = MARCHEUR implies tombeDepuis() = 0 not satisfied");
	}


	/** Operateurs **/

	public void devientTombeur(){
		// \pre getType() != TOMBEUR
		if(super.getType() == Type.TOMBEUR)
			throw new PreConditionError("devientTombeur : getType() != TOMBEUR not satisfied");
		checkInvariant();
		super.devientTombeur();
		checkInvariant();
		// \post getType() = TOMBEUR
		if(super.getType() != Type.TOMBEUR)
			throw new PostConditionError("devientTombeur : getType() = TOMBEUR not satisfied"); 
	}

	public void devientMarcheur(){
		// \pre getType() != MARCHEUR
		if(super.getType() == Type.MARCHEUR)
			throw new PreConditionError("devientMarcheur : getType() != MARCHEUR not satisfied");
		checkInvariant();
		super.devientMarcheur();
		checkInvariant();
		// \post getType() = MARCHEUR
		if(super.getType() != Type.MARCHEUR)
			throw new PostConditionError("devientMarcheur : getType() = MARCHEUR not satisfied");
		// \post tombeDepuis() = 0
		if(super.tombeDepuis() != 0)
			throw new PostConditionError("devientMarcheur : tombeDepuis() = 0 not satisfied");
	}

	public void devientGaucher(){
		// \pre getDirection() != GAUCHER
		if(super.getDirection() == Direction.GAUCHER)
			throw new PreConditionError("devientGaucher : getDirection() != GAUCHER not satisfied");
		checkInvariant();
		super.devientGaucher();
		checkInvariant();
		// \post getDirection() = GAUCHER
		if(super.getDirection() != Direction.GAUCHER)
			throw new PostConditionError("devientGaucher : getDirection() = GAUCHER not satisfied");
	}

	public void devientDroitier(){
		// \pre getDirection() != DROITIER
		if(super.getDirection() == Direction.DROITIER)
			throw new PreConditionError("devientDroitier : getDirection() != DROITIER not satisfied");
		checkInvariant();
		super.devientGaucher();
		checkInvariant();
		// \post getDirection() = DROITIER
		if(super.getDirection() != Direction.DROITIER)
			throw new PostConditionError("devientDroitier : getDirection() = DROITIER not satisfied");
	}

	@Override
	public void devientFlotteur(){
		// \pre isGrimpeur()=false
		if(super.isFlotteur() != false)
			throw new PreConditionError("devientFlotteur: isFlotteur() != false not satisfied");

		checkInvariant();
		super.devientFlotteur();
		checkInvariant();

		// \post isGrimpeur()=true
		if(!super.isFlotteur() == true)
			throw new PreConditionError("devientFlotteur: isFlotteur() = true not satisfied");
	}

	public void devientGrimpeur(){
		// \pre isGrimpeur()=false
		if(super.isGrimpeur() != false)
			throw new PreConditionError("devientGrimpeur : isGrimpeur = false not satisfied");
		// \pre getType()=MARCHEUR OR getType()=TOMBEUR OR getType()=CREUSEUR
		if(!(super.getType() == Type.MARCHEUR || super.getType() == Type.TOMBEUR || super.getType() == Type.CREUSEUR))
			throw new PreConditionError("devientGrimpeur : getType()=MARCHEUR OR getType()=TOMBEUR OR getType()=CREUSEUR not satisfied");

		checkInvariant();
		super.devientGrimpeur();
		checkInvariant();

		// \post isGrimpeur()=true
		if(isGrimpeur() != true)
			throw new PostConditionError("devientGrimpeur : isGrimpeur() = true not satisfied");
	}

	public void devientExploseur(){
		// \pre isExploseur() = false;
		if(super.isExploseur() != false)
			throw new PreConditionError("devientExploseur: isExploseur = false not satisfied");

		checkInvariant();
		super.devientExploseur();
		checkInvariant();

		// \post isExploseur = false;
		if(isExploseur() != true)
			throw new PostConditionError("devientExploseur: isExploseur() = true not satisfied");
	}

	@Override
	public void devientCreuseur() {
		// \pre getType() != CREUSEUR
		if (!(super.getType() != Type.CREUSEUR)) 
			throw new PreConditionError("devientCreuseur : getType() != CREUSEUR not satisfied");

		checkInvariant();
		super.devientCreuseur();
		checkInvariant();

		// \post getType() = CREUSEUR
		if (!(super.getType() == Type.CREUSEUR)) 
			throw new PostConditionError("devientCreuseur : getType() == CREUSEUR not satisfied");
	}

	@Override 
	public void devientBuilder() {
		// \pre isBuilder() = false
		if (!(super.isBuilder() == false)) 
			throw new PreConditionError("devientBuilder : isBuilder() = false not satisfied");

		checkInvariant();
		super.devientBuilder();
		checkInvariant();

		// \post isBuilder() = true
		if (!(super.isBuilder() == true)) 
			throw new PostConditionError("devientBuilder : isBuilder() = true not satisfied");
	}

	@Override
	public void devientMiner() {
		// \pre getType() != MINER
		if (super.getType() == Type.MINER) 
			throw new PreConditionError("devientMiner: getType() != MINER not satisfied");
		checkInvariant();
		super.devientMiner();
		checkInvariant();
		// \post getType() = MINER
		if (!(super.getType() == Type.MINER)) 
			throw new PostConditionError("devientMiner: getType() == MINER not satisfied");
	}

	@Override
	public void devientBasher() {
		// \pre getType() != BASHER
		if (!(super.getType() != Type.BASHER))
			throw new PreConditionError("devientBasher: getType() != BASHER not satisfied");
		checkInvariant();
		super.devientBasher();
		checkInvariant();
//		// \post getType() = BASHER
		if (!(super.getType() == Type.BASHER)) 
			throw new PostConditionError("devientBasher: getType() == BASHER not satisfied");
		// \post nbCreuseTunnel() = 0
		if (!(super.nbCreuseTunnel() == 0))
			throw new PostConditionError("devientBasher: nbCreuseTunnel() = 0 not satisfied");
	}


	@Override
	public void devientStoppeur(){
		// \pre getType() != TOMBEUR && getType() != STOPPEUR;
		if(!(getType() != Type.TOMBEUR))
			throw new PreConditionError("devientStoppeur: getType()@pre = TOMBEUR not satisfied");
		if(!(getType() != Type.STOPPEUR))
			throw new PreConditionError("devientStoppeur: getType()@pre = STOPPEUR not satisfied");
		checkInvariant();
		super.devientStoppeur();
		// \post getType() = STOPPEUR;
		if(getType() != Type.STOPPEUR)
			throw new PreConditionError("devientStoppeur: getType() = STOPPEUR not satisfied");
	}

	public void step(){
		// \pre !getGameEng().gameOver()
		if(super.getGameEng().gameOver())
			throw new PreConditionError("step : !getGameEng().gameOver() not satisfied");
		// \pre getId() \in getGameEng().getLemmingActifs()
		if(!super.getGameEng().getLemmingsActifs().contains(super.getId()))
			throw new PreConditionError("step : getId() in getGameEng().getLemmingActifs() not satisfied");

		Type typePre = super.getType();
		Direction directionPre = super.getDirection();
		boolean isGrimpeurPre = super.isGrimpeur();
		boolean isExploseurPre = super.isExploseur();
		int exploseurDepuisPre = super.exploseurDepuis();

		int xPre = super.getX();
		int yPre = super.getY();
		int widthPre = super.getGameEng().getLevel().getWidth();
		int heightPre = super.getGameEng().getLevel().getHeight();

		boolean isBuilderPre = super.isBuilder();
		boolean isCurrentlyBuildingPre = super.isCurrentlyBuilding();

		/* indique si les case etaient des obstacles avant le step pour BUILDER*/
		boolean isLibreDroite1Pre = xPre+1<widthPre?super.getGameEng().isLibre(xPre+1, yPre):true;
		boolean isLibreDroite2Pre = xPre+2<widthPre?super.getGameEng().isLibre(xPre+2, yPre):true;
		boolean isLibreDroite3Pre = xPre+3<widthPre?super.getGameEng().isLibre(xPre+3, yPre):true;
		boolean isMetalCaseDroiteTunnel1Pre = xPre+1<widthPre?super.getGameEng().getLevel().getNature(xPre+1, yPre) == Nature.METAL:true;
		boolean isMetalCaseDroiteTunnel2Pre = xPre+1<widthPre && yPre - 1 > 0?super.getGameEng().getLevel().getNature(xPre+1, yPre-1) == Nature.METAL:true;
		boolean isMetalCaseDroiteTunnel3Pre = xPre+1<widthPre && yPre - 2 > 0?super.getGameEng().getLevel().getNature(xPre+1, yPre-2) == Nature.METAL:true;
		boolean isMetalCaseDroite1Pre = xPre+1<widthPre?super.getGameEng().getLevel().getNature(xPre+1, yPre) == Nature.METAL:true;
		boolean isMetalCaseDroite2Pre = xPre+2<widthPre?super.getGameEng().getLevel().getNature(xPre+2, yPre) == Nature.METAL:true;
		boolean isMetalCaseDroiteBas1Pre = xPre+1<widthPre && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre+1, yPre+1) == Nature.METAL:true;
		boolean isMetalCaseDroiteBas2Pre = xPre+2<widthPre && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre+2, yPre+1) == Nature.METAL:true;
		boolean isMetalCaseDroiteBas3Pre = xPre+3<widthPre && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre+3, yPre+1) == Nature.METAL:true;
		boolean isObstacleDroite1Haut2Pre = xPre+1<widthPre && yPre-2>0?super.getGameEng().isObstacle(xPre+1, yPre-2):true;
		boolean isObstacleDroite2Haut2Pre = xPre+2<widthPre && yPre-2>0?super.getGameEng().isObstacle(xPre+2, yPre-2):true;
		boolean isObstacleDroite3Haut2Pre = xPre+3<widthPre && yPre-2>0?super.getGameEng().isObstacle(xPre+3, yPre-2):true;
		boolean isObstacle2Droite1Pre = xPre+1<widthPre?super.getGameEng().isObstacle2(xPre+1, yPre):true;
		boolean isObstacle2Droite1Haut1Pre = xPre+1<widthPre && yPre-1>0?super.getGameEng().isObstacle2(xPre+1, yPre-1):true;
		boolean isObstacle2Droite1Haut2Pre = xPre+1<widthPre && yPre-2>0?super.getGameEng().isObstacle2(xPre+1, yPre-1):true;

		boolean isLibreGauche1Pre = xPre-1>0?super.getGameEng().isLibre(xPre-1, yPre):true;
		boolean isLibreGauche2Pre = xPre-2>0?super.getGameEng().isLibre(xPre-2, yPre):true;
		boolean isLibreGauche3Pre = xPre-3>0?super.getGameEng().isLibre(xPre-3, yPre):true;
		boolean isMetalCaseGaucheTunnel1Pre = xPre-1<widthPre?super.getGameEng().getLevel().getNature(xPre-1, yPre) == Nature.METAL:true;
		boolean isMetalCaseGaucheTunnel2Pre = xPre-1>0 && yPre - 1 > 0?super.getGameEng().getLevel().getNature(xPre-1, yPre-1) == Nature.METAL:true;
		boolean isMetalCaseGaucheTunnel3Pre = xPre-1>0 && yPre - 2 > 0?super.getGameEng().getLevel().getNature(xPre-1, yPre-2) == Nature.METAL:true;
		boolean isMetalCaseGauche1Pre = xPre-1>0?super.getGameEng().getLevel().getNature(xPre-1, yPre) == Nature.METAL:true;
		boolean isMetalCaseGauche2Pre = xPre-2>0?super.getGameEng().getLevel().getNature(xPre-2, yPre) == Nature.METAL:true;
		boolean isMetalCaseGaucheBas1Pre = xPre-1>0 && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre-1, yPre+1) == Nature.METAL:true;
		boolean isMetalCaseGaucheBas2Pre = xPre-2>0 && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre-2, yPre+1) == Nature.METAL:true;
		boolean isMetalCaseGaucheBas3Pre = xPre-3>0 && yPre+1<heightPre?super.getGameEng().getLevel().getNature(xPre-3, yPre+1) == Nature.METAL:true;
		boolean isObstacleGauche1Haut2Pre = xPre-1>0 && yPre-2>0?super.getGameEng().isObstacle(xPre-1, yPre-2):true;
		boolean isObstacleGauche2Haut2Pre = xPre-2>0 && yPre-2>0?super.getGameEng().isObstacle(xPre-2, yPre-2):true;
		boolean isObstacleGauche3Haut2Pre = xPre-3>0 && yPre-2>0?super.getGameEng().isObstacle(xPre-3, yPre-2):true;
		boolean isObstacle2Gauche1Pre = xPre-1>0?super.getGameEng().isObstacle2(xPre-1, yPre):true;
		boolean isObstacle2Gauche1Haut1Pre = xPre-1>0 && yPre-1>0?super.getGameEng().isObstacle2(xPre-1, yPre-1):true;
		boolean isObstacle2Gauche1Haut2Pre = xPre-1>0 && yPre-2>0?super.getGameEng().isObstacle2(xPre-1, yPre-1):true;

		int nombreDallesPoseesPre = super.getNombreDallesPosees();
		int nombreToursBuilderPre = super.getNombreToursBuilder();
		int nbCreuseTunnelPre = super.nbCreuseTunnel();

		/* pour CREUSEUR */
		Nature caseGaucheBasPre = super.getGameEng().getLevel().getNature(xPre-1, yPre+1);
		Nature caseDroiteBasPre = super.getGameEng().getLevel().getNature(xPre+1, yPre+1);
		boolean isObstacleCaseDessousPre = super.getGameEng().isObstacle(xPre, yPre+1);
		int tombePre = super.tombeDepuis();
		int nbMortsPre = super.getGameEng().getNombreMorts();
		int nbSauvesPre = super.getGameEng().getNombreSauves();
		int nbActifsPre = super.getGameEng().getNombreActifs();

		checkInvariant();
		super.step();
		checkInvariant();

		int x = super.getX();
		int y = super.getY();

		/* \post isExploseur()@pre AND exploseurDepuis@pre = 4 
		 * 			\implies getGameEng().getNombreActifs() = getGameEng().getNombreActifs()@pre-1 
		 * 				AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre-1
		 * 				AND \forall i \in (i>getX()@pre-2 AND i<getX()@pre+2 AND i<getGameEng().getLevel.width AND i>0)
		 * 			    AND \forall j \in (j>getY()@pre-2 && j<getY()@pre+1 AND i<getGameEng().getLevel.width AND i>0)
		 * 					AND ((i!=getX()@pre-2 AND j!=getY()@pre-2) 
													AND (i!=getX()@pre+2 AND j!=getY()@pre-2) 
													AND (i!=getX()@pre-2 AND j!=getY()@pre+2)  
													AND (i!=getX()@pre+2 AND j!=getY()@pre+2))
		 * 	   				getGameEng().level(i,j)@pre = DIRT \implies getGameEng().level(i,j) = EMPTY */ 
		if(isExploseurPre && exploseurDepuisPre == 4){
			if(getGameEng().getNombreActifs() != nbActifsPre-1){
				throw new PostConditionError("step : post isExploseur()@pre AND exploseurDepuis@pre = 4 " + 
						"implies getGameEng().getNombreActifs() = getGameEng().getNombreActifs()@pre-1 not satisfied");
			}else if(getGameEng().getNombreMorts() != nbMortsPre+1){
				throw new PostConditionError("step : post isExploseur()@pre AND exploseurDepuis@pre = 4 " + 
						"implies getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre+1 not satisfied");
			}else{
				for(int i=xPre-2; i<xPre+3; i++){
					for(int j=yPre-2; j<yPre+2; j++){
						if((i>0 && i<getGameEng().getLevel().getWidth()) 
								&& (j>0 && j<getGameEng().getLevel().getHeight())
								&& ((i!=getX()-2 && j!=getY()-2) 
										&& (i!=getX()+2 && j!=getY()-2) 
										&& (i!=getX()-2 && j!=getY()+1)  
										&& (i!=getX()+2 && j!=getY()+1))
								&& getGameEng().getLevel().getNature(i, j) == Nature.DIRT){
							if(getGameEng().getLevel().getNature(i, j) != Nature.EMPTY 
									|| getGameEng().getLevel().getNature(i, j) != Nature.METAL){
								throw new PostConditionError("step : post isExploseur()@pre AND exploseurDepuis@pre = 4 " + 
										"implies getGameEng().getNature(i,j) != EMPTY forall i,j in the explosion range not satisfied");
							}
						}
					}
				}
			}
		}

		/* \post isExploseur()@pre AND exploseurDepuis@pre != 4
		 * 		\implies exploseurDepuis() = exploseurDepuisPre+1
		 */
		else if(isExploseurPre && exploseurDepuisPre != 4){
			if(exploseurDepuis() != exploseurDepuisPre+1){
				throw new PostConditionError("step : post isExploseur()@pre AND exploseurDepuis@pre != 4 " + 
						"implies exploseurDepuis() = exploseurDepuisPre+1 not satisfied");
			}
		}

		else if(typePre == Type.STOPPEUR){
			/* \post getType() = STOPPEUR 
			 * 			\implies getX() = getX()@pre AND getY() = getY()@pre
			 * 					AND getGameEng().isObstacle(getX(), getY())
			 * 					AND getGameEng().isObstacle(getX(), getY()-1) */  
			if(x != xPre || y != yPre)
				throw new PostConditionError("step : post getType() = STOPPEUR "+
						"implies getX() = getX()@pre AND getY() = getY()@pre");
			if(!getGameEng().isObstacle(x, y) || !getGameEng().isObstacle(x, y-1))
				throw new PostConditionError("step : post getType() = STOPPEUR "+
						"implies getGameEng().isObstacle(getX(), getY()) AND getGameEng().isObstacle(getX(), getY()-1");
		}

		if(typePre == Type.MARCHEUR){
			/************ MARCHEUR *************/

			/* \post getType()@pre = MARCHEUR AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1) 
			 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre
			 */
			if(!super.getGameEng().isObstacle(xPre, yPre+1) && !isGrimpeurPre) {
				if (!(super.getType() == Type.TOMBEUR)) {
					throw new PostConditionError("Type probleme");
				}
				if(!(super.getX() == xPre))
					throw new PostConditionError("x pas bon");
				if(!(y == yPre))
					throw new PostConditionError("y pas bon");
			}
			//TODO mettre bon message


			/************* MARCHEUR DROITIER ***************/

			else if(directionPre == Direction.DROITIER){

				/*********** GRIMPEUR ************/
				//Devient tombeur s'il n'y a pas d'obstacle en dessous ni à sa droite
				/* \post getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
				 * 												   AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre
				 * 														   AND getY() = getY()@pre
				 * 														   AND getDirection()=getDirection()@pre
				 */	
				if(typePre == Type.MARCHEUR && isGrimpeurPre && !super.getGameEng().isObstacle(xPre,yPre+1) && !super.getGameEng().isObstacle(xPre+1,yPre)){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)"
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)"
								+ "implies getX() = getX()@pre AND getY() = getY()@pre not satisfied");
					}
					if(super.getType()!=Type.TOMBEUR){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)"
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)"
								+ "implies getType() = TOMBEUR not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)"
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)"
								+ "implies getDirection()=getDirection()@pre not satisfied");
					}
				}
				//Grimpeur grimpe
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
				 * 								  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)   
				 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre-1
				 * 													AND getDirection()=getDirection()@pre */
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre+1, yPre-1) && getGameEng().isObstacle(xPre+1, yPre) && !getGameEng().isObstacle(xPre, yPre-2)){
					if(x!=xPre || y != yPre-1){
						System.out.println("yPre="+yPre);
						System.out.println("x="+getX()+"y="+getY());
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre) "
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2) "   
								+ "implies getX()=getX()@pre AND getY()=getY()@pre-1 not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre) "
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2) "   
								+ "implies getDirection()=getDirection()@pre not satisfied");					
					}
				}
				//Grimpeur passe au dessus de l'obstacle
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 								  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
				 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)   
				 * 						\implies  getX()=getX()@pre+1 AND getY()=getY()@pre-1
				 * 													  AND getDirection()=getDirection()@pre */
				else if(isGrimpeurPre && !getGameEng().isObstacle(xPre+1, yPre) && getGameEng().isObstacle(xPre+1, yPre-1) && !getGameEng().isObstacle(xPre, yPre-2) && !getGameEng().isObstacle(xPre+1, yPre-2)){
					if(x!=xPre+1 || y!=yPre-1){
						System.err.println("ID = "+super.getId());
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre) "
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2) "
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2) "   
								+ "implies getX()=getX()@pre+1 AND getY()=getY()@pre-1 not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre) "
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2) "
								+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2) "   
								+ "implies getDirection()=getDirection()@pre not satisfied");					
					}
				}
				//Le grimpeur tombe et change de direction
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1)
				 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
				 * 													AND getDirection()=GAUCHER
				 * 													AND getType()=TOMBEUR */
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre, yPre-2) && !getGameEng().isObstacle(xPre, yPre+1)){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2) "
								+ "AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1) "
								+ "implies  getX()=getX()@pre AND getY()=getY()@pre not satisfied");
					}

					//					if(super.getDirection()!=Direction.GAUCHER){
					//						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
					//								+ "AND isGrimpeur() "
					//								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2) "
					//								+ "AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1) "
					//								+ "implies getDirection()=GAUCHER not satisfied");					
					//					}

					if(super.getType()!=Type.TOMBEUR)
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2) "
								+ "AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1) "
								+ "implies getType()=TOMBEUR not satisfied");					

				}
				//Grimpeur bloqué, doit changer de direction
				/*\post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)
				 * 								  AND getGameEng().isObstacle(getX()@pre+1,getY()@pre)
				 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
				 * 													AND getDirection()=GAUCHER 
				 * 													AND getType()=getType()@pre */
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre, yPre-2) && getGameEng().isObstacle(xPre, yPre+1) && getGameEng().isObstacle(xPre+1, yPre)){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(xPre, yPre-2) "
								+ "AND getGameEng().isObstacle(xPre, yPre+1) "
								+ "getGameEng().isObstacle(xPre+1, yPre) "
								+ "implies getX()=getX()@pre AND getY()=getY()@pre not satisfied");
					}
					if(super.getDirection()!=Direction.GAUCHER){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(xPre, yPre-2) "
								+ "AND getGameEng().isObstacle(xPre, yPre+1) "
								+ "getGameEng().isObstacle(xPre+1, yPre) "
								+ "implies getDirection()=GAUCHER not satisfied");				
					}
					if(super.getType()!=typePre)
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER "
								+ "AND isGrimpeur() "
								+ "AND getGameEng().isObstacle(xPre, yPre-2) "
								+ "AND getGameEng().isObstacle(xPre, yPre+1) "
								+ "getGameEng().isObstacle(xPre+1, yPre) "
								+ "implies getType()=getType()@pre not satisfied");					

				} 

				/*************** FIN GRIMPEUR ****************/

				/*************** DEBUT BUILDER ***************/
				/*  \post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true
				 *  	AND getNombreToursBuilder()@pre < INTERVALLE_POSE_DALLE
				 *  	\implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1 */
				else if (isBuilderPre && isCurrentlyBuildingPre 
						&& nombreToursBuilderPre < Lemming.INTERVALLE_POSE_DALLE) {
					if (!(xPre == super.getX())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == DROITIER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getX() = getX()@pre not satisfied");
					}
					if (!(yPre == super.getY())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == DROITIER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getY() = getY()@pre not satisfied");
					}
					if(!(directionPre == super.getDirection())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == DROITIER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getDirection() = getDirection()@pre not satisfied");
					}
					if(!(nombreToursBuilderPre + 1 == super.getNombreToursBuilder())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == DROITIER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1 not satisfied");
					}
				}
				/**************** BUILDER POSE DALLE *****************/
				/* \post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true 
				 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
				 * 			AND getGameEng().isLibre(getX()@pre+1, getY()@pre)
				 * 			AND getGameEng().isLibre(getX()@pre+2 getY()@pre)
				 * 			AND getGameEng().isLibre(getX()@pre+3, getY()@pre)
				 * 			AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
				 * 			AND !getGameEng().isObstacle(getX()@pre+2, getY()@pre-2)
				 * 			AND !getGameEng().isObstacle(getX()@pre+3, getY()@pre-2)
				 * 			AND getNombreDallesPosees()@pre < 12
				 * 		 \implies getGameEng().getLevel().getNature(getX()+1, getY()) = DIRT
				 * 				  	AND getGameEng().getLevel().getNature(getX()+2, getY()) = DIRT
				 * 					AND getGameEng().getLevel().getNature(getX()+3, getY()) = DIRT
				 * 					AND getNombreDallesPosees() = getNombreDallesPosees()@pre + 1
				 * 					AND getX() = getX()@pre + 2
				 * 					AND getY() = getY()@pre - 1	
				 * 					AND getDirection() = getDirection()@pre
				 * 					AND getNombreToursBuilder() = 0;
				 */
				else if (isBuilderPre && isCurrentlyBuildingPre 
						&& nombreToursBuilderPre == Lemming.INTERVALLE_POSE_DALLE
						&& isLibreDroite1Pre && isLibreDroite2Pre && isLibreDroite3Pre
						&& !isObstacleDroite1Haut2Pre && !isObstacleDroite2Haut2Pre
						&& !isObstacleDroite3Haut2Pre
						&& nombreDallesPoseesPre < Lemming.MAX_DALLES) {
					String msg = "post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true"+ 
							"AND getNombreToursBuilder()@pre = 2"+
							"AND getGameEng().isLibre(getX()@pre+1, getY()@pre)"+
							"AND getGameEng().isLibre(getX()@pre+2 getY()@pre)"+
							"AND getGameEng().isLibre(getX()@pre+3, getY()@pre)"+
							"AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)"+
							"AND !getGameEng().isObstacle(getX()@pre+2, getY()@pre-2)"+
							"AND !getGameEng().isObstacle(getX()@pre+3, getY()@pre-2)"+
							"AND getNombreDallesPosees()@pre < 12";
					if (!(super.getGameEng().getLevel().getNature(xPre+1, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseDroite1 = DIRT not satisfied");
					}
					if (!(super.getGameEng().getLevel().getNature(xPre+2, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseDroite2 = DIRT not satisfied");
					}
					if (!(super.getGameEng().getLevel().getNature(xPre+3, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseDroite3 = DIRT not satisfied");
					}
					if (!(super.getNombreDallesPosees() == nombreDallesPoseesPre + 1)) {
						throw new PostConditionError(msg+" implies getNombreDallesPosees() = "+
								"getNombreDallesPosees()@pre + 1 not satisfied");
					}
					if (!(super.getX() == xPre+2)) {
						throw new PostConditionError(msg+" getX() = getX()@pre + 2 not satisfied");
					}
					if (!(super.getY() == yPre-1)) {
						throw new PostConditionError(msg+" getY() = getY()@pre - 1 not satisfied");
					}
					if (!(super.getDirection() == directionPre)) {
						throw new PostConditionError(msg+" getDirection() = getDirection()@pre not satisfied");
					}
					if (!(super.getNombreToursBuilder() == 0)) {
						throw new PostConditionError(msg+" getNombreToursBuilder() = 0 not satisfied");
					}			
				}
				/****************** BUILDER S'ARRETTE **********************/
				/*  \post isBuilder() = true AND getDirection() == DROITIER AND isCurrentlyBuilding() = true 
				 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
				 * 	 		AND (getGameEng().isLibre(getX()@pre+1, getY()@pre)
				 * 				OR getGameEng().isLibre(getX()@pre+2 getY()@pre)
				 * 				OR getGameEng().isLibre(getX()@pre+3, getY()@pre)
				 * 				OR getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
				 * 				OR getGameEng().isObstacle(getX()@pre+2, getY()@pre-2)
				 * 				OR getGameEng().isObstacle(getX()@pre+3, getY()@pre-2)
				 * 				OR getNombreDallesPosees()@pre >= 12)
				 * 		 \implies	AND getNombreDallesPosees() = getNombreDallesPosees()@pre
				 * 					AND getX() = getX()@pre
				 * 					AND getY() = getY()@pre	
				 * 					AND getDirection() = getDirection()@pre
				 * 					AND getNombreToursBuilder() = 0;
				 * 					AND isCurrentlyBuilding() = false
				 */
				else if (isBuilderPre && isCurrentlyBuildingPre
						&& nombreToursBuilderPre == Lemming.INTERVALLE_POSE_DALLE
						&& (!isLibreDroite1Pre || !isLibreDroite2Pre || !isLibreDroite3Pre 
								|| isObstacleDroite1Haut2Pre
								|| isObstacleDroite2Haut2Pre 
								|| isObstacleDroite3Haut2Pre 
								|| nombreDallesPoseesPre >= Lemming.MAX_DALLES)) {
					String msg = "post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true"+ 
							"AND getNombreToursBuilder()@pre = Lemming.INTERVALLE_POSE_DALLE"+
							"AND (getGameEng().isLibre(getX()@pre+1, getY()@pre)"+
							"OR getGameEng().isLibre(getX()@pre+2 getY()@pre)"+
							"OR getGameEng().isLibre(getX()@pre+3, getY()@pre)"+
							"OR getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)"+
							"OR getNombreDallesPosees()@pre >= Lemming.MAX_DALLES)";
					if (!(super.getNombreDallesPosees() == nombreDallesPoseesPre)) {
						throw new PostConditionError(msg+" implies getNombreDallesPosees() = "+
								"getNombreDallesPosees()@pre not satisfied");
					}
					if (!(super.getX() == xPre)) {
						throw new PostConditionError(msg+" getX() = getX()@pre not satisfied");
					}
					if (!(super.getY() == yPre)) {
						throw new PostConditionError(msg+" getY() = getY()@pre not satisfied");
					}
					if (!(super.getDirection() == directionPre)) {
						throw new PostConditionError(msg+" getDirection() = getDirection()@pre not satisfied");
					}
					if (!(super.getNombreToursBuilder() == 0)) {
						throw new PostConditionError(msg+" getNombreToursBuilder() = 0 not satisfied");
					}
					if (!(super.isCurrentlyBuilding() == false)) {
						throw new PostConditionError(msg+" isCurrentlyBuilding() = false not satisfied");
					}
				}


				/*************** FIN BUILDER *****************/


				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 							  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
				 * 							  \implies getDirection() = GAUCHER 
				 * 										AND getType() = MARCHEUR
				 * 										AND getX() = getX()@pre AND getY() = getY()@pre
				 */
				//			if(directionPre == Direction.DROITIER && super.getGameEng().isObstacle(xPre+1, yPre-1) 
				//					&& !(super.getDirection() == Direction.GAUCHER && super.getType() == Type.MARCHEUR && x == xPre && y == yPre))
				//				throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
				//						+ "implies getDirection() = GAUCHER AND getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre not satisfied");
				else if(super.getGameEng().isObstacle(xPre+1, yPre-1))  {
					if(!(super.getDirection() == Direction.GAUCHER))
						throw new PostConditionError("pas gaucher");
					if(!(super.getType() == Type.MARCHEUR))
						throw new PostConditionError("pas marcheur");
					if(!(x == xPre))
						throw new PostConditionError("x pas bon");
					if(!(y == yPre))
						throw new PostConditionError("y pas bon");

				}

				/* \post getType()@pre = MARCHEUR AND getDirecton()@pre = DROITIER
				 * 							  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 							  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
				 * 							  \implies getDirection() = GAUCHER
				 * 										AND getType() = MARCHEUR
				 * 										AND getX() = getX()@pre AND getY() = getY()@pre
				 */
				else if(super.getGameEng().isObstacle(xPre+1, yPre) && super.getGameEng().isObstacle(xPre+1, yPre-2)
						&& !(super.getDirection() == Direction.GAUCHER && super.getType() == Type.MARCHEUR && x == xPre && y == yPre))
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirecton()@pre = DROITIER AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)"
							+ "AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-2) "
							+ "implies getDirection() = GAUCHER AND getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre not satisfied");

				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 							  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 							  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
				 * 							  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
				 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
				 */
				else if(super.getGameEng().isObstacle(xPre+1, yPre) && !super.getGameEng().isObstacle(xPre+1, yPre-1)
						&& !super.getGameEng().isObstacle(xPre+1, yPre-2)
						&& !(x == xPre+1 && y == yPre-1 && super.getType() == Type.MARCHEUR)){
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER AND getGameEng().isObstacle(getX()@pre+1, getY()@pre) "
							+ "AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2) "
							+ "implies getX() = getX()@pre+1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR not satisfied");
				}
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
				 * 							  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)
				 * 							  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
				 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre AND getType() = MARCHEUR
				 */
				else if(!super.getGameEng().isObstacle(xPre+1, yPre) && !super.getGameEng().isObstacle(xPre+1, yPre-1)
						&& !(super.getX() == xPre+1 && y == yPre && super.getType() == Type.MARCHEUR))
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)"
							+ " AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) "
							+ "implies getX() = getX()@pre+1 AND getY() = getY()@pre AND getType() = MARCHEUR not satisfied");
			}else if(directionPre == Direction.GAUCHER){
				/*************** MARCHEUR GAUCHER **************/

				/*************** GRIMPEUR ***************/
				//Devient tombeur s'il n'y a pas d'obstacle en dessous ni à sa gauche
				/* \post getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
				 * 												   AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 							  \implies getX() = getX()@pre AND getY() = getY()@pre
				 * 														   AND getType() = TOMBEUR
				 * 														   AND getDirection()=getDirection()@pre */
				if(typePre==Type.MARCHEUR && isGrimpeurPre 
						&& !super.getGameEng().isObstacle(xPre,yPre+1)
						&& !super.getGameEng().isObstacle(xPre-1,yPre)){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(xPre,yPre+1)"
								+ "AND getGameEng().isObstacle(xPre-1,yPre)"
								+ "implies getX()=getX()@pre AND getY()=getY()@pre not satisfied");
					}
					if(super.getType()!=Type.TOMBEUR){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(xPre,yPre+1)"
								+ "AND getGameEng().isObstacle(xPre-1,yPre)"
								+ "implies getType() = TOMBEUR not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(xPre,yPre+1)"
								+ "AND getGameEng().isObstacle(xPre-1,yPre)"
								+ "implies getDirection()=getDirection()@pre not satisfied");
					}
				}
				//Grimpeur grimpe
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
				 * 								  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)   
				 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre-1
				 * 													AND getDirection()=getDirection()@pre */
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre-1, yPre-1) && getGameEng().isObstacle(xPre-1, yPre) && !getGameEng().isObstacle(xPre, yPre-2)){
					if(x!=xPre ||y != yPre-1){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getX()=getX()@pre AND getY()=getY()@pre-1 not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getDirection()=getDirection()@pre not satisfied");					
					}
				}
				//Le grimpeur passe au dessus de l'obstacle
				/* post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 								  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
				 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)   
				 * 						\implies  getX()=getX()@pre-1 AND getY()=getY()@pre-1
				 * 													  AND getDirection()=getDirection()@pre */
				else if(isGrimpeurPre && !getGameEng().isObstacle(xPre-1, yPre-1) && getGameEng().isObstacle(xPre-1, yPre) && !getGameEng().isObstacle(xPre, yPre-2) && !getGameEng().isObstacle(xPre-1, yPre-2)){
					if(x!=xPre-1 || y!=yPre-1){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getX()=getX()@pre-1 AND getY()=getY()@pre-1 not satisfied");
					}
					if(super.getDirection()!=directionPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getDirection()=getDirection()@pre not satisfied");					
					}
				}
				//Le grimpeur tombe et change de direction
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1) 
				 * 	  						\implies  getX()=getX()@pre AND getY()=getY()@pre
				 * 													AND getDirection()=DROITIER
				 * 													AND getType()=TOMBEUR*/
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre, yPre-2) &&   !getGameEng().isObstacle(xPre, yPre+1)){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getX()=getX()@pre-1 AND getY()=getY()@pre-1 not satisfied");
					}
					if(super.getDirection()!=Direction.DROITIER){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getDirection()=DROITIER not satisfied");					
					}
					if(super.getType()!=Type.TOMBEUR)
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
								+ "AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "implies getType()=TOMBEUR not satisfied");					

				}
				//Cas Grimpeur marcheur et droitier
				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 								  AND isGrimpeur()
				 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
				 * 								  AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)
				 * 								  AND getGameEng().isObstacle(getX()@pre-1,getY()@pre)
				 * 						\implies getX()=getX()@pre AND getY()=getY()@pre
				 * 													AND getDirection()=DROITIER
				 * 													AND getType()=getType()@pre */
				else if(isGrimpeurPre && getGameEng().isObstacle(xPre, yPre-2) &&   getGameEng().isObstacle(xPre, yPre+1) && getGameEng().isObstacle(xPre-1, yPre) ){
					if(x!=xPre || y!=yPre){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1,getY()@pre)"
								+ "implies getX()=getX()@pre AND getY()=getY()@pre not satisfied");
					}
					if(super.getDirection()!=Direction.DROITIER){
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1,getY()@pre)"
								+ "implies getDirection()=DROITIER not satisfied");					
					}
					if(super.getType()!=typePre)
						throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER"
								+ "AND isGrimpeur()"
								+ "AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)"
								+ "AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)"
								+ "AND getGameEng().isObstacle(getX()@pre-1,getY()@pre)"
								+ "implies getType()=getType()@pre not satisfied");					

				}
				/**************** FIN GRIMPEUR *****************/
				/*************** DEBUT BUILDER GAUCHER ***************/
				/*  \post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true
				 *  	AND getNombreToursBuilder()@pre < INTERVALLE_POSE_DALLE
				 *  	\implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1 */
				else if (isBuilderPre && isCurrentlyBuildingPre 
						&& nombreToursBuilderPre < Lemming.INTERVALLE_POSE_DALLE) {
					if (!(xPre == super.getX())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == GAUCHER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getX() = getX()@pre not satisfied");
					}
					if (!(yPre == super.getY())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == GAUCHER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getY() = getY()@pre not satisfied");
					}
					if(!(directionPre == super.getDirection())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == GAUCHER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getDirection() = getDirection()@pre not satisfied");
					}
					if(!(nombreToursBuilderPre + 1 == super.getNombreToursBuilder())) {
						throw new PostConditionError("post isBuilder() AND getDirection() == GAUCHER AND"
								+ " isCurrentlyBuilding() = true AND getNombreToursBuilder()@pre < 2"+
								"implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1 not satisfied");
					}
				}
				/**************** BUILDER POSE DALLE *****************/
				/* \post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true 
				 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
				 * 			AND getGameEng().isLibre(getX()@pre-1, getY()@pre)
				 * 			AND getGameEng().isLibre(getX()@pre-2 getY()@pre)
				 * 			AND getGameEng().isLibre(getX()@pre-3, getY()@pre)
				 * 			AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)
				 * 			AND !getGameEng().isObstacle(getX()@pre-2, getY()@pre-2)
				 * 			AND !getGameEng().isObstacle(getX()@pre-3, getY()@pre-2)
				 * 			AND getNombreDallesPosees()@pre < 12
				 * 		 \implies getGameEng().getLevel().getNature(getX()-1, getY()) = DIRT
				 * 				  	AND getGameEng().getLevel().getNature(getX()-2, getY()) = DIRT
				 * 					AND getGameEng().getLevel().getNature(getX()-3, getY()) = DIRT
				 * 					AND getNombreDallesPosees() = getNombreDallesPosees()@pre + 1
				 * 					AND getX() = getX()@pre - 2
				 * 					AND getY() = getY()@pre - 1	
				 * 					AND getDirection() = getDirection()@pre
				 * 					AND getNombreToursBuilder() = 0;
				 */
				else if (isBuilderPre && isCurrentlyBuildingPre 
						&& nombreToursBuilderPre == Lemming.INTERVALLE_POSE_DALLE
						&& isLibreGauche1Pre && isLibreGauche2Pre && isLibreGauche3Pre
						&& !isObstacleGauche1Haut2Pre 
						&& !isObstacleGauche2Haut2Pre
						&& !isObstacleGauche3Haut2Pre
						&& nombreDallesPoseesPre < Lemming.MAX_DALLES) {
					String msg = "post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true"+ 
							"AND getNombreToursBuilder()@pre = 2"+
							"AND getGameEng().isLibre(getX()@pre-1, getY()@pre)"+
							"AND getGameEng().isLibre(getX()@pre-2 getY()@pre)"+
							"AND getGameEng().isLibre(getX()@pre-3, getY()@pre)"+
							"AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)"+
							"AND !getGameEng().isObstacle(getX()@pre-2, getY()@pre-2)"+
							"AND !getGameEng().isObstacle(getX()@pre-3, getY()@pre-2)"+
							"AND getNombreDallesPosees()@pre < 12";
					if (!(super.getGameEng().getLevel().getNature(xPre-1, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseGauche1= DIRT not satisfied");
					}
					if (!(super.getGameEng().getLevel().getNature(xPre-2, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseGauche2 = DIRT not satisfied");
					}
					if (!(super.getGameEng().getLevel().getNature(xPre-3, yPre) == Nature.DIRT)) {
						throw new PostConditionError(msg+" implies caseGauche3 = DIRT not satisfied");
					}
					if (!(super.getNombreDallesPosees() == nombreDallesPoseesPre + 1)) {
						throw new PostConditionError(msg+" implies getNombreDallesPosees() = "+
								"getNombreDallesPosees()@pre + 1 not satisfied");
					}
					if (!(super.getX() == xPre-2)) {
						throw new PostConditionError(msg+" getX() = getX()@pre - 2 not satisfied");
					}
					if (!(super.getY() == yPre-1)) {
						throw new PostConditionError(msg+" getY() = getY()@pre - 1 not satisfied");
					}
					if (!(super.getDirection() == directionPre)) {
						throw new PostConditionError(msg+" getDirection() = getDirection()@pre not satisfied");
					}
					if (!(super.getNombreToursBuilder() == 0)) {
						throw new PostConditionError(msg+" getNombreToursBuilder() = 0 not satisfied");
					}			
				}
				/****************** BUILDER S'ARRETTE **********************/
				/*  \post isBuilder() = true AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true 
				 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
				 * 	 		AND ( !getGameEng().isLibre(getX()@pre-1, getY()@pre)
				 * 				OR !getGameEng().isLibre(getX()@pre-2 getY()@pre)
				 * 				OR !getGameEng().isLibre(getX()@pre-3, getY()@pre)
				 * 	 			OR getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)
				 * 				OR getGameEng().isObstacle(getX()@pre-2, getY()@pre-2)
				 * 				OR getGameEng().isObstacle(getX()@pre-3, getY()@pre-2)
				 * 				OR getNombreDallesPosees()@pre >= 12)
				 * 		 \implies   AND getNombreDallesPosees() = getNombreDallesPosees()@pre
				 * 					AND getX() = getX()@pre
				 * 					AND getY() = getY()@pre	
				 * 					AND getDirection() = getDirection()@pre
				 * 					AND getNombreToursBuilder() = 0;
				 * 					AND isCurrentlyBuilding() = false
				 */
				else if (isBuilderPre && isCurrentlyBuildingPre
						&& nombreToursBuilderPre == Lemming.INTERVALLE_POSE_DALLE
						&& (!isLibreGauche1Pre || !isLibreGauche2Pre || !isLibreGauche3Pre 
								|| isObstacleGauche1Haut2Pre
								|| isObstacleGauche2Haut2Pre 
								|| isObstacleGauche3Haut2Pre 
								|| nombreDallesPoseesPre >= Lemming.MAX_DALLES)) {
					String msg = "post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true"+ 
							"AND getNombreToursBuilder()@pre = Lemming.INTERVALLE_POSE_DALLE"+
							"AND (!getGameEng().isLibre(getX()@pre-1, getY()@pre)"+
							"OR !getGameEng().isLibre(getX()@pre-2 getY()@pre)"+
							"OR !getGameEng().isLibre(getX()@pre-3, getY()@pre)"+
							"OR getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)"+
							"OR getGameEng().isObstacle(getX()@pre-2, getY()@pre-2)"+
							"OR getGameEng().isObstacle(getX()@pre-3, getY()@pre-2)"+
							"OR getNombreDallesPosees()@pre >= Lemming.MAX_DALLES)";
					if (!(super.getNombreDallesPosees() == nombreDallesPoseesPre)) {
						throw new PostConditionError(msg+" implies getNombreDallesPosees() = "+
								"getNombreDallesPosees()@pre not satisfied");
					}
					if (!(super.getX() == xPre)) {
						throw new PostConditionError(msg+" getX() = getX()@pre not satisfied");
					}
					if (!(super.getY() == yPre)) {
						throw new PostConditionError(msg+" getY() = getY()@pre not satisfied");
					}
					if (!(super.getDirection() == directionPre)) {
						throw new PostConditionError(msg+" getDirection() = getDirection()@pre not satisfied");
					}
					if (!(super.getNombreToursBuilder() == 0)) {
						throw new PostConditionError(msg+" getNombreToursBuilder() = 0 not satisfied");
					}
					if (!(super.isCurrentlyBuilding() == false)) {
						throw new PostConditionError(msg+" isCurrentlyBuilding() = false not satisfied");
					}
				}


				/*************** FIN BUILDER *****************/

				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 							  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
				 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
				 * 										AND getX() = getX()@pre AND getY() = getY()@pre
				 */
				else if(directionPre == Direction.GAUCHER && super.getGameEng().isObstacle(xPre-1, yPre-1)
						&& !(super.getDirection() == Direction.DROITIER && super.getType() == Type.MARCHEUR && x == xPre && y == yPre))
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
							+ "&& implies getDirection() = DROITIER AND getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre not satisfied");

				/* \post getType()@pre = MARCHEUR AND getDirecton()@pre = GAUCHER
				 * 							  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 							  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)
				 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
				 * 										AND getX() = getX()@pre AND getY() = getY()@pre
				 */
				else if(directionPre == Direction.GAUCHER && super.getGameEng().isObstacle(xPre-1, yPre) && super.getGameEng().isObstacle(xPre-1, yPre-2)
						&& !(super.getDirection() == Direction.DROITIER && super.getType() == Type.MARCHEUR && x == xPre && y == yPre))
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirecton()@pre = GAUCHER AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
							+ "AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)"
							+ "implies getDirection() = DROITIER AND getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre not satisfied");

				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 							  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 							  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
				 * 							  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)
				 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
				 */
				else if(directionPre == Direction.GAUCHER && super.getGameEng().isObstacle(xPre-1, yPre) && !super.getGameEng().isObstacle(xPre-1, yPre-1)
						&& !super.getGameEng().isObstacle(xPre-1, yPre-2)
						&& !(x == xPre-1 && y == yPre-1 && super.getType() == Type.MARCHEUR))
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
							+ "AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1) AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)"
							+ "implies getX() = getX()@pre-1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR");

				/* \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
				 * 							  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
				 * 							  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
				 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre AND getType() = MARCHEUR
				 */
				else if(directionPre == Direction.GAUCHER && !super.getGameEng().isObstacle(xPre-1, yPre) && !super.getGameEng().isObstacle(xPre-1, yPre-1)
						&& !(x == xPre-1 && y == yPre && super.getType() == Type.MARCHEUR)){
					throw new PostConditionError("step : getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)"
							+ " AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)"
							+ " implies getX() = getX()@pre-1 AND getY() = getY()@pre AND getType() = MARCHEUR");
				}
			}
		}


		else if (typePre == Type.TOMBEUR){
			/************** TOMBEUR **************/

			if (!super.getGameEng().isObstacle(xPre, yPre+1) && super.isFlotteur()){
				if(super.getType()!=typePre){
					throw new PostConditionError("Flotteur descend mais change de type");
				}
				if(super.getDirection()!=directionPre){
					throw new PostConditionError("Flotteur descend mais direction a change");					
				}
				if(super.getX()!=xPre){
					throw new PostConditionError("Flotteur descend mais x change ");
				}
				if(tombePre%2==0){
					if(super.getY()!=yPre+1)
						throw new PostConditionError("Flotteur descend mais y ne change pas");
				}else{
					if(super.getY()!=yPre)
						throw new PostConditionError("Flotteur descend mais y change  alors qu'il est bloque");
				}
			}
			else if(super.getGameEng().isObstacle(xPre, yPre+1) &&  super.isFlotteur()){
				if(super.getType()!=Type.MARCHEUR){
					throw new PostConditionError("Flotteur et obstacle mais n'est pas devenue marcheur");
				}
				if(super.getDirection()!=directionPre){
					throw new PostConditionError("Flotteur et obstacle mais direction change");					
				}
				if(super.getX()!=xPre || super.getY()!=yPre){
					throw new PostConditionError("Flotteur et obstacle mais x ou y a bouge");
				}
			}


			/* \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre < 8
			 * 						\implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre+1
			 * 								 AND tombeDepuis() = 0
			 */

			else if(super.getGameEng().isObstacle(xPre, yPre+1) && tombePre < 8 && !(super.getType() == Type.MARCHEUR && super.getX() == xPre && super.getY() == yPre))

				throw new PostConditionError("step : getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre < 8"
						+ "implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre+1 AND tombeDepuis() = 0");

			/* \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre >= 8
			 * 							 \implies le lemming meurt : getId() n'appartient pas a getGameEng().getLemmingsActif()
			 * 									AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre + 1
			 */
			else if(super.getGameEng().isObstacle(xPre, yPre+1) && tombePre >= 8 
					&& !(!super.getGameEng().getLemmingsActifs().contains(super.getId())
							&& super.getGameEng().getNombreMorts() == nbMortsPre+1
							&& super.getGameEng().getNombreActifs() == nbActifsPre-1))
				throw new PostConditionError("step : getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre >= 8"
						+ "implies le lemming meurt : getId() n'appartient pas a getGameEng().getLemmingsActif()"
						+ "AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre + 1");

			/* \post getType()@pre = TOMBEUR AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre < 8
			 * 							 \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre + 1
			 * 									AND tombeDepuis() = tombeDepuis()@pre+1
			 */
			else if(!super.getGameEng().isObstacle(xPre, yPre+1) && tombePre < 8)  
				if (!(super.getType() == Type.TOMBEUR && x == xPre && y == yPre+1 && super.tombeDepuis() == tombePre+1)) {
					System.out.println("getX()@pre = "+xPre+" - getX() = "+x);
					System.out.println("getX()@pre = "+yPre+" - getX() = "+y);
					throw new PostConditionError("step : getType()@pre = TOMBEUR AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1) AND tombeDepuis()@pre < 8"
							+ "implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre + 1 AND tombeDepuis() = tombeDepuis()@pre+1");
				}

		}

		else if (typePre == Type.CREUSEUR) {
			/****************** CREUSEUR ********************/
			/* \post getType()@pre = CREUSEUR AND !getGameEng().isObstacle(getX(), getY()+1)
			 * 			\implies getType() = TOMBEUR AND getDirection()@pre = getDirection()
			 * 					AND getX() = getX()@pre AND getY() = getY()@pre 
			 */
			if (!isObstacleCaseDessousPre) {
				if (!(super.getType() == Type.TOMBEUR && directionPre == super.getDirection()
						&& xPre == x && yPre == y)) {
					System.out.println("type = "+super.getType());
					System.out.println("case en dessous = "+super.getGameEng().getLevel().getNature(xPre, yPre+1));
					System.out.println("case en dessous = "+super.getGameEng().isObstacle(xPre, yPre+1));
					throw new PostConditionError("getType()@pre = CREUSEUR "
							+ "AND !getGameEng().isObstacle(getX(), getY()+1"+
							" implies getType() = TOMBEUR AND getDirection()@pre = getDirection()" +
							" AND getX() = getX()@pre AND getY() = getY()@pre ");
				}
			}			 
			/* \post getType()@pre = CREUSEUR AND getGameEng().getLevel().getNature(getX(), getY()+1) = METAL
			 * 			\implies getType() = MARCHEUR AND getDirection()@pre = getDirection()
			 * 					AND getX() = getX()@pre AND getY() = getY()@pre
			 *
			 */
			else if(super.getGameEng().getLevel().getNature(xPre, yPre+1) == Nature.METAL) {
				if (!(super.getType() == Type.MARCHEUR && 
						super.getDirection() == directionPre &&
						x == xPre && y == yPre)) {
					throw new PostConditionError("step : getType()@pre = CREUSEUR AND "+
							"getGameEng().getLevel().getNature(getX(), getY()+1) = METAL" +
							"implies getType() = MARCHEUR AND getDirection()@pre = getDirection()"+
							"AND getX() = getX()@pre AND getY() = getY()@pre");
				}
			}
			/*\post getType()@pre = CREUSEUR AND getGameEng().getLevel().getNature(getX(), getY()+1) = DIRT
			 * 			\implies getType() = CREUSEUR AND getDirection()@pre = getDirection() 
			 * 				AND !getGameEng().isObstacle(getX(), getY()+1) AND 
			 * 				getX() = getX()@pre AND getY() = getY()@pre + 1 AND
			 * 				if getGameEng().getLevel().getNature(getX()-1, getY()+1) = DIRT
			 * 				then !getGameEng().isObstacle(getX()-1, getY()+1)
			 * 				else if getGameEng().getLevel().getNature(getX()+1, g
			 */			 
			else if (super.getGameEng().getLevel().getNature(xPre, yPre+1) == Nature.DIRT) {
				if (!(super.getType() == Type.CREUSEUR &&
						super.getDirection() == directionPre &&
						!super.getGameEng().isObstacle(xPre, yPre+1) &&
						super.getX() == xPre && y == yPre + 1)) {
					throw new PostConditionError("post getType()@pre = CREUSEUR AND "+
							"getGameEng().getLevel().getNature(getX(), getY()+1) = DIRT" +
							"implies getType() = CREUSEUR AND getDirection()@pre = getDirection()"+ 
							"AND !getGameEng().isObstacle(getX(), getY()+1) AND"+ 
							"getX() = getX()@pre AND getY() = getY()@pre + 1 AND");
				}
				else {
					if (caseGaucheBasPre == Nature.DIRT) {
						if (!(super.getGameEng().getLevel().getNature(xPre-1, yPre+1) == Nature.EMPTY)) {
							throw new PostConditionError("if getGameEng().getLevel().getNature(getX()@pre-1, getY()@pre+1) = DIRT"+
									"then !getGameEng().isObstacle(getX()-1, getY()+1)");
						}
					}
					if (caseDroiteBasPre == Nature.DIRT) {
						if (!(super.getGameEng().getLevel().getNature(xPre+1, yPre+1) == Nature.EMPTY)) {
							throw new PostConditionError("if getGameEng().getLevel().getNature(getX()@pre+1, getY()@pre+1) = DIRT"+
									"then !getGameEng().isObstacle(getX()-1, getY()+1)");
						}
					}
				}
			}
		}
		/************* MINER ******************/
		else if (typePre == Type.MINER) {
			/* \post getType()@pre = MINER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 		\implies getType() = TOMBEUR AND getX() = getX()@pre
			 * 								AND getY() = getY()@pre
			 * 								AND getDirection()=getDirection()@pre
			 */
			if (!isObstacleCaseDessousPre) {
				String msg = "post getType()@pre = BASHER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)";
				if(!(super.getType() == Type.TOMBEUR)) {
					throw new PostConditionError(msg+" implies getType() = TOMBEUR not satisfied");
				}
				if(!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if(!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if(!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection()=getDirection()@pre not satisfied");
				}
			}
			/********** MINER DROITIER CREUSE EN BAS *************/
			/* ******** MINER DROITIER DESCEND ***********
			 * \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == DROITIER
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre+1) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre+1) != METAL
			 *  							AND getGameEng().getLevel().getNature(getX()@pre + 3, getY()@pre+1) != METAL
			 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
			 *     									AND getX() = getX()@pre + 1 AND getY() = getY()@pre
			 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre+1)
			 *     									AND !getGameEng().isObstacle(getX()@pre+2, getY()@pre+1)
			 *         								AND !getGameEng().isObstacle(getX()@pre+3, getY()@pre+1)
			 */
			else if(directionPre == Direction.DROITIER && isObstacleCaseDessousPre
					&& !isMetalCaseDroite1Pre 
					&& !isMetalCaseDroite2Pre
					&& !isMetalCaseDroiteBas1Pre 
					&& !isMetalCaseDroiteBas2Pre
					&& !isMetalCaseDroiteBas3Pre) {
				String msg = "post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == DROITIER "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre+1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre+1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 3, getY()@pre+1) != METAL	";
				if (!(super.getType() == typePre)) {
					throw new PostConditionError(msg+" implies getType() = getType@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
				if (!(super.getX() == xPre + 1)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre + 1 not satisfied");
				}
				if (!(super.getY() == yPre + 1)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+1, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+1, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+2, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+2, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+1, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+1, getY()@pre+1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+2, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+2, getY()@pre+1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+3, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+3, getY()@pre+1) not satisfied");
				}
			}
			/************ MINER DROITIER S'ARRETE *************/
			/* *************** MINER DROITIER S'ARRETE **************
			 * \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == DROITIER
			 * 								AND (getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre+1) = METAL
			 * 									OR getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre+1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre + 3, getY()@pre+1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre) = METAL)
			 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
			 *     								 AND getDirection() = getDirection()@pre	
			 *  
			 */ 
			else if (directionPre == Direction.DROITIER && isObstacleCaseDessousPre
					&& (isMetalCaseDroiteBas1Pre 
							|| isMetalCaseDroiteBas2Pre
							|| isMetalCaseDroiteBas3Pre
							|| isMetalCaseDroite1Pre
							|| isMetalCaseDroite2Pre)) {
				String msg = "post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == DROITIER "
						+ "AND (getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre+1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre+1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre + 3, getY()@pre+1) = METAL ";
				if (!(super.getType() == Type.MARCHEUR)) {
					throw new PostConditionError(msg+" implies getType() = MARCHEUR not satisfied");
				}
				if (!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
			}
			/********** MINER GAUCHER CREUSE EN BAS*************/
			/*\post getType()@pre = MINEUR AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == GAUCHER
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) != METAL
			 *  							AND getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) != METAL
			 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
			 *     									AND getX() = getX()@pre + 1 AND getY() = getY()@pre
			 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre+1)
			 *     									AND !getGameEng().isObstacle(getX()@pre-2, getY()@pre+1)
			 *         								AND !getGameEng().isObstacle(getX()@pre-3, getY()@pre+1)
			 */         
			else if(directionPre == Direction.GAUCHER && isObstacleCaseDessousPre
					&& !isMetalCaseGauche1Pre 
					&& !isMetalCaseGauche2Pre
					&& !isMetalCaseGaucheBas1Pre 
					&& !isMetalCaseGaucheBas2Pre
					&& !isMetalCaseGaucheBas3Pre) {
				String msg = "post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == GAUCHER "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) != METAL	";
				if (!(super.getType() == typePre)) {
					throw new PostConditionError(msg+" implies getType() = getType@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
				if (!(super.getX() == xPre - 1)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre - 1 not satisfied");
				}
				if (!(super.getY() == yPre + 1)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-1, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-1, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-2, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-2, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-1, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-1, getY()@pre+1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-2, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-2, getY()@pre+1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-3, yPre+1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-3, getY()@pre+1) not satisfied");
				}
			}
			/************ MINER GAUCHER S'ARRETE *************/
			/* \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == GAUCHER
			 * 								AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) = METAL
			 * 									OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre) = METAL)
			 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
			 *     								 AND getDirection() = getDirection()@pre
			 */  
			else if (directionPre == Direction.GAUCHER && isObstacleCaseDessousPre
					&& (isMetalCaseGaucheBas1Pre 
							|| isMetalCaseGaucheBas2Pre
							|| isMetalCaseGaucheBas3Pre
							|| isMetalCaseGauche1Pre
							|| isMetalCaseGauche2Pre)) {
				String msg = "post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == GAUCHER "
						+ "AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre) = METAL ";
				if (!(super.getType() == Type.MARCHEUR)) {
					throw new PostConditionError(msg+" implies getType() = MARCHEUR not satisfied");
				}
				if (!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
			}
		}
		/************* BASHEUR ******************/
		else if (typePre == Type.BASHER) {
			/* \post getType()@pre = BASHER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 		\implies getType() = TOMBEUR AND getX() = getX()@pre
			 * 								AND getY() = getY()@pre
			 * 								AND getDirection()=getDirection()@pre
			 */
			if (!isObstacleCaseDessousPre) {
				String msg = "post getType()@pre = BASHER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)";
				if(!(super.getType() == Type.TOMBEUR)) {
					throw new PostConditionError(msg+" implies getType() = TOMBEUR not satisfied");
				}
				if(!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if(!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if(!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection()=getDirection()@pre not satisfied");
				}
			}
			/********** BASHEUR DROITIER CREUSE *************/
			/*\post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == DROITIER
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) != METAL
			 *  							AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) != METAL
			 * 	 							AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre)
			 * 								AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre-1)
			 *  							AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre-2)
			 *  							AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL
			 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
			 *     									AND getX() = getX()@pre + 1 AND getY() = getY()@pre
			 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)
			 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
			 *         								AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
			 *         								AND nbCreuseTunnel() = nbCreuseTunnel()@pre+1    	 
			 */         
			else if(directionPre == Direction.DROITIER && isObstacleCaseDessousPre
					&& !isMetalCaseDroiteTunnel1Pre 
					&& !isMetalCaseDroiteTunnel2Pre
					&& !isMetalCaseDroiteTunnel3Pre
					&& !isObstacle2Droite1Pre
					&& !isObstacle2Droite1Haut1Pre
					&& !isObstacle2Droite1Haut2Pre
					&& nbCreuseTunnelPre < MAX_CREUSE_TUNNEL) {
				String msg = "post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == DROITIER "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) != METAL	"
						+ "AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre)"
						+ "AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre - 1)"
						+ "AND !getGameEng().isObstacle2(getX()@pre + 1, getY()@pre - 2)"
						+ "AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL";
				if (!(super.getType() == typePre)) {
					throw new PostConditionError(msg+" implies getType() = getType@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
				if (!(super.getX() == xPre + 1)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre + 1 not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+1, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+1, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+1, yPre-1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre+1, yPre-2)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2) not satisfied");
				}
				if (!(super.nbCreuseTunnel() == nbCreuseTunnelPre +1)) {
					throw new PostConditionError(msg+" implies nbCreuseTunnel() = nbCreuseTunnel()@pre+1 not satisfied");
				}
			}
			/************ BASHEUR DROITIER S'ARRETE *************/
			/* \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == DROITIER
			 * 								AND (getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) = METAL
			 * 									OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) = METAL
			 *									OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre)
			 * 									OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre-1)
			 *  								OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre-2)
			 *  								OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)
			 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
			 *     								 AND getDirection() = getDirection()@pre
			 */  
			else if (directionPre == Direction.DROITIER && isObstacleCaseDessousPre
					&& (isMetalCaseDroiteTunnel1Pre 
							|| isMetalCaseDroiteTunnel2Pre
							|| isMetalCaseDroiteTunnel3Pre
							|| isObstacle2Droite1Pre
							|| isObstacle2Droite1Haut1Pre
							|| isObstacle2Droite1Haut2Pre
							|| nbCreuseTunnelPre >= MAX_CREUSE_TUNNEL)) {
				String msg = "post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == DROITIER "
						+ "AND (getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) = METAL "
						+ "OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre)"
						+ "OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre - 1)"
						+ "OR getGameEng().isObstacle2(getX()@pre + 1, getY()@pre - 2)"
						+ "OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)";
				if (!(super.getType() == Type.MARCHEUR)) {
					throw new PostConditionError(msg+" implies getType() = MARCHEUR not satisfied");
				}
				if (!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
			}
			/********** BASHEUR GAUCHER CREUSE *************/
			/* \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == GAUCHER
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) != METAL
			 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) != METAL
			 *  							AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) != METAL
			 *  	 						AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre)
			 * 								AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre-1)
			 *  							AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre-2)							
			 *  							AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL
			 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
			 *     									AND getX() = getX()@pre - 1 AND getY() = getY()@pre
			 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
			 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
			 *         								AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)
			 */         
			else if(directionPre == Direction.GAUCHER && isObstacleCaseDessousPre
					&& !isMetalCaseGaucheTunnel1Pre 
					&& !isMetalCaseGaucheTunnel2Pre
					&& !isMetalCaseGaucheTunnel3Pre
					&& !isObstacle2Gauche1Pre
					&& !isObstacle2Gauche1Haut1Pre
					&& !isObstacle2Gauche1Haut2Pre
					&& nbCreuseTunnelPre < MAX_CREUSE_TUNNEL) {
				String msg = "post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == GAUCHER "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) != METAL "
						+ "AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) != METAL	"
						+ "AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre)"
						+ "AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre - 1)"
						+ "AND !getGameEng().isObstacle2(getX()@pre - 1, getY()@pre - 2)"
						+ "AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL";
				if (!(super.getType() == typePre)) {
					throw new PostConditionError(msg+" implies getType() = getType@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
				if (!(super.getX() == xPre - 1)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre - 1 not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getY() = getY()@pre not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-1, yPre)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-1, getY()@pre) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-1, yPre-1)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1) not satisfied");
				}
				if (super.getGameEng().isObstacle(xPre-1, yPre-2)) {
					throw new PostConditionError(msg+" implies !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2) not satisfied");
				}
				if (!(super.nbCreuseTunnel() == nbCreuseTunnelPre +1)) {
					throw new PostConditionError(msg+" implies nbCreuseTunnel() = nbCreuseTunnel()@pre+1 not satisfied");
				}
			}
			/************ BASHEUR GAUCHER S'ARRETE *************/
			/* \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
			 * 								AND getDirection() == GAUCHER
			 * 								AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL
			 * 									OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) = METAL
			 *  								OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) = METAL
			 *    	 							OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre)
			 * 									OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre-1)
			 *  								OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre-2)
			 *  								OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)
			 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
			 *     								 AND getDirection() = getDirection()@pre
			 */  
			else if (directionPre == Direction.GAUCHER && isObstacleCaseDessousPre
					&& (isMetalCaseGaucheTunnel1Pre 
							|| isMetalCaseGaucheTunnel2Pre
							|| isMetalCaseGaucheTunnel3Pre
							|| isObstacle2Gauche1Pre
							|| isObstacle2Gauche1Haut1Pre
							|| isObstacle2Gauche1Haut2Pre
							|| nbCreuseTunnelPre >= MAX_CREUSE_TUNNEL)) {
				String msg = "post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1) "
						+ "AND getDirection() == GAUCHER "
						+ "AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) = METAL "
						+ "OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) = METAL "
						+ "OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre)"
						+ "OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre - 1)"
						+ "OR getGameEng().isObstacle2(getX()@pre - 1, getY()@pre - 2)"
						+ "OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)";
				if (!(super.getType() == Type.MARCHEUR)) {
					throw new PostConditionError(msg+" implies getType() = MARCHEUR not satisfied");
				}
				if (!(super.getX() == xPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getY() == yPre)) {
					throw new PostConditionError(msg+" implies getX() = getX()@pre not satisfied");
				}
				if (!(super.getDirection() == directionPre)) {
					throw new PostConditionError(msg+" implies getDirection() = getDirection()@pre not satisfied");
				}
			}
		}


		/********************** EXIT ***********************/

		/* \post if getGameEng().getLevel().isExit(getX()@pre, getY()@pre) == true 
		 * 			\implies le lemming est sauve : getId() n'appartient pas a getGameEng().getLemmingsActif()
		 * 					AND getGameEng().getNombreSauves() = getGameEng().getNombreSauves()@pre + 1
		 * 					AND getGameEng().getNombreActifs() = getGameEng().getNombreActifs()@pre - 1 
		 */		
		if(super.getGameEng().getLevel().isExit(xPre, yPre) 
				&& !(!super.getGameEng().getLemmingsActifs().contains(super.getId())
						&& getGameEng().getNombreSauves() == nbSauvesPre+1 && super.getGameEng().getNombreActifs() == nbActifsPre-1))
			throw new PostConditionError("step : getGameEng().getLevel().isExit(getX()@pre, getY()@pre) == true "
					+ "implies le lemming est sauve : getId() n'appartient pas a getGameEng().getLemmingsActif() "
					+ "AND getGameEng().getNombreSauves() = getGameEng().getNombreSauves()@pre + 1"
					+ "AND getGameEng().getNombreActifs() = getGameEng().getNombreActifs()@pre - 1");


	}
}


