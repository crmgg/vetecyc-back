package co.edu.uco.vetecyv.business.business.rule.validator.administrator;

import co.edu.uco.vetecyv.business.business.rule.generics.IdValueIsNotDefaultValueRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringFormatValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;
import co.edu.uco.vetecyv.business.domain.AdministratorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public class ValidateDataAdministratorConsistencyForRegisterNewInformation implements Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String MOBILE_PHONE_REGEX = "^[0-9]{1,20}$";

    private static final Validator instance = new ValidateDataAdministratorConsistencyForRegisterNewInformation();

    private ValidateDataAdministratorConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        var administratorDomainData = (AdministratorDomain) data [0];

        validateEmptyData(administratorDomainData);

        validateDataLength(administratorDomainData);

        validateDataFormatAdministrator(administratorDomainData);

        validateDomainObjectsAdministrator(administratorDomainData);
    }

    private void validateEmptyData(final AdministratorDomain data) {

        StringValueIsPresentRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), true);
        // AdministratorDomain exposes a single name field
        StringValueIsPresentRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), true);
    }

    private void validateDataLength(final AdministratorDomain data) {

        StringLengthValuesIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), 1, 25, true);
        StringLengthValuesIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), 1, 20, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())){
            StringLengthValuesIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 20, true);
        }

        StringLengthValuesIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), 1, 20, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            StringLengthValuesIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 20, true);
        }

        StringLengthValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), 1, 250, true);
        StringLengthValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), 1, 20, true);
    }

    private void validateDataFormatAdministrator(final AdministratorDomain data) {
        StringFormatValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), EMAIL_REGEX, true);
        StringFormatValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), MOBILE_PHONE_REGEX, true);
    }

    private void validateDomainObjectsAdministrator(final AdministratorDomain data) {
        // AdministratorDomain in this project does not expose nested idType or homeCity
        // If in the future those fields are added, validate with IdValueIsNotDefaultValueRule using MessagesEnumGeneric labels.
    }
}
