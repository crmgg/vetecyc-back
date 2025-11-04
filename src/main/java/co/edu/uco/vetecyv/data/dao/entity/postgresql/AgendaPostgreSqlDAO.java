package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.AgendaDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.AgendaEntity;
import co.edu.uco.vetecyv.entity.SpecialityDoctorEntity;

public final class AgendaPostgreSqlDAO extends SqlConnection implements AgendaDAO {

    public AgendaPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final AgendaEntity entity) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"Agenda\"(id, \"especialidadDoctor\", \"codigo\", \"fechaInicio\", \"fechaFin\") ");
        sql.append("VALUES(?, ?, ?, ?, ?) ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getSpecialityDoctor().getId());
            preparedStatement.setObject(3, entity.getCode());
            preparedStatement.setTimestamp(4, new Timestamp(entity.getDateTime().getTime()));
            preparedStatement.setTimestamp(5, new Timestamp(entity.getEndDateTime().getTime()));

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_INSERT_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<AgendaEntity> findAll() {
        return findByFilter(new AgendaEntity());
    }

    @Override
    public List<AgendaEntity> findByFilter(final AgendaEntity filterEntity) {

        var parametersList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parametersList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parametersList.size(); index++) {
                preparedStatement.setObject(index + 1, parametersList.get(index));
            }

            return executeSentenceFindByFilter(preparedStatement);

        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    private String createSentenceFindByFilter(final AgendaEntity filterEntity, final List<Object> parametersList) {

        final var sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("  a.\"id\" AS \"idAgenda\", ");
        sql.append("  sd.\"id\" AS \"idEspecialidadDoctor\", ");
        sql.append("  a.\"especialidadDoctor\" AS \"especialidadDoctor\", ");
        sql.append("  a.\"codigo\" AS \"codigo\", ");
        sql.append("  a.\"fechaInicio\" AS \"fechaInicio\", ");
        sql.append("  a.\"fechaFin\" AS \"fechaFin\" ");
        sql.append("FROM \"Agenda\" AS a ");
        sql.append("INNER JOIN \"EspecialidadDoctor\" AS sd ");
        sql.append("  ON a.\"especialidadDoctor\" = sd.\"id\" ");

        createWhereClauseFindByFilter(sql, parametersList, filterEntity);

        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parametersList,
                                               final AgendaEntity filterEntity) {

        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new AgendaEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
                "a.\"id\" = ", filterEntityValidated.getId());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getSpecialityDoctor().getId()),
                "a.\"especialidadDoctor\" = ", filterEntityValidated.getSpecialityDoctor().getId());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCode()),
                "a.\"codigo\" = ", filterEntityValidated.getCode());

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getDateTime()),
                "a.\"fechaInicio\" = ", new Timestamp(filterEntityValidated.getDateTime().getTime()));

        addCondition(conditions, parametersList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getEndDateTime()),
                "a.\"fechaFin\" = ", new Timestamp(filterEntityValidated.getEndDateTime().getTime()));

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
                              final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + " ?");
            parametersList.add(value);
        }
    }

    private List<AgendaEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {

        var agendas = new ArrayList<AgendaEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                var speciality = new SpecialityDoctorEntity();
                speciality.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idEspecialidadDoctor")));

                var agenda = new AgendaEntity();
                agenda.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idAgenda")));
                agenda.setSpecialityDoctor(speciality);
                agenda.setCode(resultSet.getInt("codigo"));
                agenda.setDateTime(resultSet.getTimestamp("fechaInicio"));
                agenda.setEndDateTime(resultSet.getTimestamp("fechaFin"));

                agendas.add(agenda);
            }

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_MAPPING_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return agendas;
    }

    @Override
    public AgendaEntity findById(final UUID id) {
        return findByFilter(new AgendaEntity(id)).stream().findFirst().orElse(new AgendaEntity());
    }

    @Override
    public void update(final AgendaEntity entity) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        var sql = new StringBuilder();
        sql.append("UPDATE \"Agenda\" ");
        sql.append("SET \"especialidadDoctor\" = ?, ");
        sql.append("\"codigo\" = ?, ");
        sql.append("\"fechaInicio\" = ?, ");
        sql.append("\"fechaFin\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, entity.getSpecialityDoctor().getId());
            preparedStatement.setObject(2, entity.getCode());
            preparedStatement.setTimestamp(3, new Timestamp(entity.getDateTime().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(entity.getEndDateTime().getTime()));
            preparedStatement.setObject(5, entity.getId());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UPDATE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM \"Agenda\" WHERE \"id\" = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_DELETE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }
}
