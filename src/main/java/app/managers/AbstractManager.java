package app.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class AbstractManager {
    protected Connection connection;

    public AbstractManager() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pokemonshowdown",
                "root",
                ""
            );
        }
    }

    public void closeConnection() throws SQLException {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
