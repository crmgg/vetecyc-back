package co.edu.uco.vetecyv.data.dao.entity.postgersql;

import java.sql.Connection;

import co.edu.uco.vetecyv.crosscuting.helper.SqlConnectionHelper;

public abstract class SqlConnection {

    private Connection connection;

    protected SqlConnection(Connection connection2) {
        setConnection(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        SqlConnectionHelper.ensureConnectionIsOpen(connection);
        this.connection = connection;
    }


}
