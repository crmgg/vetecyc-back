package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

public class TutorDuplicateValidator {

    private TutorDuplicateValidator() {}

    public static void validate(final TutorDomain tutor, final DAOFactory daoFactory) {
        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutor);
        var dao = daoFactory.getTutorDAO();

        var byIdentityDocument = new TutorEntity();
        byIdentityDocument.setIdentityDocument(tutorEntity.getIdentityDocument());
        var existingByIdentityDocument = dao.findByFilter(byIdentityDocument);
        if (!existingByIdentityDocument.isEmpty()) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_DUPLICATED_IDENTITYDOCUMENT.getTitle(), MessagesEnum.TUTOR_ERROR_DUPLICATED_IDENTITYDOCUMENT.getContent());
        }

        var byEmail = new TutorEntity();
        byEmail.setEmail(tutorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty()) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL.getTitle(), MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL.getContent());
        }

        var byPhone = new TutorEntity();
        byPhone.setPhoneNumber(tutorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty()) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE.getTitle(), MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE.getContent());
        }
    }
}
