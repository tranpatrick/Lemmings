package model.lemmings.main;

import java.awt.Point;

import model.lemmings.contract.GameEngContract;
import model.lemmings.contract.LevelContract;
import model.lemmings.impl.GameEngImpl;
import model.lemmings.impl.LevelImpl;
import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;

public class GameEngMain {
	public static void main(String[] args) {
		/* initialisation */
		Point entrance = new Point(5,2);
		Level levelImpl = new LevelImpl();
		Level levelContract = new LevelContract(levelImpl);
		GameEngImpl gameEngImpl = new GameEngImpl();
		GameEng gameEng = new GameEngContract(gameEngImpl);

		levelContract.init(50, 20);
		gameEngImpl.bindLevelService(levelContract);
		gameEng.init(10, 5);

		/* creation du niveau */
		for (int x = 0; x<levelContract.getWidth(); x++) {
			for (int y = 0; y<levelContract.getHeight(); y++) {
				if (x == 0 || x == levelContract.getWidth()-1)
					levelContract.setNature(x, y, Nature.METAL);
				else if (y == 0 || y == levelContract.getHeight()-1)
					levelContract.setNature(x, y, Nature.METAL);
				else if (y == levelContract.getHeight()-2) {
					if (x == 2) {
						levelContract.setNature(x, y, Nature.METAL);
					}
					else {
						levelContract.setNature(x, y, Nature.DIRT);
					}
				}
				else if (y == 3 && x == 20) {
					levelContract.setNature(x, y, Nature.DIRT);
				}
				else if (y >= 4 && y < 11 && x>2){
					levelContract.setNature(x, y, Nature.DIRT);
				}
				else if (y == 11 && x > 14) {
					levelContract.setNature(x, y, Nature.DIRT);
				}else if(x == 40 && y <= 10 && y > 5){
					levelContract.setNature(x, y, Nature.DIRT);
				}else if(x > 40 && y == 6){
					levelContract.setNature(x, y, Nature.DIRT);
				}else if(x == 3 && y<18 && y>12){
					levelContract.setNature(x, y, Nature.DIRT);
				}
				else {
					levelContract.setNature(x, y, Nature.EMPTY);
				}
			}
		}
		
		levelContract.setNature(6, 4, Nature.EMPTY);
		levelContract.setNature(6, 5, Nature.EMPTY);
		levelContract.setNature(6, 6, Nature.EMPTY);
		
		levelContract.setEntrance(entrance.x, entrance.y);
		levelContract.setExit(2, 17); //TO DO modif exit
		gameEng.getLevel().goPlay();
		while (!gameEng.gameOver()){
			gameEng.step();
			if (gameEng.gameOver())
				break;
			affiche(gameEng);
			System.out.println();
			System.out.println();
			for (int i = 0; i < 59; i++) {
				System.out.print("=");
			}
			for (int i = 0; i < 59; i++) {
				System.out.print("=");
			}
			
			
			System.out.println();
			System.out.println();
			
			
		}
		System.out.println("score "+gameEng.score()*100+"/100");

	}

	public static void affiche(GameEng g) {
		Level l = g.getLevel();
		for (int y = 0; y<l.getHeight(); y++) {
			for(int x=0; x<l.getWidth(); x++) {
				if (l.isEntrance(x, y))
					System.out.print("E");
				else if (l.isExit(x, y))
					System.out.print("S");
				else if (isOccupe(x, y, g)) {
					System.out.print("R");
				}
				else
					afficheCase(l, l.getNature(x, y));
			}
			System.out.println();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isOccupe(int x, int y, GameEng g) {
		for (int i : g.getLemmingsActifs()) {
			Lemming lemming = g.getLemming(i);
			if (lemming.getX() == x && lemming.getY() == y)
				return true;
		}
		return false;
	}

	public static void afficheCase(Level l,Nature n) {
		switch (n) {
		case METAL:
			System.out.print("M");
			break;
		case DIRT:
			System.out.print("D");
			break;
		case EMPTY:
			System.out.print(" ");
			break;
		default:
			break;
		}
	}
}
