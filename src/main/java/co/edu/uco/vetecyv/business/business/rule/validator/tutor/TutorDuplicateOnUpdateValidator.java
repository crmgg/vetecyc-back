package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

@SuppressWarnings("unused")
public class TutorDuplicateOnUpdateValidator {

    private TutorDuplicateOnUpdateValidator() {}

    public static void validate(final UUID id, final TutorDomain tutor, final DAOFactory daoFactory) {
        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutor);
        var dao = daoFactory.getTutorDAO();

        var byEmail = new TutorEntity();
        byEmail.setEmail(tutorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.getFirst().getId().equals(id)) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getTitle(), MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getContent());
        }

        var byPhone = new TutorEntity();
        byPhone.setPhoneNumber(tutorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.getFirst().getId().equals(id)) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getTitle(), MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getContent());
        }
    }
}
