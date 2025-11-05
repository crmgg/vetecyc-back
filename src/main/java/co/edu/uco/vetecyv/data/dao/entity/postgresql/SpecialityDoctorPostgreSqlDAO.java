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
import co.edu.uco.vetecyv.data.dao.entity.SpecialityDoctorDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.DoctorEntity;
import co.edu.uco.vetecyv.entity.SpecialityEntity;
import co.edu.uco.vetecyv.entity.SpecialityDoctorEntity;

public final class SpecialityDoctorPostgreSqlDAO extends SqlConnection implements SpecialityDoctorDAO {

    public SpecialityDoctorPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final SpecialityDoctorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"SpecialityDoctor\" (\"id\", \"doctor\", \"speciality\", \"code\") ");
        sql.append("VALUES (?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getDoctor().getId());
            ps.setObject(3, entity.getSpeciality().getId());
            ps.setString(4, entity.getCode());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_INSERT_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final SpecialityDoctorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"SpecialityDoctor\" SET ");
        sql.append("\"doctor\" = ?, ");
        sql.append("\"speciality\" = ?, ");
        sql.append("\"code\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getDoctor().getId());
            ps.setObject(2, entity.getSpeciality().getId());
            ps.setString(3, entity.getCode());
            ps.setObject(4, entity.getId());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UPDATE_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"SpecialityDoctor\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_DELETE_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<SpecialityDoctorEntity> findByFilter(final SpecialityDoctorEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public SpecialityDoctorEntity findById(final UUID id) {
        return findByFilter(new SpecialityDoctorEntity(id)).stream().findFirst().orElse(new SpecialityDoctorEntity());
    }

    @Override
    public List<SpecialityDoctorEntity> findAll() {
        return findByFilter(new SpecialityDoctorEntity());
    }

    private String createSentenceFindByFilter(final SpecialityDoctorEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"doctor\", \"speciality\", \"code\" ");
        sql.append("FROM \"SpecialityDoctor\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final SpecialityDoctorEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new SpecialityDoctorEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" =", filter.getId());

        var doctor = ObjectHelper.getDefault(filter.getDoctor(), new DoctorEntity());
        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(doctor.getId()),
                "\"doctor\" =", doctor.getId());

        var speciality = ObjectHelper.getDefault(filter.getSpeciality(), new SpecialityEntity());
        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(speciality.getId()),
                "\"speciality\" =", speciality.getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getCode()),
                "\"code\" =", filter.getCode());

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

    private List<SpecialityDoctorEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<SpecialityDoctorEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {
                var doctor = new DoctorEntity();
                doctor.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("doctor")));

                var speciality = new SpecialityEntity();
                speciality.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("speciality")));

                var entity = new SpecialityDoctorEntity();
                entity.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                entity.setDoctor(doctor);
                entity.setSpeciality(speciality);
                entity.setCode(rs.getString("code"));

                results.add(entity);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_MAPPING_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.SPECIALITYDOCTOR_ERROR_SQL_UNEXPECTED_MAPPING_SPECIALITYDOCTOR.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_SPECIALITYDOCTOR.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}