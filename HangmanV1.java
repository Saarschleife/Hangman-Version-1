/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Hangman Version 1
Aus einer Datei wird zufällig ein Wort gewählt. Ein Buchstabe wird eingegeben. Ist der Buchstabe im Wort enthalten, 
wird er angezeigt, sonst wird ein Teil des Hangman gezeichnet. Ein- und Ausgabe erfolgen über die Konsole.

Alle Methoden befinden sich in der Hauptklasse.
 
Literatur:
-Insel = Ullenboom, Ch., Java ist auch eine Insel, 16. Auflage
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package hangmanVersion1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanV1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanListe = new Scanner(new File("wortliste.txt"));//Scanner zum Einlesen der Datei (Insel, 389)
		Scanner scanTastatur = new Scanner (System.in);//Scanner zum Eingeben eines Buchstabens 

		List<String> worte = new ArrayList<String>();//neue Array-Liste für String (Insel, Kap. 18)
		while (scanListe.hasNextLine()) {
			worte.add(scanListe.nextLine());
		}

		Random zufall = new Random();//Zufallsgenerator mit Klasse Random (Insel, 1156f.)
		String suchwort = worte.get (zufall.nextInt(worte.size()) );//zufälliges Wort zwischen 0 u. Länge der Liste

		List<Character> ratenSpieler = new ArrayList<Character>();//neue Array-Liste für char/geratene Buchstaben	

		int fehlerZaehler = 0;//Zähler für Fehler, beginnt bei 0

		while (true) {
			if (fehlerZaehler >= 1) {//Balken	
				System.out.println(" -----------");
			}
			if (fehlerZaehler >= 2) {//Diagonalbalken
				System.out.println(" |/        |");
			}
			if (fehlerZaehler >= 3) {//Seil
				System.out.println(" |         |");
			}
			if (fehlerZaehler >= 4) {//Kopf
				System.out.println(" |        O");
			}
			if (fehlerZaehler >= 5) {//linker Arm
				System.out.print(" |       \\ ");
				if (fehlerZaehler >= 6) {//rechter Arm
					System.out.println(" /");
				}
				else {
					System.out.println(" ");
				}
			}
			if (fehlerZaehler >= 7) {//Körper
				System.out.println(" |        || ");
			}
			if (fehlerZaehler >= 8) {//linkes Bein
				System.out.print(" |       / ");
				if (fehlerZaehler >= 9) {//rechtes Bein
					System.out.println(" \\");
				}
				else {
					System.out.println(" ");
				}
				if (fehlerZaehler >= 10) {
					System.out.println(" |______________ Verloren \n");
					System.out.println("Das gesuchte Wort war " + suchwort);
					break;
				}
			}	

			if (!eingabeSpielerBuchstabe(scanTastatur, suchwort, ratenSpieler))
				fehlerZaehler++;

			if (ausgabeWortStatus(suchwort, ratenSpieler)) {
				System.out.println("Gewonnen");
				break;
			}
		}
	}

	private static boolean eingabeSpielerBuchstabe(Scanner scanTastatur, String suchwort, List<Character> ratenSpieler) {
		System.out.println("Alle Wörter der Wortliste sind in Großbuchstaben.\nBuchstabe eingeben: ");
		String rateBuchstabe = scanTastatur.nextLine();
		ratenSpieler.add(rateBuchstabe.charAt(0));
		return (suchwort.contains(rateBuchstabe));
	}

	private static boolean ausgabeWortStatus(String suchwort, List<Character> ratenSpieler) {
		int korrektZaehler = 0;

		for (int i = 0; i < suchwort.length(); i++) {
			if (ratenSpieler.contains(suchwort.charAt(i))) {
				System.out.print((suchwort.charAt(i)));
				korrektZaehler++;
			}
			else {
				System.out.print("_ ");
			}
		}
		System.out.println();
		return (suchwort.length() == korrektZaehler);
	}
}
