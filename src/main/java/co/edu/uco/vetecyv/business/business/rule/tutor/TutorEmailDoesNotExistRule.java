package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;

public final class TutorEmailDoesNotExistRule implements Rule {

    private static final Rule instance = new TutorEmailDoesNotExistRule();

    private TutorEmailDoesNotExistRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_EMAIL_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 2) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_EMAIL_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var email = (String) data [0];
        var daoFactory = (DAOFactory) data [1];

        var filter = new TutorEntity();
        filter.setEmail(email);

        var duplicateEmail = daoFactory.getTutorDAO().findByFilter(filter);

        if(!duplicateEmail.isEmpty()) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TUTOR_DOES_EXITS.getContent();
            var technicalMessage = MessagesEnumTutorRule.TECHNICAL_ERROR_TUTOR_DOES_EXISTS_BY_EMAIL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }

}
