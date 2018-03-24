package dai16014;

import java.util.concurrent.ThreadLocalRandom;

public class Entrance extends Thread{
	private int id;								// μεταβλητή με το όνομα της εισόδου
	private Queue ppl;							// η ουρά με τους πελάτες
	private Counter count;				        // μετρητής τιμής εισητηρίου - δείκτης της ουράς (synchronized)

	public Entrance(int id, Queue people, Counter c){
		this.id = id;
		ppl = people;
		count = c;
	}
	
	public void run() {
		int randomNum;
		for (int i=0;i<Main.B;i++) {							// όριο πελατών ανά είσοδο
			randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
			try {
				Thread.sleep(randomNum);						// τυχαία χρονική καθυστέρηση μεταξύ της εισόδου κάθε ατόμου	
			} catch (InterruptedException e) {
				System.err.println("this should not happen");
			} 
			Person person = new Person();						// δημιουργία πελάτη
			System.out.println("Gate: " + id);					// εκτύπωση εισόδου
			while(!ppl.add(person))	{							// πρόσθεση ατόμου στην ουρά
				try {
					Thread.sleep(5);							// όσο ουρά γεμάτη περίμενε για να μπεις
				} catch (InterruptedException e) {
					System.err.println("this should not happen");
				}
			}
			person.setTicketNo(count.add());					// έκδοση εισητηρίου
			System.out.println("          Gate: " + id+" Ticket: "+ (person.getTicketNo()+1) + " Time: " + ppl.calcAvg(0, count.getCounter()));  	// εκτύπωση εισητηρίου
		}
	}
}