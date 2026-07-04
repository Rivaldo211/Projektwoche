# Alt-Klausur — Schatzsuche (Projektwoche April 2026)

Echtes Pruefungsthema aus deiner Sammlung, als Uebungsprojekt aufbereitet.
Das Geruest ist vorgegeben — du fuellst die 16 `// TODO`-Stellen.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel (Kurzform)

Quadratisches Feld n x n mit n versteckten Schaetzen. Zwei Spieler decken abwechselnd
eine Stelle auf. Schatz gefunden → Punkt und nochmal dran. Sonst → der andere ist dran.
Beim Aufdecken wird zurueckgemeldet: Schatz (S1/S2), angrenzend ein Schatz (`?`),
oder kein Schatz in der Naehe (`X`). Spielende, wenn alle n Schaetze gefunden sind.

Anzeige von `offenesSpielfeld`:
`0` → `_`, `-1` → `X`, `-2` → `?`, `1` → `S1`, `2` → `S2`.

## Projektstruktur

```
Examen_Schatzsuche/
└── src/
    ├── Spieler.java     <- Name, Spielernummer, Punkte
    ├── Spielfeld.java   <- Schatzkarte + offenes Feld + Logik
    ├── Spiel.java       <- Menue + Spielablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 15 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getSpielernummer | leicht |
| 4 | Spieler | getPunkte | leicht |
| 5 | Spieler | punktDazu | leicht |
| 6 | Spielfeld | getN | leicht |
| 7 | Spielfeld | treffer | leicht |
| 8 | Spielfeld | gueltigeEingabe | mittel |
| 9 | Spielfeld | angrenzend | schwer (Nachbarn!) |
| 10 | Spielfeld | initialisiereSpielfeld | schwer |
| 11 | Spielfeld | deckeAuf | mittel |
| 12 | Spielfeld | toString | schwer |
| 13 | Spiel | checkEingabeMenu | leicht |
| 14 | Spiel | menu | mittel |
| 15 | Spiel | weiterspielen | mittel |
| 16 | Spiel | auswertung | mittel |

## Deine Merkpunkte (aus den letzten Uebungen)

- **2D-Grenzen**: fuer Spalten `feld[i].length` benutzen, nicht `feld.length`.
- **angrenzend (TODO 9)**: jede Richtung einzeln, Grenze VOR dem Zugriff pruefen
  (`z > 0 && schatzkarte[z-1][s]`), und die Stelle selbst NICHT mitzaehlen.
- **deckeAuf (TODO 11)**: genau die Strings `TREFFER`, `NAH`, `FERN` zurueckgeben.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (15 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg — nimm dir ruhig die vollen 3 Stunden!
