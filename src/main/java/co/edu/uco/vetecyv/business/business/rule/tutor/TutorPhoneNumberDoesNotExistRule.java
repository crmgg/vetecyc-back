package co.edu.uco.vetecyv.business.business.rule.tutor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumGeneric;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

public final class TutorPhoneNumberDoesNotExistRule implements Rule {

    private static final Rule instance = new TutorPhoneNumberDoesNotExistRule();

    private TutorPhoneNumberDoesNotExistRule() {
    }


    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if(ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.TUTOR_MOBILE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if(data.length < 2) {
            var userMessage = MessagesEnumGeneric.TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION.getContent();
            var technicalMessage = MessagesEnumGeneric.TUTOR_MOBILE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var phoneNumber = (String) data [0];
        var daoFactory = (DAOFactory) data [1];

        var filter = new TutorEntity();
        filter.setPhoneNumber(phoneNumber);

        var duplicatephoneNumber = daoFactory.getTutorDAO().findByFilter(filter);

        if(!duplicatephoneNumber.isEmpty()) {
            var userMessage = MessagesEnumGeneric.TUTOR_MOBILE_ALREADY_EXISTS.getContent();
            var technicalMessage = MessagesEnumGeneric.TUTOR_MOBILE_ALREADY_EXISTS.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

    }

}
