package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbaccess.DBAccess;
import objects.Lieferant;
import objects.Lieferantenrechnung;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Lieferanten extends JPanel {

	public static String lieferant = "";
	public static boolean abgebrochen = true;

	private JTextField nameNeuField;
	private JTextField generiertField;
	private JLabel bestehendLabel;
	private JTextField nameSuchenField;
	private JTextField idSuchenField;
	private JButton suchenButton;
	private JLabel bearbeitenLabel;
	private JTextField idBearbeitenFeld;
	private JButton bearbeitenButton;
	private JButton addRechnungButton;
	private JScrollPane pane;
	private JTable table;
	private JLabel changeLabel;
	private JLabel neuInputLabel;
	private JLabel suchenLabel;
	private JLabel neuNameLabel;
	private JLabel neuIDLabel;
	private JLabel bearbeitenIDLabel;
	private JLabel suchenIDLabel;
	private JLabel suchenNameLabel;

	// Constructor
	public Lieferanten() {
		
		setBounds(0, 0, 1280, 720);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 390, 0, 344, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		// Neuer Lieferant
		JLabel neuLabel = new JLabel("Neuer Lieferant:");
		GridBagConstraints gbc_neuLabel = new GridBagConstraints();
		gbc_neuLabel.anchor = GridBagConstraints.WEST;
		gbc_neuLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuLabel.gridx = 1;
		gbc_neuLabel.gridy = 1;
		neuLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		add(neuLabel, gbc_neuLabel);
		
		nameNeuField = new JTextField("Bitte Name eingeben...");
		GridBagConstraints gbc_nameNeuField = new GridBagConstraints();
		gbc_nameNeuField.insets = new Insets(0, 0, 5, 5);
		gbc_nameNeuField.fill = GridBagConstraints.BOTH;
		gbc_nameNeuField.gridx = 1;
		gbc_nameNeuField.gridy = 3;
		nameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		nameNeuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameNeuField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		neuNameLabel = new JLabel("Name:");
		neuNameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_neuNameLabel = new GridBagConstraints();
		gbc_neuNameLabel.anchor = GridBagConstraints.WEST;
		gbc_neuNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuNameLabel.gridx = 1;
		gbc_neuNameLabel.gridy = 2;
		add(neuNameLabel, gbc_neuNameLabel);
		
		neuIDLabel = new JLabel("Generierte ID:");
		neuIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_neuIDLabel = new GridBagConstraints();
		gbc_neuIDLabel.anchor = GridBagConstraints.WEST;
		gbc_neuIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuIDLabel.gridx = 3;
		gbc_neuIDLabel.gridy = 2;
		add(neuIDLabel, gbc_neuIDLabel);
		add(nameNeuField, gbc_nameNeuField);
		
		generiertField = new JTextField("Generierte ID erscheint hier...");
		generiertField.setEditable(false);
		generiertField.setHorizontalAlignment(SwingConstants.CENTER);
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		generiertField.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_generiertField = new GridBagConstraints();
		gbc_generiertField.insets = new Insets(0, 0, 5, 5);
		gbc_generiertField.fill = GridBagConstraints.BOTH;
		gbc_generiertField.gridx = 3;
		gbc_generiertField.gridy = 3;
		add(generiertField, gbc_generiertField);
		generiertField.setColumns(10);
		
		JButton speichernButton = new JButton(" Speichern ");
		speichernButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_speichernButton = new GridBagConstraints();
		gbc_speichernButton.fill = GridBagConstraints.VERTICAL;
		gbc_speichernButton.insets = new Insets(0, 0, 5, 5);
		gbc_speichernButton.gridx = 5;
		gbc_speichernButton.gridy = 3;
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String nameInput = nameNeuField.getText();
				nameNeuField.setText("Bitte Name eingeben...");
				
				if (nameInput.equals("") || nameInput.equals("Bitte Name eingeben...")) {
					neuInputLabel.setForeground(Color.RED);
					neuInputLabel.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				for (int i = 0; i < DBAccess.getL().toArray().length; i++)
					if (DBAccess.getL().get(i).getName().equals(nameInput)) {
						neuInputLabel.setForeground(Color.RED);
						neuInputLabel.setText("Lieferant bereits vorhanden! (ID: " + DBAccess.getL().get(i).getLieferantenID() + ")");
						return;
					}
				
				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
				neuInputLabel.setText("");
				neuInputLabel.setForeground(Color.BLACK);
				lieferantAufnehmen(nameInput);
				neuInputLabel.setText(nameInput + " erfolgreich aufgenommen.");
				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
				
				generiertField.setText(String.valueOf(DBAccess.getL().toArray().length));
				nameNeuField.setText("Bitte Name eingeben...");
				return;
			}
		});
		add(speichernButton, gbc_speichernButton);
		
		neuInputLabel = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		neuInputLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_neuInputLabel = new GridBagConstraints();
		gbc_neuInputLabel.anchor = GridBagConstraints.WEST;
		gbc_neuInputLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuInputLabel.gridx = 1;
		gbc_neuInputLabel.gridy = 4;
		add(neuInputLabel, gbc_neuInputLabel);
		
		bearbeitenLabel = new JLabel("Lieferant bearbeiten:");
		bearbeitenLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bearbeitenLabel = new GridBagConstraints();
		gbc_bearbeitenLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenLabel.gridx = 1;
		gbc_bearbeitenLabel.gridy = 6;
		add(bearbeitenLabel, gbc_bearbeitenLabel);
	 
		
		// Lieferant bearbeiten
		idBearbeitenFeld = new JTextField("Bitte ID eingeben...");
		idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenFeld.setColumns(10);
		idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
		idBearbeitenFeld.setFont(new Font("Palatino", Font.PLAIN, 14));
		idBearbeitenFeld.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idBearbeitenFeld.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		bearbeitenIDLabel = new JLabel("Lieferanten-ID:");
		bearbeitenIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_bearbeitenIDLabel = new GridBagConstraints();
		gbc_bearbeitenIDLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenIDLabel.gridx = 1;
		gbc_bearbeitenIDLabel.gridy = 7;
		add(bearbeitenIDLabel, gbc_bearbeitenIDLabel);
		GridBagConstraints gbc_idBearbeitenFeld = new GridBagConstraints();
		gbc_idBearbeitenFeld.insets = new Insets(0, 0, 5, 5);
		gbc_idBearbeitenFeld.fill = GridBagConstraints.BOTH;
		gbc_idBearbeitenFeld.gridx = 1;
		gbc_idBearbeitenFeld.gridy = 8;
		add(idBearbeitenFeld, gbc_idBearbeitenFeld);
		
		bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_bearbeitenButton = new GridBagConstraints();
		gbc_bearbeitenButton.fill = GridBagConstraints.VERTICAL;
		gbc_bearbeitenButton.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenButton.gridx = 3;
		gbc_bearbeitenButton.gridy = 8;
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("Bitte ID eingeben...") || idInput.equals("")) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("Bitte Eingabe prüfen.");
					return;
				}	
				
				if (idInput.equals("0")) {
					changeLabel.setText("ID: 0 existiert nicht.");
					return;
				}
				
				if (Integer.parseInt(idInput) > DBAccess.getL().toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}
				
				int id = Integer.parseInt(idInput);
				
				try {
			        popups.LieferantBearbeiten dialog = new popups.LieferantBearbeiten(id, getName(id));
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        dialog.setVisible(true);
			        dialog.addWindowListener(new WindowAdapter() {
			        	public void windowClosed(WindowEvent e) {
			        		changeLabel.setForeground(Color.BLACK);
			        		if (!abgebrochen) changeLabel.setText("Lieferant bearbeitet.");
			            }
			        });
				} catch (Exception e1) {e1.printStackTrace();}
				
				resetLabel(changeLabel);
			}
		});
		add(bearbeitenButton, gbc_bearbeitenButton);
		
		addRechnungButton = new JButton("Rechnung+");
		addRechnungButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_addRechnungButton = new GridBagConstraints();
		gbc_addRechnungButton.fill = GridBagConstraints.VERTICAL;
		gbc_addRechnungButton.insets = new Insets(0, 0, 5, 5);
		gbc_addRechnungButton.gridx = 5;
		gbc_addRechnungButton.gridy = 8;
		addRechnungButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("Bitte ID eingeben...") || idInput.equals("")) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("Bitte Eingabe prüfen.");
					return;
				}	

				if (idInput.equals("0")) {
					changeLabel.setText("ID: 0 existiert nicht.");
					return;
				}
				
				if (Integer.parseInt(idInput) > DBAccess.getL().toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}
				
				int id = Integer.parseInt(idInput);
				
				try {
			        popups.LieferantenRechnungBearbeiten dialog = new popups.LieferantenRechnungBearbeiten (id, getName(id), (DBAccess.getLr().toArray().length + 1), true);
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        dialog.setVisible(true);
			        dialog.addWindowListener(new WindowAdapter() {
			        	public void windowClosed(WindowEvent e) {
			        		changeLabel.setForeground(Color.BLACK);
			        		if (!abgebrochen) changeLabel.setText("Rechnung aufgenommen.");
			            }
			        });
				} catch (Exception e1) {e1.printStackTrace();}
				
				resetLabel(neuInputLabel);
				abgebrochen = true;
			}
		});
		add(addRechnungButton, gbc_addRechnungButton);
		
		changeLabel = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		changeLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_changeLabel = new GridBagConstraints();
		gbc_changeLabel.anchor = GridBagConstraints.WEST;
		gbc_changeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_changeLabel.gridx = 1;
		gbc_changeLabel.gridy = 9;
		add(changeLabel, gbc_changeLabel);
		
		
		// Lieferant suchen
		bestehendLabel = new JLabel("Lieferant suchen:");
		bestehendLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bestehendLabel = new GridBagConstraints();
		gbc_bestehendLabel.anchor = GridBagConstraints.WEST;
		gbc_bestehendLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bestehendLabel.gridx = 1;
		gbc_bestehendLabel.gridy = 11;
		add(bestehendLabel, gbc_bestehendLabel);
		
		nameSuchenField = new JTextField("Bitte Name eingeben...");
		nameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		nameSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_nameSuchenField = new GridBagConstraints();
		gbc_nameSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_nameSuchenField.fill = GridBagConstraints.BOTH;
		gbc_nameSuchenField.gridx = 1;
		gbc_nameSuchenField.gridy = 13;
		nameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		suchenNameLabel = new JLabel("Name:");
		suchenNameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_suchenNameLabel = new GridBagConstraints();
		gbc_suchenNameLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenNameLabel.gridx = 1;
		gbc_suchenNameLabel.gridy = 12;
		add(suchenNameLabel, gbc_suchenNameLabel);
		
		suchenIDLabel = new JLabel("Lieferanten-ID:");
		suchenIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_suchenIDLabel = new GridBagConstraints();
		gbc_suchenIDLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenIDLabel.gridx = 3;
		gbc_suchenIDLabel.gridy = 12;
		add(suchenIDLabel, gbc_suchenIDLabel);
		add(nameSuchenField, gbc_nameSuchenField);
		
		idSuchenField = new JTextField("Bitte ID eingeben...");
		idSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		idSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		idSuchenField.setColumns(10);
		idSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_idSuchenField = new GridBagConstraints();
		gbc_idSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_idSuchenField.fill = GridBagConstraints.BOTH;
		gbc_idSuchenField.gridx = 3;
		gbc_idSuchenField.gridy = 13;
		idSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		add(idSuchenField, gbc_idSuchenField);
		
		suchenButton = new JButton("   Suchen   ");
		suchenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_suchenButton = new GridBagConstraints();
		gbc_suchenButton.fill = GridBagConstraints.VERTICAL;
		gbc_suchenButton.insets = new Insets(0, 0, 5, 5);
		gbc_suchenButton.gridx = 5;
		gbc_suchenButton.gridy = 13;
		suchenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				ArrayList<String> list = new ArrayList<String> ();
				
				String nameInput = nameSuchenField.getText();
				String idInput = idSuchenField.getText();
				boolean skip = false;
				
				nameSuchenField.setText("Bitte Name eingeben...");
				idSuchenField.setText("Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") && !idInput.equals("Bitte ID eingeben...")) {
					suchenLabel.setForeground(Color.RED);
					suchenLabel.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				if (idInput.equals("0")) {
					suchenLabel.setText("ID: 0 existiert nicht.");
					return;
				}
				
				// Keine Eingabe
				if ((idInput.equals("Bitte ID eingeben...") || idInput.equals("")) && (nameInput.equals("Bitte Name eingeben...") || nameInput.equals(""))) {
					list = tableOfAll();
					skip = true;
				}
				
				// ID und Name eingegeben
				if (idInput.matches("[0-9]+") && !nameInput.equals("") && !nameInput.equals("Bitte Name eingeben...") && !skip) {
					list = tableByNameAndID(Integer.parseInt(idInput), nameInput);
					skip = true;
				}
				
				// Nur ID
				if (idInput.matches("[0-9]+") && !skip) {
					list = tableByID(Integer.parseInt(idInput));
					skip = true;
				}
				
				// Nur Name
				if (nameInput.length() != 0 && !skip) {
					list = tableByName(nameInput);
					skip = true;
				}
				
				// Set table
				
				String [][] array = new String [(list.toArray().length)/2][2];
				int counter = 0;
				
				for (int i = 0; i < (list.toArray().length)/2; i++) 
					for (int j = 0; j < 2; j++) {
						array[i][j] = "  " + list.get(counter);
						counter++;
					}
				
				DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] {"Lieferanten-ID", "Name"});
				table.setModel(tableModel);
				skip = false;
				return;
			}
		});
		add(suchenButton, gbc_suchenButton);
		
		suchenLabel = new JLabel("Ohne Eingabe suchen um alle Kunden auszugeben.");
		suchenLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_suchenLabel = new GridBagConstraints();
		gbc_suchenLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenLabel.gridx = 1;
		gbc_suchenLabel.gridy = 14;
		add(suchenLabel, gbc_suchenLabel);
		
		table = new JTable();
		table.setEnabled(false);
		table.setBorder(new LineBorder(Color.BLACK, 2));
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);
		
		pane = new JScrollPane(table);
		pane.setEnabled(false);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.gridheight = 6;
		gbc_pane.gridwidth = 5;
		gbc_pane.insets = new Insets(0, 0, 5, 5);
		gbc_pane.fill = GridBagConstraints.BOTH;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 16;
		add(pane, gbc_pane);
		
	}
	
	
	// Edit Lieferanten
	
		// Add Lieferant to objects and call dbAddLieferant
		private static void lieferantAufnehmen (String name) {	
			int newID = DBAccess.getL().toArray().length + 1;
			DBAccess.getL().add(new Lieferant(newID, name));
			dbAddLieferant(name);
			DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
		}
		
		// Add Lieferant in mySQL database
		public static void dbAddLieferant (String name) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				stmt.execute(" INSERT INTO lieferanten (Name) VALUES ('"+name+"')");
			} catch (Exception e) {e.printStackTrace();}
		}
		
		// Change Lieferant in objects and call dbChangeLieferant
		public static void lieferantBearbeiten (int id, String name) {
			DBAccess.getL().stream().filter(x -> x.getLieferantenID() == id).forEach(x -> x.setName(name));
			dbChangeLieferant(id, name);
			DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
		}

		// Change Lieferant in mySQL database
		public static void dbChangeLieferant (int id, String name) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				stmt.execute("UPDATE lieferanten SET name = '"+name+"' WHERE LieferantenID = '"+id+"'");
			} catch (Exception e) {e.printStackTrace();}
		}

		
	// Add invoice
		
		// Add invoice to objects and call dbAddRechnung
		public static void rechnungAufnehmen (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
			int newID = DBAccess.getLr().toArray().length + 1;
			DBAccess.getLr().add(new Lieferantenrechnung(newID, monat, jahr, bestellvolumen, status, lieferantenID));
			dbAddRechnung(lieferantenID, monat, jahr, bestellvolumen, status);
			DBAccess.dbResetAutoIncrement("RechnungsID", "lieferantenrechnungen");
		}
		
		// Add invoice in mySQL database
		public static void dbAddRechnung (int lieferantenID, int monat, int jahr, double bestellvolumen, boolean status) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				int tinyInt = 0;
				if (status) tinyInt = 1;
				stmt.execute("INSERT INTO lieferantenrechnungen (LieferantenID, Monat, Jahr, Bestellvolumen, Status) VALUES ('"+lieferantenID+"', '"+monat+"', '"+jahr+"', '"+bestellvolumen+"', '"+tinyInt+"')");
			} catch (Exception e) {e.printStackTrace();}
		}

		
	// Auxiliary functions
		
		// Get name from object
		private static String getName (int id) {
			DBAccess.getL().stream().filter(x -> x.getLieferantenID() == id).forEach(x -> lieferant = x.getName());
			return lieferant;
		}
		
		// Set label to empty String
		private static void resetLabel (JLabel label) {
			label.setText("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		}
		
		
	// Tabellen erstellen
		
		// Create table if user only inputs name
		private static ArrayList<String> tableByName (String name) {

			ArrayList<String> table = new ArrayList<String>();
			
			DBAccess.getL().stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).forEach(x -> {
				table.add(String.valueOf(x.getLieferantenID()));
				table.add(x.getName());
			});
			
			return table;
		}
		
		// Create table if user only inputs id
		private static ArrayList<String> tableByID (int id) {

			ArrayList<String> table = new ArrayList<String>();
			
			DBAccess.getL().stream().filter(x -> x.getLieferantenID() == id).forEach(x -> {
				table.add(String.valueOf(x.getLieferantenID()));
				table.add(x.getName());
			});
			
			return table;
		}
		
		// Create table if user inputs name and id
		private static ArrayList<String> tableByNameAndID (int id, String name) {

			ArrayList<String> table = new ArrayList<String>();
			
			DBAccess.getL().stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase()) && x.getLieferantenID() == id).forEach(x -> {
				table.add(String.valueOf(x.getLieferantenID()));
				table.add(x.getName());
			});
			
			return table;
		}
		
		// // Create table if user doesnt input anything
		private static ArrayList<String> tableOfAll () {

			ArrayList<String> table = new ArrayList<String>();
			
			DBAccess.getL().stream().forEach(x -> {
				table.add(String.valueOf(x.getLieferantenID()));
				table.add(x.getName());
			});
			
			return table;
		}
}