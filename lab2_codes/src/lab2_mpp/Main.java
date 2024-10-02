package lab2_mpp;

public class Main {

	public static void main(String[] args) {
		Customer c1 = new Customer("Luna");
		LoyaltyCard l1 = LoyaltyCard.newLoyaltyCard(c1);
		l1.addPurchase(new Purchase(1, 1000));
		l1.addPurchase(new Purchase(2, 2000));
		l1.addPurchase(new Purchase(3, 3000));
		for (Purchase p : l1.getPurchases())
			System.out.println(p.toString());
	}

}
