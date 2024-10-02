package lab3_mpp;

public class Admin {
	public static double computeTotalRent(Property[] properties) {
		double totalRent = 0;
		for (Property o : properties) {
			totalRent += o.computeRent();
		}
		return totalRent;
	}
}
