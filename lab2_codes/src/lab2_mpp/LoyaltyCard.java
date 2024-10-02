package lab2_mpp;

import java.util.ArrayList;
import java.util.List;

public class LoyaltyCard {
	private List<Purchase> purchases;

	public void addPurchase(Purchase purchase) {
		purchases.add(purchase);
	}

	/** package level constructor */
	private LoyaltyCard(Customer cust) {
		purchases = new ArrayList<Purchase>();
		cust.setCard(this);
	}

	public static LoyaltyCard newLoyaltyCard(Customer cust) {
		if (cust == null) {
			throw new NullPointerException("Null customer");
		}
		return new LoyaltyCard(cust);
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}
}
