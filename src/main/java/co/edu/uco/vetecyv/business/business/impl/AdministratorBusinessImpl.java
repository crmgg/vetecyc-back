package co.edu.uco.vetecyv.business.business.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uco.vetecyv.business.assembler.entity.impl.AdministratorEntityAssembler;
import co.edu.uco.vetecyv.business.business.AdministratorBusiness;
import co.edu.uco.vetecyv.business.domain.AdministratorDomain;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

public final class AdministratorBusinessImpl implements AdministratorBusiness {

    private final DAOFactory daoFactory;

    public AdministratorBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewAdministratorInformation(final AdministratorDomain administratorDomain) {
        validateAdministratorData(administratorDomain);
        validateDuplicatedAdministrator(administratorDomain);

        UUID id = UUID.randomUUID();
        // Asegurar id único (si existe, generar otro)
        while (!ObjectHelper.isNull(daoFactory.getAdministratorDAO().findById(id))) {
            id = UUID.randomUUID();
        }

        var adminEntity = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(administratorDomain);
        adminEntity.setId(id);
        daoFactory.getAdministratorDAO().create(adminEntity);
    }

    @Override
    public void dropAdministratorInformation(final UUID id) {
        daoFactory.getAdministratorDAO().delete(id);
    }

    @Override
    public void updateAdministratorInformation(final UUID id, final AdministratorDomain administratorDomain) {
        var existing = daoFactory.getAdministratorDAO().findById(id);
        if (ObjectHelper.isNull(existing)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_NOT_FOUND.getTitle(), MessagesEnum.ADMIN_ERROR_NOT_FOUND.getContent());
        }

        validateAdministratorData(administratorDomain);
        validateDuplicatedAdministratorOnUpdate(id, administratorDomain);

        var adminEntity = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(administratorDomain);
        adminEntity.setId(id);
        daoFactory.getAdministratorDAO().update(adminEntity);
    }

    @Override
    public List<AdministratorDomain> findAllAdministrators() {
        var entities = daoFactory.getAdministratorDAO().findAll();
        return AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomainList(entities);
    }

    @Override
    public List<AdministratorDomain> findAdministratorsByFilter(final AdministratorDomain administratorFilters) {
        var filterEntity = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(administratorFilters);
        var entities = daoFactory.getAdministratorDAO().findByFilter(filterEntity);
        return AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomainList(entities);
    }

    @Override
    public AdministratorDomain findAdministratorById(final UUID id) {
        var entity = daoFactory.getAdministratorDAO().findById(id);
        return AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomain(entity);
    }

    @Override
    public void confirmMobileNumber(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getAdministratorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_NOT_FOUND.getTitle(), MessagesEnum.ADMIN_ERROR_NOT_FOUND.getContent());
        }

        // Validación del código debe implementarse si se persiste; aquí sólo se marca confirmado.
        var domain = AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomain(entity);
        domain.setPhoneConfirmation(true);

        var updated = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getAdministratorDAO().update(updated);
    }

    @Override
    public void confirmEmail(final UUID id, final int confirmationCode) {
        var entity = daoFactory.getAdministratorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_NOT_FOUND.getTitle(), MessagesEnum.ADMIN_ERROR_NOT_FOUND.getContent());
        }

        // Validación del código debe implementarse si se persiste; aquí sólo se marca confirmado.
        var domain = AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomain(entity);
        domain.setEmailConfirmation(true);

        var updated = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getAdministratorDAO().update(updated);
    }

    @Override
    public void accountState(final UUID id, final int accountStateCode) {
        var entity = daoFactory.getAdministratorDAO().findById(id);
        if (ObjectHelper.isNull(entity)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_NOT_FOUND.getTitle(), MessagesEnum.ADMIN_ERROR_NOT_FOUND.getContent());
        }

        var domain = AdministratorEntityAssembler.getAdministratorEntityAssembler().toDomain(entity);
        domain.setAccountState(accountStateCode == 1);
        var updated = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(domain);
        updated.setId(id);
        daoFactory.getAdministratorDAO().update(updated);
    }

    /* Helpers privados */

    private void validateAdministratorData(final AdministratorDomain admin) {
        if (ObjectHelper.isNull(admin)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID.getContent());
        }

        if (TextHelper.isEmptyWithTrim(admin.getName()) || admin.getName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_NAME.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_NAME.getContent());
        }

        if (TextHelper.isEmptyWithTrim(admin.getFirstLastName()) || admin.getFirstLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_FIRSTLASTNAME.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_FIRSTLASTNAME.getContent());
        }

        if (TextHelper.isEmptyWithTrim(admin.getSecondLastName()) || admin.getSecondLastName().length() > 50) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_SECONDLASTNAME.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_SECONDLASTNAME.getContent());
        }

        if (!TextHelper.isValidEmail(admin.getEmail()) || admin.getEmail().length() > 100) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_EMAIL.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_EMAIL.getContent());
        }

        if (TextHelper.isEmptyWithTrim(admin.getPassword()) || admin.getPassword().length() > 26) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_PASSWORD.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_PASSWORD.getContent());
        }

        if (!TextHelper.isValidPhoneNumber(admin.getPhoneNumber()) || admin.getPhoneNumber().length() > 13) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_INVALID_PHONE.getTitle(), MessagesEnum.ADMIN_ERROR_INVALID_PHONE.getContent());
        }
    }

    private void validateDuplicatedAdministrator(final AdministratorDomain admin) {
        var adminEntity = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(admin);
        var dao = daoFactory.getAdministratorDAO();

        var byEmail = new AdministratorEntity();
        byEmail.setEmail(adminEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty()) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_DUPLICATED_EMAIL.getTitle(), MessagesEnum.ADMIN_ERROR_DUPLICATED_EMAIL.getContent());
        }

        var byPhone = new AdministratorEntity();
        byPhone.setPhoneNumber(adminEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty()) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_DUPLICATED_PHONE.getTitle(), MessagesEnum.ADMIN_ERROR_DUPLICATED_PHONE.getContent());
        }
    }

    private void validateDuplicatedAdministratorOnUpdate(final UUID id, final AdministratorDomain admin) {
        var adminEntity = AdministratorEntityAssembler.getAdministratorEntityAssembler().toEntity(admin);
        var dao = daoFactory.getAdministratorDAO();

        var byEmail = new AdministratorEntity();
        byEmail.setEmail(adminEntity.getEmail());
        var existingByEmail = dao.findByFilter(byEmail);
        if (!existingByEmail.isEmpty() && !existingByEmail.get(0).getId().equals(id)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getTitle(), MessagesEnum.ADMIN_ERROR_DUPLICATED_EMAIL_ON_UPDATE.getContent());
        }

        var byPhone = new AdministratorEntity();
        byPhone.setPhoneNumber(adminEntity.getPhoneNumber());
        var existingByPhone = dao.findByFilter(byPhone);
        if (!existingByPhone.isEmpty() && !existingByPhone.get(0).getId().equals(id)) {
            throw VetecyvException.create(MessagesEnum.ADMIN_ERROR_DUPLICATED_PHONE_ON_UPDATE.getTitle(), MessagesEnum.ADMIN_ERROR_DUPLICATED_PHONE_ON_UPDATE.getContent());
        }
    }

}