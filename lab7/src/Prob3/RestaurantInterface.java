package Prob3;

public interface RestaurantInterface {
	public String getRestaurantName();

	public double calculateDeliveryCharge();

	public static void printFormattedData(String restaurantName, String customerName, double orderAmount,
			double deliveryCharge, double discount, double totalAfterDiscount) {

		// System.out.println("Processing order for Customer: " + customerName + "at
		// Restaurant: " + restaurantName);

		System.out.println("Restaurant: " + restaurantName);
		System.out.println("Customer: " + customerName);
		System.out.println(String.format("Order Amount: $%.2f", orderAmount));
		System.out.println(String.format("Delivery Charge: $%.2f", deliveryCharge));
		System.out.println(String.format("Discount Applied: $%.2f", discount));
		System.out.println(String.format("Total after Discount: $%.2f", totalAfterDiscount));
	}

	default double caluclateDiscount(double totalOrderAmount) {
		if (totalOrderAmount > 100)
			return totalOrderAmount * 0.05;
		return 0.0;
	}
}
