package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
}
