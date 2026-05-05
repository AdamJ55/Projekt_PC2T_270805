package projekt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Databaza db = new Databaza();
		boolean run = true;

		while(run) {
		int volba = -1;
		System.out.println("");
		System.out.println("-------------------------- DATABAZOVY SYSTEM ZAMESTNANCOV --------------------------");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Pre vyber pozadovanej cinnosti zadajte prislusne cislo");
		System.out.println("");
		System.out.println("0 : Ukoncenie programu                 |  6 : Vypis databazy zamestnancov");
		System.out.println("1 : Pridanie noveho zamestnanca        |  7 : Vypis poctu zamestnancov v skupinach");
		System.out.println("2 : Odstranenie zamestnanca            |  8 : Spustenie profesnej vlastnosti");
		System.out.println("3 : Vytvorenie novej spoluprace        |  9 : Vypis statistik");
		System.out.println("4 : Odstranenie spoluprace             | 10 : Ulozenie dat do suboru");
		System.out.println("5 : Vyhladanie zamestnanca podla ID    | 11 : Nacitanie dat zo suboru"); 
		try {
		volba = sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("CHYBA: Rozhranie programu prijima len celociselne hodnoty z uvedeneho rozsahu");
			sc.nextLine();
			continue;
		}
				
			switch(volba) {
				case 1:
					try {
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
						db.novyZamestnanec(meno, priezvisko, rokNarodenia, profesia, strProfesia);
					} else if(profesia == 2) {
						strProfesia = "Bezpecnostny specialista";
						db.novyZamestnanec(meno, priezvisko, rokNarodenia, profesia, strProfesia);
					} else {
						System.out.println("CHYBA : Zadali ste neplatnu hodnotu profesie ; novy zamestnanec nebol pridany");
					}
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Zadali ste neplatnu hodnotu - novy zamestnanec nebol pridany");
						sc.nextLine();
					}
					break;
					
				case 2:
					try {
					System.out.println("---ODSTRANENIE ZAMESTNANCA---");
					System.out.println("ID zamestnanca: ");
					db.odstranZamestnanca(sc.nextInt());
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Hodnota ID musi byt cele cislo");
						sc.nextLine();
					}
					break;
					
				case 3:	
					try {
					System.out.println("---NOVA SPOLUPRACA---");
					System.out.println("Id zamestnanca: ");
					int idZam1 = sc.nextInt();
					System.out.println("Id kolegu: ");
					int idZam2 = sc.nextInt();
					System.out.println("Uroven spoluprace (zla/priemerna/dobra)");
					String uroven = sc.next();
					
					if(uroven.equals("zla") || uroven.equals("priemerna") || uroven.equals("dobra")) {
						db.novaSpolupraca(idZam1, idZam2, uroven);
						break;
					} else {
						System.out.println("CHYBA: Nezadali ste platnu uroven spoluprace");
					}
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Zadali ste neplatnu hodnotu");
						sc.nextLine();
					}
						break;
						
				case 4:
					try {
					System.out.println("---ODSTRANENIE SPOLUPRACE---");
					System.out.println("Id zamestnanca: ");
					int id1 = sc.nextInt();
					System.out.println("Id kolegu: ");
					int id2 = sc.nextInt();
					db.odstranSpolupracu(id1, id2);
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Hodnota ID musi byt cele cislo");
						sc.nextLine();
					}
					break;
					
				case 5:
					try {
					System.out.println("---VYHLADANIE ZAMESTNANCA---");
					System.out.println("ID zamestnanca: ");
					db.vyhladavac(sc.nextInt());
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Hodnota ID musi byt cele cislo");
						sc.nextLine();
					}
					break;
					
				case 6:
					System.out.println("---ZOZNAM ZAMESTNANCOV---");
					db.vypisZamestnancov();
					break;
					
				case 7:
					System.out.println("---POCET ZAMESTNANCOV V SKUPINACH---");
					db.pocetVSkupinach();
					break;
					
				case 8:
					try {
					System.out.println("---SPUSTENIE VLASTNOSTI ZAMESTNANCA---");
					System.out.println("Id zamestnanca: ");
					int idZam = sc.nextInt();
					Zamestnanec zam = db.getZamestnanec(idZam);
					
					if(zam == null) {
						System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
					} else {
						zam.schopnost(db);
					}
					} catch(InputMismatchException e) {
						System.out.println("CHYBA : Hodnota ID musi byt cele cislo");
						sc.nextLine();
					}
					break;
					
				case 9:
					System.out.println("---STATISTIKY---");
					db.statistiky();
					break;
					
				case 10:
					System.out.println("---ULOZENIE DO SUBORU---");
					db.ulozitDoSuboru("databaza.txt");
					break;
					
				case 11:
					System.out.println("---NACITANIE ZO SUBORU---");
					db.nacitatZoSuboru("databaza.txt");
					break;
						
				case 0:
					run=false;
					break;
		
			}
			}
		}
		
		
	}

