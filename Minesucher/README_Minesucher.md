# Uebungs-Klausur — Minesucher (Minesweeper, 1 Spieler)

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 14 `// TODO`s.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

Quadratisches Feld n x n mit einigen zufaellig verteilten Minen. Der Spieler deckt
Stellen auf:
- Ist dort eine Mine -> verloren.
- Sonst zeigt die Stelle, wie viele der **8 Nachbarn** (inkl. Diagonalen) Minen sind.
Alle sicheren (minenfreien) Felder aufgedeckt -> gewonnen.

Anzeige: `#` verdeckt, `*` aufgedeckte Mine, Zahl `0..8` = Anzahl Nachbarminen.

## Projektstruktur

```
Examen_Minesucher/
└── src/
    ├── Spieler.java     <- Name, Punkte
    ├── Spielfeld.java   <- Minenkarte + Logik
    ├── Spiel.java       <- Menue + Ablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 14 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getPunkte | leicht |
| 4 | Spieler | punktDazu | leicht |
| 5 | Spielfeld | getN | leicht |
| 6 | Spielfeld | istMine | leicht |
| 7 | Spielfeld | aufdecken | leicht |
| 8 | Spielfeld | gueltigeEingabe | mittel |
| 9 | Spielfeld | zaehleNachbarminen | schwer (8 Nachbarn!) |
| 10 | Spielfeld | initialisiereMinen | schwer |
| 11 | Spielfeld | alleSicherenAufgedeckt | mittel |
| 12 | Spielfeld | toString | schwer |
| 13 | Spiel | checkEingabeMenu | leicht |
| 14 | Spiel | menu | mittel |

## Deine Merkpunkte

- **zaehleNachbarminen (TODO 9)**: 8 Richtungen! Zwei Schleifen `dz`, `ds` von -1..1,
  `dz==0 && ds==0` ueberspringen, Grenzen VOR dem Zugriff pruefen, Stelle selbst nicht mitzaehlen.
- **initialisiereMinen (TODO 10)**: verschiedene Stellen -> nur zaehlen, wenn dort noch
  keine Mine lag (`while`-Schleife).
- **2D-Grenzen**: Zeilen `feld.length`, Spalten `feld[z].length`.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (14 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
