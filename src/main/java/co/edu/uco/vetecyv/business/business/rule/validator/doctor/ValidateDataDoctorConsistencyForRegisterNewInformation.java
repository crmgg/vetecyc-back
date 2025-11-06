package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import co.edu.uco.vetecyv.business.business.rule.generics.IdValueIsNotDefaultValueRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringFormatValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public class ValidateDataDoctorConsistencyForRegisterNewInformation implements Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String MOBILE_PHONE_REGEX = "^[0-9]{1,20}$";

    private static final Validator instance = new ValidateDataDoctorConsistencyForRegisterNewInformation();

    private ValidateDataDoctorConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        var doctorDomainData = (DoctorDomain) data[0];

        validateEmptyData(doctorDomainData);

        validateDataLength(doctorDomainData);

        validateDataFormat(doctorDomainData);

        validateDomainObjects(doctorDomainData);
    }

    private void validateEmptyData(final DoctorDomain data) {

        StringValueIsPresentRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), true);
    }

    private void validateDataLength(final DoctorDomain data) {

        StringLengthValuesIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), 1, 25, true);
        StringLengthValuesIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), 1, 20, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())){
            StringLengthValuesIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 20, true);
        }

        StringLengthValuesIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), 1, 20, true);

        StringLengthValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), 1, 250, true);
        StringLengthValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), 1, 20, true);
    }

    private void validateDataFormat(final DoctorDomain data) {
        StringFormatValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), EMAIL_REGEX, true);
        StringFormatValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), MOBILE_PHONE_REGEX, true);
    }

    private void validateDomainObjects(final DoctorDomain data) {
        // DoctorDomain no tiene objetos de dominio anidados que validar en este modelo.
    }
}
