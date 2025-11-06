package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import co.edu.uco.vetecyv.business.business.rule.doctor.DoctorDoesNotExistWithSameIdNumberAndIdTypeRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDoctorDoesNotExistWithSameIdNumberAndIdType implements Validator {

    private static final Validator instance = new ValidateDoctorDoesNotExistWithSameIdNumberAndIdType();

    private ValidateDoctorDoesNotExistWithSameIdNumberAndIdType() {
    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        DoctorDoesNotExistWithSameIdNumberAndIdTypeRule.executeRule(data);
    }
}