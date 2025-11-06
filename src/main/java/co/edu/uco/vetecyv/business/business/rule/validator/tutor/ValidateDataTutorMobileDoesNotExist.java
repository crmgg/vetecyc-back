package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.tutor.TutorMobileDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataTutorMobileDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataTutorMobileDoesNotExist();

    private ValidateDataTutorMobileDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        TutorMobileDoesNotExistRule.executeRule(data);
    }
}
