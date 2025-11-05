package co.edu.uco.vetecyv.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.dto.impl.TutorDTOAssembler;
import co.edu.uco.vetecyv.business.business.impl.TutorBusinessImpl;
import co.edu.uco.vetecyv.business.facade.TutorFacade;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.dto.TutorDTO;

public final class TutorFacadeImpl implements TutorFacade {

    @Override
    public void registerNewInformation(TutorDTO tutorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = TutorDTOAssembler.getTutorDTOAssembler().toDomain(tutorDTO);
            business.registerNewTutorInformation(domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_MAPPING_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_TUTOR.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void dropTutorInformation(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.dropTutorInformation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_TUTOR.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void updateTutorInformation(UUID id, TutorDTO tutorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domain = TutorDTOAssembler.getTutorDTOAssembler().toDomain(tutorDTO);
            business.updateTutorInformation(id, domain);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_TUTOR.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TutorDTO> findAllTutors() {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domains = business.findAllTutors();
            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(domains);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado consultando tutores.",
                    "Unexpected technical error finding tutors: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TutorDTO> findTutorsByFilter(TutorDTO filter) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domainFilter = TutorDTOAssembler.getTutorDTOAssembler().toDomain(filter);
            var domains = business.findTutorsByFilter(domainFilter);
            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(domains);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado consultando tutores por filtro.",
                    "Unexpected technical error finding tutors by filter: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TutorDTO findTutorById(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domain = business.findTutorById(id);
            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(domain);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado consultando el tutor.",
                    "Unexpected technical error finding tutor: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmPhoneNumber(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.confirmMobileNumber(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el teléfono del tutor.",
                    "Unexpected technical error confirming tutor phone: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmEmail(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.confirmEmail(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el email del tutor.",
                    "Unexpected technical error confirming tutor email: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmAccountStatus(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.accountState(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el estado de cuenta del tutor.",
                    "Unexpected technical error confirming tutor account status: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendPhoneNumberConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendMobileNumberConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando código de confirmación por teléfono.",
                    "Unexpected technical error sending tutor phone confirmation code: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendEmailConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendEmailConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando código de confirmación por email.",
                    "Unexpected technical error sending tutor email confirmation code: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendAccountStatusConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendAccountState(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando notificación de estado de cuenta.",
                    "Unexpected technical error sending tutor account state notification: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

}
