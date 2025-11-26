package persistence;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void inicializar() {
        try {
            Connection conn = DataSourceProvider.getConnection();
            Statement stmt = conn.createStatement();

            // Lê e executa o schema.sql
            String schema = Files.readString(Paths.get("resources/schema.sql"));
            stmt.execute(schema);

            stmt.close();
            conn.close();

            System.out.println("Banco de dados H2 criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
