package projekt;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;


public class Databaza {
	private Map<Integer, Zamestnanec> zoznamZamestnancov;
	private int defId = 1;
	
	
	public Databaza() {
		zoznamZamestnancov = new HashMap<>();
	}
	
	//pridanie zamestnanca do databazy
	public void novyZamestnanec(String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia){
		Zamestnanec zam; //upraveny system pre priradenie profesie ako triedy 
		if(profesia == 1) {	
			zam = new DatovyAnalytik(defId++, meno, priezvisko, rokNarodenia, profesia, strProfesia);
		} else {
			zam = new BezpecnostnySpecialista(defId++, meno, priezvisko, rokNarodenia, profesia, strProfesia);
		}	
		zoznamZamestnancov.put(zam.getId(), zam);	
	}
	
	//odobratie zamestnanca z databazy
	public void odstranZamestnanca(int id) {
		zoznamZamestnancov.remove(id);
		for(Zamestnanec zam : zoznamZamestnancov.values()) {
			zam.odstranitSpolupracu(id);
		}
	}
	
	//vypis zamestnancov
	public void vypisZamestnancov() {
		List<Zamestnanec> zoznam = new ArrayList<>(zoznamZamestnancov.values());
		zoznam.sort(Comparator.comparing(Zamestnanec::getPriezvisko)); //abecedne zoradenie podla PRIEZVISKA
		
		for (Zamestnanec zam : zoznam) {
			zam.vypisInfo();
		}
			
	}
	
	public void vyhladavac(int id) {
		Zamestnanec zam = zoznamZamestnancov.get(id);
		if(zam == null) {
			System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
			return;
		}
		zam.vypisInfo();
		System.out.println("------------------------------------");
		System.out.println("Zoznam kolegov a urovni spoluprac: ");
		List<Spolupraca> kol = zam.getSpoluprace();
		if(kol.isEmpty()){
			System.out.println("Zamestnanec nema priradene ziadne spoluprace");
			return;
		}
		for(Spolupraca s : kol) {
			int idKolegu = s.getIdKolegu();
			if(idKolegu == zam.getId()) {
				continue; //oprava vypisu seba sameho v zozname spoluprac
			}
			String uroven = s.getUroven();
			Zamestnanec kolega = zoznamZamestnancov.get(idKolegu);
			
			if(kolega != null) {
				System.out.println(kolega.getPriezvisko() + ", " + kolega.getMeno() + " | ID: " + kolega.getId() + " | Uroven spoluprace: " + uroven);
			}
		}
	}
	
	
	
	public void novaSpolupraca(int idZam1, int idZam2, String uroven) { //OPRAVENE
		Zamestnanec Zam1 = zoznamZamestnancov.get(idZam1);
		Zamestnanec Zam2 = zoznamZamestnancov.get(idZam2);
		if(Zam1 == null || Zam2 == null) {
			System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
			return;
		}
		
		//osetrenie duplicitnej spoluprace ; kostrbate ale funguje
		boolean ex1 = false; //musia byt 2 prem. "existuje", inak bude nespravne priradovat
		boolean ex2 = false;
		for(Spolupraca sp : Zam1.getSpoluprace()) {
			if(sp.getIdKolegu() == idZam2) {
				sp.setUroven(uroven);
				System.out.println("UPOZORNENIE: Doslo k prepisu uz existujucej spoluprace");
				ex1 = true;
				break;
			}
		}
		if(!ex1) {
			Zam1.novaSpolupraca(new Spolupraca(idZam2, uroven));
		}
		
		for(Spolupraca sp : Zam2.getSpoluprace()) {
			if(sp.getIdKolegu() == idZam1) {
				sp.setUroven(uroven);
				ex2 = true;
				break;
			}
		}
		if(!ex2) {
			Zam2.novaSpolupraca(new Spolupraca(idZam1, uroven));
		}
	}
	
	//metodu triedenia do skupin treba potom aplikovat aj na triedenie zoznamu
	public void pocetVSkupinach() {
		int dAnalytici = 0;
		int bSpecialisti = 0;
		
		for(Zamestnanec zam : zoznamZamestnancov.values()) {
			if(zam instanceof DatovyAnalytik) {
				dAnalytici++;
			} else if(zam instanceof BezpecnostnySpecialista) {
				bSpecialisti++;
			}
		}
		System.out.println("Datovi analytici: " + dAnalytici);
		System.out.println("Bezpecnostni specialisti: " + bSpecialisti);
	}
}



