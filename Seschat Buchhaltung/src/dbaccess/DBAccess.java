package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.*;
import java.util.ArrayList;

public class DBAccess {

	public static Connection conn = null;

	// Read from DB and add data to objects
	private static ArrayList<objects.Kunde> k = dbaccess.DBAccess.createKunden();
	private static ArrayList<objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
	private static ArrayList<objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	private static ArrayList<objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
		
	
	// Auxiliary functions
	
	// Get autoincrement index
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

	// Reset autoincrement
	public static void dbResetAutoIncrement (String column, String table) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
            if (dbGetAutoIncrement(column, table) != -1) stmt.execute("ALTER TABLE " + table + " AUTO_INCREMENT = " + dbGetAutoIncrement(column, table)+"");
			return;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Check if Login was successful
	public static boolean checkLogin (String username, String password) {

		checkConnection();
		
        try {
            Statement stmt = conn.createStatement();;
            ResultSet rs;
            
            rs = stmt.executeQuery("SELECT username, password FROM benutzer");

            while (rs.next()) {
            	if (rs.getString("username").equals(username) && rs.getString("password").equals(password))     
            		return true;
            }
        } catch (Exception e) {e.printStackTrace();}
        return false;
    }
	
	
	// Manage Connection to DB
	
	// Establish connection to DB
	public static void accessDB () {
    	
        String url = "jdbc:mysql://3.125.60.55:3306/";
        String dbName = "db3";
        String driver = "com.mysql.cj.jdbc.Driver";
        String dbUserName = "db3";
        String dbPassword = "!db3.winf?";
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, dbUserName, dbPassword);
        } catch (Exception e) {e.printStackTrace();}
	}	
	
	// Check if connection is live already, establish if not
	public static void checkConnection () {
		if (conn == null) accessDB();
	}
	
	// Close connection to DB
	public static void closeConnection () {
		try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	// Create Object arrays
	public static ArrayList<Kunde> createKunden () {
		
		checkConnection();
		
		try {
			Statement stmt = conn.createStatement();;
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM kunden");
           
            ArrayList<Kunde> k = new ArrayList<Kunde> ();
  
            while (rs.next()) k.add(new Kunde (rs.getInt("KundenID"), rs.getString("Vorname"), rs.getString("Nachname"), rs.getInt("PLZ")));
            
            return k;
            
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static ArrayList<Lieferant> createLieferanten () {
		
		checkConnection();
		
		try {
			Statement stmt = conn.createStatement();;
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM lieferanten");
            
            ArrayList<Lieferant> l = new ArrayList<Lieferant> ();
            
            while (rs.next()) l.add(new objects.Lieferant(rs.getInt("LieferantenID"), rs.getString("Name")));
            return l;
            
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static ArrayList<Kundenrechnung> createKundenrechnungen () {
		
		checkConnection();
		int counter = 1;
		try {
			Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM kundenrechnungen");
            
            ArrayList<Kundenrechnung> kr = new ArrayList<Kundenrechnung> ();
          
            while (rs.next()) kr.add(new objects.Kundenrechnung (rs.getInt("RechnungsID"), rs.getInt("Monat"), rs.getInt("Jahr"), rs.getDouble("Summe"),rs.getBoolean("Status"), rs.getInt("KundenID")));
            return kr;
            
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static ArrayList<Lieferantenrechnung> createLieferantenrechnungen () {
		
		checkConnection();
		int counter = 1;
		try {
			Statement stmt = conn.createStatement();;
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM lieferantenrechnungen");
            
            ArrayList<Lieferantenrechnung> lr = new ArrayList<Lieferantenrechnung> ();
            
            while (rs.next()) lr.add(new objects.Lieferantenrechnung(rs.getInt("RechnungsID"), rs.getInt("Monat"), rs.getInt("Jahr"), rs.getDouble("Bestellvolumen"),rs.getBoolean("Status"), rs.getInt("LieferantenID")));
            return lr;
            
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}

	
	// Getters
	public static ArrayList<objects.Kunde> getK() {
		return k;
	}
	
	public static ArrayList<objects.Lieferant> getL() {
		return l;
	}
	
	public static ArrayList<objects.Kundenrechnung> getKr() {
		return kr;
	}
	
	public static ArrayList<objects.Lieferantenrechnung> getLr() {
		return lr;
	}

}