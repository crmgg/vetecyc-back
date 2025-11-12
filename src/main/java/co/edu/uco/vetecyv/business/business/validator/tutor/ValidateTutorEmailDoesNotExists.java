package co.edu.uco.vetecyv.business.business.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.tutor.TutorEmailDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.validator.Validator;

public class ValidateTutorEmailDoesNotExists implements Validator{

    private static final Validator INSTANCE = new ValidateTutorEmailDoesNotExists();

    private ValidateTutorEmailDoesNotExists() {

    }

    public static void executeValidation(final Object... data) {
        INSTANCE.validate(data);
    }

    @Override
    public void validate(final Object... data) {
        TutorEmailDoesNotExistRule.executeRule(data);
    }

}
