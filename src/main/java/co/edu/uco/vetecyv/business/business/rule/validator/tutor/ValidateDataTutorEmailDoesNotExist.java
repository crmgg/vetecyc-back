package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.tutor.TutorEmailDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataTutorEmailDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataTutorEmailDoesNotExist();

    private ValidateDataTutorEmailDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {

        TutorEmailDoesNotExistRule.executeRule(data);

    }
}
