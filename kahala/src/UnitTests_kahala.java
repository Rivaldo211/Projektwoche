import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Kalaha". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /** Leeres Brett (alle 14 Mulden 0) fuer kontrollierte Tests. */
    private Spielfeld leer() {
        Spielfeld f = new Spielfeld();
        f.setMuldenFuerTest(new int[Spielfeld.MULDEN]);
        return f;
    }

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex", 1);
        assertEquals("Alex", s.getName());
        assertEquals(1, s.getSpielernummer());
    }

    // ---------- Grundlagen ----------

    @Test
    void testStartbrett() {
        Spielfeld f = new Spielfeld();
        assertEquals(4, f.getMulde(0));
        assertEquals(0, f.getMulde(6));    // Kalaha 1 leer
        assertEquals(0, f.getMulde(13));   // Kalaha 2 leer
    }

    @Test
    void testKalahaIndex() {
        Spielfeld f = new Spielfeld();
        assertEquals(6, f.kalahaIndex(1));
        assertEquals(13, f.kalahaIndex(2));
    }

    @Test
    void testGetKalaha() {
        Spielfeld f = leer();
        assertEquals(0, f.getKalaha(1));
        f.getMulden()[6] = 5;
        assertEquals(5, f.getKalaha(1));
    }

    @Test
    void testEigeneMulde() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.eigeneMulde(0, 1));
        assertTrue(f.eigeneMulde(5, 1));
        assertFalse(f.eigeneMulde(6, 1));    // Kalaha
        assertFalse(f.eigeneMulde(7, 1));    // Gegner
        assertTrue(f.eigeneMulde(7, 2));
        assertTrue(f.eigeneMulde(12, 2));
        assertFalse(f.eigeneMulde(13, 2));   // Kalaha
        assertFalse(f.eigeneMulde(0, 2));
    }

    @Test
    void testIstZugGueltig() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.istZugGueltig(0, 1));
        assertFalse(f.istZugGueltig(7, 1));   // nicht eigene Mulde
        f.getMulden()[0] = 0;
        assertFalse(f.istZugGueltig(0, 1));   // leer
    }

    // ---------- saeen (modulare Arithmetik) ----------

    @Test
    void testSaeeEinfach() {
        Spielfeld f = leer();
        f.getMulden()[2] = 3;
        int letzte = f.saee(2, 1);
        assertEquals(0, f.getMulde(2));       // ausgeleert
        assertEquals(1, f.getMulde(3));
        assertEquals(1, f.getMulde(4));
        assertEquals(1, f.getMulde(5));
        assertEquals(5, letzte);
    }

    @Test
    void testSaeeUeberspringtGegnerKalaha() {
        Spielfeld f = leer();
        f.getMulden()[5] = 10;                // Spieler 1 saet 10 Steine ab Mulde 5
        int letzte = f.saee(5, 1);
        assertEquals(0, f.getMulde(13));      // Gegner-Kalaha wurde uebersprungen
        assertEquals(1, f.getMulde(6));       // eigenes Kalaha bekommt einen
        assertEquals(1, f.getMulde(0));       // laeuft ueber Null weiter
        assertEquals(2, letzte);
    }

    @Test
    void testLetzteImEigenenKalaha() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.letzteImEigenenKalaha(6, 1));
        assertFalse(f.letzteImEigenenKalaha(6, 2));
        assertTrue(f.letzteImEigenenKalaha(13, 2));
    }

    @Test
    void testIstSpielEnde() {
        Spielfeld f = new Spielfeld();
        assertFalse(f.istSpielEnde());
        for (int i = 0; i <= 5; i++) f.getMulden()[i] = 0;   // Spieler 1 leer
        assertTrue(f.istSpielEnde());
    }

    // ---------- toString ----------

    @Test
    void testToString() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.toString().indexOf("4") >= 0);   // Startmulden haben 4 Steine
    }

    // ---------- Spiel ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Spiel.checkEingabeMenu(1));
        assertTrue(Spiel.checkEingabeMenu(2));
        assertFalse(Spiel.checkEingabeMenu(0));
        assertFalse(Spiel.checkEingabeMenu(3));
    }
}
