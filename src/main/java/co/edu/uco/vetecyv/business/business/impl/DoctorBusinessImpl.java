package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uco.vetecyv.business.assembler.entity.impl.DoctorEntityAssembler;
import co.edu.uco.vetecyv.business.business.DoctorBusiness;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnumDoctorRule;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.business.business.rule.validator.doctor.DoctorDataValidator;
import co.edu.uco.vetecyv.business.business.rule.validator.doctor.ValidateDoctorDoesNotExistWithSameIdNumberAndIdType;
import co.edu.uco.vetecyv.business.business.rule.validator.doctor.ValidateDataDoctorEmailDoesNotExist;
import co.edu.uco.vetecyv.business.business.rule.validator.doctor.ValidateDataDoctorMobileDoesNotExist;

public final class DoctorBusinessImpl implements DoctorBusiness {

    private final DAOFactory daoFactory;

    public DoctorBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewDoctorInformation(final DoctorDomain doctorDomain) {
        validateDoctorData(doctorDomain);
        validateDuplicatedDoctor(doctorDomain);

        UUID id = UUID.randomUUID();
        while (!ObjectHelper.isNull(daoFactory.getDoctorDAO().findById(id))) {
            id = UUID.randomUUID();
        }

        var doctorEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctorDomain);
        doctorEntity.setId(id);
        daoFactory.getDoctorDAO().create(doctorEntity);
    }

    @Override
    public void dropDoctorInformation(final UUID id) {
        daoFactory.getDoctorDAO().delete(id);
    }

    @Override
    public void updateDoctorInformation(final UUID id, final DoctorDomain doctorDomain) {
        var existing = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(existing)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }

        validateDoctorData(doctorDomain);
        validateDuplicatedDoctorOnUpdate(id, doctorDomain);

        var doctorEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctorDomain);
        doctorEntity.setId(id);
        daoFactory.getDoctorDAO().update(doctorEntity);
    }

    @Override
    public List<DoctorDomain> findAllDoctors() {
        var entities = daoFactory.getDoctorDAO().findAll();
        return DoctorEntityAssembler.getDoctorEntityAssembler().toDomainList(entities);
    }

    @Override
    public List<DoctorDomain> findDoctorsByFilter(final DoctorDomain doctorFilters) {
        var filterEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctorFilters);
        var entities = daoFactory.getDoctorDAO().findByFilter(filterEntity);
        return DoctorEntityAssembler.getDoctorEntityAssembler().toDomainList(entities);
    }

    @Override
    public DoctorDomain findDoctorById(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        return DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
    }

    @Override
    public void confirmMobileNumber(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }

        // Validación del código debe implementarse si se persiste; aquí sólo se marca confirmado.
        var domain = DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
        domain.setPhoneConfirmation(true);

        var updated = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getDoctorDAO().update(updated);
    }

    @Override
    public void confirmEmail(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }

        // Validación del código debe implementarse si se persiste; aquí sólo se marca confirmado.
        var domain = DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
        domain.setEmailConfirmation(true);

        var updated = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getDoctorDAO().update(updated);
    }

    @Override
    public void accountState(final UUID id, final int accountStateCode) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }

        var domain = DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
        domain.setAccountState(accountStateCode == 1);
        var updated = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getDoctorDAO().update(updated);
    }

    /* Placeholders públicos para envío de códigos / notificaciones (no forman parte de la interfaz) */

    public void sendMobileNumberConfirmation(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación móvil %d al doctor %s%n", code, entity.getPhoneNumber());
    }

    public void sendEmailConfirmation(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación por email %d al doctor %s%n", code, entity.getEmail());
    }

    public void sendAccountState(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getTitle(), MessagesEnumDoctorRule.DOCTOR_ERROR_NOT_FOUND.getContent());
        }
        var domain = DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
        System.out.printf("Notificar estado de cuenta (%s) al doctor %s / %s%n",
                domain.isAccountState() ? "ACTIVO" : "INACTIVO", domain.getName(), domain.getEmail());
    }

    /* Helpers privados */

    private void validateDoctorData(final DoctorDomain doctor) {
        DoctorDataValidator.validate(doctor);
    }

    private void validateDuplicatedDoctor(final DoctorDomain doctor) {
        // Usar validadores existentes para evitar duplicar lógica y mensajes hard-coded
        ValidateDoctorDoesNotExistWithSameIdNumberAndIdType.executeValidation(doctor.getIdentityDocument(), daoFactory);
        ValidateDataDoctorEmailDoesNotExist.executeValidation(doctor.getEmail(), daoFactory);
        ValidateDataDoctorMobileDoesNotExist.executeValidation(doctor.getPhoneNumber(), daoFactory);
    }

    private void validateDuplicatedDoctorOnUpdate(final UUID id, final DoctorDomain doctor) {
        // Delegar la validación específica de actualización al validador (fully-qualified para evitar problemas de import)
        co.edu.uco.vetecyv.business.business.rule.validator.doctor.DoctorDuplicateOnUpdateValidator.validate(id, doctor, daoFactory);
    }
}