package popups;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dbaccess.DBAccess;
import gui.Kunden;
import gui.Main;
import gui.Rechnungen;

public class KundenRechnungBearbeiten extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton speichernButton;
	private JLabel kundeLabel2;
	private JLabel inputLabel;
	private JLabel idLabel;
	private JLabel rechnungsIDLabel;
	private JLabel bestellvolumenLabel;
	private JLabel statusLabel;
	private JLabel formatLabel;
	
	static int monat = 0, jahr = 0;
	static double bestellvolumen = 0.0;
	static boolean bezahlt = false;
	
	
	// Constructor for pop-up
	public KundenRechnungBearbeiten (int kundenID, String vornameAlt, String nachnameAlt, int plzAlt, int rechnungsID, boolean isNeu) {
			
			setBounds(0, 0, 400, 500);
			getContentPane().setLayout(null);
			setLocationRelativeTo(null);
			setAlwaysOnTop(true);
			
			//Label
			JLabel kundeLabel = new JLabel("Kunde:");
			kundeLabel.setBounds(34, 35, 87, 29);
			kundeLabel.setEnabled(false);
			kundeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			kundeLabel.setFont(new Font("Palatino", Font.BOLD, 18));
			kundeLabel.setForeground(Color.BLACK);
			getContentPane().add(kundeLabel);
			
			kundeLabel2 = new JLabel(vornameAlt + " " + nachnameAlt + " (ID: " + kundenID + ")");
			kundeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			kundeLabel2.setBounds(133, 35, 261, 29);
			kundeLabel2.setFont(new Font("Palatino", Font.ITALIC, 18));
			getContentPane().add(kundeLabel2);
			
			idLabel = new JLabel("Rechnungs-ID:");
			idLabel.setHorizontalAlignment(SwingConstants.LEFT);
			idLabel.setForeground(Color.BLACK);
			idLabel.setFont(new Font("Palatino", Font.BOLD, 18));
			idLabel.setEnabled(false);
			idLabel.setBounds(34, 88, 133, 29);
			getContentPane().add(idLabel);
			
			rechnungsIDLabel = new JLabel("ID: " + rechnungsID);
			rechnungsIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rechnungsIDLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
			rechnungsIDLabel.setBounds(163, 88, 231, 29);
			getContentPane().add(rechnungsIDLabel);
			
			bestellvolumenLabel = new JLabel("Bestellvolumen:");
			bestellvolumenLabel.setHorizontalAlignment(SwingConstants.LEFT);
			bestellvolumenLabel.setForeground(Color.BLACK);
			bestellvolumenLabel.setFont(new Font("Palatino", Font.BOLD, 18));
			bestellvolumenLabel.setEnabled(false);
			bestellvolumenLabel.setBounds(34, 215, 153, 29);
			getContentPane().add(bestellvolumenLabel);
			
			statusLabel = new JLabel("Status:");
			statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			statusLabel.setForeground(Color.BLACK);
			statusLabel.setFont(new Font("Palatino", Font.BOLD, 18));
			statusLabel.setEnabled(false);
			statusLabel.setBounds(265, 215, 79, 29);
			getContentPane().add(statusLabel);
			
			formatLabel = new JLabel("Format: xxx.xx");
			formatLabel.setHorizontalAlignment(SwingConstants.CENTER);
			formatLabel.setFont(new Font("Palatino", Font.PLAIN, 14));
			formatLabel.setBounds(34, 306, 153, 29);
			getContentPane().add(formatLabel);
			
			inputLabel = new JLabel("");
			inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
			inputLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
			inputLabel.setBounds(6, 403, 388, 29);
			getContentPane().add(inputLabel);
			
			// Input Komponenten
			JComboBox monatDropdown = new JComboBox(Main.monate);
			monatDropdown.setFont(new Font("Palatino", Font.PLAIN, 14));
			monatDropdown.setMaximumRowCount(14);
			monatDropdown.setBounds(20, 158, 181, 29);
			getContentPane().add(monatDropdown);
			
			JComboBox jahrDropdown = new JComboBox(Main.lastTenYears);
			jahrDropdown.setFont(new Font("Palatino", Font.PLAIN, 14));
			jahrDropdown.setMaximumRowCount(14);
			jahrDropdown.setBounds(201, 158, 181, 29);
			getContentPane().add(jahrDropdown);
			
			JRadioButton statusRadio = new JRadioButton("Bezahlt");
			statusRadio.setFont(new Font("Palatino", Font.PLAIN, 16));
			statusRadio.setBounds(257, 266, 87, 37);
			getContentPane().add(statusRadio);
			
			JTextField bestellvolumenTextfield = new JTextField("");
			bestellvolumenTextfield.setBounds(34, 268, 153, 37);
			bestellvolumenTextfield.setColumns(10);
			bestellvolumenTextfield.setBorder(new LineBorder(Color.BLACK, 1));
			bestellvolumenTextfield.setHorizontalAlignment(SwingConstants.CENTER);
			bestellvolumenTextfield.setFont(new Font("Palatino", Font.PLAIN, 14));
			getContentPane().add(bestellvolumenTextfield);
			
			// Set current values if edit
			if(!isNeu) {
				DBAccess.getKr().stream().filter(x -> x.getRechnungsID() == rechnungsID).forEach(x-> {
					monat = x.getMonat(); jahr = x.getJahr(); bestellvolumen = x.getSumme(); bezahlt = x.isStatus();
				});
				
				monatDropdown.setSelectedIndex(monat);
				jahrDropdown.setSelectedIndex(Main.currentYear - jahr + 1);
				String s = String.valueOf(bestellvolumen);
				char [] bs = String.valueOf(bestellvolumen).toCharArray();
				if (bs[bs.length - 2] == '.') s += "0";
				bestellvolumenTextfield.setText(s);
				statusRadio.setSelected(bezahlt);
			}
			
			speichernButton = new JButton("Speichern");
			speichernButton.setFont(new Font("Palatino", Font.BOLD, 16));
			speichernButton.setBounds(144, 359, 117, 45);
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
					
					int jahr = Integer.parseInt(Main.lastTenYears[jahrDropdown.getSelectedIndex()]);
					double wert = Double.parseDouble(bestellvolumen);
					
					if (isNeu) {
						gui.Kunden.rechnungAufnehmen(kundenID, monat, jahr, wert, status);
						Kunden.abgebrochen = false;
						Rechnungen.abgebrochen = false;
						dispose();
						return;
					}
					
					gui.Rechnungen.rechnungBearbeitenKunde(rechnungsID, monat, jahr, wert, status);
					Kunden.abgebrochen = false;
					Rechnungen.abgebrochen = false;
					dispose();
				}
			});
			getContentPane().add(speichernButton);
			
	}
	
	
	// Auxiliary functions
	
	// Sets standard error message for labels
	private static void setErrMessage (JLabel inputLabel) {
		inputLabel.setForeground(Color.RED);
		inputLabel.setText("Bitte alle Felder korrekt ausf??llen.");
		return;
	}

	
}
