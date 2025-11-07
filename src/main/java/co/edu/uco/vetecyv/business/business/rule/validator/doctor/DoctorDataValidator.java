package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;
import co.edu.uco.vetecyv.business.business.rule.doctor.StringHasNoWhitespaceAndStartsWithUppercaseRule;

public class DoctorDataValidator {

    private DoctorDataValidator() {}

    public static void validate(final DoctorDomain doctor) {
        if (ObjectHelper.isNull(doctor)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID.getContent());
        }

        // Identity document: mínimo 8, máximo 10
        if (TextHelper.isEmptyWithTrim(doctor.getIdentityDocument()) || doctor.getIdentityDocument().length() < 8 || doctor.getIdentityDocument().length() > 10) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_IDENTITYDOCUMENT_LENGTH.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_IDENTITYDOCUMENT_LENGTH.getContent());
        }

        // Name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(doctor.getName()) || doctor.getName().length() < 1 || doctor.getName().length() > 50) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(doctor.getName(), MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent());

        // First last name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(doctor.getFirstLastName()) || doctor.getFirstLastName().length() < 1 || doctor.getFirstLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(doctor.getFirstLastName(), MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent());

        // Second last name: mínimo 1, máximo 50
        if (TextHelper.isEmptyWithTrim(doctor.getSecondLastName()) || doctor.getSecondLastName().length() < 1 || doctor.getSecondLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME.getContent());
        }
        StringHasNoWhitespaceAndStartsWithUppercaseRule.executeRule(doctor.getSecondLastName(), MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent());

        // Email: máximo 100 y formato válido
        if (!TextHelper.isValidEmail(doctor.getEmail()) || doctor.getEmail().length() > 100) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_EMAIL.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_EMAIL.getContent());
        }

        // Password: mínimo 8, máximo 26
        if (TextHelper.isEmptyWithTrim(doctor.getPassword()) || doctor.getPassword().length() < 8 || doctor.getPassword().length() > 26) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_PASSWORD.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_PASSWORD.getContent());
        }

        // Teléfono: formato válido y exactamente 10 caracteres
        if (doctor.getPhoneNumber() == null || !TextHelper.isValidPhoneNumber(doctor.getPhoneNumber()) || doctor.getPhoneNumber().length() != 10) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_PHONE.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_PHONE.getContent());
        }
    }
}
