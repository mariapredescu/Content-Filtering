//package Tema2;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 * Contine metode de adaugare si notificare a observatorilor
 * 
 * @author Maria Predescu
 *
 */
public class Subject {
	
	//Lista de observatori
	ArrayList<Observer> observers = new ArrayList<Observer>();
	//TreeMap pentru feed-uri
	TreeMap<String, String> feed = new TreeMap<String, String>();
	
	/**
	 * Adauga un feed in treemap-ul de feed-uri si notifica observatorul
	 * 
	 * @param name numele feed-ului
	 * @param value valoarea feed-ului
	 */
	public void setState(String name, String value) {
		if(feed.containsKey(name)) {
			feed.remove(name);
			feed.put(name, value);
		}
		else {
			feed.put(name, value);
		}
		//Se notifica observatorul
		notifyObserver(name, value);
	}
	
	/**
	 * Adauga un nou observator in lista
	 * 
	 * @param observer observatorul ce trebuie adaugat
	 */
	public void attach(Observer observer) {
		observers.add(observer);
		
		for(Map.Entry<String, String> m : feed.entrySet()) {
			observer.update(m.getKey(), m.getValue());
		}
	}
	
	/**
	 * Notifica observatorul in legatura cu un nou feed
	 * 
	 * @param name numele feed-ului
	 * @param value valoarea feed-ului
	 */
	public void notifyObserver(String name, String value) {
		for(Observer observer : observers)
			observer.update(name, value);
	}
}
