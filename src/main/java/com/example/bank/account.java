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
    private AnchorPane changer;
    @FXML
    private AnchorPane maker;
    @FXML
    private AnchorPane introducer;
    @FXML
    public ComboBox<String> com1;
    @FXML
    private ScrollPane myScrollPane;
    ObservableList<String> list = FXCollections.observableArrayList("بانک تجارت ","بانک رفاه", "بانک ملی","بانک دی");
    @FXML
    public ComboBox<String> com2;
    @FXML
    private ScrollPane myScrollPane1;
    ObservableList<String> list1 = FXCollections.observableArrayList("ساخت حساب","معرفی حساب");

    public void initialize(URL location, ResourceBundle resources) {
        com1.setItems(list);
        com2.setItems(list1);
        com2.setOnAction(this::switchForm);
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
        String selectedOption = com2.getValue();
        System.out.println("وارد متد شد");
        if ("معرفی حساب".equals(selectedOption)) {System.out.println("معرفی حساب".equals(selectedOption));
            slider.setNode(changer);
            slider.setToX(580);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((event1) -> {
                maker.setVisible(false);
                introducer.setVisible(true);
            });

            slider.play();
        } else if ("ساخت حساب".equals(selectedOption)) {
            slider.setNode(changer);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));


            slider.setOnFinished((event1) -> {
                maker.setVisible(true);
            });
            slider.play();
        }
    }
}
