package dai16014;

public class Main {
	public static final int A = 6;                                  // Σταθερές προβλήματος
	public static final int B = 10;									// Α = Είσοδοι, Β = Πελάτες/Είσοδο
	public static final int C = 4;									// C = Σημεία Εξυπηρέτησης, D = Χωρητικότητα Ουράς
	public static final int D = 20;
	
	public static void main(String[] args) {
		Entrance[] entrances = new Entrance[A];						// Αρχικοποίηση μεταβλητών
		Endpoint[] endpoints = new Endpoint[C];
		Counter total = new Counter(-1);
		Counter count = new Counter(-1);
		Queue q = new Queue(count);
		
		for (int i=0; i<A; i++) {
			entrances[i] = new Entrance(i, q ,total, count);		// Δημιουργία εισόδων - threads
			entrances[i].start();
		}
		
		 try {
			Thread.sleep(20);										// Προσθήκη μικρής καθυστέρησης προκειμένου να 
		} catch (InterruptedException e) {							// γεμίσει η ουρά με μερικούς αρχικούς πελάτες
			System.err.println("this should not happen");
		} 
		
		for (int j=0; j<C; j++) {
			endpoints[j] = new Endpoint(j, q, total, count);		// Δημιουργία σημείων εξυπηρέτησης - threads
			endpoints[j].start();
		}
		
		
	}
}