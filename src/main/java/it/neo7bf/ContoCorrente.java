package it.neo7bf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContoCorrente {
	private List<Movimento> movimenti = new ArrayList<>();
	private BigDecimal saldo;
	
	public ContoCorrente(BigDecimal saldoIniziale) {
		this.saldo = saldoIniziale;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public void addMovimento(Movimento m) {
		movimenti.add(m);
	}
}
