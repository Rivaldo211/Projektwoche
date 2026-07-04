# Uebungs-Klausur — Vier gewinnt (Connect Four)

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 14 `// TODO`s.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

Feld mit 6 Zeilen und 7 Spalten. Zwei Spieler werfen abwechselnd einen Stein in eine
Spalte; der Stein faellt nach unten auf die unterste freie Stelle. Wer zuerst **vier
gleiche Steine** in einer Reihe hat (waagerecht, senkrecht oder diagonal), gewinnt.
Ist das Feld voll und niemand hat vier in Reihe: Unentschieden.

`feld[z][s]`: `0` leer, `1` Spieler 1, `2` Spieler 2. Anzeige: `0`→`.`, `1`→`X`, `2`→`O`.

## Projektstruktur

```
Examen_VierGewinnt/
└── src/
    ├── Spieler.java     <- Name, Spielernummer
    ├── Spielfeld.java   <- 6x7-Brett + Logik
    ├── Spiel.java       <- Menue + Ablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 13 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getSpielernummer | leicht |
| 4 | Spielfeld | getZelle | leicht |
| 5 | Spielfeld | spalteGueltig | mittel |
| 6 | Spielfeld | istVoll | mittel |
| 7 | Spielfeld | einwerfen | mittel–schwer |
| 8 | Spielfeld | vierInReiheWaagerecht | mittel |
| 9 | Spielfeld | vierInReiheSenkrecht | mittel |
| 10 | Spielfeld | vierInReiheDiagonal | schwer |
| 11 | Spielfeld | hatGewonnen | leicht |
| 12 | Spielfeld | toString | schwer |
| 13 | Spiel | checkEingabeMenu | leicht |
| 14 | Spiel | menu | mittel |

## Deine Merkpunkte

- **2D-Grenzen**: Zeilen `feld.length`, Spalten `feld[z].length` (bzw. `SPALTEN`).
- **einwerfen (TODO 7)**: von UNTEN (`z = ZEILEN-1`) nach oben suchen, erste freie Zelle.
- **Gewinn (TODO 8–10)**: Startindex nur bis `LAENGE - GEWINN` laufen lassen, sonst
  laeufst du aus dem Feld. Diagonalen haben ZWEI Richtungen (`s+i` und `s-i`).
- **hatGewonnen (TODO 11)**: nur die drei Methoden mit `||` verbinden.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (13 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
