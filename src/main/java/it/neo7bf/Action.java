package it.neo7bf;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
//https://stackoverflow.com/questions/2979383/how-to-clear-the-console
public class Action {
	
	Database d;
        static final String ASK_CARTA = "Inserisci il codice della carta(...non quello vero!) :";
        static final String ERROR_CARTA = "numero della carta non valido";
	
	public Action(Database d) {
		this.d = d;
	}
	
	public void execute() {
		    boolean exit=false;
		    Scanner sd=new Scanner(System.in);
		    do
		    {
		      clearScreen();
		      System.out.println("1.Vedi importo conto corrente");
		      System.out.println("2.Preleva contanti ");
		      System.out.println("3.Versa contanti");
		      System.out.println("4.Storico movimenti");
		      System.out.println("5.Esci");
		      System.out.println("\nEffettua una Scelta: ");
		      int num= leggiIntero(sd);
		      String carta = "";
		      ContoCorrente cc = null;
		      BigDecimal importo = null;
		      switch(num)
		     {
		       case 1:
		    	   clearScreen();
		    	   //1.inserisci il codice della carta: (non quello vero! :) )
		    	   System.out.print(ASK_CARTA);
		    	   carta = leggiStringa(sd);
		    	   cc = d.getContoCorrente(carta);
		    	   //2. se non è valido mostra messaggio "numero della carta non valido (premi un tasto per tornare al menu principale)"
		    	   if(cc == null) { 
		    		   System.out.print(ERROR_CARTA);
		    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
		    	   }
		    	   //3. se valido mostra messaggio "Saldo disponibile: X" (premi un tasto per tornare al menu principale)
		    	   else {
		    		   BigDecimal saldoDisponbile = cc.getSaldo();
		    		   System.out.println("Saldo disponibile: "+saldoDisponbile);
		    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
		    	   } 
		    	   break;

		       case 2:
		    	   clearScreen();
		    	   //1.inserisci il codice della carta: (non quello vero! :) )
		    	   System.out.print(ASK_CARTA);
		    	   carta = leggiStringa(sd);
		    	   cc = d.getContoCorrente(carta);
		    	   //2. se non è valido mostra messaggio "numero della carta non valido (premi un tasto per tornare al menu principale)"
		    	   if(cc == null) { 
		    		   System.out.println(ERROR_CARTA);
		    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
		    	   }
		    	   else {
			    	   //3. Inserisci importo da prelevare
			    	   System.out.print("Inserisci importo da prelevare:\n");
			    	   importo = new BigDecimal(leggiIntero(sd));
			    	   //5. Se l'importo da prelevare è maggiore dell'importo totale del conto corrente
			    	   if(importo.intValue() > cc.getSaldo().intValue()) {
			    		   System.out.println("Saldo insufficiente!");
			    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
			    	   }
			    	   //4. altrimenti
			    	   //5.	dal conto togli l'importo richiesto 
			    	   else {
			    		   BigDecimal nuovoSaldo = cc.getSaldo();
			    		   nuovoSaldo = nuovoSaldo.subtract(importo);
			    		   cc.setSaldo(nuovoSaldo);
			    		   cc.addMovimento(creaMovimento(importo,"PRELIEVO"));
				    	   //6. mostra messaggio "importo prelevato: X nuovo saldo: Y (premi un tasto per tornare al menu principale)"
			    		   System.out.println("Prelievo effettuato ! Nuovo saldo disponibile: "+nuovoSaldo);
				    	   premiUnTastoPerTornareAlMenuPrincipale(sd);
			    	   }
		    	   }
		    	   break;

		       case 3:
		    	   clearScreen();
		    	   //1.inserisci il codice della carta: (non quello vero! :) )
		    	   System.out.print(ASK_CARTA);
		    	   carta = leggiStringa(sd);
		    	   cc = d.getContoCorrente(carta);
		    	   //2. se non è valido mostra messaggio "numero della carta non valido (premi un tasto per tornare al menu principale)"
		    	   if(cc == null) { 
		    		   System.out.println(ERROR_CARTA);
		    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
		    	   }
		    	   else {
			    	   //3. Inserisci importo da prelevare
			    	   System.out.print("Inserisci importo da versare:\n");
			    	   importo = new BigDecimal(leggiIntero(sd));
			    	   //4. Se l'importo da versare è zero
			    	   if(importo.intValue() <=0) {
			    		   System.out.println("L importo deve essere maggiore di zero");
			    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
			    	   }
			    	   //5. altrimenti
			    	   //6.	dal conto aggiungi l'importo richiesto
			    	   else {
			    		   BigDecimal nuovoSaldo = cc.getSaldo();
			    		   nuovoSaldo = nuovoSaldo.add(importo);
			    		   cc.setSaldo(nuovoSaldo);
			    		   cc.addMovimento(creaMovimento(importo,"ACCREDITO"));
			    		   System.out.println("Accredito effettuato! Nuovo saldo disponibile: "+nuovoSaldo);
			    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
			    	   }
		    	   }
		    	   break;

		       case 4:
		    	   clearScreen();
		    	   //1.inserisci il codice della carta: (non quello vero! :) )
		    	   System.out.print(ASK_CARTA);
		    	   carta = leggiStringa(sd);
		    	   cc = d.getContoCorrente(carta);
		    	   //2. se non è valido mostra messaggio "numero della carta non valido (premi un tasto per tornare al menu principale)"
		    	   if(cc == null) { 
		    		   System.out.println(ERROR_CARTA);
		    		   premiUnTastoPerTornareAlMenuPrincipale(sd);
		    	   }		    	   
		    	   //3. recupera i movimenti del conto corrente
		    	   List<Movimento> movimenti = cc.getMovimenti();
		    	   //4. Se esistono
		    	   if(movimenti.size() > 0) {
			    	   //5. Visualizza movimenti		    		   
			    	   System.out.println("Lista Movimenti: \n\n");
			    	   System.out.println(String.format ("|%1$-10s|%2$-19s|%3$-15s|","OPERAZIONE","DATA","IMPORTO"));
			    	   System.out.println(String.format ("|%1$-10s|%2$-19s|%3$-15s|","----------","-------------------","---------------"));
			    	   for(Movimento m : movimenti) {
			    		   System.out.println(m.toString());
			    	   }
		    	   }
		    	   else {
			    	   System.out.println("nessun movimento");
		    	   }
		    	   premiUnTastoPerTornareAlMenuPrincipale(sd);		    	   
		       	   break;

		       case 5:
		    	   return;
		      }

		    }while(!exit);
		    
		    sd.close();
	}

	private String leggiStringa(Scanner sd) {
		return sd.nextLine();
	}

	private int leggiIntero(Scanner sd) {
		return Integer.valueOf(leggiStringa(sd)).intValue();
	}

	private Movimento creaMovimento(BigDecimal importo, String tipoOperazione) {
		   Movimento mov = new Movimento();
		   mov.setTipoOperazione(tipoOperazione);
		   mov.setImporto(importo);
		   mov.setData(LocalDateTime.now());
		   return mov;
	}
	
	//Funziona solo fuori da Eclipse
	private void clearScreen() {
		//Clears Screen in java
		try {
		    if (System.getProperty("os.name").contains("Windows"))
		        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		    else
		        Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}		
	}

	private void premiUnTastoPerTornareAlMenuPrincipale(Scanner sd) {
		System.out.println("\n\npremi INVIO per tornare al menu principale");
		leggiStringa(sd);
	}

}
