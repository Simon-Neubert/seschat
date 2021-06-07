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

public class LieferantBearbeiten extends JDialog {
	
	private JTextField neuField;
	private JButton speichernButton;
	private JLabel altLabel;
	private JLabel inputLabel;
	private JLabel lblName;
	
	public LieferantBearbeiten(int idAlt, String lieferantAlt){
		
		setBounds(800, 200, 400, 500);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lieferant:");
		lblNewLabel.setBounds(156, 36, 87, 29);
		lblNewLabel.setEnabled(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		getContentPane().add(lblNewLabel);
		
		altLabel = new JLabel(lieferantAlt + " (ID: " + idAlt + ")");
		altLabel.setHorizontalAlignment(SwingConstants.CENTER);
		altLabel.setBounds(6, 87, 388, 29);
		altLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		getContentPane().add(altLabel);
		
		neuField = new JTextField();
		neuField.setHorizontalAlignment(SwingConstants.CENTER);
		neuField.setFont(new Font("Dialog", Font.PLAIN, 14));
		neuField.setColumns(10);
		neuField.setBorder(new LineBorder(Color.BLACK, 1));
		neuField.setBounds(124, 206, 153, 37);
		getContentPane().add(neuField);
		neuField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				neuField.setText("");
			}
			public void focusLost(FocusEvent e) {}
		});
		
		inputLabel = new JLabel("");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		inputLabel.setBounds(6, 376, 388, 29);
		getContentPane().add(inputLabel);
		
		speichernButton = new JButton("Speichern");
		speichernButton.setBounds(141, 282, 117, 45);
		speichernButton.setForeground(new Color(30, 144, 255));
		speichernButton.setBackground(new Color(30, 144, 255));
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
		getContentPane().add(speichernButton);
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Serif", Font.BOLD, 18));
		lblName.setEnabled(false);
		lblName.setBounds(124, 165, 51, 29);
		getContentPane().add(lblName);
		
	
		
	} 

}