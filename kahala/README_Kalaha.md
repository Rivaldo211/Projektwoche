# Uebungs-Klausur — Kalaha / Mancala

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 14 `// TODO`s.
Kern-Thema: **modulare Arithmetik** (Saeen im Kreis mit `% 14`).

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

14 Mulden in einem Kreis (Array):
```
Index:   0  1  2  3  4  5     6        7  8  9 10 11 12    13
        [ Spieler-1-Mulden ] [K1]  [ Spieler-2-Mulden ]  [K2]
```
- Start: jede Spielmulde 4 Steine, Kalahas (6 und 13) leer.
- Zug: alle Steine aus einer eigenen Mulde nehmen und einzeln im Kreis in die
  folgenden Mulden legen. Das **gegnerische Kalaha wird uebersprungen**.
- Landet der letzte Stein im eigenen Kalaha -> nochmal ziehen.
- Ende, wenn eine Seite (alle eigenen Mulden) leer ist. Wer mehr im Kalaha hat, gewinnt.

## Projektstruktur

```
Examen_Kalaha/
└── src/
    ├── Spieler.java     <- Name, Spielernummer
    ├── Spielfeld.java   <- 14 Mulden + Saee-Logik
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
| 4 | Spielfeld | getMulde | leicht |
| 5 | Spielfeld | kalahaIndex | leicht |
| 6 | Spielfeld | getKalaha | leicht |
| 7 | Spielfeld | eigeneMulde | mittel |
| 8 | Spielfeld | istZugGueltig | mittel |
| 9 | Spielfeld | saee | schwer (Kern!) |
| 10 | Spielfeld | letzteImEigenenKalaha | leicht |
| 11 | Spielfeld | istSpielEnde | mittel |
| 12 | Spielfeld | toString | mittel |
| 13 | Spiel | checkEingabeMenu | leicht |
| 14 | Spiel | menu | mittel |

## Deine Merkpunkte

- **saee (TODO 9)** ist das Herz und die modulare Arithmetik:
  ```
  pos = (pos + 1) % MULDEN;   // im Kreis, springt von 13 zurueck auf 0
  if (pos == gegnerKalaha) continue;   // Gegner-Kalaha ueberspringen
  ```
  gegnerisches Kalaha = `(nummer == 1) ? P2_KALAHA : P1_KALAHA`.
- **eigeneMulde (TODO 7)**: Spieler 1 -> `0..5`, Spieler 2 -> `7..12` (Kalahas 6/13 zaehlen nicht).
- **istSpielEnde (TODO 11)**: eine Seite komplett leer -> Ende.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (13 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
