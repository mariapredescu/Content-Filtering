//package Tema2;
/**
 * Reprezinta clasa pentru operatorul &&(si) ce extinde clasa Node
 * si implementeaza metoda accept a interfetei Visitable
 * 
 * @author Maria Predescu
 *
 */
public class AndNode extends Node implements Visitable {

	public AndNode(Node left, Node right) {
		super(left, right);
	}

	//@Override
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
}
