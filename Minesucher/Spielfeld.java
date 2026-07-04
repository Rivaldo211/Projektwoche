import java.util.Random;

/**
 * Spielfeld - das quadratische Feld (n x n) fuer "Minesucher".
 *
 * Zwei Felder:
 *   - minen[z][s]       : true = hier liegt eine Mine.
 *   - aufgedeckt[z][s]  : true = diese Stelle wurde bereits aufgedeckt.
 *
 * Anzeige (toString):
 *   - nicht aufgedeckt        -> "#"
 *   - aufgedeckt und Mine     -> "*"
 *   - aufgedeckt, keine Mine  -> Anzahl der Minen in den 8 Nachbarn (0..8)
 *
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spielfeld {

    private int n;
    private int anzahlMinen;
    private boolean[][] minen;
    private boolean[][] aufgedeckt;
    private Random rd;

    /** Erzeugt ein n x n Feld und verteilt anzahlMinen Minen. (gegeben - nicht aendern) */
    public Spielfeld(int n, int anzahlMinen) {
        this.n = n;
        this.anzahlMinen = anzahlMinen;
        this.minen = new boolean[n][n];
        this.aufgedeckt = new boolean[n][n];
        this.rd = new Random();
        initialisiereMinen();
    }

    /**
     * TODO 10 (schwer): Verteilt genau anzahlMinen Minen an VERSCHIEDENEN Stellen.
     * Eine Zufallszahl zwischen 0 und n-1 bekommst du mit rd.nextInt(n).
     * Hinweis: Zaehle, wie viele Minen schon gesetzt sind. Solange noch nicht
     *   anzahlMinen gesetzt sind: zufaellige Stelle (z, s) waehlen; liegt dort noch
     *   KEINE Mine, setze minen[z][s] = true und erhoehe den Zaehler. (while-Schleife)
     */
    private void initialisiereMinen() {
        // TODO 10
    }

    /** TODO 5 (leicht): n zurueckgeben. */
    public int getN() {
        // TODO 5
        return 0;
    }

    /** TODO 6 (leicht): true, wenn an (z, s) eine Mine liegt. */
    public boolean istMine(int z, int s) {
        // TODO 6
        return false;
    }

    /** TODO 7 (leicht): Stelle (z, s) aufdecken (Markierung setzen). */
    public void aufdecken(int z, int s) {
        // TODO 7
    }

    /**
     * TODO 8 (mittel): Gueltige Eingabe?
     * true, wenn (z, s) existierende Koordinaten sind UND die Stelle noch NICHT
     * aufgedeckt ist. Sonst false.
     * Hinweis: zuerst die Grenzen (0..n-1) pruefen, dann erst auf das Feld zugreifen.
     */
    public boolean gueltigeEingabe(int z, int s) {
        // TODO 8
        return false;
    }

    /**
     * TODO 9 (schwer): Zaehlt die Minen in den 8 Nachbarn von (z, s)
     * (oben, unten, links, rechts UND die vier Diagonalen). Die Stelle (z, s)
     * selbst zaehlt NICHT mit.
     * Hinweis: zwei Schleifen dz von -1..1 und ds von -1..1. Ueberspringe dz==0 && ds==0.
     *   Pruefe fuer jeden Nachbarn zuerst die Grenzen, dann minen[z+dz][s+ds].
     */
    public int zaehleNachbarminen(int z, int s) {
        // TODO 9
        return 0;
    }

    /**
     * TODO 11 (mittel): Sind alle sicheren Felder aufgedeckt (= gewonnen)?
     * true, wenn jede Stelle, die KEINE Mine ist, bereits aufgedeckt wurde.
     * Hinweis: bei der ersten sicheren, noch verdeckten Stelle -> return false.
     */
    public boolean alleSicherenAufgedeckt() {
        // TODO 11
        return false;
    }

    /**
     * TODO 12 (schwer): Baut die Anzeige als String.
     * Pro Stelle ein Token (siehe oben: "#", "*" oder eine Zahl), in einer Zeile
     * mit einem Leerzeichen getrennt, Zeilen mit '\n' getrennt.
     * Hinweis: fuer aufgedeckte sichere Felder das Ergebnis von zaehleNachbarminen
     *   benutzen (z. B. "" + zaehleNachbarminen(z, s)).
     */
    public String toString() {
        // TODO 12
        return "";
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public boolean[][] getMinen() {
        return minen;
    }

    public boolean[][] getAufgedeckt() {
        return aufgedeckt;
    }

    /** Setzt eine bekannte Minen-Karte fuer die Tests und leert das aufgedeckt-Feld. */
    public void setMinenFuerTest(boolean[][] karte) {
        this.minen = karte;
        this.n = karte.length;
        this.aufgedeckt = new boolean[n][n];
    }
}
