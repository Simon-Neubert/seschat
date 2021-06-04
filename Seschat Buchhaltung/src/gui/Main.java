package gui;
import javax.swing.JFrame;

import gui.*;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Main extends JFrame{
	
	// Kunden kundenPanel = new Kunden();
	Lieferanten lieferantenPanel = new Lieferanten();
	// Auswertung rechnungsPanel = new Rechnungen();
	// Auswertung auswertungsPanel = new Auswertung();
	
	public Main() {
		
		getContentPane().setLayout(null);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	    setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 6, 1786, 1065);
		getContentPane().add(tabbedPane);
		
		// tabbedPane.add("Kunden", kundenPanel);
		tabbedPane.add("Lieferanten", lieferantenPanel);
		// tabbedPane.add("Rechnungen", rechnungsPanel);
		// tabbedPane.add("Auswertung", auswertungsPanel);
		
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Main program = new Main();
					program.setVisible(true);
					program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					program.setTitle("Seschat Buchhaltung");
					program.pack();
					
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
}