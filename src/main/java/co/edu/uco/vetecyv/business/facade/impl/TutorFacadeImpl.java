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
    public TutorDTO registerNewInformation(final TutorDTO tutorDTO) {

        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var domain = TutorDTOAssembler.getTutorDTOAssembler().toDomain(tutorDTO);

            var createdDomain = business.registerNewTutorInformation(domain);

            daoFactory.commitTransaction();

            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(createdDomain);

        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }

    }

    @Override
    public void dropTutorInformation(final UUID id) {
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

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_DELETE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_DELETE.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }

    }

    @Override
    public void updateTutorInformation(final UUID id, final TutorDTO tutorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var tutorDomain = TutorDTOAssembler.getTutorDTOAssembler().toDomain(tutorDTO);

            business.updateTutorInformation(id, tutorDomain);

            daoFactory.commitTransaction();

        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getContent();
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

            var tutorDomainList = business.findAllTutors();

            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(tutorDomainList);


        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }


    }

    @Override
    public List<TutorDTO> findTutorsByFilter(final TutorDTO filter) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var tutorDomain = TutorDTOAssembler.getTutorDTOAssembler().toDomain(filter);

            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(business.findTutorsByFilter(tutorDomain));


        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TutorDTO findTutorById(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new TutorBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var tutorDomain = business.findTutorById(id);

            return TutorDTOAssembler.getTutorDTOAssembler().toDTO(tutorDomain);


        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {

            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
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
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
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

            business.sendAccountStateConfirmation(id);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getContent();
            var technicalMessage = MessagesEnum.TUTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

}
