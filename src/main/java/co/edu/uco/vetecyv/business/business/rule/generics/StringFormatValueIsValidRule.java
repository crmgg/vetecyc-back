package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public final class StringFormatValueIsValidRule implements Rule {

    private static final Rule INSTANCE = new StringFormatValueIsValidRule();

    private  StringFormatValueIsValidRule() {

    }

    public static void executeRule(final Object... data) {
        INSTANCE.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.USER_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.TECHNICAL_ERROR_STRING_FORMAT_VALUE.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 4) {
            var userMessage = MessagesEnumGeneric.USER_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.TECHNICAL_ERROR_WRONG_STRING_FORMAT_VALUE.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }


        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var pattern = (String) data[2];
        boolean mustApplyTrim = (Boolean) data[3];

        if(!TextHelper.formatIsValid(stringData, pattern, mustApplyTrim)) {
            var userMessage= MessagesEnumGeneric.USER_ERROR_WRONG_FORMAT.getContent() + dataName;
            var technicalMessage= MessagesEnumGeneric.TECHNICAL_ERROR_WRONG_FORMAT.getContent() + dataName + " " + pattern;

            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }

}
