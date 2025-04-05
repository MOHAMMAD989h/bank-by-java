package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatArea;

    @FXML
    private Button sendButton;

    profile pro = new profile();

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
        chatArea.setEditable(false);
    }

    public void sendMessage() {
        String userMessage = inputField.getText();
        if (!userMessage.isEmpty()) {
            chatArea.appendText("شما: " + userMessage + "\n");



            try {
                String botResponse = ChatbotService.getAIResponse(userMessage);
                chatArea.appendText("چت‌بات: " + botResponse + "\n");
            } catch (Exception e) {
                chatArea.appendText("⚠ خطا در ارتباط با چت‌بات!\n");
            }

            inputField.clear();
        }
    }

    public void backpage(ActionEvent actionEvent) {
        pro.openNewWindow("main.fxml","Home",actionEvent);
    }
}
