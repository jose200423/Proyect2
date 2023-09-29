package co.edu.unbosque.model;

import java.util.Date;

public class PersonDTO {
	protected String name;
	private long identificationNumber;
	private Date birthday;
	private String cityOfBorn;

	public PersonDTO() {
		// TODO Auto-generated constructor stub
	}

	public PersonDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super();
		this.name = name;
		this.identificationNumber = identificationNumber;
		this.birthday = birthday;
		this.cityOfBorn = cityOfBorn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Date getbirthday() {
		return birthday;
	}

	public void setbirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCityOfBorn() {
		return cityOfBorn;
	}

	public void setCityOfBorn(String cityOfBorn) {
		this.cityOfBorn = cityOfBorn;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("The name of this person is: " + getName() + "\n");
		sb.append("The identification number is: " + getIdentificationNumber() + "\n");
		sb.append("The Birthday is: " + getbirthday() + "\n");
		sb.append("It was borned on: " + getCityOfBorn() + "\n");
		return sb.toString();
	}

}
