package website.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import website.dto.UserDto;
import website.entity.UserEntity;

public interface UserService extends UserDetailsService {

	UserEntity getUser(String email);
	UserDto getUserDto(String email);
	UserEntity getUserEntityById(int id);
	void registerUser(UserDto userDto);
	String getCurrentUserEmail();
	
}
