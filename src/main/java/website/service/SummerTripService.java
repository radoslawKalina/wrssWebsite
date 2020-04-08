package website.service;

import java.util.List;

import website.dto.SummerTripDto;
import website.dto.UserDto;

public interface SummerTripService {
	
	List<SummerTripDto> getAllRecords();
	List<SummerTripDto> getPaidRecords();
	List<SummerTripDto> getUserRecords();
	SummerTripDto getRecord(int id);
	void addRecord(SummerTripDto summerTripDto);
	void updateRecord(SummerTripDto summerTripDto, int update);
	void updateRecord(SummerTripDto summerTripDto, int update, UserDto userDto);
	void deleteRecord(int id);

}
