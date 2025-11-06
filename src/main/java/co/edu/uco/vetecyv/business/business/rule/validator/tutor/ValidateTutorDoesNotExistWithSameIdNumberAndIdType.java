package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.tutor.TutorDoesNotExistWithSameIdNumberAndIdTypeRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateTutorDoesNotExistWithSameIdNumberAndIdType implements Validator {

    private static final Validator instance = new ValidateTutorDoesNotExistWithSameIdNumberAndIdType();

    private ValidateTutorDoesNotExistWithSameIdNumberAndIdType() {
    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        TutorDoesNotExistWithSameIdNumberAndIdTypeRule.executeRule(data);
    }
}