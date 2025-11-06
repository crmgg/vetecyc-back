package co.edu.uco.vetecyv.business.business.rule.validator.administrator;

import co.edu.uco.vetecyv.business.business.rule.administrator.AdministratorEmailDoesNotExistRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateDataAdministratorEmailDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataAdministratorEmailDoesNotExist();

    private ValidateDataAdministratorEmailDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {

        AdministratorEmailDoesNotExistRule.executeRule(data);

    }
}
