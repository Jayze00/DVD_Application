package controller;

// SQL imports

import java.sql.*;
import java.sql.DriverManager;

public class Connector {

    private static Connector instance = null;
    private Connection connect = null;

    public void Connector() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }


    }

    public static Connector getInstance(){
        if(Connector.instance == null)
            Connector.instance = new Connector();
            return Connector.instance;

    }

    public void open() {
        try {

            connect =
                    DriverManager.getConnection("jdbc:mysql://localhost/dvd_application", "root", "");


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public Connection getConnection(){
        this.open();
        return this.connect;
    }

}