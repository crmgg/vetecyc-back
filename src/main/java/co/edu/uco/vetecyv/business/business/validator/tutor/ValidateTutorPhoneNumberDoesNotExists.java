package co.edu.uco.vetecyv.business.business.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.generics.tutor.TutorPhoneNumberDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.validator.Validator;

public class ValidateTutorPhoneNumberDoesNotExists implements Validator {

    private static final ValidateTutorPhoneNumberDoesNotExists INSTANCE = new ValidateTutorPhoneNumberDoesNotExists();

    private ValidateTutorPhoneNumberDoesNotExists() {

    }

    public static void executeValidation(final Object... data) {
        INSTANCE.validate(data);
    }


    @Override
    public void validate(Object... data) {

        TutorPhoneNumberDoesNotExistRule.executeRule(data);

    }
}
