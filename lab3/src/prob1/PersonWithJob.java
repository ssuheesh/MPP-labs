package prob1;

public class PersonWithJob {

	private double salary;
	private Person person; // Person as in composition

	public double getSalary() {
		return salary;
	}

	PersonWithJob(String n, double s) {
		person = new Person(n); // it is also create Person
		salary = s;
	} 
	public String getName() {
		return person.getName(); /* to access the methods under Person */
	}

	//public void setSalary(double salary) {
	//	this.salary = salary;
	//}

	//@Override
	public boolean equals(Object aPersonWithJob) {
		if (aPersonWithJob == null)
			return false;
		// if(!(aPersonWithJob instanceof PersonWithJob)) return false;//p2 is Person

		if (aPersonWithJob instanceof Person) {
			Person p = (Person) aPersonWithJob;
			//PersonWithJob pj = new PersonWithJob(p.getName(),0.00);
			//pj.setSalary(30000);
			return this.getName().equals(p.getName());
		}
		else if(aPersonWithJob instanceof PersonWithJob)
		{
			PersonWithJob p = (PersonWithJob) aPersonWithJob; 
			return this.getName().equals(p.getName()) && this.getSalary() == p.getSalary();
		}
		return false;
	}

	public static void main(String[] args) {
		PersonWithJob p1 = new PersonWithJob("Joe", 30000);
		Person p2 = new Person("Joe");
		// As PersonsWithJobs, p1 should be equal to p2
		System.out.println("p1.equals(p2)? " + p1.equals(p2));
		System.out.println("p2.equals(p1)? " + p2.equals(p1));
	}

}
