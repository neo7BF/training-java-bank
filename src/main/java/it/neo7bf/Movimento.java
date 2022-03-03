package it.neo7bf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class Movimento {

    private LocalDateTime data;
    private String tipoOperazione;
    private BigDecimal importo;

    @Override
    public String toString() {
        String importoStr = new DecimalFormat("#,###.00").format(importo.doubleValue());
        String dataStr = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(data);
        return String.format("|%1$-10s|%2$-19s|%3$-15s|", tipoOperazione, dataStr, importoStr);
    }
}
