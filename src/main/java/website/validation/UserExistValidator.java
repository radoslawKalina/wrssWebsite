package website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import website.service.UserServiceInterface;

public class UserExistValidator implements ConstraintValidator<UserExist, String> {

	@Autowired
	private UserServiceInterface userService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (userService.getUser(value) != null) {
			
			return false;
		}
		
		return true;
	}

}
