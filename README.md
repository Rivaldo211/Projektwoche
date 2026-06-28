# Projektwoche

                   PRE-REQUIS:  Analyser la base (sujets, solutions, UnitTests)
					 
CONSTAT: Format de l'examen (constant sur tous les sujets) : un jeu console en Java à compléter (Schatzsuche, Zahlen raten, Memory, Würfel-21, Tic-Tac-Toe…). 

* Contraintes strictes et récurrentes :Seules les classes Scanner et Random + System.out.print/println autorisées (parfois quelques méthodes String selon le sujet) et pas de ä/ö/ü/ß dans le code.

* Architecture imposée : classes Spiel, Spieler, Spielfeld avec des méthodes au nom et à la signature exacts.
Correction automatisée par UnitTests → ton seuil de 8/10 vient de là.

Tes 3 points faibles, et où ils tombent dans l'examen :

- Felder (tableaux) — le plateau de jeu est toujours un tableau (boolean[][] schatzkarte, int[][] offenesSpielfeld, etc.). Parcours, comptage, voisins.
- Boolean, quasi toutes les méthodes-clés renvoient un boolean : checkEingabeMenu, gueltigeEingabe, treffer, angrenzend, weiterspielen, hatGewonnen. C'est là que les tests se gagnent.
- Traduire l'algo — l'énoncé décrit en allemand ce que chaque méthode doit faire ; il faut le transformer en code exact.


                   Etape 1: Révision Felder/Boolean
pdf ci joint avec exercice 1 a 7, les faire et uploader les exercices sous le nom Felder_Boolean.pdf




               Etape 2: Apprendre à traduire un algo (humain → Java)





             Etape 3: S'entraîner sur un examen complet






mich zu verbessern
