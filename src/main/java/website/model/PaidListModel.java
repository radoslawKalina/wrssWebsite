package website.model;

import java.util.List;

import website.dto.PaidDto;

public class PaidListModel {

	private List<PaidDto> paidList;

	public PaidListModel(List<PaidDto> paidList) {
		this.paidList = paidList;
	}

	public List<PaidDto> getPaidList() {
		return paidList;
	}

	public void setPaidList(List<PaidDto> paidList) {
		this.paidList = paidList;
	}

}
