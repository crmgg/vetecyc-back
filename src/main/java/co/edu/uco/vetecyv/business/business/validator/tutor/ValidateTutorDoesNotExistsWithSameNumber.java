package co.edu.uco.vetecyv.business.business.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.generics.tutor.TutorPhoneNumberDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.validator.Validator;

public class ValidateTutorDoesNotExistsWithSameNumber implements Validator {

    private static final Validator INSTANCE = new ValidateTutorDoesNotExistsWithSameNumber();

    private ValidateTutorDoesNotExistsWithSameNumber() {
    }

    public static void executeValidation(final Object... data) {
        INSTANCE.validate(data);
    }

    @Override
    public void validate(final Object... data) {
        TutorPhoneNumberDoesNotExistRule.executeRule(data);
    }

}
