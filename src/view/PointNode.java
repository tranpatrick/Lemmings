package view;

import java.awt.Point;

import javafx.scene.Node;

/**
 * Point decore avec un Node, le Node correspondant a la case (x,y) dans 
 * le plateau
 */
public class PointNode extends Point {
	private static final long serialVersionUID = 1L;
	private Node node;
	public PointNode(int x, int y, Node node) {
		super(x,y);
		this.node = node;
	}
	
	public Node getNode() {
		return node;
	}

}
