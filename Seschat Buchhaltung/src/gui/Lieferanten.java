package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JPanel;

import objects.*;
import dbaccess.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTable;

public class Lieferanten extends JPanel{

	// Auslesen DB
	static ArrayList <objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	static ArrayList <objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
	static String lieferant = "";
	
	public Lieferanten () {
		
		setLayout(null);
		setBounds(2000, 2000, 2000, 2000);

		JLabel bestehendLabel = new JLabel("Bestehender Lieferant:");
		bestehendLabel.setBounds(101, 336, 277, 26);
		bestehendLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		add(bestehendLabel);
		
		JLabel labelBestellvolumen = new JLabel("Neuer Lieferant:");
		labelBestellvolumen.setFont(new Font("Serif", Font.PLAIN, 25));
		labelBestellvolumen.setBounds(101, 83, 395, 32);
		add(labelBestellvolumen);
		
		JLabel inputLabel = new JLabel("");
		inputLabel.setForeground(Color.RED);
		inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel.setBounds(101, 273, 573, 26);
		add(inputLabel);
		
		nameNeuField = new JTextField();
		nameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nameNeuField.setText("   Bitte Name eingeben...");
		nameNeuField.setFont(new Font("Sans", Font.PLAIN, 14));
		nameNeuField.setBounds(101, 188, 219, 53);
		nameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		add(nameNeuField);
		nameNeuField.setColumns(10);
		
		generiertField = new JTextField();
		generiertField.setText("  Generierte ID erscheint hier");
		generiertField.setEditable(false);
		generiertField.setHorizontalAlignment(SwingConstants.CENTER);
		generiertField.setFont(new Font("Dialog", Font.PLAIN, 14));
		generiertField.setColumns(10);
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		generiertField.setBounds(455, 188, 219, 53);
		add(generiertField);
		nameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {nameNeuField.setText("");}
			public void focusLost(FocusEvent e) {}
		});
		
		JButton speichernButton = new JButton("Speichern");
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.setBounds(852, 191, 170, 50);
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String nameInput = nameNeuField.getText();
				if (nameInput.equals("") || nameInput.equals("   Bitte Name eingeben...")) {
					inputLabel.setForeground(Color.RED);
					inputLabel.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				for (int i = 0; i < l.toArray().length; i++)
					if (l.get(i).getName().equals(nameInput)) {
						inputLabel.setForeground(Color.RED);
						inputLabel.setText("Lieferant bereits vorhanden! (ID: " + l.get(i).getLieferantenID() + ")");
						return;
					}
				
				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
				inputLabel.setText("");
				inputLabel.setForeground(Color.BLACK);
				lieferantAufnehmen(nameInput);
				inputLabel.setText(nameInput + " erfolgreich aufgenommen.");
				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
			}
		});
		add(speichernButton);
		
	}
	
	private JTextField idField;
	private JTextField nameField;
	private JTable lieferantenTable;
	private JTextField generierteIDField;
	private JTextField neuNameField;
	private JTextField nameNeuField;
	private JTextField generiertField;
	
	// Lieferanten in ArrayList aendern
	private static void lieferantAufnehmen (String name) {	
		int newID = l.toArray().length + 1;
		l.add(new Lieferant(newID, name));
		dbAddLieferant(name);
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	
	private static void lieferantLoeschen (int id) {
		l.remove(id);
		dbDeleteLieferant(id);
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	
	private static void lieferantBearbeiten (int id, String name) {
		l.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> x.setName(name));
		dbChangeLieferant(id, name);
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	
	// Lieferanten in DB aendern
	public static void dbAddLieferant (String name) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute(" INSERT INTO lieferanten (Name) VALUES ('"+name+"')");
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
			DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
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
		DBAccess.dbResetAutoIncrement("RechnungsID", "lieferantenrechnungen");
	}
	
	public static void dbAddRechnung (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("INSERT INTO lieferantenrechnungen (LieferantenID, Monat, Jahr, Bestellvolumen, Status) VALUES ('"+lieferantenID+"', '"+monat+"', '"+jahr+"', '"+bestellvolumen+"', '"+status+"')");
			// Autoincrement should handle ID
		} catch (Exception e) {e.printStackTrace();}
	}

	
	public static void main(String[] args) {
		// Test
		//dbAddLieferant("Boser");
		System.out.println(DBAccess.dbGetAutoIncrement("LieferantenID", "lieferanten"));
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
		
		try {
			DBAccess.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}