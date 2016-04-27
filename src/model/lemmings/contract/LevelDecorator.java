package model.lemmings.contract;

import java.awt.Point;

import model.lemmings.services.Level;

public class LevelDecorator implements Level{
	
	public void setEntrance(int x, int y) {
		level.setEntrance(x, y);
	}

	public void setExit(int x, int y) {
		level.setExit(x, y);
	}

	private Level level;

	public LevelDecorator(Level level){
		this.level = level;
	}

	/**
	 * @return
	 * @see model.lemmings.services.Level#getHeight()
	 */
	public int getHeight() {
		return level.getHeight();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Level#getWidth()
	 */
	public int getWidth() {
		return level.getWidth();
	}

	/**
	 * @return
	 * @see model.lemmings.services.Level#isEditing()
	 */
	public boolean isEditing() {
		return level.isEditing();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see model.lemmings.services.Level#getNature(int, int)
	 */
	public Nature getNature(int x, int y) {
		return level.getNature(x, y);
	}

	/**
	 * @return
	 * @see model.lemmings.services.Level#getEntrance()
	 */
	public Point getEntrance() {
		return level.getEntrance();
	}
	
	public Point getExit() {
		return level.getExit();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see model.lemmings.services.Level#isEntrance(int, int)
	 */
	public boolean isEntrance(int x, int y) {
		return level.isEntrance(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see model.lemmings.services.Level#isExit(int, int)
	 */
	public boolean isExit(int x, int y) {
		return level.isExit(x, y);
	}

	/**
	 * @param w
	 * @param h
	 * @see model.lemmings.services.Level#init(int, int)
	 */
	public void init(int w, int h) {
		level.init(w, h);
	}

	/**
	 * @param x
	 * @param y
	 * @param n
	 * @see model.lemmings.services.Level#setNature(int, int, model.lemmings.services.Level.Nature)
	 */
	public void setNature(int x, int y, Nature n) {
		level.setNature(x, y, n);
	}

	/**
	 * 
	 * @see model.lemmings.services.Level#goPlay()
	 */
	public void goPlay() {
		level.goPlay();
	}

	/**
	 * @param x
	 * @param y
	 * @see model.lemmings.services.Level#remove(int, int)
	 */
	public void remove(int x, int y) {
		level.remove(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @see model.lemmings.services.Level#build(int, int)
	 */
	public void build(int x, int y) {
		level.build(x, y);
	}
	
	
	

}
