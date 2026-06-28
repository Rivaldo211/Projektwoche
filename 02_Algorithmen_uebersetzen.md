# Lernblatt 2 — Algorithmen übersetzen (Mensch → Java)

> Ziel: einen Aufgabentext (deutsch) sicher in korrekten Java-Code umwandeln.
> Genau das verlangt die Prüfung: Jede Methode wird in Worten beschrieben — du schreibst den Rumpf.
> Erinnerung: kein `ä ö ü ß` im Code.

---

## 1. Die 5-Schritte-Methode

Bei JEDER Methode immer gleich vorgehen:

1. **Signatur lesen** — Name, Parameter, Rückgabetyp stehen schon da. Der **Rückgabetyp** sagt dir, was am Ende rauskommen muss:
   - `boolean` → du musst `true` oder `false` liefern → denk an ein Vergleich/Bedingung.
   - `int` → eine Zahl (oft ein Zaehler oder ein Index).
   - `String` → ein Text (oft einer der vorgegebenen Strings).
   - `void` → kein Rückgabewert, du machst etwas (ausgeben, ein Attribut setzen).
2. **Schlüsselwörter im Text markieren** — sie verraten die Struktur (siehe Tabelle unten).
3. **Struktur aufschreiben** (Pseudocode / Kommentare) BEVOR du Java tippst.
4. **Java schreiben**, Muster aus Lernblatt 1 wiederverwenden (zaehlen, suchen, Nachbarn...).
5. **Gegen die UnitTests prüfen.**

---

## 2. Die Übersetzungstabelle (Deutsch → Java)

| Im Aufgabentext steht... | Das bedeutet in Java |
|---|---|
| „gibt `true` zurück, wenn ... , sonst `false`" | `return bedingung;` |
| „... wenn ... , ansonsten ..." | `if (...) { ... } else { ... }` |
| „so lange ... bis eine gültige Eingabe erfolgt" | Schleife `while` / `do ... while` |
| „für jede Figur / jede Zelle / alle ..." | Schleife `for` |
| „mindestens ein ..." | ODER-Logik (`\|\|`) bzw. `count >= 1` |
| „alle ... / jede ..." (Bedingung gilt überall) | Schleife + bei Gegenbeispiel `return false`, sonst `true` |
| „zählt, wie viele ..." | `int count = 0; ... count++; return count;` |
| „findet die erste/eine Stelle, an der ..." | Suchschleife: bei Treffer `return ...`, danach `return -1`/`null` |
| „setzt das Attribut X auf ..." | `this.x = ...;` |
| „erhöht / verringert um 1" | `x++;` / `x--;` |
| „eine Zufallszahl zwischen 0 und n-1" | `rd.nextInt(n)` |
| „eine Zufallszahl zwischen 1 und 6" | `rd.nextInt(6) + 1` |
| „gibt einen der Strings s1, s2, ... zurück" | genau diese vorgegebenen Strings benutzen, nichts Eigenes |

> Faustregel: Der Rückgabetyp + ein Schlüsselwort legen schon 80 % der Struktur fest.

---

## 3. Durchgerechnetes Beispiel A — `menu()`

**Aufgabentext (Prüfung April 2026):**
> `public static int menu()`: Fragt so lange nach einer Menu-Eingabe, bis eine
> erlaubte Zahl eingegeben wird. Diese Zahl ist der Rückgabewert der Methode.

**Schritt 1 — Signatur:** Rückgabetyp `int` → muss eine Zahl liefern.
**Schritt 2 — Schlüsselwörter:** „so lange ... bis" → **Schleife**. „erlaubte Zahl" → es gibt schon `checkEingabeMenu(...)`.
**Schritt 3 — Struktur:**
```
wiederhole:
    Menu anzeigen
    Zahl einlesen
solange die Zahl NICHT erlaubt ist
gib die Zahl zurueck
```
**Schritt 4 — Java:**
```java
public static int menu() {
    int eingabe;
    do {
        System.out.println("1 - Spielen");
        System.out.println("2 - Programm beenden");
        eingabe = sc.nextInt();      // sc = vorgegebener Scanner
    } while (!checkEingabeMenu(eingabe));   // wiederhole, solange NICHT gueltig
    return eingabe;
}
```
Beachte: `do...while` ist hier ideal, weil das Menu **mindestens einmal** gezeigt werden muss.

