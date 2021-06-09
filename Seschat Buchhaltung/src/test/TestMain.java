package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dbaccess.DBAccess;
import objects.*;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		ArrayList<Kundenrechnung> kr = DBAccess.createKundenrechnungen();
		
		ArrayList<Lieferantenrechnung> kl = DBAccess.createLieferantenrechnungen();
		
		
		ArrayList<String> table = new ArrayList<String>();
		String [] spalten = {"Jahresabschluss", "Forderungen ", "Einnahmen", "Verbindlichkeiten", "Ausgaben", "Saldo"};
		table.addAll(Arrays.asList(spalten));
		System.out.println(table.toString());
		
		
		/*
		Object[] test = table.toArray();
		for (int j = 0; j < test.length; j++) {
			if (j % 7 == 0 && j != 0) System.out.println();
			System.out.printf("%-20s", test[j]);
			}
		*/
		
    }
	

	private static void deezNuts() {
		
		ArrayList<Kunde> k = DBAccess.createKunden();
		
		ArrayList<Kundenrechnung> kr = DBAccess.createKundenrechnungen();
		
		ArrayList<Lieferantenrechnung> kl = DBAccess.createLieferantenrechnungen();
		
		
		k.forEach(x -> System.out.print(x.toString()));
		
		// kr.forEach(x -> System.out.println(x.isStatus()));
		kl.forEach(x -> System.out.println(x.isStatus()));
		
		String[] monate = {"Bitte Monat auswählen ...","Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};

		// Columns/Rows for JTable
		String [] spalten = {"Jahresabschluss", "Kundenrechnungen ", "Einnahmen", "Lieferantenrechnungen", "Ausgaben", "Saldo"};
		String table[][] = new String [14][6];
		
		for (int i = 0; i < spalten.length; i++) table[0][i] = spalten[i];
		for (int i = 1; i < 13; i++) table[i][0] = monate[i];
		table[13][0] = "Summe";
		
        for (int i = 0; i < 14; i++)
            for (int j = 0; j < 6; j++)
                System.out.println("arr[" + i + "][" + j + "] = " + table[i][j]);
		
	}
	/*
	private String[][] createTable(int jahr) {
		// Columns/Rows for JTable
		String [] spalten = {" Jahresabschluss", "Forderungen ", "Einnahmen", "Verbindlichkeiten", "Ausgaben","Bilanz", "Saldo"};
		String table[][] = new String [14][7];
				
		for (int i = 0; i < spalten.length; i++) table[0][i] = spalten[i];
		for (int i = 1; i < 13; i++) table[i][0] = " " + monate[i];
		table[13][0] = " Summe";
		int summeForderungen = 0, summeEinnahmen = 0, summeSchulden = 0, summeBeglichen = 0, summeBilanz = 0, saldo = 0;
		
		// Werte auffüllen
		for (int i = 1; i < 13; i++) {
			table[i][1] = String.valueOf(einnahmenImMonat(i, jahr));
			table[i][2] = String.valueOf(einnahmenImMonat(i, jahr) - ausstehend);
			table[i][3] = String.valueOf(volumenAusZeitraum(i, jahr));
			table[i][4] = String.valueOf(volumenAusZeitraum(i, jahr) - ausstehend);
			table[i][5] = String.valueOf(Integer.parseInt(table[i][1]) - Integer.parseInt(table[i][3]));
			table[i][6] = String.valueOf(Integer.parseInt(table[i][2]) - Integer.parseInt(table[i][4]));
			
			summeForderungen += Integer.parseInt(table[i][1]);
			summeEinnahmen += Integer.parseInt(table[i][2]);
			summeSchulden += Integer.parseInt(table[i][3]);
			summeBeglichen += Integer.parseInt(table[i][4]);
			summeBilanz += Integer.parseInt(table[i][5]);
			saldo += Integer.parseInt(table[i][6]);
			
			table[i][1] = String.valueOf((double)Integer.parseInt(table[i][1])/100);
			table[i][2] = String.valueOf((double)Integer.parseInt(table[i][2])/100);
			table[i][3] = String.valueOf((double)Integer.parseInt(table[i][3])/100);
			table[i][4] = String.valueOf((double)Integer.parseInt(table[i][4])/100);
			table[i][5] = String.valueOf((double)Integer.parseInt(table[i][5])/100);
			table[i][6] = String.valueOf((double)Integer.parseInt(table[i][6])/100);
			
		}
		
		table [13][1] = String.valueOf((double)summeForderungen/100);
		table [13][2] = String.valueOf((double)summeEinnahmen/100);
		table [13][3] = String.valueOf((double)summeSchulden/100);
		table [13][4] = String.valueOf((double)summeBeglichen/100);
		table [13][5] = String.valueOf((double)summeBilanz/100);
		table [13][6] = String.valueOf((double)saldo/100);
		
		for (int i = 1; i < 14; i++) for (int j = 1; j < 7; j++) table[i][j] += "€";
		
		return table;
	}
	*/
	

	/*
	DefaultTableModel tableModel = new DefaultTableModel(createTable(Integer.parseInt(jahr)), new Object[] { "Bezeichnung", "Forderungen", "Einnahmen", "Verbindlichkeiten", "Ausgaben", "Bilanz", "Saldo"});
	jahresAbschlussTable.setModel(tableModel);
	*/
	
}
