package com.example.bank;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Class.forName;

public class DataBase1 {
    Random random = new Random();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    int yyengeza,MMengeza,update;
    String name,userName,password,email,numberphone,nationcode,address, cvv2,money,credit,cartNum,bankName,phoneNumberHome,passwordCard;
    File imageCard,imageData;
    boolean isSuccessFul;
    public DataBase1(String name, String userName, String password, String email, String numberphone, String nationcode, String address, File imageData) throws SQLException, IOException {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.numberphone = numberphone;
        this.nationcode = nationcode;
        this.address = address;
        this.imageData = imageData;
        isSuccessFul=insertToemployee(name,userName,password,email,numberphone,nationcode,address,imageData);
    }

    public DataBase1() {
    }

    public DataBase1(String text, String text1, String text2, String text3, String text4, String text5, String text6, byte[] imageData) {
    }

    public boolean updateToemployee(String username, String password, String email, String numberphone, String address) throws SQLException, IOException {
        String regData = "UPDATE INTO employee (username ,password,email,numberphone,address) " +
                "VALUES(?,?,?,?,?)";
        connect = DataBase1.connectDB();


        assert connect != null;
        prepare = connect.prepareStatement(regData);
        prepare.setString(1, username);
        prepare.setString(2, password);
        prepare.setString(3, email);
        prepare.setString(4, numberphone);
        prepare.setString(5, address);
        int rowsAffected = prepare.executeUpdate();
        if(rowsAffected > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public DataBase1(Connection connect, String userName, String cvv2, String money, String credit, String cartNum, String bankName, String phoneNumberHome, String passwordCard, int MMengeza, int yyengeza, File imageCard) throws SQLException, IOException {
        this.connect = connect;
        this.userName = userName;
        this.cvv2 = cvv2;
        this.money = money;
        this.credit = credit;
        this.cartNum = cartNum;
        this.bankName = bankName;
        this.phoneNumberHome = phoneNumberHome;
        this.passwordCard = passwordCard;
        this.MMengeza = MMengeza;
        this.yyengeza=yyengeza;
        this.imageCard = imageCard;
        isSuccessFul=insertToCards(userName,  cartNum, cvv2,bankName,  phoneNumberHome,passwordCard, MMengeza, yyengeza,imageCard);
    }
    public boolean updateToCards(String username, String money, String credit, String passwordCard) throws SQLException, IOException {
        String regData = "UPDATE INTO cards (username ,money ,credit ,password) " +
                "VALUES(?,?,?,?)";
        assert connect != null;

        prepare = connect.prepareStatement(regData);
        prepare.setString(1, username);
        prepare.setString(2, money);
        prepare.setString(3, credit);
        prepare.setString(4,passwordCard );
        int rowsAffected = prepare.executeUpdate();
        if(rowsAffected > 0) {
            return true;
        }
        else{
            return false;
        }
    }
    public void DataBase11(int update,String updateS) throws SQLException, IOException {
        this.update = update;
        if(update<0){//update person
            if(update==-1){isSuccessFul=updateToemployee(updateS,password,email,numberphone,address);}
            if(update==-2){isSuccessFul=updateToemployee(userName,updateS,email,numberphone,address);}
            if(update==-3){isSuccessFul=updateToemployee(userName,password,updateS,numberphone,address);}
            if(update==-4){isSuccessFul=updateToemployee(userName,password,email,updateS,address);}
            if(update==-5){isSuccessFul=updateToemployee(userName,password,email,numberphone,updateS);}
        }else if(update>0){//update cardsبر اساس عدد
            if(update==1){isSuccessFul=updateToCards(updateS,money,credit,passwordCard);}
            if(update==2){isSuccessFul=updateToCards(userName,updateS,credit,passwordCard);}
            if(update==3){isSuccessFul=updateToCards(userName,money,updateS,passwordCard);}
            if(update==4){isSuccessFul=updateToCards(userName,money,credit,updateS);}
        }
        else if (update==0) {//حذف همه با تشخیص یک یوزر نیم
            String regData = "DELETE INTO cards (username ,money ,credit ,password) " +
                    "VALUES(?,?,?,?)";

        }

    }

    public Connection getConnect() {
        return connect;
    }
    public boolean getIsSuccessFul() {
        return isSuccessFul;
    }
    public boolean isSuccessFull() {
        if(getIsSuccessFul()==true){
            isSuccessFul=false;
            return true;
        }else{return false;}
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationcode() {
        return nationcode;
    }

    public void setNationcode(String nationcode) {
        this.nationcode = nationcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
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

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPhoneNumberHome() {
        return phoneNumberHome;
    }

    public void setPhoneNumberHome(String phoneNumberHome) {
        this.phoneNumberHome = phoneNumberHome;
    }

    public String getPasswordCard() {
        return passwordCard;
    }

    public void setPasswordCard(String passwordCard) {
        this.passwordCard = passwordCard;
    }

    public int getYyengeza() {
        return yyengeza;
    }

    public void setYyengeza(int yyengeza) {
        this.yyengeza = yyengeza;
    }

    public int getMMengeza() {
        return MMengeza;
    }
    public void setMMengeza(int MMengeza) {
        this.MMengeza = MMengeza;
    }

    public File getImageCard() {
        return imageCard;
    }

    public void setImageCard(File imageCard) {
        this.imageCard = imageCard;
    }

    public File getImageData() {
        return imageData;
    }

    public void setImageData(File imageData) {
        this.imageData = imageData;
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
    public String finddataimport(String dataimport,String table,String tableinfor) throws SQLException {
        String data = "SELECT * FROM"+table+"WHERE"+ tableinfor +" = ?";
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
    public boolean insertToCards( String username, String Cvv2Getter, String cartNumgetter, String com1, String phonehome, String accountPassword, int monthofExpire,int yearofExpire,File imageCard) throws SQLException, IOException {
        String regData = "INSERT INTO cards (username ,money ,credit ,numbercard ,cvv2,engeza,bankname ,phonenumberhome,password,imagecard) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        assert connect != null;
        BigInteger randomBigInt=new BigInteger(23,random).add(new BigInteger("1000000"));

        byte[] imageData = Files.readAllBytes(imageCard.toPath());
        String yyMM=String.valueOf(yearofExpire)+String.valueOf(monthofExpire);
        prepare = connect.prepareStatement(regData);
        prepare.setString(1, username);
        prepare.setString(2, String.valueOf(randomBigInt));
        prepare.setString(3, String.valueOf(randomBigInt));
        prepare.setString(4, cartNumgetter);
        prepare.setString(5, Cvv2Getter);
        prepare.setString(6, yyMM);
        prepare.setString(7, com1);
        prepare.setString(8, phonehome);
        prepare.setString(9,accountPassword );
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