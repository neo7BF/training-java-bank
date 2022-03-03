package it.neo7bf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class ContoCorrente {
	@Getter private List<Movimento> movimenti = new ArrayList<>();
	@Getter @Setter private BigDecimal saldo;
	
	public ContoCorrente(BigDecimal saldoIniziale) {
		this.saldo = saldoIniziale;
	}

	public void addMovimento(Movimento m) {
		movimenti.add(m);
	}
}
