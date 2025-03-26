package com.example.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;

public class account {
    @FXML
    public ComboBox<String> com1;
    @FXML
    private ScrollPane myScrollPane;
    ObservableList<String> list = FXCollections.observableArrayList("لپ تاپ ها","تبلت ها و لوازم جانبی", "جدیدترین ها","لوازم جانبی");
}
