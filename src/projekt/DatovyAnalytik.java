package projekt;

public class DatovyAnalytik extends Zamestnanec {
	
	public DatovyAnalytik(int id, String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia) {
		super(id, meno, priezvisko, rokNarodenia, profesia, strProfesia);
	}
	@Override
	public void schopnost(Databaza db) { //pocita sameho seba ako kolegu - OPRAVENE
		int max = 0;
		Zamestnanec najviac = null;
		boolean zhoda = false; 
		for(Zamestnanec druhy : db.getZoznamZamestnancov().values()) {
			if(druhy.getId() == this.getId()) {
				continue;
			}
			int pocet = 0;
				for(Spolupraca s1 : this.getSpoluprace()) {
					for(Spolupraca s2 : druhy.getSpoluprace()) {
						if(s1.getIdKolegu() == s2.getIdKolegu()) {
							pocet++;
						}
					}
				}
				if(pocet > max) {
					max=pocet;
					najviac=druhy;
					zhoda = false;
				} else if(pocet == max && max !=0) {
					zhoda = true;
				}
			}
		if(this.getSpoluprace().isEmpty()) {
			System.out.println("Dany analytik nema priradene ziadne spoluprace");
		} else if(najviac != null) {
			if(zhoda) {
				System.out.println("Pocet totoznych spoluprac je zhodny s viacerymi spolupracovnikmi:");
				for(Zamestnanec druhy : db.getZoznamZamestnancov().values()) {
					if(druhy.getId() == this.getId()) {
						continue;
					}
					int pocet = 0;
						for(Spolupraca s1 : this.getSpoluprace()) {
							for(Spolupraca s2 : druhy.getSpoluprace()) {
								if(s1.getIdKolegu() == s2.getIdKolegu()) {
									pocet++;
								}
							}
						}
						if(pocet == max) {
							System.out.println("ID: " + druhy.getId() + " (" + druhy.getPriezvisko() + ", " + druhy.getMeno() + " | " + druhy.getProfesia() + ")");
						}
					
				}
			} else {
				System.out.println("Dany analytik ma najviac totoznych spolupracovnikov so zamestnancom c. " + najviac.getId());
				System.out.println("(" + najviac.getPriezvisko() + ", " + najviac.getMeno() + " | " + najviac.getProfesia() + ")");
			}
			System.out.println("Pocet totoznych spolopracovnikov: " + max);
		} else {
			System.out.println("Dany analytik nema so ziadnym pracovnikom zhodny pocet totoznych spoluprac");
		}
	}

}
