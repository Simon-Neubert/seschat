package objects;

public abstract class Rechnung {

	private int rechnungsID;
	private int monat, jahr;
	private double summe;
	private boolean status;
	
	public Rechnung(int rechnungsID, int monat, int jahr, double summe, boolean status) {
		this.rechnungsID = rechnungsID;
		this.monat = monat;
		this.summe = summe;
		this.status = status;
		this.jahr = jahr;
	}
	
	// Getters & Setters
	public int getRechnungsID() {
		return rechnungsID;
	}

	public void setRechnungsID(int rechnungsID) {
		this.rechnungsID = rechnungsID;
	}

	public int getMonat() {
		return monat;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

	public double getSumme() {
		return summe;
	}

	public void setSumme(double summe) {
		this.summe = summe;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
}
