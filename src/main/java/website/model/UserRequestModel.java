package website.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import website.validation.PasswordMatch;
import website.validation.UserExist;
import website.validation.ValidEmail;

@PasswordMatch(first="password", second="confirmPassword")
public class UserRequestModel {
	
	@NotNull(message=" is required")
	@ValidEmail
	@UserExist
	private String email;

	@NotNull(message=" is required")
	@Size(min=2, message=" is required")
	private String firstName;
	
	@NotNull(message=" is required")
	@Size(min=2, message=" is required")
	private String lastName;
	
	@NotNull(message=" is required")
	@Size(min=6, message="Password must have 6 or more characters.")
	private String password;
	
	@NotNull(message=" is required")
	private String confirmPassword;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}	
	
}
