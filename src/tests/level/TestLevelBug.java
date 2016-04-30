package tests.level;

import model.lemmings.contract.LevelContract;
import model.lemmings.impl.LevelImplBug;
import tests.TestLevelAbstract;

public class TestLevelBug extends TestLevelAbstract {

	@Override
	public void beforeTests() {
		setLevel(new LevelContract(new LevelImplBug()));
	}

}
