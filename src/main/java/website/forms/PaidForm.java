package website.forms;

import java.util.List;

import website.entity.Paid;

public class PaidForm {
	
	private List<Paid> paidList;
	
	public PaidForm(List<Paid> paidList) {
		this.paidList = paidList;
	}

	public List<Paid> getPaidList() {
		return paidList;
	}

	public void setPaidList(List<Paid> paidList) {
		this.paidList = paidList;
	}
	
	

}
