package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import co.edu.uco.vetecyv.data.dao.entity.DetailDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.ConsultationEntity;
import co.edu.uco.vetecyv.entity.DetailEntity;
import co.edu.uco.vetecyv.entity.MedicalRecordEntity;

public final class DetailPostgreSqlDAO extends SqlConnection implements DetailDAO {

    public DetailPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final DetailEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"detail\" (\"id\", \"consultation\", \"medicalRecord\", \"code\", \"anotations\") ");
        sql.append("VALUES (?, ?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getConsultation().getId());
            ps.setObject(3, entity.getMedicalRecord().getId());
            ps.setString(4, entity.getCode());
            ps.setString(5, entity.getAnnotations());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_INSERT_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public void update(final DetailEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"detail\" SET ");
        sql.append("\"consultation\" = ?, ");
        sql.append("\"medicalRecord\" = ?, ");
        sql.append("\"code\" = ?, ");
        sql.append("\"anotations\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getConsultation().getId());
            ps.setObject(2, entity.getMedicalRecord().getId());
            ps.setString(3, entity.getCode());
            ps.setString(4, entity.getAnnotations());
            ps.setObject(5, entity.getId());
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UPDATE_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"detail\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_DELETE_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public List<DetailEntity> findByFilter(final DetailEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public DetailEntity findById(final UUID id) {
        return findByFilter(new DetailEntity(id)).stream().findFirst().orElse(new DetailEntity());
    }

    @Override
    public List<DetailEntity> findAll() {
        return findByFilter(new DetailEntity());
    }

    private String createSentenceFindByFilter(final DetailEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"consultation\", \"medicalRecord\", \"code\", \"anotations\" ");
        sql.append("FROM \"detail\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters,
                                               final DetailEntity filterEntity) {
        final var validated = ObjectHelper.getDefault(filterEntity, new DetailEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getId()),
                "\"id\" = ", validated.getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getConsultation().getId()),
                "\"consultation\" = ", validated.getConsultation().getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getMedicalRecord().getId()),
                "\"medicalRecord\" = ", validated.getMedicalRecord().getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(validated.getCode()),
                "\"code\" = ", validated.getCode());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(validated.getAnnotations()),
                "\"anotations\" = ", validated.getAnnotations());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parameters,
                              final boolean condition, final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + "?");
            parameters.add(value);
        }
    }

    private List<DetailEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var detail = new ArrayList<DetailEntity>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                var entity = new DetailEntity();
                entity.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                entity.setConsultation(new ConsultationEntity(UUIDHelper.getUUIDHelper().getFromString(rs.getString("consultation"))));
                entity.setMedicalRecord(new MedicalRecordEntity(UUIDHelper.getUUIDHelper().getFromString(rs.getString("medicalRecord"))));
                entity.setCode(rs.getString("code"));
                entity.setAnnotations(rs.getString("annotations"));
                detail.add(entity);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_MAPPING_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DETAIL_ERROR_SQL_UNEXPECTED_MAPPING_DETAIL.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_DETAIL.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }

        return detail;
    }
}
