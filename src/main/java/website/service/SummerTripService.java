package website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.SummerTripDao;
import website.entity.SummerTrip;
import website.entity.User;
import website.forms.SummerTripValidation;

@Service
public class SummerTripService {

	@Autowired
	private SummerTripDao summerTripDao;
	
	public SummerTripValidation changeEntityToValidation(SummerTrip summerTrip) {
		
		SummerTripValidation summerTripValidation = new SummerTripValidation();
		
		summerTripValidation.setFirstName(summerTrip.getFirstName());
		summerTripValidation.setLastName(summerTrip.getLastName());
		summerTripValidation.setEmail(summerTrip.getEmail());
		summerTripValidation.setIndexNumber(summerTrip.getIndexNumber());
		summerTripValidation.setShirtSize(summerTrip.getShirtSize());
		summerTripValidation.setTransportOption(summerTrip.getTransportOption());
		
		return summerTripValidation;
	}
	
	public SummerTrip changeValidationToEntity(SummerTripValidation summerTripValidation) {
		
		SummerTrip summerTrip = new SummerTrip();
		
		summerTrip.setFirstName(summerTripValidation.getFirstName());
		summerTrip.setLastName(summerTripValidation.getLastName());
		summerTrip.setEmail(summerTripValidation.getEmail());
		summerTrip.setIndexNumber(summerTripValidation.getIndexNumber());
		summerTrip.setShirtSize(summerTripValidation.getShirtSize());
		summerTrip.setTransportOption(summerTripValidation.getTransportOption());
		
		return summerTrip;
	}

	
	@Transactional
	public void addRecord(SummerTripValidation summerTripValidation) {
		
		SummerTrip summerTrip = changeValidationToEntity(summerTripValidation);
		
		summerTripDao.addRecord(summerTrip);
	}
	
	@Transactional
	public void updateRecord(SummerTripValidation summerTripValidation, int id) {
		
		SummerTrip summerTrip = changeValidationToEntity(summerTripValidation);
		summerTrip.setId(id);
		
		summerTripDao.updateRecord(summerTrip, id);
	}
	
	@Transactional
	public void updateRecord(SummerTripValidation summerTripValidation, int id, User user) {
		
		SummerTrip summerTrip = changeValidationToEntity(summerTripValidation);
		summerTrip.setId(id);
		
		summerTripDao.updateRecord(summerTrip, id, user);
	}
	
	@Transactional
	public SummerTrip getRecord(int id) {
		
		return summerTripDao.getRecord(id);
	}

	
	@Transactional
	public List<SummerTrip> getUserRecords() {
		
		List<SummerTrip> userEntry =  summerTripDao.getUserRecords();
		return userEntry;
	}

	@Transactional
	public List<SummerTrip> getAllRecords() {

		return summerTripDao.getAllRecords();
	}
	
	@Transactional
	public List<SummerTrip> getPaidRecords() {

		return summerTripDao.getPaidRecords();
	}
	
	/* @Transactional
	public List<String> getPaidRecordsShirtSizes() {
		
		return summerTripDao.getPaidRecordsShirtSizes();
	} */
	
	@Transactional
	public void deleteRecord(int id) {
		
		summerTripDao.deleteRecord(id);
		
	}
	
}
