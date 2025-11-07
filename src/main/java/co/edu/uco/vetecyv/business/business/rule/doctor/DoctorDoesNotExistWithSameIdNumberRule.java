package co.edu.uco.vetecyv.business.business.rule.doctor;

import co.edu.uco.vetecyv.business.business.rule.Rule;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.DoctorEntity;

import java.util.List;

public class DoctorDoesNotExistWithSameIdNumberRule implements Rule {

    private static final DoctorDoesNotExistWithSameIdNumberRule INSTANCE = new DoctorDoesNotExistWithSameIdNumberRule();

    private DoctorDoesNotExistWithSameIdNumberRule() {
    }

    public static void executeRule(final Object... data) {
        INSTANCE.execute(data);
    }

    @Override
    public void execute(Object... data) {
        // Validaciones preliminares
        if (data == null) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) { // aceptamos 2 o 3 parámetros para mantener compatibilidad
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_LENGTH_INVALID.getContent();
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
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_INVALID_DATA_TYPES.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_INVALID_DATA_TYPES.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        // Comprobaciones
        if (TextHelper.isEmptyWithTrim(idNumber) || daoFactory == null) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DATA_IS_NULL.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        var doctorFilter = new DoctorEntity();
        doctorFilter.setIdentityDocument(TextHelper.getDefaultWithTrim(idNumber));

        List<DoctorEntity> results = List.of();
        if (daoFactory.getDoctorDAO() != null) {
            results = daoFactory.getDoctorDAO().findByFilter(doctorFilter);
        }

        var doctor = results.stream()
                .findFirst()
                .orElse(new DoctorEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(doctor.getId())) {
            var userMessage = MessagesEnumDoctorRule.DOCTOR_RULE_DOCTOR_ALREADY_EXISTS.getTitle();
            var technicalMessage = String.format(
                    MessagesEnumDoctorRule.DOCTOR_RULE_DOCTOR_ALREADY_EXISTS.getContent(),
                    idNumber
            );
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }
}