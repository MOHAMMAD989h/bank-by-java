package com.example.bank;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class account implements Initializable {
    @FXML
    private MenuItem sakht;
    @FXML
    private MenuItem Introduce;
    @FXML
    private AnchorPane side_form;
    @FXML
    private  MenuButton MenuButton;
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

    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == sakht) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((event1) -> {
                MenuButton.setVisible(true);
            });

            slider.play();
        } else if (event.getSource() == Introduce) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((event1) -> {
                MenuButton.setVisible(true);
            });
            slider.play();
        }
    }
}
