package Prob2;

public class EquilateralTriangle implements Polygon {
	private double side;
	public EquilateralTriangle(double side) {
		this.side = side;
	}
	public double getSide() {
		return side;
	}
	public void setSide(double side) {
		this.side = side;
	}

	@Override
	public double[] getSides() {
		return new double[]{side, side, side};
	}

	@Override
	public double computePerimeter() {
		return Polygon.super.computePerimeter();
	}
}
