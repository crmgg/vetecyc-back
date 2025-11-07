package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler;
import co.edu.uco.vetecyv.business.business.TutorBusiness;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

// Import de los validadores: usar los nuevos en business.business.rule.validator.tutor
import co.edu.uco.vetecyv.business.business.rule.validator.tutor.TutorDataValidator;
import co.edu.uco.vetecyv.business.business.rule.validator.tutor.TutorDuplicateValidator;
import co.edu.uco.vetecyv.business.business.rule.validator.tutor.TutorDuplicateOnUpdateValidator;
import co.edu.uco.vetecyv.business.business.rule.validator.tutor.TutorExistenceValidator;

public final class TutorBusinessImpl implements TutorBusiness {

    private final DAOFactory daoFactory;

    public TutorBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewTutorInformation(final TutorDomain tutorDomain) {
        TutorDataValidator.validate(tutorDomain);
        TutorDuplicateValidator.validate(tutorDomain, daoFactory);

        UUID id = UUID.randomUUID();
        while (!ObjectHelper.isNull(daoFactory.getTutorDAO().findById(id))) {
            id = UUID.randomUUID();
        }

        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);
        tutorEntity.setId(id);
        daoFactory.getTutorDAO().create(tutorEntity);
    }

    @Override
    public void dropTutorInformation(final UUID id) {
        // Delegar la verificación de existencia al validador
        TutorExistenceValidator.ensureExists(id, daoFactory);
        daoFactory.getTutorDAO().delete(id);
    }

    @Override
    public void updateTutorInformation(final UUID id, final TutorDomain tutorDomain) {
        // Delegar verificación de existencia
        TutorExistenceValidator.ensureExists(id, daoFactory);

        // Llamadas directas a los validadores (datos + duplicados en update)
        TutorDataValidator.validate(tutorDomain);
        TutorDuplicateOnUpdateValidator.validate(id, tutorDomain, daoFactory);

        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);
        tutorEntity.setId(id);
        daoFactory.getTutorDAO().update(tutorEntity);
    }

    @Override
    public List<TutorDomain> findAllTutors() {
        var entities = daoFactory.getTutorDAO().findAll();
        return TutorEntityAssembler.getTutorEntityAssembler().toDomainList(entities);
    }

    @Override
    public List<TutorDomain> findTutorsByFilter(final TutorDomain tutorFilters) {
        var filterEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorFilters);
        var entities = daoFactory.getTutorDAO().findByFilter(filterEntity);
        return TutorEntityAssembler.getTutorEntityAssembler().toDomainList(entities);
    }

    @Override
    public TutorDomain findTutorById(final UUID id) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);
        return TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
    }

    @Override
    public void confirmMobileNumber(final UUID id, final int confirmationCode) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);

        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setPhoneConfirmation(true);

        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }

    @Override
    public void confirmEmail(final UUID id, final int confirmationCode) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);

        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setEmailConfirmation(true);

        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }

    @Override
    public void accountState(final UUID id, final int accountStateCode) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);

        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setAccountState(accountStateCode == 1);
        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }


    public void sendMobileNumberConfirmation(final UUID id) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación móvil %d al tutor %s%n", code, entity.getPhoneNumber());
    }

    public void sendEmailConfirmation(final UUID id) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación por email %d al tutor %s%n", code, entity.getEmail());
    }

    public void sendAccountState(final UUID id) {
        var entity = TutorExistenceValidator.ensureExists(id, daoFactory);
        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        System.out.printf("Notificar estado de cuenta (%s) al tutor %s / %s%n",
                domain.isAccountState() ? "ACTIVO" : "INACTIVO", domain.getName(), domain.getEmail());
    }
}