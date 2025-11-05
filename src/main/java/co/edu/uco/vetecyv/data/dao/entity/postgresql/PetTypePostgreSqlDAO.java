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
import co.edu.uco.vetecyv.data.dao.entity.PetTypeDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.PetTypeEntity;

public final class PetTypePostgreSqlDAO extends SqlConnection implements PetTypeDAO {

    public PetTypePostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final PetTypeEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"PetType\" (\"id\", \"name\") ");
        sql.append("VALUES (?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getName());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_INSERT_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UNEXPECTED_ERROR_INSERT_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final PetTypeEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"PetType\" SET ");
        sql.append("\"name\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getName());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UPDATE_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"PetType\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_DELETE_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UNEXPECTED_ERROR_DELETE_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<PetTypeEntity> findByFilter(final PetTypeEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }

            return executeSentenceFindByFilter(ps);

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_EXECUTING_FIND_BY_FILTER_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public PetTypeEntity findById(final UUID id) {
        return findByFilter(new PetTypeEntity(id)).stream().findFirst().orElse(new PetTypeEntity());
    }

    @Override
    public List<PetTypeEntity> findAll() {
        return findByFilter(new PetTypeEntity());
    }

    private String createSentenceFindByFilter(final PetTypeEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"name\" ");
        sql.append("FROM \"PetType\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final PetTypeEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new PetTypeEntity());
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

    private List<PetTypeEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<PetTypeEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {

                var petType = new PetTypeEntity();
                petType.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                petType.setName(rs.getString("name"));

                results.add(petType);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_MAPPING_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PETTYPE_ERROR_SQL_UNEXPECTED_MAPPING_PETTYPE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_PETTYPE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}