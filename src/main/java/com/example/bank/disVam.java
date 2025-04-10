package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.bank.loginpage.username;

public class disVam {
    @FXML
    private Label namevamLbl;

    @FXML
    private TextField numbercardfrom;

    @FXML
    private TextField numbercardto;

    @FXML
    private Label pricevamLbl;

    @FXML
    private Label soodvamLbl;

    @FXML
    private Label timevamLbl;

    @FXML
    private Label vamdiscription;

    Connection connect;
    PreparedStatement prepare;
    ResultSet rs;

    profile pro = new profile();

    Alert alert ;

    public void initialize() {
        namevamLbl.setText(Vam.nameLoan);
        pricevamLbl.setText(String.valueOf(Vam.priceLoan));
        soodvamLbl.setText(String.valueOf(Vam.soodLoan));
        timevamLbl.setText(String.valueOf(Vam.timeLoan));

        scheduleDailyInterestCheck();
    }
    @FXML
    private void getloan(ActionEvent event) throws SQLException, IOException {
        if(!numbercardfrom.getText().matches("[0-9]{16}") || !numbercardto.getText().matches("[0-9]{16}")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number");
            alert.showAndWait();
        }
        else {
            String data = "SELECT * FROM cards WHERE username = ?";
            connect = DataBase1.connectDB();
            prepare = connect.prepareStatement(data);
            prepare.setString(1, username);
            rs = prepare.executeQuery();
            boolean isusername = false;
            String numbercardto1 = "";
            while(rs.next()){
                numbercardto1 = rs.getString("numbercard");
                if(numbercardto.getText().equals(rs.getString("numbercard"))){
                    isusername = true;
                }
            }
            if(!isusername){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid numbercardto");
                alert.showAndWait();
            }
            else{
                String data1 = "SELECT * FROM blockedcard WHERE username = ?";
                connect = DataBase1.connectDB();
                prepare = connect.prepareStatement(data1);
                prepare.setString(1, username);
                rs = prepare.executeQuery();
                String numbercard2 = "";
                isusername = false;
                while(rs.next()){
                    if(numbercardfrom.getText().equals(rs.getString("numbercard"))){
                        numbercard2 = rs.getString("numbercard");
                        isusername = true;
                    }
                }
                if(!isusername){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid numbercardfrom");
                    alert.showAndWait();
                }
                else{
                    LocalDate now = LocalDate.now();

                    int n = pro.updateCredit(numbercardto.getText(), Math.toIntExact(Vam.priceLoan));
                    String insertdata = "INSERT INTO loans (username,amountmonth,month,numbercardto,numbercardfrom,startdate,lastdate) VALUES(?,?,?,?,?,?,?)";
                    connect = DataBase1.connectDB();
                    prepare = connect.prepareStatement(insertdata);
                    prepare.setString(1, username);
                    prepare.setString(2, String.valueOf((int) Vam.soodVammonth));
                    prepare.setString(3, String.valueOf(Vam.timeLoan));
                    prepare.setString(4, numbercardto1);
                    prepare.setString(5, numbercard2);
                    prepare.setDate(6, Date.valueOf(now));
                    prepare.setDate(7, Date.valueOf(now));
                    prepare.executeUpdate();
                    if(n == -1){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error");
                        alert.showAndWait();
                    }
                    else{
                        pro.fileTransfer(numbercardto1,"", (long) +Math.toIntExact(Vam.priceLoan),"وام");
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("You have successfully loaned  credits");
                        alert.showAndWait();
                    }
                }
            }
        }
    }
    private void applymonthlyInterestCheck() throws SQLException, IOException {
        String data = "SELECT * FROM loans";
        connect = DataBase1.connectDB();
        assert connect != null;
        prepare = connect.prepareStatement(data);
        rs = prepare.executeQuery();

        LocalDate now = LocalDate.now();

        boolean paid = false;

        while (rs.next()) {
            String username = rs.getString("username");
            String amountmonth = rs.getString("amountmonth");
            String month = rs.getString("month");
            LocalDate lastdate = rs.getDate("lastdate").toLocalDate();
            String numbercardto = rs.getString("numbercardto");
            String numbercardfrom = rs.getString("numbercardfrom");

            if(Integer.parseInt(month) == 0){
                String deletdata = "DELETE FROM blockedcard WHERE username = ?";
                connect = DataBase1.connectDB();
                assert connect != null;
                prepare = connect.prepareStatement(deletdata);
                prepare.setString(1, username);
                prepare.executeUpdate();
            }

            else if(ChronoUnit.MONTHS.between(lastdate, now) >= 1){
                int res =pro.updateCredit(numbercardto,-Integer.parseInt(amountmonth));
                if(res == -1){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error");
                    alert.showAndWait();

                    int ress =pro.updateCreditblocked(numbercardfrom,-Integer.parseInt(amountmonth));

                    if(ress != -1){paid = true;}
                }
                else{
                    pro.fileTransfer(numbercardto,"", (long) -Integer.parseInt(amountmonth),"مقرری وام");
                    paid = true;
                }
                if (paid){
                    String update = "UPDATE loan SET lastdate = ?,month=? WHERE username = ?";
                    prepare = connect.prepareStatement(update);
                    prepare.setDate(1, java.sql.Date.valueOf(now));
                    prepare.setString(2, String.valueOf(Integer.parseInt(month) -1));
                    prepare.setString(3, username);
                    prepare.executeUpdate();
                }
            }
        }
    }
    private void scheduleDailyInterestCheck() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // محاسبه مدت تا ساعت مورد نظر (مثلاً 2 صبح)
        LocalTime now = LocalTime.now();
        LocalTime targetTime = LocalTime.of(2, 0); // ساعت 2 صبح
        long initialDelay = ChronoUnit.MINUTES.between(now, targetTime);
        if (initialDelay < 0) initialDelay += 24 * 60; // اگر گذشته، فردا اجرا بشه

        scheduler.scheduleAtFixedRate(() -> {
            try {
                applymonthlyInterestCheck();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, 24 * 60, TimeUnit.MINUTES); // هر 24 ساعت
    }

    public void exitDisvam(ActionEvent actionEvent) {
        pro.openNewWindow("Vam.fxml","Loan",actionEvent);
    }
}
