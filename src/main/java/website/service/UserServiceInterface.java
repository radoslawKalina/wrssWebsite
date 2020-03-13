package website.service;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import website.entity.User;
import website.forms.UserValidation;

public interface UserServiceInterface extends UserDetailsService {

	User getUser(String email);

	void registerUser(@Valid UserValidation userValidation);

}
