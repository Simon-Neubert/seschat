package objects;

public class Lieferantenrechnung extends Rechnung{

	private int lieferantenID;
	
	public Lieferantenrechnung(int rechnungsID, int monat, int jahr, double summe, boolean status, int lieferantenID) {
		super(rechnungsID, monat, jahr, summe, status);
		this.lieferantenID = lieferantenID;
	}

	public int getLieferantenID() {
		return lieferantenID;
	}
	public void setLieferantenID(int lieferantenID) {
		this.lieferantenID = lieferantenID;
	}

	
	
}
