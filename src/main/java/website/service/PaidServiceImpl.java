package website.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.PaidDao;
import website.dto.PaidDto;
import website.entity.PaidEntity;

@Service
public class PaidServiceImpl implements PaidService {

	@Autowired
	private PaidDao paidDao;
	
	@Transactional
	public List<PaidDto> getAllRecords() {
		
		List<PaidEntity> paidEntityList = paidDao.getAllRecords();
		List<PaidDto> paidDtoList = new ArrayList<>();
		
		for (PaidEntity temp: paidEntityList) {
			
			paidDtoList.add(new ModelMapper().map(temp, PaidDto.class));
		}
		
		return paidDtoList;
	}

	@Transactional
	public PaidDto getRecord(int id) {
	
		PaidEntity paidEntity = paidDao.getRecord(id);
		
		return new ModelMapper().map(paidEntity, PaidDto.class);
	}
	
	
	@Transactional
	public void updateRecord(PaidDto paid) {
	
		PaidEntity paidEntity = new ModelMapper().map(paid, PaidEntity.class);
		
		paidDao.update(paidEntity);
	}
	
}
