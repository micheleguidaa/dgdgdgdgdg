package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole ioconsole = new IOConsole();

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = ioconsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome()==null) {
			return false;
		}
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			ioconsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			ioconsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioconsole.mostraMessaggio(elencoComandi[i]+" ");
		ioconsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			ioconsole.mostraMessaggio("Dove vuoi andare ?");
			ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
		else {
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioconsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}


	/**
	 * Prende un oggetto presente nella stanza, se presente nella stanza, e lo mette nella borsa 
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			ioconsole.mostraMessaggio("Cosa vuoi prendere?");
		}
		else {
			Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(attrezzo!=null&&partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
				if(partita.getGiocatore().getBorsa().getPeso()+attrezzo.getPeso()>=partita.getGiocatore().getBorsa().getPesoMax()) {
					ioconsole.mostraMessaggio("L'oggetto non può essere preso perchè non riesce ad entrare nella borsa!");
				}
				else {
					partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
					partita.getStanzaCorrente().removeAttrezzo(attrezzo);
					ioconsole.mostraMessaggio("Oggetto preso!");
				}
			}

			else {
				ioconsole.mostraMessaggio("L'oggetto non è presente nella stanza corrente!");
			}
		}
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());		
	}

	/**
	 * Posa un oggetto  nella stanza, se presente nella borsa
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			ioconsole.mostraMessaggio("Cosa vuoi posare?");
		}
		else {
			if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				if(partita.getStanzaCorrente().getNumeroAttrezzi()>=partita.getStanzaCorrente().getNumeroMassimoAttrezzi()) {
					ioconsole.mostraMessaggio("Non è possibile posare l'attrezzo perchè la stanza è piena");
				}
				else {
					Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
					partita.getStanzaCorrente().addAttrezzo(attrezzo);
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					ioconsole.mostraMessaggio("L'oggetto è stato posato!");
				}

			}
			else {
				ioconsole.mostraMessaggio("L'oggetto non è presente nella borsa!");
			}
		}
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioconsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}