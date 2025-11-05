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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getTitle();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getContent() + " - " + exception.getMessage();
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_DELETE.getTitle();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_DELETE.getContent() + " - " + exception.getMessage();
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getTitle();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getContent() + " - " + exception.getMessage();
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getContent() + " - " + exception.getMessage());
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
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getTitle(),
                    MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getContent() + " - " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

}
