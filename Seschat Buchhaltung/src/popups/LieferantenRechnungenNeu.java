package popups;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class LieferantenRechnungenNeu extends JDialog {

	private JTextField textField;
	private JTextField bestellvolumenField;
	private JButton speichernButton;
	private JLabel lieferantLabel2;
	private JLabel inputLabel;
	private JLabel idLabel;
	private JLabel rechnungsIDLabel;
	private JLabel bestellvolumenLabel;
	private JLabel statusLabel;

	static String[] monate = {"Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String[] lastTenYears = new String[11];
	private JLabel formatLabel;
	
	
	public LieferantenRechnungenNeu (int idAlt, String lieferantAlt, int rechnungsID) {
			
			setBounds(800, 200, 400, 500);
			getContentPane().setLayout(null);
			
			//Label
			JLabel lieferantLabel = new JLabel("Lieferant:");
			lieferantLabel.setBounds(34, 35, 87, 29);
			lieferantLabel.setEnabled(false);
			lieferantLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lieferantLabel.setFont(new Font("Serif", Font.BOLD, 18));
			lieferantLabel.setForeground(Color.BLACK);
			getContentPane().add(lieferantLabel);
			
			lieferantLabel2 = new JLabel(lieferantAlt + " (ID: " + idAlt + ")");
			lieferantLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			lieferantLabel2.setBounds(133, 35, 261, 29);
			lieferantLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
			getContentPane().add(lieferantLabel2);
			
			idLabel = new JLabel("Rechnungs-ID:");
			idLabel.setHorizontalAlignment(SwingConstants.LEFT);
			idLabel.setForeground(Color.BLACK);
			idLabel.setFont(new Font("Serif", Font.BOLD, 18));
			idLabel.setEnabled(false);
			idLabel.setBounds(34, 88, 117, 29);
			getContentPane().add(idLabel);
			
			rechnungsIDLabel = new JLabel("ID: " + rechnungsID);
			rechnungsIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rechnungsIDLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			rechnungsIDLabel.setBounds(163, 88, 231, 29);
			getContentPane().add(rechnungsIDLabel);
			
			bestellvolumenLabel = new JLabel("Bestellvolumen:");
			bestellvolumenLabel.setHorizontalAlignment(SwingConstants.LEFT);
			bestellvolumenLabel.setForeground(Color.BLACK);
			bestellvolumenLabel.setFont(new Font("Serif", Font.BOLD, 18));
			bestellvolumenLabel.setEnabled(false);
			bestellvolumenLabel.setBounds(34, 215, 153, 29);
			getContentPane().add(bestellvolumenLabel);
			
			statusLabel = new JLabel("Status:");
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			statusLabel.setForeground(Color.BLACK);
			statusLabel.setFont(new Font("Serif", Font.BOLD, 18));
			statusLabel.setEnabled(false);
			statusLabel.setBounds(272, 215, 72, 29);
			getContentPane().add(statusLabel);
			
			formatLabel = new JLabel("Format: xxx.xx");
			formatLabel.setHorizontalAlignment(SwingConstants.CENTER);
			formatLabel.setFont(new Font("Serif", Font.PLAIN, 14));
			formatLabel.setBounds(34, 306, 153, 29);
			getContentPane().add(formatLabel);
			
			inputLabel = new JLabel("");
			inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
			inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
			inputLabel.setBounds(6, 403, 388, 29);
			getContentPane().add(inputLabel);
			
			fillYears();
			// Input Komponenten
			JComboBox monatDropdown = new JComboBox(monate);
			monatDropdown.setMaximumRowCount(14);
			monatDropdown.setBounds(20, 129, 181, 78);
			getContentPane().add(monatDropdown);
			
			JComboBox jahrDropdown = new JComboBox(lastTenYears);
			jahrDropdown.setMaximumRowCount(14);
			jahrDropdown.setBounds(202, 129, 181, 78);
			getContentPane().add(jahrDropdown);
			
			JRadioButton statusRadio = new JRadioButton("Bezahlt");
			statusRadio.setBounds(257, 268, 87, 37);
			getContentPane().add(statusRadio);
			
			bestellvolumenField = new JTextField();
			bestellvolumenField.setHorizontalAlignment(SwingConstants.CENTER);
			bestellvolumenField.setFont(new Font("Dialog", Font.PLAIN, 14));
			bestellvolumenField.setColumns(10);
			bestellvolumenField.setBorder(new LineBorder(Color.BLACK, 1));
			bestellvolumenField.setBounds(34, 268, 153, 37);
			getContentPane().add(bestellvolumenField);
			bestellvolumenField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					bestellvolumenField.setText("");
				}
				public void focusLost(FocusEvent e) {}
			});

			speichernButton = new JButton("Speichern");
			speichernButton.setBounds(143, 341, 117, 45);
			speichernButton.setForeground(new Color(30, 144, 255));
			speichernButton.setBackground(new Color(30, 144, 255));
			speichernButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					boolean status = statusRadio.isSelected();
					int monat = monatDropdown.getSelectedIndex();
					String bestellvolumen = bestellvolumenField.getText();
					/*
					 * Stringerino muss ein double sein; vielleicht JFormattedTextField?
					 */
					// Check User Input

					if (bestellvolumen.equals("") || monat < 1 || monat > 12 || jahrDropdown.getSelectedIndex() == 0) {
						inputLabel.setForeground(Color.RED);
						inputLabel.setText("Bitte alle Felder ausfüllen.");
						return;
					}
					
					int jahr = Integer.parseInt(monate[jahrDropdown.getSelectedIndex()]);
					
					gui.Lieferanten.rechnungAufnehmen(idAlt, monat, jahr, 0.0, status);
					dispose();
				}
			});
			getContentPane().add(speichernButton);
			
	}
	
	private static void fillYears () {
		lastTenYears[0] = "Jahr auswählen ...";
		for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
	}
}
