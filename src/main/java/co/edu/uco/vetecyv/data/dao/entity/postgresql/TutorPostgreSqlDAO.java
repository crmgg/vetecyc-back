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
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.data.dao.entity.TutorDAO;
import co.edu.uco.vetecyv.entity.TutorEntity;

public final class TutorPostgreSqlDAO extends SqlConnection implements TutorDAO {

    public TutorPostgreSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(final TutorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO TUTOR (id, identificationDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setString(2, entity.getIdentityDocument());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getFirstLastName());
            preparedStatement.setString(5, entity.getSecondLastName());
            preparedStatement.setString(6, entity.getEmail());
            preparedStatement.setString(7, entity.getPhoneNumber());
            preparedStatement.setString(8, entity.getPassword());
            preparedStatement.setBoolean(9, entity.isEmailConfirmed());
            preparedStatement.setBoolean(10, entity.isPhoneConfirmed());
            preparedStatement.setBoolean(11, entity.isAccountState());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_INSERT_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final TutorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE TUTOR ");
        sql.append("SET identificationDocument = ?, ");
        sql.append("    nombre = ?, ");
        sql.append("    primerApellido = ?, ");
        sql.append("    segundoApellido = ?, ");
        sql.append("    correoElectronico = ?, ");
        sql.append("    numeroTelefono = ?, ");
        sql.append("    contrasena = ?, ");
        sql.append("    correoElectronicoConfirmado = ?, ");
        sql.append("    numeroTelefonoConfirmado = ?, ");
        sql.append("    estadoCuenta = ? ");
        sql.append("WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setString(1, entity.getIdentityDocument());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getFirstLastName());
            preparedStatement.setString(4, entity.getSecondLastName());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPhoneNumber());
            preparedStatement.setString(7, entity.getPassword());
            preparedStatement.setBoolean(8, entity.isEmailConfirmed());
            preparedStatement.setBoolean(9, entity.isPhoneConfirmed());
            preparedStatement.setBoolean(10, entity.isAccountState());
            preparedStatement.setObject(11, entity.getId());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UPDATE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM Tutor WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_DELETE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<TutorEntity> findByFilter(final TutorEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

        var parameterList = new ArrayList<Object>();
        var sql = createSentenceFindByFilter(filterEntity, parameterList);

        try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
            for (int index = 0; index < parameterList.size(); index++) {
                preparedStatement.setObject(index + 1, parameterList.get(index));
            }
            return executeSentenceFindByFilter(preparedStatement);
        } catch (final VetecyvException exception) {
            throw exception;
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public TutorEntity findById(final UUID id) {
        return findByFilter(new TutorEntity(id)).stream().findFirst().orElse(new TutorEntity());
    }

    @Override
    public List<TutorEntity> findAll() {
        return findByFilter(new TutorEntity());
    }

    private String createSentenceFindByFilter(final TutorEntity filterEntity, final List<Object> parameterList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, identificationDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta ");
        sql.append("FROM TUTOR ");
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
                                               final TutorEntity filterEntity) {
        var filter = ObjectHelper.getDefault(filterEntity, new TutorEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameterList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "id = ", filter.getId());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getIdentityDocument()),
                "Identity Document = ", filter.getIdentityDocument());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getName()),
                "nombre = ", filter.getName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getFirstLastName()),
                "primerApellido = ", filter.getFirstLastName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getSecondLastName()),
                "segundoApellido = ", filter.getSecondLastName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getEmail()),
                "correoElectronico = ", filter.getEmail());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getPhoneNumber()),
                "numeroTelefono = ", filter.getPhoneNumber());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getPassword()),
                "contrasena = ", filter.getPassword());

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
    }

    private void addCondition(final List<String> conditions, final List<Object> parameterList, final boolean condition,
                              final String clause, final Object value) {
        if (condition) {
            conditions.add(clause + " ?");
            parameterList.add(value);
        }
    }

    private List<TutorEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var list = new ArrayList<TutorEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                var tutor = new TutorEntity();
                tutor.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                tutor.setIdentityDocument(resultSet.getString("documentoIdentificacion"));
                tutor.setName(resultSet.getString("nombre"));
                tutor.setFirstLastName(resultSet.getString("primerApellido"));
                tutor.setSecondLastName(resultSet.getString("segundoApellido"));
                tutor.setEmail(resultSet.getString("correoElectronico"));
                tutor.setPhoneNumber(resultSet.getString("numeroTelefono"));
                tutor.setPassword(resultSet.getString("contrasena"));
                tutor.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
                tutor.setPhoneConfirmed(resultSet.getBoolean("numeroTelefonoConfirmado"));
                tutor.setAccountState(resultSet.getBoolean("estadoCuenta"));

                list.add(tutor);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_MAPPING_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_MAPPING_TUTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_TUTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return list;
    }

}