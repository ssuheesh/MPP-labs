import prod3.Customer;
import prod3.LoyaltyCard;
import prod3.Purchase;

public class Main3 {

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
