package projekt;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Databaza db = new Databaza();
		boolean run = true;
		
		while(run) {
		System.out.println("----------- DATABAZOVY SYSTEM ZAMESTNANCOV -----------");
		System.out.println("------------------------------------------------------");
		System.out.println("Pre vyber pozadovanej cinnosti zadajte prislusne cislo");
		System.out.println("0 : Ukoncenie programu");
		System.out.println("1 : Pridanie noveho zamestnanca");
		System.out.println("2 : Odstranenie zamestnanca");
		System.out.println("3 : Vypis zamestnancov"); // musi byt abecedne + rozdelene podla profesie
		
		int volba = sc.nextInt();
		
			switch(volba) {
				case 1:
					System.out.println("---NOVY ZAMESTNANEC---");
					System.out.println("Meno: ");
					String meno = sc.next();
					System.out.println("Priezvisko: ");
					String priezvisko = sc.next();
					System.out.println("Rok narodenia: ");
					int rokNarodenia = sc.nextInt();
					System.out.println("Profesia (1 = Datovy analytik | 2 = Bezpecnostny specialista): ");
					int profesia = sc.nextInt();
					
					String strProfesia;
					if(profesia == 1) {
						strProfesia = "Datovy analytik";
					} else {
						strProfesia = "Bezpecnostny specialista";
					}	
					
					db.novyZamestnanec(meno, priezvisko, rokNarodenia, profesia, strProfesia);
					break;
					
				case 2:
					System.out.println("---ODSTRANENIE ZAMESTNANCA---");
					System.out.println("ID zamestnanca: ");
					int id = sc.nextInt();
					
					db.odstranZamestnanca(id);
					break;
					
				case 3:
					System.out.println("---ZOZNAM ZAMESTNANCOV---");
					db.vypisZamestnancov();
					System.out.println("");
					break;
					
				case 4:
					break;
				case 0:
					run=false;
					break;
		
			}
		
		}
	}
}
