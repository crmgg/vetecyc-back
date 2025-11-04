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
import co.edu.uco.vetecyv.data.dao.entity.DoctorDAO;
import co.edu.uco.vetecyv.data.dao.entity.SqlConnection;
import co.edu.uco.vetecyv.entity.DoctorEntity;

public final class DoctorPostgreSqlDAO extends SqlConnection implements DoctorDAO {

    public DoctorPostgreSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(final DoctorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO DOCTOR (id, identificationDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setString(2, entity.getIdentificationDocument());
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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_INSERT_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void update(final DoctorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE DOCTOR ");
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
            preparedStatement.setString(1, entity.getIdentificationDocument());
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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UPDATE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM Doctor WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_DELETE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<DoctorEntity> findByFilter(final DoctorEntity filterEntity) {
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
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public DoctorEntity findById(final UUID id) {
        return findByFilter(new DoctorEntity(id)).stream().findFirst().orElse(new DoctorEntity());
    }

    @Override
    public List<DoctorEntity> findAll() {
        return findByFilter(new DoctorEntity());
    }

    private String createSentenceFindByFilter(final DoctorEntity filterEntity, final List<Object> parameterList) {
        final var sql = new StringBuilder();
        sql.append("SELECT id, identificationDocument, nombre, primerApellido, segundoApellido, correoElectronico, numeroTelefono, contrasena, correoElectronicoConfirmado, numeroTelefonoConfirmado, estadoCuenta ");
        sql.append("FROM DOCTOR ");
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
                                               final DoctorEntity filterEntity) {
        var filter = ObjectHelper.getDefault(filterEntity, new DoctorEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameterList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "id = ", filter.getId());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getIdentityDocument()),
                "identityonDocument = ", filter.getIdentityDocument());

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

    private List<DoctorEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
        var doc = new ArrayList<DoctorEntity>();

        try (var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                var doctor = new DoctorEntity();
                doctor.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                doctor.setIdentityDocument(resultSet.getString("identificationDocument"));
                doctor.setName(resultSet.getString("nombre"));
                doctor.setFirstLastName(resultSet.getString("primerApellido"));
                doctor.setSecondLastName(resultSet.getString("segundoApellido"));
                doctor.setEmail(resultSet.getString("correoElectronico"));
                doctor.setPhoneNumber(resultSet.getString("numeroTelefono"));
                doctor.setPassword(resultSet.getString("contrasena"));
                doctor.setEmailConfirmation(resultSet.getBoolean("correoElectronicoConfirmado"));
                doctor.setPhoneConfirmation(resultSet.getBoolean("numeroTelefonoConfirmado"));
                doctor.setAccountState(resultSet.getBoolean("estadoCuenta"));

                doc.add(doctor);
            }
        } catch (final SQLException exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_MAPPING_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.DOCTOR_ERROR_SQL_UNEXPECTED_MAPPING_DOCTOR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_DOCTOR.getContent() + exception.getMessage();
            throw VetecyvException.create(exception, userMessage, technicalMessage);
        }

        return doc;
    }

}