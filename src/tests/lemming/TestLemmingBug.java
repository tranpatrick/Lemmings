package tests.lemming;

import model.lemmings.contract.GameEngContract;
import model.lemmings.contract.LemmingContract;
import model.lemmings.contract.LevelContract;
import model.lemmings.impl.GameEngImpl;
import model.lemmings.impl.LemmingImplBug;
import model.lemmings.impl.LevelImpl;
import model.lemmings.services.Level.Nature;
import tests.TestLemmingAbstract;

public class TestLemmingBug extends TestLemmingAbstract{

	@Override
	public void beforeTests(){
		setLemming(new LemmingContract(new LemmingImplBug()));
		setGameEng(new GameEngContract(new GameEngImpl()));
		setLevel(new LevelContract(new LevelImpl()));
		getGameEng().bindLevelService(getLevel());
		getLemming().bindGameEngService(getGameEng());
		getGameEng().init(10, 5);
		getLevel().init(10, 10);
		getLevel().setEntrance(8, 5);
		getLevel().setNature(5, 6, Nature.METAL);
		getLevel().setExit(5, 5);
		for (int x = 0; x<getGameEng().getLevel().getWidth(); x++) {
			for (int y = 0; y<getGameEng().getLevel().getHeight(); y++) {
				if (x == 0 || x == getGameEng().getLevel().getWidth()-1 
						|| y == 0 || y == getGameEng().getLevel().getHeight()-1) {
					getGameEng().getLevel().setNature(x, y, Nature.METAL);
				}
			}
		}
		getLevel().goPlay();
	}
	
}
