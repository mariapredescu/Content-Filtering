//package Tema2;

import java.util.*;

/**
 * Contine algoritmul Shunting Yard pentru rezolvarea expresiei de filtru
 * a observatorilor
 * 
 * @author Maria Predescu
 *
 */
public class ShuntingYard {
	
	private enum Operator {
		EQ(1), NE(2), GT(3), GE(4), LT(5), LE(6), OR(7), AND(8);
		final int precedence;

		Operator(int p) {
			precedence = p;
		}
	}
	/**
	 * Se creeaza un HashMap cu operatorii (eq, ne, gt, ge, lt, le, or, and)
	 */
	private static Map<String, Operator> ops = new HashMap<String, Operator>() {
		private static final long serialVersionUID = 1L;

		{
			put("eq", Operator.EQ);
			put("ne", Operator.NE);
			put("gt", Operator.GT);
			put("ge", Operator.GE);
			put("lt", Operator.LT);
			put("le", Operator.LE);
			put("||", Operator.OR);
			put("&&", Operator.AND);
		}
	};
	/**
	 * Verfica precedenta operatorilor 
	 * 
	 * @param op
	 * @param sub
	 * @return true daca precedenta este mai mare sau false altfel
	 */
	private static boolean isHigerPrec(String op, String sub) {
		return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
	}
	/**
	 * Rezolva expresia de filtru a observatorilor prin aplicarea algoritmului
	 * Shunting Yard 
	 * 
	 * @param infix expresia de filtru a observatorului
	 * @return expresia postfix
	 */
	public static String postfix(String infix) {
		StringBuilder output = new StringBuilder();
		Deque<String> stack = new LinkedList<String>();

		for (String token : infix.split("\\s")) {
			// operator
			if (ops.containsKey(token)) {
				while (!stack.isEmpty() && isHigerPrec(token, stack.peek()))
					output.append(stack.pop()).append(' ');
				stack.push(token);
			} 
			//Paranteza stanga
			else if (token.equals("(")) {
				stack.push(token);
			} 
			//Paranteza dreapta
			else if (token.equals(")")) {
				while (!stack.peek().equals("("))
					output.append(stack.pop()).append(' ');
				stack.pop();
			} 
			//String
			else {
				output.append(token).append(' ');
			}
		}

		while (!stack.isEmpty())
			output.append(stack.pop()).append(' ');

		return output.toString();
	}

}
