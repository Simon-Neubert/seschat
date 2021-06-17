package gui;

import java.awt.Color;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dbaccess.DBAccess;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Rechnungen extends JPanel {

	private static final long serialVersionUID = 1L;
	static String vorname = "", nachname = ""; static int plz = 0;
	public static boolean abgebrochen = true;

	static String[] monate = {"Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String[] lastTenYears = new String[11];
	private static String[] selection = {"Sortieren nach...", "Summe", "Zeitraum", "ID", "Rechnungs-ID", "Status"};
	
	public static String lieferant = "";
	public static int kundeID = 0;
	public static int lieferantID = 0;
	public static int bezahltCounter = 0;
	public static int unbezahltCounter = 0;
	
	private JTextField idBearbeitenFeld;
	private JTextField statusField;
	private JButton bearbeitenButton;
	private JButton setzenButton;
	private JRadioButton kundeRadioBearbeiten;
	private JRadioButton lieferantRadioBearbeiten;
	private final ButtonGroup bearbeitenGroup = new ButtonGroup();
	private JLabel changeLabel;
	private JLabel bestehendLabel;
	private JTextField rechnungsIDField;
	private JTextField kundenIDField;
	private JRadioButton bezahltRadio;
	private JRadioButton unbezahltRadio;
	private JComboBox monatDropdownSuchen;
	private JComboBox jahrDropdownSuchen;
	private final ButtonGroup ausgebenGroup = new ButtonGroup();
	private JLabel suchenLabel;
	private JButton suchenButton;
	private JScrollPane pane;
	private JTable table;
	private JComboBox steigungsBox;
	private JComboBox selectionBox;
	private JLabel bearbeitenIDLabel;
	private JLabel bearbeitenSetzenLabel;
	private JLabel lblRechnungsid;
	private JLabel lblKundenlieferantenid;
	private JLabel bearbeitenRechnnungstypLabel;
	private JLabel suchenStatusLabel;
	private JLabel suchenZeitraumLabel_1;
	private JRadioButton placeholderRadio;


	// Constructor
	public Rechnungen() {

		setBounds(100, 100, 1280, 720);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 380, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 30, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		fillYears();
		
		// Rechnung bearbeiten
		JLabel bearbeitenLabel = new JLabel("Rechnung bearbeiten:");
		bearbeitenLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bearbeitenLabel = new GridBagConstraints();
		gbc_bearbeitenLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenLabel.gridx = 1;
		gbc_bearbeitenLabel.gridy = 1;
		add(bearbeitenLabel, gbc_bearbeitenLabel);
		
		idBearbeitenFeld = new JTextField();
		idBearbeitenFeld.setText("Bitte ID eingeben...");
		idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenFeld.setFont(new Font("Palatino", Font.PLAIN, 14));
		idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
		idBearbeitenFeld.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idBearbeitenFeld.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		bearbeitenRechnnungstypLabel = new JLabel("Rechnungstyp:");
		bearbeitenRechnnungstypLabel.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_bearbeitenRechnnungstypLabel = new GridBagConstraints();
		gbc_bearbeitenRechnnungstypLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenRechnnungstypLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenRechnnungstypLabel.gridx = 1;
		gbc_bearbeitenRechnnungstypLabel.gridy = 2;
		add(bearbeitenRechnnungstypLabel, gbc_bearbeitenRechnnungstypLabel);
		
		kundeRadioBearbeiten = new JRadioButton("Kunde    ");
		kundeRadioBearbeiten.setFont(new Font("Palatino", Font.BOLD, 16));
		kundeRadioBearbeiten.setSelected(true);
		bearbeitenGroup.add(kundeRadioBearbeiten);
		GridBagConstraints gbc_kundeRadioBearbeiten = new GridBagConstraints();
		gbc_kundeRadioBearbeiten.anchor = GridBagConstraints.WEST;
		gbc_kundeRadioBearbeiten.insets = new Insets(0, 0, 5, 5);
		gbc_kundeRadioBearbeiten.gridx = 2;
		gbc_kundeRadioBearbeiten.gridy = 2;
		add(kundeRadioBearbeiten, gbc_kundeRadioBearbeiten);
		
		lieferantRadioBearbeiten = new JRadioButton("Lieferant");
		lieferantRadioBearbeiten.setFont(new Font("Palatino", Font.BOLD, 16));
		bearbeitenGroup.add(lieferantRadioBearbeiten);
		GridBagConstraints gbc_lieferantRadioBearbeiten = new GridBagConstraints();
		gbc_lieferantRadioBearbeiten.anchor = GridBagConstraints.WEST;
		gbc_lieferantRadioBearbeiten.insets = new Insets(0, 0, 5, 5);
		gbc_lieferantRadioBearbeiten.gridx = 3;
		gbc_lieferantRadioBearbeiten.gridy = 2;
		add(lieferantRadioBearbeiten, gbc_lieferantRadioBearbeiten);
		
		bearbeitenIDLabel = new JLabel("ID bearbeiten:");
		bearbeitenIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_bearbeitenIDLabel = new GridBagConstraints();
		gbc_bearbeitenIDLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenIDLabel.gridx = 1;
		gbc_bearbeitenIDLabel.gridy = 5;
		add(bearbeitenIDLabel, gbc_bearbeitenIDLabel);
		
		bearbeitenSetzenLabel = new JLabel("ID setzen:");
		bearbeitenSetzenLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_bearbeitenSetzenLabel = new GridBagConstraints();
		gbc_bearbeitenSetzenLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenSetzenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenSetzenLabel.gridx = 3;
		gbc_bearbeitenSetzenLabel.gridy = 5;
		add(bearbeitenSetzenLabel, gbc_bearbeitenSetzenLabel);
		
		GridBagConstraints gbc_idBearbeitenFeld = new GridBagConstraints();
		gbc_idBearbeitenFeld.insets = new Insets(0, 0, 5, 5);
		gbc_idBearbeitenFeld.fill = GridBagConstraints.BOTH;
		gbc_idBearbeitenFeld.gridx = 1;
		gbc_idBearbeitenFeld.gridy = 6;
		add(idBearbeitenFeld, gbc_idBearbeitenFeld);
		
		bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_bearbeitenButton = new GridBagConstraints();
		gbc_bearbeitenButton.fill = GridBagConstraints.VERTICAL;
		gbc_bearbeitenButton.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenButton.gridx = 1;
		gbc_bearbeitenButton.gridy = 8;
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("Bitte ID eingeben...");
				
				boolean lieferant = lieferantRadioBearbeiten.isSelected();
				boolean kunde = kundeRadioBearbeiten.isSelected();
				
				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("Bitte ID eingeben...") || idInput.equals("")) {
					setErrMessage(changeLabel);
					return;
				}
				
				if (idInput.equals("0")) {
					changeLabel.setText("ID: 0 existiert nicht.");
					return;
				};

				if (kunde && Integer.parseInt(idInput) > DBAccess.getKr().toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}

				if (lieferant && Integer.parseInt(idInput) > DBAccess.getLr().toArray().length) {
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
						int kundenID = getKundenID(id);
						popups.KundenRechnungBearbeiten dialog = new popups.KundenRechnungBearbeiten(kundenID, getVorname(kundenID), getNachname(kundenID), getPLZ(kundenID), id, false);
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
						popups.LieferantenRechnungBearbeiten dialog = new popups.LieferantenRechnungBearbeiten(getLieferantenID(id), getName(getLieferantenID(id)), id, false);
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
				
				abgebrochen = true;
				
			}
		});
		
		statusField = new JTextField();
		statusField.setText("ID auf bezahlt setzen...");
		statusField.setHorizontalAlignment(SwingConstants.CENTER);
		statusField.setFont(new Font("Palatino", Font.PLAIN, 14));
		statusField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				statusField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		placeholderRadio = new JRadioButton("");
		placeholderRadio.setVisible(false);
		placeholderRadio.setFont(new Font("Palatino", Font.PLAIN, 16));
		GridBagConstraints gbc_placeholderRadio = new GridBagConstraints();
		gbc_placeholderRadio.insets = new Insets(0, 0, 5, 5);
		gbc_placeholderRadio.gridx = 2;
		gbc_placeholderRadio.gridy = 6;
		add(placeholderRadio, gbc_placeholderRadio);
		statusField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_statusField = new GridBagConstraints();
		gbc_statusField.insets = new Insets(0, 0, 5, 5);
		gbc_statusField.fill = GridBagConstraints.BOTH;
		gbc_statusField.gridx = 3;
		gbc_statusField.gridy = 6;
		add(statusField, gbc_statusField);
		add(bearbeitenButton, gbc_bearbeitenButton);
		
		setzenButton = new JButton("Setzen");
		setzenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_setzenButton = new GridBagConstraints();
		gbc_setzenButton.fill = GridBagConstraints.VERTICAL;
		gbc_setzenButton.insets = new Insets(0, 0, 5, 5);
		gbc_setzenButton.gridx = 3;
		gbc_setzenButton.gridy = 8;
		setzenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String zahl = statusField.getText();
				boolean kunde = kundeRadioBearbeiten.isSelected();
				
				if (!zahl.matches("[0-9]+") ) {
					setErrMessage(changeLabel);
					statusField.setText("ID auf bezahlt setzen...");
					return;
				}
				
				if (zahl.equals("0")) {
					changeLabel.setText("ID: 0 existiert nicht.");
					statusField.setText("ID auf bezahlt setzen...");
					return;
				};
				
				statusSetzen(Integer.parseInt(zahl), kunde);
				statusField.setText("ID auf bezahlt setzen...");
				changeLabel.setForeground(Color.BLACK);
				changeLabel.setText("Status geändert");
				return;
			}
		});
		add(setzenButton, gbc_setzenButton);
		
		changeLabel = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeLabel.setForeground(Color.BLACK);
		changeLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_changeLabel = new GridBagConstraints();
		gbc_changeLabel.anchor = GridBagConstraints.WEST;
		gbc_changeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_changeLabel.gridx = 1;
		gbc_changeLabel.gridy = 9;
		add(changeLabel, gbc_changeLabel);
		
		
		// Rechnungen ausgeben
		bestehendLabel = new JLabel("Rechnungen ausgeben:");
		bestehendLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bestehendLabel = new GridBagConstraints();
		gbc_bestehendLabel.anchor = GridBagConstraints.WEST;
		gbc_bestehendLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bestehendLabel.gridx = 1;
		gbc_bestehendLabel.gridy = 11;
		add(bestehendLabel, gbc_bestehendLabel);
		
		rechnungsIDField = new JTextField();
		rechnungsIDField.setText("Rechnungs-ID eingeben...");
		rechnungsIDField.setHorizontalAlignment(SwingConstants.CENTER);
		rechnungsIDField.setFont(new Font("Palatino", Font.PLAIN, 14));
		rechnungsIDField.setColumns(10);
		rechnungsIDField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_rechnungsIDField = new GridBagConstraints();
		gbc_rechnungsIDField.insets = new Insets(0, 0, 5, 5);
		gbc_rechnungsIDField.fill = GridBagConstraints.BOTH;
		gbc_rechnungsIDField.gridx = 1;
		gbc_rechnungsIDField.gridy = 14;
		rechnungsIDField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				rechnungsIDField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		lblRechnungsid = new JLabel("Rechnungs-ID:");
		lblRechnungsid.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRechnungsid = new GridBagConstraints();
		gbc_lblRechnungsid.anchor = GridBagConstraints.WEST;
		gbc_lblRechnungsid.insets = new Insets(0, 0, 5, 5);
		gbc_lblRechnungsid.gridx = 1;
		gbc_lblRechnungsid.gridy = 13;
		add(lblRechnungsid, gbc_lblRechnungsid);
		
		suchenStatusLabel = new JLabel("Status:");
		suchenStatusLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_suchenStatusLabel = new GridBagConstraints();
		gbc_suchenStatusLabel.fill = GridBagConstraints.VERTICAL;
		gbc_suchenStatusLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenStatusLabel.gridx = 2;
		gbc_suchenStatusLabel.gridy = 13;
		add(suchenStatusLabel, gbc_suchenStatusLabel);
		
		suchenZeitraumLabel_1 = new JLabel("Zeitraum:");
		suchenZeitraumLabel_1.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_suchenZeitraumLabel_1 = new GridBagConstraints();
		gbc_suchenZeitraumLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_suchenZeitraumLabel_1.gridx = 3;
		gbc_suchenZeitraumLabel_1.gridy = 13;
		add(suchenZeitraumLabel_1, gbc_suchenZeitraumLabel_1);
		add(rechnungsIDField, gbc_rechnungsIDField);
		
		kundenIDField = new JTextField();
		kundenIDField.setText("Kunden-/Lieferanten-ID...");
		kundenIDField.setHorizontalAlignment(SwingConstants.CENTER);
		kundenIDField.setFont(new Font("Palatino", Font.PLAIN, 14));
		kundenIDField.setColumns(10);
		kundenIDField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_kundenIDField = new GridBagConstraints();
		gbc_kundenIDField.insets = new Insets(0, 0, 5, 5);
		gbc_kundenIDField.fill = GridBagConstraints.BOTH;
		gbc_kundenIDField.gridx = 1;
		gbc_kundenIDField.gridy = 16;
		kundenIDField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				kundenIDField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		bezahltRadio = new JRadioButton("Bezahlt    ");
		bezahltRadio.setFont(new Font("Palatino", Font.PLAIN, 16));
		bezahltRadio.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (unbezahltCounter == 1) unbezahltCounter--;
			
			bezahltCounter++;
			
			if (bezahltCounter == 2) {
				ausgebenGroup.clearSelection();
				bezahltCounter = 0;
				
			}
		}
		});
		ausgebenGroup.add(bezahltRadio);
		GridBagConstraints gbc_bezahltRadio = new GridBagConstraints();
		gbc_bezahltRadio.insets = new Insets(0, 0, 5, 5);
		gbc_bezahltRadio.gridx = 2;
		gbc_bezahltRadio.gridy = 14;
		add(bezahltRadio, gbc_bezahltRadio);
		
		unbezahltRadio = new JRadioButton("Unbezahlt");
		unbezahltRadio.setFont(new Font("Palatino", Font.PLAIN, 16));
		ausgebenGroup.add(unbezahltRadio);
		unbezahltRadio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (bezahltCounter == 1) bezahltCounter--;
				
				unbezahltCounter++;
				
				if (unbezahltCounter == 2) {
					ausgebenGroup.clearSelection();
					unbezahltCounter = 0;
				}}
		});
		
		lblKundenlieferantenid = new JLabel("Kunden-/Lieferanten-ID:");
		lblKundenlieferantenid.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_lblKundenlieferantenid = new GridBagConstraints();
		gbc_lblKundenlieferantenid.anchor = GridBagConstraints.WEST;
		gbc_lblKundenlieferantenid.insets = new Insets(0, 0, 5, 5);
		gbc_lblKundenlieferantenid.gridx = 1;
		gbc_lblKundenlieferantenid.gridy = 15;
		add(lblKundenlieferantenid, gbc_lblKundenlieferantenid);
		GridBagConstraints gbc_unbezahltRadio = new GridBagConstraints();
		gbc_unbezahltRadio.insets = new Insets(0, 0, 5, 5);
		gbc_unbezahltRadio.gridx = 2;
		gbc_unbezahltRadio.gridy = 16;
		add(unbezahltRadio, gbc_unbezahltRadio);
		
		monatDropdownSuchen = new JComboBox(monate);
		monatDropdownSuchen.setFont(new Font("Palatino", Font.BOLD, 13));
		monatDropdownSuchen.setMaximumRowCount(14);
		GridBagConstraints gbc_monatDropdownSuchen = new GridBagConstraints();
		gbc_monatDropdownSuchen.insets = new Insets(0, 0, 5, 5);
		gbc_monatDropdownSuchen.gridx = 3;
		gbc_monatDropdownSuchen.gridy = 14;
		add(monatDropdownSuchen, gbc_monatDropdownSuchen);
		add(kundenIDField, gbc_kundenIDField);
		
		
		jahrDropdownSuchen = new JComboBox(lastTenYears);
		jahrDropdownSuchen.setFont(new Font("Palatino", Font.BOLD, 13));
		jahrDropdownSuchen.setMaximumRowCount(14);
		GridBagConstraints gbc_jahrDropdownSuchen = new GridBagConstraints();
		gbc_jahrDropdownSuchen.insets = new Insets(0, 0, 5, 5);
		gbc_jahrDropdownSuchen.gridx = 3;
		gbc_jahrDropdownSuchen.gridy = 16;
		add(jahrDropdownSuchen, gbc_jahrDropdownSuchen);
		
		suchenLabel = new JLabel("Ohne Eingabe suchen um alle Rechnungen auszugeben.");
		suchenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		suchenLabel.setForeground(Color.DARK_GRAY);
		suchenLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_suchenLabel = new GridBagConstraints();
		gbc_suchenLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenLabel.gridx = 1;
		gbc_suchenLabel.gridy = 17;
		add(suchenLabel, gbc_suchenLabel);
		
		selectionBox = new JComboBox(selection);
		selectionBox.setFont(new Font("Palatino", Font.BOLD, 13));
		GridBagConstraints gbc_selectionBox = new GridBagConstraints();
		gbc_selectionBox.insets = new Insets(0, 0, 5, 5);
		gbc_selectionBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectionBox.gridx = 1;
		gbc_selectionBox.gridy = 19;
		add(selectionBox, gbc_selectionBox);
		
		steigungsBox = new JComboBox(new Object[]{"absteigend", "aufsteigend"});
		steigungsBox.setFont(new Font("Palatino", Font.BOLD, 13));
		steigungsBox.setMaximumRowCount(2);
		GridBagConstraints gbc_steigungsBox = new GridBagConstraints();
		gbc_steigungsBox.insets = new Insets(0, 0, 5, 5);
		gbc_steigungsBox.gridx = 2;
		gbc_steigungsBox.gridy = 19;
		add(steigungsBox, gbc_steigungsBox);
		
		suchenButton = new JButton("Suchen");
		suchenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_suchenButton = new GridBagConstraints();
		gbc_suchenButton.fill = GridBagConstraints.VERTICAL;
		gbc_suchenButton.insets = new Insets(0, 0, 5, 5);
		gbc_suchenButton.gridx = 3;
		gbc_suchenButton.gridy = 19;
		suchenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Sortieren
				boolean isKunde = kundeRadioBearbeiten.isSelected();
				boolean isLieferant = lieferantRadioBearbeiten.isSelected();
				
				int sort = steigungsBox.getSelectedIndex();
				int selection = selectionBox.getSelectedIndex();
				if (selection != 0) sortRechnungen(sort, selection, isKunde, isLieferant);
				
				// Filtern
				ArrayList <String> list = new ArrayList<String> ();
				
				String rechnungsID = rechnungsIDField.getText();
				String id = kundenIDField.getText(); 
				boolean skip = false;
				
				if ((!rechnungsID.matches("[0-9]+") && !rechnungsID.equals("") && !rechnungsID.equals("Rechnungs-ID eingeben...")) || (!id.matches("[0-9]+") && !id.equals("") && !id.equals("Kunden-/Lieferanten-ID..."))) {
					setErrMessage(suchenLabel);
					return;
				}
				
				if (rechnungsID.equals("0") || id.equals("0")) {
					suchenLabel.setText("ID: 0 existiert nicht.");
					return;
				};
				
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
				
				// Nur Jahr
				if (!id.matches("[0-9]+") && jahrDropdownSuchen.getSelectedIndex() != 0 && monat == 0 && !skip) {
					list = tableByJahr(isKunde, Integer.parseInt(jahr), bezahlt, unbezahlt);
					skip = true;
				}
				
				// Jahr + Monat
				if (!id.matches("[0-9]+") && jahrDropdownSuchen.getSelectedIndex() != 0 && monat > 0 && !skip) {
					list = tableByMonat(isKunde, monat, Integer.parseInt(jahr), bezahlt, unbezahlt);
					skip = true;
				}
				
				// ID und Monat
				if (id.matches("[0-9]+") && monat > 0 && !skip) {
					list = tableByIDinMonat(Integer.parseInt(id), isKunde, monat, Integer.parseInt(jahr), bezahlt, unbezahlt);
					skip = true;
				}
				
				// ID und Jahr
				if (id.matches("[0-9]+") && jahrDropdownSuchen.getSelectedIndex() > 0 && !skip) {
					list = tableByIDinJahr(Integer.parseInt(id), isKunde, Integer.parseInt(jahr), bezahlt, unbezahlt);
					skip = true;
				}
				
				// ID
				if (id.matches("[0-9]+") && !skip) {
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
				skip = false;
				resetFields(rechnungsIDField, kundenIDField);
				return;
			}
		});
		add(suchenButton, gbc_suchenButton);
		
		table = new JTable();
		table.setBounds(101, 535, 1310, 420);
		table.setEnabled(false);
		table.setBorder(new LineBorder(Color.BLACK, 2));
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowHeight(30);

		pane = new JScrollPane(table);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.gridheight = 7;
		gbc_pane.gridwidth = 3;
		gbc_pane.insets = new Insets(0, 0, 5, 5);
		gbc_pane.fill = GridBagConstraints.BOTH;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 21;
		add(pane, gbc_pane);
		
		
	}

	
