package it.neo7bf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimento {
	
	private LocalDateTime data;
	private String tipoOperazione;
	private BigDecimal importo;
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getTipoOperazione() {
		return tipoOperazione;
	}
	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}
	
	@Override
	public String toString() {
		String importoStr = new DecimalFormat("#,###.00").format(importo.doubleValue());
		String dataStr = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(data);
		return String.format ("|%1$-10s|%2$-19s|%3$-15s|",tipoOperazione,dataStr,importoStr);
	}
}
