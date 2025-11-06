package co.edu.uco.vetecyv.business.business.rule.administrator;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumAdministratorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

import java.util.List;

public class AdministratorEmailDoesNotExistRule implements Rule {

    private static final Rule instance = new AdministratorEmailDoesNotExistRule();

    private AdministratorEmailDoesNotExistRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String email;
        final DAOFactory daoFactory;

        try {
            email = (String) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (TextHelper.isEmptyWithTrim(email) || daoFactory == null) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var administratorFilter = new AdministratorEntity();
        administratorFilter.setEmail(TextHelper.getDefaultWithTrim(email));

        List<AdministratorEntity> results = List.of();
        if (daoFactory.getAdministratorDAO() != null) {
            results = daoFactory.getAdministratorDAO().findByFilter(administratorFilter);
        }

        var administrator = results.stream()
                .findFirst()
                .orElse(new AdministratorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(administrator.getId())) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumAdministratorRule.ADMINISTRATOR_EMAIL_ALREADY_EXISTS.getContent(),
                    email
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}