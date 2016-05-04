package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.gameEng.TestGameEngBug;
import tests.joueur.TestJoueurBug;
import tests.lemming.TestLemmingBug;
import tests.level.TestLevelBug;


@RunWith(Suite.class)
@SuiteClasses({
	TestGameEngBug.class, 
	TestLevelBug.class,
	TestJoueurBug.class,
	TestLemmingBug.class})
public class AllTestsBug {
}
