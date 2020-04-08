package website.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import website.dao.UserDao;
import website.dto.UserDto;
import website.entity.UserEntity;
import website.entity.UserRoleEntity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Transactional
	public UserEntity getUser(String email) {

		return userDao.getUser(email);
	}

	@Transactional
	public UserDto getUserDto(String email) {

		UserEntity userEntity = userDao.getUser(email);

		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Transactional
	public UserEntity getUserEntityById(int id) {

		return userDao.getUserById(id);
	}

	@Transactional
	public void registerUser(UserDto userDto) {

		UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);

		userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
		userEntity.add(new UserRoleEntity("ROLE_STUDENT"));

		userDao.registerUser(userEntity);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userDao.getUser(email);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(email);
			builder.password(user.getEncryptedPassword());
			builder.authorities(user.getUserRolesString());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}

	public String getCurrentUserEmail() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = null;

		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();

		} else {
			email = principal.toString();

		}
		return email;
	}

}
