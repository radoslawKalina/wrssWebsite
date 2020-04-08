package website.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.SummerTripDao;
import website.dto.SummerTripDto;
import website.dto.UserDto;
import website.entity.SummerTripEntity;

@Service
public class SummerTripServiceImpl implements SummerTripService {

	@Autowired
	private SummerTripDao summerTripDao;

	@Transactional
	public List<SummerTripDto> getAllRecords() {

		List<SummerTripEntity> summerTripEntityList = summerTripDao.getAllRecords();
		List<SummerTripDto> allRecords = new ArrayList<>();

		for (SummerTripEntity temp : summerTripEntityList) {

			allRecords.add(new ModelMapper().map(temp, SummerTripDto.class));
		}

		return allRecords;
	}

	@Transactional
	public List<SummerTripDto> getPaidRecords() {

		List<SummerTripEntity> summerTripEntityList = summerTripDao.getPaidRecords();
		List<SummerTripDto> allRecords = new ArrayList<>();

		for (SummerTripEntity temp : summerTripEntityList) {

			allRecords.add(new ModelMapper().map(temp, SummerTripDto.class));
		}

		return allRecords;
	}

	@Transactional
	public List<SummerTripDto> getUserRecords() {

		List<SummerTripEntity> userRecords = summerTripDao.getUserRecords();
		List<SummerTripDto> userRecordsDto = new ArrayList<>();

		for (SummerTripEntity temp : userRecords) {

			userRecordsDto.add(new ModelMapper().map(temp, SummerTripDto.class));
		}

		return userRecordsDto;
	}

	@Transactional
	public SummerTripDto getRecord(int id) {

		SummerTripEntity summerTripEntity = summerTripDao.getRecord(id);

		return new ModelMapper().map(summerTripEntity, SummerTripDto.class);
	}

	@Transactional
	public void addRecord(SummerTripDto summerTripDto) {

		SummerTripEntity summerTripEntity = new ModelMapper().map(summerTripDto, SummerTripEntity.class);

		summerTripDao.addRecord(summerTripEntity);
	}

	@Transactional
	public void updateRecord(SummerTripDto summerTripDto, int update) {

		SummerTripEntity summerTripEntity = new ModelMapper().map(summerTripDto, SummerTripEntity.class);
		summerTripEntity.setId(update);

		summerTripDao.updateRecord(summerTripEntity, update);
	}

	@Transactional
	public void updateRecord(SummerTripDto summerTripDto, int update, UserDto userDto) {

		SummerTripEntity summerTripEntity = new ModelMapper().map(summerTripDto, SummerTripEntity.class);
		summerTripEntity.setId(update);

		summerTripDao.updateRecord(summerTripEntity, update, userDto.getId());
	}

	@Transactional
	public void deleteRecord(int id) {

		summerTripDao.deleteRecord(id);

	}

}
