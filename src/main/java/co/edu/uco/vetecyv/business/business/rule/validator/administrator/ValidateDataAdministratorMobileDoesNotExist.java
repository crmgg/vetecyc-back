package co.edu.uco.vetecyv.business.business.rule.validator.administrator;

import co.edu.uco.vetecyv.business.business.rule.administrator.AdministratorMobileDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataAdministratorMobileDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataAdministratorMobileDoesNotExist();

    private ValidateDataAdministratorMobileDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        AdministratorMobileDoesNotExistRule.executeRule(data);
    }
}
