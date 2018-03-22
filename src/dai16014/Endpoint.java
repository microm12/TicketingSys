package dai16014;

public class Endpoint extends Thread{
	private int id;								// μεταβλητή με το όνομα του σημείου εξηπυρέτησης
	private Queue ppl;							// ουρά ατόμων
	private Counter total;						// τέλος ουράς
	private Counter count;						// αρχή ουράς
	
	public Endpoint(int id, Queue ppl, Counter total, Counter counter) {
		this.id = id;
		this.ppl = ppl;
		this.total = total;
		this.count = counter;
	}
	
	public void run() {
		while(count.getCounter() < total.getCounter()) {	// αναγκαία συνθήκη 
			Person person = ppl.get(count.add());			// αφαίρεση ατόμου από την ουρά
			System.out.println("                                        Endpoint: "+ id + " Number: " + (person.getTicketNo() + 1));		//εκτύπωση ανάθεσης ατόμου σε σημείο εξυπ.
			try {
				Thread.sleep(person.getRealTime());			// καθυστέρηση εξυπηρέτησης ατόμου
			} catch (InterruptedException e) {
				System.err.println("this should not happen");
			}
			System.out.println("                                                                Endpoint: "+ id + " Number: " + (person.getTicketNo() + 1)+" Time: " + ppl.calcAvg(1, count.getCounter()));		//εκτύπωση τέλους εξηπυρέτησης
		}
	}
}