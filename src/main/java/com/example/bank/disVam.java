package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;

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
    }
    @FXML
    private void getloan(ActionEvent event) throws SQLException {
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
            while(rs.next()){
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
                isusername = false;
                while(rs.next()){
                    if(numbercardfrom.getText().equals(rs.getString("numbercard"))){
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
                    String insertdata = "INSERT INTO loans (username,amountmonth,month,startdate,lastdate) VALUES(?,?,?,?,?)";
                    connect = DataBase1.connectDB();
                    prepare = connect.prepareStatement(insertdata);
                    prepare.setString(1, username);
                    prepare.setString(2, String.valueOf((int) Vam.soodVammonth));
                    prepare.setString(3, String.valueOf(Vam.timeLoan));
                    prepare.setDate(4, Date.valueOf(now));
                    prepare.setDate(5, Date.valueOf(now));
                    prepare.executeUpdate();
                    if(n == -1){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error");
                        alert.showAndWait();
                    }
                    else{
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

    public void exitDisvam(ActionEvent actionEvent) {
        pro.openNewWindow("Vam.fxml","Loan",actionEvent);
    }
}
