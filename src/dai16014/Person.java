package dai16014;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Person {
	private int ticketNo;		// αριθμός εισητηρίου
	private long expTime;		// αναμενόμενος χρόνος 
	private long realTime;		// πραγματικός χρόνος 
	private Random r;			// γενήτρια τυχαίων αριθμών 
	
	Person(){
		this.r = new Random();
		this.ticketNo = -1;
		this.expTime = ThreadLocalRandom.current().nextInt(2, 10 + 1);		// εκχώρηση διαφορετικών
		this.realTime = r.nextInt(8) + 2;									// τυχαίων τιμών real/expected time
	}
	
	public synchronized void setTicketNo(int num) {
		ticketNo = num;
	}

	public synchronized int getTicketNo() {
		return ticketNo;
	}
	
	public synchronized long getTime() {
		return expTime;
	}
	
	public synchronized void setRealTime(int realTime) {
		this.realTime = realTime;
	}

	public synchronized long getRealTime() {
		return realTime;
	}
}