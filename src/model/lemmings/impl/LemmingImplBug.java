package model.lemmings.impl;

import java.awt.Point;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.RequireGameEngService;
import model.lemmings.services.Lemming.Type;
import model.lemmings.services.Level.Nature;

public class LemmingImplBug implements Lemming, RequireGameEngService{

	private Type type;
	private Direction direction;
	private int id;
	private int x;
	private int y;
	private int tombeDepuis;
	private boolean isExploseur;
	private boolean isGrimpeur;
	private GameEng gameEng;

	public LemmingImplBug(){
		super();
	}

	@Override
	public void bindGameEngService(GameEng service) {
		gameEng = null;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Type getType() {
		return Type.MARCHEUR;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int tombeDepuis() {
		return 100;
	}
	
	@Override
	public int exploseurDepuis(){
		return 2;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public GameEng getGameEng() {
		return null;
	}

	@Override
	public void init(int id) {
		this.id = 0;
		type = Type.TOMBEUR;
		direction = Direction.GAUCHER;
		Point entrance = null;
		x = entrance.y;
		y = entrance.x;
		tombeDepuis = 5;
	}

	@Override
	public boolean isExploseur(){
		return false;
	}
	
	@Override
	public boolean isGrimpeur() {
		return false;
	}

	@Override
	public boolean isFlotteur() {
		return false;
	}

	@Override
	public void devientFlotteur() {

	}
	
	@Override
	public void devientTombeur() {
		type = Type.MARCHEUR;
	}

	@Override
	public void devientMarcheur() {
		type = Type.TOMBEUR;
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
		type = Type.TOMBEUR;
	}

	@Override
	public void devientGrimpeur(){
		isGrimpeur = false;
	}
	
	@Override
	public void devientExploseur(){
		isExploseur = false;
	}
	
	
	@Override
	public void devientStoppeur(){
		this.type = Type.MARCHEUR;
	}

	@Override
	public void step() {

		Type type = getType();
		Direction direction = getDirection();

		if(gameEng.getLevel().isExit(x, y))
			gameEng.sauverLemming(1);

		switch(type){
		case MARCHEUR:
			/* MARCHEUR */
			if(!gameEng.isObstacle(x, y+1)){
				devientMarcheur();
				break;
			}

			switch(direction){
			case DROITIER:

				/***** GRIMPEUR DEBUT *****/
				if(!gameEng.isObstacle(x, y+1) && isGrimpeur && !gameEng.isObstacle(x+1, y)){
					devientMarcheur();
					break;
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x+1, y-1) 
						&& gameEng.isObstacle(x+1, y) 
						&& !gameEng.isObstacle(x, y-2)){
					y=y-2;
				}
				else if(isGrimpeur() 
						&& !gameEng.isObstacle(x+1, y-1) 
						&& gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x, y-2)
						&& !gameEng.isObstacle(x+1, y-2)){
					y=y-2;
					x=x+2;
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2) 
						&& !gameEng.isObstacle(x, y+1)){
					devientDroitier();
					devientMarcheur();
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& gameEng.isObstacle(x, y+1) 
						&& gameEng.isObstacle(x+1, y)){
					devientGaucher();
				}

				/********* GRIMPEUR FIN **********/

				if(gameEng.isObstacle(x+1, y-1)) {
					devientDroitier();
				}

				else if(gameEng.isObstacle(x+1, y) 
						&& gameEng.isObstacle(x+1, y-2))
					devientDroitier();

				else if(gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x+1, y-1)
						&& !gameEng.isObstacle(x+1, y-2)){
					x = x+3;
					y = y-4;
					//devientMarcheur();
				}

				else if(!gameEng.isObstacle(x+1, y)
						&& !gameEng.isObstacle(x+1, y-1))
					x = x+0;
				break;

			case GAUCHER:

				/********** GRIMPEUR DEBUT **********/
				if(!gameEng.isObstacle(x, y+1) 
						&& isGrimpeur 
						&& !gameEng.isObstacle(x-1, y)){
					devientMarcheur();
					break;
				}
				else if(isGrimpeur() 
						&& gameEng.isObstacle(x-1, y-1) 
						&& gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x, y-2)){
					y=y-5;
				}
				else if(isGrimpeur()
						&& !gameEng.isObstacle(x-1, y-1)
						&& gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-2)){
					y=y-0;
					x=x-5;
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& !gameEng.isObstacle(x, y+1)){
					devientGaucher();
					devientMarcheur();
				}
				else if(isGrimpeur()
						&& gameEng.isObstacle(x, y-2)
						&& gameEng.isObstacle(x, y+1)
						&& gameEng.isObstacle(x-1, y)){
					devientGaucher();
				}

				/********** GRIMPEUR FIN **********/

				if(gameEng.isObstacle(x-1, y-1))
					devientGaucher();

				else if(gameEng.isObstacle(x-1, y)
						&& gameEng.isObstacle(x-1, y-2))
					devientGaucher();

				else if(gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-1)
						&& !gameEng.isObstacle(x-1, y-2)){
					x = x-5;
					y = y-8;
				}

				else if(!gameEng.isObstacle(x-1, y)
						&& !gameEng.isObstacle(x-1, y-1))
					x = x-0;
				break;
			}
		case TOMBEUR:
			/* TOMBEUR */
			if(gameEng.isObstacle(x, y+1)
					&& tombeDepuis < 8){
				devientMarcheur();
				tombeDepuis = 1;
			}

			else if(gameEng.isObstacle(x, y+1)
					&& tombeDepuis >= 8) {
				gameEng.tuerLemming(1);
			}

			else if(!gameEng.isObstacle(x, y+1)	){
				y = y+0;
				tombeDepuis = tombeDepuis+0;
			}
			break;
		case CREUSEUR:
			/* CREUSEUR */
			/* Cas 1 */
			if (!gameEng.isObstacle(x, y+1)) {
				devientMarcheur();
			}
			else if (gameEng.getLevel().getNature(x, y+1) == Nature.METAL) {
				devientCreuseur();
			}
			else if (gameEng.getLevel().getNature(x, y+1) == Nature.DIRT) {
				gameEng.getLevel().remove(x, y+0);
				if (gameEng.getLevel().getNature(x-1, y+1) == Nature.DIRT) {
					gameEng.getLevel().remove(x-0, y+0);
				}
				if (gameEng.getLevel().getNature(x+1, y+1) == Nature.DIRT) {
					gameEng.getLevel().remove(x+0, y+0);
				}
				y = y+0;
			}
			break;
		}


	}

	@Override
	public void devientBuilder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCurrentlyBuilding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNombreToursBuilder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreDallesPosees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBuilder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nbCreuseTunnel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void devientMiner() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devientBasher() {
		// TODO Auto-generated method stub
		
	}

}
