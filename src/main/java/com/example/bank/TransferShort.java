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


public class TransferShort {


    @FXML
    private Label moneyCharge;

    @FXML
    private VBox vboxTransferRec;

    profile pro = new profile();

    @FXML
    void backtoHomeFromCharge(ActionEvent event) {
        pro.openNewWindow("hesab.fxml","Hesab",event);
    }
}
