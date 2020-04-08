package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.PaidEntity;
import website.entity.SummerTripEntity;
import website.entity.UserEntity;
import website.service.UserService;

@Repository
public class SummerTripDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	public UserService userService;
	
	public List<SummerTripEntity> getAllRecords() {

		Session session = sessionFactory.getCurrentSession();

		Query<SummerTripEntity> query = session.createQuery(
				"from SummerTripEntity order by lastName", SummerTripEntity.class);
		List<SummerTripEntity> allRecords = query.getResultList();

		return allRecords;
	}
	
	public List<SummerTripEntity> getPaidRecords() {

		Session session = sessionFactory.getCurrentSession();

		Query<SummerTripEntity> query = session.createQuery(
				"from SummerTripEntity summerTrip where summerTrip.paid.paid=:paid", SummerTripEntity.class);
		query.setParameter("paid", "YES");

		List<SummerTripEntity> paidRecords = query.getResultList();

		return paidRecords;

	}
	
	public List<SummerTripEntity> getUserRecords() {

		Session session = sessionFactory.getCurrentSession();

		Query<SummerTripEntity> query = session.createQuery(
				"from SummerTripEntity where user_id=:id", SummerTripEntity.class);
		query.setParameter("id", getCurrentUserId());

		List<SummerTripEntity> userRecords = query.getResultList();

		return userRecords;
	}
	
	public SummerTripEntity getRecord(int id) {

		Session session = sessionFactory.getCurrentSession();
		
		SummerTripEntity summerTripEntity = session.get(SummerTripEntity.class, id);

		return summerTripEntity;
	}
	
	public void addRecord(SummerTripEntity summerTripEntity) {

		Session session = sessionFactory.getCurrentSession();

		UserEntity userEntity = session.get(UserEntity.class, getCurrentUserId());
		PaidEntity paidEntity = new PaidEntity("NO");
		
		summerTripEntity.setUser(userEntity);
		
		paidEntity.setSummerTrip(summerTripEntity);

		session.save(paidEntity);
	}
	
	public void updateRecord(SummerTripEntity summerTrip, int id) {

		Session session = sessionFactory.getCurrentSession();

		UserEntity user = session.get(UserEntity.class, getCurrentUserId());
		summerTrip.setUser(user);

		session.update(summerTrip);
	}
	
	public void updateRecord(SummerTripEntity summerTrip, int id, int userId) {

		Session session = sessionFactory.getCurrentSession();

		UserEntity user = session.get(UserEntity.class, userId);
		summerTrip.setUser(user);

		session.update(summerTrip);
	}
	
	public void deleteRecord(int id) {

		Session session = sessionFactory.getCurrentSession();
		
		SummerTripEntity summerTripEntity = session.get(SummerTripEntity.class, id);
		PaidEntity paid = summerTripEntity.getPaid();
		
		session.delete(paid);

	}
	
	public int getCurrentUserId() {

		UserEntity user = userService.getUser(userService.getCurrentUserEmail());
		
		return user.getId();
	}


}
