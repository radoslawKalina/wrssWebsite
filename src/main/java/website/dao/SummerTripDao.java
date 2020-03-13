package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import website.entity.SummerTrip;
import website.entity.User;

@Repository
public class SummerTripDao {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	public UserDao userDao;
	
	public String getCurrentUserEmail() {
		
		String email = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			email = ((UserDetails)principal).getUsername();
		} else {
			email = principal.toString();
		}
		
		return email;
	}
	
	public int getCurrentUserId() {
		
		User user = userDao.getUser(getCurrentUserEmail());
		
		return user.getId();
	}

	
	public void add(SummerTrip summerTrip) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, getCurrentUserId());
		summerTrip.setUser(user);
		
		session.save(summerTrip);
	}

	public List<SummerTrip> getMySign() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<SummerTrip> query = session.createQuery("from SummerTrip where user_id=:id", SummerTrip.class);
		query.setParameter("id", getCurrentUserId());
		
		List<SummerTrip> mySign = query.getResultList();
		
		return mySign;
	}

	public List<SummerTrip> getAllSign() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<SummerTrip> query = session.createQuery("from SummerTrip order by lastName", SummerTrip.class);
		List<SummerTrip> allSign = query.getResultList();

		return allSign;
	}

}
