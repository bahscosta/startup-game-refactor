package actions;


import model.Deltas;
import model.Startup;




public class CortarCustosStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup startup) {
        return new Deltas(+8_000, 0, -5, 0.0);
    }
}
