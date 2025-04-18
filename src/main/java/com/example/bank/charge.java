package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

public class charge {

    @FXML
    private TextField numberCharge;

    @FXML
    private Label moneyCharge;

    @FXML
    private VBox vboxCharge;

    Random random = new Random();
    File file = new File("charge.txt");

    @FXML
    private TextField numberphone;

    @FXML
    private VBox vboxInternet;

    private String input;
    private String[] inputs;
    Alert alert;

    Connection connect;
    PreparedStatement prepare;
    ResultSet rs;

    private List<productCharge> productcharges = new ArrayList<productCharge>();
    profile pro=new profile();
    Hesab pro1 = new Hesab();
    public void setMethod(String method) throws IOException{
        productcharges.add(new productCharge("همراه اول","5000"));
        productcharges.add(new productCharge("همراه اول","10000"));
        productcharges.add(new productCharge("همراه اول","20000"));
        productcharges.add(new productCharge("همراه اول","50000"));
        productcharges.add(new productCharge("ایرانسل","5000"));
        productcharges.add(new productCharge("ایرانسل","10000"));
        productcharges.add(new productCharge("ایرانسل","20000"));
        productcharges.add(new productCharge("ایرانسل","50000"));
        System.out.println("به ست وارد شد");


        for (productCharge p : productcharges) {
            vboxCharge.getChildren().add(createproductpane(p));
        }


        input = Files.readString(file.toPath());
        inputs = input.split(",");
        numberCharge.setText(method);
        System.out.println(method);
    }


    private AnchorPane createproductpane(productCharge productcharge) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setStyle("-fx-background-color: #f7dc84; -fx-background-radius: 10px;-fx-border-radius:10px;-fx-padding: 15px; -fx-margin: 15px;-fx-border-color: #fff;");
        vboxCharge.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;-fx-padding: 15px; -fx-margin: 15px");

        //price
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
                        System.out.println("Payment successful! New balance: " + result);
                        try {
                            pro.fileTransfer(numberCharge.getText(),"", (long) -Integer.parseInt(productcharge.getPrice()),"شارژ سیمکارت");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        pro.openNewWindow("main.fxml", "Home", e);
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("این شماره ثبت نام نشده است");
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


    @FXML
    private void backtoHesabFromCharge(ActionEvent event) {
        String method=numberCharge.getText();
        hessabView(method,event);
    }
    public void hessabView(String hessabNum,ActionEvent event) {
        openNewWindow2("hessabView.fxml","نمایش حساب",hessabNum,event);
    }
    public void openNewWindow2 (String fxmlFile, String title, String method, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 1535, 790);

            //ارسال متد

            hessabView controller = loader.getController();
            controller.setMethod(method);

            // ایجاد و نمایش صفحه جدید
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

            // بستن صفحه فعلی
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
