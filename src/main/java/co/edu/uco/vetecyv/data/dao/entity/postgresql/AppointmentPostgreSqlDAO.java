package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.*;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.AppointmentDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.AgendaEntity;
import co.edu.uco.vetecyv.entity.AppointmentEntity;
import co.edu.uco.vetecyv.entity.PetEntity;
import co.edu.uco.vetecyv.entity.StateEntity;

public final class AppointmentPostgreSqlDAO extends SqlConnection implements AppointmentDAO {

    public AppointmentPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final AppointmentEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        var sql = new StringBuilder();
        sql.append("INSERT INTO \"Cita\" ");
        sql.append("(\"id\", \"agenda\", \"estado\", \"mascota\", \"codigo\", \"fechaHoraInicio\", \"fechaHoraFin\") ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getAgenda().getId());
            preparedStatement.setObject(3, entity.getState().getId());
            preparedStatement.setObject(4, entity.getPet().getId());
            preparedStatement.setObject(5, entity.getCode());
            preparedStatement.setTimestamp(6, new Timestamp(DateHelper.getDefault(entity.getDateTimeStare()).getTime()));
            preparedStatement.setTimestamp(7, new Timestamp(DateHelper.getDefault(entity.getEndDateTime()).getTime()));
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_INSERTING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_INSERTING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_INSERTING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_INSERTING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final AppointmentEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        var sql = new StringBuilder();
        sql.append("UPDATE \"Cita\" SET ");
        sql.append("\"agenda\" = ?, ");
        sql.append("\"estado\" = ?, ");
        sql.append("\"mascota\" = ?, ");
        sql.append("\"codigo\" = ?, ");
        sql.append("\"fechaHoraInicio\" = ?, ");
        sql.append("\"fechaHoraFin\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getAgenda().getId());
            preparedStatement.setObject(3, entity.getState().getId());
            preparedStatement.setObject(4, entity.getPet().getId());
            preparedStatement.setObject(5, entity.getCode());
            preparedStatement.setTimestamp(6, new Timestamp(DateHelper.getDefault(entity.getDateTimeStare()).getTime()));
            preparedStatement.setTimestamp(7, new Timestamp(DateHelper.getDefault(entity.getEndDateTime()).getTime()));
            preparedStatement.setObject(11, entity.getId());

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_UPDATING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_UPDATING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_UPDATING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_UPDATING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        var sql = new StringBuilder("DELETE FROM \"Cita\" WHERE \"id\" = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_DELETING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_DELETING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_DELETING_APPOINTMENT.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_DELETING_APPOINTMENT.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<AppointmentEntity> findByFilter(final AppointmentEntity filterEntity) {

        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }

            return executeSentenceFindByFilter(preparedStatement);

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public AppointmentEntity findById(final UUID id) {
        return findByFilter(new AppointmentEntity(id)).stream().findFirst().orElse(new AppointmentEntity());
    }

    @Override
    public List<AppointmentEntity> findAll() {
        return findByFilter(new AppointmentEntity());
    }

    private String createSentenceFindByFilter(final AppointmentEntity filterEntity, final List<Object> parametersList) {

        var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"agenda\", \"estado\", \"mascota\", \"codigo\", \"fechaHoraInicio\", \"fechaHoraFin\" ");
        sql.append("FROM \"Cita\" ");

        createWhereClauseFindByFilter(sql, parametersList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
                                               final AppointmentEntity filterEntity) {

        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new AppointmentEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
                "\"id\" = ", filterEntityValidated.getId());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getAgenda()),
                "\"agenda\" = ", filterEntityValidated.getAgenda().getId());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getState()),
                "\"estado\" = ", filterEntityValidated.getState().getId());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getPet()),
                "\"mascota\" = ", filterEntityValidated.getPet().getId());

        addCondition(conditions, parametersList,
                !TextHelper.isEmptyWithTrim(filterEntityValidated.getCode()),
                "\"codigo\" = ", filterEntityValidated.getCode());

        addCondition(conditions, parametersList,
                !DateHelper.isValid(filterEntityValidated.getDateTimeStare()),
                "\"fechaHoraInicio\" = ", new Timestamp(filterEntityValidated.getDateTimeStare().getTime()));

        addCondition(conditions, parametersList,
                !DateHelper.isValid(filterEntityValidated.getEndDateTime()),
                "\"fechaHoraFin\" = ", new Timestamp(filterEntityValidated.getEndDateTime().getTime()));

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList,
                              final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + " ?");
            parametersList.add(value);
        }
    }

    private List<AppointmentEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

        var appointments = new ArrayList<AppointmentEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                var appointment = new AppointmentEntity();
                appointment.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                appointment.setAgenda(new AgendaEntity(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("agenda"))));
                appointment.setState(new StateEntity(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("estado"))));
                appointment.setPet(new PetEntity(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("mascota"))));
                appointment.setCode(String.valueOf(resultSet.getObject("codigo", Integer.class)));

                var dateTimeStart = resultSet.getTimestamp("fechaHoraInicio");
                appointment.setDateTimeStare(dateTimeStart != null ? new java.util.Date(dateTimeStart.getTime()) : null);

                var dateTimeEnd = resultSet.getTimestamp("fechaHoraFin");
                appointment.setEndDateTime(dateTimeEnd != null ? new java.util.Date(dateTimeEnd.getTime()) : null);

                appointments.add(appointment);
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_APPOINTMENT_WHILE_EXECUTION.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return appointments;
    }
}
