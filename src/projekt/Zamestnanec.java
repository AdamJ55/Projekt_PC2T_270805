package projekt;

public class Zamestnanec {
	private int id;
	private String meno;
	private String priezvisko;
	private int rokNarodenia;
	private int profesia;
	private String strProfesia;

	public Zamestnanec(int id, String meno, String priezvisko, int rokNarodenia, int profesia, String strProfesia) {
		this.id = id;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.rokNarodenia = rokNarodenia;
		this.profesia = profesia;
		this.strProfesia = strProfesia;								
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
}