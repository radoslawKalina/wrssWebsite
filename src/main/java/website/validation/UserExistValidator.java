package website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import website.service.UserService;

public class UserExistValidator implements ConstraintValidator<UserExist, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (userService.getUser(value) != null) {
			
			return false;
		}
		
		return true;
	}

}
