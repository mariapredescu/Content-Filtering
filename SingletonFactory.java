//package Tema2;
/**
 * Implementeaza metoda de creare a unui obiect de tipul Operator
 * 
 * @author Maria Predescu
 *
 */
public class SingletonFactory implements OperatorFactory {
	
	private Operator operator = null;
	
	public Operator create() {
		
		if(this.operator == null) {
			operator = new Operator();
		}
		return operator;
	}

}
