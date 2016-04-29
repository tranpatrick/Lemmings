package model.lemmings.contract;

import java.awt.Point;

import model.lemmings.services.Level;

public class LevelContract extends LevelDecorator {

	public LevelContract(Level level) {
		super(level);
	}

	/** Observators **/

	public int getHeight(){
		checkInvariant();
		int height = super.getHeight();
		checkInvariant();
		return height;
	}

	public int getWidth(){
		checkInvariant();
		int width = super.getWidth();
		checkInvariant();
		return width;
	}

	public boolean isEditing(){
		checkInvariant();
		boolean editing = super.isEditing();
		checkInvariant();
		return editing;
	}

	public Nature getNature(int x, int y){
		Nature res;

		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x < super.getWidth() && 0 <= y && y < super.getHeight()))
			throw new PreConditionError("getNature : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		checkInvariant();
		res = super.getNature(x, y);
		checkInvariant();

		return res;
	}

	public Point getEntrance(){
		checkInvariant();
		Point res = super.getEntrance();
		checkInvariant();
		return res;
	}

	public Point getExit(){
		checkInvariant();
		Point res = super.getExit();
		checkInvariant();
		return res;
	}

	public boolean isEntrance(int x, int y){
		boolean res;

		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x < super.getWidth() && 0 <= y && y < super.getHeight()))
			throw new PreConditionError("isEntrance : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		checkInvariant();
		res = super.isEntrance(x, y);
		checkInvariant();

		return res;
	}


	public boolean isExit(int x, int y){
		boolean res;

		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x <= super.getWidth() && 0 <= y && y <= super.getHeight()))
			throw new PreConditionError("isExit : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		checkInvariant();
		res = super.isExit(x, y);
		checkInvariant();

		return res;
	}



	/** Constructors **/

	public void init(int w, int h){
		// \pre h > 0 AND w > 0
		if(!(h > 0 && w > 0))
			throw new PreConditionError("init : h>0 AND w>0 not satisfied");

		checkInvariant();
		super.init(w, h);
		checkInvariant();

		// \post getHeight() = h
		if(!(super.getHeight() == h))
			throw new PostConditionError("init : getHeight = h not satisfied");

		// \post getwidth() = w
		if(!(super.getWidth() == w))
			throw new PostConditionError("init : getWidth = w not satisfied");

		// \post isEditing() = true;
		if(!(super.isEditing() == true))
			throw new PostConditionError("init : isEditing() = true not satisfied");

		// \post \forall (x,y) getNature(x, y) = EMPTY
		for(int x=0; x<super.getWidth(); x++){
			for(int y=0; y<super.getHeight(); y++){
				if(super.getNature(x, y) != Nature.EMPTY)
					throw new PostConditionError("init : forall (x,y) getNature(x, y) = EMPTY not satisfied");
			}
		}
	}



	/** Operators **/

	public void setNature(int x, int y, Nature n){
		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x < super.getWidth() && 0 <= y && y < super.getHeight()))
			throw new PreConditionError("setNature : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		// \pre isEditing()
		if(!super.isEditing())
			throw new PreConditionError("setNature : isEditing() not satisfied");

		//Initialisation de la matrice des nature@pre
		Nature[][] naturePre = new Nature[super.getHeight()][super.getWidth()];
		for(int i=0; i<super.getHeight(); i++){
			for(int j=0; j<super.getWidth(); j++){
				naturePre[i][j] = super.getNature(j, i);
			}
		}

		checkInvariant();
		super.setNature(x, y, n);
		checkInvariant();

		// \post getNature(x,y) = n
		if(!(super.getNature(x,y) == n))
			throw new PostConditionError("setNature : getNature(x,y) = n not satisifed");

		// \post getNature(i,j) = getNature(i,j)@pre if (i,j) != (x,y)
		for(int i=0; i<super.getHeight(); i++){
			for(int j=0; j<super.getWidth(); j++){
				if(i != y && j != x) {
					if(super.getNature(j, i) != naturePre[i][j])
						throw new PostConditionError("setNature : getNature(i,j) = getNature(i,j)@pre if (i,j) != (x,y) not satisifed");
				}
			}
		}
	}

	@Override
	public void goEditing() {
		if(super.isEditing() == true)
			throw new PreConditionError("goEditing : !isEditing() not satisfied");
		super.goEditing();
		if(super.isEditing() != true)
			throw new PostConditionError("goEditing : isEditing() not satisfied");
	}
	
	public void goPlay(){
		// \pre isEditing()
		if(super.isEditing() == false)
			throw new PreConditionError("goPlay : isEditing() not satisfied");

		// \pre \forall j getNature(0,j) = METAL
		for(int j=0; j<super.getHeight(); j++)
			if(super.getNature(0, j) != Nature.METAL)
				throw new PreConditionError("goPlay : \forall j getNature(0,j) = METAL not satisfied");

		// \pre \forall j getNature(getWidth()-1,j) = METAL
		for(int j=0; j<super.getHeight(); j++)
			if(super.getNature(super.getWidth()-1, j) != Nature.METAL)
				throw new PreConditionError("goPlay : \forall j getNature(getWidth()-1,j) = METAL not satisfied");

		// \pre \forall i getNature(i,0) = METAL
		for(int i=0; i<super.getWidth(); i++)
			if(super.getNature(i, 0) != Nature.METAL)
				throw new PreConditionError("goPlay : \forall j getNature(i,0) = METAL not satisfied");

		// \pre \forall i getNature(i,getHeight()-1) = METAL
		for(int i=0; i<super.getWidth(); i++)
			if(super.getNature(i, super.getHeight()-1) != Nature.METAL)
				throw new PreConditionError("goPlay : \forall j getNature(i,getHeight()-1) = METAL not satisfied");

		checkInvariant();
		super.goPlay();
		checkInvariant();

		// \post isEditing() == false
		if(super.isEditing() == true)
			throw new PostConditionError("goPlay : isEditing() = false not satisfied");

		int nbEntrance=0, nbExit=0;

		/* \post \exists unique (x,y) tq isEntrance(x,y)
		 *								 AND getNature(x, y-1) = EMPTY
		 *								 AND getNature(x, y+1) = EMPTY
		 */	
		for(int i=0; i<super.getWidth(); i++){
			for(int j=0; j<super.getHeight(); j++){
				if(super.isEntrance(i, j) && super.getNature(i, j-1)==Nature.EMPTY && super.getNature(i, j+1)==Nature.EMPTY)
					nbEntrance++;
			}
		}
		if(nbEntrance != 1)
			throw new PostConditionError("goPlay : exists unique (x,y) tq isEntrance(x,y) AND getNature(x, y-1) = EMPTY AND getNature(x, y+1) = EMPTY not satisfied");

		/* \post \exists unique (x,y) tq isExit(x,y)
		 *								 AND getNature(x, y-1) = EMPTY
		 *								 AND getNature(x, y+1) = METAL
		 */
		for(int i=0; i<super.getWidth(); i++){
			for(int j=0; j<super.getHeight(); j++){
				if(super.isExit(i, j) && super.getNature(i, j-1)==Nature.EMPTY && super.getNature(i, j+1)==Nature.METAL)
					nbExit++;
			}
		}
		if(nbExit != 1)
			throw new PostConditionError("goPlay : exists unique (x,y) tq isExit(x,y) AND getNature(x, y-1) = EMPTY AND getNature(x, y+1) = METAL not satisfied");

	}

	public void remove(int x, int y){
		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x < super.getWidth() && 0 <= y && 0 < super.getHeight()))
			throw new PreConditionError("remove : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		// \pre !isEditing()
		if(super.isEditing())
			throw new PreConditionError("remove : !isEditing() not satisfied");

		// \pre getNature(x,y) = DIRT
		if(super.getNature(x, y) != Nature.DIRT)
			throw new PreConditionError("remove : getNature(x,y) = DIRT not satisfied");

		//Initialisation de la matrice des nature@pre
		Nature[][] naturePre = new Nature[super.getHeight()][super.getWidth()];
		for(int i=0; i<super.getHeight(); i++){
			for(int j=0; j<super.getWidth(); j++){
				naturePre[i][j] = super.getNature(j, i);
			}
		}

		checkInvariant();
		super.remove(x, y);
		checkInvariant();

		// \post getNature(x,y) = EMPTY
		if(super.getNature(x, y) != Nature.EMPTY)
			throw new PostConditionError("remove : getNature(x,y) = EMPTY not satisfied");

	}

	public void build(int x, int y){
		// \pre 0 <= x < getWidth() AND 0 <= y < getHeight()
		if(!(0 <= x && x <= super.getWidth() && 0 <= y && 0 <= super.getHeight()))
			throw new PreConditionError("build : 0 <= x < getWidth() AND 0 <= y < getHeight() not satisfied");

		// \pre !isEditing()
		if(super.isEditing())
			throw new PreConditionError("build : !isEditing() not satisfied");

		// \pre getNature(x,y) = EMPTY
		if(super.getNature(x, y) != Nature.EMPTY)
			throw new PreConditionError("build : getNature(x,y) = EMPTY not satisfied");

		// \pre !isEntrance(x,y) AND !isExit(x,y)
		if(super.isEntrance(x, y) || super.isExit(x, y))
			throw new PreConditionError("build : !isEntrance(x,y) AND !isExit(x,y) not satisfied");

		//Initialisation de la matrice des nature@pre
		Nature[][] naturePre = new Nature[super.getHeight()][super.getWidth()];
		for(int i=0; i<super.getHeight(); i++){
			for(int j=0; j<super.getWidth(); j++){
				naturePre[i][j] = super.getNature(j, i);
			}
		}

		checkInvariant();
		super.build(x, y);
		checkInvariant();

		// \post getNature(x,y) = DIRT
		if(super.getNature(x, y) != Nature.DIRT)
			throw new PostConditionError("build : getNature(x,y) = DIRT not satisfied");

	}

	@Override
	public void setEntrance(int x, int y) {
		// \pre getNature(x,y) = EMPTY
		if(super.getNature(x, y) != Nature.EMPTY)
			throw new PreConditionError("setEntrance : getNature(x,y) = EMPTY not satisfied");
		// \pre getNature(x,y-1) = EMPTY
		if(y-1<0 || (y-1>0 && super.getNature(x, y-1) != Nature.EMPTY))
			throw new PreConditionError("setEntrance : getNature(x,y-1) = EMPTY not satisfied");
		// \pre getNature(x,y+1) = EMPTY
		if(y+1>super.getHeight()-1 || (y+1<super.getHeight() && super.getNature(x, y+1) != Nature.EMPTY))
			throw new PreConditionError("setEntrance : getNature(x,y+1) = EMPTY not satisfied");
		checkInvariant();
		super.setEntrance(x, y);
		checkInvariant();
		// \post isEntrance(x,y) = true
		if(super.isEntrance(x, y) != true)
			throw new PostConditionError("setEntrance : isEntrance(x,y) not satisfied");
	}

	@Override
	public void setExit(int x, int y) {
		// \pre getNature(x,y) = EMPTY
		if(super.getNature(x, y) != Nature.EMPTY)
			throw new PreConditionError("setExit : getNature(x,y) = EMPTY not satisfied");
		// \pre getNature(x,y-1) = EMPTY
		if(y-1<0 || (y-1>0 && super.getNature(x, y-1) != Nature.EMPTY))
			throw new PreConditionError("setExit: getNature(x,y-1) = EMPTY not satisfied");
		// \pre getNature(x,y+1) = METAL
		if(y+1>super.getHeight()-1 || (y+1<super.getHeight() && super.getNature(x, y+1) != Nature.METAL))
			throw new PreConditionError("setExit : getNature(x,y+1) = METAL not satisfied");
		checkInvariant();
		super.setExit(x, y);
		checkInvariant();
		// \post isExit(x,y) = true
		if(super.isExit(x, y) != true)
			throw new PostConditionError("setExit : isExit(x,y) not satisfied");
	}

	@Override
	public void reset() {
		super.reset();
	}
	
	private void checkInvariant(){

	}

}
