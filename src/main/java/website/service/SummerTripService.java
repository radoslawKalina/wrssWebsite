package website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.SummerTripDao;
import website.entity.SummerTrip;
import website.forms.SummerTripValidation;

@Service
public class SummerTripService {

	@Autowired
	private SummerTripDao summerTripDao;
	
	@Transactional
	public void addSign(SummerTripValidation summerTripValidation) {
		
		SummerTrip summerTrip = new SummerTrip();
		summerTrip.setFirstName(summerTripValidation.getFirstName());
		summerTrip.setLastName(summerTripValidation.getLastName());
		summerTrip.setEmail(summerTripValidation.getEmail());
		summerTrip.setIndexNumber(summerTripValidation.getIndexNumber());
		summerTrip.setShirtSize(summerTripValidation.getShirtSize());
		summerTrip.setTransportOption(summerTripValidation.getTransportOption());
		
		summerTripDao.add(summerTrip);;
	}

	@Transactional
	public List<SummerTrip> getMySign() {
		
		List<SummerTrip> mySign =  summerTripDao.getMySign();
		return mySign;
	}

	@Transactional
	public List<SummerTrip> getAllSign() {

		return summerTripDao.getAllSign();
	}
	
}
