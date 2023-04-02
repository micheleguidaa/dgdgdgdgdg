package it.uniroma3.diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Attrezzo spada = new Attrezzo("spada",40);
	private Attrezzo osso = new Attrezzo("osso",20);
	private Stanza stanza1 = new Stanza("stanza1");
	private Stanza stanzaPiena = new Stanza("stanza2");
	
	@Before
	public void riempimentoStanza() {
		for(int i=0;i<this.stanzaPiena.getNumeroMassimoAttrezzi();i++) {
			this.stanzaPiena.addAttrezzo(spada);
		}
	}

	/* TEST addAttrezzo */

	@Test
	public void testAddAttrezzoNull() {
		assertFalse(stanza1.addAttrezzo(null)); //verifichiamo se il metodo ha funzionato
		assertEquals(0, stanza1.getNumeroAttrezzi()); //verifichiamo il numero di attrezi nell'array
	}

	@Test
	public void testAddAttrezzoArrayVuoto() {
		assertTrue(stanza1.addAttrezzo(spada));
		assertEquals(1, stanza1.getNumeroAttrezzi());
		assertEquals(spada,stanza1.getAttrezzo("spada"));
	}

	@Test
	public void testAddAttrezzo2Attrezzi() {
		assertTrue(stanza1.addAttrezzo(spada));
		assertTrue(stanza1.addAttrezzo(osso));
		assertEquals(2, stanza1.getNumeroAttrezzi());
		assertEquals(spada,stanza1.getAttrezzo("spada"));
		assertEquals(osso,stanza1.getAttrezzo("osso"));
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		assertFalse(stanzaPiena.addAttrezzo(osso));
	}



	/* TEST hasAttrezzo */

	@Test
	public void testHasAttrezzoNull() {
		assertFalse(stanza1.hasAttrezzo(null));
	}

	@Test
	public void testHasAttrezzoVuoto() {
		assertFalse(stanza1.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzoVoluto() {
		stanza1.addAttrezzo(osso);
		assertTrue(stanza1.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzoNonVoluto() {
		stanza1.addAttrezzo(osso);
		assertFalse(stanza1.hasAttrezzo("spada"));
	}



	/* TEST removeAttrezzo */

	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(stanza1.removeAttrezzo(null));
	}

	public void testRemoveAttrezzoArrayVuoto() {
		assertFalse(stanza1.removeAttrezzo(osso));
	}

	@Test
	public void testRemoveAttrezzoArray1AttrezzoVoluto() {
		stanza1.addAttrezzo(spada);
		assertTrue(stanza1.removeAttrezzo(spada));
		assertEquals(0,stanza1.getNumeroAttrezzi());
	}

	@Test
	public void testRemoveAttrezzoArray1AttrezzoNonVoluto() {
		stanza1.addAttrezzo(spada);
		assertFalse(stanza1.removeAttrezzo(osso));
		assertEquals(1,stanza1.getNumeroAttrezzi());
	}

	@Test
	public void testRemoveAttrezzoArray2AttrezziUguali() {
		stanza1.addAttrezzo(spada);
		stanza1.addAttrezzo(spada);
		assertTrue(stanza1.removeAttrezzo(spada));
		assertEquals(1,stanza1.getNumeroAttrezzi()); //cancella solo il primo che trova
	}





}
