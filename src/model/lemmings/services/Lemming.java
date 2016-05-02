package model.lemmings.services;

public interface Lemming extends RequireGameEngService {

	public enum Direction { 
		DROITIER, GAUCHER;
	}	

	public enum Type {
		MARCHEUR, TOMBEUR, CREUSEUR, BASHER, MINER, STOPPEUR;
	}

	public static final int INTERVALLE_POSE_DALLE = 2;
	public static final int MAX_DALLES = 12;
	public static final int MAX_CREUSE_TUNNEL = 20;


	/** Observateurs **/
	public Direction getDirection();
	public Type getType();
	public int getX();
	public int getY();

	// \pre getType() = TOMBEUR
	public int tombeDepuis();

	// \pre isExploseur = true
	public int exploseurDepuis();

	public int getId();

	public boolean isGrimpeur();
	public boolean isFlotteur();
	public boolean isBuilder();
	public boolean isExploseur();
	
	public GameEng getGameEng();


	// \pre isBuilder()
	public boolean isCurrentlyBuilding();
	
	// \pre isGrimpeur() 
	public boolean isCurrentlyClimbing();

	// \pre isBuilder()
	public int getNombreToursBuilder();

	// \pre isBuilder()
	public int getNombreDallesPosees();
	
	// \pre getType() == BASHER
	public int nbCreuseTunnel();


	/** Constructeur **/
	// \post getDirection() = DROITIER
	// \post getType() = TOMBEUR
	// \post getId() = id
	// \post isGrimpeur() = false
	// \post isExploseur() = false
	// \post isFlotteur() = false
	// \post tombeDepuis() = 0
	// \post isBuilder() = false
	// \post isCurrentlyBuilding() = false
	// \post isCurrentlyClimbing() = false
	// \post nbCreuseTunnel() = 0
	// \post getNombreToursBuilder() = 0
	// \post getNombreDallesPosees() = 0
	public void init(int id);

	/** Operateurs **/
	// \pre getType() != TOMBEUR
	// \post getType() = TOMBEUR
	public void devientTombeur();

	// \pre getType() != MARCHEUR
	// \post getType() = MARCHEUR
	// \post tombeDepuis() = 0
	public void devientMarcheur();

	// \pre getDirection() != GAUCHER
	// \post getDirection() = GAUCHER
	public void devientGaucher();

	// \pre getDirection() != DROITIER
	// \post getDirection() = DROITIER
	public void devientDroitier();

	// \pre getType() != CREUSEUR
	// \post getType() = CREUSEUR
	public void devientCreuseur();

	// \pre isGrimpeur()=false
	// \pre getType()=MARCHEUR OR getType()=TOMBEUR OR getType()=CREUSEUR 
	// \post isGrimpeur()=true
	public void devientGrimpeur();

	// \pre isFlotteur()=false
	// \post isFlotteur()=true
	public void devientFlotteur();

	// \pre isBuilder() = false
	// \post isBuilder() = true
	public void devientBuilder();
	
	// \pre getType() != MINER
	// \post getType() = MINER
	public void devientMiner();
	
	// \pre getType() != BASHER
	// \post getType() = BASHER
	// \post nbCreuseTunnel() = 0
	public void devientBasher();

	// \pre isExploseur() = false;
	// \post isExploseur = false;
	public void devientExploseur();
	
	// \pre getType() != TOMBEUR && getType() != STOPPEUR;
	// \post getType() = STOPPEUR;
	public void devientStoppeur();

	// TODO revoir les preconditon des devient
	// TODO le stoppeur peut aussi �tre basher, creuseur, grimpeur etc.......
	// \pre !getGameEng().gameOver()
	// \pre getId() \in getGameEng().getLemmingActifs()

