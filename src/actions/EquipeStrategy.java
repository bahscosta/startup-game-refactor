package actions; 


import model.Deltas;
import model.Startup;



public class EquipeStrategy implements DecisaoStrategy {
    @Override
    public Deltas aplicar(Startup s) {
        return new Deltas(-5_000, 0, +7, 0.0);
    }
}