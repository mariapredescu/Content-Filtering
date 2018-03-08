//package Tema2;
/**
 * Reprezinta clasa pentru nodul operator ce are doi fii, din aceasta cauza
 * constructorul primeste ca parametrii doua noduri. De asemenea, implementeaza
 * metoda accept a interfetei Visitable
 * 
 * @author Maria Predescu
 *
 */
public class Node implements Visitable {

	public Node Left;
    public Node Right;

    public Node(Node left, Node right) {
        Left = left;
        Right = right;
    }

	//@Override
	public boolean accept(Visitor visitor) {
		return false;
	}

}
