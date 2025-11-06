package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public final class StringValueIsPresentRule implements Rule {

    private static final Rule instance = new StringValueIsPresentRule();

    private StringValueIsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {
        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 3) {
            var userMessage = MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        boolean mustApplyTrim = (Boolean) data[2];

        if ((mustApplyTrim) ? TextHelper.isEmptyWithTrim(stringData) : TextHelper.isEmpty(stringData)) {
            var userMessage = String.format(
                    MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_IS_EMPTY.getTitle(),
                    dataName
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.STRING_VALUES_PRESENT_RULE_DATA_IS_EMPTY.getContent(),
                    dataName
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}