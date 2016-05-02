package model.lemmings.impl;

import java.util.HashMap;
import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;
import model.lemmings.services.RequireGameEngService;

public class JoueurImpl implements Joueur {

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
	public void init(int nbJetons) {
		jetons.put("DIGGER", nbJetons);
		jetons.put("CLIMBER", nbJetons);
		jetons.put("BUILDER", nbJetons);
		jetons.put("FLOATER", nbJetons);
		jetons.put("BOMBER", nbJetons);
		jetons.put("STOPPER", nbJetons);
		jetons.put("BASHER", nbJetons);
		jetons.put("MINER", nbJetons);
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
	public void changeSpawnSpeed(int s) {
		gameEng.setSpawnSpeed(s);
	}

	@Override
	public void annihilation() {
		Set<Integer> lemmingsActifs = gameEng.getLemmingsActifs();
		gameEng.goAnnihilation();
		for(int i : lemmingsActifs){
			gameEng.getLemming(i).devientExploseur();
		}
	}

	@Override
	public void bindGameEngService(GameEng service) {
		gameEng = service;
	}

}
