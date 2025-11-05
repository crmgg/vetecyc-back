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
import co.edu.uco.vetecyv.data.dao.entity.RaceDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.RaceEntity;
import co.edu.uco.vetecyv.entity.PetTypeEntity;

public final class RacePostgreSqlDAO extends SqlConnection implements RaceDAO {

    public RacePostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final RaceEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"Race\" (\"id\", \"petType\", \"name\") ");
        sql.append("VALUES (?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getPetType().getId());
            ps.setString(3, entity.getName());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_INSERT_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UNEXPECTED_ERROR_INSERT_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final RaceEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"Race\" SET ");
        sql.append("\"petType\" = ?, ");
        sql.append("\"name\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getPetType().getId());
            ps.setString(3, entity.getName());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UPDATE_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"Race\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_DELETE_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UNEXPECTED_ERROR_DELETE_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<RaceEntity> findByFilter(final RaceEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_EXECUTING_FIND_BY_FILTER_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public RaceEntity findById(final UUID id) {
        return findByFilter(new RaceEntity(id)).stream().findFirst().orElse(new RaceEntity());
    }

    @Override
    public List<RaceEntity> findAll() {
        return findByFilter(new RaceEntity());
    }

    private String createSentenceFindByFilter(final RaceEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"petType\", \"name\" ");
        sql.append("FROM \"Race\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final RaceEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new RaceEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" =", filter.getId());

        var petType = ObjectHelper.getDefault(filter.getPetType(), new PetTypeEntity());
        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(petType.getId()),
                "\"petType\" =", petType.getId());

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

    private List<RaceEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<RaceEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {
                var petType = new PetTypeEntity();
                petType.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("petType")));

                var race = new RaceEntity();
                race.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                race.setPetType(petType);
                race.setName(rs.getString("name"));

                results.add(race);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_MAPPING_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RACE_ERROR_SQL_UNEXPECTED_MAPPING_RACE.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_RACE.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}