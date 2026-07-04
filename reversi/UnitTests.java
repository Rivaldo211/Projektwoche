import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Reversi/Othello". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /** 3x3-Testbrett: in der Mitte ein Stein von Spieler 2, rechts daneben Spieler 1. */
    private Spielfeld testBoard() {
        Spielfeld f = new Spielfeld();
        int[][] b = {
            { 0, 0, 0 },
            { 0, 2, 1 },
            { 0, 0, 0 }
        };
        f.setFeldFuerTest(b);
        return f;
    }

    private int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == c) n++;
        return n;
    }

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex", 1);
        assertEquals("Alex", s.getName());
        assertEquals(1, s.getSpielernummer());
    }

    // ---------- Spielfeld: Grundlagen ----------

    @Test
    void testGegner() {
        Spielfeld f = new Spielfeld();
        assertEquals(2, f.gegner(1));
        assertEquals(1, f.gegner(2));
    }

    @Test
    void testStartaufstellung() {
        Spielfeld f = new Spielfeld();
        assertEquals(1, f.getZelle(2, 2));
        assertEquals(0, f.getZelle(0, 0));
        assertEquals(2, f.zaehleSteine(1));
        assertEquals(2, f.zaehleSteine(2));
    }

    @Test
    void testImFeld() {
        Spielfeld f = new Spielfeld();   // 6x6
        assertTrue(f.imFeld(0, 0));
        assertTrue(f.imFeld(5, 5));
        assertFalse(f.imFeld(-1, 0));
        assertFalse(f.imFeld(6, 0));
        assertFalse(f.imFeld(0, 6));
    }

    @Test
    void testIstVoll() {
        Spielfeld f = new Spielfeld();
        assertFalse(f.istVoll());
        int[][] voll = { {1, 1}, {2, 2} };
        f.setFeldFuerTest(voll);
        assertTrue(f.istVoll());
    }

    // ---------- Spielfeld: Kern-Logik ----------

    @Test
    void testPruefeRichtung() {
        Spielfeld f = testBoard();
        assertTrue(f.pruefeRichtung(1, 0, 0, 1, 1));    // nach rechts: Gegner dann eigener
        assertFalse(f.pruefeRichtung(1, 0, 0, -1, 1));  // nach links: nichts
        assertFalse(f.pruefeRichtung(1, 0, 1, 0, 1));   // nach unten: kein Gegner
    }

    @Test
    void testIstZugGueltig() {
        Spielfeld f = testBoard();
        assertTrue(f.istZugGueltig(1, 0, 1));    // Spieler 1 kann hier einklemmen
        assertFalse(f.istZugGueltig(1, 0, 2));   // Spieler 2 nicht
        assertFalse(f.istZugGueltig(1, 1, 1));   // Stelle ist belegt
    }

    @Test
    void testSetzeSteinDrehtUm() {
        Spielfeld f = testBoard();
        f.setzeStein(1, 0, 1);
        assertEquals(1, f.getZelle(1, 0));   // gesetzter Stein
        assertEquals(1, f.getZelle(1, 1));   // umgedreht (war 2)
        assertEquals(1, f.getZelle(1, 2));   // eigener Abschluss
    }

    @Test
    void testHatGueltigenZug() {
        Spielfeld f = testBoard();
        assertTrue(f.hatGueltigenZug(1));
        assertFalse(f.hatGueltigenZug(2));
    }

    // ---------- Spielfeld: toString ----------

    @Test
    void testToString() {
        Spielfeld f = testBoard();
        String t = f.toString();
        assertEquals(7, zaehle(t, '.'));
        assertTrue(t.indexOf("X") >= 0);
        assertTrue(t.indexOf("O") >= 0);
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
