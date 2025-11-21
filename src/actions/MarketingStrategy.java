
//cada decisão vira uma classe q retorna um objeto Deltas com as alteraçoes.
//cada decisão vira um Strategy
// um Strategy é uma opção para o jogador

package actions; 

import model.Deltas;
import model.Startup;



public class MarketingStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        return new Deltas(-10_000, +5, 0, 0.03);
    }
}