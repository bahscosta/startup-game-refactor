//vai implementar o ObserverStartup
//vai imprimir a mensagem 

package observer;

import model.Startup;

public class ConsoleObserver implements ObserverStartup {

    @Override
    public void onEvento(String evento, Startup s) {
        System.out.println(
            "\n[EVENTO] " + evento.toUpperCase() +
            " | Startup: " + s.getNome() +
            " | Caixa: " + s.getCaixa().valor() +
            " | Reputação: " + s.getReputacao().valor() +
            " | Moral: " + s.getMoral().valor()
        );
    }
}
