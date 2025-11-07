package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.generics.StringFormatValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.vetecyv.business.business.rule.validator.Validator;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;
import co.edu.uco.vetecyv.business.business.rule.tutor.StringHasNoWhitespaceAndStartsWithUppercaseRule;

@SuppressWarnings("unused")
public class ValidateDataTutorConsistencyForRegisterNewInformation implements Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    // Teléfono: exactamente 10 dígitos
    private static final String MOBILE_PHONE_REGEX = "^[0-9]{10}$";

    private static final Validator instance = new ValidateDataTutorConsistencyForRegisterNewInformation();

    private ValidateDataTutorConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        var tutorDomainData = (TutorDomain) data[0];

        validateEmptyData(tutorDomainData);

        validateDataLength(tutorDomainData);

        validateDataFormat(tutorDomainData);

        validateDomainObjects(tutorDomainData);
    }

    private void validateEmptyData(final TutorDomain data) {

        StringValueIsPresentRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPassword(), "Password", true);
    }

    private void validateDataLength(final TutorDomain data) {

        // Identity document: mínimo 7, máximo 10
        StringLengthValuesIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), 7, 10, true);
        // Name: mínimo 1, máximo 7
        StringLengthValuesIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), 1, 7, true);
        // First last name: mínimo 1, máximo 7
        StringLengthValuesIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), 1, 7, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            // Second last name: mínimo 1, máximo 7
            StringLengthValuesIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 7, true);
        }

        // Email: máximo 100
        StringLengthValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), 1, 100, true);
        // Password: mínimo 8, máximo 26
        StringLengthValuesIsValidRule.executeRule(data.getPassword(), "Password", 8, 26, true);

        // Teléfono: exactamente 10 caracteres
        StringLengthValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), 10, 10, true);

        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent());
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent());
        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent());
        }
    }

    private void validateDataFormat(final TutorDomain data) {
        StringFormatValuesIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), EMAIL_REGEX, true);
        StringFormatValuesIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), MOBILE_PHONE_REGEX, true);
    }

    private void validateDomainObjects(final TutorDomain data) {
        // No nested objects to validate in current model
    }
}
