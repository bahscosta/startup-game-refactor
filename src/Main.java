import engine.GameEngine;
import config.Config;


public class Main {
    public static void main(String[] args) {

        // Lê as configurações do game.properties automaticamente
        Config config = new Config();

        // Cria a engine
        GameEngine engine = new GameEngine(config);

        // Inicia o jogo
        engine.iniciarSimulacaoStartup();
    }
}
