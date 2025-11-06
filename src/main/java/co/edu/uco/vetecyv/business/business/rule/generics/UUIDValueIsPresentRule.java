package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

import java.util.UUID;

public final class UUIDValueIsPresentRule implements Rule {

    private static final Rule instance = new UUIDValueIsPresentRule();

    private UUIDValueIsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var uuid = (UUID) data[0];
        var dataName = (String) data[1];

        if (ObjectHelper.isNull(uuid)) {
            var userMessage = String.format(
                    MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL.getTitle(),
                    dataName
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL.getContent(),
                    dataName
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(uuid)) {
            var userMessage = String.format(
                    MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT.getTitle(),
                    dataName
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT.getContent(),
                    dataName
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}