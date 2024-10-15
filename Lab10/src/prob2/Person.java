package prob2;

public class Person {
	String name;
	public Person(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Person)) return false;
		Person p = (Person)o;
		return name.equals(p.name);
	}
}
