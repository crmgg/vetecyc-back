package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.GenderDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.GenderEntity;

public final class GenderPostgreSqlDAO extends SqlConnection implements GenderDAO {

    public GenderPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final GenderEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"gender\" (\"id\", \"name\") ");
        sql.append("VALUES (?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getName());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_INSERT_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_UNEXPECTED_ERROR_INSERT_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final GenderEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"gender\" SET ");
        sql.append("\"name\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setString(1, entity.getName());
            ps.setObject(2, entity.getId());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_UPDATE_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"gender\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_DELETE_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_UNEXPECTED_ERROR_DELETE_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<GenderEntity> findByFilter(final GenderEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public GenderEntity findById(final UUID id) {
        return findByFilter(new GenderEntity(id)).stream().findFirst().orElse(new GenderEntity());
    }

    @Override
    public List<GenderEntity> findAll() {
        return findByFilter(new GenderEntity());
    }

    private String createSentenceFindByFilter(final GenderEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"name\" ");
        sql.append("FROM \"gender\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters,
                                               final GenderEntity filterEntity) {
        final var validated = ObjectHelper.getDefault(filterEntity, new GenderEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getId()),
                "\"id\" = ", validated.getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(validated.getName()),
                "\"name\" = ", validated.getName());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parameters, final boolean condition,
                              final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + " ?");
            parameters.add(value);
        }
    }

    private List<GenderEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<GenderEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {
                var gender = new GenderEntity();
                gender.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                gender.setName(rs.getString("name"));
                results.add(gender);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_MAPPING_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.GENDER_ERROR_SQL_UNEXPECTED_MAPPING_GENDER.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_GENDER.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}
