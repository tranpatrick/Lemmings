package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.gameEng.TestGameEng;
import tests.joueur.TestJoueur;
import tests.lemming.TestLemming;
import tests.level.TestLevel;


@RunWith(Suite.class)
@SuiteClasses({
	TestGameEng.class, 
	TestLevel.class,
	TestJoueur.class,
	TestLemming.class})
public class AllTests {
}
