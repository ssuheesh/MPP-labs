package Problem2;

import java.util.Comparator;
import java.util.List;

public class Product {
	final String title;
	final double price;
	final int model;
	public enum SortMethod {BYTITLE, BYPRICE}

	public static void mySort(List<Product> productList, SortMethod method){
		//Local inner class
		class MyComparator implements Comparator<Product> {
			@Override
			public int compare(Product o1, Product o2) {
				if(method == SortMethod.BYTITLE){
					return o1.getTitle().compareTo(o2.getTitle());
				}
				else{
					return Double.compare(o1.getPrice(), o2.getPrice());
				}
			}
		}
		productList.sort(new MyComparator());
	}
	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getModel() {
		return model;
	}

	public Product(String title, Double price, int model) {
		this.title = title;
		this.price = price;
		this.model = model;
	}

	@Override
	public String toString() {
		return String.format("\n %s : %s : %s", title, price, model);
	}

}
