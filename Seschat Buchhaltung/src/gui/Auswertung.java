package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbaccess.DBAccess;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Auswertung extends JPanel {

	// Monatsliste für Dropdown Menus
	static String[] monate = {"Bitte Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String[] lastTenYears = new String[11];
	
	// Globale Hilfsvariablen
	private static int summe;
	private static int ausstehend;
	private static int[] kunden = new int [DBAccess.getK().toArray().length];
	private static int counter = 0;
	private JTextField plzField;
	private JTextField einnahmenResultField;
	private JTable jahresAbschlussTable;
	private JTextField bestellvolumenResultField;
	
	public Auswertung() {
		
		setBounds(0, 0, 1280, 720);
		
		fillYears();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 310, 202, 0, 310, 138, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		
		// Labels
		JLabel labelEinnahmen = new JLabel("Einnahmen ausgeben:");
		labelEinnahmen.setFont(new Font("Serif", Font.PLAIN, 25));
		GridBagConstraints gbc_labelEinnahmen = new GridBagConstraints();
		gbc_labelEinnahmen.fill = GridBagConstraints.VERTICAL;
		gbc_labelEinnahmen.anchor = GridBagConstraints.WEST;
		gbc_labelEinnahmen.insets = new Insets(0, 0, 5, 5);
		gbc_labelEinnahmen.gridx = 1;
		gbc_labelEinnahmen.gridy = 1;
		add(labelEinnahmen, gbc_labelEinnahmen);
		
		JLabel labelBestellvolumen = new JLabel("Bestellvolumen ausgeben:");
		labelBestellvolumen.setFont(new Font("Serif", Font.PLAIN, 25));
		GridBagConstraints gbc_labelBestellvolumen = new GridBagConstraints();
		gbc_labelBestellvolumen.fill = GridBagConstraints.VERTICAL;
		gbc_labelBestellvolumen.anchor = GridBagConstraints.WEST;
		gbc_labelBestellvolumen.insets = new Insets(0, 0, 5, 5);
		gbc_labelBestellvolumen.gridx = 4;
		gbc_labelBestellvolumen.gridy = 1;
		add(labelBestellvolumen, gbc_labelBestellvolumen);
		
		JLabel labelJahresabschluss = new JLabel("Jahresabschluss:");
		labelJahresabschluss.setHorizontalAlignment(SwingConstants.LEFT);
		labelJahresabschluss.setFont(new Font("Serif", Font.PLAIN, 25));
		GridBagConstraints gbc_labelJahresabschluss = new GridBagConstraints();
		gbc_labelJahresabschluss.fill = GridBagConstraints.VERTICAL;
		gbc_labelJahresabschluss.anchor = GridBagConstraints.WEST;
		gbc_labelJahresabschluss.insets = new Insets(0, 0, 5, 5);
		gbc_labelJahresabschluss.gridx = 1;
		gbc_labelJahresabschluss.gridy = 9;
		add(labelJahresabschluss, gbc_labelJahresabschluss);
		
		
		// Einnahmen Komponenten
		JComboBox monatDropdownEinnahmen = new JComboBox(monate);
		GridBagConstraints gbc_monatDropdownEinnahmen = new GridBagConstraints();
		gbc_monatDropdownEinnahmen.fill = GridBagConstraints.BOTH;
		gbc_monatDropdownEinnahmen.insets = new Insets(0, 0, 5, 5);
		gbc_monatDropdownEinnahmen.gridx = 1;
		gbc_monatDropdownEinnahmen.gridy = 3;
		monatDropdownEinnahmen.setMaximumRowCount(14);
		add(monatDropdownEinnahmen, gbc_monatDropdownEinnahmen);

		JComboBox jahrDropdownEinnahmen = new JComboBox(lastTenYears);
		GridBagConstraints gbc_jahrDropdownEinnahmen = new GridBagConstraints();
		gbc_jahrDropdownEinnahmen.fill = GridBagConstraints.BOTH;
		gbc_jahrDropdownEinnahmen.insets = new Insets(0, 0, 5, 5);
		gbc_jahrDropdownEinnahmen.gridx = 1;
		gbc_jahrDropdownEinnahmen.gridy = 5;
		jahrDropdownEinnahmen.setMaximumRowCount(14);
		add(jahrDropdownEinnahmen, gbc_jahrDropdownEinnahmen);

		plzField = new JTextField();
		plzField.setHorizontalAlignment(SwingConstants.CENTER);
		plzField.setFont(new Font("Sans", Font.PLAIN, 14));
		plzField.setBorder(new LineBorder(Color.BLACK, 1));
		plzField.setText("Bitte PLZ eingeben...");
		GridBagConstraints gbc_plzField = new GridBagConstraints();
		gbc_plzField.fill = GridBagConstraints.BOTH;
		gbc_plzField.insets = new Insets(0, 0, 5, 5);
		gbc_plzField.gridx = 2;
		gbc_plzField.gridy = 3;
		plzField.setMaximumSize(new Dimension (219, 53));
		plzField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {plzField.setText("");}
			public void focusLost(FocusEvent e) {}
		});
		add(plzField, gbc_plzField);
		
		
		JButton einnahmenButton = new JButton("Ausgeben");
		GridBagConstraints gbc_einnahmenButton = new GridBagConstraints();
		gbc_einnahmenButton.fill = GridBagConstraints.BOTH;
		gbc_einnahmenButton.insets = new Insets(0, 0, 5, 5);
		gbc_einnahmenButton.gridx = 2;
		gbc_einnahmenButton.gridy = 5;
		einnahmenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				
				String plzText = plzField.getText();
				
				// Make sure input is valid
				if (plzText.equals("Bitte PLZ eingeben...")) plzText = "0";
				if (!isInputValidPLZ(plzText) && !plzText.equals("0") && !plzText.equals("")) {
					einnahmenResultField.setText("Bitte Eingabe prüfen");
					return;
				}
				
				int plz = 0;
				if (!plzText.equals("")) plz = Integer.parseInt(plzText);
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
					einnahmenResultField.setText("Einnahmen im " + zeitraum + ", " + jahr + " aus " + plz + ": " + (double)einnahmenAusPLZInMonat(plz, zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					plzField.setText("Bitte PLZ eingeben...");
					resetKunden();
					return;
				}
				// PLZ + Jahr (ohne Monat)
				if (plzLength == 5 && jahrIndex > 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Einnahmen in " + jahr + " aus " + plz + ": " + (double)einnahmenAusPLZImJahr(plz, Integer.parseInt(jahr))/100 + (pending) + (double)ausstehend/100 + "€)");
					plzField.setText("Bitte PLZ eingeben...");
					resetKunden();
					return;
				}
				
				// Monat und Jahr ohne PLZ
				if (plzLength != 5 && zeitraumIndex > 0 && jahrIndex > 0) {
					einnahmenResultField.setText("Einnahmen im " + zeitraum + ", " + jahr + ": " + (double) einnahmenImMonat(zeitraumIndex, Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Jahr ohne Monat
				if (plzLength != 5 && jahrIndex > 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Einnahmen in " + jahr + ": " + (double)einnahmenImJahr(Integer.parseInt(jahr))/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Nur PLZ
				if (plzLength == 5 && jahrIndex == 0 && zeitraumIndex == 0) {
					einnahmenResultField.setText("Einnahmen aus " + plz + ": " + (double)einnahmenAusPLZ(plz)/100 + pending + (double)ausstehend/100 + "€)");
					resetKunden();
					return;
				}
				// Invalide Eingabe
				if (plzLength != 5 && zeitraumIndex == 0 && jahrIndex == 0) einnahmenResultField.setText("Bitte Auswahl prüfen");
				plzField.setText("Bitte PLZ eingeben...");
				return;
			}
		});
		add(einnahmenButton, gbc_einnahmenButton);

		einnahmenResultField = new JTextField();
		einnahmenResultField.setHorizontalAlignment(SwingConstants.CENTER);
		einnahmenResultField.setEditable(false);
		einnahmenResultField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_einnahmenResultField = new GridBagConstraints();
		gbc_einnahmenResultField.gridwidth = 2;
		gbc_einnahmenResultField.insets = new Insets(0, 0, 5, 5);
		gbc_einnahmenResultField.fill = GridBagConstraints.BOTH;
		gbc_einnahmenResultField.gridx = 1;
		gbc_einnahmenResultField.gridy = 7;
		add(einnahmenResultField, gbc_einnahmenResultField);
		einnahmenResultField.setColumns(10);
		
		
		// Lieferanten for Dropdowns
		String[] lieferanten = new String [DBAccess.getL().toArray().length + 1];
		lieferanten [0] = "Bitte Lieferanten auswählen ...";
		for (counter = 1; counter <= DBAccess.getL().toArray().length; counter++) DBAccess.getL().stream().filter(x -> x.getLieferantenID() == counter).forEach(x -> lieferanten [counter] = x.getName());
		
		
		// Bestellvolumen Komponenten
		JComboBox monatDropdownBestellvolumen = new JComboBox(monate);
		GridBagConstraints gbc_monatDropdownBestellvolumen = new GridBagConstraints();
		gbc_monatDropdownBestellvolumen.fill = GridBagConstraints.BOTH;
		gbc_monatDropdownBestellvolumen.insets = new Insets(0, 0, 5, 5);
		gbc_monatDropdownBestellvolumen.gridx = 4;
		gbc_monatDropdownBestellvolumen.gridy = 3;
		monatDropdownBestellvolumen.setMaximumRowCount(14);
		add(monatDropdownBestellvolumen, gbc_monatDropdownBestellvolumen);
		
		JComboBox jahrDropdownBestellvolumen = new JComboBox(lastTenYears);
		GridBagConstraints gbc_jahrDropdownBestellvolumen = new GridBagConstraints();
		gbc_jahrDropdownBestellvolumen.fill = GridBagConstraints.HORIZONTAL;
		gbc_jahrDropdownBestellvolumen.insets = new Insets(0, 0, 5, 5);
		gbc_jahrDropdownBestellvolumen.gridx = 4;
		gbc_jahrDropdownBestellvolumen.gridy = 5;
		add(jahrDropdownBestellvolumen, gbc_jahrDropdownBestellvolumen);
		
		JComboBox lieferantDropdown = new JComboBox(lieferanten);
		lieferantDropdown.setMaximumRowCount(DBAccess.getL().toArray().length + 1);
		GridBagConstraints gbc_lieferantDropdown = new GridBagConstraints();
		gbc_lieferantDropdown.insets = new Insets(0, 0, 5, 5);
		gbc_lieferantDropdown.fill = GridBagConstraints.BOTH;
		gbc_lieferantDropdown.gridx = 5;
		gbc_lieferantDropdown.gridy = 3;
		add(lieferantDropdown, gbc_lieferantDropdown);
		
		JButton bestellVolumenButton = new JButton("Ausgeben");
		GridBagConstraints gbc_bestellVolumenButton = new GridBagConstraints();
		gbc_bestellVolumenButton.fill = GridBagConstraints.BOTH;
		gbc_bestellVolumenButton.insets = new Insets(0, 0, 5, 5);
		gbc_bestellVolumenButton.gridx = 5;
		gbc_bestellVolumenButton.gridy = 5;
		bestellVolumenButton.addMouseListener(new MouseAdapter() {
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
					bestellvolumenResultField.setText("Bestellvolummen in " + jahr + " bei " + lieferant + ": " + (double) volumenVonLieferantImJahr(lieferantIndex, Integer.parseInt(jahr)) / 100 + pending + (double) ausstehend / 100 + "€)");
					return;
				}
				
				// Lieferant und Monat + Jahr
				if (lieferantIndex > 0 && zeitraumIndex > 0) {
					bestellvolumenResultField.setText("Bestellvolummen im " + zeitraum + ", " + jahr + " bei " + lieferant + ": " + (double) volumenVonLieferantImMonat(lieferantIndex, zeitraumIndex, Integer.parseInt(jahr)) / 100 + pending + (double) ausstehend / 100 + "€)");
					return;
				}
				
				// Monat + Jahr
				if (zeitraumIndex > 0)
					bestellvolumenResultField.setText("Bestellvolumen im " + zeitraum + ", " + jahr + ": " + (double) volumenAusZeitraum(zeitraumIndex, Integer.parseInt(jahr)) / 100 + pending + (double) ausstehend / 100 + "€)");
				
				// Jahr ohne Monat
				if (jahrIndex > 0 && zeitraumIndex == 0)
					bestellvolumenResultField.setText("Bestellvolumen in " + jahr + ": " + (double) volumenAusJahr(Integer.parseInt(jahr)) / 100 + pending + (double) ausstehend / 100 + "€)");
				
				// Lieferant
				if (lieferantIndex > 0)
					bestellvolumenResultField.setText("Bestellvolumen bei " + lieferant + ": " + (double) volumenVonLieferant(lieferantIndex) / 100 + pending + (double) ausstehend / 100 + "€)");
				
				// Keine Auswahl
				if (zeitraumIndex == 0 && lieferantIndex == 0 && jahrIndex == 0)
					bestellvolumenResultField.setText("Bitte Auswahl prüfen");
				return;
			}
			});
		add(bestellVolumenButton, gbc_bestellVolumenButton);
		
		bestellvolumenResultField = new JTextField();
		bestellvolumenResultField.setHorizontalAlignment(SwingConstants.CENTER);
		bestellvolumenResultField.setEditable(false);
		bestellvolumenResultField.setBorder(new LineBorder(Color.BLACK, 1));
		bestellvolumenResultField.setColumns(10);
		GridBagConstraints gbc_bestellvolumenResultField = new GridBagConstraints();
		gbc_bestellvolumenResultField.gridwidth = 2;
		gbc_bestellvolumenResultField.insets = new Insets(0, 0, 5, 5);
		gbc_bestellvolumenResultField.fill = GridBagConstraints.BOTH;
		gbc_bestellvolumenResultField.gridx = 4;
		gbc_bestellvolumenResultField.gridy = 7;
		add(bestellvolumenResultField, gbc_bestellvolumenResultField);
		
		
		// Jahresabschluss Komponenten
		JComboBox jahrDropdownJahresabschluss = new JComboBox(lastTenYears);
		GridBagConstraints gbc_jahrDropdownJahresabschluss = new GridBagConstraints();
		gbc_jahrDropdownJahresabschluss.insets = new Insets(0, 0, 5, 5);
		gbc_jahrDropdownJahresabschluss.fill = GridBagConstraints.HORIZONTAL;
		gbc_jahrDropdownJahresabschluss.gridx = 1;
		gbc_jahrDropdownJahresabschluss.gridy = 10;
		add(jahrDropdownJahresabschluss, gbc_jahrDropdownJahresabschluss);
		
		JButton speichernButton = new JButton("Speichern");
		GridBagConstraints gbc_speichernButton = new GridBagConstraints();
		gbc_speichernButton.fill = GridBagConstraints.BOTH;
		gbc_speichernButton.insets = new Insets(0, 0, 5, 5);
		gbc_speichernButton.gridx = 3;
		gbc_speichernButton.gridy = 10;
		jahrDropdownJahresabschluss.setMaximumRowCount(14);
		speichernButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jahr = String.valueOf(jahrDropdownJahresabschluss.getSelectedItem());
				if (jahrDropdownJahresabschluss.getSelectedIndex() != 0) {
					exportToCSV(createCVS(Integer.parseInt(jahr)));
				}
			}
		});
		add(speichernButton, gbc_speichernButton);
		
		JButton abschlussJahrButton = new JButton("Ausgeben");
		GridBagConstraints gbc_abschlussJahrButton = new GridBagConstraints();
		gbc_abschlussJahrButton.fill = GridBagConstraints.BOTH;
		gbc_abschlussJahrButton.insets = new Insets(0, 0, 5, 5);
		gbc_abschlussJahrButton.gridx = 2;
		gbc_abschlussJahrButton.gridy = 10;
		abschlussJahrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jahr = String.valueOf(jahrDropdownJahresabschluss.getSelectedItem());
				if (jahrDropdownJahresabschluss.getSelectedIndex() != 0) {
					resetCounter();
					ArrayList<String> list = createCVS(Integer.parseInt(jahr));
					String [][] table = new String [13][7];
					for (int i = 0; i < 13; i++) 
						for (int j = 0; j < 7; j++) {
							table[i][j] = list.get(counter);
							counter++;
						}
					
					DefaultTableModel tableModel = new DefaultTableModel(table, new Object[] { "Monat", "Forderungen", "Einnahmen", "Verbindlichkeiten", "Ausgaben", "Bilanz", "Saldo"});
					jahresAbschlussTable.setModel(tableModel);
					
				}
			}
		});
		add(abschlussJahrButton, gbc_abschlussJahrButton);
		
		jahresAbschlussTable = new JTable();
		jahresAbschlussTable.setEnabled(false);
		jahresAbschlussTable.setBorder(new LineBorder(Color.BLACK, 2));
		jahresAbschlussTable.setFillsViewportHeight(true);
		jahresAbschlussTable.setShowHorizontalLines(true);
		jahresAbschlussTable.setShowVerticalLines(true);
		jahresAbschlussTable.setRowHeight(30);
		jahresAbschlussTable.setGridColor(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane(jahresAbschlussTable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		add(scrollPane, gbc_scrollPane);
	
		
	}
	
	
	// Hilfsfunktionen
		private static void fillYears () {
			lastTenYears[0] = "Bitte Jahr auswählen ...";
			for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
		}
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
			DBAccess.getKr().stream().filter(x -> x.getMonat()== monat && x.getJahr() == jahr).forEach(x -> {
					summe += (int)(x.getSumme()*100);
					checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}

		private static int einnahmenImJahr (int jahr) {
			resetSumme();
			resetAusstehend();
			DBAccess.getKr().stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
					summe += (int)(x.getSumme()*100);
					checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}
		
		private static void sortByPLZ (int plz) {
			DBAccess.getK().stream().filter(x -> x.getPlz() == plz).forEach(x -> { 
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID()== kunden[counter]).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID()== kunden[counter] && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID()== kunden[counter] && x.getJahr() == jahr).forEach(x -> {
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
			DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == lieferant).forEach(x -> {
			summe += (int)(x.getSumme()*100);
			checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}
		
		private static int volumenAusZeitraum (int monat, int jahr) {
			resetSumme();
			resetAusstehend();
			DBAccess.getLr().stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}
		
		private static int volumenAusJahr (int jahr) {
			resetSumme();
			resetAusstehend();
			DBAccess.getLr().stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}
		
		private static int volumenVonLieferantImJahr (int lieferant, int jahr) {
			resetSumme();
			resetAusstehend();
			DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == lieferant && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}

		private static int volumenVonLieferantImMonat (int lieferant, int monat, int jahr) {
			resetSumme();
			resetAusstehend();
			DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == lieferant && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
				summe += (int)(x.getSumme()*100);
				checkStatus(x.isStatus(), x.getSumme());
			});
			return summe;
		}
		
		// Auswertung Jahresabschluss
		
		private static ArrayList<String> createCVS(int jahr) {
			
			ArrayList<String> table = new ArrayList<String>();
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
