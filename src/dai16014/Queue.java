package dai16014;

import java.util.ArrayList;

public class Queue {
	private  ArrayList<Person> people = new ArrayList<Person>();  // λίστα ατόμων στην ουρά
	private Counter ext;										  // μετρητής ατόμων που βγήκαν από την ουρά
	
	public Queue(Counter exited) {
		ext=exited;
	}
	
	public synchronized boolean add(Person person) {
		if (people.size() < Main.D + ext.getCounter()) {	// έλεγχος για γεμάτη ουρά
			people.add(person);								// προσθήκη στην ουρά
			return true;
			}
		else {
			System.out.println("\n                                                                                                  The queue is full. Waiting...\n");		// εκτύπωση μηνύματος για γεμάτη ουρά
			return false;
		}
	}
	
	public synchronized Person get(int c) {
		return people.get(c);
	}

	public synchronized long calcAvg(int choice, int size) {
		long max=0;												// συνάρτηση υπολογισμού 
		if (choice==0) {										// συνολικού χρόνου αναμονής/ πραγματικού
			for (int i = 0; i<size;i++) {						// των ατόμων στην ουρά
				max = max+people.get(i).getTime();
			}
		} else if (choice==1){
			for (int i = 0; i<size;i++) {
				max = max+people.get(i).getRealTime();
			}
		}
		return max;
	}
	
	public synchronized int getSize(){
		return people.size();
	}
}