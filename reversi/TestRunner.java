/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld testBoard() {
        Spielfeld f = new Spielfeld();
        int[][] b = {
            { 0, 0, 0 },
            { 0, 2, 1 },
            { 0, 0, 0 }
        };
        f.setFeldFuerTest(b);
        return f;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Reversi ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getSpielernummer() == 1;
        });

        pruefe("gegner", () -> {
            Spielfeld f = new Spielfeld();
            return f.gegner(1) == 2 && f.gegner(2) == 1;
        });

        pruefe("Startaufstellung", () -> {
            Spielfeld f = new Spielfeld();
            return f.getZelle(2, 2) == 1 && f.getZelle(0, 0) == 0
                && f.zaehleSteine(1) == 2 && f.zaehleSteine(2) == 2;
        });

        pruefe("imFeld", () -> {
            Spielfeld f = new Spielfeld();
            return f.imFeld(0, 0) && f.imFeld(5, 5)
                && !f.imFeld(-1, 0) && !f.imFeld(6, 0) && !f.imFeld(0, 6);
        });

        pruefe("istVoll", () -> {
            Spielfeld f = new Spielfeld();
            if (f.istVoll()) return false;
            f.setFeldFuerTest(new int[][] { {1, 1}, {2, 2} });
            return f.istVoll();
        });

        pruefe("pruefeRichtung", () -> {
            Spielfeld f = testBoard();
            return f.pruefeRichtung(1, 0, 0, 1, 1)
                && !f.pruefeRichtung(1, 0, 0, -1, 1)
                && !f.pruefeRichtung(1, 0, 1, 0, 1);
        });

        pruefe("istZugGueltig", () -> {
            Spielfeld f = testBoard();
            return f.istZugGueltig(1, 0, 1)
                && !f.istZugGueltig(1, 0, 2)
                && !f.istZugGueltig(1, 1, 1);
        });

        pruefe("setzeStein dreht um", () -> {
            Spielfeld f = testBoard();
            f.setzeStein(1, 0, 1);
            return f.getZelle(1, 0) == 1 && f.getZelle(1, 1) == 1 && f.getZelle(1, 2) == 1;
        });

        pruefe("hatGueltigenZug", () -> {
            Spielfeld f = testBoard();
            return f.hatGueltigenZug(1) && !f.hatGueltigenZug(2);
        });

        pruefe("toString", () -> {
            Spielfeld f = testBoard();
            String t = f.toString();
            return zaehle(t, '.') == 7 && t.indexOf("X") >= 0 && t.indexOf("O") >= 0;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3));

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
    }

    interface Pruefung { boolean test() throws Exception; }

    static void pruefe(String name, Pruefung p) {
        gesamt++;
        boolean ok;
        try {
            ok = p.test();
        } catch (Throwable t) {
            ok = false;
            name = name + "  (Ausnahme: " + t.getClass().getSimpleName() + ")";
        }
        if (ok) bestanden++;
        System.out.println((ok ? "[ OK ]  " : "[FAIL]  ") + name);
    }
}
