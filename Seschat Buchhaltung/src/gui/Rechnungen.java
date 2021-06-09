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
		
	// Components for GUI

		private JTextField vornameNeuField;
		private JTextField generiertField;
		private JTextField vornameSuchenField;
		private JTextField idSuchenField;
		private JTable table;
		private JTextField idBearbeitenFeld;
		private JTextField summeNeuField;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private final ButtonGroup buttonGroup_1 = new ButtonGroup();
		private final ButtonGroup buttonGroup_2 = new ButtonGroup();
		private final ButtonGroup buttonGroup_3 = new ButtonGroup();
		private final ButtonGroup buttonGroup_4 = new ButtonGroup();

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

			
			JComboBox monatDropdownSuchen = new JComboBox(new Object[]{});
			monatDropdownSuchen.setMaximumRowCount(14);
			monatDropdownSuchen.setBounds(585, 149, 181, 78);
			add(monatDropdownSuchen);
			
			JComboBox jahrDropdownSuchen = new JComboBox(new Object[]{});
			jahrDropdownSuchen.setMaximumRowCount(14);
			jahrDropdownSuchen.setBounds(873, 149, 181, 78);
			add(jahrDropdownSuchen);
			
			JLabel lblNewLabel = new JLabel("Sortieren nach:");
			lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
			lblNewLabel.setBounds(265, 385, 132, 26);
			add(lblNewLabel);
			
			JRadioButton summeRadio = new JRadioButton("Summe");
			buttonGroup_3.add(summeRadio);
			summeRadio.setBounds(636, 385, 78, 23);
			add(summeRadio);
			
			JRadioButton zeitraumRadio = new JRadioButton("Zeitraum");
			buttonGroup_3.add(zeitraumRadio);
			zeitraumRadio.setBounds(636, 436, 92, 23);
			add(zeitraumRadio);
			
			JComboBox sortierenBox = new JComboBox();
			sortierenBox.setMaximumRowCount(2);
			sortierenBox.setBounds(1026, 386, 181, 27);
			add(sortierenBox);
			
			JRadioButton statusRadioSuchen = new JRadioButton("Bezahlt");
			buttonGroup_2.add(statusRadioSuchen);
			statusRadioSuchen.setBounds(419, 385, 92, 26);
			add(statusRadioSuchen);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Bezahlt");
			buttonGroup_1.add(rdbtnNewRadioButton);
			rdbtnNewRadioButton.setBounds(585, 268, 141, 23);
			add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Rechnungs-ID");
			buttonGroup_3.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.setBounds(809, 435, 132, 23);
			add(rdbtnNewRadioButton_1);
			
			JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Kunden-ID");
			buttonGroup_3.add(rdbtnNewRadioButton_1_1);
			rdbtnNewRadioButton_1_1.setBounds(809, 385, 119, 23);
			add(rdbtnNewRadioButton_1_1);
			
			JRadioButton rdbtnUnbezahlt = new JRadioButton("Unbezahlt");
			buttonGroup_1.add(rdbtnUnbezahlt);
			rdbtnUnbezahlt.setBounds(873, 268, 141, 23);
			add(rdbtnUnbezahlt);
			
			JRadioButton rdbtnUezahlt = new JRadioButton("Unbezahlt");
			buttonGroup_2.add(rdbtnUezahlt);
			rdbtnUezahlt.setBounds(419, 436, 119, 26);
			add(rdbtnUezahlt);
			
			JRadioButton kundeRadio = new JRadioButton("Kunde");
			buttonGroup.add(kundeRadio);
			kundeRadio.setBounds(585, 98, 141, 23);
			add(kundeRadio);
			
			JRadioButton lieferantRadio = new JRadioButton("Lieferant");
			buttonGroup.add(lieferantRadio);
			lieferantRadio.setBounds(873, 98, 141, 23);
			add(lieferantRadio);
			
			vornameSuchenField = new JTextField();
			vornameSuchenField.setText("Kunden-ID eingeben...");
			vornameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
			vornameSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
			vornameSuchenField.setColumns(10);
			vornameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
			vornameSuchenField.setBounds(265, 253, 219, 53);
			vornameSuchenField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					vornameSuchenField.setText("");
				}

				public void focusLost(FocusEvent e) {
				}
			});
			add(vornameSuchenField);
			
			idSuchenField = new JTextField();
			idSuchenField.setText("Rechnungs-ID eingeben...");
			idSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
			idSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
			idSuchenField.setColumns(10);
			idSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
			idSuchenField.setBounds(265, 160, 219, 53);
			idSuchenField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					idSuchenField.setText("");
				}
				public void focusLost(FocusEvent e) {
				}
			});
			add(idSuchenField);
			
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
			suchenButton.setBounds(1026, 424, 181, 50);
			suchenButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
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

			JLabel changeLabel = new JLabel("Eingabe pruefen");
			changeLabel.setEnabled(false);
			changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			changeLabel.setForeground(Color.RED);
			changeLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			changeLabel.setBounds(1365, 453, 210, 26);
			add(changeLabel);
			
			JRadioButton kundeRadioBearbeiten = new JRadioButton("Kunde");
			buttonGroup_4.add(kundeRadioBearbeiten);
			kundeRadioBearbeiten.setBounds(1356, 283, 119, 23);
			add(kundeRadioBearbeiten);
			
			JRadioButton lieferantRadioBearbeiten = new JRadioButton("Lieferant");
			buttonGroup_4.add(lieferantRadioBearbeiten);
			lieferantRadioBearbeiten.setBounds(1484, 283, 91, 23);
			add(lieferantRadioBearbeiten);
			
			JButton bearbeitenButton = new JButton("Bearbeiten");
			bearbeitenButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			bearbeitenButton.setForeground(new Color(30, 144, 255));
			bearbeitenButton.setBackground(new Color(30, 144, 255));
			bearbeitenButton.setBounds(1382, 339, 170, 50);
			bearbeitenButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					String idInput = idBearbeitenFeld.getText();
					idBearbeitenFeld.setText("   Bitte ID eingeben...");

					// Check User Input
					if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
						setErrMessage(changeLabel);
						return;
					}

					if (kundeRadioBearbeiten.isSelected() && Integer.parseInt(idInput) > kr.toArray().length) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("ID existiert nicht.");
						return;
					}

					if (lieferantRadioBearbeiten.isSelected() && Integer.parseInt(idInput) > lr.toArray().length) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("ID existiert nicht.");
						return;
					}
					
					if (!lieferantRadioBearbeiten.isSelected() && !kundeRadioBearbeiten.isSelected()) {
						changeLabel.setForeground(Color.RED);
						changeLabel.setText("Bitte Rechnungstyp angeben.");
						return;
					}
					
					int id = Integer.parseInt(idInput);

					try {
						if (kundeRadioBearbeiten.isSelected()) {
							popups.KundenRechnungBearbeiten dialog = new popups.KundenRechnungBearbeiten(kr.get(id).getKundenID(), getVorname(id), getNachname(id), getPLZ(id), id);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									changeLabel.setForeground(Color.BLACK);
									if (!abgebrochen) changeLabel.setText("Kunde bearbeitet.");
								}
							});
						}
						
						if (lieferantRadioBearbeiten.isSelected()) {
							popups.LieferantenRechnungBearbeiten dialog = new popups.LieferantenRechnungBearbeiten(lr.get(id).getLieferantenID(), getName(lr.get(id).getLieferantenID()), id);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.addWindowListener(new WindowAdapter() {
								public void windowClosed(WindowEvent e) {
									changeLabel.setForeground(Color.BLACK);
									if (!abgebrochen) changeLabel.setText("Kunde bearbeitet.");
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

		}


	// Edit invoice

		// Add invoive to objects and call dbAddRechnung
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
				stmt.execute("UPDATE kundenrechnungen  SET monat = '" + monat + "', jahr = '" + jahr + "', summe = '" + bestellvolumen + "', status = '"+ tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Add invoive to objects and call dbAddRechnung
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
				stmt.execute("UPDATE lieferantenrechnungen  SET monat = '" + monat + "', jahr = '" + jahr + "', summe = '" + bestellvolumen + "', status = '" + tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
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
		private static void resetFields (JTextField vornameSuchenField, JTextField nachnameSuchenField, JTextField plzSuchenField, JTextField idSuchenField) {
			vornameSuchenField.setText("   Vorname eingeben...");
			nachnameSuchenField.setText("   Nachname eingeben...");
			plzSuchenField.setText("   PLZ eingeben...");
			idSuchenField.setText("   Bitte ID eingeben...");
		}

		
	// Tabellen erstellen (suchen)

		private static void addToTable (ArrayList<String> table, int kundenID, String vorname, String nachname, int plz) {
			table.add(String.valueOf(kundenID));
			table.add(vorname);
			table.add(nachname);
			table.add(String.valueOf(plz));
		}
		
		// Create table if user only inputs first name
		private static ArrayList<String> tableBy(String vorname) {

			ArrayList<String> table = new ArrayList<String>();

			k.stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase())).forEach(x -> {
				table.add(String.valueOf(x.getKundenID()));
				table.add(x.getVorname());
				table.add(x.getNachname());
				table.add(String.valueOf(x.getPlz()));
			});

			return table;
		}

		// Create table if user doesnt input anything
		private static ArrayList<String> tableOfAll() {

			ArrayList<String> table = new ArrayList<String>();

			k.stream().forEach(x -> {
				table.add(String.valueOf(x.getKundenID()));
				table.add(x.getVorname());
				table.add(x.getNachname());
				table.add(String.valueOf(x.getPlz()));
			});

			return table;
		}

		private static void fillYears () {
			lastTenYears[0] = "Jahr auswählen ...";
			for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
		}
}