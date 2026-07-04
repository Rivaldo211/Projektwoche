import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Vier gewinnt". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

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
    void testGetZelleLeer() {
        Spielfeld f = new Spielfeld();
        assertEquals(0, f.getZelle(0, 0));
        assertEquals(0, f.getZelle(5, 6));
    }

    @Test
    void testSpalteGueltig() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.spalteGueltig(0));
        assertTrue(f.spalteGueltig(6));
        assertFalse(f.spalteGueltig(-1));
        assertFalse(f.spalteGueltig(7));
        f.getFeld()[0][2] = 1;   // oberste Zelle belegt -> Spalte voll
        assertFalse(f.spalteGueltig(2));
    }

    @Test
    void testEinwerfen() {
        Spielfeld f = new Spielfeld();
        assertEquals(5, f.einwerfen(3, 1));   // faellt ganz nach unten
        assertEquals(1, f.getZelle(5, 3));
        assertEquals(4, f.einwerfen(3, 2));   // liegt darueber
        assertEquals(2, f.getZelle(4, 3));
    }

    @Test
    void testIstVoll() {
        Spielfeld f = new Spielfeld();
        assertFalse(f.istVoll());
        for (int z = 0; z < Spielfeld.ZEILEN; z++)
            for (int s = 0; s < Spielfeld.SPALTEN; s++)
                f.getFeld()[z][s] = 1;
        assertTrue(f.istVoll());
    }

    // ---------- Spielfeld: Gewinn ----------

    @Test
    void testWaagerecht() {
        Spielfeld f = new Spielfeld();
        for (int s = 0; s < 4; s++) f.getFeld()[5][s] = 1;
        assertTrue(f.vierInReiheWaagerecht(1));
        assertFalse(f.vierInReiheWaagerecht(2));
    }

    @Test
    void testSenkrecht() {
        Spielfeld f = new Spielfeld();
        for (int z = 2; z <= 5; z++) f.getFeld()[z][3] = 1;
        assertTrue(f.vierInReiheSenkrecht(1));
        assertFalse(f.vierInReiheSenkrecht(2));
    }

    @Test
    void testDiagonalUntenRechts() {
        Spielfeld f = new Spielfeld();
        f.getFeld()[2][0] = 1; f.getFeld()[3][1] = 1;
        f.getFeld()[4][2] = 1; f.getFeld()[5][3] = 1;
        assertTrue(f.vierInReiheDiagonal(1));
    }

    @Test
    void testDiagonalUntenLinks() {
        Spielfeld f = new Spielfeld();
        f.getFeld()[2][6] = 2; f.getFeld()[3][5] = 2;
        f.getFeld()[4][4] = 2; f.getFeld()[5][3] = 2;
        assertTrue(f.vierInReiheDiagonal(2));
    }

    @Test
    void testHatGewonnen() {
        Spielfeld f = new Spielfeld();
        for (int s = 0; s < 4; s++) f.getFeld()[0][s] = 1;
        assertTrue(f.hatGewonnen(1));
        assertFalse(f.hatGewonnen(2));
    }

    // ---------- Spielfeld: toString ----------

    @Test
    void testToStringLeer() {
        Spielfeld f = new Spielfeld();
        assertEquals(42, zaehle(f.toString(), '.'));   // 6 x 7 leere Zellen
    }

    @Test
    void testToStringMitStein() {
        Spielfeld f = new Spielfeld();
        f.einwerfen(3, 1);
        String t = f.toString();
        assertEquals(41, zaehle(t, '.'));
        assertTrue(t.indexOf("X") >= 0);
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
