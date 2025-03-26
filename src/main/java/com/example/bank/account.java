package com.example.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class account implements Initializable {
    @FXML
    public ComboBox<String> com1;
    @FXML
    private ScrollPane myScrollPane;
    ObservableList<String> list = FXCollections.observableArrayList("بانک تجارت ","بانک رفاه", "بانک ملی","بانک دی");

    public void initialize(URL location, ResourceBundle resources) {
        com1.setItems(list);
        /*myScrollPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                event.consume();
                double scrollAmount = (event.getCode() == KeyCode.UP) ? 0.05 : -0.05 ;
                double newValue = myScrollPane.getVvalue() + scrollAmount;
                newValue = Math.max(0, Math.min(1, newValue));

                myScrollPane.setVvalue(newValue);
            }
        });*/

    }
}
