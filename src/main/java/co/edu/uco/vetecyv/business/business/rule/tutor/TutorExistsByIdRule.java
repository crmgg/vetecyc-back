package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumTutorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

import java.util.UUID;

public class TutorExistsByIdRule implements Rule {

    private static final Rule instance = new TutorExistsByIdRule();

    private TutorExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
        if (data.length < 2){
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final UUID id;
        final DAOFactory daoFactory;

        try {
            id = (UUID) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumTutorRule.TUTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var tutor = daoFactory.getTutorDAO().findById(id);

        if (ObjectHelper.isNull(tutor) || UUIDHelper.getUUIDHelper().isDefaultUUID(tutor.getId())) {

            var userMessage = MessagesEnumTutorRule.TUTOR_RULE_NOT_FOUND.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumTutorRule.TUTOR_RULE_NOT_FOUND.getContent(),
                    id.toString()
            );
            throw VetecyvException.create(userMessage,technicalMessage);
        }


    }
}