package Prob3;

public class Restaurant implements RestaurantInterface {
	private String restaurantName;
	private double baseDeliverCharge;
	private double locationDistance;

	Restaurant(String restaurantName, double baseDeliverCharge, double locationDistance) {
		this.restaurantName = restaurantName;
		this.baseDeliverCharge = baseDeliverCharge;
		this.locationDistance = locationDistance;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public double getLocationDistance() {
		return locationDistance;
	}

	@Override
	public double calculateDeliveryCharge() {
		return baseDeliverCharge + (locationDistance * 0.05);
	}
}
