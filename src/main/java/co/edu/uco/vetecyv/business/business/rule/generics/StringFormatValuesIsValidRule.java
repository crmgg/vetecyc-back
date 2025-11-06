package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

import java.util.regex.Pattern;

public final class StringFormatValuesIsValidRule implements Rule {

    private static final Rule instance = new StringFormatValuesIsValidRule();

    private StringFormatValuesIsValidRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 4) {
            var userMessage = MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var regex = (String) data[2];
        var mustApplyTrim = (Boolean) data[3];

        var dataToValidate = (mustApplyTrim)
                ? TextHelper.getDefaultWithTrim(stringData)
                : stringData;

        if (!TextHelper.isEmpty(dataToValidate)) {

            boolean matches;
            try {
                matches = Pattern.compile(regex).matcher(dataToValidate).matches();
            } catch (Exception ex) {
                // If the regex is invalid, treat as not matching and include technical detail
                var userMessage = String.format(MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getTitle(), dataName);
                var technicalMessage = String.format(MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getContent(), dataName, regex);
                throw VetecyvException.create(userMessage, technicalMessage);
            }

            if (!matches) {
                var userMessage = String.format(MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getTitle(), dataName);
                var technicalMessage = String.format(MessagesEnumGeneric.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getContent(), dataName, regex);
                throw VetecyvException.create(userMessage, technicalMessage);
            }
        }
    }
}