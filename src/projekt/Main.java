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
		System.out.println("3 : Vyhladanie zamestnanca podla ID");
		System.out.println("4 : Vypis databazy zamestnancov");
		System.out.println("5 : Vypis poctu zamestnancov v skupinach"); 
		System.out.println("6 : Vytvorenie novej spoluprace");
		System.out.println("7 : Spustenie profesnej vlastnosti");
		System.out.println("8 : Vypis statistik");
		
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
					db.odstranZamestnanca(sc.nextInt());
					System.out.println("");
					break;
					
				case 3:
					System.out.println("---VYHLADANIE ZAMESTNANCA---");
					System.out.println("ID zamestnanca: ");
					db.vyhladavac(sc.nextInt());
					System.out.println("");
					break;
						
				case 4:
					System.out.println("---ZOZNAM ZAMESTNANCOV---");
					db.vypisZamestnancov();
					System.out.println("");
					break;
				
				case 5:
					System.out.println("---POCET ZAMESTNANCOV V SKUPINACH---");
					db.pocetVSkupinach();
					System.out.println("");
					break;
					
				case 6:
					
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
					System.out.println("");
						break;
				case 7:
					System.out.println("---SPUSTENIE VLASTNOSTI ZAMESTNANCA---");
					System.out.println("Id zamestnanca: ");
					int idZam = sc.nextInt();
					Zamestnanec zam = db.getZamestnanec(idZam);
					
					if(zam == null) {
						System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
					} else {
						zam.schopnost(db);
					}
					System.out.println("");
						
					break;
				case 8:
					System.out.println("---STATISTIKY---");
					db.statistiky();
					System.out.println("");
					
					break;
				case 9:
					break;
				case 0:
					run=false;
					break;
		
			}
		
		}
	}
}
