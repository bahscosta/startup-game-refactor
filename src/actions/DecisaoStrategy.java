package actions;

import model.Startup;
import model.Deltas;

public interface DecisaoStrategy {
    Deltas aplicar(Startup startup);
    default void reverter(Startup startup, model.Deltas d) { /* opcional */ }
}
