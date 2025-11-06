package co.edu.uco.vetecyv.business.business.rule.generics;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;

import java.util.UUID;

public final class IdValueIsNotDefaultValueRule implements Rule {

    private static final Rule instance = new IdValueIsNotDefaultValueRule();

    private IdValueIsNotDefaultValueRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID.getTitle();
            var technicalMessage = MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var uuid = (UUID) data[0];
        var dataName = (String) data[1];

        if (ObjectHelper.isNull(uuid)) {
            var userMessage = String.format(
                    MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL.getTitle(),
                    dataName
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL.getContent(),
                    dataName
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(uuid)) {
            var userMessage = String.format(
                    MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT.getTitle(),
                    dataName
            );
            var technicalMessage = String.format(
                    MessagesEnumGeneric.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT.getContent(),
                    dataName
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}