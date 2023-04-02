package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartitaTest {
	Partita partita = new Partita();
	
	@Test
	public void testIsFinita() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testIsFinitaTramiteSet() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}


}
