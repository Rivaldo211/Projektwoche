import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Minesucher". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /** 3x3-Feld mit genau einer Mine in der Mitte (1,1). */
    private Spielfeld mitteBoard() {
        Spielfeld f = new Spielfeld(3, 1);
        boolean[][] karte = new boolean[3][3];
        karte[1][1] = true;
        f.setMinenFuerTest(karte);
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
        Spieler s = new Spieler("Alex");
        assertEquals("Alex", s.getName());
        assertEquals(0, s.getPunkte());
    }

    @Test
    void testPunktDazu() {
        Spieler s = new Spieler("Alex");
        s.punktDazu(); s.punktDazu();
        assertEquals(2, s.getPunkte());
    }

    // ---------- Spielfeld: Grundlagen ----------

    @Test
    void testGetN() {
        assertEquals(5, new Spielfeld(5, 3).getN());
    }

    @Test
    void testAnzahlMinen() {
        int[][] faelle = { {4, 5}, {5, 3}, {6, 10} };
        for (int[] fall : faelle) {
            Spielfeld f = new Spielfeld(fall[0], fall[1]);
            int c = 0;
            for (int z = 0; z < fall[0]; z++)
                for (int s = 0; s < fall[0]; s++)
                    if (f.getMinen()[z][s]) c++;
            assertEquals(fall[1], c, "Falsche Minenzahl bei n=" + fall[0]);
        }
    }

    @Test
    void testIstMine() {
        Spielfeld f = mitteBoard();
        assertTrue(f.istMine(1, 1));
        assertFalse(f.istMine(0, 0));
    }

    @Test
    void testGueltigeEingabe() {
        Spielfeld f = mitteBoard();
        assertTrue(f.gueltigeEingabe(0, 0));
        assertFalse(f.gueltigeEingabe(-1, 0));
        assertFalse(f.gueltigeEingabe(3, 0));
        assertFalse(f.gueltigeEingabe(0, 3));
        f.aufdecken(0, 0);
        assertFalse(f.gueltigeEingabe(0, 0));
    }

    // ---------- Spielfeld: Nachbarminen ----------

    @Test
    void testNachbarminenMitte() {
        Spielfeld f = mitteBoard();          // Mine nur in (1,1)
        assertEquals(1, f.zaehleNachbarminen(0, 0));  // diagonal
        assertEquals(1, f.zaehleNachbarminen(0, 1));
        assertEquals(1, f.zaehleNachbarminen(2, 2));
        assertEquals(0, f.zaehleNachbarminen(1, 1));  // die Stelle selbst zaehlt nicht
    }

    @Test
    void testNachbarminenMehrere() {
        Spielfeld f = new Spielfeld(3, 1);
        boolean[][] karte = new boolean[3][3];
        karte[0][0] = true;
        karte[0][1] = true;
        f.setMinenFuerTest(karte);
        assertEquals(2, f.zaehleNachbarminen(1, 1));
    }

    // ---------- Spielfeld: Sieg ----------

    @Test
    void testAlleSicherenAufgedeckt() {
        Spielfeld f = mitteBoard();
        assertFalse(f.alleSicherenAufgedeckt());
        for (int z = 0; z < 3; z++)
            for (int s = 0; s < 3; s++)
                if (!f.getMinen()[z][s]) f.aufdecken(z, s);
        assertTrue(f.alleSicherenAufgedeckt());
    }

    // ---------- Spielfeld: toString ----------

    @Test
    void testToStringVerdeckt() {
        Spielfeld f = new Spielfeld(4, 3);
        assertEquals(16, zaehle(f.toString(), '#'));
    }

    @Test
    void testToStringZahl() {
        Spielfeld f = mitteBoard();
        f.aufdecken(0, 0);                       // (0,0) hat 1 Nachbarmine
        String t = f.toString();
        assertEquals(8, zaehle(t, '#'));
        assertTrue(t.indexOf("1") >= 0);
    }

    @Test
    void testToStringMine() {
        Spielfeld f = mitteBoard();
        f.aufdecken(1, 1);                       // Mine aufgedeckt
        assertTrue(f.toString().indexOf("*") >= 0);
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
