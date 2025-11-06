package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import co.edu.uco.vetecyv.business.business.rule.doctor.DoctorEmailDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataDoctorEmailDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataDoctorEmailDoesNotExist();

    private ValidateDataDoctorEmailDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {

        DoctorEmailDoesNotExistRule.executeRule(data);

    }
}
