//package Tema2;
/**
 * Implementeaza interfata Visitor, astfel contine trei metode visit 
 * (pentru nodul Or, And si Operand)
 * 
 * @author Maria Predescu
 *
 */
public class CalculatorVisitor implements Visitor {
	
	OperatorFactory factory = new SingletonFactory();
	Operator op = factory.create();
	//@Override
	//Returneaza valoarea nodului operand
	public boolean visit(OperandNode operandNode) {
		
		return Boolean.valueOf(operandNode.Value);
	}
	
	//@Override
	//Realizeaza sau logic intre cei doi fii ai nodului Or
	public boolean visit(OrNode operatorNode) {
		
		return (Boolean.valueOf(operatorNode.Left.accept(this)) || 
				Boolean.valueOf(operatorNode.Right.accept(this)));
	}

	//@Override
	//Realizeaza si logic intre cei doi fii ai nodului And
	public boolean visit(AndNode operatorNode) {
		
		return (Boolean.valueOf(operatorNode.Left.accept(this)) && 
				Boolean.valueOf(operatorNode.Right.accept(this)));
	}
	
}
