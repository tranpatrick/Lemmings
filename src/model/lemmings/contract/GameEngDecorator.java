package model.lemmings.contract;

import java.util.Set;

import model.lemmings.services.GameEng;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level;
import view.IObserver;

public class GameEngDecorator implements GameEng{
	
	private GameEng gameEng;
	
	public GameEngDecorator(GameEng gameEng) {
		this.gameEng = gameEng;
	}
	
	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getLevel()
	 */
	public Level getLevel() {
		return gameEng.getLevel();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see model.lemmings.services.GameEng#isObstacle(int, int)
	 */
	public boolean isObstacle(int x, int y) {
		return gameEng.isObstacle(x, y);
	}

	@Override
	public boolean isLibre(int x, int y) {
		return gameEng.isLibre(x, y);
	}
	

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getSizeColony()
	 */
	public int getSizeColony() {
		return gameEng.getSizeColony();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getSpawnSpeed()
	 */
	public int getSpawnSpeed() {
		return gameEng.getSpawnSpeed();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#gameOver()
	 */
	public boolean gameOver() {
		return gameEng.gameOver();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#score()
	 */
	public double score() {
		return gameEng.score();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreTours()
	 */
	public int getNombreTours() {
		return gameEng.getNombreTours();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreToursFinal()
	 */
	public int getNombreToursFinal() {
		return gameEng.getNombreToursFinal();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getLemmingsActifs()
	 */
	public Set<Integer> getLemmingsActifs() {
		return gameEng.getLemmingsActifs();
	}

	/**
	 * @param i
	 * @return
	 * @see model.lemmings.services.GameEng#getLemming(int)
	 */
	public Lemming getLemming(int i) {
		return gameEng.getLemming(i);
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreSauves()
	 */
	public int getNombreSauves() {
		return gameEng.getNombreSauves();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreMorts()
	 */
	public int getNombreMorts() {
		return gameEng.getNombreMorts();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreActifs()
	 */
	public int getNombreActifs() {
		return gameEng.getNombreActifs();
	}

	/**
	 * @return
	 * @see model.lemmings.services.GameEng#getNombreCrees()
	 */
	public int getNombreCrees() {
		return gameEng.getNombreCrees();
	}
	
	public boolean isAnnihilation(){
		return gameEng.isAnnihilation();
	}

	/**
	 * @param sizeColony
	 * @param spawnSpeed
	 * @see model.lemmings.services.GameEng#init(int, int)
	 */
	public void init(int sizeColony, int spawnSpeed) {
		gameEng.init(sizeColony, spawnSpeed);
	}

	/**
	 * 
	 * @see model.lemmings.services.GameEng#step()
	 */
	public void step() {
		gameEng.step();
	}

	/**
	 * @param i
	 * @see model.lemmings.services.GameEng#tuerLemming(int)
	 */
	public void tuerLemming(int i) {
		gameEng.tuerLemming(i);
	}

	/**
	 * @param i
	 * @see model.lemmings.services.GameEng#sauverLemming(int)
	 */
	public void sauverLemming(int i) {
		gameEng.sauverLemming(i);
	}
	
	public void goAnnihilation() {
		gameEng.goAnnihilation();
	}

	@Override
	public void addObserver(IObserver obs) {
		gameEng.addObserver(obs);
	}

	@Override
	public void deleteObserver(IObserver obs) {
		gameEng.deleteObserver(obs);
	}

	@Override
	public void notifierObservateurs() {
		gameEng.notifierObservateurs();
	}

	@Override
	public void setSpawnSpeed(int s) {
		gameEng.setSpawnSpeed(s);
	}

}