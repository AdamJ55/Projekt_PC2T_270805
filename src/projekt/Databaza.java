package projekt;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Databaza {
	private Map<Integer, Zamestnanec> zoznamZamestnancov;
	private int defId = 1;
	
	
	public Databaza() {
		zoznamZamestnancov = new HashMap<>();
	}
	
	//pridanie zamestnanca do databazy
	public void novyZamestnanec(String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia){
		Zamestnanec zam = new Zamestnanec(defId++, meno, priezvisko, rokNarodenia, profesia, strProfesia);
		zoznamZamestnancov.put(zam.getId(), zam);	
	}
	
	//odobratie zamestnanca z databazy
	public void odstranZamestnanca(int id) {
		zoznamZamestnancov.remove(id);
	}
	
	//vypis zamestnancov
	public void vypisZamestnancov() {
		List<Zamestnanec> zoznam = new ArrayList<>(zoznamZamestnancov.values());
		
		for (Zamestnanec zam : zoznam) {
			zam.vypisInfo();
		}
			
	}
	
}
