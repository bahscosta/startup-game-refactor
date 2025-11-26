package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceProvider {

    private static final String URL = "jdbc:h2:./database/startupgame";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
