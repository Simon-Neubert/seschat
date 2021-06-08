package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbaccess.DBAccess;

import javax.swing.JButton;
import javax.swing.JDialog;

import objects.*;
import popups.KundenRechnungenNeu;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kunden extends JPanel {

// Read from DB and add data to objects
	static ArrayList<objects.Kunde> k = dbaccess.DBAccess.createKunden();
	static ArrayList<objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
	static String vorname = "", nachname = ""; static int plz = 0;
	public static boolean abgebrochen = true;

// Components for GUI

	private JTextField vornameNeuField;
	private JTextField generiertField;
	private JTextField vornameSuchenField;
	private JTextField idSuchenField;
	private JTable table;
	private JTextField idBearbeitenFeld;
	private JTextField nachnameNeuField;
	private JTextField plzNeuField;
	private JTextField nachnameSuchenField;
	private JTextField plzSuchenField;

// Constructor for JPanel

	public Kunden() {

		// Define Panel
		setLayout(null);
		setBounds(2000, 2000, 2000, 2000);

// Neuer Kunde
		
		JLabel labelBestellvolumen = new JLabel("Neuer Kunde:");
		labelBestellvolumen.setFont(new Font("Serif", Font.PLAIN, 25));
		labelBestellvolumen.setBounds(101, 90, 395, 32);
		add(labelBestellvolumen);
		
		vornameNeuField = new JTextField();
		vornameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameNeuField.setFont(new Font("Sans", Font.PLAIN, 14));
		vornameNeuField.setBounds(101, 134, 219, 53);
		vornameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		vornameNeuField.setText("   Vorname eingeben...");
		vornameNeuField.setColumns(10);
		vornameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				vornameNeuField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		add(vornameNeuField);
		
		nachnameNeuField = new JTextField();
		nachnameNeuField.setText("   Nachname eingeben...");
		nachnameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameNeuField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nachnameNeuField.setColumns(10);
		nachnameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		nachnameNeuField.setBounds(378, 134, 219, 53);
		nachnameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nachnameNeuField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(nachnameNeuField);
		
		plzNeuField = new JTextField();
		plzNeuField.setText("   PLZ eingeben...");
		plzNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		plzNeuField.setFont(new Font("Dialog", Font.PLAIN, 14));
		plzNeuField.setColumns(10);
		plzNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		plzNeuField.setBounds(649, 134, 219, 53);
		plzNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				plzNeuField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(plzNeuField);
		
		generiertField = new JTextField();
		generiertField.setText(" Generierte ID erscheint hier");
		generiertField.setEditable(false);
		generiertField.setHorizontalAlignment(SwingConstants.CENTER);
		generiertField.setFont(new Font("Dialog", Font.PLAIN, 14));
		generiertField.setColumns(10);
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		generiertField.setBounds(101, 199, 219, 53);
		add(generiertField);
	
		JLabel neuLabel = new JLabel("");
		neuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		neuLabel.setForeground(Color.RED);
		neuLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		neuLabel.setBounds(378, 212, 490, 26);
		add(neuLabel);
		
		JButton speichernButton = new JButton("Speichern");
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.setForeground(new Color(30, 144, 255));
		speichernButton.setBounds(936, 137, 170, 50);
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String vornameInput = vornameNeuField.getText();
				String nachnameInput = nachnameNeuField.getText();
				String plzInput = plzNeuField.getText();
				
				vornameNeuField.setText("   Vorname eingeben...");
				nachnameNeuField.setText("   Nachname eingeben...");
				plzNeuField.setText("   PLZ eingeben...");
				
				// Check if all Fields have been filled out
				if (vornameInput.equals("") || vornameInput.equals("   Vorname eingeben...") || nachnameInput.equals("") || nachnameInput.equals("   Nachname eingeben...") || plzInput.equals("") || plzInput.equals("   PLZ eingeben...") || !plzInput.matches("[0-9]+") || plzInput.length() != 5) {
					setErrMessage(neuLabel);
					return;
				}

				for (int i = 0; i < k.toArray().length; i++)
					if (k.get(i).getVorname().equals(vornameInput) && k.get(i).getNachname().equals(nachnameInput) && k.get(i).getPlz() == Integer.parseInt(plzInput)) {
						neuLabel.setForeground(Color.RED);
						neuLabel.setText("Kunde bereits vorhanden! (ID: " + k.get(i).getKundenID() + ")");
						return;
					}

				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
				neuLabel.setText("");
				neuLabel.setForeground(Color.BLACK);
				kundeAufnehmen(vornameInput, nachnameInput, Integer.parseInt(plzInput));
				neuLabel.setText("Kunde erfolgreich aufgenommen.");
				DBAccess.dbResetAutoIncrement("KundenID", "kunden");

				generiertField.setText(String.valueOf(k.toArray().length));
				vornameNeuField.setText("   Bitte Name eingeben...");
				return;
			}
		});
		add(speichernButton);
		