// Rechnungsbearbeitung in Objects und DB
	// Edit invoice

	// Add invoice to objects and call dbEditRechnung
	public static void rechnungBearbeitenLieferant(int rechnungsID, int monat, int jahr, double bestellvolumen, boolean status) {
		DBAccess.getLr().stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> {
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
		DBAccess.getKr().stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> {
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
		} catch (Exception e) {e.printStackTrace();}
	}
	
	// Set invoice to bezahlt
	public static void statusSetzen (int rechnungsID, boolean kunde) {
		if (kunde) {
			DBAccess.getKr().stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> x.setStatus(true));
			dbStatusSetzenKunde(rechnungsID);
		}
		else {
			DBAccess.getLr().stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x -> x.setStatus(true));
			dbStatusSetzenLieferant(rechnungsID);
		}
	}
	
	// Set invoice to bezahlt in DB
	public static void dbStatusSetzenKunde (int rechnungsID) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			int tinyInt = 1;
			stmt.execute("UPDATE kundenrechnungen SET status = '" + tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void dbStatusSetzenLieferant (int rechnungsID) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			int tinyInt = 1;
			stmt.execute("UPDATE lieferantenrechnungen SET status = '" + tinyInt + "' WHERE RechnungsID = '" + rechnungsID + "'");
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
// Auxiliary functions
	
	// Getters
	private static String getVorname (int id) {
		DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {vorname = x.getVorname();});
		return vorname;
	}
	private static String getNachname (int id) {
		DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {nachname = x.getNachname();});
		return nachname;
	}
	private static int getPLZ (int id) {
		DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {plz = x.getPlz();});
		return plz;
	}
	private static String getName (int id) {
		DBAccess.getL().stream().filter(x -> x.getLieferantenID() == id).forEach(x -> lieferant = x.getName());
		return lieferant;
	}
	private static int getKundenID (int id) {
		DBAccess.getKr().stream().filter(x -> x.getRechnungsID() == id).forEach(x -> kundeID = x.getKundenID());
		return kundeID;
	}
	private static int getLieferantenID (int id) {
		DBAccess.getLr().stream().filter(x -> x.getRechnungsID() == id).forEach(x -> lieferantID = x.getLieferantenID());
		return lieferantID;
	}
	
	// Fill LastTenYears
	private static void fillYears () {
		lastTenYears[0] = "Jahr auswählen ... ";
		for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
	}

	
	private static void resetFields(JTextField field1, JTextField field2) {
		field1.setText("Rechnungs-ID eingeben...");
		field2.setText("Kunden-/Lieferanten-ID...");
	}

	// Set label to display error message
	private static void setErrMessage (JLabel inputLabel) {
		inputLabel.setForeground(Color.RED);
		inputLabel.setText("Bitte alle Felder korrekt ausfüllen.");
		return;
	}

	
