package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import objects.*;
import dbaccess.*;

public class Lieferanten {

	// Auslesen DB
	static ArrayList <objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	static ArrayList <objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
	static List<Integer> changedIndex = new ArrayList<Integer>();	
	
	// Checks hinzufuegen: Lieferant schon/noch vorhanden?
	
	// Lieferanten in ArrayList aendern
	private static void lieferantAufnehmen (String name) {	
		int newID = l.toArray().length + 1;
		l.add(new Lieferant(newID, name));
		dbAddLieferant(name);
	}
	
	private static void lieferantLoeschen (int id) {
		l.remove(id);
		dbDeleteLieferant(id);
	}
	
	private static void lieferantBearbeiten (int id, String name) {
		
		l.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> x.setName(name));
		dbChangeLieferant(id, name);
		
	}
	
	// Lieferanten in DB aendern
	public static void dbAddLieferant (String name) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute(" INSERT INTO lieferanten (Name) VALUES ('"+name+"')");
			// Autoincrement should handle ID
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void dbDeleteLieferant (int id) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("DELETE FROM lieferanten WHERE LieferantenID = '"+id+"'");
	        /* Backup:
			PreparedStatement ps = DBAccess.conn.prepareStatement("DELETE FROM lieferanten WHERE LieferantenID = ?");
	        ps.setInt(?);
	        ps.executeUpdate();
	        */
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void dbChangeLieferant (int id, String name) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("UPDATE lieferanten SET name = '"+name+"' WHERE LieferantenID = '"+id+"'");
		} catch (Exception e) {e.printStackTrace();}
	}

	// Neue Rechnung anlegen
	private static void rechnungAufnehmen (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		int newID = lr.toArray().length + 1;
		lr.add(new Lieferantenrechnung(newID, monat, jahr, bestellvolumen, status, lieferantenID));
		dbAddRechnung(lieferantenID, monat, jahr, bestellvolumen, status);
	}
	
	public static void dbAddRechnung (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute(" INSERT INTO lieferantenrechnungen (LieferantenID, Monat, Jahr, Bestellvolumen, Status) VALUES ('"+lieferantenID+"', '"+monat+"', '"+jahr+"', '"+bestellvolumen+"', '"+status+"')");
			// Autoincrement should handle ID
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		// Test
		lieferantAufnehmen ("Acer");
	}
	
}
