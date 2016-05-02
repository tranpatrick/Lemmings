package model.lemmings.impl;

import java.awt.Point;

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
	private Nature[][] backup;
	private Point entree;
	private Point sortie;

	public LevelImplBug(){
		super();
	}



	/** Observators **/
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isEditing() {
		return editing;
	}

	@Override
	public Nature getNature(int x, int y) {
		return terrain[y][x];
	}

	@Override
	public Point getEntrance() {
		return entree;
	}

	@Override
	public Point getExit() {
		return sortie;
	}

	/** Constructors **/
	@Override
	public void init(int w, int h) {
		height = h;
		width = w;
		editing = true;
		terrain = new Nature[h][w];
		backup = new Nature[h][w];
		for (int x = 0; x<getWidth(); x++) {
			for (int y = 0; y<getHeight(); y++) {
				setNature(x, y, Nature.EMPTY);
			}
		}
		entree = null;
		sortie = null;
	}

	/** Operators **/
	@Override
	public void setNature(int x, int y, Nature n) {
		terrain[y][x] = n;
		if (isEditing() == true)
			backup[y][x] = n;
	}

	@Override
	public void reset() {
		for (int x = 0; x<getWidth(); x++) 
			for (int y = 0; y<getHeight(); y++) 
				terrain[y][x] = backup[y][x];
	}

	@Override
	public void goEditing() {
		editing = true;
	}
	
	@Override
	public void goPlay() {
		//bug
	}

	@Override
	public void remove(int x, int y) {
		setNature(x,0,Nature.EMPTY);
	}

	@Override
	public void build(int x, int y) {
		setNature(0,y,Nature.DIRT);
	}

	@Override
	public boolean isEntrance(int x, int y) {
		if (entree == null) 
			return false;
		return x == entree.x && y == entree.y;
	}

	@Override
	public boolean isExit(int x, int y) {
		if (sortie == null)
			return false;
		return x == sortie.x && y == sortie.y;
	}

	@Override
	public void setEntrance(int x, int y) {
		entree = new Point(0, y); //bug
	}

	@Override
	public void setExit(int x, int y) {
		sortie = new Point(x, 0);//bug
	}


}
