package com.example.bank;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

public class Hesab {
    @FXML
    private Label messagelabel;

    @FXML
    private Label userlabel;

    @FXML
    private Label codelabel;

    @FXML
    private Label numlabel;

    @FXML
    private void copyuser() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String username = userlabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(username);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }
    @FXML
    private void copycode() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String code = codelabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(code);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }
    @FXML
    private void copynum() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String num = numlabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(num);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }

    public void HomeToLoginpage(ActionEvent actionEvent) {
    }

    public void profile(ActionEvent actionEvent) {

    }
}
