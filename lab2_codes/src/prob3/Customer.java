package prob3;

public class Customer {
	private String name;
	private LoyaltyCard card;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCard(LoyaltyCard card) {
		this.card = card;
	}

	public LoyaltyCard getCard() {
		return card;
	}
}
