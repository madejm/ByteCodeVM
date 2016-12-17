package com.madejm.ByteCodeVM.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejdej on 17/12/16.
 */
public class Some_fancy_strategy {

    /*

    Rozważmy program generujący statystyki dotyczące podanego na wejściu kodu źródłowego takie, jak liczba wierszy, liczba klas itd. W mechanizmie generowania statystyk możemy wyróżnić część kodu specyficzną dla języka programowania, w którym napisany został kod oraz ogólną, niezależną od języka. Chcielibyśmy, aby nasz program był uniwersalny i obsługiwał wiele języków programowania, a także by w przyszłości istniała możliwość dodawania nowych.

    Rozwiązaniem jest zastosowanie wzorca Strategia, gdzie algorytmy specyficzne dla języków programowania wydzielimy jako osobne klasy ze wspólnym interfejsem, który umożliwi programowi wyciąganie określonych informacji o kodzie źródłowym.

    Budowa[edytuj]

    Diagram klas wzorca Strategia
    We wzorcu Strategia definiujemy wspólny interfejs dla wszystkich obsługiwanych algorytmów i zawierający wszystkie dozwolone operacje. Następnie implementujemy go w poszczególnych klasach dostarczających konkretne algorytmy. Dodatkowo, we wzorcu wyróżniamy także klienta korzystającego z algorytmów. Posiada on referencję do aktualnie używanej strategii oraz metodę ustawStrategie(), która pozwala ją zmienić.

    Elementy wzorca:

    Strategia — interfejs definiujący operacje, które muszą obsługiwać wszystkie dostępne algorytmy. Zakładamy, że wszyscy klienci zainteresowani wykorzystaniem algorytmów będą używać właśnie tego interfejsu.
    Konkretna strategia — implementuje określony algorytm zgodnie ze zdefiniowanym interfejsem.
    Klient — użytkownik rodziny algorytmów posiadający referencję do obiektu Strategia.
    Istotne jest, że obiekty Klient oraz Strategia współpracują ze sobą w celu wykonania określonego zadania. Klient wykonuje wszystkie ogólne zadania i nadzoruje przepływ sterowania, zaś strategie implementują te części zadania, które można wymieniać.

     */
}


class StrategyPatternWiki {

    public static void main(final String[] arguments) {
        Customer firstCustomer = new Customer(new NormalStrategy());

        // Normal billing
        firstCustomer.add(1.0, 1);

        // Start Happy Hour
        firstCustomer.setStrategy(new HappyHourStrategy());
        firstCustomer.add(1.0, 2);

        // New Customer
        Customer secondCustomer = new Customer(new HappyHourStrategy());
        secondCustomer.add(0.8, 1);
        // The Customer pays
        firstCustomer.printBill();

        // End Happy Hour
        secondCustomer.setStrategy(new NormalStrategy());
        secondCustomer.add(1.3, 2);
        secondCustomer.add(2.5, 1);
        secondCustomer.printBill();
    }
}

class Customer {

    private List<Double> drinks;
    private BillingStrategy strategy;

    public Customer(final BillingStrategy strategy) {
        this.drinks = new ArrayList<Double>();
        this.strategy = strategy;
    }

    public void add(final double price, final int quantity) {
        drinks.add(strategy.getActPrice(price*quantity));
    }

    // Payment of bill
    public void printBill() {
        double sum = 0;
        for (Double i : drinks) {
            sum += i;
        }
        System.out.println("Total due: " + sum);
        drinks.clear();
    }

    // Set Strategy
    public void setStrategy(final BillingStrategy strategy) {
        this.strategy = strategy;
    }

}

interface BillingStrategy {
    public double getActPrice(final double rawPrice);
}

// Normal billing strategy (unchanged price)
class NormalStrategy implements BillingStrategy {

    @Override
    public double getActPrice(final double rawPrice) {
        return rawPrice;
    }

}

// Strategy for Happy hour (50% discount)
class HappyHourStrategy implements BillingStrategy {

    @Override
    public double getActPrice(final double rawPrice) {
        return rawPrice*0.5;
    }

}
