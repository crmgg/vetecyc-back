package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;

public class StringHasNoWhitespaceAndStartsWithUppercaseRule implements Rule {

    private static final Rule instance = new StringHasNoWhitespaceAndStartsWithUppercaseRule();

    private StringHasNoWhitespaceAndStartsWithUppercaseRule() {}

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {
        if (data == null || data.length < 2) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID.getContent());
        }

        final String value;
        final String fieldName;

        try {
            value = (String) data[0];
            fieldName = (String) data[1];
        } catch (ClassCastException ex) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID.getContent());
        }

        if (TextHelper.isEmptyWithTrim(value)) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_INVALID.getTitle(), MessagesEnum.TUTOR_ERROR_INVALID.getContent());
        }

        // Prohibir cualquier whitespace en la cadena
        if (value.matches(".*\\s+.*")) {
            var userMessage = String.format("%s contiene espacios en blanco no permitidos", fieldName);
            throw VetecyvException.create(userMessage, userMessage);
        }

        // Primera letra debe ser mayúscula
        var firstChar = value.charAt(0);
        if (!Character.isUpperCase(firstChar)) {
            var userMessage = String.format("%s debe comenzar con letra mayúscula", fieldName);
            throw VetecyvException.create(userMessage, userMessage);
        }
    }
}

