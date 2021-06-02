package gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import objects.*;

import javax.swing.JTable;

public class Kunden extends JPanel{

	// Auslesen DB
	static ArrayList <objects.Kunde> k = dbaccess.DBAccess.createKunden();
	static ArrayList <objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
	
	private JTextField textFieldVorname;
	private JTextField textFieldName;
	private JTextField textFieldPlz;
	private JButton btnNewButton;
	private JTextField textFieldID;
	private JTextField idField;
	private JTextField nameField;
	private JTable table;
	private JLabel neuerKundeLabel;
	private JTextField vornameField;
	private JTextField nachnameField;
	private JTextField textField;
	private JButton speichernButton;
	private JTextField generierteIDField;
	
	public Kunden () {
		
	}
	
}