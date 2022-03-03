package it.neo7bf;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        Database d = new Database();
        d.aggiungiContoCorrente("1234", new ContoCorrente(new BigDecimal(50000)));
        d.aggiungiContoCorrente("1236", new ContoCorrente(new BigDecimal(13000)));

        Action a = new Action(d);
        a.execute();
    }

}
