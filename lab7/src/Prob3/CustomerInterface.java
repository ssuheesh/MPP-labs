package Prob3;

public interface CustomerInterface {
	default boolean isLayaltyCustomer(int loyaltypoints) {
		return loyaltypoints > 50;
	}
	public String getCustomerName();
	public int getLoyaltyPoints();
}
