package observer;

import model.Startup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVObserver implements ObserverStartup {

    private static final String NOME_ARQUIVO = "relatorio_startups.csv";
    private boolean headerEscrito;

    public CSVObserver() {
        // se o arquivo já existe e tem conteúdo, não precisa escrever o cabeçalho de novo
        File f = new File(NOME_ARQUIVO);
        headerEscrito = f.exists() && f.length() > 0;
    }

    @Override
    public void onEvento(String evento, Startup s) {

        // só queremos registrar quando o jogo termina
        if (!"jogo_finalizado".equals(evento)) {
            return;
        }

        try (FileWriter writer = new FileWriter(NOME_ARQUIVO, true)) {
            if (!headerEscrito) {
                writer.write("Startup,Caixa,ReceitaBase,Reputacao,Moral,ScoreFinal\n");
                headerEscrito = true;
            }

            String linha = String.format(
                    "%s,%.2f,%.2f,%d,%d,%.2f%n",
                    s.getNome(),
                    s.getCaixa().valor(),
                    s.getReceitaBase().valor(),
                    s.getReputacao().valor(),
                    s.getMoral().valor(),
                    s.scoreFinal()
            );

            writer.write(linha);

        } catch (IOException e) {
            System.out.println("Erro ao gravar CSV: " + e.getMessage());
        }
    }
}
