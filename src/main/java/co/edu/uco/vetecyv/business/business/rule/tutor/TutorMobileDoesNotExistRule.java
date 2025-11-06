package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

import java.util.List;

public class TutorMobileDoesNotExistRule implements Rule {

    private static final Rule instance = new TutorMobileDoesNotExistRule();

    private TutorMobileDoesNotExistRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String mobile;
        final DAOFactory daoFactory;

        try {
            mobile = (String) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_MOBILE_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (TextHelper.isEmptyWithTrim(mobile) || daoFactory == null) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_MOBILE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var tutorFilter = new TutorEntity();
        tutorFilter.setPhoneNumber(TextHelper.getDefaultWithTrim(mobile));

        List<TutorEntity> results = List.of();
        if (daoFactory.getTutorDAO() != null) {
            results = daoFactory.getTutorDAO().findByFilter(tutorFilter);
        }

        var tutor = results.stream()
                .findFirst()
                .orElse(new TutorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(tutor.getId())) {
            var userMessage = MessagesEnumTutorRule.TUTOR_MOBILE_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumTutorRule.TUTOR_MOBILE_ALREADY_EXISTS.getContent(),
                    mobile
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}