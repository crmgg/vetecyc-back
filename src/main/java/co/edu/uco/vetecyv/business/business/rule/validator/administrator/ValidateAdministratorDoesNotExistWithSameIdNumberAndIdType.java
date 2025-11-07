package co.edu.uco.vetecyv.business.business.rule.validator.administrator;

import co.edu.uco.vetecyv.business.business.rule.administrator.AdministratorDoesNotExistWithSameIdNumberRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;

public class ValidateAdministratorDoesNotExistWithSameIdNumberAndIdType implements Validator {

    private static final Validator instance = new ValidateAdministratorDoesNotExistWithSameIdNumberAndIdType();

    private ValidateAdministratorDoesNotExistWithSameIdNumberAndIdType() {
    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        AdministratorDoesNotExistWithSameIdNumberRule.executeRule(data);
    }
}