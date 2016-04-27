package model.lemmings.services;

import java.awt.Point;

public interface Level {
	
	public enum Nature {
		EMPTY,DIRT,METAL;
	}
	
	/** Observators **/
	public int getHeight();
	
	public int getWidth();
	
	public boolean isEditing();
	
	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
	public Nature getNature(int x, int y);
	
	public Point getEntrance();

	public Point getExit();
	
	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
	public boolean isEntrance(int x, int y);
	
	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
	public boolean isExit(int x, int y);
	
	
	/** Constructors **/
	// \pre h > 0 AND w > 0
	// \post getHeight() = h
	// \post getwidth() = w
	// \post isEditing() = true;
	// \post \forall (x,y) getNature(x, y) = EMPTY
	public void init(int w, int h);
	
	/** Operators **/
	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
	// \pre isEditing()
	// \post getNature(x,y) = n
	// \post getNature(i,j) = getNature(i,j)@pre if (i,j) != (x,y) 
	//TODO attention setNature sur empty
	public void setNature(int x, int y, Nature n);
	
	// \pre getNature(x,y) = EMPTY
	// \pre getNature(x,y-1) = EMPTY
	// \pre getNature(x,y+1) = EMPTY
	// \post isEntrance(x,y) = true 
	public void setEntrance(int x, int y);

	// \pre getNature(x,y) = EMPTY
	// \pre getNature(x,y-1) = EMPTY
	// \pre getNature(x,y+1) = METAL
	// \post isExit(x,y) = true 
	public void setExit(int x, int y);
	
	// \pre isEditing()
	// \pre \forall j getNature(0,j) = METAL
	// \pre \forall j getNature(getWidth()-1,j) = METAL
	// \pre \forall i getNature(i,0) = METAL
	// \pre \forall j getNature(i,getHeight()-1) = METAL
	// \post isEditing() == false
	/* \post \exists unique (x,y) tq isEntrance(x,y)
	 *								 AND getNature(x, y-1) = EMPTY
	 *								 AND getNature(x, y+1) = EMPTY
	 */	
	/* \post \exists unique (x,y) tq isExit(x,y)
	 *								 AND getNature(x, y-1) = EMPTY
	 *								 AND getNature(x, y+1) = METAL
	 */	
 	public void goPlay();
 	
 	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
 	// \pre !isEditing()
 	// \pre getNature(x,y) = DIRT;
 	// \post getNature(x,y) = EMPTY;
 	// \post getNature(i,j) = getNature(i,j)@pre if (i,j) != (x,y) 	
	public void remove(int x, int y);

 	// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
 	// \pre !isEditing()
 	// \pre getNature(x,y) = EMPTY;
	// \pre !isEntrance(x,y) AND !isExit(x,y);
 	// \post getNature(x,y) = DIRT;
 	// \post getNature(i,j) = getNature(i,j)@pre if (i,j) != (x,y) //TODO a retirer
	public void build(int x, int y);
}
