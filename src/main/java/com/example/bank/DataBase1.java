package com.example.bank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

import static java.lang.Class.forName;

public class DataBase1 {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public static Connection connectDB() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connect;

    }
    public boolean isdataimportvalid(String dataimport,String table,String tableinfor) throws SQLException {
        String data = "SELECT * FROM"+table+"WHERE"+ tableinfor +"= ?";
        connect = connectDB();
        assert connect != null;
        prepare = connect.prepareStatement(data);
        prepare.setString(1, dataimport);
        result = prepare.executeQuery();
        if(result.next()) {
            return false;
        }
        else{
            return true;
        }
    }
    public String finddataimport(String dataimport,String table,String tableinfor) throws SQLException {
        String data = "SELECT * FROM"+table+"WHERE"+ tableinfor +"= ?";
        connect = connectDB();
        assert connect != null;
        prepare = connect.prepareStatement(data);
        prepare.setString(1, dataimport);
        result = prepare.executeQuery();
        while (result.next()) {
            return result.getString(1);
        }
        return null;
    }
    public boolean insertToemployee(String name, String username, String password, String email, String numberphone, String nationcode, String address, File imagedata) throws SQLException, IOException {
        String regData = "INSERT INTO employee (name,username ,password,email,numberphone,nationcode,address,imageData) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        connect = DataBase1.connectDB();

        byte[] imageData = Files.readAllBytes(imagedata.toPath());

        assert connect != null;
        prepare = connect.prepareStatement(regData);
        prepare.setString(1, name);
        prepare.setString(2, username);
        prepare.setString(3, password);
        prepare.setString(4, email);
        prepare.setString(5, numberphone);
        prepare.setString(6, nationcode);
        prepare.setString(7, address);
        prepare.setBytes(8, imageData);
        int rowsAffected = prepare.executeUpdate();
        if(rowsAffected > 0) {
            return true;
        }
        else{
            return false;
        }
    }


}
