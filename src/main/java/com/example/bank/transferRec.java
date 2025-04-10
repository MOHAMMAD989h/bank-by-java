package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

import static com.example.bank.hessabView.numbercard;
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


    private List<productVam> productVam = new ArrayList<productVam>();


    public void initialize() throws IOException {
        try {
            String data = "SELECT * FROM cards WHERE username= ?";
            String line = Files.readString(file1.toPath());
            inputs = line.split(",");
            for (int i = 0; i < inputs.length; i = i + 6) {
                conn = DataBase1.connectDB();
                assert conn != null;
                pst = conn.prepareStatement(data);
                pst.setString(1,username);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if(rs.getString("numbercard").equals(inputs[i])) {
                        productVam.add(new productVam(inputs[i], inputs[i + 1], inputs[i + 2], inputs[i + 3], inputs[i + 4], "", ""));
                    }
                }
            }

            for (productVam product1 : productVam) {
                vboxTransferRec.getChildren().add(createproductpane(product1));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void backtoHomeFromCharge(ActionEvent event) {
        pro.openNewWindow("hessabView.fxml","Hessab",event);
    }
    private AnchorPane createproductpane(productVam p) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");
        vboxTransferRec.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;");

        //numbercard1
        Label numbercard1Lbl = new Label("شماره کارت مبدا"+p.getName());
        numbercard1Lbl.setLayoutX(50);
        numbercard1Lbl.setLayoutY(50);

        //namecard2
        Label nameLbl = new Label(p.getPrice());
        nameLbl.setLayoutX(300);
        nameLbl.setLayoutY(50);

        //numbercard2
        Label numbercard2Lbl = new Label(p.getSood());
        numbercard2Lbl.setLayoutX(500);
        numbercard2Lbl.setLayoutY(50);

        //money
        Label moneyLbl = new Label( "(تومان)"+p.getTime());
        moneyLbl.setLayoutX(700);
        moneyLbl.setLayoutY(50);

        //number
        Label numberLbl = new Label("شماره تراکنش"+p.getPagePath());
        numberLbl.setLayoutX(900);
        numberLbl.setLayoutY(50);

        //date
        Label dateLbl = new Label("تاریخ "+p.getDiscription());
        dateLbl.setLayoutX(1300);
        dateLbl.setLayoutY(50);



        pane.getChildren().addAll(numbercard2Lbl,nameLbl, moneyLbl,numbercard1Lbl,dateLbl,numberLbl);



        return pane;
    }
}
