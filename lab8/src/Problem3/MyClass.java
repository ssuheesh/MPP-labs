package Problem3;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MyClass {
	int x;
	String y;

	public MyClass(int x, String y) {
		this.x = x;
		this.y = y;
	}

	// testing method to check the equality using Lambda and Method reference
	public void myMethod(MyClass cl) {
		BiFunction<MyClass, MyClass, Boolean> a1 = (b, c) -> b.equals(c);
		System.out.println("Lambda :" + a1.apply(this, cl));

		BiFunction<MyClass, MyClass, Boolean> a2 = MyClass::equals;
		System.out.println("Method reference :" + a2.apply(this, cl));

	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		MyClass mc = (MyClass) ob;
		return mc.x == x && mc.y.equals(y);
	}

	public static void main(String[] args) {
		MyClass myclass = new MyClass(1, "A");
		MyClass myclass1 = new MyClass(1, "B");
		myclass.myMethod(myclass); // print true
		myclass.myMethod(myclass1); // print false
	}
}