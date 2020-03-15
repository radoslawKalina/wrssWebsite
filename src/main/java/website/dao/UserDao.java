package website.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.User;


@Repository
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void registerUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		
	}
	
	public User getUser(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> userQuery = session.createQuery("from User where email=:mail", User.class);
		userQuery.setParameter("mail", email);
		
		User user = null;
		try {
			user = userQuery.getSingleResult();
		} catch (Exception exc) {
			user = null;
		}
		
		return user;
	}
	
	public User getUserById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		
		return user;
	}
	
}
