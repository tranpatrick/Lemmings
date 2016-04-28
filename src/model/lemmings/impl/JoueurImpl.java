package model.lemmings.impl;

import java.util.HashMap;
import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;
import model.lemmings.services.RequireGameEngService;

public class JoueurImpl implements Joueur, RequireGameEngService {

	private HashMap<String, Integer> jetons;
	GameEng gameEng;
	
	public JoueurImpl(){
		super();
		jetons = new HashMap<String, Integer>();
	}
	
	@Override
	public int getNbJetons(String type) {
		return jetons.get(type);
	}

	@Override
	public GameEng getGameEng() {
		return gameEng;
	}

	@Override
	public void init() {
		jetons.put("DIGGER", 10);
		jetons.put("CLIMBER", 10);
		jetons.put("BUILDER", 10);
		jetons.put("FLOATER", 10);
		jetons.put("BOMBER", 10);
		jetons.put("STOPPER", 10);
		jetons.put("BASHER", 10);
		jetons.put("MINER", 10);
	}

	@Override
	public Lemming select(int x, int y) {
		Set<Integer> set = gameEng.getLemmingsActifs();
		for(int i : set){
			if(gameEng.getLemming(i).getX() == x && gameEng.getLemming(i).getY() == y)
				return gameEng.getLemming(i);
		}
		return null;
	}

	@Override
	public void changeClasse(Lemming l, String type) {
		int nbJetonsPre = jetons.get(type);
		switch (type) {
		case "DIGGER":
			l.devientCreuseur();
			break;
		case "CLIMBER":
			l.devientGrimpeur();
			break;
		case "BUILDER":
			l.devientBuilder();
			break;
		case "FLOATER":
			l.devientFlotteur();
			break;
		case "BOMBER":
			l.devientExploseur();
			break;
		case "STOPPER":
			l.devientStoppeur();
			break;
		case "BASHER":
			l.devientBasher();
			break;
		case "MINER":
			l.devientMiner();
			break;
		default:
			break;
		}
		jetons.put(type, nbJetonsPre-1);
	}

	@Override
	public void changeSpawnSpeed(GameEng g, int s) {
		gameEng.setSpawnSpeed(s);
	}

	@Override
	public void killThemAll(GameEng g) {
		//TODO mode Anhilation
	}

	@Override
	public void reset(GameEng g) {
		//TODO reset
	}

	@Override
	public void bindGameEngService(GameEng service) {
		gameEng = service;
	}

}
