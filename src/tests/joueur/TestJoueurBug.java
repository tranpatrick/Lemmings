package tests.joueur;

import model.lemmings.contract.GameEngContract;
import model.lemmings.contract.JoueurContract;
import model.lemmings.contract.LevelContract;
import model.lemmings.impl.GameEngImpl;
import model.lemmings.impl.JoueurImpl;
import model.lemmings.impl.JoueurImplBug;
import model.lemmings.impl.LevelImpl;
import model.lemmings.services.Level.Nature;
import tests.TestJoueurAbstract;

public class TestJoueurBug extends TestJoueurAbstract {

	@Override
	public void beforeTests() {
		setLevel(new LevelContract(new LevelImpl()));
		setGameEng(new GameEngContract(new GameEngImpl()));
		setJoueur(new JoueurContract(new JoueurImplBug()));
		getGameEng().bindLevelService(getLevel());
		getJoueur().bindGameEngService(getGameEng());
		
		getLevel().init(25, 25);
		getGameEng().init(10, 5);
		
		for (int x = 0; x<getGameEng().getLevel().getWidth(); x++) {
			for (int y = 0; y<getGameEng().getLevel().getHeight(); y++) {
				if (x == 0 || x == getGameEng().getLevel().getWidth()-1 
						|| y == 0 || y == getGameEng().getLevel().getHeight()-1) {
					getGameEng().getLevel().setNature(x, y, Nature.METAL);
				}
			}
		}
		getGameEng().getLevel().setEntrance(8, 5);
		getGameEng().getLevel().setNature(5, 6, Nature.METAL);
		getGameEng().getLevel().setExit(5, 5);
		getGameEng().getLevel().goPlay();
	}

}
