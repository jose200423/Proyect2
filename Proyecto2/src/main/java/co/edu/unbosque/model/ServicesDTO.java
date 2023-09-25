package co.edu.unbosque.model;

import java.util.Date;

public class ServicesDTO extends PersonDTO {
	private int salary;
	private int sessionsCleaned;

	public ServicesDTO() {
	}

	public ServicesDTO(int salary, int sessionsCleaned) {
		super();
		this.salary = salary;
		this.sessionsCleaned = sessionsCleaned;
	}

	public ServicesDTO(String name, long identificationNumber, Date birthday, String cityOfBorn, int salary,
			int sessionsCleaned) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.salary = salary;
		this.sessionsCleaned = sessionsCleaned;
	}

	public ServicesDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getSessionsCleaned() {
		return sessionsCleaned;
	}

	public void setSessionsCleaned(int sessionsCleaned) {
		this.sessionsCleaned = sessionsCleaned;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("The salary is: " + getSalary() + "\n");
		sb.append("Number of sessions cleaned: " + getSessionsCleaned() + "\n");
		return sb.toString();
	}

}
