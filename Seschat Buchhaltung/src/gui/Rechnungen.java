package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbaccess.DBAccess;
import objects.Kunde;
import objects.Kundenrechnung;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Rechnungen extends JPanel{


	// Read from DB and add data to objects
		static ArrayList<objects.Kunde> k = dbaccess.DBAccess.createKunden();
		static ArrayList<objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
		static ArrayList<objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
		static ArrayList<objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
		
		static String vorname = "", nachname = ""; static int plz = 0;
		public static boolean abgebrochen = true;

		static String[] monate = {"Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
		private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		private static String[] lastTenYears = new String[11];
		
		public static String lieferant = "";
		public static int kundeID = 0;
		public static int lieferantID = 0;
		
		
	// Components for GUI

		private JTextField kundenIDField;
		private JTextField rechnungsIDField;
		private JTable table;
		private JTextField idBearbeitenFeld;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private final ButtonGroup ausgebenGroup = new ButtonGroup();
		private final ButtonGroup sortierenGroup = new ButtonGroup();
		private final ButtonGroup bearbeitenGroup = new ButtonGroup();

	// Constructor for JPanel

		public Rechnungen() {

		// Define Panel
			setLayout(null);
			setBounds(2000, 2000, 2000, 2000);

	// Rechnungen ausgeben	
			
			JLabel bestehendLabel = new JLabel("Rechnungen ausgeben:");
			bestehendLabel.setBounds(265, 96, 277, 26);
			bestehendLabel.setFont(new Font("Serif", Font.PLAIN, 25));
			add(bestehendLabel);

			fillYears();
			JComboBox monatDropdownSuchen = new JComboBox(monate);
			monatDropdownSuchen.setMaximumRowCount(14);
			monatDropdownSuchen.setBounds(585, 149, 181, 78);
			add(monatDropdownSuchen);
			
			JComboBox jahrDropdownSuchen = new JComboBox(lastTenYears);
			jahrDropdownSuchen.setMaximumRowCount(14);
			jahrDropdownSuchen.setBounds(873, 149, 181, 78);
			add(jahrDropdownSuchen);
			
			JLabel lblNewLabel = new JLabel("Sortieren nach:");
			lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
			lblNewLabel.setBounds(265, 385, 132, 26);
			add(lblNewLabel);
			
			JRadioButton summeRadio = new JRadioButton("Summe");
			sortierenGroup.add(summeRadio);
			summeRadio.setBounds(432, 386, 78, 23);
			add(summeRadio);
			
			JRadioButton zeitraumRadio = new JRadioButton("Zeitraum");
			sortierenGroup.add(zeitraumRadio);
			zeitraumRadio.setBounds(585, 386, 92, 23);
			add(zeitraumRadio);
			
			String sortier [] = {"absteigend", "aufsteigend"};
			JComboBox sortierenBox = new JComboBox(sortier);
			sortierenBox.setMaximumRowCount(2);
			sortierenBox.setBounds(432, 436, 181, 27);
			add(sortierenBox);
			
			JRadioButton bezahltRadio = new JRadioButton("Bezahlt");
			ausgebenGroup.add(bezahltRadio);
			bezahltRadio.setBounds(585, 268, 141, 23);
			add(bezahltRadio);
			
			JRadioButton rechnungsIDRadio = new JRadioButton("Rechnungs-ID");
			sortierenGroup.add(rechnungsIDRadio);
			rechnungsIDRadio.setBounds(922, 386, 132, 23);
			add(rechnungsIDRadio);
			
			JRadioButton kundenIDRadio = new JRadioButton("Kunden-/Lieferanten-ID");
			sortierenGroup.add(kundenIDRadio);
			kundenIDRadio.setBounds(714, 386, 207, 23);
			add(kundenIDRadio);
			
			JRadioButton unbezahltRadio = new JRadioButton("Unbezahlt");
			ausgebenGroup.add(unbezahltRadio);
			unbezahltRadio.setBounds(873, 268, 141, 23);
			add(unbezahltRadio);
			
			JRadioButton kundeRadio = new JRadioButton("Kunde");
			buttonGroup.add(kundeRadio);
			kundeRadio.setBounds(585, 98, 141, 23);
			add(kundeRadio);
			
			JRadioButton lieferantRadio = new JRadioButton("Lieferant");
			buttonGroup.add(lieferantRadio);
			lieferantRadio.setBounds(873, 98, 141, 23);
			add(lieferantRadio);
			
			kundenIDField = new JTextField();
			kundenIDField.setText("Kunden-/Lieferanten-ID...");
			kundenIDField.setHorizontalAlignment(SwingConstants.CENTER);
			kundenIDField.setFont(new Font("Dialog", Font.PLAIN, 14));
			kundenIDField.setColumns(10);
			kundenIDField.setBorder(new LineBorder(Color.BLACK, 1));
			kundenIDField.setBounds(265, 253, 219, 53);
			kundenIDField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					kundenIDField.setText("");
				}

				public void focusLost(FocusEvent e) {
				}
			});
			add(kundenIDField);
			
			rechnungsIDField = new JTextField();
			rechnungsIDField.setText("Rechnungs-ID eingeben...");
			rechnungsIDField.setHorizontalAlignment(SwingConstants.CENTER);
			rechnungsIDField.setFont(new Font("Dialog", Font.PLAIN, 14));
			rechnungsIDField.setColumns(10);
			rechnungsIDField.setBorder(new LineBorder(Color.BLACK, 1));
			rechnungsIDField.setBounds(265, 160, 219, 53);
			rechnungsIDField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					rechnungsIDField.setText("");
				}
				public void focusLost(FocusEvent e) {
				}
			});
			add(rechnungsIDField);
			
			JLabel suchenLabel = new JLabel("Ohne Eingabe suchen um alle Rechnungen auszugeben.");
			suchenLabel.setHorizontalAlignment(SwingConstants.CENTER);
			suchenLabel.setForeground(Color.DARK_GRAY);
			suchenLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			suchenLabel.setBounds(265, 487, 749, 26);
			add(suchenLabel);

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
			pane.setBounds(265, 533, 1310, 420);
			add(pane);

			JButton suchenButton = new JButton("Suchen");
			suchenButton.setForeground(new Color(30, 144, 255));
			suchenButton.setBackground(new Color(30, 144, 255));
			suchenButton.setBounds(873, 424, 181, 50);
			suchenButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					// Sortieren
					
					boolean isKunde = kundeRadio.isSelected();
					boolean isLieferant = lieferantRadio.isSelected();
					
					if (!isKunde && !isLieferant) {
						suchenLabel.setForeground(Color.RED);
						suchenLabel.setText("Bitte Rechnungstyp angeben.");
						return;
					}
					
					int sort = sortierenBox.getSelectedIndex();
					boolean zeitraum = zeitraumRadio.isSelected();
					boolean summe = summeRadio.isSelected();
					boolean rechnung = rechnungsIDRadio.isSelected();
					boolean kunde = kundenIDRadio.isSelected();
				    
					sortRechnungen (sort, zeitraum, summe, rechnung, kunde, isKunde, isLieferant);
					
					// Filtern
					
					ArrayList <String> list = new ArrayList<String> ();
					
					String rechnungsID = rechnungsIDField.getText();
					String id = kundenIDField.getText(); 
					boolean skip = false;
					
					if ((!rechnungsID.matches("[0-9]+") && !rechnungsID.equals("") && !rechnungsID.equals("Rechnungs-ID eingeben...")) || (!id.matches("[0-9]+") && !id.equals("") && !id.equals("Kunden-/Lieferanten-ID..."))) {
						setErrMessage(suchenLabel);
						return;
					}
					
					// Rechnungs-ID eingegeben
					if (rechnungsID.matches("[0-9]+")) {
						list = tableByrechnungsID(Integer.valueOf(rechnungsID), isKunde);
						skip = true;
					}
				
					int monat = monatDropdownSuchen.getSelectedIndex();
					String jahr = lastTenYears[jahrDropdownSuchen.getSelectedIndex()];
					
					if (monat > 0 && !jahr.matches("[0-9]+")) {
						suchenLabel.setText("Monat immer mit Jahr auswählen");
						return;
					}
					
					boolean bezahlt = bezahltRadio.isSelected();
					boolean unbezahlt = unbezahltRadio.isSelected();
					
					// ID und Monat
					if (id.matches("[1-9]+") && monat > 0 && !skip) {
						list = tableByIDinMonat(Integer.parseInt(id), isKunde, monat, Integer.parseInt(jahr), bezahlt, unbezahlt);
						skip = true;
					}
					
					// ID und Jahr
					if (id.matches("[1-9]+") && jahrDropdownSuchen.getSelectedIndex() > 0 && !skip) {
						list = tableByIDinJahr(Integer.parseInt(id), isKunde, Integer.parseInt(jahr), bezahlt, unbezahlt);
						skip = true;
					}
					
					// ID
					if (id.matches("[1-9]+") && !skip) {
						list = tableByID(Integer.parseInt(id), isKunde, bezahlt, unbezahlt);
						skip = true;
					}
					
					if (!skip) list = tableOfAll(isKunde, bezahlt, unbezahlt);
					
					// list to table
					String[][] array = new String[(list.toArray().length)/6][6];
					int counter = 0;
					
					for (int i = 0; i < (list.toArray().length)/6; i++)
						for (int j = 0; j < 6; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Rechnungs-ID", "Kunden- / Lieferanten-ID", "Monat", "Jahr", "Summe", "Status"});
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					skip = false;
					return;
					
				}
			});
			add(suchenButton);

			
	// Rechnung bearbeiten
			
			JLabel bearbeitenLabel = new JLabel("Rechnung bearbeiten:");
			bearbeitenLabel.setFont(new Font("Serif", Font.PLAIN, 25));
			bearbeitenLabel.setBounds(1356, 96, 229, 26);
			add(bearbeitenLabel);

			idBearbeitenFeld = new JTextField();
			idBearbeitenFeld.setText("   Bitte ID eingeben...");
			idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
			idBearbeitenFeld.setFont(new Font("Dialog", Font.PLAIN, 14));
			idBearbeitenFeld.setColumns(10);
			idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
			idBearbeitenFeld.setBounds(1356, 185, 219, 53);
			idBearbeitenFeld.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					idBearbeitenFeld.setText("");
				}

				public void focusLost(FocusEvent e) {
				}
			});
			add(idBearbeitenFeld);

			JLabel changeLabel = new JLabel("");
			changeLabel.setEnabled(false);
			changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			changeLabel.setForeground(Color.RED);
			changeLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			changeLabel.setBounds(1219, 453, 500, 26);
			add(changeLabel);
			
			JRadioButton kundeRadioBearbeiten = new JRadioButton("Kunde");
			bearbeitenGroup.add(kundeRadioBearbeiten);
			kundeRadioBearbeiten.setBounds(1356, 289, 119, 23);
			add(kundeRadioBearbeiten);
			
			JRadioButton lieferantRadioBearbeiten = new JRadioButton("Lieferant");
			bearbeitenGroup.add(lieferantRadioBearbeiten);
			lieferantRadioBearbeiten.setBounds(1484, 289, 91, 23);
			add(lieferantRadioBearbeiten);
			
			JButton bearbeitenButton = new JButton("Bearbeiten");
			bearbeitenButton.setForeground(new Color(30, 144, 255));
			bearbeitenButton.setBackground(new Color(30, 144, 255));
			bearbeitenButton.setBounds(1382, 361, 170, 50);
			bearbeitenButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					String idInput = idBearbeitenFeld.getText();
					idBearbeitenFeld.setText("   Bitte ID eingeben...");

					boolean lieferant = lieferantRadioBearbeiten.isSelected();
					boolean kunde = kundeRadioBearbeiten.isSelected();
					
					// Check User Input
					if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
						setErrMessage(changeLabel);
						return;
					}

					if (kunde && Integer.parseInt(idInput) > kr.toArray().length) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("ID existiert nicht.");
						return;
					}

					if (lieferant && Integer.parseInt(idInput) > lr.toArray().length) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("ID existiert nicht.");
						return;
					}
					
					if (!lieferant && !kunde) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("Bitte Rechnungstyp angeben.");
						return;
					}
					
					int id = Integer.parseInt(idInput);

					try {
						if (kunde) {
							popups.KundenRechnungBearbeiten dialog = new popups.KundenRechnungBearbeiten(getKundenID(id), getVorname(id), getNachname(id), getPLZ(id), id);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									changeLabel.setForeground(Color.BLACK);
									if (!abgebrochen) changeLabel.setText("Rechnung bearbeitet.");
								}
							});
						}
						
						if (lieferant) {
							popups.LieferantenRechnungBearbeiten dialog = new popups.LieferantenRechnungBearbeiten(getLieferantenID(id), getName(getLieferantenID(id)), id);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									changeLabel.setForeground(Color.BLACK);
									if (!abgebrochen) changeLabel.setText("Rechnung bearbeitet.");
								}
							});
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					resetLabel(changeLabel);
				}
			});
			add(bearbeitenButton);
			
			JButton loeschenButton = new JButton("Auswahl löschen");
			loeschenButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					sortierenGroup.clearSelection();
				}
			});
			loeschenButton.setBounds(656, 435, 163, 29);
			add(loeschenButton);

		}


	// Edit invoice

		// Add invoice to objects and call dbEditRechnung
		public static void rechnungBearbeitenLieferant(int rechnungsID, int monat, int jahr, double bestellvolumen, boolean status) {
			lr.stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> {
				x.setMonat(monat);
				x.setJahr(jahr);
				x.setSumme(bestellvolumen);
				x.setStatus(status);
			});
			dbEditRechnungLieferant(rechnungsID, monat, jahr, bestellvolumen, status);
		}

		// Add invoice in mySQL database
		public static void dbEditRechnungLieferant(int rechnungsID, int monat, int jahr, double bestellvolumen, boolean status) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				int tinyInt = 0;
				if (status) tinyInt = 1;
				stmt.execute("UPDATE lieferantenrechnungen  SET monat = '" + monat + "', jahr = '" + jahr + "', bestellvolumen = '" + bestellvolumen + "', status = '"+ tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Add invoice to objects and call dbEditRechnung
		public static void rechnungBearbeitenKunde(int rechnungsID, int monat, int jahr, double bestellvolumen, boolean status) {
			kr.stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> {
				x.setMonat(monat);
				x.setJahr(jahr);
				x.setSumme(bestellvolumen);
				x.setStatus(status);
			});
			dbEditRechnungLieferant(rechnungsID, monat, jahr, bestellvolumen, status);
		}

		// Add invoice in mySQL database
		public static void dbEditRechnungKunde(int rechnungsID, int monat, int jahr, double bestellvolumen, boolean status) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				int tinyInt = 0;
				if (status)
					tinyInt = 1;
				stmt.execute("UPDATE kundenrechnungen SET monat = '" + monat + "', jahr = '" + jahr + "', summe = '" + bestellvolumen + "', status = '" + tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	// Auxiliary functions
		
		// Get vorname
		private static String getVorname (int id) {
			k.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				vorname = x.getVorname();
			});
			return vorname;
		}
		// Get nachname
		private static String getNachname (int id) {
			k.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				nachname = x.getNachname();
			});
			return nachname;
		}
		// Get plz
		private static int getPLZ (int id) {
			k.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				plz = x.getPlz();
			});
			return plz;
		}
		// Get name from object
		private static String getName (int id) {
			l.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> lieferant = x.getName());
			return lieferant;
		}
		// Get KundenID
		private static int getKundenID (int id) {
			kr.stream().filter(x -> x.getRechnungsID() == id).forEach(x -> kundeID = x.getKundenID());
			return kundeID;
		}
		// Get LieferantenID
		private static int getLieferantenID (int id) {
			lr.stream().filter(x -> x.getRechnungsID() == id).forEach(x -> lieferantID = x.getLieferantenID());
			return lieferantID;
		}
		
		// Fill LastTenYears
		private static void fillYears () {
			lastTenYears[0] = "Jahr auswählen ...";
			for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
		}

		
		// Set label to empty String
		private static void resetLabel(JLabel label) {
			label.setText("");
		}

		// Set label to display error message
		private static void setErrMessage (JLabel inputLabel) {
			inputLabel.setForeground(Color.RED);
			inputLabel.setText("Bitte alle Felder korrekt ausfüllen.");
			return;
		}

		// Reset input fields
		private static void resetFields () {
		}

		
	// Tabellen erstellen (suchen)

		// Create tables
		private static ArrayList<String> tableByrechnungsID(int id, boolean kunde) {

			ArrayList<String> table = new ArrayList<String>();
			String bezahlt = "bezahlt";
			String unbezahlt = "ausstehend";
			
			if (kunde) {
				kr.stream().filter(x -> x.getRechnungsID() == id).forEach(x -> {
					table.add(String.valueOf(x.getRechnungsID()));
					table.add(String.valueOf(x.getKundenID()));
					table.add(monate[x.getMonat()]);
					table.add(String.valueOf(x.getJahr()));
					table.add(String.valueOf(x.getSumme()) + "€");
					if (x.isStatus()) table.add(bezahlt);
					else table.add(unbezahlt);
					});
			}
			else {
				lr.stream().filter(x -> x.getRechnungsID() == id).forEach(x -> {
					table.add(String.valueOf(x.getRechnungsID()));
					table.add(String.valueOf(x.getLieferantenID()));
					table.add(monate[x.getMonat()]);
					table.add(String.valueOf(x.getJahr()));
					table.add(String.valueOf(x.getSumme()) + "€");
					if (x.isStatus()) table.add(bezahlt);
					else table.add(unbezahlt);
					});
			}
			return table;
		}

		private static ArrayList<String> tableOfAll(boolean kunde, boolean bezahlt, boolean unbezahlt) {

			ArrayList<String> table = new ArrayList<String>();
			String bezahltS = "bezahlt";
			String unbezahltS = "ausstehend";
			
			if (kunde) {
				if (!bezahlt && !unbezahlt) {
					kr.stream().forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
				
				if (bezahlt) {
					kr.stream().filter(x -> x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
				
				if (unbezahlt) {
					kr.stream().filter(x -> !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
			}
			else {
				if (!bezahlt && !unbezahlt) {
					lr.stream().forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
				
				if (bezahlt) {
					lr.stream().filter(x -> x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
				
				if (unbezahlt) {
					lr.stream().filter(x -> !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
			}
			
			return table;
			
		}
		
		private static ArrayList<String> tableByID(int id, boolean kunde, boolean bezahlt, boolean unbezahlt) {

			ArrayList<String> table = new ArrayList<String>();
			String bezahltS = "bezahlt";
			String unbezahltS = "ausstehend";
			
			if (kunde) {
				if (!bezahlt && ! unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				
				if (bezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});					
				}
				
				if (unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
							});	
				}
			}
			else {
				
				if (!bezahlt && ! unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
				
				if (bezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});				
				}
				
				if (unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
						});
				}
			}
			return table;
		}
		
		private static ArrayList<String> tableByIDinJahr(int id, boolean kunde, int jahr, boolean bezahlt, boolean unbezahlt) {

			ArrayList<String> table = new ArrayList<String>();
			String bezahltS= "bezahlt";
			String unbezahltS = "ausstehend";
			
			if (kunde) {
				if (!bezahlt && !unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				if (bezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				
				if (unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
			}
			else {
				if (!bezahlt && !unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				if (bezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				
				if (unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
			}
			return table;
		}

		private static ArrayList<String> tableByIDinMonat(int id, boolean kunde, int monat, int jahr, boolean bezahlt, boolean unbezahlt) {

			ArrayList<String> table = new ArrayList<String>();
			String bezahltS= "bezahlt";
			String unbezahltS = "ausstehend";
			
			if (kunde) {
				if (!bezahlt && !unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				if (bezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				
				if (unbezahlt) {
					kr.stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getKundenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
			}
			else {
				if (!bezahlt && !unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat  && x.getJahr() == jahr).forEach(x -> {
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				if (bezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat  && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
				
				if (unbezahlt) {
					lr.stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
						table.add(String.valueOf(x.getRechnungsID()));
						table.add(String.valueOf(x.getLieferantenID()));
						table.add(monate[x.getMonat()]);
						table.add(String.valueOf(x.getJahr()));
						table.add(String.valueOf(x.getSumme()) + "€");
						if (x.isStatus()) table.add(bezahltS);
						else table.add(unbezahltS);
					});
				}
			}
			return table;			
		}
	
		
	// ArrayListen sortieren
		
		private static void sortRechnungen (int sort, boolean zeitraum, boolean summe, boolean rechnung, boolean kunde, boolean isKunde, boolean isLieferant) {
			
			Comparator<objects.Rechnung> byZeitraum = (s1, s2) -> {
				if (sort == 1) return Integer.compare(s1.getJahr(),s2.getJahr()); 
				return Double.compare(s2.getJahr(),s1.getJahr()); 
			};
			
			if (zeitraum) {
				if (isLieferant) lr.sort(byZeitraum);
				if (isKunde) kr.sort(byZeitraum);
			}
			
			Comparator<objects.Rechnung> bySumme = (s1, s2) -> {
				if (sort == 1) return Double.compare(s1.getSumme(),s2.getSumme()); 
				return Double.compare(s2.getSumme(),s1.getSumme()); 
			};

			if (summe) {
				if (isLieferant) lr.sort(bySumme);
				if (isKunde) kr.sort(bySumme);
			}
		    
			Comparator<objects.Rechnung> byRechnung = (s1, s2) -> {
				if (sort == 1) return Integer.compare(s1.getRechnungsID(),s2.getRechnungsID()); 
				return Double.compare(s2.getRechnungsID(),s1.getRechnungsID()); 
			};
			
			if (rechnung) {
				if (isLieferant) lr.sort(byRechnung);
				if (isKunde) kr.sort(byRechnung);
			}
			
			Comparator<objects.Kundenrechnung> byKunde = (s1, s2) -> {
				if (sort == 1) return Integer.compare(s1.getKundenID(),s2.getKundenID()); 
				return Double.compare(s2.getKundenID(),s1.getKundenID()); 
			};
			
			Comparator<objects.Lieferantenrechnung> byLieferant = (s1, s2) -> {
				if (sort == 1) return Integer.compare(s1.getLieferantenID(),s2.getLieferantenID()); 
				return Double.compare(s2.getLieferantenID(),s1.getLieferantenID()); 
			};
			
			if (kunde) {
				if (isLieferant) lr.sort(byLieferant);
				if (isKunde) kr.sort(byKunde);
			}
			
		}
}