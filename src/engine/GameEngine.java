//MEXER NESSE CÓDIGO DEPOIS!!

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



//classe GameEngine
public class GameEngine { 
    private final Config config;
    private final Scanner scanner = new Scanner (System.in);
    private List<Startup> startups;


    //essa classe guarda o Config pra saber a qtd de rodadas e qtd de decisões
    public GameEngine(Config config) {
        this.config = config;
}





//Início do Jogo- Método IniciarSimulacaoStartup:
public void iniciarSimulacaoStartup() {
    System.out.println ("**Bem-vindo ao Startup Game!**");

    System.out.println("Vamos simular o crescimento das startups ao longo de " 
                       + config.totalRodadas() + " rodadas");
    System.out.println("Máximo de decisões por rodada: " 
                       + config.maxDecisoesPorRodada());
    System.out.println("--------------------------------------------\n");
    
    //cria a startup
    startups = criarStartups();
    
    
    //Loop das rodadas:
    for (int rodada = 1; rodada <= config.totalRodadas(); rodada++) {
        System.out.println("\n INICIANDO RODADA " + rodada);
        System.out.println("-----------------------------------------");
        
            
        for (Startup startup : startups) {
            startup.setRodadaAtual(rodada);
            processarRodadaDoJogador(startup);
        }
    }
    
    //mostrar os resultados - encerramento:
    System.out.println("**FIM DO JOGO**");
    for (Startup startup : startups) {
        System.out.println(startup.getNome() + " -> SCORE: " + startup.scoreFinal());
    }
} //esse código cria as startups, faz o loop de cada rodada e no final mostra o score de cada uma






//método para criar as startups iniciais:
private List<Startup> criarStartups() {
    List <Startup> list = new ArrayList<> ();

    list.add(
        new Startup (
            "StartupUm",
            new model.vo.Dinheiro(10000),  
            new model.vo.Dinheiro (10000),
            new model.vo.Humor (80),
            new model.vo.Humor (80)

        )
    );

    list.add(
        new Startup(
            "BioNova",
            new model.vo.Dinheiro(100000),
            new model.vo.Dinheiro(7000),
            new model.vo.Humor(60),
            new model.vo.Humor(60)

        )
    );
    return list;
}
    



//método que vai processar todas as ações de uma startup na rodada
private void processarRodadaDoJogador(Startup startup) {
    System.out.println("\n[ Startup: " + startup.getNome() + " ]");
    System.out.println("Status antes da rodada:");
    System.out.println(startup);
    System.out.println("---------------------------------");

    //escolher as decisões:
    List<String> escolhas = escolherDecisoesRodada();

    //aplicar essas decisões:
    aplicarDecisoes(startup, escolhas);

    //fechar a rodada:
    fecharRodada(startup);


    System.out.println("Status ao final da rodada:");
    System.out.println(startup);


}

// serve para o processarRodadaDoJogador não dar erro. 
//ele chama o método fecharRodada(startup) sendo q o método ainda n existe.
private void fecharRodada(Startup startup) {
    // TODO: lógica de fechar rodada (receita, crescimento, etc.)
}





//  Método escolherDecisoes: permite ao jogador escolher até N decisões em uma rodada
private List<String> escolherDecisoes() {

    // lista de decisões:
    List<String> opcoes = new ArrayList<>();
    opcoes.add("MARKETING");
    opcoes.add("PRODUTO");
    opcoes.add("EQUIPE");
    opcoes.add("INVESTIDORES");
    opcoes.add("CORTAR_CUSTOS");


    // lista para guardar as decisões escolhidas:
    List<String> escolhidas = new ArrayList<>();

    int maxDecisoes = config.maxDecisoesPorRodada();

    for (int i = 0; i < maxDecisoes; i++) {

        System.out.println("\nEscolha uma decisão (" + (maxDecisoes - i) + " restante(s)):");
        System.out.println("Opções disponíveis:");
        for (String op : opcoes) {
            System.out.println(" - " + op);
        }
        System.out.print("Digite a decisão ou apenas Enter para parar: ");

        String entrada = scanner.nextLine().trim().toUpperCase();

        // sair se o usuário não quiser escolher mais nada
        if (entrada.isEmpty()) {
            break;
        }

        // decisão não existe
        if (!opcoes.contains(entrada)) {
            System.out.println("Decisão inválida. Tente novamente.");
            i--; // não conta essa tentativa
            continue;
        }

        // decisão repetida
        if (escolhidas.contains(entrada)) {
            System.out.println("Você já escolheu essa decisão.");
            i--; // não conta essa tentativa
            continue;
        }

        // decisão válida
        escolhidas.add(entrada);
        System.out.println("Decisão '" + entrada + "' adicionada.");
    }

    return escolhidas;
}




//agora é usar o método aplicarDecisoes
//a partir da startup e das decisões escolhidas, vai pedir pro DecisaoFactory criar a estratégia
//chama o strategy.aplicar(startup) pra devolver um Delta e depoois o aplica na startup
// Aplica as decisões escolhidas na startup usando Strategy + Factory
private void aplicarDecisoes(Startup startup, List<String> escolhas) {

    if (escolhas == null || escolhas.isEmpty()) {
        System.out.println("Nesta rodada, nenhuma decisão foi tomada!");
        return;
    }


    for (String tipo : escolhas) {
        System.out.println("\nAplicando decisão: " + tipo);

        // cria a estratégia de acordo com o tipo da decisão:
        DecisaoStrategy strategy = DecisaoFactory.criar(tipo);

        if (strategy == null) {
            System.out.println("Não foi encontrada estratégia para o tipo: " + tipo);
            continue; // pula para a próxima decisão
        }



        try {
            // aplica a estratégia e recebe os deltas (alterações) que ela gera
            Deltas d = strategy.aplicar(startup);

        
            // --- aplica os deltas na startup ---

            // caixa
            double novoCaixa = startup.getCaixa().valor() + d.caixaDelta();
            startup.setCaixa(new model.vo.Dinheiro(novoCaixa));

            // reputação
            int novaReputacao = startup.getReputacao().valor() + d.reputacaoDelta();
            startup.setReputacao(new model.vo.Humor(novaReputacao));

            // moral
            int novaMoral = startup.getMoral().valor() + d.moralDelta();
            startup.setMoral(new model.vo.Humor(novaMoral));

            // bônus de receita para a próxima rodada (Percentual)
            startup.addBonusPercentReceitaProx(d.bonusDelta());

            System.out.println("Decisão aplicada com sucesso.");



        
        } catch (Exception e) {
            // tratamento de exceção obrigatório (prof pediu)
            System.out.println("Erro ao aplicar a decisão '" + tipo + "': " + e.getMessage());
        }
    }
}


}
