package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceProvider {

    private static final String URL = "jdbc:h2:./database/startupgame";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // bloco estático: carrega o driver H2 quando a classe é carregada
    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver H2 não encontrado no classpath.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
