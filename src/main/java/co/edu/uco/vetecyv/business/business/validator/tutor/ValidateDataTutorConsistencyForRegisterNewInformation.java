package co.edu.uco.vetecyv.business.business.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.generics.StringFormatValueIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.vetecyv.business.business.validator.Validator;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public final class ValidateDataTutorConsistencyForRegisterNewInformation implements Validator {

    private static final Validator INSTANCE = new ValidateDataTutorConsistencyForRegisterNewInformation();

    private ValidateDataTutorConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        INSTANCE.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.GENERIC_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 1) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.GENERIC_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var tutorDomainData = (TutorDomain) data[0];

        validateEmptyData(tutorDomainData);

        validateDataLength(tutorDomainData);

        validateDataFormat(tutorDomainData);

    }

    private void validateEmptyData(final TutorDomain data) {

        StringValueIsPresentRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), true);
        StringValueIsPresentRule.executeRule(data.getPassword(), "contraseña", true);

    }

    private void validateDataLength(final TutorDomain data) {

        StringLengthValueIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), 7, 10, true);
        StringLengthValueIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), 1, 50, true);
        StringLengthValueIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), 1, 50, true);
        StringLengthValueIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), 1, 100, true);
        StringLengthValueIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), 1, 10, true);
        StringLengthValueIsValidRule.executeRule(data.getPassword(), "contraseña", 8, 100, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            StringLengthValueIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 100, true);
        }

    }

    private void validateDataFormat(final TutorDomain data) {

        StringFormatValueIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), "^\\d+$", true);
        StringFormatValueIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
        StringFormatValueIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
        StringFormatValueIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", true);
        StringFormatValueIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), "^\\+?\\d+$", true);
        // Contraseña: al menos 6 caracteres ya verificados por longitud; asegurarse de que no contiene espacios solo
        StringFormatValueIsValidRule.executeRule(data.getPassword(), "contraseña", "^\\S+$", true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            StringFormatValueIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
        }

    }

}
