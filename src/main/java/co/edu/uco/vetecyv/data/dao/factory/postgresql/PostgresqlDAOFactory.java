// java
    package co.edu.uco.vetecyv.data.dao.factory.postgresql;

    import java.sql.DriverManager;
    import java.sql.SQLException;

    import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
    import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
    import co.edu.uco.vetecyv.data.dao.entity.AdministratorDAO;
    import co.edu.uco.vetecyv.data.dao.entity.AgendaDAO;
    import co.edu.uco.vetecyv.data.dao.entity.AppointmentDAO;
    import co.edu.uco.vetecyv.data.dao.entity.ConsultationDAO;
    import co.edu.uco.vetecyv.data.dao.entity.DetailDAO;
    import co.edu.uco.vetecyv.data.dao.entity.DoctorDAO;
    import co.edu.uco.vetecyv.data.dao.entity.GenderDAO;
    import co.edu.uco.vetecyv.data.dao.entity.MedicalRecordDAO;
    import co.edu.uco.vetecyv.data.dao.entity.PetDAO;
    import co.edu.uco.vetecyv.data.dao.entity.PetTypeDAO;
    import co.edu.uco.vetecyv.data.dao.entity.RaceDAO;
    import co.edu.uco.vetecyv.data.dao.entity.ReceiptDAO;
    import co.edu.uco.vetecyv.data.dao.entity.SpecialityDAO;
    import co.edu.uco.vetecyv.data.dao.entity.SpecialityDoctorDAO;
    import co.edu.uco.vetecyv.data.dao.entity.StateDAO;
    import co.edu.uco.vetecyv.data.dao.entity.TutorDAO;
    import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;

    import co.edu.uco.vetecyv.data.dao.entity.postgresql.AdministratorPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.AgendaPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.AppointmentPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.ConsultationPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.DetailPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.DoctorPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.GenderPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.MedicalRecordPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.PetPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.PetTypePostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.RacePostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.ReceiptPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.SpecialityPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.SpecialityDoctorPostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.StatePostgreSqlDAO;
    import co.edu.uco.vetecyv.data.dao.entity.postgresql.TutorPostgreSqlDAO;

    public final class PostgresqlDAOFactory extends DAOFactory {

        public PostgresqlDAOFactory() {
            openConnection();
        }

        @Override
        protected void openConnection() {
            try {
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vetecyv", "postgres", "Park");
            } catch (final SQLException exception) {
                var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_EXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS.getContent();
                throw VetecyvException.create(userMessage, technicalMessage);
            } catch (final Exception exception) {
                var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
                throw VetecyvException.create(exception, userMessage, technicalMessage);
            }
        }

        @Override
        public AdministratorDAO getAdministratorDAO() {
            return new AdministratorPostgreSqlDAO(connection);
        }

        @Override
        public AgendaDAO getAgendaDAO() {
            return new AgendaPostgreSqlDAO(connection);
        }

        @Override
        public AppointmentDAO getAppointmentDAO() {
            return new AppointmentPostgreSqlDAO(connection);
        }

        @Override
        public ConsultationDAO getConsultationDAO() {
            return new ConsultationPostgreSqlDAO(connection);
        }

        @Override
        public DetailDAO getDetailDAO() {
            return new DetailPostgreSqlDAO(connection);
        }

        @Override
        public DoctorDAO getDoctorDAO() {
            return new DoctorPostgreSqlDAO(connection);
        }

        @Override
        public GenderDAO getGenderDAO() {
            return new GenderPostgreSqlDAO(connection);
        }

        @Override
        public MedicalRecordDAO getMedicalRecordDAO() {
            return new MedicalRecordPostgreSqlDAO(connection);
        }

        @Override
        public PetDAO getPetDAO() {
            return new PetPostgreSqlDAO(connection);
        }

        @Override
        public PetTypeDAO getPetTypeDAO() {
            return new PetTypePostgreSqlDAO(connection);
        }

        @Override
        public RaceDAO getRaceDAO() {
            return new RacePostgreSqlDAO(connection);
        }

        @Override
        public ReceiptDAO getReceiptDAO() {
            return new ReceiptPostgreSqlDAO(connection);
        }

        @Override
        public SpecialityDAO getSpecialityDAO() {
            return new SpecialityPostgreSqlDAO(connection);
        }

        @Override
        public SpecialityDoctorDAO getSpecialityDoctorDAO() {
            return new SpecialityDoctorPostgreSqlDAO(connection);
        }

        @Override
        public StateDAO getStateDAO() {
            return new StatePostgreSqlDAO(connection);
        }

        @Override
        public TutorDAO getTutorDAO() {
            return new TutorPostgreSqlDAO(connection);
        }
    }