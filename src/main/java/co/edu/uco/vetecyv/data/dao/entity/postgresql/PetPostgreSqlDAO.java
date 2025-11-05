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
import co.edu.uco.vetecyv.data.dao.entity.PetDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.PetEntity;
import co.edu.uco.vetecyv.entity.TutorEntity;
import co.edu.uco.vetecyv.entity.GenderEntity;
import co.edu.uco.vetecyv.entity.RaceEntity;
import co.edu.uco.vetecyv.entity.StateEntity;

public final class PetPostgreSqlDAO extends SqlConnection implements PetDAO {

    public PetPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final PetEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"Pet\" (\"id\", \"tutor\", \"gender\", \"race\", \"code\", \"name\", \"size\", \"dateBirth\", \"state\", \"color\") ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getTutor().getId());
            ps.setObject(3, entity.getGender().getId());
            ps.setObject(4, entity.getRace().getId());
            ps.setString(5, entity.getCode());
            ps.setString(6, entity.getName());
            ps.setString(7, entity.getSize());
            ps.setTimestamp(8, new Timestamp(DateHelper.getDefault(entity.getDateBirth()).getTime()));
            ps.setBoolean(9, entity.getState());
            ps.setString(10, entity.getColor());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_INSERT_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UNEXPECTED_ERROR_INSERT_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final PetEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"Pet\" SET ");
        sql.append("\"tutor\" = ?, ");
        sql.append("\"gender\" = ?, ");
        sql.append("\"race\" = ?, ");
        sql.append("\"code\" = ?, ");
        sql.append("\"name\" = ?, ");
        sql.append("\"size\" = ?, ");
        sql.append("\"dateBirth\" = ?, ");
        sql.append("\"state\" = ?, ");
        sql.append("\"color\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getTutor().getId());
            ps.setObject(2, entity.getGender().getId());
            ps.setObject(3, entity.getRace().getId());
            ps.setObject(4, entity.getCode());
            ps.setObject(5, entity.getName());
            ps.setObject(6, entity.getSize());
            ps.setObject(7, new Timestamp(entity.getDateBirth().getTime()));
            ps.setObject(8, entity.getState());
            ps.setObject(9, entity.getColor());
            ps.setObject(10, entity.getId());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UPDATE_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"Pet\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_DELETE_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UNEXPECTED_ERROR_DELETE_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<PetEntity> findByFilter(final PetEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }

            return executeSentenceFindByFilter(ps);

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_EXECUTING_FIND_BY_FILTER_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UNEXECUTING_FIND_BY_FILTER_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public PetEntity findById(final UUID id) {
        return findByFilter(new PetEntity(id)).stream().findFirst().orElse(new PetEntity());
    }

    @Override
    public List<PetEntity> findAll() {
        return findByFilter(new PetEntity());
    }

    private String createSentenceFindByFilter(final PetEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"tutor\", \"gender\", \"race\", \"code\", \"name\", \"size\", \"dateBirth\", \"state\", \"color\" ");
        sql.append("FROM \"Pet\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final PetEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new PetEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" =", filter.getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getTutor().getId()),
                "\"tutor\" =", filter.getTutor().getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getGender().getId()),
                "\"gender\" =", filter.getGender().getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getRace().getId()),
                "\"race\" =", filter.getRace().getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getCode()),
                "\"code\" =", filter.getCode());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getName()),
                "\"name\" =", filter.getName());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getSize()),
                "\"size\" =", filter.getSize());

        addCondition(conditions, parameters,
                !DateHelper.isValid(filter.getDateBirth()),
                "\"dateBirth\" =", filter.getDateBirth());

        addCondition(conditions, parameters,
                !filter.getState(),
                "\"state\" =", filter.getState());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getColor()),
                "\"color\" =", filter.getColor());

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

    private List<PetEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<PetEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {

                var tutor = new TutorEntity();
                tutor.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("tutor")));

                var gender = new GenderEntity();
                gender.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("gender")));

                var race = new RaceEntity();
                race.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("race")));

                var state = new StateEntity();
                state.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("state")));

                var pet = new PetEntity();
                pet.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                pet.setTutor(tutor);
                pet.setGender(gender);
                pet.setRace(race);
                pet.setCode(rs.getString("code"));
                pet.setName(rs.getString("name"));
                pet.setSize(rs.getString("size"));
                pet.setDateBirth(rs.getTimestamp("dateBirth"));
                pet.setState(rs.getBoolean("state"));
                pet.setColor(rs.getString("color"));

                results.add(pet);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_MAPPING_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.PET_ERROR_SQL_UNEXPECTED_MAPPING_PET.getTitle();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_PET.getContent();
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}
