package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.PaidEntity;

@Repository
public class PaidDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PaidEntity> getAllRecords() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<PaidEntity> query = session.createQuery("from PaidEntity", PaidEntity.class);
		List<PaidEntity> paidList = query.getResultList();
		
		return paidList;
	}
	
	public PaidEntity getRecord(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		PaidEntity paid = session.get(PaidEntity.class, id);
		
		return paid;
	}

	public void update(PaidEntity paid) {

		Session session = sessionFactory.getCurrentSession();
		
		session.update(paid);
	}

}
