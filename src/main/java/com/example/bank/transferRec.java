package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.bank.hessabView.isTransferShort;
import static com.example.bank.loginpage.username;

public class transferRec {
    @FXML
    private Label moneyCharge;

    @FXML
    private VBox vboxTransferRec;
    profile pro = new profile();
    private String[] inputs;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    File file1 = new File("filetransfer.txt");

    FileReader fr = null;


    private List<productVam> productlist = new ArrayList<productVam>();


    public void initialize() throws IOException {
        try {
            String data = "SELECT * FROM cards WHERE username= ?";
            String line = Files.readString(file1.toPath());
            inputs = line.split(",|\\n");
            for (int i = 0; i < inputs.length; i++) {
                System.out.println(inputs[i]);
            }
            conn = DataBase1.connectDB();
            assert conn != null;
            pst = conn.prepareStatement(data);
            pst.setString(1,username);
            rs = pst.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < inputs.length; i += 6) {
                    if (rs.getString("numbercard").trim().equals(inputs[i].trim())) {
                        if(!isTransferShort){
                            productlist.add(new productVam(inputs[i], inputs[i + 1], inputs[i + 2], inputs[i + 3], inputs[i + 4], inputs[i + 5], "",""));
                        }
                        else{
                            productlist.add(new productVam("","","", inputs[i + 3],"","", "",""));
                        }
                    }
                }
            }
            for (productVam product1 : productlist) {
                System.out.println(isTransferShort);
                if(!isTransferShort) {
                    vboxTransferRec.getChildren().add(createproductpane(product1));
                }
                else{
                    vboxTransferRec.getChildren().add(createpaneshort(product1));
                }
            }
            isTransferShort = false;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void backtoHomeFromCharge(ActionEvent event) {
        pro.openNewWindow("hesab.fxml","Hessab",event);
    }
    private AnchorPane createproductpane(productVam p) {
        AnchorPane pane = null;
        pane = new AnchorPane();
        pane.setPrefHeight(150);
        pane.setStyle("-fx-background-color: #f7dc84; -fx-background-radius: 10px;-fx-border-radius:10px;-fx-padding: 15px; -fx-margin: 15px;-fx-border-color: #fff;");
        vboxTransferRec.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;-fx-padding: 15px; -fx-margin: 15px");


        //numbercard1
        Label numbercard1Lbl = new Label("شماره کارت مبدا : " + p.getName());
        numbercard1Lbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        numbercard1Lbl.setLayoutX(300);
        numbercard1Lbl.setLayoutY(50);

        //namecard2
        Label nameLbl = new Label(p.getPrice());
        nameLbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        nameLbl.setLayoutX(50);
        nameLbl.setLayoutY(50);

        //numbercard2
        Label numbercard2Lbl = new Label(p.getSood());
        numbercard2Lbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        numbercard2Lbl.setLayoutX(600);
        numbercard2Lbl.setLayoutY(50);

        //money
        Label moneyLbl = new Label("(تومان) : " + p.getTime());
        moneyLbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        moneyLbl.setLayoutX(900);
        moneyLbl.setLayoutY(50);

        //number
        Label numberLbl = new Label("شماره تراکنش : " + p.getPagePath());
        numberLbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        numberLbl.setLayoutX(300);
        numberLbl.setLayoutY(100);

        //date
        Label dateLbl = new Label("تاریخ : " + p.getDiscription());
        dateLbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        dateLbl.setLayoutX(800);
        dateLbl.setLayoutY(100);

        //colorLbl
        Button color = new Button();

        if (p.getTime().charAt(0) == '-') {
            color.setText("برداشت");
            color.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                    "    -fx-font-size: 18;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-text-fill: white;\n" +
                        "    -fx-background-color: transparrent; -fx-background-color: RED;" +
                        "    -fx-background-radius: 15px;   " +
                        "    -fx-text-fill:  #4d4d4d;\n" +
                        "    -fx-translate-y: -3px;\n" +
                        "    -fx-scale-x: 1.05;\n" +
                        "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                        "    -fx-translate-y: -3px;\n" +
                        "    -fx-scale-x: 1.05;\n" +
                        "    -fx-scale-y: 1.05;");
        } else {
            color.setText(" واریز ");
            color.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                        "    -fx-font-size: 18;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-text-fill: white;\n" +
                        "    -fx-background-color: transparrent; -fx-background-color: GREEN;" +
                        "    -fx-background-radius: 15px;   " +
                        "    -fx-text-fill:  #4d4d4d;\n" +
                        "    -fx-translate-y: -3px;\n" +
                        "    -fx-scale-x: 1.05;\n" +
                        "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                        "    -fx-translate-y: -3px;\n" +
                        "    -fx-scale-x: 1.05;\n" +
                        "    -fx-scale-y: 1.05;");
        }
        color.setLayoutX(50);
        color.setLayoutY(100);

        //copy button
        Button button = new Button("copy");
        button.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                "    -fx-font-size: 12;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-color: transparrent;\n" +
                "    -fx-background-radius: 15px;    -fx-background-color: linear-gradient(to bottom right, #1E8E73,  #1fa784);\n" +
                "    -fx-text-fill:  #4d4d4d;\n" +
                "    -fx-background-color: linear-gradient(to bottom right,#4A80D0 ,#70A0E0,  #90B0C8);\n" +
                "    -fx-translate-y: -3px;\n" +
                "    -fx-scale-x: 1.05;\n" +
                "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                "    -fx-background-color: linear-gradient(to bottom right,#4A80D0 ,#70A0E0,  #90B0C8);\n" +
                "    -fx-translate-y: -3px;\n" +
                "    -fx-scale-x: 1.05;\n" +
                "    -fx-scale-y: 1.05;    -fx-cursor: hand;\n");
        button.setLayoutX(600);
        button.setLayoutY(100);

        String clipboardtxt = numbercard1Lbl.getText() +"\n"+nameLbl.getText() +"\n"+numbercard2Lbl.getText()+
                "\n"+moneyLbl.getText()+"\n"+numberLbl.getText()+"\n"+dateLbl.getText()+"\n";
        button.setOnAction(e -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(clipboardtxt);
            clipboard.setContent(content);
        });

        pane.getChildren().addAll(numbercard2Lbl, nameLbl, moneyLbl, numbercard1Lbl, color,dateLbl, numberLbl,button);


        return pane;
    }
    private AnchorPane createpaneshort(productVam p){
        AnchorPane pane = null;
        pane = new AnchorPane();
        pane.setPrefHeight(50);
        pane.setStyle("-fx-background-color: #f7dc84; -fx-background-radius: 10px;-fx-border-radius:10px;-fx-padding: 10px; -fx-margin: 10px;-fx-border-color: #fff;");
        vboxTransferRec.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;-fx-padding: 10px; -fx-margin: 10px");

        //money
        Label moneyLbl = new Label("(تومان) : " + p.getTime());
        moneyLbl.setStyle("   -fx-font-family: \"B Nazanin\";");
        moneyLbl.setLayoutX(300);
        moneyLbl.setLayoutY(25);

        //colorLbl
        Button color = new Button();
        if (p.getTime().charAt(0) == '-') {
            color.setText("برداشت");
            color.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                    "    -fx-font-size: 14;\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-background-color: transparrent; -fx-background-color: RED;" +
                    "    -fx-background-radius: 15px;   " +
                    "    -fx-text-fill:  #4d4d4d;\n" +
                    "    -fx-translate-y: -3px;\n" +
                    "    -fx-scale-x: 1.05;\n" +
                    "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                    "    -fx-translate-y: -3px;\n" +
                    "    -fx-scale-x: 1.05;\n" +
                    "    -fx-scale-y: 1.05;");
        } else {
            color.setText(" واریز ");
            color.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                    "    -fx-font-size: 14;\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-background-color: transparrent; -fx-background-color: GREEN;" +
                    "    -fx-background-radius: 15px;   " +
                    "    -fx-text-fill:  #4d4d4d;\n" +
                    "    -fx-translate-y: -3px;\n" +
                    "    -fx-scale-x: 1.05;\n" +
                    "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                    "    -fx-translate-y: -3px;\n" +
                    "    -fx-scale-x: 1.05;\n" +
                    "    -fx-scale-y: 1.05;");
        }
        color.setLayoutX(50);
        color.setLayoutY(25);

        pane.getChildren().addAll(moneyLbl, color);

        return pane;
    }
}
