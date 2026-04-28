package projekt;

import java.util.List;
import java.util.ArrayList;

public class Zamestnanec {
	private int id;
	private String meno;
	private String priezvisko;
	private int rokNarodenia;
	private int profesia;
	private String strProfesia;
	private List<Spolupraca> spoluprace;
	

	public Zamestnanec(int id, String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia) {
		this.id = id;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.rokNarodenia = rokNarodenia;
		this.profesia = profesia;
		this.strProfesia = strProfesia;	
		spoluprace = new ArrayList<>();
	}
	
	public void vypisInfo() {
		System.out.println(priezvisko +", " + meno + " | nar.: " + rokNarodenia + " | ID: " + id + " | Profesia: " + strProfesia);
	}
	
	public int getId() {
		return id;
	}
	
	public String getMeno() {
		return meno;
	}
	
	public String getPriezvisko() {
		return priezvisko;
	}
	
	public int getProfesia() {
		return profesia;
	}
	
	public List<Spolupraca> getSpoluprace() {
		return spoluprace;
	}
	
	public void novaSpolupraca(Spolupraca sp) {
		spoluprace.add(sp);
	}
	
	public void odstranitSpolupracu(int idKolegu) {
		for(int i = 0; i < spoluprace.size(); i++) {
			if(spoluprace.get(i).getIdKolegu() == idKolegu) {
				spoluprace.remove(i);
				i--;
			}
		}
	}
}