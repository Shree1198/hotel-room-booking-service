// DBUtil.java
package org.example.util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DBUtil {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
//            Class.forName(properties.getProperty("db.driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
    }

    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Overloaded method for closing resources without ResultSet
    public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
        closeResources(connection, preparedStatement, null);
    }
}
