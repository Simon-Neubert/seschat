package objects;

public class Lieferant {

	private int lieferantenID;
	private String name;
	
	public Lieferant(int lieferantenID, String name) {
		this.lieferantenID = lieferantenID;
		this.name = name;
	}

	public int getLieferantenID() {
		return lieferantenID;
	}

	public void setLieferantenID(int lieferantenID) {
		this.lieferantenID = lieferantenID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
