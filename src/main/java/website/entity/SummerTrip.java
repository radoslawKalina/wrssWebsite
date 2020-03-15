package website.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="summer_trip")
public class SummerTrip {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="number")
	private String indexNumber;
	
	@Column(name="shirt")
	private String shirtSize;
	
	@Column(name="transport")
	private String transportOption;

	@ManyToOne(fetch=FetchType.LAZY, 
			cascade= { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(mappedBy="summerTrip", cascade=CascadeType.ALL)
	private Paid paid;
	
	public SummerTrip() {
		
	}

	public SummerTrip(String firstName, String lastName, String email, String indexNumber, String shirtSize,
			String transportOption) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.indexNumber = indexNumber;
		this.shirtSize = shirtSize;
		this.transportOption = transportOption;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Paid getPaid() {
		return paid;
	}

	public void setPaid(Paid paid) {
		this.paid = paid;
	}
	
	
}
