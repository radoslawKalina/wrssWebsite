package website.dto;

public class PaidDto {

	private int id;
	private String paid;
	private SummerTripDto summerTrip;

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

	public SummerTripDto getSummerTrip() {
		return summerTrip;
	}

	public void setSummerTrip(SummerTripDto summerTrip) {
		this.summerTrip = summerTrip;
	}

}
