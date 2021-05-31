package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	
	
	// Lieferanten in ArrayList aendern
	private static void lieferantAufnehmen (String name) {	
		int newID = l.toArray().length + 1;
		l.add(new Lieferant(newID, name));
		dbAddLieferant(name);
		dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	
	private static void lieferantLoeschen (int id) {
		l.remove(id);
		dbDeleteLieferant(id);
		dbResetAutoIncrement("LieferantenID", "lieferanten");
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
		dbResetAutoIncrement("RechnungsID", "lieferantenrechnungen");
	}
	
	public static void dbAddRechnung (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("INSERT INTO lieferantenrechnungen (LieferantenID, Monat, Jahr, Bestellvolumen, Status) VALUES ('"+lieferantenID+"', '"+monat+"', '"+jahr+"', '"+bestellvolumen+"', '"+status+"')");
			// Autoincrement should handle ID
		} catch (Exception e) {e.printStackTrace();}
	}

	// Hilfsfunktionen
	public static int dbGetAutoIncrement (String column, String table) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(" + column + ") FROM " + table);
            if (rs.next()) return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void dbResetAutoIncrement (String column, String table) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
            if (dbGetAutoIncrement(column, table) != -1) stmt.execute("ALTER TABLE " + table + " AUTO_INCREMENT = "+dbGetAutoIncrement(column, table)+"");
			return;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Test
		//dbAddLieferant("Boser");
		System.out.println(dbGetAutoIncrement("LieferantenID", "lieferanten"));
		dbResetAutoIncrement("LieferantenID", "lieferanten");
		
		try {
			DBAccess.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
