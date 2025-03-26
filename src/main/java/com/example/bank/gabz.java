package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Random;

import static com.example.bank.loginpage.IDCard;
import static com.example.bank.loginpage.loginID;

public class gabz {

    loginpage login = new loginpage();

    Random random = new Random();

    @FXML
    private Button gabzPayment;

    @FXML
    private TextField gabzTextfield;

    @FXML
    private Button gabznumber;

    @FXML
    private Label moneyGabz;
    int payment = random.nextInt(10000,1000000);

    @FXML
    void gabzPayment(ActionEvent event) throws SQLException {
        gabzTextfield.setVisible(false);
        gabznumber.setVisible(false);
        moneyGabz.setVisible(false);
        gabznumber.setVisible(false);
        int result =login.updateCredit(String.valueOf(IDCard),-payment);
        if (result == -1) {
            System.out.println("Not enough credit!");
        } else {
            System.out.println("Payment successful! New balance: " + result);
        }
    }

    @FXML
    void gabznumber(ActionEvent event) {
        if(loginID){
            if(gabzTextfield.getText().matches("[0-9]{10}")){
                moneyGabz.setText(String.valueOf(payment));
            }
        }
        else{
            moneyGabz.setText("first open account");
        }
    }
}
