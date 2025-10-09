package co.edu.uco.vetecyv.data.dao.entity;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;

public abstract class SqlConnection {

    private Connection connection;

    protected SqlConnection(Connection connection2) {
        setConnection(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        if (ObjectHelper.isNull(connection)) {
            var userMessage= MessagesEnum.USER_ERROR_SQL_CONNCETION_IS_EMPTY.getContent();
            var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNCETION_IS_EMPTY.getContent();
            throw vetecyvException.create(userMessage, technicalMessage);
        }

        try {
            if(connection.isClosed()) {
                var userMessage= MessagesEnum.USER_ERROR_SQL_CONNCETION_IS_CLOSED.getContent();
                var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNCETION_IS_CLOSED.getContent();
                throw NoseException.create(userMessage, technicalMessage);

            }
        } catch (SQLException exception) {
            var userMessage= MessagesEnum.USER_ERROR_SQL_CONNCETION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
            var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNCETION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
            throw NoseException.create(userMessage, technicalMessage);

        }

        this.connection = connection;
    }



}
