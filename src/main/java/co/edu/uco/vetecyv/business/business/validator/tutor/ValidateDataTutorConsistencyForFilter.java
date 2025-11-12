package co.edu.uco.vetecyv.business.business.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.generics.StringFormatValueIsValidRule;
import co.edu.uco.vetecyv.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.vetecyv.business.business.validator.Validator;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public class ValidateDataTutorConsistencyForFilter implements Validator {

    private static final Validator INSTANCE = new ValidateDataTutorConsistencyForFilter();

    private ValidateDataTutorConsistencyForFilter(){

    }

    public static void executeValidation(final Object... data) {
        INSTANCE.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.GENERIC_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 1) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.GENERIC_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var tutorDomainData = (TutorDomain) data[0];

        validateDataLength(tutorDomainData);

        validateDataFormat(tutorDomainData);

    }

    private void validateDataLength(final TutorDomain data) {

        if(!TextHelper.isEmptyWithTrim(data.getIdentityDocument())) {
            StringLengthValueIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), 1, 50, true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getName())){
            StringLengthValueIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), 1, 100, true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getFirstLastName())){
            StringLengthValueIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), 1, 100, true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getSecondLastName())){
            StringLengthValueIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), 1, 100, true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getEmail())){
            StringLengthValueIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), 1, 100, true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getPhoneNumber())){
            StringLengthValueIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), 1, 15, true);
        }

    }

    private void validateDataFormat(final TutorDomain data) {

        if(!TextHelper.isEmptyWithTrim(data.getIdentityDocument())) {
            StringFormatValueIsValidRule.executeRule(data.getIdentityDocument(), MessagesEnumGeneric.GENERIC_ID_NUMBER_LABEL.getContent(), "^\\d+$", true );
        }

        if(!TextHelper.isEmptyWithTrim(data.getName())){
            StringFormatValueIsValidRule.executeRule(data.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getFirstLastName())){
            StringFormatValueIsValidRule.executeRule(data.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true);
        }

        if(!TextHelper.isEmptyWithTrim(data.getSecondLastName())){
            StringFormatValueIsValidRule.executeRule(data.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent(), "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$", true );
        }

        if(!TextHelper.isEmptyWithTrim(data.getEmail())){
            StringFormatValueIsValidRule.executeRule(data.getEmail(), MessagesEnumGeneric.GENERIC_EMAIL_LABEL.getContent(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", true );
        }

        if(!TextHelper.isEmptyWithTrim(data.getPhoneNumber())){
            StringFormatValueIsValidRule.executeRule(data.getPhoneNumber(), MessagesEnumGeneric.GENERIC_MOBILE_LABEL.getContent(), "^\\+?\\d+$", true);
        }

    }

}
