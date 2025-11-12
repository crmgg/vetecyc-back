package co.edu.uco.vetecyv.business.business.rule.generics.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

import java.util.List;

public final class TutorDoesNotExistWithSameIdNumberRule implements Rule {

    private static final Rule INSTANCE = new TutorDoesNotExistWithSameIdNumberRule();

    private TutorDoesNotExistWithSameIdNumberRule() {
    }

    public static void executeRule(final Object... data) {
        INSTANCE.execute(data);
    }

    @Override
    public void execute(Object... data) {
        // Validaciones preliminares
        if (data == null) {
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) { // aceptamos 2 o 3 parámetros para mantener compatibilidad
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String idNumber;
        final DAOFactory daoFactory;

        try {
            idNumber = (String) data[0];
            // Tomamos siempre el último parámetro como DAOFactory para soportar llamadas con
            // (idNumber, daoFactory) o (idNumber, idType, daoFactory) sin interpretar el idType.
            daoFactory = (DAOFactory) data[data.length - 1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_MOBILE_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        // Comprobaciones
        if (TextHelper.isEmptyWithTrim(idNumber) || daoFactory == null) {
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var tutorFilter = new TutorEntity();
        tutorFilter.setIdentityDocument(TextHelper.getDefaultWithTrim(idNumber));

        List<TutorEntity> results = List.of();
        if (daoFactory.getTutorDAO() != null) {
            results = daoFactory.getTutorDAO().findByFilter(tutorFilter);
        }

        var tutor = results.stream()
                .findFirst()
                .orElse(new TutorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(tutor.getId())) {
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_TUTOR_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumTutorRule.TUTOR_RULE_TUTOR_ALREADY_EXISTS.getContent(),
                    idNumber
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}