package Problem9;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Optional<Dish> isVegetarian = Dish.menu.stream().filter(Dish::isVegetarian).findAny();
		System.out.println(isVegetarian.isPresent());
		// isVegetarian.ifPresentOrElse(x -> System.out.println(x.isVegetarian()), () ->
		// System.out.println(false));

		Optional<Dish> isHealthy = Dish.menu.stream().filter(x -> x.getCalories() < 1000).findFirst();
		System.out.println(isHealthy.isPresent());

		Optional<Dish> isUnHealthy = Dish.menu.stream().filter(x -> x.getCalories() > 1000).findFirst();
		System.out.println(isUnHealthy.isPresent());

		Optional<Dish> meat = Dish.menu.stream().filter(x -> x.getType() == Dish.Type.MEAT).findFirst();
		meat.ifPresentOrElse(System.out::println,()-> System.out.print("No found"));
		
		/*e. calculateTotalCalories() in the menu using reduce. (return int)*/
		
		/*f. calculateTotalCaloriesMethodReference()in the menu using MethodReferences. (return int)*/
		
	}

}
