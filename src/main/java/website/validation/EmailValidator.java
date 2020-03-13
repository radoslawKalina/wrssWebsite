package website.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	
	private Matcher matcher;
	private Pattern pattern;
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+.[A-Za-z0-9]+";
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (value != null) {
			
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(value);
			
			return matcher.matches();	
			
		} else return false;
	}

}
