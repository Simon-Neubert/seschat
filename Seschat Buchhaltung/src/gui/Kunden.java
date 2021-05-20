package gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

public class Kunden extends JFrame{

	// Auslesen DB
	static ArrayList<objects.Kunde> k = dbaccess.DBAccess.createKunden();
	static ArrayList<objects.Lieferant> l = dbaccess.DBAccess.createLieferanten();
	static ArrayList<objects.Kundenrechnung> kr = dbaccess.DBAccess.createKundenrechnungen();
	static ArrayList<objects.Lieferantenrechnung> lr = dbaccess.DBAccess.createLieferantenrechnungen();
	private JTextField textFieldVorname;
	private JTextField textFieldName;
	private JTextField textFieldPlz;
	private JButton btnNewButton;
	private JTextField textFieldID;
	
	public Kunden () {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelKundeSuchen = new JLabel("Kunde suchen:");
		GridBagConstraints gbc_labelKundeSuchen = new GridBagConstraints();
		gbc_labelKundeSuchen.anchor = GridBagConstraints.NORTH;
		gbc_labelKundeSuchen.insets = new Insets(0, 0, 5, 5);
		gbc_labelKundeSuchen.gridx = 1;
		gbc_labelKundeSuchen.gridy = 1;
		panel.add(labelKundeSuchen, gbc_labelKundeSuchen);
		
		textFieldVorname = new JTextField();
		textFieldVorname.setText("Vorname...");
		GridBagConstraints gbc_textFieldVorname = new GridBagConstraints();
		gbc_textFieldVorname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVorname.gridx = 1;
		gbc_textFieldVorname.gridy = 3;
		panel.add(textFieldVorname, gbc_textFieldVorname);
		textFieldVorname.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setText("Name...");
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 4;
		panel.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPlz = new JTextField();
		textFieldPlz.setText("PLZ...");
		GridBagConstraints gbc_textFieldPlz = new GridBagConstraints();
		gbc_textFieldPlz.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPlz.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPlz.gridx = 1;
		gbc_textFieldPlz.gridy = 5;
		panel.add(textFieldPlz, gbc_textFieldPlz);
		textFieldPlz.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setText("Kunden-ID...");
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 1;
		gbc_textFieldID.gridy = 6;
		panel.add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(10);
		
		btnNewButton = new JButton("Suchen");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);
		
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kunden panel = new Kunden();
					panel.setExtendedState(MAXIMIZED_BOTH);
					panel.setVisible(true);
					panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					panel.setTitle("Seschat Buchhaltung");
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	
	}
	
}
