package model.lemmings.impl;

import java.awt.Point;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.RequireGameEngService;
import model.lemmings.services.Level.Nature;

public class LemmingImplBug implements Lemming{

	private Type type;
	private Direction direction;
	private int id;
	private int x;
	private int y;
	private int tombeDepuis;
	private int nombreToursBuilder;
	private int nombreDallesPosees;
	private int nbCreuseTunnel;
	private int exploseurDepuis;
	private boolean isGrimpeur;
	private boolean isFlotteur;
	private boolean isBuilder;
	private boolean isCurrentlyBuilding;
	private boolean isCurrentlyClimbing;
	private boolean isExploseur;

	private GameEng gameEng;

	public LemmingImplBug(){
		super();
	}

	@Override
	public void bindGameEngService(GameEng service) {
		this.gameEng = service;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Type getType() {
		return Type.MINER;
	}

	@Override
	public boolean isGrimpeur() {
		return !isGrimpeur;
	}

	@Override
	public boolean isExploseur(){
		return !isExploseur;
	}

	@Override
	public int getX() {
		return x+1;
	}

	@Override
	public int getY() {
		return y-1;
	}

	@Override
	public int tombeDepuis() {
		return tombeDepuis+1;
	}

	@Override
	public int exploseurDepuis(){
		return exploseurDepuis+1;
	}

	@Override
	public boolean isFlotteur() {
		return !isFlotteur;
	}

	@Override
	public boolean isBuilder() {
		return !isBuilder;
	}

	@Override
	public boolean isCurrentlyBuilding() {
		return isCurrentlyBuilding;
	}

	@Override
	public boolean isCurrentlyClimbing() {
		return isCurrentlyClimbing;
	}


	@Override
	public int getNombreToursBuilder() {
		return nombreToursBuilder-1;
	}

	@Override
	public int getNombreDallesPosees() {
		return nombreDallesPosees-1;
	}

	@Override
	public int nbCreuseTunnel() {
		return nbCreuseTunnel+1;	
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public GameEng getGameEng(){
		return gameEng;
	}

	@Override
	public void init(int id) {
		this.id = id;
		type = Type.TOMBEUR;
		direction = Direction.DROITIER;
		Point entrance = gameEng.getLevel().getEntrance();
		x = entrance.x+1;
		y = entrance.y-1;
		isGrimpeur = true;
		isFlotteur = true;
		isBuilder = true;
		isCurrentlyBuilding = false;
		isCurrentlyClimbing = false;
		nombreToursBuilder = 0;
		isExploseur = false;
		tombeDepuis = 0;
		exploseurDepuis = 0;
		nbCreuseTunnel = 0;
		nombreDallesPosees = 0;
	}

	@Override
	public void devientTombeur() {
		type = Type.MARCHEUR;
		tombeDepuis = 1;
	}

	@Override
	public void devientMarcheur() {
		type = Type.TOMBEUR;
		tombeDepuis = 0;
	}

	@Override
	public void devientGaucher() {
		direction = Direction.DROITIER;
	}

	@Override
	public void devientDroitier() {
		direction = Direction.GAUCHER;
	}

	@Override
	public void devientCreuseur() {
		type = Type.MINER;
	}

	@Override
	public void devientGrimpeur(){
		isGrimpeur = !true;
	}

	@Override
	public void devientFlotteur() {
		isFlotteur = !true;		
	}

	@Override
	public void devientExploseur(){
		isExploseur = true;
	}

	@Override
	public void devientBuilder() {
		isBuilder = !true;
		isCurrentlyBuilding = true;
		nombreDallesPosees = 1;
	}

	@Override
	public void devientBasher() {
		this.type = Type.BASHER;
		nbCreuseTunnel = 0;
	}

	@Override
	public void devientMiner() {
		this.type = Type.MINER;
	}

	@Override
	public void devientStoppeur(){
		this.type = Type.STOPPEUR;
	}

	@Override
	public void step() {

		final Type typePre = getType();
		final Direction directionPre = getDirection();
		isCurrentlyClimbing = false;
		if(isExploseur){
			exploseurDepuis++; 
			if(exploseurDepuis == 5){
				/* explosion */
				for(int i=getX()-2; i<getX()+3; i++){
					for(int j=getY()-2; j<getY()+2; j++){
						if((i>=0 && i<getGameEng().getLevel().getWidth()) 
								&& (j>=0 && j<getGameEng().getLevel().getHeight())
								&& (!(i==getX()-2 && j==getY()-2) 
										&& !(i==getX()+2 && j==getY()-2) 
										&& !(i==getX()-2 && j==getY()+1)  
										&& !(i==getX()+2 && j==getY()+1))
								&& getGameEng().getLevel().getNature(i, j) == Nature.DIRT){
							getGameEng().getLevel().remove(i, j);
						}
					}	
				}
				/* faire mourir le lemming */
				gameEng.tuerLemming(getId());
				return;
			}
		}

		switch(typePre){
		case BASHER:
			if(!getGameEng().isObstacle(x,y+1)) {
				devientTombeur();
			}
			/** BASHEUR DROITIER CREUSE **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.DROITIER
					&& getGameEng().getLevel().getNature(x+1, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+1, y-1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+1, y-2) != Nature.METAL
					&& !getGameEng().isObstacle2(x+1, y)
					&& !getGameEng().isObstacle2(x+1, y-1)
					&& !getGameEng().isObstacle2(x+1, y-2)
					&& nbCreuseTunnel() < MAX_CREUSE_TUNNEL) {
				if (getGameEng().getLevel().getNature(x+1, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+1, y);
				if (getGameEng().getLevel().getNature(x+1, y-1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+1, y-1);
				if (getGameEng().getLevel().getNature(x+1, y-2) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+1, y-2);
				nbCreuseTunnel++;
				x = x + 1;
			}
			/** BASHEUR DROITIER S"ARRETE DE CREUSER **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.DROITIER
					&& (getGameEng().getLevel().getNature(x+1, y) == Nature.METAL
					|| getGameEng().getLevel().getNature(x+1, y-1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x+1, y-2) == Nature.METAL
					|| getGameEng().isObstacle2(x+1, y)
					|| getGameEng().isObstacle2(x+1, y-1)
					|| getGameEng().isObstacle2(x+1, y-2)
					|| nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)) {
				nbCreuseTunnel = 0;
				devientMarcheur();
			}
			/** BASHEUR GAUCHER CREUSE **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.GAUCHER
					&& getGameEng().getLevel().getNature(x-1, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-1, y-1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-1, y-2) != Nature.METAL
					&& !getGameEng().isObstacle2(x-1, y)
					&& !getGameEng().isObstacle2(x-1, y-1)
					&& !getGameEng().isObstacle2(x-1, y-2)
					&& nbCreuseTunnel() < MAX_CREUSE_TUNNEL) {
				if (getGameEng().getLevel().getNature(x-1, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-1, y);
				if (getGameEng().getLevel().getNature(x-1, y-1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-1, y-1);
				if (getGameEng().getLevel().getNature(x-1, y-2) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-1, y-2);
				nbCreuseTunnel++;
				x = x - 1;
			}
			/** BASHEUR GAUCHER S"ARRETE DE CREUSER **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.GAUCHER
					&& (getGameEng().getLevel().getNature(x-1, y) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-1, y-1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-1, y-2) == Nature.METAL
					|| getGameEng().isObstacle2(x-1, y)
					|| getGameEng().isObstacle2(x-1, y-1)
					|| getGameEng().isObstacle2(x-1, y-2)
					|| nbCreuseTunnel() >= MAX_CREUSE_TUNNEL)) {
				nbCreuseTunnel = 0;
				devientMarcheur();
			}
			break;
		case MINER:
			if(!getGameEng().isObstacle(x,y+1)) {
				devientTombeur();
			}
			/** MINER DROITIER CREUSE EN BAS**/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.DROITIER
					&& getGameEng().getLevel().getNature(x+1, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+2, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+1, y+1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+2, y+1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x+3, y+1) != Nature.METAL) {
				if (getGameEng().getLevel().getNature(x+1, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+1, y);
				if (getGameEng().getLevel().getNature(x+2, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+2, y);
				if (getGameEng().getLevel().getNature(x+1, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+1, y+1);
				if (getGameEng().getLevel().getNature(x+2, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+2, y+1);
				if (getGameEng().getLevel().getNature(x+3, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x+3, y+1);
				x = x + 1;
				y = y + 1;
			}
			/** MINER DROITIER S"ARRETE DE CREUSER **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.DROITIER
					&& (getGameEng().getLevel().getNature(x+1, y+1) != Nature.METAL
					|| getGameEng().getLevel().getNature(x+2, y+1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x+3, y+1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x+1, y) == Nature.METAL
					|| getGameEng().getLevel().getNature(x+2, y) == Nature.METAL)) {
				devientMarcheur();
			}
			/** MINER GAUCHER CREUSE EN BAS **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.GAUCHER
					&& getGameEng().getLevel().getNature(x-1, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-2, y) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-1, y+1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-2, y+1) != Nature.METAL
					&& getGameEng().getLevel().getNature(x-3, y+1) != Nature.METAL) {
				if (getGameEng().getLevel().getNature(x-1, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-1, y);
				if (getGameEng().getLevel().getNature(x-2, y) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-2, y);
				if (getGameEng().getLevel().getNature(x-1, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-1, y+1);
				if (getGameEng().getLevel().getNature(x-2, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-2, y+1);
				if (getGameEng().getLevel().getNature(x-3, y+1) != Nature.EMPTY)
					getGameEng().getLevel().remove(x-3, y+1);
				x = x - 1;
				y = y + 1;
			}
			/** MINER GAUCHER S"ARRETE DE CREUSER **/
			else if (getGameEng().isObstacle(x, y+1)
					&& getDirection() == Direction.GAUCHER
					&& (getGameEng().getLevel().getNature(x-1, y+1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-2, y+1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-3, y+1) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-1, y) == Nature.METAL
					|| getGameEng().getLevel().getNature(x-2, y) == Nature.METAL)) {
				devientMarcheur();
			}
			break;
		case MARCHEUR:
			/* MARCHEUR */
			if(!gameEng.isObstacle(x, y+1) && !isGrimpeur()){
				devientTombeur();
				break;
			}

			switch(directionPre){
			case DROITIER:

				/***** GRIMPEUR DEBUT *****/
				//Grimpeur devient tombeur s'il n'y a rien en dessous de lui ni à sa droite 
				if(isGrimpeur() 
						&& !gameEng.isObstacle(x, y+1) 
						&& !gameEng.isObstacle(x+1, y)){
					devientTombeur();
					break;
				}
				//Grimpeur grimpe
				else if(isGrimpeur()
						&& gameEng.isObstacle(x+1, y-1) 
						&& gameEng.isObstacle(x+1, y) 
						&& !gameEng.isObstacle(x, y-2)){
					y=y-1;
					isCurrentlyClimbing = true;
				}
				//Grimpeur passe au dessus de l'obstacle
				else if(isGrimpeur() 
						&& !gameEng.isObstacle(x+1, y-1) 
						&& gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x, y-2)
						&& !gameEng.isObstacle(x+1, y-2)){
					y=y-1;
					x=x+1;
				}
				// Si le grimpeur ne peut plus grimper, il tombe et devient gaucher pour ne pas à avoir remonter
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2) 
						&& !gameEng.isObstacle(x, y+1)){
					devientGaucher();
					devientTombeur();
				}
				//Si le grimpeur est coincé, devient gaucher
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& gameEng.isObstacle(x, y+1) 
						&& gameEng.isObstacle(x+1, y)){
					devientGaucher();
				}

				/********* GRIMPEUR FIN **********/

				/***** BUILDER DROITIER ******/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() < INTERVALLE_POSE_DALLE) {
					nombreToursBuilder++;
				}
				/** POSE DALLE **/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() == INTERVALLE_POSE_DALLE
						&& getNombreDallesPosees() < MAX_DALLES
						&& getGameEng().isLibre(getX()+1, getY())
						&& getGameEng().isLibre(getX()+2, getY())
						&& getGameEng().isLibre(getX()+3, getY())
						&& !getGameEng().isObstacle(getX()+1, getY()-2)
						&& !getGameEng().isObstacle(getX()+2, getY()-2)
						&& !getGameEng().isObstacle(getX()+3, getY()-2)) {
					getGameEng().getLevel().build(getX()+1, getY());
					getGameEng().getLevel().build(getX()+2, getY());
					getGameEng().getLevel().build(getX()+3, getY());
					nombreDallesPosees++;
					nombreToursBuilder = 0;
					x = x + 2;
					y = y - 1;
				}
				/** BUILDER S'ARRETE **/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() == INTERVALLE_POSE_DALLE && (
						!getGameEng().isLibre(getX()+1, getY())
						|| !getGameEng().isLibre(getX()+2, getY())
						|| !getGameEng().isLibre(getX()+3, getY())
						|| getGameEng().isObstacle(getX()+1, getY()-2)
						|| getGameEng().isObstacle(getX()+2, getY()-2)
						|| getGameEng().isObstacle(getX()+3, getY()-2)
						|| getNombreDallesPosees() >= MAX_DALLES)) {
					isCurrentlyBuilding = false;
					nombreToursBuilder = 0;
				}
				/***** FIN BUILDER ******/

				else if(gameEng.isObstacle(x+1, y-1)) {
					devientGaucher();
				}

				else if(gameEng.isObstacle(x+1, y) 
						&& gameEng.isObstacle(x+1, y-2))
					devientGaucher();

				else if(gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x+1, y-1)
						&& !gameEng.isObstacle(x+1, y-2)){
					x = x+1;
					y = y-1;
					//devientMarcheur();
				}

