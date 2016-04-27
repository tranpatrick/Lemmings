package model.lemmings.impl;

import java.awt.Point;
import java.util.Random;

import model.lemmings.services.Level;

public class LevelImplBug implements Level{
	
	/* NOTES pour les tableaux
	 * dans tab[v1][v2] v1 est la hauteur et v2 est la largeur
	 * lorsque l'on veut accéder à la case [x,y] faire tab[y][x]
	 */

	private int height;
	private int width;
	private boolean editing;
	private Nature[][] terrain;
	private Point entree;
	private Point sortie;
	
	public LevelImplBug(){
		super();
	}
	
	/** Observators **/
	@Override
	public int getHeight() {
		return -1;
	}

	@Override
	public int getWidth() {
		return -1;
	}

	@Override
	public boolean isEditing() {
		return !editing;
	}

	@Override
	public Nature getNature(int x, int y) {
		return Nature.EMPTY;
	}
	
	@Override
	public Point getEntrance(){
		return new Point(0,0);
	}

	/** Constructors **/
	@Override
	public void init(int w, int h) {
		height = -1;
		width = -1;
		editing = true;
		terrain = new Nature[h][w];
		entree = null;
		sortie = null;
	}

	/** Operators **/
	@Override
	public void setNature(int x, int y, Nature n) {
		terrain[y][x] = Nature.EMPTY;
	}

	@Override
	public void goPlay() {
		editing = true;
		entree = null;
		sortie = null;
	}

	@Override
	public void remove(int x, int y) {
		setNature(x,y,Nature.METAL);
	}

	@Override
	public void build(int x, int y) {
		setNature(x,y,Nature.METAL);
	}

	@Override
	public boolean isEntrance(int x, int y) {
		return true;
	}

	@Override
	public boolean isExit(int x, int y) {
		return true;
	}

	@Override
	public void setEntrance(int x, int y) {
		entree = new Point(x, y);		
	}

	@Override
	public void setExit(int x, int y) {
		sortie = new Point(x, y);		
	}

}
