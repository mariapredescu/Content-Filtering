//package Tema2;
/**
 * Interfata ce reprezinta visitor-ul ce contine metode ce vor fi implementate
 * in clasa CalculatorVisitor
 * 
 * @author Maria Predescu
 *
 */
public interface Visitor {
	public boolean visit(OrNode operatorNode);
	public boolean visit(AndNode operatorNode);
	public boolean visit(OperandNode operandNode);
}
