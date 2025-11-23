package observer;

import model.Startup;

public interface ObserverStartup {
    void onEvento(Startup startup, String mensagem);
}
