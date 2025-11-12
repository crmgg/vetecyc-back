package co.edu.uco.vetecyv.business.business.rule.tutor;

import java.util.UUID;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

public final class TutorExistsByIdRule implements Rule {

    private static final Rule instance = new TutorExistsByIdRule();

    private TutorExistsByIdRule() {

    }

    public static void executeRule (final Object... data) {

        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 2) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumTutorRule.TECHNICAL_ERROR_WRONG_TUTOR_LENGTH_VALUE.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data [1];

        var user = daoFactory.getTutorDAO().findById(id);

        if(UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = MessagesEnumTutorRule.TUTOR_ERROR_TUTOR_DOES_NOT_EXITS.getContent();
            var technicalMessage = MessagesEnumTutorRule.TECHNICAL_ERROR_TUTOR_DOES_NOT_EXITS.getContent() + id;
            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }



}
