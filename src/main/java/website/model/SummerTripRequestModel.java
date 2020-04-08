package website.model;

import java.util.LinkedHashMap;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import website.validation.ValidEmail;

public class SummerTripRequestModel {

	private LinkedHashMap<String, String> shirtSizes;

	public SummerTripRequestModel() {

		shirtSizes = new LinkedHashMap<>();
		shirtSizes.put("S", "S");
		shirtSizes.put("M", "M");
		shirtSizes.put("L", "L");
		shirtSizes.put("XL", "XL");
	}

	@NotNull(message = "is required")
	@Size(min = 2, message = " is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 2, message = " is required")
	private String lastName;

	@NotNull(message = "is required")
	@ValidEmail
	private String email;

	@NotNull(message = "is required")
	@Pattern(regexp = "\\d{6}", message = "Enter an index number")
	private String indexNumber;

	@NotNull(message = "is required")
	private String shirtSize;

	@NotNull(message = "is required")
	private String transportOption;

	public LinkedHashMap<String, String> getShirtSizes() {
		return shirtSizes;
	}

	public void setShirtSizes(LinkedHashMap<String, String> shirtSizes) {
		this.shirtSizes = shirtSizes;
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

}
