package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.*;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.MedicalRecordDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.MedicalRecordEntity;
import co.edu.uco.vetecyv.entity.PetEntity;

public final class MedicalRecordPostgreSqlDAO extends SqlConnection implements MedicalRecordDAO {

    public MedicalRecordPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final MedicalRecordEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"MedicalRecord\" (\"id\", \"pet\", \"code\", \"creationDate\") ");
        sql.append("VALUES (?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getPet().getId());
            ps.setObject(3, entity.getCode());
            ps.setTimestamp(4, new Timestamp(DateHelper.getDefault(entity.getCreationDate()).getTime()));

            ps.executeUpdate();

        } catch (final SQLException exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_INSERT_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_MEDICAL_RECORD.getContent()
            );
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_UNEXPECTED_ERROR_INSERT_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_MEDICAL_RECORD.getContent()
            );
        }
    }

    @Override
    public void update(final MedicalRecordEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"MedicalRecord\" SET ");
        sql.append("\"pet\" = ?, ");
        sql.append("\"code\" = ?, ");
        sql.append("\"creationDate\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getPet().getId());
            ps.setObject(3, entity.getCode());
            ps.setTimestamp(4, new Timestamp(DateHelper.getDefault(entity.getCreationDate()).getTime()));
            ps.setObject(4, entity.getId());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_UPDATE_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_MEDICAL_RECORD.getContent()
            );
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_MEDICAL_RECORD.getContent()
            );
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"MedicalRecord\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_DELETE_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_MEDICAL_RECORD.getContent()
            );
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_UNEXPECTED_ERROR_DELETE_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_MEDICAL_RECORD.getContent()
            );
        }
    }

    @Override
    public List<MedicalRecordEntity> findByFilter(final MedicalRecordEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_EXECUTING_FIND_BY_FILTER_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_MEDICAL_RECORD.getContent()
            );
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_EXECUTING_FIND_BY_FILTER_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_MEDICAL_RECORD.getContent()
            );
        }
    }

    @Override
    public MedicalRecordEntity findById(final UUID id) {
        return findByFilter(new MedicalRecordEntity(id)).stream().findFirst().orElse(new MedicalRecordEntity());
    }

    @Override
    public List<MedicalRecordEntity> findAll() {
        return findByFilter(new MedicalRecordEntity());
    }

    private String createSentenceFindByFilter(final MedicalRecordEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"pet\", \"code\", \"creationDate\" ");
        sql.append("FROM \"MedicalRecord\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters,
                                               final MedicalRecordEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new MedicalRecordEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" = ", filter.getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getPet().getId()),
                "\"pet\" = ", filter.getPet().getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getCode()),
                "\"code\" = ", filter.getCode());

        addCondition(conditions, parameters,
                !DateHelper.isValid(filter.getCreationDate()),
                "\"creationDate\" = ", new Timestamp(filter.getCreationDate().getTime()));

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

    private List<MedicalRecordEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var medicalRecord = new ArrayList<MedicalRecordEntity>();

        try (var rs = ps.executeQuery()) {
            while (rs.next()) {
                var record = new MedicalRecordEntity();
                record.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));

                var petIdStr = rs.getString("pet");
                record.setPet(petIdStr != null ? new PetEntity(UUIDHelper.getUUIDHelper().getFromString(petIdStr)) : new PetEntity());

                record.setCode(rs.getString("code"));

                var ts = rs.getTimestamp("creationDate");
                record.setCreationDate(ts != null ? new java.util.Date(ts.getTime()) : null);

                medicalRecord.add(record);
            }
        } catch (final SQLException exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_MAPPING_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_MEDICAL_RECORD.getContent()
            );
        } catch (final Exception exception) {
            throw VetecyvException.create(
                    MessagesEnum.MEDICAL_RECORD_ERROR_SQL_UNEXPECTED_MAPPING_MEDICAL_RECORD.getTitle(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_MEDICAL_RECORD.getContent()
            );
        }

        return medicalRecord;
    }
}
