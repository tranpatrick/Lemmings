package tests.gameEng;

import model.lemmings.contract.GameEngContract;
import model.lemmings.contract.LevelContract;
import model.lemmings.impl.GameEngImpl;
import model.lemmings.impl.GameEngImplBug;
import model.lemmings.impl.LevelImpl;
import model.lemmings.services.Level.Nature;
import tests.TestGameEngAbstract;

public class TestGameEng extends TestGameEngAbstract {

	@Override
	public void beforeTests() {
		setLevel(new LevelContract(new LevelImpl()));
		setGameEng(new GameEngContract(new GameEngImpl()));
		setLevel(new LevelContract(new LevelImpl()));
		setGameEng(new GameEngContract(new GameEngImplBug()));
		getLevel().init(10, 10);
		for (int x = 0; x<getLevel().getWidth(); x++) {
			for (int y = 0; y<getLevel().getHeight(); y++) {
				if (x == 0 || x == getLevel().getWidth()-1 
						|| y == 0 || y == getLevel().getHeight()-1) {
					getLevel().setNature(x, y, Nature.METAL);
				}
			}
		}
		getLevel().setEntrance(8, 5);
		getLevel().setNature(5, 6, Nature.METAL);
		getLevel().setExit(5, 5);
		getLevel().goPlay();
		getGameEng().bindLevelService(getLevel());
	}

}
