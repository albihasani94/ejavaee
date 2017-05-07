package al.ikubinfo.doit.business;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CrossCheckConstraintValidator implements ConstraintValidator<CrossCheck, ValidEntity>{

	@Override
	public void initialize(CrossCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(ValidEntity entity, ConstraintValidatorContext context) {
		return entity.isValid();
	}

}
