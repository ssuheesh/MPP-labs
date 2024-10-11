package Problem4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Human
{
	String name;
	int age;
	String gender;
	
	public Human(String name){
		this.name = name;
	}
	public Human(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public Human(String name,int age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}
 

public class ConstructorReference {
public static void main(String args[]){
	Human[] list = { new Human("Joe",35,"Male"), new Human("Jane",45,"Female"), new Human("John",30,"Male")};
	
    // Query 1  : Print only Female candidates names
	Arrays.asList(list).stream()
	.filter((Human x)-> x.getGender().equals("Female"))
	.map((Human x)->x.getName())
	//.forEach(Human::toString);
	.forEach(System.out::println); 
	
    /* Query 2 : Create an object for the Type of Human class by choosing suitable Interface for the three  constructors using ClassName::new. 
                 Then print the object status */
	
	Function<String,Human> human1=Human::new;
	BiFunction<String,Integer,Human> human2 = Human::new;
	TriFunction<String,Integer,String,Human> human3=Human::new;
	
	Human one=human1.apply("Jack");
	Human two=human2.apply("Jim",39);
	Human three=human3.apply("James", 80, "Male");
  
	System.out.println(one.toString());
	System.out.println(two.toString());
	System.out.println(three.toString());
 
	// Query 3 : Count the male candidates whose age is more than 30
	System.out.println(Arrays.asList(list).stream()
	.filter((Human x)-> x.getGender().equals("Male"))
	.filter((Human x)->x.getAge()>30)
	//.map((Human x)->x.getName())
	.count());
    
   }



}
