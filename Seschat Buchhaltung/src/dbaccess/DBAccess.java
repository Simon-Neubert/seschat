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
	
	public static void checkConnection () {
		if (conn == null) accessDB();
	}
	
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

}