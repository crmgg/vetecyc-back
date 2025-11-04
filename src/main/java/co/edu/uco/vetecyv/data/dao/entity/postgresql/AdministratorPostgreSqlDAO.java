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
import co.edu.uco.vetecyv.data.dao.entity.AdministratorDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

public final class AdministratorPostgreSqlDAO extends SqlConnection implements AdministratorDAO {

    public AdministratorPostgreSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(final AdministratorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO Administrador (id, identificationDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta) ");
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
            preparedStatement.setBoolean(9, entity.isEmailConfirmation());
            preparedStatement.setBoolean(10, entity.isPhoneConfirmation());
            preparedStatement.setBoolean(11, entity.isAccountState());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_INSERT_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final AdministratorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE Administrador ");
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
            preparedStatement.setBoolean(8, entity.isEmailConfirmation());
            preparedStatement.setBoolean(9, entity.isPhoneConfirmation());
            preparedStatement.setBoolean(10, entity.isAccountState());
            preparedStatement.setObject(11, entity.getId());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_UPDATE_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM Administrador WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_DELETE_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<AdministratorEntity> findByFilter(final AdministratorEntity filterEntity) {
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
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public AdministratorEntity findById(final UUID id) {
        return findByFilter(new AdministratorEntity(id)).stream().findFirst().orElse(new AdministratorEntity());
    }

    @Override
    public List<AdministratorEntity> findAll() {
        return findByFilter(new AdministratorEntity());
    }

    private String createSentenceFindByFilter(final AdministratorEntity filterEntity, final List<Object> parameterList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, identitynDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta ");
        sql.append("FROM Administrador ");
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
                                               final AdministratorEntity filterEntity) {
        var filter = ObjectHelper.getDefault(filterEntity, new AdministratorEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameterList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "id = ", filter.getId());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getIdentityDocument()),
                "identificationDocument = ", filter.getIdentityDocument());

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

    private List<AdministratorEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var admin = new ArrayList<AdministratorEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                var administrator = new AdministratorEntity();
                administrator.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                administrator.setIdentityDocument(resultSet.getString("identificationDocument"));
                administrator.setName(resultSet.getString("nombre"));
                administrator.setFirstLastName(resultSet.getString("primerApellido"));
                administrator.setSecondLastName(resultSet.getString("segundoApellido"));
                administrator.setEmail(resultSet.getString("correoElectronico"));
                administrator.setPhoneNumber(resultSet.getString("numeroTelefono"));
                administrator.setPassword(resultSet.getString("contrasena"));
                administrator.setEmailConfirmation(resultSet.getBoolean("correoElectronicoConfirmado"));
                administrator.setPhoneConfirmation(resultSet.getBoolean("numeroTelefonoConfirmado"));
                administrator.setAccountState(resultSet.getBoolean("estadoCuenta"));

                admin.add(administrator);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_MAPPING_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.ADMINISTRATOR_ERROR_SQL_UNEXPECTED_MAPPING_ADMINISTRATOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_ADMINISTRATOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return admin;
    }

}