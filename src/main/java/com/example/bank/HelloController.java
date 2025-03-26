package com.example.bank;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public ComboBox<String> com1;
    @FXML
    public Label label1; // لیبل برای نمایش تاریخ

    ObservableList<String> list = FXCollections.observableArrayList(
            "شارژ", "خرید اینترنت", "پرداخت قبوض", "انتقال به کارت", "خرید ارز"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // مقداردهی کمبوباکس
        com1.setItems(list);
        com1.setOnAction(this::handleComboBoxAction);
        // فرمت نمایش تاریخ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");

        // تایمر برای نمایش تاریخ زنده
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now(); // دریافت تاریخ فعلی
            label1.setText("تاریخ: " + now.format(formatter)); // نمایش در لیبل
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // اجرا دائمی
        timeline.play(); // اجرای تایمر
    }

    public void handleComboBoxAction(ActionEvent event) {
        String selectedOption = com1.getValue();
        if ("شارژ".equals(selectedOption)) {
            openNewWindow("charge.fxml", "شارژ");
        } else if ("خرید اینترنت".equals(selectedOption)) {
            openNewWindow("Internet.fxml", "خرید اینترنت");
        } else if ("پرداخت قبوض".equals(selectedOption)) {
            openNewWindow("gabz.fxml", "پرداخت قبوض");
        } else if ("انتقال به کارت".equals(selectedOption)) {
            openNewWindow("cart.fxml", "انتقال به کارت");
        } else if ("خرید ارز".equals(selectedOption)) {
            openNewWindow("arz.fxml", "خرید ارز");
        }
    }

    public void openNewWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 1535, 790);

            // ایجاد و نمایش صفحه جدید
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
