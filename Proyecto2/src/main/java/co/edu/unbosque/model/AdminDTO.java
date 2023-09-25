package co.edu.unbosque.model;

import java.util.Date;

public class AdminDTO extends PersonDTO {
	public AdminDTO() {
	}

	public AdminDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

}