	/* ************ BASHEUR ***************
	 * \post getType()@pre = BASHER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 		\implies getType() = TOMBEUR AND getX() = getX()@pre
	 * 								AND getY() = getY()@pre
	 * 								AND getDirection()=getDirection()@pre
	 * 
	 * ******** BASHEUR DROITIER ***********
	 * \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == DROITIER
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) != METAL
	 *  							AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) != METAL
	 * 	 							AND !getGameEng().isObstacle(getX()@pre + 1, getY()@pre)
	 * 								AND !getGameEng().isObstacle(getX()@pre + 1, getY()@pre-1)
	 *  							AND !getGameEng().isObstacle(getX()@pre + 1, getY()@pre-2)
	 *  							AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL
	 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
	 *     									AND getX() = getX()@pre + 1 AND getY() = getY()@pre
	 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)
	 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
	 *         								AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)
	 *         								AND nbCreuseTunnel() = nbCreuseTunnel()@pre+1    	
	 * ******** BASHEUR DROITIER S'ARRETE ***********        
	 * \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == DROITIER
	 * 								AND (getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) = METAL
	 * 									OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-1) = METAL
	 *  								OR getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre-2) = METAL
	 *									OR getGameEng().isObstacle(getX()@pre + 1, getY()@pre)
	 * 									OR getGameEng().isObstacle(getX()@pre + 1, getY()@pre-1)
	 *  								OR getGameEng().isObstacle(getX()@pre + 1, getY()@pre-2)
	 *  								OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)
	 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 *     								 AND getDirection() = getDirection()@pre	
	 *  
	 *     		
	 * ********* BASHEUR GAUCHER ************
	 * \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == GAUCHER
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) != METAL
	 *  							AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) != METAL
	 *  	 						AND !getGameEng().isObstacle(getX()@pre - 1, getY()@pre)
	 * 								AND !getGameEng().isObstacle(getX()@pre - 1, getY()@pre-1)
	 *  							AND !getGameEng().isObstacle(getX()@pre - 1, getY()@pre-2)							
	 *  							AND nbCreuseTunnel() < MAX_CREUSE_TUNNEL
	 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
	 *     									AND getX() = getX()@pre - 1 AND getY() = getY()@pre
	 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
	 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
	 *         								AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)

	 *         								AND nbCreuseTunnel() = nbCreuseTunnel()@pre+1    	
	 * ******** BASHEUR GAUCHER S'ARRETE ***********         
	 * \post getType()@pre = BASHER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == GAUCHER
	 * 								AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL
	 * 									OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-1) = METAL
	 *  								OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre-2) = METAL
	 *    	 							OR getGameEng().isObstacle(getX()@pre - 1, getY()@pre)
	 * 									OR getGameEng().isObstacle(getX()@pre - 1, getY()@pre-1)
	 *  								OR getGameEng().isObstacle(getX()@pre - 1, getY()@pre-2)
	 *  								OR nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)
	 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 *     								 AND getDirection() = getDirection()@pre	
	 *	
	 * ************ FIN BASHEUR *************
	 * 
	 * ************ MINER ***************
	 * \post getType()@pre = MINER AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 		\implies getType() = TOMBEUR AND getX() = getX()@pre
	 * 								AND getY() = getY()@pre
	 * 								AND getDirection()=getDirection()@pre
	 * 
	 * ******** MINER DROITIER DESCEND ***********
	 * \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == DROITIER
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 1, getY()@pre+1) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre + 2, getY()@pre+1) != METAL
	 *  							AND getGameEng().getLevel().getNature(getX()@pre + 3, getY()@pre+1) != METAL
	 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
	 *     									AND getX() = getX()@pre + 1 AND getY() = getY()@pre+1
	 *     									AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre+1)
	 *     									AND !getGameEng().isObstacle(getX()@pre+2, getY()@pre+1)
	 *         								AND !getGameEng().isObstacle(getX()@pre+3, getY()@pre+1)
	 * *************** MINER DROITIER S'ARRETE **************
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
	 *     		
	 * ********* MINER GAUCHER DESCEND ************
	 * \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == GAUCHER
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) != METAL
	 * 								AND getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) != METAL
	 *  							AND getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) != METAL
	 *     \implies getType() = getType@pre AND getDirection() = getDirection()@pre
	 *     									AND getX() = getX()@pre - 1 AND getY() = getY()@pre+1
	 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
	 *     									AND !getGameEng().isObstacle(getX()@pre-2, getY()@pre)
	 *     									AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre+1)
	 *     									AND !getGameEng().isObstacle(getX()@pre-2, getY()@pre+1)
	 *         								AND !getGameEng().isObstacle(getX()@pre-3, getY()@pre+1)
	 * ********* MINER DROITIER S'ARRETE **********
	 * \post getType()@pre = MINER AND getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								AND getDirection() == GAUCHER
	 * 								AND (getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre+1) = METAL
	 * 									OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre+1) = METAL
	 *  								OR getGameEng().getLevel().getNature(getX()@pre - 3, getY()@pre+1) = METAL
	 *  								OR getGameEng().getLevel().getNature(getX()@pre - 1, getY()@pre) = METAL
	 *  								OR getGameEng().getLevel().getNature(getX()@pre - 2, getY()@pre) = METAL)
	 *     \implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 *     								 AND getDirection() = getDirection()@pre	
	 *	
	 * ************ FIN MINER *************
	 * 
	 * 
	 * ********************** STOPPEUR ***********************
	 * \post getType() = STOPPEUR 
	 * 			\implies getX() = getX()@pre AND getY() = getY()@pre
	 * 					AND getGameEng().isObstacle(getX(), getY())
	 * 					AND getGameEng().isObstacle(getX(), getY()-1)  
	 * 
	/* ********************** EXPLOSEUR **********************
	 * \post isExploseur()@pre AND exploseurDepuis@pre = 4 
	 * 			\implies getGameEng().getNombreActifs() = getGameEng().getNombreActifs()@pre-1 
	 * 				AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre-1
	 * 				AND \forall i \in (i>getX()@pre-2 AND i<getX()@pre+3) AND \forall j \in (j>getY()@pre-2 && j<getY()@pre+2)
	 * 					AND (!(i=getX()@pre-2 AND j=getY()@pre-2) 
										AND !(i=getX()@pre+2 AND j=getY()@pre-2) 
										AND !(i=getX()@pre-2 AND j=getY()@pre+1)  
										AND !(i=getX()@pre+2 AND j=getY()@pre+1))
	 * 	   				getGameEng().level(i,j)@pre = DIRT \implies getGameEng().level(i,j) = EMPTY 
	 * 
	 * 	 \post isExploseur()@pre AND exploseurDepuis@pre != 4
	 * 		\implies exploseurDepuis() = exploseurDepuisPre+1
	 *	
	 * ********************** MARCHEUR **********************
	 * 
	 * \post getType()@pre = MARCHEUR AND !getGameEng().isObstacle(getX(), getY()+1) AND !isGrimpeur()
	 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * *************** DROITIER ***************
	 * *********** GRIMPEUR ***********
	 * \post getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 												   AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre)
	 *  											   AND getDirection()@pre = DROITIER
	 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre
	 * 														   AND getY() = getY()@pre
	 * 														   AND getDirection()=getDirection()@pre	
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
	 * 								  AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
	 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)   
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre-1
	 * 													AND getDirection()=getDirection()@pre
	 * 	
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre+1, getY()@pre)
	 * 								  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-1)
	 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND !getGameEng().isObstacle(getX()@pre+1, getY()@pre-2)   
	 * 						\implies  getX()=getX()@pre+1 AND getY()=getY()@pre-1
	 * 													  AND getDirection()=getDirection()@pre
	 *  
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1)
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
	 * 													AND getDirection()=GAUCHER
	 * 													AND getType()=TOMBEUR
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)
	 * 								  AND getGameEng().isObstacle(getX()@pre+1,getY()@pre)
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
	 * 													AND getDirection()=GAUCHER
	 * 													AND getType()=getType()@pre
	 * *********** FIN GRIMPEUR ***********
	 * 
	 * *********** DEBUT BUILDER *************
	 *  ******** BUILDER DROITIER START ********
	 *  	
	 *  \post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true
	 *  	AND getNombreToursBuilder()@pre < 2
	 *  	\implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1
	 * 					AND getX() = getX()@pre
	 * 					AND getY() = getY()@pre	
	 * 					AND getDirection() = getDirection()@pre
	 *  
	 *  ****** BUILDER DROITIER OK ********
	 * \post isBuilder() AND getDirection() == DROITIER AND isCurrentlyBuilding() = true 
	 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
	 * 			AND getGameEng().isLibre(getX()@pre+1, getY()@pre)
	 * 			AND getGameEng().isLibre(getX()@pre+2 getY()@pre)
	 * 			AND getGameEng().isLibre(getX()@pre+3, getY()@pre)
	 * 			AND !getGameEng().isObstacle2(getX()@pre+1, getY()@pre-2)
	 * 			AND !getGameEng().isObstacle2(getX()@pre+2, getY()@pre-2)
	 * 			AND !getGameEng().isObstacle2(getX()@pre+3, getY()@pre-2)
	 * 			AND getNombreDallesPosees()@pre < 12
	 * 		 \implies getGameEng().getLevel().getNature(getX()+1, getY()) = DIRT
	 * 				  	AND getGameEng().getLevel().getNature(getX()+2, getY()) = DIRT
	 * 					AND getGameEng().getLevel().getNature(getX()+3, getY()) = DIRT
	 * 					AND getNombreDallesPosees() = getNombreDallesPosees()@pre + 1
	 * 					AND getX() = getX()@pre + 2
	 * 					AND getY() = getY()@pre - 1	
	 * 					AND getDirection() = getDirection()@pre
	 * 					AND getNombreToursBuilder() = 0;
	 *  
	 * ******* BUILDER DROITIER S'ARRETE ********
	 *  \post isBuilder() = true AND getDirection() == DROITIER AND isCurrentlyBuilding() = true 
	 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
	 * 	 		AND (getGameEng().isLibre(getX()@pre+1, getY()@pre)
	 * 				OR getGameEng().isLibre(getX()@pre+2 getY()@pre)
	 * 				OR getGameEng().isLibre(getX()@pre+3, getY()@pre)
	 * 				OR getGameEng().isObstacle2(getX()@pre+1, getY()@pre-2)
	 * 				OR getGameEng().isObstacle2(getX()@pre+2, getY()@pre-2)
	 * 				OR getGameEng().isObstacle2(getX()@pre+3, getY()@pre-2)
	 * 				OR getNombreDallesPosees()@pre >= 12)
	 * 		 \implies	AND getNombreDallesPosees() = getNombreDallesPosees()@pre
	 * 					AND getX() = getX()@pre
	 * 					AND getY() = getY()@pre	
	 * 					AND getDirection() = getDirection()@pre
	 * 					AND getNombreToursBuilder() = 0;
	 * 					AND isCurrentlyBuilding() = false
	 * *********** FIN BUILDER *************
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  \implies getDirection() = GAUCHER 
	 * 										AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirecton()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND getGameEng().isObstacle(getX()+1, getY()-2)
	 * 							  \implies getDirection() = GAUCHER
	 * 										AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-2)
	 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
	 * 
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre AND getType() = MARCHEUR
	 * 
	 * *************** GAUCHER ***************
	 * *********** GRIMPEUR ***********
	 * \post getType()@pre = MARCHEUR AND isGrimpeur() AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 												   AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre)
	 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre
	 * 														   AND getY() = getY()@pre
	 * 														   AND getDirection()=getDirection()@pre	
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
	 * 								  AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
	 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)   
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre-1
	 * 													AND getDirection()=getDirection()@pre
	 * 	
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre-1, getY()@pre)
	 * 								  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-1)
	 * 								  AND !getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND !getGameEng().isObstacle(getX()@pre-1, getY()@pre-2)   
	 * 						\implies  getX()=getX()@pre-1 AND getY()=getY()@pre-1
	 * 													  AND getDirection()=getDirection()@pre
	 *  
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND !getGameEng().isObstacle(getX()@pre,getY()@pre+1)
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
	 * 													AND getDirection()=DROITIER
	 * 													AND getType()=TOMBEUR
	 * 
	  \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 								  AND isGrimpeur()
	 * 							      AND getGameEng().isObstacle(getX()@pre, getY()@pre-2)
	 * 								  AND getGameEng().isObstacle(getX()@pre,getY()@pre+1)
	 * 								  AND getGameEng().isObstacle(getX()@pre-1,getY()@pre)
	 * 						\implies  getX()=getX()@pre AND getY()=getY()@pre
	 * 													AND getDirection()=DROITIER
	 * 													AND getType()=getType()@pre
	 * 
	 * *********** FIN GRIMPEUR ***********
	 * *********** DEBUT BUILDER *************
	 *  ******** BUILDER GAUCHER START ********
	 *  	
	 *  \post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true
	 *  	AND getNombreToursBuilder()@pre < INTERVALLE_POSE_DALLE
	 *  	\implies getNombreToursBuilder() = getNombreToursBuilder()@pre + 1
	 * 					AND getX() = getX()@pre
	 * 					AND getY() = getY()@pre	
	 * 					AND getDirection() = getDirection()@pre
	 *  
	 *  
	 *  ****** BUILDER GAUCHER OK ********
	 * \post isBuilder() AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true 
	 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
	 * 			AND getGameEng().isLibre(getX()@pre-1, getY()@pre)
	 * 			AND getGameEng().isLibre(getX()@pre-2 getY()@pre)
	 * 			AND getGameEng().isLibre(getX()@pre-3, getY()@pre)
	 * 			AND !getGameEng().isObstacle2(getX()@pre-1, getY()@pre-2)
	 * 			AND !getGameEng().isObstacle2(getX()@pre-2, getY()@pre-2)
	 * 			AND !getGameEng().isObstacle2(getX()@pre-3, getY()@pre-2)
	 * 			AND getNombreDallesPosees()@pre < 12
	 * 		 \implies getGameEng().getLevel().getNature(getX()-1, getY()) = DIRT
	 * 				  	AND getGameEng().getLevel().getNature(getX()-2, getY()) = DIRT
	 * 					AND getGameEng().getLevel().getNature(getX()-3, getY()) = DIRT
	 * 					AND getNombreDallesPosees() = getNombreDallesPosees()@pre + 1
	 * 					AND getX() = getX()@pre - 2
	 * 					AND getY() = getY()@pre - 1	
	 * 					AND getDirection() = getDirection()@pre
	 * 					AND getNombreToursBuilder() = 0;
	 *  
	 * ******* BUILDER GAUCHER S'ARRETE ********
	 *  \post isBuilder() = true AND getDirection() == GAUCHER AND isCurrentlyBuilding() = true 
	 * 			AND getNombreToursBuilder()@pre = INTERVALLE_POSE_DALLE
	 * 	 		AND ( !getGameEng().isLibre(getX()@pre-1, getY()@pre)
	 * 				OR !getGameEng().isLibre(getX()@pre-2 getY()@pre)
	 * 				OR !getGameEng().isLibre(getX()@pre-3, getY()@pre)
	 * 	 			OR getGameEng().isObstacle2(getX()@pre-1, getY()@pre-2)
	 * 				OR getGameEng().isObstacle2(getX()@pre-2, getY()@pre-2)
	 * 				OR getGameEng().isObstacle2(getX()@pre-3, getY()@pre-2)
	 * 				OR getNombreDallesPosees()@pre >= 12)
	 * 		 \implies   AND getNombreDallesPosees() = getNombreDallesPosees()@pre
	 * 					AND getX() = getX()@pre
	 * 					AND getY() = getY()@pre	
	 * 					AND getDirection() = getDirection()@pre
	 * 					AND getNombreToursBuilder() = 0;
	 * 					AND isCurrentlyBuilding() = false
	 * *********** FIN BUILDER *************
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirecton()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND getGameEng().isObstacle(getX()-1, getY()-2)
	 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-2)
	 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre AND getType() = MARCHEUR
	 * 
	 * ******** STATUT TOMBEUR ********
	 *   *** CAS FLOTTEUR ***
	 *   
	 * 	 \post getType()@pre = TOMBEUR AND !getGameEng().isObstacle(getX()@pre, getY()@pre+1)
	 * 								   AND isFlotteur()
	 * 							\implies 	getType()=getType()@pre
	 * 										AND getX()= getX()@pre
	 * 										AND tombeDepuis()=tombeDepuis()@pre +1 
	 * 										AND getDirection()=getDirection()@pre
	 * 										AND if tombeDepuis()@pre%2==0 then
	 * 												getY()=getY()@pre +1
	 * 											else
	 * 												getY()=getY()@pre
	 *   \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()@pre+1)
	 * 								   AND isFlotteur()
	 * 							\implies 	getType()=MARCHEUR
	 * 										AND getX()= getX()@pre
	 * 										AND getY()= getY()@pre
	 * 										AND getDirection()=getDirection()@pre
	 * 									
	 * 
	 *  ******* FIN FLOTTEUR *******
	 *   
	 * \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis() < 8
	 * 						\implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre+1
	 * 								 AND tombeDepuis() = 0
	 * 
	 * \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis >= 8
	 * 							 \implies le lemming meurt : getId() n'appartient pas a getGameEng().getLemmingsActif()
	 * 									AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre + 1
	 * 
	 * \post getType()@pre = TOMBEUR AND !getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis() < 8
	 * 							 \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre + 1
	 * 									AND tombeDepuis() = tombeDepuis()@pre+1
	 * 
	 * ********************** EXIT **********************
	 * 
	 * \post if getGameEng().getLevel().isExit(getX(), getY()) == true 
	 * 			\implies le lemming est sauve : getId() n'appartient pas a getGameEng().getLemmingsActif()
	 * 					AND getGameEng().getNombreSauves() = getGameEng().getNombreSauves()@pre + 1
	 * 
	 * ********************** CREUSEUR **********************
	 * \post getType()@pre = CREUSEUR AND !getGameEng().isObstacle(getX(), getY()+1)
	 * 			\implies getType() = TOMBEUR AND getDirection()@pre = getDirection()
	 * 					AND getX() = getX()@pre AND getY() = getY()@pre 
	 * 
	 * \post getType()@pre = CREUSEUR AND getGameEng().getLevel().getNature(getX(), getY()+1) = METAL
	 * 			\implies getType() = MARCHEUR AND getDirection()@pre = getDirection()
	 * 					AND getX() = getX()@pre AND getY() = getY()@pre
	 *
	 * \post getType()@pre = CREUSEUR AND getGameEng().getLevel().getNature(getX(), getY()+1) = DIRT
	 * 			\implies getType() = CREUSEUR AND getDirection()@pre = getDirection() 
	 * 				AND !getGameEng().isObstacle(getX(), getY()+1) AND 
	 * 				getX() = getX()@pre AND getY() = getY()@pre + 1 AND
	 * 				if getGameEng().getLevel().getNature(getX()-1, getY()+1) = DIRT
	 * 				then !getGameEng().isObstacle(getX()-1, getY()+1)
	 * 				else if getGameEng().getLevel().getNature(getX()+1, getY()+1) = DIRT
	 * 				then !getGameEng().isObstacle(getX()+1, getY()+1)
	 */

	public void step();

	/** Invariants **/
	// \inv 1 <= getX() <= getGameEng().getLevel().getWidth()-1 
	// \inv 1 <= getY() <= getGameEng().getLevel().getHeight()-1
	// \inv getId() > 0
	// \inv getType() = TOMBEUR \implies tombeDepuis() >= 0
	// \inv getType() = MARCHEUR \implies tombeDepuis() = 0

}