// Bestehender Kunde	
		
		JLabel bestehendLabel = new JLabel("Kunde suchen:");
		bestehendLabel.setBounds(101, 308, 277, 26);
		bestehendLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		add(bestehendLabel);

		vornameSuchenField = new JTextField();
		vornameSuchenField.setText("   Vorname eingeben...");
		vornameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		vornameSuchenField.setColumns(10);
		vornameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		vornameSuchenField.setBounds(101, 346, 219, 53);
		vornameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				vornameSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(vornameSuchenField);
		
		nachnameSuchenField = new JTextField();
		nachnameSuchenField.setText("   Nachname eingeben...");
		nachnameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nachnameSuchenField.setColumns(10);
		nachnameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		nachnameSuchenField.setBounds(378, 346, 219, 53);
		nachnameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nachnameSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(nachnameSuchenField);
		
		plzSuchenField = new JTextField();
		plzSuchenField.setText("   PLZ eingeben...");
		plzSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		plzSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		plzSuchenField.setColumns(10);
		plzSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		plzSuchenField.setBounds(649, 346, 219, 53);
		plzSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				plzSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(plzSuchenField);
		
		idSuchenField = new JTextField();
		idSuchenField.setText("   Bitte ID eingeben...");
		idSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		idSuchenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		idSuchenField.setColumns(10);
		idSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		idSuchenField.setBounds(101, 422, 219, 53);
		idSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		add(idSuchenField);
		
		JLabel suchenLabel = new JLabel("Ohne Eingabe suchen um alle Kunden auszugeben.");
		suchenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		suchenLabel.setForeground(Color.DARK_GRAY);
		suchenLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		suchenLabel.setBounds(378, 436, 490, 26);
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
		pane.setBounds(101, 535, 1310, 420);
		add(pane);

		JButton suchenButton = new JButton("Suchen");
		suchenButton.setForeground(new Color(30, 144, 255));
		suchenButton.setBackground(new Color(30, 144, 255));
		suchenButton.setBounds(936, 349, 170, 50);
		suchenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String vornameInput = vornameSuchenField.getText();
				String nachnameInput = nachnameSuchenField.getText();
				String plzInput = plzSuchenField.getText();
				String idInput = idSuchenField.getText();
				
				// Keine Eingabe
				if ((idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) && (vornameInput.equals("   Vorname eingeben...") || vornameInput.equals("")) && (nachnameInput.equals("   Nachname eingeben...") || nachnameInput.equals("")) && (plzInput.equals("   PLZ eingeben...") || plzInput.equals(""))) {

					ArrayList<String> list = tableOfAll();
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array,
							new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ"});
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					return;
				}

				// Check ID Input
				if ((!idInput.matches("[0-9]+") && !idInput.equals("   Bitte ID eingeben...")) && !idInput.equals("")) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// ID eingegeben
				if (idInput.matches("[0-9]+")) {

					ArrayList<String> list = tableByID(Integer.parseInt(idInput));
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ"});
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				// Check PLZ Input
				if ((!plzInput.equals("   PLZ eingeben...") && !plzInput.equals("")) && (plzInput.length() != 5 || !plzInput.matches("[0-9]+"))) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Check Vorname Input
				if (!vornameInput.equals("   Vorname eingeben...") && !vornameInput.equals("") && !vornameInput.matches("[a-zA-Z]+")) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Check Nachname Input
				if (!nachnameInput.equals("   Nachname eingeben...") && !nachnameInput.equals("") && !nachnameInput.matches("[a-zA-Z]+")) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Vor- und Nachname + PLZ eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && nachnameInput.matches("[a-zA-Z]+") && plzInput.length() == 5 && (!idInput.equals("") || !idInput.equals("   Bitte ID eingeben..."))) {

					ArrayList<String> list = tableByVorUndNachnameUndPLZ(vornameInput, nachnameInput, Integer.parseInt(plzInput));
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Lieferanten-ID", "Vorname", "Nachname", "PLZ"});
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				// Vor- und Nachname eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && nachnameInput.matches("[a-zA-Z]+") && (plzInput.equals("   PLZ eingeben...") || plzInput.equals("")) && (idInput.equals("") || idInput.equals("   Bitte ID eingeben..."))) {

					ArrayList<String> list = tableByVorUndNachname(vornameInput, nachnameInput);
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				// Vorname und PLZ eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && plzInput.matches("[0-9]+") && (nachname.equals("   Nachname eingeben...") || nachname.equals("")) && (idInput.equals("") || idInput.equals("   Bitte ID eingeben..."))) {
					
					ArrayList<String> list = tableByVornameUndPLZ(vornameInput, Integer.parseInt(plzInput));
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}

				// Nachname und PLZ eingegeben
				if (nachnameInput.matches("[a-zA-Z]+") && plzInput.matches("[0-9]+") && (vorname.equals("   Vorname eingeben...") || vorname.equals("")) && (idInput.equals("") || idInput.equals("   Bitte ID eingeben..."))) {

					ArrayList<String> list = tableByNachnameUndPLZ(nachnameInput, Integer.parseInt(plzInput));
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetLabel(suchenLabel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}

				// Nur Vorname
				if (vornameInput.matches("[a-zA-Z]+")) {
					ArrayList<String> list = tableByVorname(vornameInput);
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				// Nur Nachname
				if (nachnameInput.matches("[a-zA-Z]+")) {
					ArrayList<String> list = tableByNachname(nachnameInput);
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				// Nur PLZ
				if (plzInput.length() == 5) {

					ArrayList<String> list = tableByPLZ(Integer.parseInt(plzInput));
					String[][] array = new String[(list.toArray().length)/4][4];
					int counter = 0;

					for (int i = 0; i < (list.toArray().length)/4; i++)
						for (int j = 0; j < 4; j++) {
							array[i][j] = "  " + list.get(counter);
							counter++;
						}

					DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ" });
					table.setModel(tableModel);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					return;
				}
				
				resetLabel(suchenLabel);
				return;
			}
		});
		add(suchenButton);

