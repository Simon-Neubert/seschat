package gui;

import java.awt.Color;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbaccess.DBAccess;
import objects.Kunde;
import objects.Kundenrechnung;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Kunden extends JPanel {

		static String vorname = "", nachname = ""; static int plz = 0;
		public static boolean abgebrochen = true;
		
	// Components for GUI
		private JTable table;
		private JTextField vornameNeuField;
		private JTextField nachnameNeuField;
		private JTextField plzNeuField;
		private JButton speichernButton;
		private JLabel bestehendLabel;
		private JTextField generiertField;
		private JLabel bearbeitenLabel;
		private JTextField vornameSuchenField;
		private JTextField nachnameSuchenField;
		private JTextField plzSuchenField;
		private JTextField idSuchenField;
		private JButton suchenButton;
		private JTextField idBearbeitenFeld;
		private JButton bearbeitenButton;
		private JButton addRechnungButton;
		private JLabel suchenLabel;
		private JLabel neuLabel;
		private JScrollPane pane;
		private JLabel changeLabel;
		private JLabel neuVornameLabel;
		private JLabel neuNachnameLabel;
		private JLabel neuPLZLabel;
		private JLabel neuIDLabel;
		private JLabel bearbeitenIDLabel;
		private JLabel suchenVornameLabel;
		private JLabel suchenNachnameLabel;
		private JLabel suchenPLZLabel;
		private JLabel suchenIDLabel;
	
	
	// Constructor
	public Kunden() {
		
		setBounds(100, 100, 1280, 720);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 206, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		// Kunde aufnehmen
		JLabel labelBestellvolumen = new JLabel("Neuer Kunde:");
		labelBestellvolumen.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_labelBestellvolumen = new GridBagConstraints();
		gbc_labelBestellvolumen.anchor = GridBagConstraints.WEST;
		gbc_labelBestellvolumen.insets = new Insets(0, 0, 5, 5);
		gbc_labelBestellvolumen.gridx = 1;
		gbc_labelBestellvolumen.gridy = 1;
		add(labelBestellvolumen, gbc_labelBestellvolumen);
		
		vornameNeuField = new JTextField();
		vornameNeuField.setText("Vorname eingeben...");
		vornameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameNeuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		vornameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		vornameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				vornameNeuField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		neuVornameLabel = new JLabel("Vorname:");
		neuVornameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_neuVornameLabel = new GridBagConstraints();
		gbc_neuVornameLabel.anchor = GridBagConstraints.WEST;
		gbc_neuVornameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuVornameLabel.gridx = 1;
		gbc_neuVornameLabel.gridy = 2;
		add(neuVornameLabel, gbc_neuVornameLabel);
		
		neuNachnameLabel = new JLabel("Nachname:");
		neuNachnameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		neuNachnameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_neuNachnameLabel = new GridBagConstraints();
		gbc_neuNachnameLabel.anchor = GridBagConstraints.WEST;
		gbc_neuNachnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuNachnameLabel.gridx = 2;
		gbc_neuNachnameLabel.gridy = 2;
		add(neuNachnameLabel, gbc_neuNachnameLabel);
		
		neuPLZLabel = new JLabel("PLZ:");
		neuPLZLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		neuPLZLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_neuPLZLabel = new GridBagConstraints();
		gbc_neuPLZLabel.anchor = GridBagConstraints.WEST;
		gbc_neuPLZLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuPLZLabel.gridx = 3;
		gbc_neuPLZLabel.gridy = 2;
		add(neuPLZLabel, gbc_neuPLZLabel);
		
		neuIDLabel = new JLabel("Generierte ID:");
		neuIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		neuIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_neuIDLabel = new GridBagConstraints();
		gbc_neuIDLabel.anchor = GridBagConstraints.WEST;
		gbc_neuIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuIDLabel.gridx = 4;
		gbc_neuIDLabel.gridy = 2;
		add(neuIDLabel, gbc_neuIDLabel);
		GridBagConstraints gbc_vornameNeuField = new GridBagConstraints();
		gbc_vornameNeuField.insets = new Insets(0, 0, 5, 5);
		gbc_vornameNeuField.fill = GridBagConstraints.BOTH;
		gbc_vornameNeuField.gridx = 1;
		gbc_vornameNeuField.gridy = 3;
		add(vornameNeuField, gbc_vornameNeuField);
		
		nachnameNeuField = new JTextField();
		nachnameNeuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nachnameNeuField.setText("Nachname eingeben...");
		nachnameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameNeuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nachnameNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nachnameNeuField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		nachnameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_nachnameNeuField = new GridBagConstraints();
		gbc_nachnameNeuField.insets = new Insets(0, 0, 5, 5);
		gbc_nachnameNeuField.fill = GridBagConstraints.BOTH;
		gbc_nachnameNeuField.gridx = 2;
		gbc_nachnameNeuField.gridy = 3;
		add(nachnameNeuField, gbc_nachnameNeuField);
		
		plzNeuField = new JTextField();
		plzNeuField.setText("PLZ eingeben...");
		plzNeuField.setHorizontalAlignment(SwingConstants.CENTER);
		plzNeuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		plzNeuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				plzNeuField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		plzNeuField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_plzNeuField = new GridBagConstraints();
		gbc_plzNeuField.insets = new Insets(0, 0, 5, 5);
		gbc_plzNeuField.fill = GridBagConstraints.BOTH;
		gbc_plzNeuField.gridx = 3;
		gbc_plzNeuField.gridy = 3;
		add(plzNeuField, gbc_plzNeuField);
		
		generiertField = new JTextField();
		generiertField.setText(" Generierte ID erscheint hier");
		generiertField.setEditable(false);
		generiertField.setHorizontalAlignment(SwingConstants.CENTER);
		generiertField.setFont(new Font("Palatino", Font.PLAIN, 14));
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		generiertField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_generiertField = new GridBagConstraints();
		gbc_generiertField.insets = new Insets(0, 0, 5, 5);
		gbc_generiertField.fill = GridBagConstraints.BOTH;
		gbc_generiertField.gridx = 4;
		gbc_generiertField.gridy = 3;
		add(generiertField, gbc_generiertField);
		
		speichernButton = new JButton("Speichern");
		speichernButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_speichernButton = new GridBagConstraints();
		gbc_speichernButton.insets = new Insets(0, 0, 5, 5);
		gbc_speichernButton.fill = GridBagConstraints.VERTICAL;
		gbc_speichernButton.gridx = 5;
		gbc_speichernButton.gridy = 3;
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String vornameInput = vornameNeuField.getText();
				String nachnameInput = nachnameNeuField.getText();
				String plzInput = plzNeuField.getText();
				
				vornameNeuField.setText("Vorname eingeben...");
				nachnameNeuField.setText("Nachname eingeben...");
				plzNeuField.setText("PLZ eingeben...");
				
				// Check if all Fields have been filled out
				if (vornameInput.equals("") || vornameInput.equals("Vorname eingeben...") || nachnameInput.equals("") || nachnameInput.equals("Nachname eingeben...") || plzInput.equals("") || plzInput.equals("PLZ eingeben...") || !plzInput.matches("[0-9]+") || plzInput.length() != 5) {
					setErrMessage(neuLabel);
					return;
				}

				for (int i = 0; i < DBAccess.getK().toArray().length; i++)
					if (DBAccess.getK().get(i).getVorname().equals(vornameInput) && DBAccess.getK().get(i).getNachname().equals(nachnameInput) && DBAccess.getK().get(i).getPlz() == Integer.parseInt(plzInput)) {
						neuLabel.setForeground(Color.RED);
						neuLabel.setText("Kunde bereits vorhanden! (ID: " + DBAccess.getK().get(i).getKundenID() + ")");
						return;
					}

				DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
				neuLabel.setText("");
				neuLabel.setForeground(Color.BLACK);
				kundeAufnehmen(vornameInput, nachnameInput, Integer.parseInt(plzInput));
				neuLabel.setText("Kunde erfolgreich aufgenommen.");
				DBAccess.dbResetAutoIncrement("KundenID", "kunden");

				generiertField.setText(String.valueOf(DBAccess.getK().toArray().length));
				vornameNeuField.setText("Bitte Name eingeben...");
				return;
			}
		});
		add(speichernButton, gbc_speichernButton);
		
		neuLabel = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		neuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		neuLabel.setForeground(Color.RED);
		neuLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_neuLabel = new GridBagConstraints();
		gbc_neuLabel.anchor = GridBagConstraints.WEST;
		gbc_neuLabel.insets = new Insets(0, 0, 5, 5);
		gbc_neuLabel.gridx = 1;
		gbc_neuLabel.gridy = 4;
		add(neuLabel, gbc_neuLabel);
		
		
		// Kunde bearbeiten
		bearbeitenLabel = new JLabel("Kunde bearbeiten:");
		bearbeitenLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bearbeitenLabel = new GridBagConstraints();
		gbc_bearbeitenLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenLabel.gridx = 1;
		gbc_bearbeitenLabel.gridy = 6;
		add(bearbeitenLabel, gbc_bearbeitenLabel);
		
		idBearbeitenFeld = new JTextField();
		idBearbeitenFeld.setText("Bitte ID eingeben...");
		idBearbeitenFeld.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenFeld.setFont(new Font("Palatino", Font.PLAIN, 14));
		idBearbeitenFeld.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idBearbeitenFeld.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		bearbeitenIDLabel = new JLabel("Kunden-ID:");
		bearbeitenIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_bearbeitenIDLabel = new GridBagConstraints();
		gbc_bearbeitenIDLabel.anchor = GridBagConstraints.WEST;
		gbc_bearbeitenIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bearbeitenIDLabel.gridx = 1;
		gbc_bearbeitenIDLabel.gridy = 7;
		add(bearbeitenIDLabel, gbc_bearbeitenIDLabel);
		idBearbeitenFeld.setBorder(new LineBorder(Color.BLACK, 1));
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
		gbc_bearbeitenButton.gridx = 2;
		gbc_bearbeitenButton.gridy = 8;
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				String idInput = idBearbeitenFeld.getText();
				idBearbeitenFeld.setText("Bitte ID eingeben...");

				// Check User Input
				if (!idInput.matches("[0-9]+") || idInput.equals("Bitte ID eingeben...") || idInput.equals("")) {
					setErrMessage(changeLabel);
					return;
				}

				if (idInput.equals("0")) {
					changeLabel.setText("ID: 0 existiert nicht.");
					return;
				}
				
				if (Integer.parseInt(idInput) > DBAccess.getK().toArray().length) {
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
		add(bearbeitenButton, gbc_bearbeitenButton);
		
		addRechnungButton = new JButton("Neue Rechnung");
		addRechnungButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_addRechnungButton = new GridBagConstraints();
		gbc_addRechnungButton.fill = GridBagConstraints.VERTICAL;
		gbc_addRechnungButton.insets = new Insets(0, 0, 5, 5);
		gbc_addRechnungButton.gridx = 3;
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
				
				if (Integer.parseInt(idInput) > DBAccess.getK().toArray().length) {
					changeLabel.setForeground(Color.RED);
					changeLabel.setText("ID existiert nicht.");
					return;
				}

				int id = Integer.parseInt(idInput);

				try {
					popups.KundenRechnungenNeu dialog = new popups.KundenRechnungenNeu(id, getVorname(id), getNachname(id), getPLZ(id), (DBAccess.getKr().toArray().length + 1));
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
		add(addRechnungButton, gbc_addRechnungButton);
		
		changeLabel = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeLabel.setForeground(Color.RED);
		changeLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_changeLabel = new GridBagConstraints();
		gbc_changeLabel.anchor = GridBagConstraints.WEST;
		gbc_changeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_changeLabel.gridx = 1;
		gbc_changeLabel.gridy = 9;
		add(changeLabel, gbc_changeLabel);
		
		
		// Kunde suchen
		bestehendLabel = new JLabel("Kunde suchen:");
		bestehendLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		GridBagConstraints gbc_bestehendLabel = new GridBagConstraints();
		gbc_bestehendLabel.anchor = GridBagConstraints.WEST;
		gbc_bestehendLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bestehendLabel.gridx = 1;
		gbc_bestehendLabel.gridy = 11;
		add(bestehendLabel, gbc_bestehendLabel);
		
		vornameSuchenField = new JTextField();
		vornameSuchenField.setText("Vorname eingeben...");
		vornameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		vornameSuchenField.setColumns(10);
		vornameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_vornameSuchenField = new GridBagConstraints();
		gbc_vornameSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_vornameSuchenField.fill = GridBagConstraints.BOTH;
		gbc_vornameSuchenField.gridx = 1;
		gbc_vornameSuchenField.gridy = 13;
		vornameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				vornameSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		
		suchenVornameLabel = new JLabel("Vorname:");
		suchenVornameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		GridBagConstraints gbc_suchenVornameLabel = new GridBagConstraints();
		gbc_suchenVornameLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenVornameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenVornameLabel.gridx = 1;
		gbc_suchenVornameLabel.gridy = 12;
		add(suchenVornameLabel, gbc_suchenVornameLabel);
		
		suchenNachnameLabel = new JLabel("Nachname:");
		suchenNachnameLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		suchenNachnameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_suchenNachnameLabel = new GridBagConstraints();
		gbc_suchenNachnameLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenNachnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenNachnameLabel.gridx = 2;
		gbc_suchenNachnameLabel.gridy = 12;
		add(suchenNachnameLabel, gbc_suchenNachnameLabel);
		
		suchenPLZLabel = new JLabel("PLZ:");
		suchenPLZLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		suchenPLZLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_suchenPLZLabel = new GridBagConstraints();
		gbc_suchenPLZLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenPLZLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenPLZLabel.gridx = 3;
		gbc_suchenPLZLabel.gridy = 12;
		add(suchenPLZLabel, gbc_suchenPLZLabel);
		
		suchenIDLabel = new JLabel("Kunden-ID:");
		suchenIDLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
		suchenIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_suchenIDLabel = new GridBagConstraints();
		gbc_suchenIDLabel.anchor = GridBagConstraints.WEST;
		gbc_suchenIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_suchenIDLabel.gridx = 4;
		gbc_suchenIDLabel.gridy = 12;
		add(suchenIDLabel, gbc_suchenIDLabel);
		add(vornameSuchenField, gbc_vornameSuchenField);
		
		nachnameSuchenField = new JTextField();
		nachnameSuchenField.setText("Nachname eingeben...");
		nachnameSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nachnameSuchenField.setColumns(10);
		nachnameSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_nachnameSuchenField = new GridBagConstraints();
		gbc_nachnameSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_nachnameSuchenField.fill = GridBagConstraints.BOTH;
		gbc_nachnameSuchenField.gridx = 2;
		gbc_nachnameSuchenField.gridy = 13;
		nachnameSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nachnameSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(nachnameSuchenField, gbc_nachnameSuchenField);
		
		plzSuchenField = new JTextField();
		plzSuchenField.setText("PLZ eingeben...");
		plzSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		plzSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		plzSuchenField.setColumns(10);
		plzSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_plzSuchenField = new GridBagConstraints();
		gbc_plzSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_plzSuchenField.fill = GridBagConstraints.BOTH;
		gbc_plzSuchenField.gridx = 3;
		gbc_plzSuchenField.gridy = 13;
		plzSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				plzSuchenField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		add(plzSuchenField, gbc_plzSuchenField);
		
		idSuchenField = new JTextField();
		idSuchenField.setText("Bitte ID eingeben...");
		idSuchenField.setHorizontalAlignment(SwingConstants.CENTER);
		idSuchenField.setFont(new Font("Palatino", Font.PLAIN, 14));
		idSuchenField.setColumns(10);
		idSuchenField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_idSuchenField = new GridBagConstraints();
		gbc_idSuchenField.insets = new Insets(0, 0, 5, 5);
		gbc_idSuchenField.fill = GridBagConstraints.BOTH;
		gbc_idSuchenField.gridx = 4;
		gbc_idSuchenField.gridy = 13;
		idSuchenField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idSuchenField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		add(idSuchenField, gbc_idSuchenField);
		
		suchenButton = new JButton("Suchen");
		suchenButton.setFont(new Font("Palatino", Font.BOLD, 16));
		GridBagConstraints gbc_suchenButton = new GridBagConstraints();
		gbc_suchenButton.fill = GridBagConstraints.VERTICAL;
		gbc_suchenButton.insets = new Insets(0, 0, 5, 5);
		gbc_suchenButton.gridx = 5;
		gbc_suchenButton.gridy = 13;
		suchenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ArrayList<String> list = new ArrayList<String> ();
				
				String vornameInput = vornameSuchenField.getText();
				String nachnameInput = nachnameSuchenField.getText();
				String plzInput = plzSuchenField.getText();
				String idInput = idSuchenField.getText();
				
				boolean skip = false;
				
				// Keine Eingabe
				if ((idInput.equals("Bitte ID eingeben...") || idInput.equals("")) && (vornameInput.equals("Vorname eingeben...") || vornameInput.equals("")) && (nachnameInput.equals("Nachname eingeben...") || nachnameInput.equals("")) && (plzInput.equals("PLZ eingeben...") || plzInput.equals(""))) {
					list = tableOfAll();
					skip = true;
				}

				// Check ID Input
				if (((!idInput.matches("[0-9]+") && !idInput.equals("Bitte ID eingeben...")) && !idInput.equals(""))) {
					setErrMessage(suchenLabel);
					return;
				}
				
				if (idInput.equals("0")) {
					suchenLabel.setText("ID: 0 existiert nicht.");
				};
				
				// ID eingegeben
				if (idInput.matches("[0-9]+") && !skip) {
					list = tableByID(Integer.parseInt(idInput));
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				// Check PLZ Input
				if ((!plzInput.equals("PLZ eingeben...") && !plzInput.equals("")) && (plzInput.length() != 5 || !plzInput.matches("[0-9]+")) && !skip) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Check Vorname Input
				if (!vornameInput.equals("Vorname eingeben...") && !vornameInput.equals("") && !vornameInput.matches("[a-zA-Z]+") && !skip) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Check Nachname Input
				if (!nachnameInput.equals("Nachname eingeben...") && !nachnameInput.equals("") && !nachnameInput.matches("[a-zA-Z]+") && !skip) {
					setErrMessage(suchenLabel);
					return;
				}
				
				// Vor- und Nachname + PLZ eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && nachnameInput.matches("[a-zA-Z]+") && plzInput.length() == 5 && (!idInput.equals("") || !idInput.equals("Bitte ID eingeben...")) && !skip) {
					list = tableByVorUndNachnameUndPLZ(vornameInput, nachnameInput, Integer.parseInt(plzInput));
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				// Vor- und Nachname eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && nachnameInput.matches("[a-zA-Z]+") && (plzInput.equals("PLZ eingeben...") || plzInput.equals("")) && (idInput.equals("") || idInput.equals("Bitte ID eingeben...")) && !skip) {
					list = tableByVorUndNachname(vornameInput, nachnameInput);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				// Vorname und PLZ eingegeben
				if (vornameInput.matches("[a-zA-Z]+") && plzInput.matches("[0-9]+") && (nachname.equals("Nachname eingeben...") || nachname.equals("")) && (idInput.equals("") || idInput.equals("Bitte ID eingeben...")) && !skip) {
					list = tableByVornameUndPLZ(vornameInput, Integer.parseInt(plzInput));
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}

				// Nachname und PLZ eingegeben
				if (nachnameInput.matches("[a-zA-Z]+") && plzInput.matches("[0-9]+") && (vorname.equals("Vorname eingeben...") || vorname.equals("")) && (idInput.equals("") || idInput.equals("Bitte ID eingeben...")) && !skip) {
					list = tableByNachnameUndPLZ(nachnameInput, Integer.parseInt(plzInput));
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}

				// Nur Vorname
				if (vornameInput.matches("[a-zA-Z]+") && !skip) {
					list = tableByVorname(vornameInput);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				// Nur Nachname
				if (nachnameInput.matches("[a-zA-Z]+") && !skip) {
					list = tableByNachname(nachnameInput);
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				// Nur PLZ
				if (plzInput.length() == 5 && !skip) {
					list = tableByPLZ(Integer.parseInt(plzInput));
					resetFields(vornameSuchenField, nachnameSuchenField, plzSuchenField, idSuchenField);
					skip = true;
				}
				
				
				// Set table 
				String[][] array = new String[(list.toArray().length)/4][4];
				int counter = 0;

				for (int i = 0; i < (list.toArray().length)/4; i++)
					for (int j = 0; j < 4; j++) {
						array[i][j] = "  " + list.get(counter);
						counter++;
					}

				DefaultTableModel tableModel = new DefaultTableModel(array, new Object[] { "Kunden-ID", "Vorname", "Nachname", "PLZ"});
				table.setModel(tableModel);
				skip = false;
				return;
				
			}
		});
		add(suchenButton, gbc_suchenButton);
		
		suchenLabel = new JLabel("Ohne Eingabe suchen um alle Kunden auszugeben.");
		suchenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		suchenLabel.setForeground(Color.DARK_GRAY);
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
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.gridheight = 5;
		gbc_pane.gridwidth = 5;
		gbc_pane.insets = new Insets(0, 0, 5, 5);
		gbc_pane.fill = GridBagConstraints.BOTH;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 16;
		add(pane, gbc_pane);
		
		
		
	}


	// Edit Kunden

		// Add Kunde to objects and call dbAddLieferant
		private static void kundeAufnehmen(String vorname, String nachname, int plz) {
			int newID = DBAccess.getK().toArray().length + 1;
			DBAccess.getK().add(new Kunde(newID, vorname, nachname, plz));
			dbAddKunde(vorname, nachname, plz);
			DBAccess.dbResetAutoIncrement("KundenID", "kunden");
		}

		// Add Kunde in mySQL database
		public static void dbAddKunde(String vorname, String nachname, int plz) {
			DBAccess.dbResetAutoIncrement("KundenID", "kunden");
			try {
				Statement stmt = DBAccess.conn.createStatement();
				stmt.execute("INSERT INTO kunden (Vorname, Nachname, PLZ) VALUES ('" + vorname + "', '" + nachname + "', '" + plz + "')");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Change Kunde in objects and call dbChangeLieferant
		public static void kundeBearbeiten(int id, String vorname, String nachname, int plz) {
			DBAccess.dbResetAutoIncrement("KundenID", "kunden");
			DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
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
			int newID = DBAccess.getKr().toArray().length + 1;
			DBAccess.getKr().add(new Kundenrechnung(newID, monat, jahr, bestellvolumen, status, kundenID));
			dbAddRechnung(kundenID, monat, jahr, bestellvolumen, status);
			DBAccess.dbResetAutoIncrement("RechnungsID", "kundenrechnungen");
		}

		// Add invoice in mySQL database
		public static void dbAddRechnung(int kundenID, int monat, int jahr, double bestellvolumen, boolean status) {
			try {
				Statement stmt = DBAccess.conn.createStatement();
				int tinyInt = 0;
				if (status) tinyInt = 1;
				stmt.execute("INSERT INTO kundenrechnungen (KundenID, Monat, Jahr, Summe, Status) VALUES ('" + kundenID + "', '" + monat + "', '" + jahr + "', '" + bestellvolumen + "', '" + tinyInt + "')");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	// Auxiliary functions

		// Get vorname
		private static String getVorname (int id) {
			DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				vorname = x.getVorname();
			});
			return vorname;
		}
		// Get nachname
		private static String getNachname (int id) {
			DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				nachname = x.getNachname();
			});
			return nachname;
		}
		// Get plz
		private static int getPLZ (int id) {
			DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
				plz = x.getPlz();
			});
			return plz;
		}
		
		// Set label to empty String
		private static void resetLabel(JLabel label) {
			label.setText("⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		}

		// Set label to display error message
		private static void setErrMessage (JLabel inputLabel) {
			inputLabel.setForeground(Color.RED);
			inputLabel.setText("Bitte alle Felder korrekt ausfüllen.");
			return;
		}

		// Reset input fields
		private static void resetFields (JTextField vornameSuchenField, JTextField nachnameSuchenField, JTextField plzSuchenField, JTextField idSuchenField) {
			vornameSuchenField.setText("Vorname eingeben...");
			nachnameSuchenField.setText("Nachname eingeben...");
			plzSuchenField.setText("PLZ eingeben...");
			idSuchenField.setText("Bitte ID eingeben...");
		}
		
		
	// Tabellen erstellen (suchen)

		// Create table if user only inputs first name
		private static ArrayList<String> tableByVorname(String vorname) {

			ArrayList<String> table = new ArrayList<String>();

			DBAccess.getK().stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase())).forEach(x -> {
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

			DBAccess.getK().stream().filter(x -> x.getNachname().toLowerCase().contains(nachname.toLowerCase())).forEach(x -> {
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

			DBAccess.getK().stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getNachname().toLowerCase().contains(nachname.toLowerCase())).forEach(x -> {
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

			DBAccess.getK().stream().filter(x -> x.getKundenID() == id).forEach(x -> {
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

			DBAccess.getK().stream().filter(x -> x.getPlz() == plz)
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

			DBAccess.getK().stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
				table.add(String.valueOf(x.getKundenID()));
				table.add(x.getVorname());
				table.add(x.getNachname());
				table.add(String.valueOf(x.getPlz()));
			});

			return table;
		}
		
		private static ArrayList<String> tableByNachnameUndPLZ(String nachname, int plz) {

			ArrayList<String> table = new ArrayList<String>();

			DBAccess.getK().stream().filter(x -> x.getNachname().toLowerCase().contains(nachname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
				table.add(String.valueOf(x.getKundenID()));
				table.add(x.getVorname());
				table.add(x.getNachname());
				table.add(String.valueOf(x.getPlz()));
			});

			return table;
		}
		
		private static ArrayList<String> tableByVorUndNachnameUndPLZ(String vorname, String nachname, int plz) {

			ArrayList<String> table = new ArrayList<String>();

			DBAccess.getK().stream().filter(x -> x.getVorname().toLowerCase().contains(vorname.toLowerCase()) && x.getNachname().toLowerCase().contains(nachname.toLowerCase()) && x.getPlz() == plz).forEach(x -> {
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

			DBAccess.getK().stream().forEach(x -> {
				table.add(String.valueOf(x.getKundenID()));
				table.add(x.getVorname());
				table.add(x.getNachname());
				table.add(String.valueOf(x.getPlz()));
			});

			return table;
		}
	}