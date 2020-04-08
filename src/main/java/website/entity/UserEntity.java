package website.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "encryptedPassword")
	private String encryptedPassword;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_id")
	private List<UserRoleEntity> userRoles;

	public void add(UserRoleEntity userRole) {
		if (userRoles == null) {
			userRoles = new ArrayList<>();
		}
		userRoles.add(userRole);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<SummerTripEntity> summerTripList;

	public UserEntity() {

	}

	public UserEntity(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.encryptedPassword = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	public List<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public String[] getUserRolesString() {

		List<String> roles = new ArrayList<>();

		for (UserRoleEntity role : userRoles) {
			roles.add(role.getRole());
		}

		return roles.stream().toArray(String[]::new);
	}

	public void setUserRoles(List<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public List<SummerTripEntity> getSummerTripList() {
		return summerTripList;
	}

	public void setSummerTripList(List<SummerTripEntity> summerTripList) {
		this.summerTripList = summerTripList;
	}

}
