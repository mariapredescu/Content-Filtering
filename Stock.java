//package Tema2;

import java.util.ArrayList;
/**
 * Contine metoda filterStock ce rezolva expresia de filtru pentru fiecare observator
 * 
 * @author Maria Predescu
 *
 */
public class Stock {
	String name;
	String value;
	public Stock(String name, String value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * Calculeaza expresia de filtru a fiecarui observator
	 * 
	 * @param expression expresia de filtru
	 * @param name numele stock-ului
	 * @param value valoarea stock-ului
	 * @return true daca feed-ul trece de filtru si false altfel
	 */
	public boolean filterStock(String expression, String name, String value) {
		
		//Daca expresia este nil pentru toate feed-urile se va returna true
		if(expression.equals("nil ")) {
			return true;
		}
		else {
		//Se calculeaza expresia postfix
		String expr = ShuntingYard.postfix(expression);
		expr = expr.replace("name", name);
		expr = expr.replace("value", value);
		Operator op = new Operator();
		String[] postfix = expr.split(" ");
		ArrayList<String> postList = new ArrayList<String>();
		for(int i = 0; i < postfix.length; i++) {
			postList.add(postfix[i]);
		}
		//Se calculeza operatiile cu eq, ne, ge, gt, lt, le pentru a se obtine o expresie
		//in true, false, || si && 
		for(int i = 0; i < postList.size(); i++) {
			if(postList.get(i).equals("eq")) {
				postList.set(i, String.valueOf(op.eq(postList.get(i - 1), postList.get(i - 2))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
			if(postList.get(i).equals("ne")) {
				postList.set(i, String.valueOf(op.ne(postList.get(i - 1), postList.get(i - 2))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
			if(postList.get(i).equals("gt")) {
				postList.set(i, String.valueOf(op.gt(postList.get(i - 2), postList.get(i - 1))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
			if(postList.get(i).equals("ge")) {
				postList.set(i, String.valueOf(op.ge(postList.get(i - 2), postList.get(i - 1))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
			if(postList.get(i).equals("lt")) {
				postList.set(i, String.valueOf(op.lt(postList.get(i - 2), postList.get(i - 1))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
			if(postList.get(i).equals("le")) {
				postList.set(i, String.valueOf(op.le(postList.get(i - 2), postList.get(i - 1))));
				postList.set(i - 1, "*");
				postList.set(i - 2, "*");
			}
		}
		for(int i = 0; i < postList.size(); i++) {
			if(postList.get(i).equals("*"))
				postList.remove(i--);
		}
		
		//Expresia ce contine doar true, false, || si && este pusa in arbore si rezolvata 
		//cu ajutorul pattern-ului visitor
		String[] Postfix = postList.toArray(new String[0]);
		ExpressionTree et = new ExpressionTree();
		Node root = et.constructTree(Postfix);
		boolean filtru = root.accept(new CalculatorVisitor());
		
		return filtru;
		}
	}
}
