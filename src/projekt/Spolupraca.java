package projekt;

import java.util.List;

public class Spolupraca {
	private int idKolegu;
	private String uroven;
	
	public Spolupraca(int idKolegu, String uroven) {
		this.idKolegu = idKolegu;
		this.uroven = uroven;
	}
	
	public int getIdKolegu() {
		return idKolegu;
	}
	
	public String getUroven() {
		return uroven;
	}
	
	public void setUroven(String uroven) {
		this.uroven=uroven;
	}
	
}

