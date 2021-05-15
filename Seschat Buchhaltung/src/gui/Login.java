package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import dbaccess.DBAccess;
import java.awt.EventQueue;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login extends JFrame{

	private String username;
	private String password;
	// Username: Leiterin Passsword: Leitung
	
	// Login Fenster
	private JPasswordField passwordField;
	private JTextField usernameField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login loginFrame = new Login();
					loginFrame.setVisible(true);
					loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginFrame.setTitle("Seschat Buchhaltung");
					loginFrame.setAlwaysOnTop(isDefaultLookAndFeelDecorated());
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public Login() {
		
		// Window
		setBounds(100, 100, 355, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{42, 279, 0, 0};
		gridBagLayout.rowHeights = new int[]{63, 16, 26, 40, 16, 26, 0, 68, 0, 0, 29, 16, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		// Labels
		JLabel usernameLabel = new JLabel("Username:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 1;
		getContentPane().add(usernameLabel, gbc_usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 3;
		getContentPane().add(passwordLabel, gbc_passwordLabel);
		
		JLabel wrongInput = new JLabel("");
		wrongInput.setForeground(Color.RED);
		GridBagConstraints gbc_wrongInput = new GridBagConstraints();
		gbc_wrongInput.insets = new Insets(0, 0, 5, 5);
		gbc_wrongInput.gridx = 1;
		gbc_wrongInput.gridy = 7;
		getContentPane().add(wrongInput, gbc_wrongInput);
		
		// Username Textfield
		usernameField = new JTextField();
		usernameField.setColumns(30);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.BOTH;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 2;
		getContentPane().add(usernameField, gbc_usernameField);
		
		// Password Textfield
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			// User presses enter in Passowrd field
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() == 10) {
					checkInput();
					wrongInput.setText("Wrong username/password");
				}
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		getContentPane().add(passwordField, gbc_passwordField);
		
		// Login Button
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			// Login-Button Press
			public void mouseClicked(MouseEvent e) {
				checkInput();
				wrongInput.setText("Wrong username/password");
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 6;
		getContentPane().add(loginButton, gbc_loginButton);
		
		// Logo
		JLabel icon = new JLabel(new ImageIcon(Login.class.getResource("/gui/logo.png")));
		GridBagConstraints gbc_icon = new GridBagConstraints();
		gbc_icon.insets = new Insets(0, 0, 5, 5);
		gbc_icon.gridx = 1;
		gbc_icon.gridy = 9;
		getContentPane().add(icon, gbc_icon);
		
	}
	
	// Check if typed values match database entries, exit if true
	private void checkInput () {
		username = usernameField.getText();
		password = String.valueOf(passwordField.getPassword());
		if (DBAccess.checkLogin(username, password)) {
			System.out.println("Login successful");
			dispose();
		}
		// Access main program
	}
	
}