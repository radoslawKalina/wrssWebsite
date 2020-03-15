package website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.PaidDao;
import website.entity.Paid;

@Service
public class PaidService {

	@Autowired
	private PaidDao paidDao;
	
	@Transactional
	public List<Paid> getAllRecords() {
		
		return paidDao.getAllRecords();
	}

	@Transactional
	public Paid getRecord(int id) {
	
		Paid paid = paidDao.getRecord(id);
		
		return paid;
	}
	
	@Transactional
	public void update(Paid paid) {
	
		paidDao.update(paid);
	}
	
}
