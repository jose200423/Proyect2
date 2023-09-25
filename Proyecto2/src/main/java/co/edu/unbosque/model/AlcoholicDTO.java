package co.edu.unbosque.model;

import java.util.Date;

public class AlcoholicDTO extends PersonDTO {
	private int sessionsPresent;
	private String nickname;

	public AlcoholicDTO() {
	}

	public AlcoholicDTO(int sessionsPresent, String nickname) {
		super();
		this.sessionsPresent = sessionsPresent;
		this.nickname = nickname;
	}

	public AlcoholicDTO(String name, long identificationNumber, Date birthday, String cityOfBorn, int sessionsPresent,
			String nickname) {
		super(name, identificationNumber, birthday, cityOfBorn);
		this.sessionsPresent = sessionsPresent;
		this.nickname = nickname;
	}

	public AlcoholicDTO(String name, long identificationNumber, Date birthday, String cityOfBorn) {
		super(name, identificationNumber, birthday, cityOfBorn);
	}

	public int getSessionsPresent() {
		return sessionsPresent;
	}

	public void setSessionsPresent(int sessionsPresent) {
		this.sessionsPresent = sessionsPresent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Person has been in: " + getSessionsPresent() + "sessions \n");
		sb.append("Nickname of this person is: " + getNickname() + "\n");
		return sb.toString();
	}

}
