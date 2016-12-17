package com.madejm.ByteCodeVM.BusinessLogic;

import java.util.Observable;
import java.util.Scanner;

/**
 * Created by mejdej on 17/12/16.
 */
public class Some_fancy_observer {

    // Używany jest do powiadamiania zainteresowanych obiektów o zmianie stanu pewnego innego obiektu.

/*
    We wzorcu obserwator wyróżniamy dwa podstawowe typy obiektów:

    obserwowany (ang. observable, subject) - obiekt, o którym chcemy uzyskiwać informacje,
    obserwator (ang. observer, listener) - obiekty oczekujące na powiadomienie o zmianie stanu obiektu obserwowanego.
    Kiedy stan obiektu obserwowanego się zmienia, wywołuje on metodę powiadomObserwatorow(), która wysyła powiadomienia do wszystkich zarejestrowanych obserwatorów:

    public void powiadomObserwatorow() {
        dla każdego obserwatora obserwator z listy obserwatorzy:
        wywołaj obserwator.aktualizacja();
    }
    Podczas powiadamiania obserwatorzy otrzymują także referencję do obiektu obserwowanego. Jeden obserwator może obserwować kilka innych obiektów, a jeden obiekt obserwowany może być obserwowany przez kilku obserwatorów. Ponieważ oba te typy obiektów zdefiniowane są jako interfejsy do samodzielnej implementacji, obserwatorzy i obserwowani nie muszą się nawzajem znać, a ponadto obiekt obserwowany sam może obserwować inny obiekt.
*/

}

class EventSource extends Observable implements Runnable {
    public void run() {
        while (true) {
            String response = new Scanner(System.in).next();
            setChanged();
            notifyObservers(response);
        }
    }
}

class MyApp {
    public static void main(String[] args) {
        System.out.println("Enter Text: ");
        EventSource eventSource = new EventSource();

        // New java 8 style with lambda expressions
        eventSource.addObserver( (Observable obj, Object arg) -> {
            System.out.println("Received response: " + arg);
        });
        /*
        eventSource.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
                System.out.println("Received response: " + arg);
            }
        });
        */

        new Thread(eventSource).start();
    }
}