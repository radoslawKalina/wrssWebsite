package website.service;

import java.util.List;

import website.dto.PaidDto;

public interface PaidService {
	
	List<PaidDto> getAllRecords();
	PaidDto getRecord(int id);
	void updateRecord(PaidDto paid);

}
