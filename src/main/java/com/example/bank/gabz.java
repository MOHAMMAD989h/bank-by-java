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

import static com.example.bank.loginpage.*;


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

    profile pro = new profile();

    @FXML
    private Label organGabz;

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
        int result = pro.updateCredit(username, -payment);
        if (result == -1) {
            organGabz.setText("پرداخت نشد");
            gabznumber.setText("");
            gabzTextfield.setText("");
            moneyGabz.setText("");
        }
        else {
            login.openNewWindow("main.fxml", "Home", event);
        }


    }

    @FXML
    void gabznumber(ActionEvent event) throws IOException {
        if(loginID){
            for (int i = 0; i < inputs.length; i=i+2) {
                if (gabzTextfield.getText().equals(inputs[i])) {
                    payment = Integer.parseInt(inputs[i+1]);
                    moneyGabz.setText("" + payment);
                    if(String.valueOf(inputs[i]).trim().charAt(0) == '1'){
                        organGabz.setText(" اداره اب و فاضلاب");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '2') {
                        organGabz.setText("اداره برق");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '3') {
                        organGabz.setText("اداره گاز");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '4') {
                        organGabz.setText("خدمات تلفن ثابت");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '5') {
                        organGabz.setText("خدمات تلفن همراه");
                    }else if (String.valueOf(inputs[i]).trim().charAt(0) == '6') {
                        organGabz.setText("عوارض شهرداری");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '7') {
                        organGabz.setText("سازمان مالیات");
                    } else if (String.valueOf(inputs[i]).trim().charAt(0) == '8') {
                        organGabz.setText("جرایم رانندگی");
                    } else{
                        organGabz.setText("");
                    }

                }
            }
        }
        else{
            moneyGabz.setText("first open account");
        }
    }
    private void filegabz() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 20000; i++) {
            int pricegabz = random.nextInt(20000, 2000000);
            long numbergabz = random.nextLong(10000000, 999999999);
            int pishnum = random.nextInt(1, 9);
            long number = Long.parseLong(String.valueOf(pishnum) + String.valueOf(numbergabz));
            fw.write(String.valueOf(number) + ',' + pricegabz + ',');
        }


    }

    public void backtomainFromGabz(ActionEvent actionEvent) {
        login.openNewWindow("main.fxml", "Home", actionEvent);
    }
}
