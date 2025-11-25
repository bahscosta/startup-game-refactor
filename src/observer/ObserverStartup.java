package observer;

import model.Startup;

public interface ObserverStartup {
    void onEvento(String evento, Startup startup);
}
