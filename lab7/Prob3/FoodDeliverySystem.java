package Prob3;

import java.util.*;

public class FoodDeliverySystem {

	public static void main(String[] args) {
		// Create an ArrayList of Restaurants
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(new Restaurant("Sushi Palace", 5.0, 10.0));
		restaurants.add(new Restaurant("Pizza Corner", 3.5, 5.0));
		restaurants.add(new Restaurant("Burger World", 4.0, 7.5));
		// Create an ArrayList of Customers
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(new Customer("John Doe", 60));
		customers.add(new Customer("Jane Smith", 40));
		customers.add(new Customer("Bob Johnson", 80));
		// Print welcome message
		System.out.println("Welcome to the Food Delivery System!");
		System.out.println("");
		for (Restaurant restaurant : restaurants) {
			for (Customer customer : customers) {
				System.out.println("Processing order for Customer: " + customer.getCustomerName() + " at Restaurant: "
						+ restaurant.getRestaurantName());

				String customerLoyaltyStatus = "New Customer";
				if (customer.isLayaltyCustomer(customer.getLoyaltyPoints())) {
					customerLoyaltyStatus = "Loyal Customer";
				}
				System.out.println("Customer Loyalty Status: " + customerLoyaltyStatus);

				double orderAmount = random();
				double deliveryCharge = restaurant.calculateDeliveryCharge();
				double totalOrderAmount = deliveryCharge + orderAmount;
				double discount = restaurant.caluclateDiscount(orderAmount); // discount is only applicable for order
																				// amount without delivery charge
				// double discount = restaurant.caluclateDiscount(totalOrderAmount); // discount
				// is applicable including delivery charge
				double totalAfterDiscount = totalOrderAmount - discount;
				RestaurantInterface.printFormattedData(restaurant.getRestaurantName(), customer.getCustomerName(),
						orderAmount, deliveryCharge, discount, totalAfterDiscount);

				System.out.println("");
			}
		}
	}

	private static double random() {
		Random r = new Random();
		double low = 50;
		double high = 200;
		double result = r.nextDouble(high - low) + low;
		return result;
	}
}
