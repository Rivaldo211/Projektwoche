import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Memory Plus". NICHT veraendern!
 * Fuehre sie in Eclipse aus: Rechtsklick auf UnitTests.java -> Run As -> JUnit Test.
 *
 * Tipp: Du musst nicht alle TODOs auf einmal schaffen. Jeder bestandene Test
 * zeigt dir, dass eine Methode korrekt funktioniert.
 */
class UnitTests {

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex");
        assertEquals("Alex", s.getName());
        assertEquals(0, s.getPunkte());
        assertEquals(0, s.getVersuche());
    }

    @Test
    void testPunktDazu() {
        Spieler s = new Spieler("Alex");
        s.punktDazu();
        s.punktDazu();
        assertEquals(2, s.getPunkte());
    }

    @Test
    void testVersuchDazu() {
        Spieler s = new Spieler("Mika");
        s.versuchDazu();
        assertEquals(1, s.getVersuche());
    }

    // ---------- Memory: Feld ----------

    @Test
    void testFeldGroesse() {
        Memory m = new Memory();
        assertEquals(4, m.getFeld().length);
        assertEquals(6, m.getFeld()[0].length);
    }

    @Test
    void testFeldInhaltVollstaendig() {
        // Egal in welcher Reihenfolge: jedes A..L und a..l muss genau einmal vorkommen.
        Memory m = new Memory();
        int[] anzahl = new int[128];
        for (int z = 0; z < m.getFeld().length; z++) {
            for (int s = 0; s < m.getFeld()[z].length; s++) {
                anzahl[m.getFeld()[z][s]]++;
            }
        }
        for (char c = 'A'; c <= 'L'; c++) {
            assertEquals(1, anzahl[c], "Grossbuchstabe fehlt/doppelt: " + c);
        }
        for (char c = 'a'; c <= 'l'; c++) {
            assertEquals(1, anzahl[c], "Kleinbuchstabe fehlt/doppelt: " + c);
        }
    }

    // ---------- Memory: passenZusammen ----------

    @Test
    void testPassenZusammenTrue() {
        Memory m = new Memory();
        assertTrue(m.passenZusammen('A', 'a'));
        assertTrue(m.passenZusammen('a', 'A'));
        assertTrue(m.passenZusammen('b', 'B'));
        assertTrue(m.passenZusammen('L', 'l'));
    }

    @Test
    void testPassenZusammenFalse() {
        Memory m = new Memory();
        assertFalse(m.passenZusammen('A', 'b'));   // verschiedene Buchstaben
        assertFalse(m.passenZusammen('A', 'A'));   // gleich, Abstand 0
        assertFalse(m.passenZusammen('a', 'c'));   // Abstand 2
    }

    // ---------- Memory: gueltigeEingabe ----------

    @Test
    void testGueltigeEingabeNeu() {
        Memory m = new Memory();
        assertTrue(m.gueltigeEingabe(0, 0));
        assertTrue(m.gueltigeEingabe(3, 5));
        assertFalse(m.gueltigeEingabe(-1, 0));
        assertFalse(m.gueltigeEingabe(4, 0));   // nur Zeilen 0-3
        assertFalse(m.gueltigeEingabe(0, 6));   // nur Spalten 0-5
        assertFalse(m.gueltigeEingabe(0, -1));
    }

    @Test
    void testGueltigeEingabeNachAufdecken() {
        Memory m = new Memory();
        m.aufdecken(1, 2);
        assertFalse(m.gueltigeEingabe(1, 2));   // schon aufgedeckt
        assertTrue(m.gueltigeEingabe(1, 3));    // noch verdeckt
    }

    // ---------- Memory: aufdecken / getZeichen / allesAufgedeckt ----------

    @Test
    void testGetZeichen() {
        Memory m = new Memory();
        char erwartet = m.getFeld()[2][3];
        assertEquals(erwartet, m.getZeichen(2, 3));
    }

    @Test
    void testAllesAufgedeckt() {
        Memory m = new Memory();
        assertFalse(m.allesAufgedeckt());
        for (int z = 0; z < Memory.ZEILEN; z++) {
            for (int s = 0; s < Memory.SPALTEN; s++) {
                m.aufdecken(z, s);
            }
        }
        assertTrue(m.allesAufgedeckt());
    }

    // ---------- Memory: toString ----------

    @Test
    void testToStringVerdeckt() {
        Memory m = new Memory();
        String t = m.toString();
        assertEquals(24, zaehle(t, '-'), "Frisches Feld muss 24 verdeckte Stellen zeigen.");
    }

    @Test
    void testToStringNachAufdecken() {
        Memory m = new Memory();
        m.aufdecken(0, 0);
        String t = m.toString();
        assertEquals(23, zaehle(t, '-'));
        assertTrue(t.indexOf(m.getZeichen(0, 0)) >= 0, "Aufgedecktes Zeichen muss sichtbar sein.");
    }

    // ---------- Main ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Main.checkEingabeMenu(1));
        assertTrue(Main.checkEingabeMenu(2));
        assertFalse(Main.checkEingabeMenu(0));
        assertFalse(Main.checkEingabeMenu(3));
        assertFalse(Main.checkEingabeMenu(-1));
    }

    @Test
    void testAuswertungSieg() {
        Spieler s1 = new Spieler("Alex");
        Spieler s2 = new Spieler("Mika");
        s1.punktDazu(); s1.punktDazu(); s1.punktDazu();   // Alex 3
        s2.punktDazu();                                   // Mika 1
        String e = Main.auswertung(s1, s2);
        assertTrue(e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0, "Alex sollte gewinnen.");
    }

    @Test
    void testAuswertungUnentschieden() {
        Spieler s1 = new Spieler("Alex");
        Spieler s2 = new Spieler("Mika");
        s1.punktDazu();
        s2.punktDazu();
        String e = Main.auswertung(s1, s2);
        assertTrue(e.indexOf("unentschieden") >= 0);
    }

    // ---------- Hilfsmethode fuer die Tests ----------

    private int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == c) n++;
        }
        return n;
    }
}
