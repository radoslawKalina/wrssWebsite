package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.Paid;

@Repository
public class PaidDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Paid> getAllRecords() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Paid> query = session.createQuery("from Paid", Paid.class);
		List<Paid> paidList = query.getResultList();
		
		return paidList;
	}
	
	public Paid getRecord(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Paid paid = session.get(Paid.class, id);
		
		return paid;
	}

	public void update(Paid paid) {

		Session session = sessionFactory.getCurrentSession();
		session.update(paid);
	}

}
