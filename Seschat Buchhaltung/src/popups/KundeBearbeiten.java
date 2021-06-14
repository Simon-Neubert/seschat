package popups;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class KundeBearbeiten extends JDialog{
	
	private JTextField vornameField;
	private JButton speichernButton;
	private JLabel altLabel;
	private JLabel inputLabel;
	private JLabel vornameLabel;
	private JTextField nachnameField;
	private JTextField plzField;
	
	public KundeBearbeiten(int idAlt, String vornameAlt, String nachnameAlt, int plz){
		
		setBounds(0, 0, 400, 535);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 249, 40, 0};
		gridBagLayout.rowHeights = new int[]{0, 29, 32, 0, 37, 39, 37, 0, 44, 0, 37, 35, 23, 51, -31, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		speichernButton = new JButton("Speichern");
		speichernButton.setFont(new Font("Palatino", Font.BOLD, 16));
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String vornameInput = vornameField.getText();
				String nachnameInput = nachnameField.getText();
				String plzInput = plzField.getText();
				
				// Check User Input
				if (vornameInput.equals("") || !vornameInput.toLowerCase().matches("[a-z]+") || nachnameInput.equals("") || !nachnameInput.toLowerCase().matches("[a-z]+") || !plzInput.matches("[0-9]+") || plzInput.equals("") || plzInput.length() != 5) {
					inputLabel.setForeground(Color.RED);
					inputLabel.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				gui.Kunden.kundeBearbeiten(idAlt, vornameInput, nachnameInput, Integer.parseInt(plzInput));
				gui.Kunden.abgebrochen = false;
				dispose();
			}
		});
		
		JLabel kundeLabel = new JLabel("Kunde:");
		kundeLabel.setEnabled(false);
		kundeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kundeLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		kundeLabel.setForeground(Color.BLACK);
		GridBagConstraints gbc_kundeLabel = new GridBagConstraints();
		gbc_kundeLabel.fill = GridBagConstraints.VERTICAL;
		gbc_kundeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_kundeLabel.gridx = 1;
		gbc_kundeLabel.gridy = 1;
		getContentPane().add(kundeLabel, gbc_kundeLabel);
		
		altLabel = new JLabel(vornameAlt + " " + nachnameAlt + " " + " (ID: " + idAlt + ")");
		altLabel.setHorizontalAlignment(SwingConstants.CENTER);
		altLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_altLabel = new GridBagConstraints();
		gbc_altLabel.fill = GridBagConstraints.VERTICAL;
		gbc_altLabel.insets = new Insets(0, 0, 5, 5);
		gbc_altLabel.gridx = 1;
		gbc_altLabel.gridy = 2;
		getContentPane().add(altLabel, gbc_altLabel);
		
		vornameLabel = new JLabel("Vorname:");
		vornameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vornameLabel.setForeground(Color.BLACK);
		vornameLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		vornameLabel.setEnabled(false);
		GridBagConstraints gbc_vornameLabel = new GridBagConstraints();
		gbc_vornameLabel.fill = GridBagConstraints.VERTICAL;
		gbc_vornameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_vornameLabel.gridx = 1;
		gbc_vornameLabel.gridy = 4;
		getContentPane().add(vornameLabel, gbc_vornameLabel);
		
		vornameField = new JTextField(vornameAlt);
		vornameField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameField.setFont(new Font("Palatino", Font.PLAIN, 14));
		vornameField.setColumns(10);
		vornameField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_vornameField = new GridBagConstraints();
		gbc_vornameField.fill = GridBagConstraints.BOTH;
		gbc_vornameField.insets = new Insets(0, 0, 5, 5);
		gbc_vornameField.gridx = 1;
		gbc_vornameField.gridy = 5;
		getContentPane().add(vornameField, gbc_vornameField);
		
		JLabel nachnameLabel = new JLabel("Nachname:");
		nachnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameLabel.setForeground(Color.BLACK);
		nachnameLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		nachnameLabel.setEnabled(false);
		GridBagConstraints gbc_nachnameLabel = new GridBagConstraints();
		gbc_nachnameLabel.fill = GridBagConstraints.VERTICAL;
		gbc_nachnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nachnameLabel.gridx = 1;
		gbc_nachnameLabel.gridy = 6;
		getContentPane().add(nachnameLabel, gbc_nachnameLabel);
		
		nachnameField = new JTextField(nachnameAlt);
		nachnameField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameField.setFont(new Font("Palatino", Font.PLAIN, 14));
		nachnameField.setColumns(10);
		nachnameField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_nachnameField = new GridBagConstraints();
		gbc_nachnameField.fill = GridBagConstraints.BOTH;
		gbc_nachnameField.insets = new Insets(0, 0, 5, 5);
		gbc_nachnameField.gridx = 1;
		gbc_nachnameField.gridy = 8;
		getContentPane().add(nachnameField, gbc_nachnameField);
		
		JLabel plzLabel = new JLabel("PLZ:");
		plzLabel.setHorizontalAlignment(SwingConstants.CENTER);
		plzLabel.setForeground(Color.BLACK);
		plzLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		plzLabel.setEnabled(false);
		GridBagConstraints gbc_plzLabel = new GridBagConstraints();
		gbc_plzLabel.fill = GridBagConstraints.VERTICAL;
		gbc_plzLabel.insets = new Insets(0, 0, 5, 5);
		gbc_plzLabel.gridx = 1;
		gbc_plzLabel.gridy = 10;
		getContentPane().add(plzLabel, gbc_plzLabel);
		
		plzField = new JTextField(String.valueOf(plz));
		plzField.setHorizontalAlignment(SwingConstants.CENTER);
		plzField.setFont(new Font("Palatino", Font.PLAIN, 14));
		plzField.setColumns(10);
		plzField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_plzField = new GridBagConstraints();
		gbc_plzField.fill = GridBagConstraints.BOTH;
		gbc_plzField.insets = new Insets(0, 0, 5, 5);
		gbc_plzField.gridx = 1;
		gbc_plzField.gridy = 11;
		getContentPane().add(plzField, gbc_plzField);
		GridBagConstraints gbc_speichernButton = new GridBagConstraints();
		gbc_speichernButton.fill = GridBagConstraints.VERTICAL;
		gbc_speichernButton.insets = new Insets(0, 0, 5, 5);
		gbc_speichernButton.gridx = 1;
		gbc_speichernButton.gridy = 13;
		getContentPane().add(speichernButton, gbc_speichernButton);
		
		inputLabel = new JLabel("         ");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_inputLabel = new GridBagConstraints();
		gbc_inputLabel.insets = new Insets(0, 0, 5, 5);
		gbc_inputLabel.fill = GridBagConstraints.BOTH;
		gbc_inputLabel.gridx = 1;
		gbc_inputLabel.gridy = 14;
		getContentPane().add(inputLabel, gbc_inputLabel);
		
	
	} 
}