// Kunde bearbeiten
		
		JLabel bearbeitenLabel = new JLabel("Kunde bearbeiten:");
		bearbeitenLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		bearbeitenLabel.setBounds(1532, 90, 277, 26);
		add(bearbeitenLabel);

		idBearbeitenFeld = new JTextField();
		idBearbeitenFeld.setText("   Bitte ID eingeben...");
		idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenFeld.setFont(new Font("Dialog", Font.PLAIN, 14));
		idBearbeitenFeld.setColumns(10);
		idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
		idBearbeitenFeld.setBounds(1520, 131, 219, 53);
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
		changeLabel.setBounds(1520, 453, 210, 26);
		add(changeLabel);
		
		JButton bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bearbeitenButton.setForeground(new Color(30, 144, 255));
		bearbeitenButton.setBackground(new Color(30, 144, 255));
		bearbeitenButton.setBounds(1544, 236, 170, 50);
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("   Bitte ID eingeben...");

				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
					setErrMessage(changeLabel);
					return;
				}

				if (Integer.parseInt(idInput) > k.toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}

				int id = Integer.parseInt(idInput);

				try {
					popups.KundeBearbeiten dialog = new popups.KundeBearbeiten(id, getVorname(id), getNachname(id), getPLZ(id));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							changeLabel.setForeground(Color.BLACK);
							if (!abgebrochen)
								changeLabel.setText("Kunde bearbeitet.");
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				resetLabel(changeLabel);
			}
		});
		add(bearbeitenButton);

		JButton addRechnungButton = new JButton("Neue Rechnung");
		addRechnungButton.setForeground(new Color(30, 144, 255));
		addRechnungButton.setBackground(new Color(30, 144, 255));
		addRechnungButton.setBounds(1544, 346, 170, 50);
		addRechnungButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("   Bitte ID eingeben...");

				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("   Bitte ID eingeben...") || idInput.equals("")) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("Bitte Eingabe prüfen.");
					return;
				}

				if (Integer.parseInt(idInput) > k.toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}

				int id = Integer.parseInt(idInput);

				try {
					popups.KundenRechnungenNeu dialog = new popups.KundenRechnungenNeu(id, getVorname(id), getNachname(id), getPLZ(id), (kr.toArray().length + 1));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							changeLabel.setForeground(Color.BLACK);
							if (!abgebrochen) changeLabel.setText("Rechnung aufgenommen.");
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(addRechnungButton);
		
	}

