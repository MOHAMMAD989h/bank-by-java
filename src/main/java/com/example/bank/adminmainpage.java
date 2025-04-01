package com.example.bank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class adminmainpage {
    @FXML
    private TextField text;

    @FXML
    private TextField text2;

    @FXML
    private Button btn;

    @FXML
    public ListView<String> list1;


    @FXML
    private void textonaction() {
        String a = text.getText();
        String b = text2.getText();
        try {
            FileWriter file = new FileWriter("topics.txt", true);
            file.write(a + "," + b + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        list1.getItems().add(a);
        text.setText("");
        text2.setText("");
    }
    public void main() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Hello!");
            newStage.setScene(new Scene(root, 320, 240));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        try {
            Scanner scan = new Scanner(new File("topics.txt"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, ",");
                if (tokens.hasMoreTokens()) {
                    String topic = tokens.nextToken();
                    list1.getItems().add(topic);
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("خطا در خواندن فایل موضوعات.");
        }
    }
}
