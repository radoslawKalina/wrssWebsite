package website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, Object> {

	private String firstField;
	private String secondField;
	private String message;
	
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
		this.firstField = constraintAnnotation.first();
		this.secondField = constraintAnnotation.second();
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean isValid = false;
		
		try {
			Object first = new BeanWrapperImpl(value).getPropertyValue(firstField);
			Object second = new BeanWrapperImpl(value).getPropertyValue(secondField);
			
			isValid = (first != null && first.equals(second));
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	
		if (!isValid){
			context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(firstField)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
		}

		return isValid;
	}
}
