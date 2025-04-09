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
import java.util.ArrayList;
import java.util.List;

public class transferRec {
    @FXML
    private Label moneyCharge;

    @FXML
    private VBox vboxTransferRec;
    profile pro = new profile();
    private String[] inputs;

    File file1 = new File("filetransfer.txt");

    FileReader fr = null;

    private List<productVam> productVam = new ArrayList<productVam>();


    public void initialize() throws IOException {
        String line = Files.readString(file1.toPath());
        inputs = line.split(",");
        for (int i = 0; i < inputs.length; i = i +6) {
            productVam.add(new productVam(inputs[i],inputs[i+1],inputs[i+2],inputs[i+3],inputs[i+4],inputs[i+5],""));
        }
    }
    @FXML
    private void backtoHomeFromCharge(ActionEvent event) {
        pro.openNewWindow("hessabView.fxml","Hessab",event);
    }
    private AnchorPane createproductpane(productVam p) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(120);
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");
        vboxTransferRec.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;");

        //numbercard1
        Label numbercard1Lbl = new Label(p.getName());
        numbercard1Lbl.setLayoutX(50);
        numbercard1Lbl.setLayoutY(50);

        //numbercard2
        Label numbercard2Lbl = new Label( p.getSood());
        numbercard2Lbl.setLayoutX(100);
        numbercard2Lbl.setLayoutY(50);

        //money
        Label moneyLbl = new Label( "قیمت (تومان) :  "+p.getPrice());
        moneyLbl.setLayoutX(150);
        moneyLbl.setLayoutY(50);

        //data
        Label dataLbl = new Label(p.getDiscription());
        dataLbl.setLayoutX(50);
        dataLbl.setLayoutY(20);

        //number
        Label numberLbl = new Label(p.getTime());
        numberLbl.setLayoutX(150);
        numberLbl.setLayoutY(50);

        //date
        Label dateLbl = new Label(p.getPagePath());
        dateLbl.setLayoutX(150);
        dateLbl.setLayoutY(50);


        pane.getChildren().addAll(numbercard2Lbl, moneyLbl,numbercard1Lbl,dateLbl,dateLbl,numberLbl);



        return pane;
    }
}
