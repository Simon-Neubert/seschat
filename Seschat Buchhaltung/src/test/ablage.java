package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import dbaccess.DBAccess;

public class ablage {


	/*
	public Lieferanten () {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		JLabel bestehendLabel = new JLabel("Bestehender Lieferant:");
		add(bestehendLabel, "4, 4");
		bestehendLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		
		idField = new JTextField();
		add(idField, "4, 8, fill, default");
		idField.setColumns(10);
		idField.setBorder(new LineBorder(Color.BLACK, 1));
		idField.setText(" ID eingeben...");
		idField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				idField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		nameField = new JTextField();
		add(nameField, "6, 8, fill, default");
		nameField.setColumns(10);
		nameField.setBorder(new LineBorder(Color.BLACK, 1));
		nameField.setText(" Name eingeben...");
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		JButton suchenButton = new JButton("Suchen");
		add(suchenButton, "8, 8");
		
		lieferantenTable = new JTable();
		add(lieferantenTable, "4, 10, 5, 13, fill, fill");
		lieferantenTable.setBorder(new LineBorder(Color.BLACK, 1));
		
		JLabel neuerLieferantLabel = new JLabel("Neuer Lieferant:");
		add(neuerLieferantLabel, "4, 28");
		neuerLieferantLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		
		generierteIDField = new JTextField();
		add(generierteIDField, "4, 32, fill, default");
		generierteIDField.setColumns(10);
		generierteIDField.setBorder(new LineBorder(Color.BLACK, 1));
		generierteIDField.setText("  ID erscheint hier");
		
		neuNameField = new JTextField();
		add(neuNameField, "6, 32, fill, default");
		neuNameField.setColumns(10);
		neuNameField.setBorder(new LineBorder(Color.BLACK, 1));
		neuNameField.setText("  Name eingeben...");
		neuNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				neuNameField.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		
		JButton speichernButton = new JButton("Speichern");
		add(speichernButton, "8, 32");
	
	}
	*/
	
	/*
	 
	 
	public static void main(String[] args) {
		// Test
		//dbAddLieferant("Boser");
		System.out.println(DBAccess.dbGetAutoIncrement("LieferantenID", "lieferanten"));
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
		
		try {
			DBAccess.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
} 
	  
	 */
	/*
	public static void dbDeleteLieferant (int id) {
		try {
			Statement stmt = DBAccess.conn.createStatement();
			stmt.execute("DELETE FROM lieferanten WHERE LieferantenID = '"+id+"'");
	        // Backup:
			//PreparedStatement ps = DBAccess.conn.prepareStatement("DELETE FROM lieferanten WHERE LieferantenID = ?");
	        //ps.setInt(?);
	        //ps.executeUpdate();
	        
			DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
		} catch (Exception e) {e.printStackTrace();}
	}
	

	private static void lieferantLoeschen (int id) {
		l.remove(id);
		dbDeleteLieferant(id);
		DBAccess.dbResetAutoIncrement("LieferantenID", "lieferanten");
	}
	*/
	
	/*
	JLabel bearbeitenLabel = new JLabel("Lieferant bearbeiten:");
		bearbeitenLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		bearbeitenLabel.setBounds(1365, 75, 219, 32);
		add(bearbeitenLabel);
		
		idBearbeitenField = new JTextField();
		idBearbeitenField.setText("   Bitte ID eingeben...");
		idBearbeitenField.setHorizontalAlignment(SwingConstants.CENTER);
		idBearbeitenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		idBearbeitenField.setColumns(10);
		idBearbeitenField.setBorder(new LineBorder(Color.BLACK, 1));
		idBearbeitenField.setBounds(1365, 160, 219, 53);
		add(idBearbeitenField);
		
		nameBearbeitenField = new JTextField();
		nameBearbeitenField.setText("   Bitte Name eingeben...");
		nameBearbeitenField.setHorizontalAlignment(SwingConstants.CENTER);
		nameBearbeitenField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameBearbeitenField.setColumns(10);
		nameBearbeitenField.setBorder(new LineBorder(Color.BLACK, 1));
		nameBearbeitenField.setBounds(1365, 281, 219, 53);
		add(nameBearbeitenField);
		
		JLabel inputLabel3 = new JLabel("");
		inputLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel3.setForeground(Color.RED);
		inputLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel3.setBounds(1441, 833, 456, 26);
		add(inputLabel3);
		
		JButton bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.setForeground(new Color(30, 144, 255));
		bearbeitenButton.setBackground(new Color(30, 144, 255));
		bearbeitenButton.setBounds(1390, 392, 170, 50);
		bearbeitenButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String nameInput = nameBearbeitenField.getText();
				String idInput = idBearbeitenField.getText();
				nameSuchenField.setText("   Bitte Name eingeben...");
				idSuchenField.setText("   Bitte ID eingeben...");
				
				// Check User Input
				if (!idInput.matches("[0-9]+") && !idInput.equals("   Bitte ID eingeben...")) {
					inputLabel3.setForeground(Color.RED);
					inputLabel3.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				
				
			}
		});
		add(bearbeitenButton);

	 */
	
