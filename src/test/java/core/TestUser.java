package core;

public class TestUser {

	String firstname;
	String lastname;

	/**
	 * 
	 */
	public TestUser() {
		super();
	}

	/**
	 * @param firstname
	 * @param lastname
	 */
	public TestUser(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
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
