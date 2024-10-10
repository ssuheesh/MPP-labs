package Prob2;

public class Ellipse implements ClosedCurve {
	private double a;
	private double e;
	public Ellipse(double a, double e) {
		this.a = a;
		this.e = e;
	}
	public double getA() {
		return a;
	}
	public double getE() {
		return e;
	}
	public void setA(double a) {
		this.a = a;
	}

	public void setE(double e) {
		this.e = e;
	}

	@Override
	public double computePerimeter() {
		return 4 * a * e;
	}
}
