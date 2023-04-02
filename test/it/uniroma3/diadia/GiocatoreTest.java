package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore giocatore;
	

	@Test
	public void testGetCfu() {
		assertEquals(20, giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(7);
		assertEquals(7, giocatore.getCfu());
	}

	@Test
	public void testGetBorsa() {
		assertNotNull(giocatore.getBorsa());
	}

}
