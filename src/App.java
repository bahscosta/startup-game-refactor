import config.Config;
import engine.GameEngine;


public class App {
    public static void main (String[] args) {
        Config config = new Config();

        GameEngine engine = new GameEngine(config);

        engine.iniciarSimulacaoStartup();
    }
}
