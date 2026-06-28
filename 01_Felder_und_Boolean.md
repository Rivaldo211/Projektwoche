# Lernblatt 1 — Felder & Boolean

> Ziel: die zwei Themen beherrschen, an denen die UnitTests der Prüfung gewonnen werden.
> Goldene Regel der Prüfung: **kein `ä ö ü ß`** im Code, auch nicht in Kommentaren oder String-Literalen.

---

## TEIL A — Felder (Arrays)

### A.1 Das 1D-Feld

Ein Feld ist eine Folge von Zellen, die ab **0** nummeriert sind.

```java
int[] punkte = new int[5];   // 5 Zellen: punkte[0] ... punkte[4], alle 0
punkte[2] = 7;               // schreibt in die Zelle mit Index 2
int x = punkte[2];           // liest -> x = 7
int groesse = punkte.length; // 5  (KEINE Klammern: .length, nicht .length())
```

Erzeugung mit direkten Werten:
```java
String[] namen = {"Alex", "Toni"};   // namen.length == 2
```

### A.2 Ein Feld durchlaufen

Die klassische `for`-Schleife (wenn man den Index braucht):
```java
for (int i = 0; i < punkte.length; i++) {
    System.out.println(punkte[i]);
}
```

Die `for-each`-Schleife (wenn man den Index NICHT braucht):
```java
for (int p : punkte) {
    System.out.println(p);
}
```

### A.3 Das 2D-Feld (das Spielfeld)

Das ist **der** zentrale Punkt der Prüfung: Das Spielfeld ist fast immer ein 2D-Feld.

```java
boolean[][] schatzkarte = new boolean[n][n];  // n Zeilen, n Spalten, alles false
int[][] feld = new int[n][n];                  // alles 0

feld[zeile][spalte] = -2;          // Zelle (zeile, spalte)
int v = feld[1][3];                // Lesen
int zeilen  = feld.length;         // Anzahl Zeilen (n)
int spalten = feld[0].length;      // Anzahl Spalten (n)
```

Doppelschleife, um alles zu durchlaufen:
```java
for (int z = 0; z < feld.length; z++) {
    for (int s = 0; s < feld[z].length; s++) {
        // ... feld[z][s] ...
    }
}
```

> Konvention der Prüfung: **erster Index = zeile (Zeile)**, **zweiter = spalte (Spalte)**.

### A.4 Die 3 Muster, die immer wiederkommen

**1) Zaehlen** (wie viele Zellen erfüllen eine Bedingung):
```java
int count = 0;
for (int z = 0; z < feld.length; z++) {
    for (int s = 0; s < feld[z].length; s++) {
        if (feld[z][s] == 1) count++;
    }
}
return count;
```

**2) Suchen / Existenz prüfen** → liefert einen boolean (siehe Teil B).

**3) Die Nachbarn betrachten** (oben/unten/links/rechts, NICHT diagonal) — typisch für `angrenzend()`:
```java
// Zelle (z, s): wir betrachten die 4 Nachbarn und bleiben IM Feld
if (z > 0            && feld[z-1][s] ...) ...   // oben
if (z < n-1          && feld[z+1][s] ...) ...   // unten
if (s > 0            && feld[z][s-1] ...) ...   // links
if (s < n-1          && feld[z][s+1] ...) ...   // rechts
```
Die Prüfung `z > 0`, `z < n-1` usw. verhindert, dass man aus dem Feld läuft (`ArrayIndexOutOfBoundsException`).

---

## TEIL B — Boolean

### B.1 Wiederholung

Ein `boolean` kann nur `true` oder `false` sein.

Vergleichsoperatoren (ergeben einen boolean): `==`, `!=`, `<`, `<=`, `>`, `>=`
Logische Operatoren: `&&` (UND), `||` (ODER), `!` (NICHT)

| a | b | a && b | a \|\| b | !a |
|---|---|--------|----------|----|
| true | true | true | true | false |
| true | false | false | true | false |
| false | true | false | true | true |
| false | false | false | false | true |

### B.2 Der Fehler Nr. 1 bei Anfängern

