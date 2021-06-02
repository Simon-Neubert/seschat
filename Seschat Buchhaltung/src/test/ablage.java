package test;

import java.sql.SQLException;

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
}
