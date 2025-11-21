package actions;


import model.Deltas;
import model.Startup;




public class ProdutoStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        return new Deltas(-8_000, 0, 0, 0.04);
    }
}
