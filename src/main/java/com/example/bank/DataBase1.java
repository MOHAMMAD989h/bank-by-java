package com.example.bank;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import static com.example.bank.loginpage.username;
import static java.lang.Class.forName;

public class DataBase1 {

    private String name,email,nationcode,numberphone,month,amountmonth,username,money,credit, numbercard, cvv2,engeza,bankname,phonenumberhome,password;
    private boolean isSignUp;
    private File imagecards;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private LocalDate lastdate,startdate;
    Random random = new Random();

    public DataBase1(File imagecards,String password, String phonenumberhome, String bankname, String engeza, String cvv2, String numbercard, String money, String username, String credit){
        this.imagecards = imagecards;
        this.password = password;
        this.phonenumberhome = phonenumberhome;
        this.bankname = bankname;
        this.engeza = engeza;
        this.cvv2 = cvv2;
        this.numbercard = numbercard;
        this.money = money;
        this.username = username;
        this.credit = credit;
    }
    public DataBase1(String name,String username,String password,String email,String numberphone,String nationcode,String address,File imagecard ) throws SQLException, IOException {
        insertToemployee(name,username,password,email,numberphone,nationcode,address,imagecard);
        //insert to employee
        isSignUp = true;

        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.numberphone=numberphone;
        this.nationcode=nationcode;
        this.imagecards=imagecard;
    }
    public boolean checkIfSign(){
        if(isSignUp){return true;
        }
        else{return false;
        }
    }

    public DataBase1(File imagecards,LocalDate lastdate, LocalDate startdate, String password, String phonenumberhome, String bankname, String engeza, String cvv2, String numbercard, String money, String username, String credit) {
        this.imagecards = imagecards;
        this.password = password;
        this.phonenumberhome = phonenumberhome;
        this.bankname = bankname;
        this.engeza = engeza;
        this.cvv2 = cvv2;
        this.numbercard = numbercard;
        this.money = money;
        this.username = username;
        this.credit = credit;
        this.lastdate = lastdate;
        this.startdate = startdate;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getEngeza() {
        return engeza;
    }

    public void setEngeza(String engeza) {
        this.engeza = engeza;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getPhonenumberhome() {
        return phonenumberhome;
    }

    public void setPhonenumberhome(String phonenumberhome) {
        this.phonenumberhome = phonenumberhome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getImagecards() {
        return imagecards;
    }

    public void setImagecards(File imagecards) {
        this.imagecards = imagecards;
    }
    public LocalDate getLastdate() {
        return lastdate;
    }

    public void setLastdate(LocalDate lastdate) {
        this.lastdate = lastdate;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }



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
    public String finddataimport(String dataimport,String table,String tableinfor,String columnlabel) throws SQLException {
        String data = "SELECT * FROM"+table+"WHERE"+ tableinfor +"= ?";
        connect = connectDB();
        assert connect != null;
        prepare = connect.prepareStatement(data);
        prepare.setString(1, dataimport);
        result = prepare.executeQuery();
        while(result.next()) {
            return result.getString(columnlabel);
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
    public boolean insertToCards(File imagecard,String username,String cartNumgetter,String Cvv2Getter, String com1,String phonehome,String accountpassword,int monthofExpire,int yearofExpire) throws SQLException, IOException {
        String  regdata = "INSERT INTO cards (username,money,credit,numbercard,cvv2,engeza,bankname,phonenumberhome,password,imagecard) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        assert connect != null;

        BigInteger randomBigInt = new BigInteger(23, random).add(new BigInteger("1000000"));

        byte[] imageData = Files.readAllBytes(imagecard.toPath());
        String yyMM = String.valueOf(yearofExpire) + String.valueOf(monthofExpire);

        prepare = connect.prepareStatement(regdata);
        prepare.setString(1, username);
        prepare.setString(2, String.valueOf(randomBigInt));
        prepare.setString(3, String.valueOf(randomBigInt));
        prepare.setString(4, cartNumgetter);
        prepare.setString(5, Cvv2Getter);
        prepare.setString(6, yyMM);
        prepare.setString(7, com1);
        prepare.setString(8, phonehome);
        prepare.setString(9, accountpassword);
        prepare.setString(10, Arrays.toString(imageData));
        int rowsAffected = prepare.executeUpdate();

        if(rowsAffected > 0) {
            return true;
        }
        else{
            return false;
        }
    }
}

