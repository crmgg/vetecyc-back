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

        // Identity document: mínimo 7, máximo 10
        if (TextHelper.isEmptyWithTrim(tutor.getIdentityDocument()) || tutor.getIdentityDocument().length() < 7 || tutor.getIdentityDocument().length() > 10) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID.getContent());
        }

        // Name: mínimo 1, máximo 7
        if (TextHelper.isEmptyWithTrim(tutor.getName()) || tutor.getName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_NAME.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_NAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getName(), "Nombre");

        // First last name: mínimo 1, máximo 7
        if (TextHelper.isEmptyWithTrim(tutor.getFirstLastName()) || tutor.getFirstLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_FIRSTLASTNAME.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_FIRSTLASTNAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getFirstLastName(), "Primer apellido");

        // Second last name: mínimo 1, máximo 7
        if (TextHelper.isEmptyWithTrim(tutor.getSecondLastName()) || tutor.getSecondLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_SECONDLASTNAME.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_SECONDLASTNAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(tutor.getSecondLastName(), "Segundo apellido");

        // Email: máximo 100
        if (!TextHelper.isValidEmail(tutor.getEmail()) || tutor.getEmail().length() > 100) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_EMAIL.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_EMAIL.getContent());
        }

        // Password: mínimo 8, máximo 26
        if (TextHelper.isEmptyWithTrim(tutor.getPassword()) || tutor.getPassword().length() < 8 || tutor.getPassword().length() > 26) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_PASSWORD.getContent());
        }

        // Requerir exactamente 10 caracteres y formato de teléfono válido
        if (tutor.getPhoneNumber() == null || !TextHelper.isValidPhoneNumber(tutor.getPhoneNumber()) || tutor.getPhoneNumber().length() != 10) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID_PHONE.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID_PHONE.getContent());
        }
    }
}
