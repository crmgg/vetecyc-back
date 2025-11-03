package co.edu.uco.vetecyv.data.dao.factory.Postgresql;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.*;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.AdministratorPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgresql.AgendaPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.AppointmentPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.ConsultationPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.DetailPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.DoctorPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.GenderPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.MedicalRecordPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.PetPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.PetTypePostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.RacePostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.ReceiptPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.SpecialityDoctorPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.SpecialityPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.StatePostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.entity.postgersql.TutorPostgresqlDAO;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

public final class PostgresqlDAOFactory extends DAOFactory {

    @Override
    protected void openConnection() {

        String url = "jdbc:postgresql://localhost:5432/vetecyv";
        String user = "postgres";
        String password = "postgres";
        // Recomendado: mover estas credenciales a application.properties/env vars

        try {

            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException exception) {

            var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } catch (Exception exception) {

            var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public AdministratorDAO getAdministratorDAO() {
        return new AdministratorPostgresqlDAO(connection);
    }

    @Override
    public AgendaDAO getAgendaDAO() {
        return new AgendaPostgresqlDAO(connection);
    }

    @Override
    public AppointmentDAO getAppointmentDAO() {
        return new AppointmentPostgresqlDAO(connection);
    }

    @Override
    public ConsultationDAO getConsultationDAO() {
        return new ConsultationPostgresqlDAO(connection);
    }

    @Override
    public DetailDAO getDetailDAO() {
        return new DetailPostgresqlDAO(connection);
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new DoctorPostgresqlDAO(connection);
    }

    @Override
    public GenderDAO getGenderDAO() {
        return new GenderPostgresqlDAO(connection);
    }

    @Override
    public MedicalRecordDAO getMedicalRecordDAO() {
        return new MedicalRecordPostgresqlDAO(connection);
    }

    @Override
    public PetDAO getPetDAO() {
        return new PetPostgresqlDAO(connection);
    }

    @Override
    public PetTypeDAO getPetTypeDAO() {
        return new PetTypePostgresqlDAO(connection);
    }

    @Override
    public RaceDAO getRaceDAO() {
        return new RacePostgresqlDAO(connection);
    }

    @Override
    public ReceiptDAO getReceiptDAO() {
        return new ReceiptPostgresqlDAO(connection);
    }

    @Override
    public SpecialityDAO getSpecialityDAO() {
        return new SpecialityPostgresqlDAO(connection);
    }

    @Override
    public SpecialityDoctorDAO getSpecialityDoctorDAO() {
        return new SpecialityDoctorPostgresqlDAO(connection);
    }

    @Override
    public StateDAO getStateDAO() {
        return new StatePostgresqlDAO(connection);
    }

    @Override
    public TutorDAO getTutorDAO() {
        return new TutorPostgresqlDAO(connection);
    }
}