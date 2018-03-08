//package Tema2;
/**
 * Contine implemetarea metodelor pentru fiecare operator
 * (eq, ne, ge, gt, le, lt)
 * 
 * @author Maria Predescu
 *
 */
public class Operator {
	/*
	 * Metoda eq returneaza true daca cele doua valori primite ca 
	 * parametrii sunt egale si false altfel
	 */
	public boolean eq(String value1, String value2) {
		if(value1.equals(value2))
			return true;
		return false;
	}
	/*
	 * Metoda ne returneaza true daca cele doua valori primite ca 
	 * parametrii nu sunt egale si false altfel
	 */
	public boolean ne(String value1, String value2) {
		
		if(value1.equals(value2))
			return false;
		return true;
	}
	/*
	 * Metoda ge returneaza true daca prima valoare primita ca parametru 
	 * este mai mare sau egala decat cea de-a doua si false altfel
	 */
	public boolean ge(String value1, String value2) {
		
		if(Double.parseDouble(value1) >= Double.parseDouble(value2))
			return true;
		return false;
	}
	/*
	 * Metoda gt returneaza true daca prima valoare primita ca parametru 
	 * este mai mare decat cea de-a doua si false altfel
	 */
	public boolean gt(String value1, String value2) {
		
		if(Double.parseDouble(value1) > Double.parseDouble(value2))
			return true;
		return false;
	}
	/*
	 * Metoda le returneaza true daca prima valoare primita ca parametru 
	 * este mai mica sau egala decat cea de-a doua si false altfel
	 */
	public boolean le(String value1, String value2) {
		
		if(Double.parseDouble(value1) <= Double.parseDouble(value2))
			return true;
		return false;
	}
	/*
	 * Metoda lt returneaza true daca prima valoare primita ca parametru 
	 * este mai mica decat cea de-a doua si false altfel
	 */
	public boolean lt(String value1, String value2) {
		
		if(Double.parseDouble(value1) < Double.parseDouble(value2))
			return true;
		return false;
	}

}
