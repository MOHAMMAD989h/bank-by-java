package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.bank.loginpage.*;


public class internet {
    @FXML
    private Label moneyCharge;

    @FXML
    private TextField numberCharge;

    @FXML
    private TextField numberphone;

    @FXML
    private VBox vboxInternet;

    Random random = new Random();
    File file = new File("charge.txt");


    private String input;
    private String[] inputs;
    Alert alert;

    profile pro = new profile();

    Connection connect;
    PreparedStatement prepare;
    ResultSet rs;

    charge Charge = new charge();

    private List<productCharge> productInternet = new ArrayList<productCharge>();


    public void initialize() throws IOException {
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت هفتگی","15000"));
        productInternet.add(new productCharge("همراه اول دو گیگ اینترنت هفتگی","18000"));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت هفتگی","22000"));
        productInternet.add(new productCharge("همراه اول پنچ گیگ اینترنت هفتگی","25000"));
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت ماهانه","19000"));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت ماهانه","24000"));
        productInternet.add(new productCharge("همراه اول پنج گیگ اینترنت ماهانه","28000"));
        productInternet.add(new productCharge("همراه اول هفت گیگ اینترنت ماهانه","33000"));
        productInternet.add(new productCharge("ایرانسل اول یک گیگ اینترنت هفتگی","15000"));
        productInternet.add(new productCharge("ایرانسل  اول دو گیگ اینترنت هفتگی","18000"));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت هفتگی","22000"));
        productInternet.add(new productCharge("ایرانسل  اول پنچ گیگ اینترنت هفتگی","25000"));
        productInternet.add(new productCharge("ایرانسل  اول یک گیگ اینترنت ماهانه","19000"));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت ماهانه","24000"));
        productInternet.add(new productCharge("ایرانسل  اول پنج گیگ اینترنت ماهانه","28000"));
        productInternet.add(new productCharge("ایرانسل  اول هفت گیگ اینترنت ماهانه","33000"));

        for (productCharge p : productInternet) {
            vboxInternet.getChildren().add(createproductpane(p));
        }

        fileCharge();

        input = Files.readString(file.toPath());
        inputs = input.split(",");
    }private AnchorPane createproductpane(productCharge productcharge) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setStyle("-fx-background-color: #f7dc84; -fx-background-radius: 10px;-fx-border-radius:10px;-fx-padding: 15px; -fx-margin: 15px;-fx-border-color: #fff;");
        vboxInternet.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;-fx-padding: 15px; -fx-margin: 15px");

        //type
        Label typeLbl = new Label( productcharge.getType());
        typeLbl.setLayoutX(50);
        typeLbl.setLayoutY(20);

        //price
        Label PriceLbl = new Label( "قیمت (تومان) :  "+productcharge.getPrice());
        PriceLbl.setLayoutX(600);
        PriceLbl.setLayoutY(20);

        //button
        Button Chargebtn = new Button("خرید");
        Chargebtn.setLayoutX(900);
        Chargebtn.setLayoutY(20);
        Chargebtn.setStyle("    -fx-background-color: linear-gradient(to bottom right, #1E8E73,  #1fa784);\n" +
                "    -fx-background-radius: 10px;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-text-fill: #fff;\n" +
                "    -fx-font-size: 14px;"+"-fx-font-weight: bold;"+"-fx-font-family: 'B Nazanin';");

        pane.getChildren().addAll(typeLbl,PriceLbl,Chargebtn);

        Chargebtn.setOnAction((ActionEvent e) -> {
            boolean flag = false;

            if (loginID) {
                for (int i = 0; i < inputs.length; i++) {
                    if (numberphone.getText().equals(inputs[i])) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    int result = 0;
                    try {
                        connect = DataBase1.connectDB();
                        String data = "SELECT * FROM cards WHERE username=?";
                        assert connect != null;
                        prepare = connect.prepareStatement(data);
                        prepare.setString(1, username);
                        rs = prepare.executeQuery();

                        while (rs.next()) {
                            if (numberCharge.getText().equals(rs.getString("numbercard"))) {
                                result = pro.updateCredit(numberCharge.getText(), -Integer.parseInt(productcharge.getPrice()));
                            }
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (result == -1) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("خطا");
                        alert.setTitle("ERROR");
                        alert.showAndWait();
                    } else {
                        try {
                            pro.fileTransfer(numberCharge.getText(),"", (long) -Integer.parseInt(productcharge.getPrice()),"خرید اینترنت");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("Payment successful! New balance: " + result);
                        pro.openNewWindow("main.fxml", "Home", e);
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("شماره همراه ثبت نشده است");
                    alert.setTitle("ERROR");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("لطفا اول وارد شوید");
                alert.setTitle("ERROR");
                alert.showAndWait();
            }
        });

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
        pro.openNewWindow("hesab.fxml","Account",event);
    }
}
