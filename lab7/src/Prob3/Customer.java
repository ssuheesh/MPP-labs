package Prob3;

public class Customer implements CustomerInterface {
	private String customerName;
	private int loyaltyPoints;

	Customer(String customerName, int loyaltyPoints) {
		this.customerName = customerName;
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

}
