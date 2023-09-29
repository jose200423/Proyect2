package co.edu.unbosque.model;

import java.util.Date;

public class PsychologistDTO extends PersonDTO {
	private int graduationYear;
	private int daysSinceGraduation;
	private int supportedSessions;
	private int salary;

	public PsychologistDTO() {
	}

	public PsychologistDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}

	public PsychologistDTO(String name, long identificationNumber, Date birthday, String cityOfBorn, int graduationYear,
			int daysSinceGraduation, int supportedSessions, int salary) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.graduationYear = graduationYear;
		this.daysSinceGraduation = daysSinceGraduation;
		this.supportedSessions = supportedSessions;
		this.salary = salary;
	}

	public PsychologistDTO(int graduationYear, int daysSinceGraduation, int supportedSessions, int salary) {
		super();
		this.graduationYear = graduationYear;
		this.daysSinceGraduation = daysSinceGraduation;
		this.supportedSessions = supportedSessions;
		this.salary = salary;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public int getDaysSinceGraduation() {
		return daysSinceGraduation;
	}

	public void setDaysSinceGraduation(int daysSinceGraduation) {
		this.daysSinceGraduation = daysSinceGraduation;
	}

	public int getSupportedSessions() {
		return supportedSessions;
	}

	public void setSupportedSessions(int supportedSessions) {
		this.supportedSessions = supportedSessions;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Graduation's year: " + getGraduationYear() + "\n");
		sb.append("Days since graduation: " + getDaysSinceGraduation() + "\n");
		sb.append("Number of sessions supported: " + getSupportedSessions() + "\n");
		sb.append("Salary is : " + getSalary() + "\n");
		return sb.toString();
	}
}
