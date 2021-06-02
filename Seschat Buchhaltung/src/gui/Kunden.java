package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import objects.*;

import com.jgoodies.forms.layout.FormSpecs;
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
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:pref:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Bestehender Kunde:");
		add(lblNewLabel, "4, 4");
		
		idField = new JTextField();
		add(idField, "4, 8, fill, default");
		idField.setColumns(10);
		
		nameField = new JTextField();
		add(nameField, "6, 8, 3, 1, fill, default");
		nameField.setColumns(10);
		
		JButton suchenButton = new JButton("Suchen");
		add(suchenButton, "10, 8");
		
		table = new JTable();
		add(table, "4, 12, 7, 13, fill, fill");
		
		neuerKundeLabel = new JLabel("Neuer Kunde:");
		add(neuerKundeLabel, "4, 28");
		
		vornameField = new JTextField();
		add(vornameField, "4, 30, fill, default");
		vornameField.setColumns(10);
		
		nachnameField = new JTextField();
		add(nachnameField, "6, 30, 3, 1, fill, default");
		nachnameField.setColumns(10);
		
		textField = new JTextField();
		add(textField, "10, 30, fill, default");
		textField.setColumns(10);
		
		speichernButton = new JButton("Speichern");
		add(speichernButton, "4, 32");
		
		generierteIDField = new JTextField();
		generierteIDField.setColumns(10);
		add(generierteIDField, "6, 32, 5, 1, fill, default");
		
		
		
	}
	

}