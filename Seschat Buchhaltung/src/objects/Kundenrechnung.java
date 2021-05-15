package objects;

public class Kundenrechnung extends Rechnung{

	private int kundenID;
	
	public Kundenrechnung(int rechnungsID, int monat, int jahr, double summe, boolean status, int kundenID) {
		super(rechnungsID, monat, jahr, summe, status);
		this.kundenID = kundenID;
	}

	public int getKundenID() {
		return kundenID;
	}
	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}
	
}
