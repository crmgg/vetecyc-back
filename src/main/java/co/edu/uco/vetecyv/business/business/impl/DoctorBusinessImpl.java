package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uco.vetecyv.business.assembler.entity.impl.DoctorEntityAssembler;
import co.edu.uco.vetecyv.business.business.DoctorBusiness;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.DoctorEntity;

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
        // Reintentar mientras el id ya exista
        while (ObjectHelper.isNull(daoFactory.getDoctorDAO().findById(id))) {
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
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
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
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
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
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
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
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
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
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación móvil %d al doctor %s%n", code, entity.getPhoneNumber());
    }

    public void sendEmailConfirmation(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
        }
        int code = ThreadLocalRandom.current().nextInt(100000, 1_000_000);
        System.out.printf("Enviar código de confirmación por email %d al doctor %s%n", code, entity.getEmail());
    }

    public void sendAccountState(final UUID id) {
        var entity = daoFactory.getDoctorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create("Doctor no encontrado", "No existe doctor con id proporcionado");
        }
        var domain = DoctorEntityAssembler.getDoctorEntityAssembler().toDomain(entity);
        System.out.printf("Notificar estado de cuenta (%s) al doctor %s / %s%n",
                domain.isAccountState() ? "ACTIVO" : "INACTIVO", domain.getName(), domain.getEmail());
    }

    /* Helpers privados */

    private void validateDoctorData(final DoctorDomain doctor) {
        if (ObjectHelper.isNull(doctor)) {
            throw VetecyvException.create("Doctor inválido", "El objeto DoctorDomain es nulo");
        }

        if (TextHelper.isEmptyWithTrim(doctor.getName()) || doctor.getName().length() > 50) {
            throw VetecyvException.create("Nombre inválido", "Nombre del doctor nulo o demasiado largo");
        }

        if (TextHelper.isEmptyWithTrim(doctor.getFirstLastName()) || doctor.getFirstLastName().length() > 50) {
            throw VetecyvException.create("Primer apellido inválido", "Primer apellido nulo o demasiado largo");
        }

        if (TextHelper.isEmptyWithTrim(doctor.getSecondLastName()) || doctor.getSecondLastName().length() > 50) {
            throw VetecyvException.create("Segundo apellido inválido", "Segundo apellido nulo o demasiado largo");
        }

        if (!TextHelper.isValidEmail(doctor.getEmail()) || doctor.getEmail().length() > 250) {
            throw VetecyvException.create("Email inválido", "Email nulo o con formato incorrecto");
        }

        if (TextHelper.isEmptyWithTrim(doctor.getPassword()) || doctor.getPassword().length() > 100) {
            throw VetecyvException.create("Password inválido", "Password nulo o demasiado largo");
        }

        if (!TextHelper.isValidPhoneNumber(doctor.getPhoneNumber()) || doctor.getPhoneNumber().length() > 20) {
            throw VetecyvException.create("Teléfono inválido", "Número de teléfono nulo o con formato incorrecto");
        }
    }

    private void validateDuplicatedDoctor(final DoctorDomain doctor) {
        var doctorEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctor);
        var dao = daoFactory.getDoctorDAO();

        var byEmail = new DoctorEntity();
        byEmail.setEmail(doctorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty()) {
            throw VetecyvException.create("Email duplicado", "Ya existe un doctor con ese email");
        }

        var byPhone = new DoctorEntity();
        byPhone.setPhoneNumber(doctorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty()) {
            throw VetecyvException.create("Teléfono duplicado", "Ya existe un doctor con ese número de teléfono");
        }
    }

    private void validateDuplicatedDoctorOnUpdate(final UUID id, final DoctorDomain doctor) {
        var doctorEntity = DoctorEntityAssembler.getDoctorEntityAssembler().toEntity(doctor);
        var dao = daoFactory.getDoctorDAO();

        var byEmail = new DoctorEntity();
        byEmail.setEmail(doctorEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.get(0).getId().equals(id)) {
            throw VetecyvException.create("Email duplicado", "Otro doctor ya usa ese email");
        }

        var byPhone = new DoctorEntity();
        byPhone.setPhoneNumber(doctorEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.get(0).getId().equals(id)) {
            throw VetecyvException.create("Teléfono duplicado", "Otro doctor ya usa ese teléfono");
        }
    }
}