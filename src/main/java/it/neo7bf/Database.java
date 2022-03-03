package it.neo7bf;

import java.util.HashMap;
import java.util.Map;

//per ora senza la gestione delle eccezioni
public class Database {

    private Map<String, ContoCorrente> conti = new HashMap<>();

    public void aggiungiContoCorrente(String carta, ContoCorrente conto) {
        conti.put(carta, conto);
    }

    public ContoCorrente getContoCorrente(String carta) {
        return conti.get(carta);
    }
}
