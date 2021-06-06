package gui;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import objects.*;
import dbaccess.*;
import popups.*;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;

public class Lieferanten extends JPanel{

	// Auslesen DB
	static ArrayList <objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	static ArrayList <objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
	public static String lieferant = "";
	public static boolean abgebrochen = true;
	
	public Lieferanten () {
		
		setLayout(null);
		setBounds(2000, 2000, 2000, 2000);

		JLabel bestehendLabel = new JLabel("Lieferant suchen:");
		bestehendLabel.setBounds(101, 308, 277, 26);
		bestehendLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		add(bestehendLabel);
		
		JLabel labelBestellvolumen = new JLabel("Neuer Lieferant:");
		labelBestellvolumen.setFont(new Font("Serif", Font.PLAIN, 25));
		labelBestellvolumen.setBounds(101, 75, 395, 32);
		add(labelBestellvolumen);
		
		JLabel inputLabel = new JLabel("");
		inputLabel.setForeground(Color.RED);
		inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel.setBounds(101, 253, 573, 26);
		add(inputLabel);
		
		nameNeuField = new JTextField();
		nameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nameNeuField.setFont(new Font("Sans", Font.PLAIN, 14));
		nameNeuField.setBounds(101, 160, 219, 53);
		nameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		add(nameNeuField);
		nameNeuField.setText("   Bitte Name eingeben...");
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
		generiertField.setBounds(518, 160, 219, 53);
		add(generiertField);
		
		JButton speichernButton = new JButton("Speichern");
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.setForeground(new Color(30, 144, 255));
		speichernButton.setBounds(936, 163, 170, 50);
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
		nameSuchenField.setBounds(101, 389, 219, 53);
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
		idSuchenField.setBounds(518, 389, 219, 53);
		add(idSuchenField);
		idSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		JLabel inputLabel2 = new JLabel("Ohne Eingabe suchen um alle Lieferanten auszugeben.");
		inputLabel2.setForeground(Color.DARK_GRAY);
		inputLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel2.setBounds(101, 469, 573, 26);
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
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(101, 535, 1310, 420);
		add(pane);
		
		JButton suchenButton = new JButton("Suchen");
		suchenButton.setForeground(new Color(30, 144, 255));
		suchenButton.setBackground(new Color(30, 144, 255));
		suchenButton.setBounds(936, 392, 170, 50);
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
		add(suchenButton);
		
		JLabel bearbeitenLabel = new JLabel("Lieferant bearbeiten:");
		bearbeitenLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		bearbeitenLabel.setBounds(1304, 84, 277, 26);
		add(bearbeitenLabel);
		
		JLabel inputLabel3 = new JLabel("");
		inputLabel3.setEnabled(false);
		inputLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel3.setForeground(Color.RED);
		inputLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel3.setBounds(1293, 469, 210, 26);
		add(inputLabel3);
		
		idBearbeitenFeld = new JTextField();
		idBearbeitenFeld.setText("   Bitte ID eingeben...");
		idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenFeld.setFont(new Font("Dialog", Font.PLAIN, 14));
		idBearbeitenFeld.setColumns(10);
		idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
		idBearbeitenFeld.setBounds(1293, 160, 219, 53);
		add(idBearbeitenFeld);
		idBearbeitenFeld.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idBearbeitenFeld.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		JButton bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.setForeground(new Color(30, 144, 255));
		bearbeitenButton.setBackground(new Color(30, 144, 255));
		bearbeitenButton.setBounds(1315, 278, 170, 50);
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("   Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
					inputLabel3.setForeground(Color.RED);
					inputLabel3.setText("Bitte Eingabe prüfen.");
					return;
				}	
				
				if (Integer.parseInt(idInput) > l.toArray().length) {
					inputLabel3.setForeground(Color.RED);
					inputLabel3.setText("ID existiert nicht.");
					return;
				}
				
				int id = Integer.parseInt(idInput);
				
				try {
			        popups.LieferantBearbeiten dialog = new popups.LieferantBearbeiten(id, getName(id));
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        dialog.setVisible(true);
			        dialog.addWindowListener(new WindowAdapter() {
			        	public void windowClosed(WindowEvent e) {
			        		inputLabel3.setForeground(Color.BLACK);
			        		if (!abgebrochen) inputLabel3.setText("Lieferant bearbeitet.");
			            }
			        });
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(bearbeitenButton);
		
		JButton addRechnungButton = new JButton("Neue Rechnung");
		addRechnungButton.setForeground(new Color(30, 144, 255));
		addRechnungButton.setBackground(new Color(30, 144, 255));
		addRechnungButton.setBounds(1315, 389, 170, 50);
		addRechnungButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("   Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
					inputLabel3.setForeground(Color.RED);
					inputLabel3.setText("Bitte Eingabe prüfen.");
					return;
				}	

				if (Integer.parseInt(idInput) > l.toArray().length) {
					inputLabel3.setForeground(Color.RED);
					inputLabel3.setText("ID existiert nicht.");
					return;
				}
				
				int id = Integer.parseInt(idInput);
				
				try {
			        popups.LieferantenRechnungenNeu dialog = new popups.LieferantenRechnungenNeu (id, getName(id), (lr.toArray().length + 1));
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        dialog.setVisible(true);
			        dialog.addWindowListener(new WindowAdapter() {
			        	public void windowClosed(WindowEvent e) {
			        		inputLabel3.setForeground(Color.BLACK);
			        		if (!abgebrochen) inputLabel3.setText("Rechnung aufgenommen.");
			            }
			        });
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(addRechnungButton);
		
	}

	private JTextField nameNeuField;
	private JTextField generiertField;
	private JTextField nameSuchenField;
	private JTextField idSuchenField;
	private JTable table;
	private JTextField idBearbeitenFeld;
	
	
	// Lieferanten in ArrayList aendern
	private static void lieferantAufnehmen (String name) {	
		int newID = l.toArray().length + 1;
		l.add(new Lieferant(newID, name));
		dbAddLieferant(name);
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	
	public static void lieferantBearbeiten (int id, String name) {
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
	
	public static void dbChangeLieferant (int id, String name) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("UPDATE lieferanten SET name = '"+name+"' WHERE LieferantenID = '"+id+"'");
		} catch (Exception e) {e.printStackTrace();}
	}

	
	// Neue Rechnung anlegen
	public static void rechnungAufnehmen (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		int newID = lr.toArray().length + 1;
		lr.add(new Lieferantenrechnung(newID, monat, jahr, bestellvolumen, status, lieferantenID));
		dbAddRechnung(lieferantenID, monat, jahr, bestellvolumen, status);
		DBAccess.dbResetAutoIncrement("RechnungsID", "lieferantenrechnungen");
	}
	
	public static void dbAddRechnung (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			int tinyInt = 0;
			if (status) tinyInt = 1;
			stmt.execute("INSERT INTO lieferantenrechnungen (LieferantenID, Monat, Jahr, Bestellvolumen, Status) VALUES ('"+lieferantenID+"', '"+monat+"', '"+jahr+"', '"+bestellvolumen+"', '"+tinyInt+"')");
		} catch (Exception e) {e.printStackTrace();}
	}

	
	// Hilfsfunktinoen
	private static String getName (int id) {
		l.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> lieferant = x.getName());
		return lieferant;
	}
	
	
	// Tabelle erstellen
	private static ArrayList<String> tableByName (String name) {

		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Lieferanten-ID", "Name"};
		table.addAll(Arrays.asList(spalten));
		
		l.stream().filter(x -> x.getName().toLowerCase().contains(name)).forEach(x -> {
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
		
		l.stream().filter(x -> x.getName().toLowerCase().contains(name) && x.getLieferantenID() == id).forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}

	private static ArrayList<String> tableOfAll () {

		ArrayList<String> table = new ArrayList<String>();
		
		l.stream().forEach(x -> {
			table.add(String.valueOf(x.getLieferantenID()));
			table.add(x.getName());
		});
		
		return table;
	}
}