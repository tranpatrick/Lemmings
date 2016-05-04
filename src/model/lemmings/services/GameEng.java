package model.lemmings.services;

import java.util.Set;

import view.IObservable;

public interface GameEng extends IObservable, RequireLevelService{
	
	/** Observators **/
	public Level getLevel();
	
	// \pre 0 <= x < getLevel().getWidth() AND 0 <= y < getLevel().getHeight()
	public boolean isObstacle(int x, int y);

	// \pre 0 <= x < getLevel().getWidth() AND 0 <= y < getLevel().getHeight()
	public boolean isObstacle2(int x, int y);
	
	// \pre 0 <= x < getLevel().getWidth() AND 0 <= y < getLevel().getHeight()
	public boolean isLibre(int x, int y);
	
	public int getSizeColony();
	
	public int getSpawnSpeed();
	
	public boolean gameOver();
	
	// \pre gameOver()
	public double score();
	
	// \pre getLevel().isEditing() == false
	// \pre !gameOver()
	public int getNombreTours();
	
	// \pre gameOver()
	public int getNombreToursFinal();
	
	// \pre !gameOver()
	public Set<Integer> getLemmingsActifs();
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs()
	public Lemming getLemming(int i);
	
	public int getNombreSauves();
	
	public int getNombreMorts();
	
	public int getNombreActifs();
	
	public int getNombreCrees();
	
	public boolean isAnnihilation();
	
	/** Constructors **/
	// \pre sizeColony > 0
	// \pre spawnSpeed > 0
	// \post getSpawnSpeed() = spawnSpeed;
	// \post getSizeColony() = sizeColony;
	// \post getNombreSauves() = 0;
	// \post getNombreMorts() = 0;
	// \post getNombreActifs() = 0;
	// \post getNombreCrees() = 0;
	// \post getNombreTours() = 0
	// \post isAnnihilation = false;
	public void init(int sizeColony, int spawnSpeed);
	
	/** Opérateurs **/
	// \pre !gameOver()
	// \pre s > 0
	// \post setSpawnSpeed(s) \implies getSpawnSpeed() = s
	public void setSpawnSpeed(int s);
	
	// \pre !gameOver()
	/* \post !isAnnihilation && getNombresTours mod getSpawnSpeed() = 0 AND getNombresCrees() < getSizeColony()
	 * 		\implies getNombreCrees() = getNombreCrees()@pre+1
	 * 				 AND getNombreCrees() \in getLemmingsActifs()
	 * 				 AND getLemming(getNombreCrees()).getX() = x
	 * 				 AND getLemming(getNombreCrees()).getY() = y
	 * 				 AND isEntrance(x,y)
	 */
	/* \post isAnnihilation && getNombresTours mod getSpawnSpeed() = 0 AND getNombresCrees() < getSizeColony()
	 * 		\implies getNombreCrees() = getNombreCrees()@pre
	 */
	// \post getNombreTours() = getNombreTours()@pre + 1
	public void step();
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs
	/* \post tuerLemming(i) \implies getNombreActifs() = getNombreActifs()@pre-1
	 *						AND getNombreMorts() = getNombreMorts()@pre+1
	 *						AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}	
	 */
	public void tuerLemming(int i);
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs
	// \pre getLemming(i).getX() = x AND getLemming(i).getY() = y AND getLevel().isExit(x, y)
	/* \post sauverLemming(i) \implies getNombreActifs() = getNombreActifs()@pre-1
	 *						  AND getNombreSauves() = getNombreSauves()@pre+1
	 *						  AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}					  
	 */
	public void sauverLemming(int i);

	
	// \pre !gameOver()
	// \post isAnnihilation() = true
	public void goAnnihilation();
	
	/** Invariants **/
	// \invMin getNombreActifs() == | getLemmingActifs() |
	// \invMin score() == getNombreSauves / getSizeColony()
	// \invMin getNombreCrees() == getNombreActifs() + getNombreSauves() + getNombreMorts()
	// \inv getNombreTours() >= 0
	// \inv getNombreSauves() >= 0 AND getNombreSauves() <= getNombreCrees() 
	// \inv getNombreMorts() >= 0 AND getNombreMorts() <= getNombreCrees()
	// \inv getNombreActifs() >= 0 AND getActifs() <= getNombreCrees()
	// \inv getNombreCrees() >= 0
}	
