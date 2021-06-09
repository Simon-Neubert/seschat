package popups;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class KundenRechnungBearbeiten extends JDialog{


	private JButton speichernButton;
	private JLabel kundeLabel2;
	private JLabel inputLabel;
	private JLabel idLabel;
	private JLabel rechnungsIDLabel;
	private JLabel bestellvolumenLabel;
	private JLabel statusLabel;

	static String[] monate = {"Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String[] lastTenYears = new String[11];
	private JLabel formatLabel;
	
	public KundenRechnungBearbeiten (int idAlt, String vornameAlt, String nachnameAlt, int plzAlt, int rechnungsID) {
			
			setBounds(800, 200, 400, 500);
			getContentPane().setLayout(null);
			
			//Label
			JLabel kundeLabel = new JLabel("Kunde:");
			kundeLabel.setBounds(34, 35, 87, 29);
			kundeLabel.setEnabled(false);
			kundeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			kundeLabel.setFont(new Font("Serif", Font.BOLD, 18));
			kundeLabel.setForeground(Color.BLACK);
			getContentPane().add(kundeLabel);
			
			kundeLabel2 = new JLabel(vornameAlt + " " + nachnameAlt + " (ID: " + idAlt + ")");
			kundeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			kundeLabel2.setBounds(133, 35, 261, 29);
			kundeLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
			getContentPane().add(kundeLabel2);
			
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
			
			JTextField bestellvolumenTextfield = new JTextField("");
			bestellvolumenTextfield.setBounds(34, 268, 153, 37);
			bestellvolumenTextfield.setColumns(10);
			bestellvolumenTextfield.setBorder(new LineBorder(Color.BLACK, 1));
			bestellvolumenTextfield.setHorizontalAlignment(SwingConstants.CENTER);
			bestellvolumenTextfield.setFont(new Font("Serif", Font.PLAIN, 14));
	
			
			getContentPane().add(bestellvolumenTextfield);
			
			speichernButton = new JButton("Speichern");
			speichernButton.setBounds(144, 359, 117, 45);
			speichernButton.setForeground(new Color(30, 144, 255));
			speichernButton.setBackground(new Color(30, 144, 255));
			speichernButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					boolean status = statusRadio.isSelected();
					int monat = monatDropdown.getSelectedIndex();
					String bestellvolumen = bestellvolumenTextfield.getText();
					
					// Check Bestellvolumen
					if (bestellvolumen.equals("") || bestellvolumen.length() < 4 ) {
						setErrMessage(inputLabel);
						return;
					}
					
					char temp [] = bestellvolumen.toCharArray();
					
					if (temp[temp.length - 3] != '.') {
						setErrMessage(inputLabel);
						return;
					}
					
					temp [temp.length - 3] = '0';
					
					for (int i = 0; i < temp.length; i++)
						if(!Character.isDigit(temp[i])) {
							setErrMessage(inputLabel);
							return;
						}

					// Check User Input everywhere else
					if (monat < 1 || jahrDropdown.getSelectedIndex() == 0) {
						setErrMessage(inputLabel);
						return;
					}
					
					int jahr = Integer.parseInt(lastTenYears[jahrDropdown.getSelectedIndex()]);
					double wert = Double.parseDouble(bestellvolumen);

					gui.Rechnungen.rechnungBearbeitenKunde(rechnungsID, monat, jahr, wert, status);
					dispose();
				}
			});
			getContentPane().add(speichernButton);
			
	}
	
	private static void fillYears () {
		lastTenYears[0] = "Jahr auswählen ...";
		for (int i = 1; i < lastTenYears.length; i++) lastTenYears[i] = String.valueOf(currentYear-i+1);
	}
	
	private static void setErrMessage (JLabel inputLabel) {
		inputLabel.setForeground(Color.RED);
		inputLabel.setText("Bitte alle Felder korrekt ausfüllen.");
		return;
	}

	
}
