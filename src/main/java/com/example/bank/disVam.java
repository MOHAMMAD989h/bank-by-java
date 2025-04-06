package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class disVam {
    @FXML
    private Label namevamLbl;

    @FXML
    private TextField numbercardfrom;

    @FXML
    private TextField numbercardto;

    @FXML
    private Label pricevamLbl;

    @FXML
    private Label soodvamLbl;

    @FXML
    private Label timevamLbl;

    @FXML
    private Label vamdiscription;

    profile pro = new profile();

    Alert alert ;

    public void initialize() {
        namevamLbl.setText(Vam.nameLoan);
        pricevamLbl.setText(String.valueOf(Vam.priceLoan));
        soodvamLbl.setText(String.valueOf(Vam.soodLoan));
        timevamLbl.setText(String.valueOf(Vam.timeLoan));
    }
    @FXML
    private void getloan(ActionEvent event) throws SQLException {
        int n = pro.updateCredit(numbercardfrom.getText(), Math.toIntExact(Vam.priceLoan));
        if(n < 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully loaned  credits");
            alert.showAndWait();
        }
    }
}
