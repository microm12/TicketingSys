package dai16014;

public class Counter {
	
	private int value;							// κλάση synchronized μετρητή
	
	public Counter(int c) {
		this.value = c;
	}

	public synchronized int add() {
		value++;
		return value;
	}
	
	public synchronized int getCounter() {
		return value;
	}
}