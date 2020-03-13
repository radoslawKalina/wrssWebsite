package website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import website.entity.Rajd;
import website.entity.User;

@Repository
public class RajdDao {
	
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

	
	public void addSign(Rajd rajd) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, getCurrentUserId());
		rajd.setUser(user);
		
		session.save(rajd);
	}

	public List<Rajd> getMySign() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Rajd> query = session.createQuery("from Rajd where user_id=:id", Rajd.class);
		query.setParameter("id", getCurrentUserId());
		
		List<Rajd> mySign = query.getResultList();
		
		return mySign;
	}

	public List<Rajd> getAllSign() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Rajd> query = session.createQuery("from Rajd order by lastName", Rajd.class);
		List<Rajd> allSign = query.getResultList();

		return allSign;
	}

}
