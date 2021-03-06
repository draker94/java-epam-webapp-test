package by.training.dao.sql;

import by.training.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Andrey Kliuchnikov
 */

public class BaseDaoImpl {
    private static final Logger LOGGER = LogManager.getLogger(BaseDaoImpl.class);
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void delete(Long id, String tableName) throws DaoException {
        String sql = String.format("DELETE FROM `%s` WHERE `id` = ?", tableName);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
    }
}
