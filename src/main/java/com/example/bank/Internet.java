package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.bank.loginpage.IDCard;
import static com.example.bank.loginpage.loginID;

public class Internet {

    @FXML
    private TextField numberCharge;

    @FXML
    private Label moneyCharge;

    @FXML
    private VBox vboxCharge;
    Random random = new Random();
    File file = new File("charge.txt");

    private String input;
    private String[] inputs;


    private List<PRODUCTcharge> productcharges = new ArrayList<PRODUCTcharge>();

    loginpage login = new loginpage();

    public void initialize() throws IOException {
        productcharges.add(new PRODUCTcharge("5000"));
        productcharges.add(new PRODUCTcharge("10000"));
        productcharges.add(new PRODUCTcharge("20000"));
        productcharges.add(new PRODUCTcharge("50000"));


        for (PRODUCTcharge p : productcharges) {
            vboxCharge.getChildren().add(createproductpane(p));
        }

        fileCharge();

        input = Files.readString(file.toPath());
        inputs = input.split(",");
    }

    private AnchorPane createproductpane(PRODUCTcharge productcharge) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");
        vboxCharge.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;");

        //price
        Label PriceLbl = new Label( "قیمت (تومان) :  "+productcharge.getPrice());
        PriceLbl.setLayoutX(50);
        PriceLbl.setLayoutY(20);

        //button
        Button Chargebtn = new Button("خرید");
        Chargebtn.setLayoutX(400);
        Chargebtn.setLayoutY(20);
        Chargebtn.setStyle("    -fx-background-color: linear-gradient(to bottom right ,#FFD700, #d3005f );\n" +
                "    -fx-background-radius: 5px;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-text-fill: #fff;\n" +
                "    -fx-font-size: 14px;");

        pane.getChildren().addAll(PriceLbl,Chargebtn);

        /*Chargebtn.setOnAction((ActionEvent e) -> {
            int result = 0;
            try {
                result = login.updateCredit(String.valueOf(IDCard), -Integer.parseInt(productcharge.getPrice()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            login.openNewWindow("main.fxml", "Home", e);
            if (result == -1) {System.out.println("Not enough credit!");}
            else {System.out.println("Payment successful! New balance: " + result);}
        });*/

        return pane;
    }
    private void fileCharge() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 10000; i++) {
            long numberCharge = random.nextLong(10000000, 99999999);
            int pishnum = random.nextInt(1, 3);
            long number = Long.parseLong("09"+String.valueOf(pishnum) + String.valueOf(numberCharge));
            fw.write(String.valueOf(number) + ',');
        }


    }

    @FXML
    void backtoHomeFromCharge(ActionEvent event) {
        login.openNewWindow("main.fxml","Home",event);
    }

    @FXML
    void nextbtnCharge(ActionEvent event) {
        if(loginID){
            for (int i = 0; i < inputs.length; i=i+2) {
                if (numberCharge.getText().equals(inputs[i])) {
                }
            }
        }
        else{
            moneyCharge.setText("first open account");
        }
    }
}