```java
// SCHLECHT - umstaendlich und fehleranfaellig
boolean checkEingabeMenu(int e) {
    if (e == 1 || e == 2) {
        return true;
    } else {
        return false;
    }
}

// GUT - man gibt den booleschen Ausdruck DIREKT zurueck
boolean checkEingabeMenu(int e) {
    return e == 1 || e == 2;
}
```
Regel: Wenn du `if (bedingung) return true; else return false;` schreibst, ersetze es durch `return bedingung;`.

### B.3 Mehrere Bedingungen mit && verknüpfen

Echtes Beispiel: `gueltigeEingabe(zeile, spalte)` soll true sein, wenn die Koordinaten existieren **UND** die Zelle noch nicht aufgedeckt ist (`offenesSpielfeld == 0`).
```java
boolean gueltigeEingabe(int zeile, int spalte) {
    return zeile >= 0 && zeile < n
        && spalte >= 0 && spalte < n
        && offenesSpielfeld[zeile][spalte] == 0;
}
```
> Tipp: Setze IMMER die Grenzprüfung (`>= 0 && < n`) VOR den Feldzugriff.
> Dank der Kurzschluss-Auswertung von `&&` wertet Java den Rest nicht aus, wenn die Grenzen falsch sind, und greift so nicht auf eine Zelle außerhalb des Feldes zu.

### B.4 Ein boolean als „Flagge“ (Flag)

Für `treffer` / „gibt es mindestens ein...“:
```java
boolean angrenzend(int z, int s) {
    boolean gefunden = false;
    if (z > 0   && schatzkarte[z-1][s]) gefunden = true;
    if (z < n-1 && schatzkarte[z+1][s]) gefunden = true;
    if (s > 0   && schatzkarte[z][s-1]) gefunden = true;
    if (s < n-1 && schatzkarte[z][s+1]) gefunden = true;
    return gefunden;
}
```
(Hinweis: `schatzkarte[z-1][s]` ist bereits ein boolean, kein `== true` nötig.)

---

## AUFGABEN

Bearbeite sie der Reihe nach. Schreibe den Methodenrumpf.
Erinnerung: kein `ä ö ü ß`.

### Aufgabe 1 — Feld 1D (zaehlen)
Schreibe `public static int zaehleGerade(int[] a)`, die die Anzahl der **geraden** Werte im Feld `a` zurückgibt. (gerade = `x % 2 == 0`)

### Aufgabe 2 — Feld 1D (Existenz → boolean)
Schreibe `public static boolean enthaelt(int[] a, int gesucht)`, die `true` zurückgibt, wenn `gesucht` mindestens einmal in `a` vorkommt, sonst `false`.

### Aufgabe 3 — Boolean (Vereinfachung)
Schreibe diese Methode in EINER einzigen Zeile `return ...;` um:
```java
public static boolean istImBereich(int x) {
    if (x >= 1 && x <= 100) { return true; } else { return false; }
}
```

### Aufgabe 4 — Boolean (Verknüpfung &&)
Schreibe `public static boolean gueltigeKoordinate(int z, int s, int n)`, die `true` zurückgibt, wenn `z` und `s` beide zwischen `0` (inklusive) und `n` (exklusive) liegen.

### Aufgabe 5 — Feld 2D (zaehlen)
Gegeben `int[][] feld`. Schreibe `public static int zaehleWert(int[][] feld, int wert)`, die zurückgibt, wie viele Zellen genau den Wert `wert` haben.

### Aufgabe 6 — Feld 2D + Boolean (Nachbarn)
Gegeben `boolean[][] karte` der Größe `n x n`. Schreibe
`public static boolean hatNachbarTrue(boolean[][] karte, int z, int s)`,
die `true` zurückgibt, wenn es mindestens einen direkten Nachbarn (oben, unten, links, rechts — nicht diagonal) gibt, der `true` ist. Achte darauf, nicht aus dem Feld zu laufen.

### Aufgabe 7 — Synthese (nah an der Prüfung)
Gegeben `int[][] offenesSpielfeld` der Größe `n x n`, wobei `0` = noch nicht aufgedeckte Zelle.
Schreibe `public static boolean alleAufgedeckt(int[][] feld)`, die `true` zurückgibt, wenn **keine** Zelle mehr `0` ist.
