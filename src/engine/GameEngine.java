//esse é p coração do jogo


//essa classe está no pacote engine
package engine; 

import java.util.Scanner;

//listar as startups e tomada de decisões:
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    //é o construtor
    public GameEngine(Config config) {
        this.config = config;
}




//Método IniciarSimulacaoStartup - Início do Jogo
public void iniciarSimulacaoStartup() {
    System.out.println("\n╔══════════════════════════════════════╗");
    System.out.println("  ║  🚀  BEM-VINDO AO STARTUP GAME  🚀  ║");
    System.out.println("  ╚══════════════════════════════════════╝\n");





    System.out.println("Vamos simular o crescimento das startups ao longo de " 
                       + config.totalRodadas() + " rodadas");
    System.out.println("Máximo de decisões por rodada: " 
                       + config.maxDecisoesPorRodada());
    System.out.println("--------------------------------------------\n");
    




    //cria a startup:
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
    System.out.println("\n**FIM DO JOGO**");
    for (Startup startup : startups) {
        System.out.println(startup.getNome() + " -> SCORE: " + startup.scoreFinal());
    } 

    // depois, mostra o ranking final:
    mostrarRankingFinal();
}


//método para criar as startups iniciais:
private List<Startup> criarStartups() {
    List <Startup> list = new ArrayList<> ();

    list.add(
        new Startup (
            "AlphaLabs",
            new model.vo.Dinheiro(10000),  
            new model.vo.Dinheiro (700),
            new model.vo.Humor (60),
            new model.vo.Humor (80)

        )
    );

    list.add(
        new Startup(
            "BetaTech",
            new model.vo.Dinheiro(10000),
            new model.vo.Dinheiro(7000),
            new model.vo.Humor(60),
            new model.vo.Humor(80)

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
    List<String> escolhas = escolherDecisoes();

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
    // calcula a receita da rodada (incluindo o bônus)
    double receita = startup.receitaRodada();

    // adiciona a receita ao caixa
    double novoCaixa = startup.getCaixa().valor() + receita;
    startup.setCaixa(new model.vo.Dinheiro(novoCaixa));

    // calcula crescimento da receita base
    double crescimento =
        1.0
        + (startup.getReputacao().valor() / 100.0) * 0.01
        + (startup.getMoral().valor() / 100.0) * 0.005;

    double novaBase = startup.getReceitaBase().valor() * crescimento;
    startup.setReceitaBase(new model.vo.Dinheiro(novaBase));
}


//mostrar o ranking com base no Score:
private void mostrarRankingFinal() {
    System.out.println("\n****RANKING FINAL****");

    // cria uma cópia da lista para ordenar
    List<Startup> ordenada = new ArrayList<>(startups);

    // ordena do MAIOR score para o menor:
    ordenada.sort((s1, s2) -> Double.compare(s2.scoreFinal(), s1.scoreFinal()));

    int pos = 1;
    for (Startup s : ordenada) {
        System.out.println(
            pos + "º lugar - " + s.getNome() +
            " | SCORE: " + String.format(Locale.US, "%.2f", s.scoreFinal())
        );
        pos++;
    }
}











//  Método escolherDecisoes: permite ao jogador escolher até N decisões em uma rodada
private List<String> escolherDecisoes() {

    // lista de decisões: ( para o Strategy/Factory)
    List<String> codigos = new ArrayList<>();
    codigos.add("MARKETING");
    codigos.add("PRODUTO");
    codigos.add("EQUIPE");
    codigos.add("INVESTIDORES");
    codigos.add("CORTAR_CUSTOS");


    // lista para guardar as decisões escolhidas:
    List<String> descricoes = new ArrayList<>();
    descricoes.add("Investir em divulgação, aumenta reputação, mas custa dinheiro.");
    descricoes.add("Melhorar o produto, aumenta chance de receita futura.");
    descricoes.add("Cuidar da equipe, melhora moral e clima interno.");
    descricoes.add("Buscar investidores, pode trazer dinheiro ou frustração.");
    descricoes.add("Cortar custos, melhora caixa mas pode afetar moral.");



    List<String> escolhidas = new ArrayList<>();

    int maxDecisoes = config.maxDecisoesPorRodada();

    for (int i = 0; i < maxDecisoes; i++) {

        System.out.println();
        System.out.println("\nEscolha uma decisão (" + (maxDecisoes - i) + " restante(s)):");
        System.out.println("\n***Opções disponíveis: ***");


        for (int idx = 0; idx < codigos.size(); idx++) {
            System.out.println(" [" + (idx + 1) + "] " + codigos.get(idx) + " - " + descricoes.get(idx));
        }
        System.out.println(" [0] Encerrar escolhas desta rodada");

        System.out.print("Digite o número da decisão ou 0/Enter para parar: ");

        String entrada = scanner.nextLine().trim();

        // Enter vazio = parar
        if (entrada.isEmpty()) {
            break;
        }

        int opcao;
        try {
            opcao = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite apenas o número da opção.");
            i--; // não conta essa tentativa
            continue;
        }

        if (opcao == 0) {
            // jogador decidiu não escolher mais nada
            break;
        }

        if (opcao < 1 || opcao > codigos.size()) {
            System.out.println("Opção inexistente. Tente novamente.");
            i--; // não conta essa tentativa
            continue;
        }

        String codigoEscolhido = codigos.get(opcao - 1);

        if (escolhidas.contains(codigoEscolhido)) {
            System.out.println("Você já escolheu essa decisão nessa rodada.");
            i--; // não conta essa tentativa
            continue;
        }

        escolhidas.add(codigoEscolhido);
        System.out.println("Decisão '" + codigoEscolhido + "' adicionada.");
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


/*cria startups iniciais 

roda as rodadas 

pergunta decisões 

usa Strategy + Factory 

aplica Deltas na Startup 

fecha a rodada calculando receita e crescimento 

mostra score final */