				else if(!gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x+1, y-1))
					x = x+1;
				break;

			case GAUCHER:

				/********** GRIMPEUR DEBUT **********/
				if(!gameEng.isObstacle(x, y+1) 
						&& isGrimpeur 
						&& !gameEng.isObstacle(x-1, y)){
					devientTombeur();
					break;
				}
				else if(isGrimpeur() 
						&& gameEng.isObstacle(x-1, y-1) 
						&& gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x, y-2)){
					isCurrentlyClimbing = true;
					y=y-1;
				}
				else if(isGrimpeur()
						&& !gameEng.isObstacle(x-1, y-1)
						&& gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-2)){
					isCurrentlyClimbing = true;
					y=y-1;
					x=x-1;
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& !gameEng.isObstacle(x, y+1)){
					devientDroitier();
					devientTombeur();
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& gameEng.isObstacle(x, y+1)
						&& gameEng.isObstacle(x-1, y)){
					devientDroitier();
				}

				/********** GRIMPEUR FIN **********/

				/***** BUILDER GAUCHER ******/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() < INTERVALLE_POSE_DALLE) {
					nombreToursBuilder++;
				}
				/** POSE DALLE **/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() == INTERVALLE_POSE_DALLE
						&& getNombreDallesPosees() < MAX_DALLES
						&& getGameEng().isLibre(getX()-1, getY())
						&& getGameEng().isLibre(getX()-2, getY())
						&& getGameEng().isLibre(getX()-3, getY())
						&& !getGameEng().isObstacle(getX()-1, getY()-2)
						&& !getGameEng().isObstacle(getX()-2, getY()-2)
						&& !getGameEng().isObstacle(getX()-3, getY()-2)) {
					getGameEng().getLevel().build(getX()-1, getY());
					getGameEng().getLevel().build(getX()-2, getY());
					getGameEng().getLevel().build(getX()-3, getY());
					nombreDallesPosees++;
					nombreToursBuilder = 0;
					x = x - 2;
					y = y - 1;
				}
				/** BUILDER S'ARRETE **/
				else if (isBuilder() && isCurrentlyBuilding() && getNombreToursBuilder() == INTERVALLE_POSE_DALLE && (
						!getGameEng().isLibre(getX()-1, getY())
						|| !getGameEng().isLibre(getX()-2, getY())
						|| !getGameEng().isLibre(getX()-3, getY())
						|| getGameEng().isObstacle(getX()-1, getY()-2)
						|| getGameEng().isObstacle(getX()-2, getY()-2)
						|| getGameEng().isObstacle(getX()-3, getY()-2)
						|| getNombreDallesPosees() >= MAX_DALLES)) {
					isCurrentlyBuilding = false;
					nombreToursBuilder = 0;
				}
				else if(gameEng.isObstacle(x-1, y-1)) {
					devientDroitier();
				}

				else if(gameEng.isObstacle(x-1, y)
						&& gameEng.isObstacle(x-1, y-2))
					devientDroitier();

				else if(gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-1)
						&& !gameEng.isObstacle(x-1, y-2)){
					x = x-1;
					y = y-1;
				}

				else if(!gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-1)) {
					x = x-1;
				}
				break;
			}
			break;
		case TOMBEUR:

			/* FLOTTEUR */
			if(!gameEng.isObstacle(x,y+1) && isFlotteur()){
				if(tombeDepuis%2==0)
					y++;
				tombeDepuis++;
			}
			else if(gameEng.isObstacle(x,y+1) && isFlotteur()){ 
				devientMarcheur();
			}

			/* TOMBEUR NORMAL*/
			else if(gameEng.isObstacle(x, y+1)
					&& tombeDepuis < 8){
				devientMarcheur();
				tombeDepuis = 0;
			}

			else if((gameEng.isObstacle(x, y+1) || gameEng.getLevel().isExit(x, y+1))
					&& tombeDepuis >= 8) {
				gameEng.tuerLemming(id);
			}

			else if(!gameEng.isObstacle(x, y+1)	){
				y = y+1;
				tombeDepuis = tombeDepuis+1;
			}
			break;
		case CREUSEUR:
			/* CREUSEUR */
			/* Cas 1 */
			if (!gameEng.isObstacle(x, y+1)) {
				devientTombeur();
			}
			else if (gameEng.getLevel().getNature(x, y+1) == Nature.METAL) {
				devientMarcheur();
			}
			else if (gameEng.getLevel().getNature(x, y+1) == Nature.DIRT) {
				gameEng.getLevel().remove(x, y+1);
				if (gameEng.getLevel().getNature(x-1, y+1) == Nature.DIRT) {
					gameEng.getLevel().remove(x-1, y+1);
				}
				if (gameEng.getLevel().getNature(x+1, y+1) == Nature.DIRT) {
					gameEng.getLevel().remove(x+1, y+1);
				}
				y = y+1;
			}
			break;
		case STOPPEUR:
			break;
		default:
			break;
		}
		if(gameEng.getLevel().isExit(x, y))
			gameEng.sauverLemming(id);

	}

}
