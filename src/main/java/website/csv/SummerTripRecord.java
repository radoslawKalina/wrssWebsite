package website.csv;

public class SummerTripRecord {
	
    private String firstName;
    private String lastName;
    private String email;
    private String indexNumber;
    private String transportOption;
    private String paid;
    
    public SummerTripRecord() {
    	
    }

	public SummerTripRecord(String firstName, String lastName, String email, String indexNumber, String transportOption,
			String paid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.indexNumber = indexNumber;
		this.transportOption = transportOption;
		this.paid = paid;
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

	public String getTransportOption() {
		return transportOption;
	}

	public void setTransportOption(String transportOption) {
		this.transportOption = transportOption;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}
    
    

}
