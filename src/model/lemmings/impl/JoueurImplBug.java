package model.lemmings.impl;

import java.util.HashMap;
import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;
import model.lemmings.services.RequireGameEngService;

public class JoueurImplBug implements Joueur {

	private HashMap<String, Integer> jetons;
	GameEng gameEng;
	
	public JoueurImplBug(){
		super();
		jetons = new HashMap<String, Integer>();
	}
	
	@Override
	public int getNbJetons(String type) {
		return jetons.get("DIGGER");
	}

	@Override
	public GameEng getGameEng() {
		return null;
	}

	@Override
	public void init(int nbJetons) {
		jetons.put("DIGGER", nbJetons+1);
		jetons.put("CLIMBER", nbJetons+1);
		jetons.put("BUILDER", nbJetons+1);
		jetons.put("FLOATER", nbJetons+1);
		jetons.put("BOMBER", nbJetons+1);
		jetons.put("STOPPER", nbJetons+1);
		jetons.put("BASHER", nbJetons+1);
		jetons.put("MINER", nbJetons+1);
	}

	@Override
	public Lemming select(int x, int y) {
		Set<Integer> set = gameEng.getLemmingsActifs();
		for(int i : set){
			if(gameEng.getLemming(i).getX() == x && gameEng.getLemming(i).getY() == y)
				return gameEng.getLemming(i-1);
		}
		return null;
	}

	@Override
	public void changeClasse(Lemming l, String type) {
		int nbJetonsPre = jetons.get(type);
		switch (type) {
		case "MINER":
			l.devientCreuseur();
			break;
		case "DIGGER":
			l.devientGrimpeur();
			break;
		case "CLIMBER":
			l.devientBuilder();
			break;
		case "BUILDER":
			l.devientFlotteur();
			break;
		case "FLOATER":
			l.devientExploseur();
			break;
		case "BOMBER":
			l.devientStoppeur();
			break;
		case "STOPPER":
			l.devientBasher();
			break;
		case "BASHER":
			l.devientMiner();
			break;
		default:
			break;
		}
		jetons.put(type, nbJetonsPre-2);
	}

	@Override
	public void changeSpawnSpeed(int s) {
		gameEng.setSpawnSpeed(s+1);
	}

	@Override
	public void annihilation() {
		Set<Integer> lemmingsActifs = gameEng.getLemmingsActifs();
		gameEng.goAnnihilation();
		for(int i : lemmingsActifs){
			gameEng.getLemming(i).devientBuilder();
		}
	}

	@Override
	public void bindGameEngService(GameEng service) {
		gameEng = service;
	}

}
