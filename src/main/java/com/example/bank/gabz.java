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

    @FXML
    void gabzPayment(ActionEvent event) throws SQLException {
        int payment = random.nextInt(100);
        String pay = String.valueOf(payment)+"0000";
        payment = Integer.parseInt(pay);
        login.updateCredit(IDCard,-payment);
    }

    @FXML
    void gabznumber(ActionEvent event) {
        if(loginID){
            if(gabzTextfield.getText().length() > 8){
                gabzTextfield.setVisible(false);
                gabznumber.setVisible(false);
                moneyGabz.setVisible(true);
                gabznumber.setVisible(true);
            }
        }
    }
}