	/*
	 
			MaskFormatter mask = null;
			try {
				mask = new MaskFormatter("#0.##");
				mask.setValidCharacters("0123456789");
				mask.setPlaceholderCharacter('0');
				mask.setAllowsInvalid(false);
			
			} catch (Exception e) {e.printStackTrace();}
			
			NumberFormat money = NumberFormat.getCurrencyInstance();
			money.setMinimumFractionDigits(2);
			money.setMaximumFractionDigits(2);
			money.setMinimumIntegerDigits(1);
			
			DecimalFormatSymbols dot = new DecimalFormatSymbols();
			dot.setDecimalSeparator('.'); 
			DecimalFormat format = new DecimalFormat ("#0.##", dot);
			
	 */
	
	
	
	/*
	 		// Neue Rechnung
			
			JLabel labelrechnungNeu = new JLabel("Neue Rechnung:");
			labelrechnungNeu.setFont(new Font("Serif", Font.PLAIN, 25));
			labelrechnungNeu.setBounds(101, 90, 395, 32);
			getContentPane().add(labelrechnungNeu);
			
			fillYears();
			JComboBox monatDropdown = new JComboBox(monate);
			monatDropdown.setMaximumRowCount(14);
			monatDropdown.setBounds(398, 136, 181, 78);
			getContentPane().add(monatDropdown);
			
			JComboBox jahrDropdown = new JComboBox(lastTenYears);
			jahrDropdown.setMaximumRowCount(14);
			jahrDropdown.setBounds(669, 136, 181, 78);
			getContentPane().add(jahrDropdown);
			
			vornameNeuField = new JTextField();
			vornameNeuField.setHorizontalAlignment(SwingConstants.CENTER);
			vornameNeuField.setFont(new Font("Sans", Font.PLAIN, 14));
			vornameNeuField.setBounds(101, 147, 219, 53);
			vornameNeuField.setBorder(new LineBorder(Color.BLACK, 1));
			vornameNeuField.setText("ID eingeben...");
			vornameNeuField.setColumns(10);
			vornameNeuField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					vornameNeuField.setText("");
				}
				public void focusLost(FocusEvent e) {
				}
			});
			getContentPane().add(vornameNeuField);
			
			summeNeuField = new JTextField();
			summeNeuField.setText("Summe eingeben...");
			summeNeuField.setHorizontalAlignment(SwingConstants.CENTER);
			summeNeuField.setFont(new Font("Dialog", Font.PLAIN, 14));
			summeNeuField.setColumns(10);
			summeNeuField.setBorder(new LineBorder(Color.BLACK, 1));
			summeNeuField.setBounds(940, 147, 219, 53);
			summeNeuField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					summeNeuField.setText("");
				}

				public void focusLost(FocusEvent e) {
				}
			});
			getContentPane().add(summeNeuField);
			
			generiertField = new JTextField();
			generiertField.setText(" Generierte ID erscheint hier");
			generiertField.setEditable(false);
			generiertField.setHorizontalAlignment(SwingConstants.CENTER);
			generiertField.setFont(new Font("Dialog", Font.PLAIN, 14));
			generiertField.setColumns(10);
			generiertField.setBorder(new LineBorder(Color.BLACK, 1));
			generiertField.setBounds(643, 224, 219, 53);
			getContentPane().add(generiertField);
		
			JLabel neuLabel = new JLabel("Eingabe pruefen");
			neuLabel.setHorizontalAlignment(SwingConstants.CENTER);
			neuLabel.setForeground(Color.RED);
			neuLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			neuLabel.setBounds(940, 238, 471, 26);
			getContentPane().add(neuLabel);
			
			JRadioButton kundeRadio = new JRadioButton("Kunde");
			buttonGroup.add(kundeRadio);
			kundeRadio.setSelected(true);
			kundeRadio.setBounds(101, 224, 127, 53);
			getContentPane().add(kundeRadio);
			
			JRadioButton lieferantRadio = new JRadioButton("Lieferant");
			buttonGroup.add(lieferantRadio);
			lieferantRadio.setBounds(222, 224, 92, 53);
			getContentPane().add(lieferantRadio);
			
			JRadioButton statusRadio = new JRadioButton("Bezahlt");
			statusRadio.setBounds(398, 224, 92, 53);
			getContentPane().add(statusRadio);
			
			JButton speichernButton = new JButton("Speichern");
			speichernButton.setBackground(new Color(30, 144, 255));
			speichernButton.setForeground(new Color(30, 144, 255));
			speichernButton.setBounds(1241, 150, 170, 50);
			speichernButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				}
			});
			getContentPane().add(speichernButton);
			
			
	 */
	
	
}
