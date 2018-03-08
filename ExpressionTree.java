//package Tema2;

import java.util.Stack;
/**
 * Contine metoda constructTree care construieste arborele aferent 
 * expresiei postfix rezultata in urma algoritmului Shunting Yard
 * apilcat pe expresia de filtru a observatorului
 * 
 * @author Maria Predescu
 *
 */
public class ExpressionTree {
	/**
	 * Verifica daca un string primit ca parametru este sau nu operator
	 * 
	 * @param s un string
	 * @return true daca stringul este parametru sau false altfel
	 */
	public boolean isOperator(String s) {
		if(s.equals("||") || s.equals("&&"))
			return true;
		return false;
	}
	/**
	 * Construieste un arbore pe baza expresiei postfix
	 * rezultata in urma aplicarii algoritmului Shunting Yard pe expresia de filtru
	 * a unui observator
	 * 
	 * @param postfix vector de string-uri rezultat in urma algoritmului Shunting Yard
	 * @return radacina arborelui creat
	 */
	Node constructTree(String postfix[]) {
		Stack <Node> st = new Stack<Node>();
		Node t, t1, t2;
		
		//Se parcurge fiecare element al expresiei date
		for(int i = 0; i < postfix.length; i++) {
			
			//Daca este operand se adauga pe stiva
			if(!isOperator(postfix[i])) {
				t = new OperandNode(postfix[i]);
				st.push(t);
			}
			else { 
				//Operator
				t1 = st.pop();
                t2 = st.pop();
                if(postfix[i].equals("&&")) {
                	t = new AndNode(t1, t2);
                	st.push(t);
                }
                else if(postfix[i].equals("||")) {
                	t = new OrNode(t1, t2);
                	st.push(t);
                }
			}
		}
		//Se extrage varful stivei, adica radacina arborelui
		t = st.peek();
		st.pop();
		
		return t;
	}
}
