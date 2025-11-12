package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler;
import co.edu.uco.vetecyv.business.business.TutorBusiness;
import co.edu.uco.vetecyv.business.business.validator.tutor.ValidateDataTutorConsistencyForFilter;
import co.edu.uco.vetecyv.business.business.validator.tutor.ValidateDataTutorConsistencyForRegisterNewInformation;
import co.edu.uco.vetecyv.business.business.validator.tutor.ValidateTutorDoesNotExistsWithSameNumber;
import co.edu.uco.vetecyv.business.business.validator.tutor.ValidateTutorEmailDoesNotExists;
import co.edu.uco.vetecyv.business.business.validator.tutor.ValidateTutorPhoneNumberDoesNotExists;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

public final class TutorBusinessImpl implements TutorBusiness {

    private final DAOFactory daoFactory;

    public TutorBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public TutorDomain registerNewTutorInformation(TutorDomain tutorDomain) {

        ValidateDataTutorConsistencyForRegisterNewInformation.executeValidation(tutorDomain);

        // Validar que no exista tutor con mismo número de documento
        ValidateTutorDoesNotExistsWithSameNumber.executeValidation(tutorDomain.getIdentityDocument(), daoFactory);

        // Validar email y teléfono no existentes (usar el getter correcto: getPhoneNumber)
        ValidateTutorEmailDoesNotExists.executeValidation(tutorDomain.getEmail(), daoFactory);
        ValidateTutorPhoneNumberDoesNotExists.executeValidation(tutorDomain.getPhoneNumber(), daoFactory);

        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);

        // No generamos id aquí; lo genera la BD y el DAO asignará el id devuelto
        daoFactory.getTutorDAO().create(tutorEntity);

        // Convertir la entidad (ahora con id asignado) a dominio y devolverla
        return TutorEntityAssembler.getTutorEntityAssembler().toDomain(tutorEntity);

    }

    @Override
    public void dropTutorInformation(UUID id) {

        daoFactory.getTutorDAO().delete(id);

    }

    @Override
    public void updateTutorInformation(UUID id, TutorDomain tutorDomain) {

        daoFactory.getTutorDAO().findById(id);

        validateDuplicatedTutorOnUpdate(id, tutorDomain);

        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);

        tutorEntity.setId(id);

        daoFactory.getTutorDAO().update(tutorEntity);

    }

    private void validateDuplicatedTutorOnUpdate(final UUID id, final TutorDomain tutorDomain) {

        var tutorEntityFilter = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);
        var tutorDAO = daoFactory.getTutorDAO();

        var filterByIdentification = new co.edu.uco.vetecyv.entity.TutorEntity();
        filterByIdentification.setIdentityDocument(tutorEntityFilter.getIdentityDocument());

        var existingById = tutorDAO.findByFilter(filterByIdentification);
        if (!existingById.isEmpty() && !existingById.get(0).getId().equals(id)) {
            var userMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_IDENTITYDOCUMENT_ON_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_IDENTITYDOCUMENT_ON_UPDATE.getContent();
            throw co.edu.uco.vetecyv.crosscuting.exception.VetecyvException.create(userMessage, technicalMessage);
        }

        var filterByEmail = new co.edu.uco.vetecyv.entity.TutorEntity();
        filterByEmail.setEmail(tutorEntityFilter.getEmail());

        var existingByEmail = tutorDAO.findByFilter(filterByEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.get(0).getId().equals(id)) {
            var userMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getContent();
            throw co.edu.uco.vetecyv.crosscuting.exception.VetecyvException.create(userMessage, technicalMessage);
        }

        var filterByPhone = new co.edu.uco.vetecyv.entity.TutorEntity();
        filterByPhone.setPhoneNumber(tutorEntityFilter.getPhoneNumber());

        var existingByPhone = tutorDAO.findByFilter(filterByPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.get(0).getId().equals(id)) {
            var userMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE.getContent();
            throw co.edu.uco.vetecyv.crosscuting.exception.VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<TutorDomain> findAllTutors() {

        var tutorEntities = daoFactory.getTutorDAO().findAll();

        return TutorEntityAssembler.getTutorEntityAssembler().toDomainList(tutorEntities);

    }

    @Override
    public List<TutorDomain> findTutorsByFilter(TutorDomain tutorFilters) {

        ValidateDataTutorConsistencyForFilter.executeValidation(tutorFilters);

        var tutorFiltersEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorFilters);

        var tutorEntities = daoFactory.getTutorDAO().findByFilter(tutorFiltersEntity);

        return TutorEntityAssembler.getTutorEntityAssembler().toDomainList(tutorEntities);
    }

    @Override
    public TutorDomain findTutorById (UUID id) {

        var tutorEntity = daoFactory.getTutorDAO().findById(id);

        return TutorEntityAssembler.getTutorEntityAssembler().toDomain(tutorEntity);
    }


    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {
        // Se delega a reglas/servicios específicos cuando existan
        throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void confirmEmail(UUID id, int confirmationCode) {
        // Se delega a reglas/servicios específicos cuando existan
        throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void accountState(UUID id, int accountStateCode) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {
        // Placeholder: implementar envío real (SMS, etc.) en el futuro
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendEmailConfirmation(UUID id) {
        // Placeholder: implementar envío real (email) en el futuro
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendAccountStateConfirmation(UUID id) {
        // Placeholder: implementar envío real (notificación) en el futuro
        throw new UnsupportedOperationException("Not implemented yet");
    }


}
