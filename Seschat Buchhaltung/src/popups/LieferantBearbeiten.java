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

public class LieferantBearbeiten extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField neuField;
	private JButton speichernButton;
	private JLabel altLabel;
	private JLabel inputLabel;
	private JLabel lblName;
	
	// Constructor for pop-up
	public LieferantBearbeiten(int idAlt, String lieferantAlt){
		
		setBounds(800, 200, 400, 500);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{388, 0};
		gridBagLayout.rowHeights = new int[]{29, 29, 30, 29, 41, 29, 37, 39, 45, 29, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Lieferant:");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		altLabel = new JLabel(lieferantAlt + " (ID: " + idAlt + ")");
		altLabel.setHorizontalAlignment(SwingConstants.CENTER);
		altLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_altLabel = new GridBagConstraints();
		gbc_altLabel.fill = GridBagConstraints.VERTICAL;
		gbc_altLabel.insets = new Insets(0, 0, 5, 0);
		gbc_altLabel.gridx = 0;
		gbc_altLabel.gridy = 3;
		getContentPane().add(altLabel, gbc_altLabel);
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Palatino", Font.BOLD, 18));
		lblName.setEnabled(false);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.VERTICAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 5;
		getContentPane().add(lblName, gbc_lblName);
		
		speichernButton = new JButton("Speichern");
		speichernButton.setFont(new Font("Palatino", Font.BOLD, 16));
		speichernButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String input = neuField.getText();
				
				// Check User Input
				if (input.equals("")) {
					inputLabel.setForeground(Color.RED);
					inputLabel.setText("Bitte Eingabe prüfen.");
					return;
				}
				
				gui.Lieferanten.lieferantBearbeiten(idAlt, input);
				gui.Lieferanten.abgebrochen = false;
				dispose();
			}
		});
		
		neuField = new JTextField();
		neuField.setHorizontalAlignment(SwingConstants.CENTER);
		neuField.setFont(new Font("Palatino", Font.PLAIN, 14));
		neuField.setColumns(10);
		neuField.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagConstraints gbc_neuField = new GridBagConstraints();
		gbc_neuField.fill = GridBagConstraints.VERTICAL;
		gbc_neuField.insets = new Insets(0, 0, 5, 0);
		gbc_neuField.gridx = 0;
		gbc_neuField.gridy = 6;
		getContentPane().add(neuField, gbc_neuField);
		neuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				neuField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		GridBagConstraints gbc_speichernButton = new GridBagConstraints();
		gbc_speichernButton.fill = GridBagConstraints.VERTICAL;
		gbc_speichernButton.insets = new Insets(0, 0, 5, 0);
		gbc_speichernButton.gridx = 0;
		gbc_speichernButton.gridy = 8;
		getContentPane().add(speichernButton, gbc_speichernButton);
		
		inputLabel = new JLabel("");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("Palatino", Font.ITALIC, 18));
		GridBagConstraints gbc_inputLabel = new GridBagConstraints();
		gbc_inputLabel.insets = new Insets(0, 0, 5, 0);
		gbc_inputLabel.fill = GridBagConstraints.BOTH;
		gbc_inputLabel.gridx = 0;
		gbc_inputLabel.gridy = 9;
		getContentPane().add(inputLabel, gbc_inputLabel);
		
	
		
	} 

}