package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler;
import co.edu.uco.vetecyv.business.business.TutorBusiness;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.TutorEntity;

public final class TutorBusinessImpl implements TutorBusiness {

    private final DAOFactory daoFactory;

    public TutorBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewTutorInformation(final TutorDomain tutorDomain) {
        validateTutorData(tutorDomain);
        validateDuplicatedTutor(tutorDomain);

        UUID id = UUID.randomUUID();
        // Asegurar id único (si existe, generar otro)
        while (ObjectHelper.isNull(daoFactory.getTutorDAO().findById(id))) {
            id = UUID.randomUUID();
        }

        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutorDomain);
        tutorEntity.setId(id);
        daoFactory.getTutorDAO().create(tutorEntity);
    }

    @Override
    public void dropTutorInformation(final UUID id) {
        daoFactory.getTutorDAO().delete(id);
    }

    @Override
    public void updateTutorInformation(final UUID id, final TutorDomain tutorDomain) {
        var existing = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(existing)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }

        validateTutorData(tutorDomain);
        validateDuplicatedTutorOnUpdate(id, tutorDomain);

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
        var entity = daoFactory.getTutorDAO().findById(id);
        return TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
    }

    @Override
    public void confirmMobileNumber(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }

        // Validación del código no implementada (adaptar si se persiste código)
        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setPhoneConfirmation(true);

        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }

    @Override
    public void confirmEmail(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }

        // Validación del código no implementada (adaptar si se persiste código)
        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setEmailConfirmation(true);

        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }

    @Override
    public void accountState(final UUID id, final int accountStateCode) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }

        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        domain.setAccountState(accountStateCode == 1);
        var updated = TutorEntityAssembler.getTutorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getTutorDAO().update(updated);
    }

    /* Placeholders para envío de códigos */

    public void sendMobileNumberConfirmation(final UUID id) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación móvil %d al tutor %s%n", code, entity.getPhoneNumber());
    }

    public void sendEmailConfirmation(final UUID id) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación por email %d al tutor %s%n", code, entity.getEmail());
    }

    public void sendAccountState(final UUID id) {
        var entity = daoFactory.getTutorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Tutor no encontrado", "No existe tutor con id proporcionado");
        }
        var domain = TutorEntityAssembler.getTutorEntityAssembler().toDomain(entity);
        System.out.printf("Notificar estado de cuenta (%s) al tutor %s / %s%n",
                domain.isAccountState() ? "ACTIVO" : "INACTIVO", domain.getName(), domain.getEmail());
    }

    /* Helpers privados */

    private void validateTutorData(final TutorDomain tutor) {
        if (ObjectHelper.isNull(tutor)) {
            throw VetecyvException.create("Tutor inválido", "El objeto TutorDomain es nulo");
        }

        if (TextHelper.isEmptyWithTrim(tutor.getName()) || tutor.getName().length() > 50) {
            throw VetecyvException.create("Nombre inválido", "Nombre del tutor nulo o demasiado largo");
        }

        if (TextHelper.isEmptyWithTrim(tutor.getFirstLastName()) || tutor.getFirstLastName().length() > 50) {
            throw VetecyvException.create("Primer apellido inválido", "Primer apellido nulo o demasiado largo");
        }

        if (TextHelper.isEmptyWithTrim(tutor.getSecondLastName()) || tutor.getSecondLastName().length() > 50) {
            throw VetecyvException.create("Segundo apellido inválido", "Segundo apellido nulo o demasiado largo");
        }

        if (!TextHelper.isValidEmail(tutor.getEmail()) || tutor.getEmail().length() > 250) {
            throw VetecyvException.create("Email inválido", "Email nulo o con formato incorrecto");
        }

        if (TextHelper.isEmptyWithTrim(tutor.getPassword()) || tutor.getPassword().length() > 100) {
            throw VetecyvException.create("Password inválido", "Password nulo o demasiado largo");
        }

        if (!TextHelper.isValidPhoneNumber(tutor.getPhoneNumber()) || tutor.getPhoneNumber().length() > 20) {
            throw VetecyvException.create("Teléfono inválido", "Número de teléfono nulo o con formato incorrecto");
        }
    }

    private void validateDuplicatedTutor(final TutorDomain tutor) {
        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutor);
        var dao = daoFactory.getTutorDAO();

        var byEmail = new TutorEntity();
        byEmail.setEmail(tutorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty()) {
            throw VetecyvException.create("Email duplicado", "Ya existe un tutor con ese email");
        }

        var byPhone = new TutorEntity();
        byPhone.setPhoneNumber(tutorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty()) {
            throw VetecyvException.create("Teléfono duplicado", "Ya existe un tutor con ese número de teléfono");
        }
    }

    private void validateDuplicatedTutorOnUpdate(final UUID id, final TutorDomain tutor) {
        var tutorEntity = TutorEntityAssembler.getTutorEntityAssembler().toEntity(tutor);
        var dao = daoFactory.getTutorDAO();

        var byEmail = new TutorEntity();
        byEmail.setEmail(tutorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.get(0).getId().equals(id)) {
            throw VetecyvException.create("Email duplicado", "Otro tutor ya usa ese email");
        }

        var byPhone = new TutorEntity();
        byPhone.setPhoneNumber(tutorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.get(0).getId().equals(id)) {
            throw VetecyvException.create("Teléfono duplicado", "Otro tutor ya usa ese teléfono");
        }
    }
}