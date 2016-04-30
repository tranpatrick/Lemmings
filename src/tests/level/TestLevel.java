package tests.level;

import model.lemmings.contract.LevelContract;
import model.lemmings.impl.LevelImpl;
import tests.TestLevelAbstract;

public class TestLevel extends TestLevelAbstract {

	@Override
	public void beforeTests() {
		setLevel(new LevelContract(new LevelImpl()));
	}

}
