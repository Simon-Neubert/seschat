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

public class KundeBearbeiten extends JDialog{
	
	private JTextField vornameField;
	private JButton speichernButton;
	private JLabel altLabel;
	private JLabel inputLabel;
	private JLabel vornameLabel;
	private JTextField nachnameField;
	private JTextField plzField;
	
	public KundeBearbeiten(int idAlt, String vornameAlt, String nachnameAlt, int plz){
		
		setBounds(800, 200, 400, 500);
		getContentPane().setLayout(null);
		
		JLabel kundeLabel = new JLabel("Kunde:");
		kundeLabel.setBounds(13, 34, 110, 29);
		kundeLabel.setEnabled(false);
		kundeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kundeLabel.setFont(new Font("Serif", Font.BOLD, 18));
		kundeLabel.setForeground(Color.BLACK);
		getContentPane().add(kundeLabel);
		
		altLabel = new JLabel(vornameAlt + " " + nachnameAlt + " " + " (ID: " + idAlt + ")");
		altLabel.setHorizontalAlignment(SwingConstants.CENTER);
		altLabel.setBounds(83, 34, 311, 29);
		altLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		getContentPane().add(altLabel);
		
		vornameField = new JTextField();
		vornameField.setHorizontalAlignment(SwingConstants.CENTER);
		vornameField.setFont(new Font("Dialog", Font.PLAIN, 14));
		vornameField.setColumns(10);
		vornameField.setBorder(new LineBorder(Color.BLACK, 1));
		vornameField.setBounds(176, 95, 153, 37);
		getContentPane().add(vornameField);
		vornameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				vornameField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		vornameLabel = new JLabel("Vorname:");
		vornameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vornameLabel.setForeground(Color.BLACK);
		vornameLabel.setFont(new Font("Serif", Font.BOLD, 18));
		vornameLabel.setEnabled(false);
		vornameLabel.setBounds(21, 100, 102, 29);
		getContentPane().add(vornameLabel);
		
		JLabel nachnameLabel = new JLabel("Nachname:");
		nachnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameLabel.setForeground(Color.BLACK);
		nachnameLabel.setFont(new Font("Serif", Font.BOLD, 18));
		nachnameLabel.setEnabled(false);
		nachnameLabel.setBounds(21, 176, 102, 29);
		getContentPane().add(nachnameLabel);
		
		JLabel plzLabel = new JLabel("PLZ:");
		plzLabel.setHorizontalAlignment(SwingConstants.CENTER);
		plzLabel.setForeground(Color.BLACK);
		plzLabel.setFont(new Font("Serif", Font.BOLD, 18));
		plzLabel.setEnabled(false);
		plzLabel.setBounds(21, 257, 102, 29);
		getContentPane().add(plzLabel);
		
		nachnameField = new JTextField();
		nachnameField.setHorizontalAlignment(SwingConstants.CENTER);
		nachnameField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nachnameField.setColumns(10);
		nachnameField.setBorder(new LineBorder(Color.BLACK, 1));
		nachnameField.setBounds(176, 171, 153, 37);
		getContentPane().add(nachnameField);
		
		plzField = new JTextField();
		plzField.setHorizontalAlignment(SwingConstants.CENTER);
		plzField.setFont(new Font("Dialog", Font.PLAIN, 14));
		plzField.setColumns(10);
		plzField.setBorder(new LineBorder(Color.BLACK, 1));
		plzField.setBounds(176, 252, 153, 37);
		getContentPane().add(plzField);
		
		inputLabel = new JLabel("");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel.setBounds(6, 401, 388, 29);
		getContentPane().add(inputLabel);
		
		speichernButton = new JButton("Speichern");
		speichernButton.setBounds(138, 337, 117, 45);
		speichernButton.setForeground(new Color(30, 144, 255));
		speichernButton.setBackground(new Color(30, 144, 255));
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String vornameInput = vornameField.getText();
				String nachnameInput = vornameField.getText();
				String plzInput = vornameField.getText();
				
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
		getContentPane().add(speichernButton);
		
	
	} 
}
