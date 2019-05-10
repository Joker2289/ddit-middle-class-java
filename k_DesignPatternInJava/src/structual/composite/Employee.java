package structual.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String name;						//직원명
	private String dept;						//부서명
	private int salary;							//봉급
	private List<Employee> subordinates;		//부하직원

	// constructor
	public Employee(String name, String dept, int sal) {
		this.name = name;
		this.dept = dept;
		this.salary = sal;
		subordinates = new ArrayList<Employee>();
	}

	public void add(Employee e) {
		subordinates.add(e);
	}

	public void remove(Employee e) {
		subordinates.remove(e);
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public String toString() {
		return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary + " ]");
	}
}
