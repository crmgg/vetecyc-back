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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DOCTOR.getContent() + " - " + exception.getMessage();
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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DOCTOR.getContent() + " - " + exception.getMessage();
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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DOCTOR.getContent() + " - " + exception.getMessage();
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
                    "Se presentó un error inesperado consultando doctores.",
                    "Unexpected technical error finding doctors: " + exception.getMessage());
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
                    "Se presentó un error inesperado consultando doctores por filtro.",
                    "Unexpected technical error finding doctors by filter: " + exception.getMessage());
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
                    "Se presentó un error inesperado consultando el doctor.",
                    "Unexpected technical error finding doctor: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el teléfono del doctor.",
                    "Unexpected technical error confirming doctor phone: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el email del doctor.",
                    "Unexpected technical error confirming doctor email: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado confirmando el estado de cuenta del doctor.",
                    "Unexpected technical error confirming doctor account status: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando código de confirmación por teléfono.",
                    "Unexpected technical error sending doctor phone confirmation code: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando código de confirmación por email.",
                    "Unexpected technical error sending doctor email confirmation code: " + exception.getMessage());
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
            throw VetecyvException.create(exception,
                    "Se presentó un error inesperado enviando notificación de estado de cuenta.",
                    "Unexpected technical error sending doctor account state notification: " + exception.getMessage());
        } finally {
            daoFactory.closeConnection();
        }
    }

}
