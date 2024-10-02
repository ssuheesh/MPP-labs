package prod3;

public class Purchase {
	private int id;
	private double amount;

	public Purchase(int id, double amount) {
		this.amount = amount;
		this.id = id;
	}

	public String toString() {
		return "id : " + id + " amount : " + amount;
	}

}
