package it.uniroma3.diadia;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsaConSpada;
	private Borsa borsaConOsso;
	private Attrezzo spada;
	private Attrezzo osso;
	
	@Before
	public void setup() {
		this.borsaVuota = new Borsa();
		this.spada = new Attrezzo("spada",9);
		this.osso = new Attrezzo("osso",2);
		this.borsaConSpada = new Borsa();
		this.borsaConSpada.addAttrezzo(spada);
		this.borsaConOsso =  new Borsa();
		this.borsaConOsso.addAttrezzo(osso);
	}

	/* TEST addAttrezzo */

	@Test
	public void testAddAttrezzoNull() {
		assertFalse(borsaVuota.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzoSuBorsaVuota() {
		assertTrue(borsaVuota.addAttrezzo(spada));
	}

	@Test
	public void testAddAttrezzoSuperamentoPesoMax() {
		assertFalse(borsaConSpada.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzoNonSuperamnetoPesoMax() {
		assertTrue(borsaConOsso.addAttrezzo(osso));
	}


	/* TEST removeAttrezzo */

	@Test
	public void testRemoveAttrezzoNull() {
		assertNull(borsaVuota.removeAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzo1ElementoVoluto() {
		assertEquals(osso,borsaConOsso.removeAttrezzo("osso"));
		assertEquals(0,borsaConOsso.getPeso());//verifico che ora la borsa sia vuota
	}

	@Test
	public void testRemoveAttrezzo1ElementoNonVoluto() {
		assertEquals(null,borsaConOsso.removeAttrezzo("spada"));
		assertEquals(2,borsaConOsso.getPeso());//verifico che ora la borsa non sia vuota
	}

	@Test
	public void testRemoveAttrezzosuArrayCon2ElemtiUguali() {
		//Voglio che rimuova solo il primo
		borsaConOsso.addAttrezzo(osso); //ora ho 2 ossi in borsa
		assertEquals(osso,borsaConOsso.removeAttrezzo("osso"));
		assertTrue(borsaConOsso.hasAttrezzo("osso")); //verifico che sia ancora presente l'altro osso
		assertEquals(2,borsaConOsso.getPeso());//verifico la borsa non sia vuota
	}





}
