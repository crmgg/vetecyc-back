package co.edu.uco.vetecyv.business.business.rule.generics;

import java.util.UUID;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

public final class UUIDValueIsPresentRule implements Rule {

    private static final Rule instance = new UUIDValueIsPresentRule();

    private UUIDValueIsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {
        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 2) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var uuidData = (UUID) data[0];
        var dataName = (String) data[1];

        if(UUIDHelper.getUUIDHelper().isDefaultUUID(uuidData)) {
            var userMessage= MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage= MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT.getContent() + " " + dataName;

            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }
}
