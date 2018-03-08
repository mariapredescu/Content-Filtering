//package Tema2;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
/**
 * Clasa principala in care se realizeaza citirea de la tastatura a datelor si 
 * realizarea in timp real a comenzilor primite
 * 
 * @author Maria Predescu
 *
 */
public class MainClass {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		String line;
		Subject subject = new Subject();
		String expr = "";
		//Cat timp nu se citeste "end" se realizeaza citirea datelor de la tastatura
		while (input.hasNext() && !(line = input.nextLine()).equals("end")) {
			
			//Fiecare linie este impartita intr-un vector de String-uri
			String[] tokens = line.split(" ");
			
			//Comanda create_obs va crea un nou observator
			if (tokens[0].equals("create_obs")) {
				expr = "";
				for (int i = 2; i < tokens.length; i++) {

					expr = expr + tokens[i] + " ";

				}
				//tokens[1] = id-ul observatorului
				//subject realizeaza conexiunea intre Observer si Subject
				//expr = expresia de filtru a observatorlui
				new Observer(tokens[1], subject, expr);
			}
			
			//Comanda delete_obs va sterge un observator din lista de Observeri
			if (tokens[0].equals("delete_obs")) {
				for (Iterator<Observer> iterator = subject.observers.iterator(); iterator.hasNext();) {
					Observer o = iterator.next();
					if (o.obs_id.equals(tokens[1])) {
						iterator.remove();
					}
				}
			}
			
			//Comanda feed adauga un nou feed la lista de feed-uri si notifica observatorii
			//cu privire la acest lucru prin intermediul functiei setState
			if (tokens[0].equals("feed")) {
				subject.setState(tokens[1], tokens[2]);
				
				//Pentru fiecare observator se actualizeaza in treemap-ul nr_of_changes
				//numarul de schimbari al stock-ului
				for (Observer observer : subject.observers) {
					
					//Daca feed-ul exista in treemap se incrementeaza contorul
					if (observer.nr_of_changes.containsKey(tokens[1])) {
						int n = observer.nr_of_changes.get(tokens[1]);
						n++;
						observer.nr_of_changes.remove(tokens[1]);
						observer.nr_of_changes.put(tokens[1], n);
					} else {
						//Daca nu exista feed-ul respectiv in treemap se adauga
						observer.nr_of_changes.put(tokens[1], 1);
					}
				}
			}
			//Comanda print afiseaza pentru fiecare observator, stock-urile, fluctuatia si numarul de schimbari
			if (tokens[0].equals("print")) {
				DecimalFormat df2 = new DecimalFormat("0.00");
				//Se parcurge lista de observatori
				for (Observer observer : subject.observers) {
					
					if (observer.obs_id.equals(tokens[1])) {
						
						//Daca este prima data cand se face print pentru observatorul respectiv se adauga in treemap-ul
						//print al observatorului ultima valoare a stock-ului si se afiseaza
						if (observer.print.isEmpty()) {
							
							for (Map.Entry<String, String> m : observer.stock.entrySet()) {
								observer.print.put(m.getKey(), m.getValue());
								String change = String.valueOf(observer.nr_of_changes.get(m.getKey()));
								
								//Daca pentru un feed cheia este null inseamna ca feed-ul a fost adaugat inainte de 
								//crearea observatorului si numarul de schimbari este 0
								if (change.equals("null"))
									change = "0";
								
								System.out.println("obs " + tokens[1] + ": " + m.getKey() + " "
										+ Double.valueOf(m.getValue()) + " 0.00% " + change);
								
								//Dupa printare numarul de schimbari se actualizeaza la 0
								if (observer.nr_of_changes.containsKey(m.getKey())) {
									observer.nr_of_changes.remove(m.getKey());
									observer.nr_of_changes.put(m.getKey(), 0);
								}
							}
						} else {
							//Daca s-a mai facut print inainte
							for (Map.Entry<String, String> m : observer.stock.entrySet()) {
								if (!observer.print.containsKey(m.getKey()))
									observer.print.put(m.getKey(), m.getValue());
							}
							for (Map.Entry<String, String> m : observer.stock.entrySet()) {
								Double increase = 0.0;
								
								for (Map.Entry<String, String> n : observer.print.entrySet()) {
									
									//Se calculeaza fluctuatia intre valoarea de la ultima printare si cea curenta
									if (n.getKey().equals(m.getKey())) {
										increase = ((Double.valueOf(m.getValue()) - Double.valueOf(n.getValue())) * 100)
												/ Double.valueOf(n.getValue());
										String change = String.valueOf(observer.nr_of_changes.get(m.getKey()));
										
										//Daca pentru un feed cheia este null inseamna ca feed-ul a fost adaugat  
										//inainte de crearea observatorului si numarul de schimbari este 0
										if (change.equals("null"))
											change = "0";
										System.out.println("obs " + tokens[1] + ": " + m.getKey() + " "
												+ Double.valueOf(m.getValue()) + " " + df2.format(increase) + "% "
												+ change);
									}
								}
								//Dupa printare numarul de schimbari se actualizeaza la 0
								if (observer.nr_of_changes.containsKey(m.getKey())) {
									observer.nr_of_changes.remove(m.getKey());
									observer.nr_of_changes.put(m.getKey(), 0);
								}
							}
							//Se actualizeaza valoarea in treemap-ul print dupa executarea comenzii de printare
							for (Map.Entry<String, String> m : observer.stock.entrySet()) {
								observer.print.put(m.getKey(), m.getValue());
							}
						}
					}
				}
			}
		}

		input.close();
	}
}
