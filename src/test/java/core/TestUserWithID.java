package core;

import javax.persistence.Id;

public class TestUserWithID {

	@Id
	int id;
	String firstname;
	String lastname;

	/**
	 * 
	 */
	public TestUserWithID() {
		super();
	}

	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 */
	public TestUserWithID(int id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
