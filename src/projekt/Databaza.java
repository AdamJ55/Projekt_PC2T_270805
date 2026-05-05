package projekt;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Databaza {
	private Map<Integer, Zamestnanec> zoznamZamestnancov;
	private int defId = 1;
	
	public Zamestnanec getZamestnanec(int id) {
		return getZoznamZamestnancov().get(id);
	}
	public Databaza() {
		zoznamZamestnancov = new HashMap<>();
	}
	
	public Map<Integer, Zamestnanec> getZoznamZamestnancov() {
		return zoznamZamestnancov;
	}
	
	public void novyZamestnanec(String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia){
		Zamestnanec zam; 
		if(profesia == 1) {	
			zam = new DatovyAnalytik(defId++, meno, priezvisko, rokNarodenia, profesia, strProfesia);
		} else {
			zam = new BezpecnostnySpecialista(defId++, meno, priezvisko, rokNarodenia, profesia, strProfesia);
		}	
		zoznamZamestnancov.put(zam.getId(), zam);	
	}
	
	
	public void odstranZamestnanca(int id) {
		zoznamZamestnancov.remove(id);
		for(Zamestnanec zam : zoznamZamestnancov.values()) {
			zam.odstranitSpolupracu(id);
		}
	}
	
	public void vypisZamestnancov() {
		List<Zamestnanec> zoznam = new ArrayList<>(zoznamZamestnancov.values());
		zoznam.sort(Comparator.comparing(Zamestnanec::getPriezvisko));
		System.out.println("-----------------|Datovi analytici|-----------------");
		for (Zamestnanec zam : zoznam) {
			if(zam instanceof BezpecnostnySpecialista) {
				continue;
			} else if(zam instanceof DatovyAnalytik) {
				zam.vypisInfo();	
				}	
		}
		System.out.println("-------------|Bezpecnostni specialisti|-------------");
		for (Zamestnanec zam : zoznam) {
		if(zam instanceof BezpecnostnySpecialista) {
			zam.vypisInfo();
			}
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
				continue;
			}
			String uroven = s.getUroven();
			Zamestnanec kolega = zoznamZamestnancov.get(idKolegu);
			
			if(kolega != null) {
				System.out.println(kolega.getPriezvisko() + ", " + kolega.getMeno() + " | ID: " + kolega.getId() + " | Uroven spoluprace: " + uroven);
			}
		}
	}
	
	public void novaSpolupraca(int idZam1, int idZam2, String uroven) {
		Zamestnanec Zam1 = zoznamZamestnancov.get(idZam1);
		Zamestnanec Zam2 = zoznamZamestnancov.get(idZam2);
		if(Zam1 == null || Zam2 == null) {
			System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
			return;
		}
		
		boolean ex1 = false;
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
		System.out.println("Stav spoluprace medzi zamestnancami: " + uroven);
	}
	
	public void odstranSpolupracu(int idZam1, int idZam2) {
		Zamestnanec Zam1 = zoznamZamestnancov.get(idZam1);
		Zamestnanec Zam2 = zoznamZamestnancov.get(idZam2);
		if(Zam1 == null || Zam2 == null) {
			System.out.println("CHYBA: Zamestnanec s ID ktore ste zadali neexistuje");
			return;
		}
		
		for(int i = 0; i < Zam1.getSpoluprace().size(); i++) {
			if(Zam1.getSpoluprace().get(i).getIdKolegu() == idZam2) {
				Zam1.getSpoluprace().remove(i);
			}
		}
		for(int i = 0; i < Zam2.getSpoluprace().size(); i++) {
			if(Zam2.getSpoluprace().get(i).getIdKolegu() == idZam1) {
				Zam2.getSpoluprace().remove(i);
			}
		}
		System.out.println("Spolupraca medzi zamesntancami c. " + idZam1 + " a " + idZam2 + " bola odstranena");
	}

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
		System.out.println("Pocet zamestnancov firmy: " + (dAnalytici + bSpecialisti));
		System.out.println("-> Datovi analytici: " + dAnalytici);
		System.out.println("-> Bezpecnostni specialisti: " + bSpecialisti);
	}
	
	public void statistiky() {
		int zla = 0;
		int priemerna = 0;
		int dobra = 0;
		Zamestnanec najviac = null;
		int maxVazieb = 0;
		
		for(Zamestnanec zam : zoznamZamestnancov.values()) {
			int pocet = zam.getSpoluprace().size();
			if(pocet > maxVazieb) {
				maxVazieb = pocet;
				najviac = zam;
			} else if(pocet == maxVazieb) {
				najviac = null;
			}
			for(Spolupraca sp : zam.getSpoluprace()) {
				String uroven = sp.getUroven();
				if(uroven.equals("zla")) {
					zla++;
				} else if(uroven.equals("priemerna")) {
					priemerna++;
				} else if(uroven.equals("dobra")) {
					dobra++;
				}
			}	
		}
		
		if(najviac != null) {
			System.out.println("Zamestnanec s najvacsim poctom spoluprac: ");
			najviac.vypisInfo();
			System.out.println("Pocet spoluprac: " + maxVazieb);
		} else {
			System.out.println("Ziaden zo zamestnancov nema v pocte spoluprac dominantne postavenie");
		}
		
		if(zla > priemerna && zla > dobra) {
			System.out.println("Prevazujuca kvalita spoluprace: ZLA");
		} else if(priemerna > zla && priemerna > dobra) {
			System.out.println("Prevazujuca kvalita spoluprace: PRIEMERNA");
		} else if(dobra > zla && dobra > priemerna) {
			System.out.println("Prevazujuca kvalita spoluprace: DOBRA");
		} else if(zla > dobra && zla == priemerna) {
			System.out.println("Prevazujuce kvality spoluprace: PRIEMERNA, ZLA");
		} else if(dobra > zla && dobra == priemerna) {
			System.out.println("Prevazujuce kvality spoluprace: DOBRA, PRIEMERNA");
		} else if(dobra > priemerna && dobra == zla) {
			System.out.println("Prevazujuce kvality spoluprace: DOBRA, ZLA");
		} else {
			System.out.println("Kvalita spoluprace je vo vyrovnanom stave");
		}
	}
	
	public boolean ulozitDoSuboru(String nazovSuboru) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nazovSuboru));
			bw.write(String.valueOf(zoznamZamestnancov.size()));
			bw.newLine();
			for(Zamestnanec zam : zoznamZamestnancov.values()) {
				bw.write(zam.getId() + " " + zam.getMeno() + " " + zam.getPriezvisko() + " " + zam.getRok() + " " + zam.getProfesia());
				bw.newLine();
			}
			bw.write("Spoluprace:");
			bw.newLine();
			for(Zamestnanec zam : zoznamZamestnancov.values()) {
				for(Spolupraca sp : zam.getSpoluprace()) {
					if(zam.getId() < sp.getIdKolegu()) {
						bw.write(zam.getId() + " " + sp.getIdKolegu() + " " + sp.getUroven());
						bw.newLine();
					}	
				}
			}
			bw.close();
			System.out.println("Databaza bola uspesne ulozena do suboru " + nazovSuboru);
			return true;
		}
		catch(IOException e) {
			System.out.println("CHYBA: Databazu sa nepodarilo ulozit do suboru");
			return false;
		}	
	}
	
	public boolean nacitatZoSuboru(String nazovSuboru) {
		  try {
			  Scanner sc = new Scanner(new File(nazovSuboru));
			  int pocet = sc.nextInt();
			  for(int i = 0 ; i < pocet ; i++) {
				  int id = sc.nextInt();
				  String meno = sc.next();
				  String priezvisko = sc.next();
				  int rokNarodenia = sc.nextInt();
				  int profesia = sc.nextInt();
				  String strProfesia;
				  if(profesia == 1) {
					  strProfesia = "Datovy analytik";
				  } else {
					  strProfesia = "Bezpecnostny specialista";
				  }

				  Zamestnanec zam;
				  if(profesia == 1) {	
						zam = new DatovyAnalytik(id, meno, priezvisko, rokNarodenia, profesia, strProfesia);
					} else {
						zam = new BezpecnostnySpecialista(id, meno, priezvisko, rokNarodenia, profesia, strProfesia);
					}
				  zoznamZamestnancov.put(id, zam);
				  if(id >= defId) {
					  defId = id + 1;
				  }
			  }
			  sc.next();
			  while(sc.hasNext()) {
				  int id1 = sc.nextInt();
				  int id2 = sc.nextInt();
				  String uroven = sc.next();
				  novaSpolupraca(id1, id2, uroven);
			  }
			  sc.close();
			  System.out.println("Databaza bola uspesne nacitana zo suboru " + nazovSuboru);
			  return true;
		  }
		  catch(IOException e) {
			  System.out.println("CHYBA: Databazu sa nepodarilo nacitat zo suboru");
			  return false;
		  }
	}
}
