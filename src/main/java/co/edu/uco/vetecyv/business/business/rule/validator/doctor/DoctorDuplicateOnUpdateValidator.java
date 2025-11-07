package co.edu.uco.vetecyv.business.business.rule.validator.doctor;

import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.entity.impl.DoctorEntityAssembler;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.DoctorEntity;

@SuppressWarnings("unused")
public class DoctorDuplicateOnUpdateValidator {

    private DoctorDuplicateOnUpdateValidator() {}

    public static void validate(final UUID id, final DoctorDomain doctor, final DAOFactory daoFactory) {
        var doctorEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctor);
        var dao = daoFactory.getDoctorDAO();

        var byEmail = new DoctorEntity();
        byEmail.setEmail(doctorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.getFirst().getId().equals(id)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getContent());
        }

        var byPhone = new DoctorEntity();
        byPhone.setPhoneNumber(doctorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.getFirst().getId().equals(id)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getContent());
        }
    }
}

