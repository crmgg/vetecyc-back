package co.edu.uco.vetecyv.business.business.rule.administrator;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumAdministratorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

import java.util.List;

public class AdministratorDoesNotExistWithSameIdNumberRule implements Rule {

    private static final Rule instance = new AdministratorDoesNotExistWithSameIdNumberRule();

    private AdministratorDoesNotExistWithSameIdNumberRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {
        // Validaciones preliminares
        if (data == null) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) { // aceptamos 2 o 3 parámetros para mantener compatibilidad
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String idNumber;
        final DAOFactory daoFactory;

        try {
            idNumber = (String) data[0];
            // Antes se usaba una condición que tomaba data[1] o data[2].
            // Para soportar llamadas con 2 o 3 parámetros (por compatibilidad) siempre tomamos el último parámetro
            // como el DAOFactory. De esta forma, si se llama con (idNumber, daoFactory) o con (idNumber, idType, daoFactory)
            // el daoFactory será correctamente obtenido.
            daoFactory = (DAOFactory) data[data.length - 1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        // Comprobaciones
        if (TextHelper.isEmptyWithTrim(idNumber) || daoFactory == null) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var administratorFilter = new AdministratorEntity();
        administratorFilter.setIdentityDocument(TextHelper.getDefaultWithTrim(idNumber));

        List<AdministratorEntity> results = List.of();
        if (daoFactory.getAdministratorDAO() != null) {
            results = daoFactory.getAdministratorDAO().findByFilter(administratorFilter);
        }

        var administrator = results.stream()
                .findFirst()
                .orElse(new AdministratorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(administrator.getId())) {
            var userMessage = MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_ADMINISTRATOR_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumAdministratorRule.ADMINISTRATOR_RULE_ADMINISTRATOR_ALREADY_EXISTS.getContent(),
                    idNumber
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}