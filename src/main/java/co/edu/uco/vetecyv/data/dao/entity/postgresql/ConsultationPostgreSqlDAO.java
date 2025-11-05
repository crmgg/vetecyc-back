package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.*;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.ConsultationDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.AppointmentEntity;
import co.edu.uco.vetecyv.entity.ConsultationEntity;
import co.edu.uco.vetecyv.entity.DetailEntity;
import org.springframework.validation.annotation.Validated;

public final class ConsultationPostgreSqlDAO extends SqlConnection implements ConsultationDAO {

    public ConsultationPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final ConsultationEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"consultation\" (\"id\", \"appointment\", \"detail\", \"code\", \"consultationPrice\") ");
        sql.append("VALUES (?, ?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getAppointment().getId());
            ps.setObject(3, entity.getDetail().getId());
            ps.setString(4, entity.getCode());
            ps.setDouble(5, entity.getConsultationPrice());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_INSERT_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UNEXPECTED_ERROR_INSERT_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public void update(final ConsultationEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"consultation\" SET ");
        sql.append("\"appointment\" = ?, ");
        sql.append("\"detail\" = ?, ");
        sql.append("\"code\" = ?, ");
        sql.append("\"consultationPrice\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, entity.getAppointment().getId());
            ps.setObject(2, entity.getDetail().getId());
            ps.setString(3, entity.getCode());
            ps.setDouble(4, entity.getConsultationPrice());
            ps.setObject(5, entity.getId());

            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UPDATE_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"consultation\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_DELETE_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UNEXPECTED_ERROR_DELETE_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public List<ConsultationEntity> findByFilter(final ConsultationEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }
            return executeSentenceFindByFilter(ps);
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }
    }

    @Override
    public ConsultationEntity findById(final UUID id) {
        return findByFilter(new ConsultationEntity(id)).stream().findFirst().orElse(new ConsultationEntity());
    }

    @Override
    public List<ConsultationEntity> findAll() {
        return findByFilter(new ConsultationEntity());
    }

    private String createSentenceFindByFilter(final ConsultationEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"appointment\", \"detail\", \"code\", \"consultationPrice\" ");
        sql.append("FROM \"consultation\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters,
                                               final ConsultationEntity filterEntity) {

        final var validated = ObjectHelper.getDefault(filterEntity, new ConsultationEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getId()),
                "\"id\" = ", validated.getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getAppointment().getId()),
                "\"appointment\" = ", validated.getAppointment().getId());

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(validated.getDetail().getId()),
                "\"detail\" = ", validated.getDetail().getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(validated.getCode()),
                "\"code\" = ", validated.getCode());

        addCondition(conditions, parameters,
                !NumericHelper.getDefaultWithZero().equals(validated.getConsultationPrice()),
                "\"consultationPrice\" = ", validated.getConsultationPrice());

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

    private List<ConsultationEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var consultation = new ArrayList<ConsultationEntity>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                var entity = new ConsultationEntity();
                entity.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                entity.setAppointment(new AppointmentEntity(UUIDHelper.getUUIDHelper().getFromString(rs.getString("appointment"))));
                entity.setDetail(new DetailEntity(UUIDHelper.getUUIDHelper().getFromString(rs.getString("detail"))));
                entity.setCode(rs.getString("code"));
                entity.setConsultationPrice(rs.getDouble("consultationPrice"));

                consultation.add(entity);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_MAPPING_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.CONSULTATION_ERROR_SQL_UNEXPECTED_MAPPING_CONSULTATION.getTitle();
            var technicalTemplate = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_CONSULTATION.getContent();
            throw VetecyvException.create(userMessage, technicalTemplate);
        }

        return consultation;
    }
}
