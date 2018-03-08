//package Tema2;
/**
 * Interfata ce reprezinta elementele si contine o functie accept
 * ce va fi implementata in clasele Node, OperandNode, OrNode, AndNode
 * 
 * @author Maria Predescu
 *
 */
public interface Visitable {
	public boolean accept(Visitor visitor);
}
