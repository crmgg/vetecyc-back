package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.StateDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.StateEntity;

public final class StatePostgreSqlDAO extends SqlConnection implements StateDAO {

    public StatePostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final StateEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"State\" (\"id\", \"name\") ");
        sql.append("VALUES (?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_INSERT_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UNEXPECTED_ERROR_INSERT_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_STATE.getContent()+ exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final StateEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"state\" SET ");
        sql.append("\"name\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setString(1, entity.getName());
            ps.setObject(2, entity.getId());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UPDATE_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_STATE.getTitle();
            var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"state\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_DELETE_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UNEXPECTED_ERROR_DELETE_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<StateEntity> findByFilter(final StateEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_EXECUTING_FIND_BY_FILTER_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public StateEntity findById(final UUID id) {
        return findByFilter(new StateEntity(id)).stream().findFirst().orElse(new StateEntity());
    }

    @Override
    public List<StateEntity> findAll() {
        return findByFilter(new StateEntity());
    }

    private String createSentenceFindByFilter(final StateEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"name\" ");
        sql.append("FROM \"state\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final StateEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new StateEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" =", filter.getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getName()),
                "\"name\" =", filter.getName());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parameters, final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + " ?");
            parameters.add(value);
        }
    }

    private List<StateEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<StateEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {
                var entity = new StateEntity();
                entity.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                entity.setName(rs.getString("name"));
                results.add(entity);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_MAPPING_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.STATE_ERROR_SQL_UNEXPECTED_MAPPING_STATE.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_STATE.getContent() + exception.getMessage();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}