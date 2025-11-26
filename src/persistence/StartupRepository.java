//vai inserir uma linha na tabela STARTUP com: nome, caixa, reputação, moral e receita.

package persistence;

import model.Startup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StartupRepository {

    // salva o estado final da startup na tabela STARTUP
    public static void salvarEstadoFinal(Startup s) {
        String sql = """
            INSERT INTO STARTUP (nome, caixa, reputacao, moral, receita_base)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getCaixa().valor());
            ps.setInt(3, s.getReputacao().valor());
            ps.setInt(4, s.getMoral().valor());
            ps.setDouble(5, s.getReceitaBase().valor());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao salvar startup no banco: " + e.getMessage());
        }
    }


    // >>>>>>>>>>>>> COLE ESTE MÉTODO AQUI <<<<<<<<<<<<<<

    // lê todas as startups salvas no BD e imprime um relatório simples
    public static void imprimirRelatorioBanco() {
        String sql = "SELECT id, nome, caixa, reputacao, moral, receita_base FROM STARTUP";

        System.out.println("\n==== DADOS SALVOS NO BANCO (H2) ====");

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double caixa = rs.getDouble("caixa");
                int reputacao = rs.getInt("reputacao");
                int moral = rs.getInt("moral");
                double receitaBase = rs.getDouble("receita_base");

                System.out.println(
                    "ID: " + id +
                    " | Startup: " + nome +
                    " | Caixa: " + caixa +
                    " | Reputação: " + reputacao +
                    " | Moral: " + moral +
                    " | Receita Base: " + receitaBase
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler startups do banco: " + e.getMessage());
        }

        System.out.println("====================================\n");
    }

}
