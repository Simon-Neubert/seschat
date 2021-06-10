package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JFrame;
import dbaccess.*;
import objects.*;
import javax.swing.JPanel;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;

import javax.swing.SwingConstants;
import javax.swing.JTable;

public class Auswertung extends JPanel{
	
	// Auslesen DB
	static ArrayList<objects.Kunde> k = dbaccess.DBAccess.createKunden();
	static ArrayList<objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	static ArrayList<objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
	static ArrayList<objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
	
	public Auswertung() {
		
		// Set Frame
		setBounds(2000, 2000, 2000, 2000);
		setLayout(null);
		
		fillYears();
		
		// Labels
		JLabel labelEinnahmen = new JLabel("Einnahmen ausgeben nach:");
		labelEinnahmen.setBounds(171, 60, 286, 32);
		labelEinnahmen.setFont(new Font("Serif", Font.PLAIN, 25));
		add(labelEinnahmen);
		
		JLabel labelBestellvolumen = new JLabel("Bestellvolumen ausgeben nach:");
		labelBestellvolumen.setFont(new Font("Serif", Font.PLAIN, 25));
		labelBestellvolumen.setBounds(1104, 60, 395, 32);
		add(labelBestellvolumen);
		
		JLabel labelJahresabschluss = new JLabel("Jahresabschluss:");
		labelJahresabschluss.setFont(new Font("Serif", Font.PLAIN, 25));
		labelJahresabschluss.setBounds(171, 416, 286, 32);
		add(labelJahresabschluss);
		
		// Textfelder Ausgabe
		einnahmenResultField = new JTextField();
		einnahmenResultField.setEditable(false);
		einnahmenResultField.setHorizontalAlignment(SwingConstants.CENTER);
		einnahmenResultField.setBounds(171, 318, 587, 53);
		einnahmenResultField.setBorder(new LineBorder(Color.BLACK, 1));
		einnahmenResultField.setColumns(10);
		add(einnahmenResultField);
		
		bestellvolumenResultField = new JTextField();
		bestellvolumenResultField.setEditable(false);
		bestellvolumenResultField.setHorizontalAlignment(SwingConstants.CENTER);
		bestellvolumenResultField.setColumns(10);
		bestellvolumenResultField.setBounds(1104, 318, 587, 53);
		bestellvolumenResultField.setBorder(new LineBorder(Color.BLACK, 1));
		add(bestellvolumenResultField);

		
		// Einnahmen Komponenten
		plzField = new JTextField();
		plzField.setHorizontalAlignment(SwingConstants.CENTER);
		plzField.setText("   Bitte PLZ eingeben...");
		plzField.setFont(new Font("Sans", Font.PLAIN, 14));
		plzField.setBounds(171, 217, 219, 53);
		plzField.setBorder(new LineBorder(Color.BLACK, 1));
		plzField.setColumns(10);
		plzField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {plzField.setText("");}
			public void focusLost(FocusEvent e) {}
		});
		add(plzField);
		
		JComboBox monatDropdownEinnahmen = new JComboBox(monate);
		monatDropdownEinnahmen.setMaximumRowCount(14);
		monatDropdownEinnahmen.setBounds(171, 118, 219, 78);
		add(monatDropdownEinnahmen);
		
		JComboBox jahrDropdownEinnahmen = new JComboBox(lastTenYears);
		jahrDropdownEinnahmen.setMaximumRowCount(14);
		jahrDropdownEinnahmen.setBounds(539, 118, 219, 78);
		add(jahrDropdownEinnahmen);
		
		JButton einnahmenButton = new JButton("Ausgeben");
		einnahmenButton.addMouseListener(new MouseAdapter() {
			// Ausgabe Einnahmen		
			public void mouseClicked(MouseEvent e) {
				
				fillYears();
				
				// Make sure input is valid
				String plzText = plzField.getText();
				if (plzText.equals("   Bitte PLZ eingeben...")) plzText = "0";
				if (!isInputValidPLZ(plzText) && !plzText.equals("0")) {
					einnahmenResultField.setText("Bitte Eingabe prüfen");
					return;
				}
				
				int plz = Integer.parseInt(plzText);
				int plzLength = plzField.getText().length();
				int zeitraumIndex = monatDropdownEinnahmen.getSelectedIndex();
				String zeitraum = String.valueOf(monatDropdownEinnahmen.getSelectedItem());
				String pending = "€  (Davon ausstehend: ";
				resetSumme();
				
				if (!checkIfTimeframeInputIsValid(zeitraumIndex, jahrDropdownEinnahmen.getSelectedIndex())) {
					einnahmenResultField.setText("Bitte Monat nur in Kombination mit Jahr auswählen");
					return;
				}
				
				int jahrIndex = jahrDropdownEinnahmen.getSelectedIndex();
				String jahr = lastTenYears[jahrIndex];
				
				// PLZ + Monat
				if (plzLength == 5 && zeitraumIndex > 0 && jahrIndex > 0) {
					einnahmenResultField.setText("Gesamteinnahmen im " + zeitraum + ", " + jahr + " aus " + plz + ": " + (double)einnahmenAusPLZInMonat(plz, zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					plzField.setText("   Bitte PLZ eingeben...");
					resetKunden();
					return;
				}
				// PLZ + Jahr (ohne Monat)
				if (plzLength == 5 && jahrIndex > 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Gesamteinnahmen in " + jahr + " aus " + plz + ": " + (double)einnahmenAusPLZImJahr(plz, Integer.parseInt(jahr))/100 + (pending) + (double)ausstehend/100 + "€)");
					plzField.setText("   Bitte PLZ eingeben...");
					resetKunden();
					return;
				}
				
				// Monat und Jahr ohne PLZ
				if (plzLength != 5 && zeitraumIndex > 0 && jahrIndex > 0) {
					einnahmenResultField.setText("Gesamteinnahmen im " + zeitraum + ", " + jahr + ": " + (double) einnahmenImMonat(zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Jahr ohne Monat
				if (plzLength != 5 && jahrIndex > 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Gesamteinnahmen in " + jahr + ": " + (double)einnahmenImJahr(Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Nur PLZ
				if (plzLength == 5 && jahrIndex == 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Gesamteinnahmen aus " + plz + ": " + (double)einnahmenAusPLZ(plz)/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Invalide Eingabe
				if (plzLength != 5 && zeitraumIndex == 0 && jahrIndex == 0) einnahmenResultField.setText("Bitte Auswahl prüfen");
				plzField.setText("   Bitte PLZ eingeben...");
				return;
			}
		});
		einnahmenButton.setBackground(new Color(30, 144, 255));
		einnahmenButton.setBounds(561, 220, 170, 50);
		add(einnahmenButton);
		
		
		//Bestellvolumen Komponenten
		// Lieferantenliste für Dropdown Menus
		String[] lieferanten = new String [l.toArray().length + 1];
		lieferanten [0] = "Bitte Lieferanten auswählen ...";
		for (counter = 1; counter <= l.toArray().length; counter++) l.stream().filter(x -> x.getLieferantenID() == counter).forEach(x -> lieferanten [counter] = x.getName());

		JComboBox monatDropdownBestellvolumen = new JComboBox(monate);
		monatDropdownBestellvolumen.setMaximumRowCount(14);
		monatDropdownBestellvolumen.setBounds(1104, 118, 219, 78);
		add(monatDropdownBestellvolumen);
		
		JComboBox jahrDropdownBestellvolumen = new JComboBox(lastTenYears);
		jahrDropdownBestellvolumen.setMaximumRowCount(14);
		jahrDropdownBestellvolumen.setBounds(1472, 118, 219, 78);
		add(jahrDropdownBestellvolumen);
		
		JComboBox lieferantDropdown = new JComboBox(lieferanten);
		lieferantDropdown.setMaximumRowCount(l.toArray().length + 1);
		lieferantDropdown.setBounds(1104, 206, 250, 78);
		add(lieferantDropdown);
		
		JButton bestellVolumenButton = new JButton("Ausgeben");
		bestellVolumenButton.setBackground(new Color(30, 144, 255));
		bestellVolumenButton.setBounds(1500, 220, 170, 50);
		bestellVolumenButton.addMouseListener(new MouseAdapter() {
		// Ausgabe Bestellvolumen
			public void mouseClicked(MouseEvent e) {
				
				int lieferantIndex = lieferantDropdown.getSelectedIndex();
				String lieferant = String.valueOf(lieferantDropdown.getSelectedItem());
				int zeitraumIndex = monatDropdownBestellvolumen.getSelectedIndex();
				String zeitraum = String.valueOf(monatDropdownBestellvolumen.getSelectedItem());
				String pending = "€  (Davon unbezahlt: ";
				
				// Check if input is valid
				if (!checkIfTimeframeInputIsValid(zeitraumIndex, jahrDropdownBestellvolumen.getSelectedIndex())) {
					bestellvolumenResultField.setText("Bitte Monat nur in Kombination mit Jahr auswählen");
					return;
				}
				int jahrIndex = jahrDropdownBestellvolumen.getSelectedIndex();
				String jahr = lastTenYears[jahrIndex];
				// Lieferant und Jahr ohne Monat
				if (lieferantIndex > 0 && zeitraumIndex == 0 && jahrIndex > 0) {
					bestellvolumenResultField.setText("Bestellvolummen in " + jahr + " bei " + lieferant + ": " + (double)volumenVonLieferantImJahr(lieferantIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)") ;	
					return;
				}
				// Lieferant und Monat + Jahr
				if (lieferantIndex > 0 && zeitraumIndex > 0) {
					bestellvolumenResultField.setText("Bestellvolummen im " + zeitraum + ", " + jahr + " bei " + lieferant + ": " + (double)volumenVonLieferantImMonat(lieferantIndex, zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)") ;
					return;
				}
				// Monat + Jahr
				if (zeitraumIndex > 0) bestellvolumenResultField.setText("Bestellvolumen im " + zeitraum + ", " + jahr + ": " + (double)volumenAusZeitraum(zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
				// Jahr ohne Monat
				if (jahrIndex > 0 && zeitraumIndex == 0) bestellvolumenResultField.setText("Bestellvolumen in " + jahr + ": " + (double)volumenAusJahr(Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
				// Lieferant 
				if (lieferantIndex > 0) bestellvolumenResultField.setText("Bestellvolumen bei " + lieferant + ": " + (double)volumenVonLieferant(lieferantIndex)/100 + pending + (double)ausstehend/100 + "€)");
				// Keine Auswahl
				if (zeitraumIndex == 0 && lieferantIndex == 0 && jahrIndex == 0) bestellvolumenResultField.setText("Bitte Auswahl prüfen");
				return;
			}
		});
		add(bestellVolumenButton);
		
		
		//Jahresabschluss Komponenten
		JComboBox jahrDropdownJahresabschluss = new JComboBox(lastTenYears);
		jahrDropdownJahresabschluss.setMaximumRowCount(14);
		jahrDropdownJahresabschluss.setBounds(172, 460, 219, 78);
		add(jahrDropdownJahresabschluss);
		
		JButton abchlussJahrButton = new JButton("Ausgeben");
		abchlussJahrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jahr = String.valueOf(jahrDropdownJahresabschluss.getSelectedItem());
				if (jahrDropdownJahresabschluss.getSelectedIndex() != 0) {
					resetCounter();
					ArrayList<String> list = createCVS(Integer.parseInt(jahr));
					String [][] table = new String [14][7];
					for (int i = 0; i < 14; i++) 
						for (int j = 0; j < 7; j++) {
							table[i][j] = list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(table, new Object[] { "Bezeichnung", "Forderungen", "Einnahmen", "Verbindlichkeiten", "Ausgaben", "Bilanz", "Saldo"});
					jahresAbschlussTable.setModel(tableModel);
					
				}
			}
		});
		abchlussJahrButton.setBackground(new Color(30, 144, 255));
		abchlussJahrButton.setBounds(471, 474, 170, 50);
		add(abchlussJahrButton);
		
		JButton speichernButton = new JButton("Speichern");
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.setBounds(740, 474, 170, 50);
		speichernButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jahr = String.valueOf(jahrDropdownJahresabschluss.getSelectedItem());
				if (jahrDropdownJahresabschluss.getSelectedIndex() != 0) {
					exportToCSV(createCVS(Integer.parseInt(jahr)));
				}
			}
		});
		add(speichernButton);

		jahresAbschlussTable = new JTable();
		jahresAbschlussTable.setEnabled(false);
		jahresAbschlussTable.setBounds(171, 550, 1519, 420);
		jahresAbschlussTable.setBorder(new LineBorder(Color.BLACK, 2));
		jahresAbschlussTable.setFillsViewportHeight(true);
		jahresAbschlussTable.setShowHorizontalLines(true);
		jahresAbschlussTable.setShowVerticalLines(true);
		jahresAbschlussTable.setGridColor(Color.LIGHT_GRAY);
		jahresAbschlussTable.setRowHeight(30);
		add(jahresAbschlussTable);
		
	}

	// Globale Hilfsvariablen
	private static int summe;
	private static int ausstehend;
	private static int[] kunden = new int [k.toArray().length];
	private static int counter = 0;
	
	// Monatsliste für Dropdown Menus
	static String[] monate = {"Bitte Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String[] lastTenYears = new String[11];
	
	// Textfelder
	private JTextField plzField;
	private JTextField einnahmenResultField;
	private JTextField bestellvolumenResultField;
	private JTable jahresAbschlussTable;
	
	// Hilfsfunktionen
	private static void resetSumme () {
		summe = 0;
	}
	private static void resetCounter () {
		counter = 0;
	}
	private static void resetAusstehend () {
		ausstehend = 0;
	}
	private static void resetKunden () {
		Arrays.fill(kunden, 0);
	}
	private static void fillYears () {
		lastTenYears[0] = "Bitte Jahr auswählen ...";
		for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
	}
	private static void checkStatus (boolean status, double debt) {
		if (!status) ausstehend += (int)(debt*100);
	}
	private static boolean checkIfTimeframeInputIsValid (int monat, int jahr) {
		if (monat != 0) {
			if (monat != 0 && jahr == 0) return false;
		}
		return true;
	}
	private static boolean isInputValidPLZ(String plz) {
		if (plz.matches("[0-9]+") && plz.length() == 5) return true;
		return false;
	}
	
	
	// Auswertung Einnahmen
	
	private static int einnahmenImMonat (int monat, int jahr) {
		resetSumme();
		resetAusstehend();
		kr.stream().filter(x -> x.getMonat()== monat && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}

	private static int einnahmenImJahr (int jahr) {
		resetSumme();
		resetAusstehend();
		kr.stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}
	
	private static void sortByPLZ (int plz) {
		k.stream().filter(x -> x.getPlz() == plz).forEach(x -> { 
			resetCounter();
			while (counter < kunden.length) {
				if (kunden[counter] == 0) {
					kunden[counter] = x.getKundenID();
					break;
				}
				counter++;
			}
		});
		resetCounter();
	}
	
	private static int einnahmenAusPLZ (int plz) {
		sortByPLZ(plz);
		resetSumme();
		resetAusstehend();
		while (kunden[counter] != 0) {
			kr.stream().filter(x -> x.getKundenID()== kunden[counter]).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			counter++;
		}
		return summe;
	}
	
	private static int einnahmenAusPLZInMonat (int plz, int monat, int jahr) {
		sortByPLZ(plz);
		resetSumme();
		resetAusstehend();
		while (kunden[counter] != 0) {
			kr.stream().filter(x -> x.getKundenID()== kunden[counter] && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			counter++;
		}
		return summe;
	}
	
	private static int einnahmenAusPLZImJahr (int plz, int jahr) {
		sortByPLZ(plz);
		resetSumme();
		resetAusstehend();
		while (kunden[counter] != 0) {
			kr.stream().filter(x -> x.getKundenID()== kunden[counter] && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			counter++;
		}
		return summe;
	}
	
	
	// Auswertung Bestellvolumen
	
	private static int volumenVonLieferant (int lieferant) {
		resetSumme();
		resetAusstehend();
		lr.stream().filter(x -> x.getLieferantenID() == lieferant).forEach(x -> {
		summe += (int)(x.getSumme()*100);
		checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}
	
	private static int volumenAusZeitraum (int monat, int jahr) {
		resetSumme();
		resetAusstehend();
		lr.stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
			summe += (int)(x.getSumme()*100);
			checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}
	
	private static int volumenAusJahr (int jahr) {
		resetSumme();
		resetAusstehend();
		lr.stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
			summe += (int)(x.getSumme()*100);
			checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}
	
	private static int volumenVonLieferantImJahr (int lieferant, int jahr) {
		resetSumme();
		resetAusstehend();
		lr.stream().filter(x -> x.getLieferantenID() == lieferant && x.getJahr() == jahr).forEach(x -> {
			summe += (int)(x.getSumme()*100);
			checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}

	private static int volumenVonLieferantImMonat (int lieferant, int monat, int jahr) {
		resetSumme();
		resetAusstehend();
		lr.stream().filter(x -> x.getLieferantenID() == lieferant && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
			summe += (int)(x.getSumme()*100);
			checkStatus(x.isStatus(), x.getSumme());
		});
		return summe;
	}
	
	// Auswertung Jahresabschluss
	
	private static ArrayList<String> createCVS(int jahr) {
		
		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {" Jahresabschluss", "Forderungen", "Einnahmen", "Verbindlichkeiten", "Ausgaben", "Bilanzsumme", "Saldo"};
		table.addAll(Arrays.asList(spalten));
		resetSumme();
		resetAusstehend();
		
		int summeForderungen = 0, summeEinnahmen = 0, summeVerbindlichkeiten = 0, summeAusgaben = 0, summeBilanz = 0, summeSaldo = 0;
		for (int i = 1; i < 13; i++) {
			
			int forderungen = einnahmenImMonat(i, jahr);
			int einnahmen = forderungen - ausstehend;
			int verbindlichkeiten = volumenAusZeitraum(i, jahr);
			int ausgaben = verbindlichkeiten - ausstehend;
			int bilanz = forderungen - verbindlichkeiten;
			int saldo = einnahmen - ausgaben;

			summeForderungen += forderungen;
			summeEinnahmen += einnahmen;
			summeVerbindlichkeiten += verbindlichkeiten;
			summeAusgaben += ausgaben;
			summeBilanz += bilanz;
			summeSaldo += saldo;
			
			String [] hold = {" " + monate[i], doubleAsString(forderungen), doubleAsString(einnahmen), doubleAsString(verbindlichkeiten), doubleAsString(ausgaben), doubleAsString(bilanz), doubleAsString(saldo)};
			addEuro(hold);
			table.addAll(Arrays.asList(hold));
		}
		String [] hold = {" Summe", doubleAsString(summeForderungen), doubleAsString(summeEinnahmen), doubleAsString(summeVerbindlichkeiten), doubleAsString(summeAusgaben), doubleAsString(summeBilanz), doubleAsString(summeSaldo)};
		addEuro(hold);
		table.addAll(Arrays.asList(hold));
		return table;
	}
	
	// Hilfsfunktionen
	private static String doubleAsString (int i) {
		return String.valueOf((double)i/100);
	}
	
	private static void addEuro (String [] s) {
		for (int i = 1; i < s.length; i++) s[i] += "€";
	}
	
	private static void exportToCSV(ArrayList<String> list) {
		
		StringBuilder str = new StringBuilder("");
		int i = 1; 
	    for (String s : list) {
	    	if (i % 7 == 0) str.append(s).append("\n");
	    	else str.append(s).append(",");
	    	i++;
	    }
		
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(null);
		
		try(FileWriter fw = new FileWriter(fc.getSelectedFile() + ".csv")) {
		    fw.write(str.toString());
		}
		catch (Exception e) {e.printStackTrace();}
		
	}
}