---

## 4. Durchgerechnetes Beispiel B — `weiterspielen()`

**Aufgabentext:**
> `public static boolean weiterspielen()`: Gibt `true` zurück, wenn noch nicht alle
> Schätze gefunden wurden, `false` sonst. (Tipp: Punkte der Spieler + Anzahl der Schätze.)

**Schritt 1 — Signatur:** `boolean` → ein Vergleich.
**Schritt 2:** „noch nicht alle gefunden" → Summe der Punkte < Anzahl Schätze.
Annahme: Attribute `spieler1`, `spieler2` mit `getPunkte()`, und `feld.getN()` = Anzahl Schätze.
**Schritt 4 — Java:**
```java
public static boolean weiterspielen() {
    int gefunden = spieler1.getPunkte() + spieler2.getPunkte();
    return gefunden < feld.getN();
}
```
Kein `if/else`, kein `return true/false` — direkt der boolesche Ausdruck.

---

## 5. AUFGABEN

Schreibe jeweils den **Methodenrumpf**. Geh die 5 Schritte durch.
Die nötigen Attribute/Methoden stehen jeweils dabei.

### Aufgabe 1 — `checkEingabe` (boolean, Bereich)
> Gibt `true` zurück, wenn die Spielfeldgröße gültig ist. Gültig heißt: zwischen 2 und 10 (beide inklusive).
```java
public static boolean checkGroesse(int n) {
    // TODO
}
```

### Aufgabe 2 — `gueltigeEingabe` (boolean, Feld 2D)
> Attribut: `int[][] offenesSpielfeld` der Größe `n x n`, Attribut `int n`.
> Gibt `true` zurück, wenn (zeile, spalte) existierende Koordinaten sind UND die Stelle
> in `offenesSpielfeld` den Wert `0` hat. Sonst `false`.
```java
public boolean gueltigeEingabe(int zeile, int spalte) {
    // TODO
}
```

### Aufgabe 3 — `treffer` (boolean, Feld 2D)
> Attribut: `boolean[][] schatzkarte`. Gibt `true` zurück, wenn an der Stelle (zeile, spalte)
> ein Schatz liegt. Es ist sichergestellt, dass nur gültige Werte übergeben werden.
```java
public boolean treffer(int zeile, int spalte) {
    // TODO
}
```

### Aufgabe 4 — `wuerfeln` (int, Random)
> Attribut: `Random rd`. Gibt eine Zufallszahl zwischen 1 und 6 zurück.
```java
public static int wuerfeln() {
    // TODO
}
```

### Aufgabe 5 — `zaehleSchaetze` (int, Feld 2D, zählen)
> Attribut: `boolean[][] schatzkarte`. Gibt zurück, wie viele Schätze (true) insgesamt
> auf der Karte versteckt sind.
```java
public int zaehleSchaetze() {
    // TODO
}
```

### Aufgabe 6 — `auswertung` (String, Mehrfach-Verzweigung)
> Vorgegebene Strings (Attribute): `s1`, `s2`, `unentschieden`.
> Vergleicht die Punkte von `spieler1` und `spieler2`.
> Gibt `s1` zurück, wenn Spieler 1 mehr Punkte hat, `s2`, wenn Spieler 2 mehr hat,
> sonst `unentschieden`. (Benutze NUR diese drei Strings.)
```java
public static String auswertung() {
    // TODO  (spieler1.getPunkte(), spieler2.getPunkte())
}
```

### Aufgabe 7 — `menu()` Variante (do-while, eigenständig)
> Gibt so lange das Menu aus und liest eine Zahl ein, bis eine Zahl zwischen 1 und 3
> eingegeben wird. Diese Zahl wird zurückgegeben. (Scanner-Attribut: `sc`.)
```java
public static int menu() {
    // TODO
}
```
