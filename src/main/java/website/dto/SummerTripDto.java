package website.dto;

public class SummerTripDto {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String indexNumber;
	private String shirtSize;
	private String transportOption;
	private UserDto user;
	private PaidDto paid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public String getTransportOption() {
		return transportOption;
	}

	public void setTransportOption(String transportOption) {
		this.transportOption = transportOption;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public PaidDto getPaid() {
		return paid;
	}

	public void setPaid(PaidDto paid) {
		this.paid = paid;
	}

}
