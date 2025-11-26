//é o meu BOT IA


package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotDecision {

    private final Random random = new Random();

    //Escolhe automaticamente até maxDecisoes
    //Sem repetir decisões na mesma rodada.
    public List<String> escolherDecisoesBot(int maxDecisoes) {

        // opções de decisão que o bot pode escolher
        List<String> codigos = new ArrayList<>();
        codigos.add("MARKETING");
        codigos.add("PRODUTO");
        codigos.add("EQUIPE");
        codigos.add("INVESTIDORES");
        codigos.add("CORTAR_CUSTOS");

        List<String> escolhidas = new ArrayList<>();

        // garante que não tenta escolher mais decisões do que opções disponíveis
        int limite = Math.min(maxDecisoes, codigos.size());

        for (int i = 0; i < limite; i++) {
            int index = random.nextInt(codigos.size());
            String codigo = codigos.get(index);

            // evita repetir a mesma decisão na mesma rodada
            if (!escolhidas.contains(codigo)) {
                escolhidas.add(codigo);
            } else {
                i--; // tenta de novo se repetiu
            }
        }

        return escolhidas;
    }
}
