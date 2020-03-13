package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.UserDao;
import website.entity.User;
import website.entity.UserRole;
import website.forms.UserValidation;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Transactional
	public void registerUser(UserValidation userValidation) {
		
		User user = new User();
		user.setFirstName(userValidation.getFirstName());
		user.setLastName(userValidation.getLastName());
		user.setEmail(userValidation.getEmail());
		user.setPassword(passwordEncoder.encode(userValidation.getPassword()));
		
		user.add(new UserRole("ROLE_STUDENT"));
		
		userDao.registerUser(user);
	}
	
	@Transactional
	public User getUser(String email) {
		
		return userDao.getUser(email);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getUser(email);
		
	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(email);
	      builder.password(user.getPassword());
	      builder.authorities(user.getUserRolesString());
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
		
		return builder.build();
	}

}
