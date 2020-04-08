package website.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import website.entity.UserEntity;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserEntity getUser(String email) {

		Session session = sessionFactory.getCurrentSession();

		Query<UserEntity> userQuery = session.createQuery("from UserEntity where email=:mail", UserEntity.class);
		userQuery.setParameter("mail", email);

		UserEntity user = null;

		try {
			user = userQuery.getSingleResult();

		} catch (Exception exc) {
			user = null;

		}
		return user;
	}

	public UserEntity getUserById(int id) {

		Session session = sessionFactory.getCurrentSession();
		
		return session.get(UserEntity.class, id);
	}

	public void registerUser(UserEntity userEntity) {

		Session session = sessionFactory.getCurrentSession();
		
		session.save(userEntity);

	}

}
