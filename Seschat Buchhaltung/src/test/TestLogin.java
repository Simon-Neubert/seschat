package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import dbaccess.DBAccess;

public class TestLogin {

    public static void main(String[] args) {
 
    	Scanner s = new Scanner (System.in);
    	System.out.print("Username: ");
    	String username = s.next();
    	System.out.print("Password: ");
    	String password = s.next();
    	System.out.printf("%s%n%s%n",username, password);
    	if (DBAccess.checkLogin(username, password)) System.out.println("Vega: Welcome home");
    	else System.out.println("Fuck off");
    	DBAccess.closeConnection();
 
   }

}