package co.edu.uco.vetecyv.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.*;
import co.edu.uco.vetecyv.data.dao.factory.postgresql.PostgresqlDAOFactory;

public abstract class DAOFactory {

    protected Connection connection;

    protected static FactoryEnum factory = FactoryEnum.POSTGRESQL;

    public static DAOFactory getFactory() {
        switch (factory) {
            case POSTGRESQL: {
                return new PostgresqlDAOFactory();
            }
            default:
                var userMessage = MessagesEnum.USER_ERROR_SQL_DATASOURCE_NOT_AVAILABLE.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DATASOURCE_NOT_AVAILABLE.getContent();
                throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    public abstract AdministratorDAO getAdministratorDAO();
    public abstract AgendaDAO getAgendaDAO();
    public abstract AppointmentDAO getAppointmentDAO();
    public abstract ConsultationDAO getConsultationDAO();
    public abstract DetailDAO getDetailDAO();
    public abstract DoctorDAO getDoctorDAO();
    public abstract GenderDAO getGenderDAO();
    public abstract MedicalRecordDAO getMedicalRecordDAO();
    public abstract PetDAO getPetDAO();
    public abstract PetTypeDAO getPetTypeDAO();
    public abstract RaceDAO getRaceDAO();
    public abstract ReceiptDAO getReceiptDAO();
    public abstract SpecialityDAO getSpecialityDAO();
    public abstract SpecialityDoctorDAO getSpecialityDoctorDAO();
    public abstract StateDAO getStateDAO();
    public abstract TutorDAO getTutorDAO();

    protected abstract void openConnection();

    public final void initTransaction() {
        SqlConnectionHelper.ensureTransactionIsNotStarted(connection);

        try {
            connection.setAutoCommit(false);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_INIT_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_INIT_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    public final void commitTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);

        try {
            connection.commit();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_COMMIT_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_COMMIT_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    public final void rollbackTransaction() {
        SqlConnectionHelper.ensureTransactionIsStarted(connection);

        try {
            connection.rollback();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    public final void closeConnection() {
        SqlConnectionHelper.ensureConnectionIsOpen(connection);

        try {
            connection.close();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_CLOSE_CONNECTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_CLOSE_CONNECTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_CLOSE_CONNECTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_CLOSE_CONNECTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }
}