// Create tables
	
	// Create table by bill-ID
	private static ArrayList<String> tableByrechnungsID(int id, boolean kunde) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahlt = "bezahlt";
		String unbezahlt = "ausstehend";
		
		if (kunde) {
			DBAccess.getKr().stream().filter(x -> x.getRechnungsID() == id).forEach(x -> {
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
			DBAccess.getLr().stream().filter(x -> x.getRechnungsID() == id).forEach(x -> {
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

	// // Create table of all
	private static ArrayList<String> tableOfAll(boolean kunde, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS = "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && !unbezahlt) {
				DBAccess.getKr().stream().forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> !x.isStatus()).forEach(x -> {
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
	
	// Create table by ID
	private static ArrayList<String> tableByID(int id, boolean kunde, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS = "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && ! unbezahlt) {
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && !x.isStatus()).forEach(x -> {
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
	
	// Create table by year
	private static ArrayList<String> tableByJahr(boolean kunde, int jahr, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS = "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && ! unbezahlt) {
				DBAccess.getKr().stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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

	// Create table by month
	private static ArrayList<String> tableByMonat(boolean kunde, int monat, int jahr, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS= "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && !unbezahlt) {
				DBAccess.getKr().stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getMonat() == monat  && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getMonat() == monat  && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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
	
	// Create table by year
	private static ArrayList<String> tableByIDinJahr(int id, boolean kunde, int jahr, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS= "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && !unbezahlt) {
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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

	// Create table by ID in month
	private static ArrayList<String> tableByIDinMonat(int id, boolean kunde, int monat, int jahr, boolean bezahlt, boolean unbezahlt) {

		ArrayList<String> table = new ArrayList<String>();
		String bezahltS= "bezahlt";
		String unbezahltS = "ausstehend";
		
		if (kunde) {
			if (!bezahlt && !unbezahlt) {
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getKr().stream().filter(x -> x.getKundenID() == id && x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat  && x.getJahr() == jahr).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat  && x.getJahr() == jahr && x.isStatus()).forEach(x -> {
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
				DBAccess.getLr().stream().filter(x -> x.getLieferantenID() == id && x.getMonat() == monat && x.getJahr() == jahr && !x.isStatus()).forEach(x -> {
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

	
	// Rechnungen-Objekte sortieren
	private static void sortRechnungen (int steigung, int selection, boolean isKunde, boolean isLieferant) {
		
		Comparator<objects.Rechnung> byMonth = (s1, s2) -> {
			if (steigung > 0) return Integer.compare(s1.getMonat(), s2.getMonat()); 
			return Integer.compare(s2.getMonat(), s1.getMonat()); 
		};
		
		Comparator<objects.Rechnung> byZeitraum = (s1, s2) -> {
			if (steigung > 0) return Integer.compare(s1.getJahr(), s2.getJahr()); 
			return Integer.compare(s2.getJahr(), s1.getJahr());
		};
		
		Comparator<objects.Rechnung> bySumme = (s1, s2) -> {
			if (steigung > 0) return Double.compare(s1.getSumme(),s2.getSumme()); 
			return Double.compare(s2.getSumme(), s1.getSumme()); 
		};
	    
		Comparator<objects.Rechnung> byRechnung = (s1, s2) -> {
			if (steigung > 0) return Integer.compare(s1.getRechnungsID(), s2.getRechnungsID()); 
			return Integer.compare(s2.getRechnungsID(), s1.getRechnungsID()); 
		};
		
		Comparator<objects.Kundenrechnung> byKunde = (s1, s2) -> {
			if (steigung > 0) return Integer.compare(s1.getKundenID(), s2.getKundenID()); 
			return Integer.compare(s2.getKundenID(), s1.getKundenID()); 
		};
		
		Comparator<objects.Lieferantenrechnung> byLieferant = (s1, s2) -> {
			if (steigung > 0) return Integer.compare(s1.getLieferantenID(), s2.getLieferantenID()); 
			return Integer.compare(s2.getLieferantenID(), s1.getLieferantenID()); 
		};
		
		Comparator<objects.Rechnung> byStatus = (s1, s2) -> {
			if (steigung > 0) return Boolean.compare(s1.isStatus(), s2.isStatus()); 
			return Boolean.compare(s2.isStatus(), s1.isStatus()); 
		};
		
		switch (selection) {
		case 1: 
			if (isLieferant) DBAccess.getLr().sort(bySumme);
			if (isKunde) DBAccess.getKr().sort(bySumme);
			break;
		case 2: 
			if (isLieferant) {
				DBAccess.getLr().sort(byMonth);
				DBAccess.getLr().sort(byZeitraum);
			}
			if (isKunde) {
				DBAccess.getLr().sort(byMonth);
				DBAccess.getKr().sort(byZeitraum);
			}
			break;
		case 3:
			if (isLieferant) DBAccess.getLr().sort(byLieferant);
			if (isKunde) DBAccess.getKr().sort(byKunde);
			break;
		case 4: 
			if (isLieferant) DBAccess.getLr().sort(byRechnung);
			if (isKunde) DBAccess.getKr().sort(byRechnung);
			return;
		case 5:
			if (isLieferant) DBAccess.getLr().sort(byStatus);
			if (isKunde) DBAccess.getKr().sort(byStatus);
			return;
		default: break;
		}
		
	}
	
}
