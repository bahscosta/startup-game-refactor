// é a porta de entrada do jogo

import config.Config;
import engine.GameEngine;
import persistence.DatabaseInitializer;

public class App {

    public static void main(String[] args) {

        // inicializa o BD H2 (cria tabelas se não existirem)
        DatabaseInitializer.inicializar();

        System.out.println("======================================");
        System.out.println("      BEM-VINDO AO STARTUP GAME ");
        System.out.println("======================================");

        // carrega as configurações do jogo (total de rodadas, decisões por rodada)
        Config config = new Config();
        System.out.println("Configurações carregadas:");
        System.out.println(" - Total de rodadas: " + config.totalRodadas());
        System.out.println(" - Máx. decisões por rodada: " + config.maxDecisoesPorRodada());
        System.out.println("--------------------------------------");

        // cria o motor do jogo - GameEngine
        GameEngine engine = new GameEngine(config);

        // inicia a simulação
        engine.iniciarSimulacaoStartup();

        System.out.println("\nObrigada por jogar o Startup Game ✨");
        System.out.println("Fim do jogo. Volte sempre!");
    }
}
