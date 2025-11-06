package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public final class StringLengthValuesIsValidRule implements Rule {

    private static final Rule instance = new StringLengthValuesIsValidRule();

    private StringLengthValuesIsValidRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 5) {
            var userMessage = MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        int minLength = (Integer) data[2];
        int maxLength = (Integer) data[3];
        boolean mustApplyTrim = (Boolean) data[4];

        var value = mustApplyTrim ? TextHelper.getDefaultWithTrim(stringData) : TextHelper.getDefault(stringData);
        var length = value == null ? 0 : value.length();

        var isValid = length >= minLength && length <= maxLength;

        if (!isValid) {
            var minLengthStr = String.valueOf(minLength);
            var maxLengthStr = String.valueOf(maxLength);

            var userMessage = String.format(
                    MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID.getTitle(),
                    dataName, minLengthStr, maxLengthStr
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID.getContent(),
                    dataName, minLengthStr, maxLengthStr
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}