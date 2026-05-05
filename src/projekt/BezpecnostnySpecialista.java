package projekt;

import java.util.List;

public class BezpecnostnySpecialista extends Zamestnanec{
	public BezpecnostnySpecialista(int id, String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia) {
		super(id, meno, priezvisko, rokNarodenia, profesia, strProfesia);
	}
	
	@Override
	public void schopnost(Databaza db) {
		List<Spolupraca> spoluprace = this.getSpoluprace();
		if(spoluprace.isEmpty()) {
			System.out.println("CHYBA: Tento bezp. specialista nema priradene ziadne spoluprace");
			return;
		}
		
		double sucet = 0;
		for(Spolupraca sp : spoluprace) {
			String ur = sp.getUroven();
			if(ur.equals("zla")) { 
				sucet += 0;
			} else if(ur.equals("priemerna")) {
				sucet += 0.5;
			} else if(ur.equals("dobra")) {
				sucet += 1;
			}
		}
		
		double kv = (sucet/spoluprace.size())*100;
		System.out.println("Kvalita spolupráce: " + Math.round(kv) + " %"); //Zaokruhlenie pre lepsiu prehladnost
		if(100>= kv & kv >= 80) {
			System.out.println("Rizikové skóre (0-3): 0 - Spolupraca je na vybornej urovni");	
		} else if(80 > kv & kv >= 50) {
			System.out.println("Rizikové skóre (0-3): 1 - Spolupraca je na adekvatnej urovni");	
		} else if(50 > kv & kv >= 35) {
			System.out.println("Rizikové skóre (0-3): 2 - Spolupraca je na zlej urovni");	
		} else if(35 > kv & kv >= 0) {
			System.out.println("Rizikové skóre (0-3): 3 - SPOLUPRACA JE KRITICKA ; ODPORUCAME VYMENU SPOLUPRACOVNIKOV");	
		} 
	}
}
