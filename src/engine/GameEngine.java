
//esse é p coração do jogo
//criando a estrutura básica

//essa classe está no pacote engine
package engine; 

import java.util.Scanner;

//listar as startups e tomada de decisões:
import java.util.ArrayList;
import java.util.List;

import model.Startup;
import model.Deltas;
import actions.DecisaoStrategy;
import actions.DecisaoFactory;
import config.Config;



//chasse GameEngine

public class GameEngine { 
    private final Config config;
    private final Scanner scanner = new Scanner (System.in);


    //essa classe guarda o Config pra saber a qtd de rodadas e qtd de decisões
    public GameEngine(Config config) {
        this.config = config;
}


public void iniciarJogo() {
    System.out.println ("**Iniciando Startup Game :**");

    //cria a startup
    List <Startup> startups = criarStartups();


    //loop das rodadas:
    for (int rodada = 1; rodada <= config.totalRodadas(); rodada++) {
        System.out.println("***RODADA " + rodada + "***");
        for (Startup s : startups) {
            s.setRodadaAtual(rodada);
            processarJogador(s);
        }
    }
    
    //mostrar os resultados:
    System.out.println("**FIM DO JOGO**");
    for (Startup s : startups) {
        System.out.println(s.getNome() + " -> SCORE: " + s.scoreFinal());
    }


}