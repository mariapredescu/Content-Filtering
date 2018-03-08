//package Tema2;

import java.util.TreeMap;
/**
 * Clasa observatorului ce contine constructorul observatorului si metoda update
 * 
 * @author Maria Predescu
 *
 */
public class Observer {

	String obs_id;	//Id-ul observatorului
	Subject feed;	//Legatura cu clasa Subject
	String filter;	//Expresia de filtru a observatorului
	
	//Retine stock-urile observatorului
	TreeMap<String, String> stock = new TreeMap<String, String>();
	//Retine stock-urile de la ultima afisare
	TreeMap<String, String> print = new TreeMap<String, String>();
	//Retine numarul de schimbari al fiecarui stock
	TreeMap<String, Integer> nr_of_changes = new TreeMap<String, Integer>();
	
	public Observer(String obs_id, Subject stock, String filter) {
		this.obs_id = obs_id;
		this.feed = stock;
		this.filter = filter;
		this.feed.attach(this);
	}
	/**
	 * Face update-ul stock-ului
	 * 
	 * @param name nume feed
	 * @param value valoare feed
	 */
	public void update(String name, String value) {
		
		Stock s = new Stock(name, value);
		
		//Daca feed-ul nu trece de filtru se elimina din lista de stock-uri
		if(stock.containsKey(name) && s.filterStock(filter, name, value) == false) {
			stock.remove(name);
		}
		//Daca feed-ul trece de filtru se adauga in lista de stock-uri
		if(s.filterStock(filter, name, value)) {
			stock.put(name, value);
		}
	}
}
