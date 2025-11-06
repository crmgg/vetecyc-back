package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import co.edu.uco.vetecyv.business.business.rule.doctor.DoctorMobileDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataDoctorMobileDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataDoctorMobileDoesNotExist();

    private ValidateDataDoctorMobileDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        DoctorMobileDoesNotExistRule.executeRule(data);
    }
}
