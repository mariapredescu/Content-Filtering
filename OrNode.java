//package Tema2;
/**
 * Reprezinta clasa pentru operantorul ||(sau) ce extinde clasa Node 
 * si implementeaza metoda accept a intefetei Visitable
 * 
 * @author Maria Predescu
 *
 */
public class OrNode extends Node implements Visitable {

	public OrNode(Node left, Node right) {
		super(left, right);
	}

	//@Override
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
}
