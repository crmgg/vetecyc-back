package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.AgendaDAO;
import co.edu.uco.vetecyv.entity.AgendaEntity;

public class AgendaPostgresqlDAO extends SqlConnection implements AgendaDAO {

    public AgendaPostgresqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(final AgendaEntity entity) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append(" INSERT INTO Agenda (id, DoctorEspecilidad, codigo, fechaInicio, fechaFin) ");
        sql.append("VALUES (?, ?, ?, ?, ?) ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setString(2, entity.getSpecialityDoctor());
            preparedStatement.setString(3, entity.getCode());
            preparedStatement.setObject(4, entity.getDateTime());
            preparedStatement.setObject(5, entity.getEndDateTime());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_INSERT_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {

            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final AgendaEntity entity) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();

        sql.append("UPDATE Agenda ");
        sql.append("SET Especialista = ?, ");
        sql.append("    codigo = ?, ");
        sql.append("    fechaInicio = ?, ");
        sql.append("    fechaFin = ? ");
        sql.append("WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setString(1, entity.getSpecialityDoctor());
            preparedStatement.setString(2, entity.getCode());
            preparedStatement.setObject(3, entity.getDateTime());
            preparedStatement.setObject(4, entity.getEndDateTime());
            preparedStatement.setObject(5, entity.getId());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UPDATE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE ");
        sql.append("FROM   Agenda ");
        sql.append("WHERE  id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_DELETE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<AgendaEntity> findByFilter(AgendaEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        var parameterList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parameterList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

            for (int i = 0; i < parameterList.size(); i++) {
                preparedStatement.setObject(i + 1, parameterList.get(i));
            }

            return executeSentenceFindByFilter(preparedStatement);
        } catch (final VetecyvException exception) {
            throw exception;

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public AgendaEntity findById(final UUID id) {
        return findByFilter(new AgendaEntity(id))
                .stream()
                .findFirst()
                .orElse(new AgendaEntity());
    }

    @Override
    public List<AgendaEntity> findAll() {
        return findByFilter(new AgendaEntity());
    }

    private String createSentenceFindByFilter(final AgendaEntity filterEntity, final List<Object> parameterList) {
        final var sql = new StringBuilder();

        sql.append("SELECT id, ");
        sql.append("       Especilidad Doctor, ");
        sql.append("       codigo, ");
        sql.append("       fechaInicio, ");
        sql.append("       fechaFin ");
        sql.append("FROM   Agenda ");

        createWhereClauseFindByFilter(sql, parameterList, filterEntity);

        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
                                               final AgendaEntity filterEntity) {
        var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new AgendaEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameterList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getId()),
                "id = ?", filterEntityValidated.getId());

        addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getSpecialityDoctor().getCode()),
                "Especialidad Doctor = ?", filterEntityValidated.getSpecialityDoctor());

        addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filterEntityValidated.getCode()),
                "codigo = ?", filterEntityValidated.getCode());

        // Asumiendo métodos para verificar valores por defecto en fechas, ajusta si no existen
        addCondition(conditions, parameterList, filterEntityValidated.getDateTime() != null,
                "fechaInicio = ?", filterEntityValidated.getDateTime());

        addCondition(conditions, parameterList, filterEntityValidated.getEndDateTime() != null,
                "fechaFin = ?", filterEntityValidated.getEndDateTime());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parameterList, final boolean condition,
                              final String clause, final Object value) {
        if (condition) {
            conditions.add(clause);
            parameterList.add(value);
        }
    }

    private List<AgendaEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var listAgenda = new ArrayList<AgendaEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                var agenda = new AgendaEntity();

                agenda.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                agenda.setSpecialityDoctor(resultSet.getString("EspecilidadDoctor"));
                agenda.setCode(Integer.valueOf(resultSet.getString("codigo")));
                agenda.setDateTime(resultSet.getObject("fechaInicio", java.util.Date.class));
                agenda.setEndDateTime(resultSet.getObject("fechaFin", java.util.Date.class));

                listAgenda.add(agenda);
            }

        } catch (final SQLException exception) {

            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_MAPPING_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);

        } catch (final Exception exception) {

            var userMessage = MessagesEnum.AGENDA_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA
                    .getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return listAgenda;
    }

    @Override
    public void delete(AgendaEntity agendaEntity) {

    }
}