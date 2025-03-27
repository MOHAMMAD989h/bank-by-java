package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import static com.example.bank.loginpage.IDCard;
import static com.example.bank.loginpage.loginID;


public class gabz {

    loginpage login = new loginpage();

    Random random = new Random();
    File file = new File("gabz.txt");

    @FXML
    private Button gabzPayment;

    @FXML
    private TextField gabzTextfield;

    @FXML
    private Button gabznumber;

    @FXML
    private Label gabz1;

    @FXML
    private Label gabz2;

    @FXML
    private Label gabz3;

    @FXML
    private Label gabz4;

    @FXML
    private Label gabz5;

    private String input;
    private String[] inputs;
    int payment;
    @FXML
    private Label moneyGabz;
    public void initialize() throws IOException {
        filegabz();
        input = Files.readString(file.toPath());
        inputs = input.split(",");
        int  k = random.nextInt(inputs.length);
        if((k&2) != 0){
            k++;
        }
        gabz1.setText(inputs[k]);
        gabz2.setText(inputs[k+2]);
        gabz3.setText(inputs[k+4]);
        gabz4.setText(inputs[k+6]);
        gabz5.setText(inputs[k+8]);
    }

    @FXML
    void gabzPayment(ActionEvent event) throws SQLException, IOException {
        /*gabzTextfield.setVisible(false);
        gabznumber.setVisible(false);
        moneyGabz.setVisible(false);
        gabznumber.setVisible(false);
        int result = login.updateCredit(String.valueOf(IDCard), -payment);
        login.openNewWindow("main.fxml", "Home", event);
        if (result == -1) {System.out.println("Not enough credit!");}
        else {System.out.println("Payment successful! New balance: " + result);}
*/

    }

    @FXML
    void gabznumber(ActionEvent event) throws IOException {
        if(loginID){
            for (int i = 0; i < inputs.length; i=i+2) {
                if (gabzTextfield.getText().equals(inputs[i])) {
                    payment = Integer.parseInt(inputs[i+1]);
                    moneyGabz.setText("" + payment);
                }
            }
        }
        else{
            moneyGabz.setText("first open account");
        }
    }
    private void filegabz() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 10000; i++) {
            int pricegabz = random.nextInt(20000, 2000000);
            long numbergabz = random.nextLong(10000000, 999999999);
            int pishnum = random.nextInt(1, 3);
            long number = Long.parseLong(String.valueOf(pishnum) + String.valueOf(numbergabz));
            fw.write(String.valueOf(number) + ',' + pricegabz + ',');
        }


    }

    public void backtomainFromGabz(ActionEvent actionEvent) {
        login.openNewWindow("main.fxml", "Home", actionEvent);
    }
}
