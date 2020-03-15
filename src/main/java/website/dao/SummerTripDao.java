package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.Paid;
import website.entity.SummerTrip;
import website.entity.User;
import website.service.UserServiceInterface;

@Repository
public class SummerTripDao {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	public UserServiceInterface userService;
	
	public int getCurrentUserId() {
		
		User user = userService.getUser(userService.getCurrentUserEmail());
		return user.getId();
	}
	
	public void addRecord(SummerTrip summerTrip) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, getCurrentUserId());
		summerTrip.setUser(user);
		
		Paid paid = new Paid("NO");
		paid.setSummerTrip(summerTrip);
		
		session.save(paid);
	}
	
	public void updateRecord(SummerTrip summerTrip, int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, getCurrentUserId());
		summerTrip.setUser(user);
		
		session.update(summerTrip);
	}
	
	public void updateRecord(SummerTrip summerTrip, int id, User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		summerTrip.setUser(user);
		
		session.update(summerTrip);
	}
	
	public SummerTrip getRecord(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		SummerTrip summerTrip = session.get(SummerTrip.class, id);
		
		return summerTrip;
	}

	public List<SummerTrip> getUserRecords() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<SummerTrip> query = session.createQuery("from SummerTrip where user_id=:id", SummerTrip.class);
		query.setParameter("id", getCurrentUserId());
		
		List<SummerTrip> userRecords = query.getResultList();
		
		return userRecords;
	}

	public List<SummerTrip> getAllRecords() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<SummerTrip> query = session.createQuery("from SummerTrip order by lastName", SummerTrip.class);
		List<SummerTrip> allRecords = query.getResultList();

		return allRecords;
	}
	
	public List<SummerTrip> getPaidRecords() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<SummerTrip> query = session.createQuery("from SummerTrip summerTrip where summerTrip.paid.paid=:paid",
				SummerTrip.class);
		query.setParameter("paid", "YES");
		
		List<SummerTrip> paidRecords = query.getResultList();
		
		return paidRecords;
		
	}
	
	/* public List<String> getPaidRecordsShirtSizes() {
		
		List<SummerTrip> allRecords  = getAllRecords();
		List<String> shirtSizesList = new ArrayList<>();
		
		for (SummerTrip temp : allRecords) {
			
			String paid = temp.getPaid().getPaid();
			System.out.println(paid);
			if (paid == "YES") {
				shirtSizesList.add(paid);
			}
		}
		
		return shirtSizesList;
	} */
	
	public void deleteRecord(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		SummerTrip summerTrip = session.get(SummerTrip.class, id);
		Paid paid = summerTrip.getPaid();
		session.delete(paid);
		
	}

}
