package objects;

public class Kunde {

	private int kundenID;
	private String vorname;
	private String nachname;
	private int plz;
	
	public Kunde(int kundenID, String vorname, String nachname, int plz) {
		this.kundenID = kundenID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.plz = plz;
	}
	
	// Getters & Setters
	public int getKundenID() {
		return kundenID;
	}
	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Kunde: " + kundenID + ", Vorname: " + vorname + ", Nachname: " + nachname + ", PLZ: " + plz + "\n";
	}
	
}
