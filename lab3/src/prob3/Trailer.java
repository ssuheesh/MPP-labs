package lab3_mpp;

public class Trailer extends Property {

	private static final double RENT = 500;

	public Trailer(Address address) {
		super(address);
	}

	@Override
	public double computeRent() {
		return RENT;
	}

}
