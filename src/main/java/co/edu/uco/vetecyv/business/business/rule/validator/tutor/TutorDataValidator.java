package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.business.business.rule.tutor.StringHasNoWhitespaceAndStartsWithUppercaseRule;

public class TutorDataValidator {

    private TutorDataValidator() {}

    public static void validate(final TutorDomain tutor) {
        if (ObjectHelper.isNull(tutor)) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID.getContent());
        }

        // Identity document: mínimo 8, máximo 10
        if (TextHelper.isEmptyWithTrim(tutor.getIdentityDocument()) || tutor.getIdentityDocument().length() < 8 || tutor.getIdentityDocument().length() > 10) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_IDENTITYDOCUMENT_LENGTH.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_IDENTITYDOCUMENT_LENGTH.getContent());
        }

        // Identity document: no puede contener espacios
        if (tutor.getIdentityDocument().matches(".*\\s+.*")) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_IDENTITYDOCUMENT_FORMAT.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_IDENTITYDOCUMENT_FORMAT.getContent());
        }

        // Name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(tutor.getName()) || tutor.getName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_NAME_FORMAT.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_NAME_FORMAT.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getName(), "Nombre");

        // First last name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(tutor.getFirstLastName()) || tutor.getFirstLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getFirstLastName(), "Primer apellido");

        // Second last name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(tutor.getSecondLastName()) || tutor.getSecondLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getSecondLastName(), "Segundo apellido");

        // Email: máximo 100
        if (!TextHelper.isValidEmail(tutor.getEmail()) || tutor.getEmail().length() > 100) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_EMAIL.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_EMAIL.getContent());
        }

        // Password: mínimo 8, máximo 26
        if (TextHelper.isEmptyWithTrim(tutor.getPassword()) || tutor.getPassword().length() < 8 || tutor.getPassword().length() > 26) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD_LENGTH.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD_LENGTH.getContent());
        }

        // Password: no puede contener espacios
        if (tutor.getPassword().matches(".*\\s+.*")) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD_FORMAT.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD_FORMAT.getContent());
        }

        // Requerir exactamente 10 caracteres y formato de teléfono válido
        if (tutor.getPhoneNumber() == null || !TextHelper.isValidPhoneNumber(tutor.getPhoneNumber()) || tutor.getPhoneNumber().length() != 10) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_PHONE_LENGTH.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_PHONE_LENGTH.getContent());
        }
    }
}
