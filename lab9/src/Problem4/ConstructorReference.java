package Problem4;

import java.util.Arrays;
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
	Human[] list = {
			new Human("Joe",35,"Male"),
			new Human("Jane",45,"Female"),
			new Human("John",30,"Male")
	};
	
    // Query 1  : Print only Female candidates names
	Stream.of(list).filter(x->x.getGender().equals("Female")).map(Human::getName).forEach(System.out::println);

    /* Query 2 : Create an object for the Type of Human class by choosing suitable Interface for the three  constructors using ClassName::new. 
                 Then print the object status */
	TriFunction<String, Integer, String, Human> func = Human::new;
	System.out.println(func.apply("Joana",32,"Female"));

	// Query 3 : Count the male candidates whose age is more than 30
       long c = Stream.of(list).filter(x->x.getAge() >= 30)
			   .filter(x->x.getGender().equals("Male"))
			   .count();
		System.out.println(c);
    
   }



}
