package website.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="paid")
public class Paid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="paid")
	private String paid;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="summer_trip_id")
	private SummerTrip summerTrip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public Paid() {
		
	}

	public Paid(String paid) {
		this.paid = paid;
	}

	public SummerTrip getSummerTrip() {
		return summerTrip;
	}

	public void setSummerTrip(SummerTrip summerTrip) {
		this.summerTrip = summerTrip;
	}
	
	
	
	

} 
