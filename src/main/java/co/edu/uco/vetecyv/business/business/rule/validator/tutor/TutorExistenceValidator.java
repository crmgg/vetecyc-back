package co.edu.uco.vetecyv.business.business.rule.validator.tutor;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

public class TutorExistenceValidator {

    private TutorExistenceValidator() {}

    /**
     * Recupera la entidad Tutor por id. Lanza VetecyvException con el mensaje correspondiente si no existe.
     */
    public static TutorEntity ensureExists(final UUID id, final DAOFactory daoFactory) {
        final var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnum.TUTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnum.TUTOR_ERROR_NOT_FOUND.getContent());
        }
        return entity;
    }
}

