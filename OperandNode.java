//package Tema2;
/**
 * Reprezinta clasa pentru nodul operand, iar constructorul primeste ca
 * parametru valoarea operandului
 * 
 * @author Maria Predescu
 *
 */
class OperandNode extends Node implements Visitable {

    public String Value;

    public OperandNode(String value) {
        super(null, null);
        Value = value;
    }

    //@Override
    public boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

