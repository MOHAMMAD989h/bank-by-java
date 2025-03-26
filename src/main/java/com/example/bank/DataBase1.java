package com.example.bank;

import java.sql.*;

import static java.lang.Class.forName;

public class DataBase1 {

    public static Connection connectDB() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            return connect;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
