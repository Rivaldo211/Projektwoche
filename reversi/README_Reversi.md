# Uebungs-Klausur — Reversi / Othello (6x6)

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 15 `// TODO`s.
Das ist die anspruchsvollste der drei Aufgaben (Richtungen + Umdrehen).

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

6x6-Brett, Start: vier Steine in der Mitte. Zwei Spieler setzen abwechselnd.
Ein Zug ist gueltig, wenn man in mindestens einer der **8 Richtungen** eine Reihe
GEGNERISCHER Steine "einklemmt", die von einem EIGENEN Stein abgeschlossen wird.
Alle eingeklemmten Steine werden umgedreht. Kann ein Spieler nicht ziehen, setzt er aus.
Ende, wenn niemand mehr ziehen kann oder das Feld voll ist. Wer mehr Steine hat, gewinnt.

`feld[z][s]`: `0` leer, `1` Spieler 1, `2` Spieler 2. Anzeige: `.`, `X`, `O`.

## Projektstruktur

```
Examen_Reversi/
└── src/
    ├── Spieler.java     <- Name, Spielernummer
    ├── Spielfeld.java   <- Brett + Reversi-Logik
    ├── Spiel.java       <- Menue + Ablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 12 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getSpielernummer | leicht |
| 4 | Spielfeld | getZelle | leicht |
| 5 | Spielfeld | gegner | leicht |
| 6 | Spielfeld | imFeld | leicht |
| 7 | Spielfeld | zaehleSteine | mittel |
| 8 | Spielfeld | istVoll | mittel |
| 9 | Spielfeld | pruefeRichtung | schwer (Kern!) |
| 10 | Spielfeld | istZugGueltig | mittel |
| 11 | Spielfeld | setzeStein | schwer |
| 12 | Spielfeld | hatGueltigenZug | mittel |
| 13 | Spielfeld | toString | mittel–schwer |
| 14 | Spiel | checkEingabeMenu | leicht |
| 15 | Spiel | menu | mittel |

## Deine Merkpunkte

- **pruefeRichtung (TODO 9)** ist das Herz: erst ein Schritt in Richtung (dz, ds),
  dort MUSS ein Gegnerstein liegen; dann weiterlaufen ueber Gegnersteine; am Ende
  MUSS ein eigener Stein stehen. Immer `imFeld(...)` pruefen, bevor du zugreifst.
- **setzeStein (TODO 11)** nutzt dieselbe Logik: in jeder Richtung, in der
  pruefeRichtung true ist, die Gegnersteine bis zum eigenen Stein umdrehen.
- **8 Richtungen**: nutze die vorgegebenen Arrays `DZ`/`DS` in einer Schleife.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (12 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg — nimm dir Zeit fuer TODO 9 und 11!