// Edit Kunden

	// Add Kunde to objects and call dbAddLieferant
	private static void kundeAufnehmen(String vorname, String nachname, int plz) {
		int newID = k.toArray().length + 1;
		k.add(new Kunde(newID, vorname, nachname, plz));
		dbAddKunde(vorname, nachname, plz);
		DBAccess.dbResetAutoIncrement("KundenID", "kunden");
	}

	// Add Kunde in mySQL database
	public static void dbAddKunde(String vorname, String nachname, int plz) {
		DBAccess.dbResetAutoIncrement("KundenID", "kunden");
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute(" INSERT INTO kunden (Vorname, Nachname, PLZ) VALUES ('" + vorname + "', '" + nachname + "', '" + plz + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Kunde in objects and call dbChangeLieferant
	public static void kundeBearbeiten(int id, String vorname, String nachname, int plz) {
		DBAccess.dbResetAutoIncrement("KundenID", "kunden");
		k.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
			x.setKundenID(id);
			x.setVorname(vorname);
			x.setNachname(nachname);
			x.setPlz(plz);
		});
		dbChangeKunde(id, vorname, nachname, plz);
		DBAccess.dbResetAutoIncrement("KundenID", "kunden");
	}

	// Change Kunde in mySQL database
	public static void dbChangeKunde(int id, String vorname, String nachname, int plz) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("UPDATE kunden SET vorname = '" + vorname + "', nachname = '" + nachname + "', plz = '" + plz + "'  WHERE KundenID = '" + id + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
// Add invoice

	// Add invoive to objects and call dbAddRechnung
	public static void rechnungAufnehmen(int kundenID, int monat, int jahr, double bestellvolumen, boolean status) {
		int newID = kr.toArray().length + 1;
		kr.add(new Kundenrechnung(newID, monat, jahr, bestellvolumen, status, kundenID));
		dbAddRechnung(kundenID, monat, jahr, bestellvolumen, status);
		DBAccess.dbResetAutoIncrement("RechnungsID", "kundenrechnungen");
	}

	// Add invoice in mySQL database
	public static void dbAddRechnung(int kundenID, int monat, int jahr, double bestellvolumen, boolean status) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			int tinyInt = 0;
			if (status) tinyInt = 1;
			stmt.execute(
					"INSERT INTO kundenrechnungen (KundenID, Monat, Jahr, Summe, Status) VALUES ('"
							+ kundenID + "', '" + monat + "', '" + jahr + "', '" + bestellvolumen + "', '"
							+ tinyInt + "')");
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
	private static ArrayList<String> tableByVorname(String vorname) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase())).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}

	// Create table if user only inputs last name
	private static ArrayList<String> tableByNachname(String nachname) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getNachname().toLowerCase().contains(nachname.toLowerCase())).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}
	
	// Create table if user inputs first and last name
	private static ArrayList<String> tableByVorUndNachname(String vorname, String nachname) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getNachname().toLowerCase().contains(nachname.toLowerCase())).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}
	
	// Create table if user only inputs id
	private static ArrayList<String> tableByID(int id) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getKundenID() == id).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}

	// Create table if user inputs name and plz
	private static ArrayList<String> tableByPLZ(int plz) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getPlz() == plz)
				.forEach(x -> {
					table.add(String.valueOf(x.getKundenID()));
					table.add(x.getVorname());
					table.add(x.getNachname());
					table.add(String.valueOf(x.getPlz()));
				});

		return table;
	}

	private static ArrayList<String> tableByVornameUndPLZ(String vorname, int plz) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}
	
	private static ArrayList<String> tableByNachnameUndPLZ(String nachname, int plz) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getNachname().toLowerCase().contains(nachname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
			table.add(String.valueOf(x.getKundenID()));
			table.add(x.getVorname());
			table.add(x.getNachname());
			table.add(String.valueOf(x.getPlz()));
		});

		return table;
	}
	
	private static ArrayList<String> tableByVorUndNachnameUndPLZ(String vorname, String nachname, int plz) {

		ArrayList<String> table = new ArrayList<String>();

		k.stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getNachname().toLowerCase().contains(nachname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
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
}