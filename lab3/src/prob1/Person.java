package prob1;

public class Person {

	private String name;
	private PersonWithJob pj; // PersonWithJob as in composition

	Person(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		if (pj == null)
			pj = new PersonWithJob();// to call default constructor, so it will not call again Person constructor
		return pj.getSalary(); // to access the methods under PersonWithJob
	}

	public void setSalary(double salary) {
		if (pj == null)
			pj = new PersonWithJob();// to call default constructor, so it will not call again Person constructor
		pj.setSalary(salary); // to access the methods under PersonWithJob
	}

	@Override
	public boolean equals(Object aPerson) {
		if (aPerson == null)
			return false;
		// if(!(aPerson instanceof Person)) return false;

		boolean isEqual = false;
		if (aPerson instanceof PersonWithJob) {
			PersonWithJob p = (PersonWithJob) aPerson;
			isEqual = this.name.equals(p.getName());
		} else if (aPerson instanceof Person) {
			Person p = (Person) aPerson;
			isEqual = this.name.equals(p.getName());
		}
		return isEqual;
	}

	public static void main(String[] args) {

	}

}
