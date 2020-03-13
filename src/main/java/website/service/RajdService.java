package website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.RajdDao;
import website.entity.Rajd;
import website.forms.RajdSignValidation;

@Service
public class RajdService {

	@Autowired
	private RajdDao rajdDao;
	
	@Transactional
	public void addSign(RajdSignValidation rajdForm) {
		
		Rajd rajd = new Rajd();
		rajd.setFirstName(rajdForm.getFirstName());
		rajd.setLastName(rajdForm.getLastName());
		rajd.setEmail(rajdForm.getEmail());
		rajd.setIndexNumber(rajdForm.getIndexNumber());
		rajd.setShirtSize(rajdForm.getShirtSize());
		rajd.setTransportOption(rajdForm.getTransportOption());
		
		rajdDao.addSign(rajd);;
	}

	@Transactional
	public List<Rajd> getMySign() {
		
		List<Rajd> mySign =  rajdDao.getMySign();
		return mySign;
	}

	@Transactional
	public List<Rajd> getAllSign() {

		return rajdDao.getAllSign();
	}
	
}
