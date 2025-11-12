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

        // Si la entidad trae el id por defecto (UUID cero), dejamos que la BD genere el id
        boolean hasDefaultId = UUIDHelper.getUUIDHelper().isDefaultUUID(entity.getId());

        if (hasDefaultId) {
            final var sql = new StringBuilder();
            sql.append("INSERT INTO tutor (\"identityDocument\", name, \"firstLastName\", \"secondLastName\", email, \"phoneNumber\", password, \"emailConfirmation\", \"phoneConfirmation\", \"accountState\") ");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");

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

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        Object idObj = rs.getObject(1);
                        if (idObj instanceof UUID) {
                            entity.setId((UUID) idObj);
                        } else if (idObj instanceof String) {
                            entity.setId(UUID.fromString((String) idObj));
                        }
                    }
                }

            } catch (final SQLException exception) {
                var userMessage = MessagesEnum.TUTOR_ERROR_SQL_INSERT_TUTOR.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_TUTOR.getContent() + exception.getMessage();
                throw VetecyvException.create(exception, userMessage, technicalMessage);
            } catch (final Exception exception) {
                var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent() + exception.getMessage();
                throw VetecyvException.create(exception, userMessage, technicalMessage);
            }

        } else {
            final var sql = new StringBuilder();
            sql.append("INSERT INTO tutor (id, \"identityDocument\", name, \"firstLastName\", \"secondLastName\", email, \"phoneNumber\", password, \"emailConfirmation\", \"phoneConfirmation\", \"accountState\") ");
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
                var userMessage = MessagesEnum.TUTOR_ERROR_SQL_INSERT_TUTOR.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_TUTOR.getContent() + exception.getMessage();
                throw VetecyvException.create(exception, userMessage, technicalMessage);
            } catch (final Exception exception) {
                var userMessage = MessagesEnum.TUTOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_TUTOR.getContent() + exception.getMessage();
                throw VetecyvException.create(exception, userMessage, technicalMessage);
            }
        }
    }

    @Override
    public void update(final TutorEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE \"tutor\" ");
        sql.append("SET \"identityDocument\" = ?, ");
        sql.append("    \"name\" = ?, ");
        sql.append("    \"firstLastName\" = ?, ");
        sql.append("    \"secondLastName\" = ?, ");
        sql.append("    \"email\" = ?, ");
        sql.append("    \"phoneNumber\" = ?, ");
        sql.append("    \"password\" = ?, ");
        sql.append("    \"emailConfirmation\" = ?, ");
        sql.append("    \"phoneConfirmation\" = ?, ");
        sql.append("    \"accountState\" = ? ");
        sql.append("WHERE \"id\" = ?");

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
        sql.append("DELETE FROM \"tutor\" WHERE \"id\" = ?");

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

        // Protección contra bucles infinitos por errores de construcción del filtro
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;
        List<TutorEntity> results = new ArrayList<>();

        while (attempts < MAX_ATTEMPTS) {
            try (var preparedStatement = getConnection().prepareStatement(sql)) {
                for (int index = 0; index < parameterList.size(); index++) {
                    preparedStatement.setObject(index + 1, parameterList.get(index));
                }

                results = executeSentenceFindByFilter(preparedStatement);
                break; // si la ejecución es exitosa, salir

            } catch (final SQLException exception) {
                attempts++;
                if (attempts >= MAX_ATTEMPTS) {
                    var userMessage = MessagesEnum.TUTOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_TUTOR.getContent();
                    var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_TUTOR.getContent()
                            + " Intentos fallidos: " + MAX_ATTEMPTS + ". Detalle: " + exception.getMessage();
                    throw VetecyvException.create(exception, userMessage, technicalMessage);
                }
            }
        }

        return results;
    }


    @Override
    public TutorEntity findById(final UUID id) {
        if (ObjectHelper.isNull(id)) {
            return null; // ID inválido
        }

        var results = findByFilter(new TutorEntity(id));

        // Si no hay resultados, devolver null (no una entidad vacía)
        if (results == null || results.isEmpty()) {
            return null;
        }

        return results.get(0);
    }


    @Override
    public List<TutorEntity> findAll() {
        return findByFilter(new TutorEntity());
    }

    private String createSentenceFindByFilter(final TutorEntity filterEntity, final List<Object> parameterList) {
        final var sql = new StringBuilder();
        sql.append("SELECT \"id\", \"identityDocument\", \"name\", \"firstLastName\", \"secondLastName\", \"email\", \"phoneNumber\", \"password\", \"emailConfirmation\", \"phoneConfirmation\", \"accountState\" ");
        sql.append("FROM \"tutor\" ");
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        return sql.toString();
    }

    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList,
                                               final TutorEntity filterEntity) {
        var filter = ObjectHelper.getDefault(filterEntity, new TutorEntity());
        final var conditions = new ArrayList<String>();

        addCondition(conditions, parameterList,
                !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()),
                "\"id\"= ", filter.getId());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getIdentityDocument()),
                "\"identityDocument\" = ", filter.getIdentityDocument());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getName()),
                "\"name\" = ", filter.getName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getFirstLastName()),
                "\"firstLastName\" = ", filter.getFirstLastName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getSecondLastName()),
                "\"secondLastName\" = ", filter.getSecondLastName());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getEmail()),
                "\"email\" = ", filter.getEmail());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getPhoneNumber()),
                "\"phoneNumber\" = ", filter.getPhoneNumber());

        addCondition(conditions, parameterList,
                !TextHelper.isEmptyWithTrim(filter.getPassword()),
                "\"password\" = ", filter.getPassword());

        addCondition(conditions, parameterList,
                !filter.isEmailConfirmation(),
                "\"emailConfirmation\" = ", filter.isEmailConfirmation());

        addCondition(conditions, parameterList,
                !filter.isPhoneConfirmation(),
                "\"phoneConfirmation\" = ", filter.isPhoneConfirmation());

        addCondition(conditions, parameterList,
                !filter.isAccountState(),
                "\"accountState\" = ", filter.isAccountState());

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
                tutor.setIdentityDocument(resultSet.getString("identityDocument"));
                tutor.setName(resultSet.getString("name"));
                tutor.setFirstLastName(resultSet.getString("firstLastName"));
                tutor.setSecondLastName(resultSet.getString("secondLastName"));
                tutor.setEmail(resultSet.getString("email"));
                tutor.setPhoneNumber(resultSet.getString("phoneNumber"));
                tutor.setPassword(resultSet.getString("password"));
                tutor.setEmailConfirmation(resultSet.getBoolean("emailConfirmation"));
                tutor.setPhoneConfirmation(resultSet.getBoolean("phoneConfirmation"));
                tutor.setAccountState(resultSet.getBoolean("accountState"));

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