package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
import javax.swing.table.DefaultTableModel;
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
		labelBestellvolumen.setBounds(101, 103, 395, 32);
		add(labelBestellvolumen);
		
		JLabel inputLabel = new JLabel("");
		inputLabel.setForeground(Color.RED);
		inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel.setBounds(1256, 202, 573, 26);
		add(inputLabel);
		
		nameNeuField = new JTextField();
		nameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nameNeuField.setText("   Bitte Name eingeben...");
		nameNeuField.setFont(new Font("Sans", Font.PLAIN, 14));
		nameNeuField.setBounds(101, 188, 219, 53);
		nameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		add(nameNeuField);
		nameNeuField.setColumns(10);
		nameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameNeuField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		
		generiertField = new JTextField();
		generiertField.setText(" Generierte ID erscheint hier");
		generiertField.setEditable(false);
		generiertField.setHorizontalAlignment(SwingConstants.CENTER);
		generiertField.setFont(new Font("Dialog", Font.PLAIN, 14));
		generiertField.setColumns(10);
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		generiertField.setBounds(518, 188, 219, 53);
		add(generiertField);
		
		JButton speichernButton = new JButton("Speichern");
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.setForeground(new Color(30, 144, 255));
		speichernButton.setBounds(936, 191, 170, 50);
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String nameInput = nameNeuField.getText();
				nameNeuField.setText("   Bitte Name eingeben...");
				
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
				
				generiertField.setText(String.valueOf(l.toArray().length));
				nameNeuField.setText("   Bitte Name eingeben...");
				return;
			}
		});
		add(speichernButton);
		
		nameSuchenField = new JTextField();
		nameSuchenField.setText("   Bitte Name eingeben...");
		nameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		nameSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameSuchenField.setColumns(10);
		nameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		nameSuchenField.setBounds(101, 417, 219, 53);
		add(nameSuchenField);
		nameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		
		idSuchenField = new JTextField();
		idSuchenField.setText("   Bitte ID eingeben...");
		idSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		idSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		idSuchenField.setColumns(10);
		idSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		idSuchenField.setBounds(518, 417, 219, 53);
		add(idSuchenField);
		idSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		JLabel inputLabel2 = new JLabel("");
		inputLabel2.setForeground(Color.RED);
		inputLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel2.setBounds(1256, 435, 573, 26);
		add(inputLabel2);
		
		table = new JTable();
		table.setBounds(101, 535, 1310, 420);
		table.setEnabled(false);
		table.setBorder(new LineBorder(Color.BLACK, 2));
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		add(table);
		
		JButton suchenButton = new JButton("Suchen");
		suchenButton.setForeground(new Color(30, 144, 255));
		suchenButton.setBackground(new Color(30, 144, 255));
		suchenButton.setBounds(936, 420, 170, 50);
		add(suchenButton);
		suchenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String nameInput = nameSuchenField.getText();
				String idInput = idSuchenField.getText();
				nameSuchenField.setText("   Bitte Name eingeben...");
				idSuchenField.setText("   Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") && !idInput.equals("   Bitte ID eingeben...")) {
					inputLabel2.setForeground(Color.RED);
					inputLabel2.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				// Keine Eingabe
				if ((idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) && (nameInput.equals("   Bitte Name eingeben...") || nameInput.equals(""))) {
					
					ArrayList<String> list = tableOfAll();
					String [][] array = new String [(list.toArray().length)/2][2];
					int counter = 0;
					
					for (int i = 0; i < (list.toArray().length)/2; i++) 
						for (int j = 0; j < 2; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] {"Lieferanten-ID", "Name"});
					table.setModel(tableModel);
					return;
				}
				
				// ID und Name eingegeben
				if (idInput.matches("[0-9]+") && !nameInput.equals("") && !nameInput.equals("   Bitte Name eingeben...")) {
					
					ArrayList<String> list = tableByNameAndID(Integer.parseInt(idInput), nameInput);
					String [][] array = new String [(list.toArray().length)/2][2];
					int counter = 0;
					
					for (int i = 0; i < (list.toArray().length)/2; i++) 
						for (int j = 0; j < 2; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] {"Lieferanten-ID", "Name"});
					table.setModel(tableModel);
					return;
				}
				
				// Nur ID
				if (idInput.matches("[0-9]+")) {
					ArrayList<String> list = tableByID(Integer.parseInt(idInput));
					String [][] array = new String [(list.toArray().length)/2][2];
					int counter = 0;
					
					for (int i = 0; i < (list.toArray().length)/2; i++) 
						for (int j = 0; j < 2; j++) {
							array[i][j] = "  " + list.get(counter);
							System.out.println(array[i][j]);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] {"Lieferanten-ID", "Name"});
					table.setModel(tableModel);
					return;
				}
				
				// Nur Name
				if (nameInput.length() != 0) {
					ArrayList<String> list = tableByName(nameInput);
					String [][] array = new String [(list.toArray().length)/2][2];
					int counter = 0;
					
					for (int i = 0; i < (list.toArray().length)/2; i++) 
						for (int j = 0; j < 2; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] {"Lieferanten-ID", "Name"});
					table.setModel(tableModel);
					
				}
				
				return;
			}
		});
		
	}
	
	private JTextField idField;
	private JTextField nameField;
	private JTable lieferantenTable;
	private JTextField generierteIDField;
	private JTextField neuNameField;
	private JTextField nameNeuField;
	private JTextField generiertField;
	private JTextField nameSuchenField;
	private JTextField idSuchenField;
	private JTable table;
	
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

	// Tabelle erstellen
	private static ArrayList<String> tableByName (String name) {

		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Lieferanten-ID", "Name"};
		table.addAll(Arrays.asList(spalten));
		
		l.stream().filter(x -> x.getName().contains(name)).forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}

	private static ArrayList<String> tableByID (int id) {

		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Lieferanten-ID", "Name"};
		table.addAll(Arrays.asList(spalten));
		
		l.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}

	private static ArrayList<String> tableByNameAndID (int id, String name) {

		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Lieferanten-ID", "Name"};
		table.addAll(Arrays.asList(spalten));
		
		l.stream().filter(x -> x.getName().contains(name) && x.getLieferantenID() == id).forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}

	private static ArrayList<String> tableOfAll () {

		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Lieferanten-ID", "Name"};
		table.addAll(Arrays.asList(spalten));
		
		l.stream().forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}

}