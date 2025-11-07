package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.business.rule.validator.Validator;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

@SuppressWarnings("unused")
public class ValidateDataTutorEmailDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataTutorEmailDoesNotExist();

    private ValidateDataTutorEmailDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        // Se espera: data[0] = TutorDomain, data[1] = DAOFactory
        var tutor = (TutorDomain) data[0];
        var daoFactory = (DAOFactory) data[1];
        TutorDuplicateValidator.validate(tutor, daoFactory);
    }
}
