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
fichier .md ci joint avec exercice 1 a 7, les faire et uploader les exercices sous le nom Felder_Boolean.pdf

               Etape 2: Apprendre à traduire un algo (humain → Java)
le nouveau fichier .md ci joint avec le commentaire stufe 2 , lire et faire les exercices


             Etape 3: S'entraîner sur un examen complet

[29.06.26, 02:34:01] Mon Spécimen rare❤️🖤: package algorithmen;

public class aufgabe2 {
	public boolean gueltigeEingabe(int zeile, int spalte) {
		int[][] offenesSpielfeld ;
		int n = offenesSpielfeld.length;
		
		if(zeile >= 0 && zeile < n && spalte >= 0 && spalte < n) {
			if( offenesSpielfeld[zeile][spalte] == 0) {
				return true;
			}
		}
		return false;
	  
	}
}

package algorithmen;

public class aufgabe6 {
	static String s1;
	static String s2;
	static String unentschieden;
	public static String auswertung() {
	    if(spieler1.getPunkte() > spieler2.getPunkte()) {
	    	return s1 ;
	    }
	    else if(spieler1.getPunkte() < spieler2.getPunkte()) {
	    	return s2 ;
	    }
	    else if(spieler1.getPunkte() == spieler2.getPunkte()) {
	    	return unentschieden;
	    }
	}
}

mich zu verbessern

