package gui;
import javax.swing.JFrame;

import gui.*;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class Main extends JFrame{
	
	Kunden kundenPanel = new Kunden();
	Lieferanten lieferantenPanel = new Lieferanten();
	Rechnungen rechnungsPanel = new Rechnungen();
	Auswertung auswertungsPanel = new Auswertung();
	static boolean abgebrochen = true;
	
	public Main() {
		
		getContentPane().setLayout(null);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	    setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(getBounds());
		tabbedPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(tabbedPane);
		
		tabbedPane.add("Kunden", kundenPanel);
		tabbedPane.add("Lieferanten", lieferantenPanel);
		tabbedPane.add("Rechnungen", rechnungsPanel);
		tabbedPane.add("Auswertung", auswertungsPanel);
		
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Main program = new Main();
				program.setVisible(false);
				program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				program.setTitle("Seschat Buchhaltung");
				program.pack();
				
				Login login = new Login();
				login.setVisible(true);
				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				login.setTitle("Seschat Buchhaltung");
				login.setAlwaysOnTop(true);
				login.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if (abgebrochen) program.dispose();;
						program.setVisible(true);
					}
				});
				
			}
		});
		
	}
	
}