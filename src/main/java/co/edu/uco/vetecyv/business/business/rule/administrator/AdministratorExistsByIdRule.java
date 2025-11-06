package co.edu.uco.vetecyv.business.business.rule.administrator;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumAdministratorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

import java.util.UUID;

public class AdministratorExistsByIdRule implements Rule {

    private static final Rule instance = new AdministratorExistsByIdRule();

    private AdministratorExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
        if (data.length < 2){
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final UUID id;
        final DAOFactory daoFactory;

        try {
            id = (UUID) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var administrator = daoFactory.getAdministratorDAO().findById(id);

        if (ObjectHelper.isNull(administrator) || UUIDHelper.getUUIDHelper().isDefaultUUID(administrator.getId())) {

            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_NOT_FOUND.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_NOT_FOUND.getContent(),
                    id.toString()
            );
            throw VetecyvException.create(userMessage,technicalMessage);
        }


    }
}