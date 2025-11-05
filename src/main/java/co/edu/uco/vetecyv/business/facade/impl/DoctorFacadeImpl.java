package co.edu.uco.vetecyv.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.assembler.dto.impl.DoctorDTOAssembler;
import co.edu.uco.vetecyv.business.business.impl.DoctorBusinessImpl;
import co.edu.uco.vetecyv.business.facade.DoctorFacade;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.dto.DoctorDTO;

public final class DoctorFacadeImpl implements DoctorFacade {

    @Override
    public void registerNewDoctorInformation(DoctorDTO doctorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = DoctorDTOAssembler.getDoctorDTOAssembler().toDomain(doctorDTO);
            business.registerNewDoctorInformation(domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_REGISTER.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void dropDoctorInformation(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.dropDoctorInformation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_DELETE.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_DELETE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void updateDoctorInformation(UUID id, DoctorDTO doctorDTO) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domain = DoctorDTOAssembler.getDoctorDTOAssembler().toDomain(doctorDTO);
            business.updateDoctorInformation(id, domain);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_UPDATE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<DoctorDTO> findAllDoctors() {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domains = business.findAllDoctors();
            return DoctorDTOAssembler.getDoctorDTOAssembler().toDTO(domains);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getTitle(),
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_ALL.getContent() + " - " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<DoctorDTO> findDoctorsByFilter(DoctorDTO filter) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domainFilter = DoctorDTOAssembler.getDoctorDTOAssembler().toDomain(filter);
            var domains = business.findDoctorsByFilter(domainFilter);
            return DoctorDTOAssembler.getDoctorDTOAssembler().toDTO(domains);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getTitle(),
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_FILTER.getContent() + " - " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public DoctorDTO findDoctorById(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            var domain = business.findDoctorById(id);
            return DoctorDTOAssembler.getDoctorDTOAssembler().toDTO(domain);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw VetecyvException.create(exception,
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getTitle(),
                    MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_FIND_BY_ID.getContent() + " - " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmPhoneNumber(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.confirmMobileNumber(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_PHONE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmEmail(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.confirmEmail(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_EMAIL.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmAccountStatus(UUID id, int confirmationCode) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.accountState(id, confirmationCode);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_CONFIRM_ACCOUNT_STATUS.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendPhoneNumberConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendMobileNumberConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_PHONE_CODE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendEmailConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendEmailConfirmation(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_EMAIL_CODE.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void sendAccountStatusConfirmationCode(UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DoctorBusinessImpl(daoFactory);
        try {
            daoFactory.initTransaction();
            business.sendAccountState(id);
            daoFactory.commitTransaction();
        } catch (final VetecyvException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getTitle();
            var technicalMessage = MessagesEnum.DOCTOR_FACADE_ERROR_UNEXPECTED_SEND_ACCOUNT_STATUS.getContent() + " - " + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } finally {
            daoFactory.closeConnection();
        }
    }

}
