package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {

	Labirinto labirinto;
	Stanza biblioteca;
	Stanza N11;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		labirinto.creaLabirinto();
	}


	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaFinale().getNome());
	}



}
