package co.edu.uco.vetecyv.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.dto.impl.AdministratorDTOAssembler;
import co.edu.uco.vetecyv.business.business.impl.AdministratorBusinessImpl;
import co.edu.uco.vetecyv.business.facade.AdministratorFacade;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.dto.AdministratorDTO;

public final class AdministratorFacadeImpl implements AdministratorFacade {

    @Override
    public void registerNewInformation(final AdministratorDTO administratorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = AdministratorDTOAssembler.getAdministratorDTOAssembler().toDomain(administratorDTO);

            business.registerNewAdministratorInformation(domain);

            daoFactory.commitTransaction();

        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_REGISTER.getTitle();
            var technicalMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_REGISTER.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }

    }


    @Override
    public void dropAdministratorInformation(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            business.dropAdministratorInformation(id);

            daoFactory.commitTransaction();

        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_DELETE.getTitle();
            var technicalMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_DELETE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void updateAdministratorInformation(final UUID id, final AdministratorDTO administratorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = AdministratorDTOAssembler.getAdministratorDTOAssembler().toDomain(administratorDTO);
            business.updateAdministratorInformation(id, domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_UPDATE.getTitle();
            var technicalMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_UPDATE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<AdministratorDTO> findAllAdministrators() {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domainList = business.findAllAdministrators();
            return AdministratorDTOAssembler.getAdministratorDTOAssembler().toDTO(domainList);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {

            var userMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getTitle();
            var technicalMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<AdministratorDTO> findAdministratorsByFilter(final AdministratorDTO filter) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domainFilter = AdministratorDTOAssembler.getAdministratorDTOAssembler().toDomain(filter);
            var domainList = business.findAdministratorsByFilter(domainFilter);
            return AdministratorDTOAssembler.getAdministratorDTOAssembler().toDTO(domainList);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getTitle(),
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getContent() + " - " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public AdministratorDTO findAdministratorById(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = business.findAdministratorById(id);
            return AdministratorDTOAssembler.getAdministratorDTOAssembler().toDTO(domain);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getTitle(),
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getContent() + " - " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirAccountStatus(final UUID id, final int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.accountState(id, confirmationCode);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getTitle(),
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getContent() + " - " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendPhoneNumberConfirmationCode(final UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.confirmMobileNumber(id, confirmationCode);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getTitle();
            var technicalMessage = MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendEmailConfirmationCode(final UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.confirmEmail(id, confirmationCode);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getTitle(),
                    MessagesEnum.ADMINISTRATOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getContent() + " - " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}
