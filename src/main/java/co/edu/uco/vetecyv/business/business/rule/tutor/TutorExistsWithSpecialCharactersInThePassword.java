package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;

public final class TutorExistsWithSpecialCharactersInThePassword implements Rule {

    private static final Rule instance = new TutorExistsWithSpecialCharactersInThePassword();

    private TutorExistsWithSpecialCharactersInThePassword() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {
        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 1) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String password;
        try {
            password = (String) data[0];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (TextHelper.isEmptyWithTrim(password)) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        // Verifica que la contraseña contenga al menos un carácter especial
        // Consideramos carácter especial cualquier caracter que no sea letra ni dígito
        boolean hasSpecial = password.chars()
                .anyMatch(c -> !Character.isLetterOrDigit(c));

        if (!hasSpecial) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getTitle();
            var technicalMessage = "La contraseña debe contener al menos un carácter especial.";
            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }
}
