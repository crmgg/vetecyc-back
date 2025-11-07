package co.edu.uco.vetecyv.business.business.rule.doctor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public class StringHasNoWhitespaceAndStartsWithUppercaseRule implements Rule {

    private static final Rule instance = new StringHasNoWhitespaceAndStartsWithUppercaseRule();

    private StringHasNoWhitespaceAndStartsWithUppercaseRule() {}

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {
        if (data == null || data.length < 2) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getTitle(), MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getContent());
        }

        final String value;
        final String fieldName;

        try {
            value = (String) data[0];
            fieldName = (String) data[1];
        } catch (ClassCastException ex) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_RULE_INVALID_DATA_TYPES.getTitle(), MessagesEnumDoctorRule.DOCTOR_RULE_INVALID_DATA_TYPES.getContent());
        }

        if (TextHelper.isEmptyWithTrim(value)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID.getContent());
        }

        // Prohibir cualquier whitespace en la cadena
        if (value.matches(".*\\s+.*")) {
            // Elegir mensaje específico según el fieldName
            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Nombre"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Primer apellido"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Segundo apellido"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            // Por defecto usar mensaje genérico
            var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIELD_FORMAT.getTitle();
            var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIELD_FORMAT.getContent(), fieldName);
            throw VetecyvException.create(title, content);
        }

        // Primera letra debe ser mayúscula
        var firstChar = value.charAt(0);
        if (!Character.isUpperCase(firstChar)) {
            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_FIRST_NAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Nombre"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_NAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_FIRST_SURNAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Primer apellido"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            if (fieldName != null && (fieldName.equals(MessagesEnumGeneric.GENERIC_SECOND_SURNAME_LABEL.getContent()) || fieldName.equalsIgnoreCase("Segundo apellido"))) {
                var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getTitle();
                var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT.getContent(), value);
                throw VetecyvException.create(title, content);
            }

            var title = MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIELD_FORMAT.getTitle();
            var content = String.format(MessagesEnumDoctorRule.DOCTOR_ERROR_INVALID_FIELD_FORMAT.getContent(), fieldName);
            throw VetecyvException.create(title, content);
        }
    }
}
