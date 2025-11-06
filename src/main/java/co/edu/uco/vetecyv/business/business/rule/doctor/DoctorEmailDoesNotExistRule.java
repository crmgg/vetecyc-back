package co.edu.uco.vetecyv.business.business.rule.doctor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.DoctorEntity;

import java.util.List;

public class DoctorEmailDoesNotExistRule implements Rule {

    private static final Rule instance = new DoctorEmailDoesNotExistRule();

    private DoctorEmailDoesNotExistRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final String email;
        final DAOFactory daoFactory;

        try {
            email = (String) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (TextHelper.isEmptyWithTrim(email) || daoFactory == null) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var doctorFilter = new DoctorEntity();
        doctorFilter.setEmail(TextHelper.getDefaultWithTrim(email));

        List<DoctorEntity> results = List.of();
        if (daoFactory.getDoctorDAO() != null) {
            results = daoFactory.getDoctorDAO().findByFilter(doctorFilter);
        }

        var doctor = results.stream()
                .findFirst()
                .orElse(new DoctorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(doctor.getId())) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_EMAIL_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumDoctorRule.DOCTOR_EMAIL_ALREADY_EXISTS.getContent(),
                    email
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}