package it.uniroma3.diadia.giocatore;

/**
 * Questa classe gestisce i cfu di un giocatore del gioco
 *
 * @author  Michele Guida
 * @version base
 */
public class Giocatore {
	static final private int CFU_INIZIALI = 20; 
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	
}
