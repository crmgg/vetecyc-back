package co.edu.uco.vetecyv.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.*;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.data.dao.entity.ReceiptDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.AdministratorEntity;
import co.edu.uco.vetecyv.entity.ConsultationEntity;
import co.edu.uco.vetecyv.entity.ReceiptEntity;

public final class ReceiptPostgreSqlDAO extends SqlConnection implements ReceiptDAO {

    public ReceiptPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final ReceiptEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO \"Receipt\" (\"id\", \"administrator\", \"consultation\", \"code\", \"dateTime\", \"totalCoast\") ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?)");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getAdministrator().getId());
            ps.setObject(3, entity.getConsultation().getId());
            ps.setString(4, entity.getCode());
            ps.setTimestamp(5, new Timestamp(DateHelper.getDefault(entity.getDateTime()).getTime()));
            ps.setDouble(6, entity.getTotalCoast());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_INSERT_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UNEXPECTED_ERROR_INSERT_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final ReceiptEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"Receipt\" SET ");
        sql.append("\"administrator\" = ?, ");
        sql.append("\"consultation\" = ?, ");
        sql.append("\"code\" = ?, ");
        sql.append("\"dateTime\" = ?, ");
        sql.append("\"totalCoast\" = ? ");
        sql.append("WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getAdministrator().getId());
            ps.setObject(2, entity.getConsultation().getId());
            ps.setString(3, entity.getCode());
            ps.setTimestamp(4, new Timestamp(DateHelper.getDefault(entity.getDateTime()).getTime()));
            ps.setDouble(5, entity.getTotalCoast());
            ps.setObject(6, entity.getId());

            ps.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UPDATE_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("DELETE FROM \"Receipt\" WHERE \"id\" = ?");

        try (var ps = getConnection().prepareStatement(sql.toString())) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_DELETE_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UNEXPECTED_ERROR_DELETE_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public List<ReceiptEntity> findByFilter(final ReceiptEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        final var parameters = new ArrayList<Object>();
        final var sql = createSentenceFindByFilter(filterEntity, parameters);

        try (var ps = getConnection().prepareStatement(sql)) {

            for (int index = 0; index < parameters.size(); index++) {
                ps.setObject(index + 1, parameters.get(index));
            }

            return executeSentenceFindByFilter(ps);

        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_EXECUTING_FIND_BY_FILTER_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_EXECUTING_FIND_BY_FILTER_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }
    }

    @Override
    public ReceiptEntity findById(final UUID id) {
        return findByFilter(new ReceiptEntity(id)).stream().findFirst().orElse(new ReceiptEntity());
    }

    @Override
    public List<ReceiptEntity> findAll() {
        return findByFilter(new ReceiptEntity());
    }

    private String createSentenceFindByFilter(final ReceiptEntity filterEntity, final List<Object> parameters) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"administrator\", \"consultation\", \"code\", \"dateTime\", \"totalCoast\" ");
        sql.append("FROM \"Receipt\" ");
        createWhereClauseFindByFilter(sql, parameters, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameters, final ReceiptEntity filterEntity) {
        final var filter = ObjectHelper.getDefault(filterEntity, new ReceiptEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\" =", filter.getId());

        var admin = ObjectHelper.getDefault(filter.getAdministrator(), new AdministratorEntity());
        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(admin.getId()),
                "\"administrator\" =", admin.getId());

        var consultation = ObjectHelper.getDefault(filter.getConsultation(), new ConsultationEntity());
        addCondition(conditions, parameters,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(consultation.getId()),
                "\"consultation\" =", consultation.getId());

        addCondition(conditions, parameters,
                !TextHelper.isEmptyWithTrim(filter.getCode()),
                "\"code\" =", filter.getCode());

        addCondition(conditions, parameters,
                !DateHelper.isValid(filter.getDateTime()),
                "\"dateTime\" =",filter.getDateTime());

        addCondition(conditions, parameters,
                !NumericHelper.getDefaultWithZero().equals(filter.getTotalCoast()),
                "\"totalCoast\" =", filter.getTotalCoast());

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

    private List<ReceiptEntity> executeSentenceFindByFilter(final PreparedStatement ps) {
        final var results = new ArrayList<ReceiptEntity>();

        try (final var rs = ps.executeQuery()) {
            while (rs.next()) {

                var admin = new AdministratorEntity();
                admin.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("administrator")));

                var consultation = new ConsultationEntity();
                consultation.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("consultation")));

                var receipt = new ReceiptEntity();
                receipt.setId(UUIDHelper.getUUIDHelper().getFromString(rs.getString("id")));
                receipt.setAdministrator(admin);
                receipt.setConsultation(consultation);
                receipt.setCode(rs.getString("code"));
                receipt.setDateTime(rs.getTimestamp("dateTime"));
                receipt.setTotalCoast(rs.getDouble("totalCoast"));

                results.add(receipt);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_MAPPING_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.RECEIPT_ERROR_SQL_UNEXPECTED_MAPPING_RECEIPT.getTitle();
            var technicalMessage = MessageFormat.format(MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_RECEIPT.getContent(), exception.getMessage());
            throw VetecyvException.create(userMessage, technicalMessage);
        }

        return results;
    }
}