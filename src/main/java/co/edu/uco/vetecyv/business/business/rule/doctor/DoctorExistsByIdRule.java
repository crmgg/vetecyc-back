package co.edu.uco.vetecyv.business.business.rule.doctor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.DoctorEntity;

import java.util.UUID;

public class DoctorExistsByIdRule implements Rule {

    private static final Rule instance = new DoctorExistsByIdRule();

    private DoctorExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
        if (data.length < 2){
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_LENGTH_INVALID.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        final UUID id;
        final DAOFactory daoFactory;

        try {
            id = (UUID) data[0];
            daoFactory = (DAOFactory) data[1];
        } catch (ClassCastException ex) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        DoctorEntity doctor = daoFactory.getDoctorDAO().findById(id);

        if (ObjectHelper.isNull(doctor) || UUIDHelper.getUUIDHelper().isDefaultUUID(doctor.getId())) {

            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_NOT_FOUND.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumDoctorRule.DOCTOR_RULE_NOT_FOUND.getContent(),
                    id.toString()
            );
            throw VetecyvException.create(userMessage,technicalMessage);
        }


    }
}