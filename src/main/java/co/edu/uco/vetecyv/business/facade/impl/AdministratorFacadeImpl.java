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

            var domain = AdministratorDTOAssembler.getInstance().toDomainFromDTO(administratorDTO);
            business.registerNewInformation(domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMIN_ERROR_UNEXPECTED_EXCEPTION_REGISTERING.getContent(),
                    MessagesEnum.ADMIN_TECHNICAL_UNEXPECTED_EXCEPTION_REGISTERING.getContent() + " - " + exception.getMessage()
            );
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
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado eliminando el administrador.",
                    "Unexpected technical error deleting administrator: " + exception.getMessage()
            );
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

            var domain = AdministratorDTOAssembler.getInstance().toDomainFromDTO(administratorDTO);
            business.updateAdministratorInformation(id, domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado actualizando el administrador.",
                    "Unexpected technical error updating administrator: " + exception.getMessage()
            );
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
            return AdministratorDTOAssembler.getInstance().toDTOList(domainList);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    exception,
                    MessagesEnum.ADMIN_ERROR_UNEXPECTED_EXCEPTION_FINDING.getContent(),
                    "Unexpected technical error finding administrators: " + exception.getMessage()
            );
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

            var domainFilter = AdministratorDTOAssembler.getInstance().toDomainFromDTO(filter);
            var domainList = business.findAdministratorsByFilter(domainFilter);
            return AdministratorDTOAssembler.getInstance().toDTOList(domainList);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado consultando administradores por filtro.",
                    "Unexpected technical error finding administrators by filter: " + exception.getMessage()
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
            return AdministratorDTOAssembler.getInstance().toDTO(domain);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado consultando el administrador.",
                    "Unexpected technical error finding administrator: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmPhoneNumber(final UUID id, final int confirmationCode) {
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
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado confirmando el teléfono del administrador.",
                    "Unexpected technical error confirming administrator phone: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendEmailConfirmationCode(final UUID id, final int confirmationCode) {
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
                    "Se presentó un error inesperado confirmando el email del administrador.",
                    "Unexpected technical error confirming administrator email: " + exception.getMessage()
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
                    "Se presentó un error inesperado confirmando el estado de la cuenta del administrador.",
                    "Unexpected technical error confirming administrator account status: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendPhoneNumberConfirmationCode(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.sendPhoneNumberConfirmationCode(id);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado enviando código de confirmación telefónico.",
                    "Unexpected technical error sending phone confirmation code: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendEmailConfirmationCode(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.sendEmailConfirmationCode(id);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado enviando código de confirmación por email.",
                    "Unexpected technical error sending email confirmation code: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendAccountStatusConfirmationCode(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            business.sendAccountStatusConfirmationCode(id);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(
                    exception,
                    "Se presentó un error inesperado enviando código de confirmación de estado de cuenta.",
                    "Unexpected technical error sending account status confirmation code: " + exception.getMessage()
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}