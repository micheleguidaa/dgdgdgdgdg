package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un labirinto del gioco
 *
 * @author  Michele Guida & Lorenzo Benzi
 * @see Stanza
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;

	
	 /**
     * Crea tutte le stanze e le porte di collegamento
     */
    public void creaLabirinto() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		stanzaIniziale = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		stanzaIniziale.impostaStanzaAdiacente("nord", biblioteca);
		stanzaIniziale.impostaStanzaAdiacente("est", aulaN11);
		stanzaIniziale.impostaStanzaAdiacente("sud", aulaN10);
		stanzaIniziale.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", stanzaIniziale);
		aulaN10.impostaStanzaAdiacente("nord", stanzaIniziale);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", stanzaIniziale);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", stanzaIniziale);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		stanzaIniziale.addAttrezzo(osso);
 
		stanzaFinale = biblioteca;
    }

	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}


	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	

	
	
}
