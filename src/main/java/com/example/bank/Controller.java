package com.example.bank;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private AnchorPane ancktext;
    @FXML
    private Text bank1;
    @FXML
    private Text bank2;
    @FXML
    private Text bank3;
    @FXML
    private Text bank4;
    @FXML
    private Text bank5;
    @FXML
    private Text bank6;
    @FXML
    private Text bank7;
    @FXML
    private Text bank8;
    @FXML
    private Text bank9;
    @FXML
    private Text bank10;
    @FXML
    private Text bank11;
    @FXML
    private Text bank12;

    @FXML
    private GridPane loanOptions;

    @FXML
    private StackPane optionsStackPane;

    @FXML
    private Label timeLabel;

    @FXML
    private GridPane bank;

    @FXML
    private ImageView backgroundImage;

    private AnimationTimer timer;
    private PauseTransition pauseTransition;
    Alert alert;


    loginpage login = new loginpage();

    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTime();
            }
        };
        timer.start();

        imagePaths = new ArrayList<>();
        imagePaths.add(getClass().getResource("/images/Untitled-1.png").toExternalForm());
        imagePaths.add(getClass().getResource("/images/hjmhjmhjm.png").toExternalForm());
        imagePaths.add(getClass().getResource("/images/123.png").toExternalForm());
        startSlideshow();

        // استفاده از Platform.runLater برای اطمینان از تنظیم Scene
        /*Platform.runLater(() -> {
            bindImageSizeToWindow();
        });*/

        // تنظیم رویدادهای موس برای گرید پین‌ها
        setupGridPaneHoverBehavior();
    }

    /*private void bindImageSizeToWindow() {
        // بررسی null نبودن Scene و Window
        if (backgroundImage.getScene() != null && backgroundImage.getScene().getWindow() != null) {
            Stage stage = (Stage) backgroundImage.getScene().getWindow();

            // اتصال fitHeight و fitWidth از ImageView به ارتفاع و عرض Stage
            backgroundImage.fitHeightProperty().bind(stage.heightProperty());
            backgroundImage.fitWidthProperty().bind(stage.widthProperty());
            backgroundImage.setPreserveRatio(false); // این خط را اضافه کنید
        } else {
            System.err.println("Scene یا Window هنوز تنظیم نشده است.");
        }
    }*/
    @FXML
    private ImageView imgg;
    private List<String> imagePaths;
    private int currentIndex = 0;
    private Timeline imageSlideshow;
    @FXML
    private void handleLeftClick () {
        if (!imagePaths.isEmpty()) {

            currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
            showImage();
            resetSlideshow();
        }
    }

    @FXML
    private void handleRightClick() {
        if (!imagePaths.isEmpty()) {

            currentIndex = (currentIndex + 1) % imagePaths.size();
            showImage();
            resetSlideshow();
        }
    }
    private void startSlideshow() {
        imageSlideshow = new Timeline(new KeyFrame(Duration.seconds(5), event -> handleRightClick()));
        imageSlideshow.setCycleCount(Animation.INDEFINITE);
        imageSlideshow.play();
    }

    private void resetSlideshow() {
        if (imageSlideshow != null) {
            imageSlideshow.stop();
            startSlideshow();
        }
    }

    private void showImage() {

        Image image = new Image(imagePaths.get(currentIndex));
        imgg.setImage(image);
    }

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText(now.format(formatter));
    }

    private void setupGridPaneHoverBehavior() {
        // تنظیم رویدادهای موس برای loanOptions
        loanOptions.setOnMouseEntered(event -> {
            loanOptions.setVisible(true);
            cancelPauseTransition();
        });

        loanOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> loanOptions.setVisible(false));
        });

        bank.setOnMouseEntered(event -> {
            bank.setVisible(true);
            cancelPauseTransition();
        });

        bank.setOnMouseExited(event -> {
            startPauseTransition(() -> bank.setVisible(false));
            startPauseTransition(() -> ancktext.setVisible(false));
            startPauseTransition(() -> bank1.setVisible(false));
            startPauseTransition(() -> bank2.setVisible(false));
            startPauseTransition(() -> bank3.setVisible(false));
            startPauseTransition(() -> bank4.setVisible(false));
            startPauseTransition(() -> bank5.setVisible(false));
            startPauseTransition(() -> bank6.setVisible(false));
            startPauseTransition(() -> bank7.setVisible(false));
            startPauseTransition(() -> bank8.setVisible(false));
            startPauseTransition(() -> bank9.setVisible(false));
            startPauseTransition(() -> bank10.setVisible(false));
            startPauseTransition(() -> bank11.setVisible(false));
            startPauseTransition(() -> bank12.setVisible(false));
        });
    }

    private void startPauseTransition(Runnable action) {
        pauseTransition = new PauseTransition(Duration.seconds(0.5)); // تأخیر 0.5 ثانیه
        pauseTransition.setOnFinished(event -> action.run());
        pauseTransition.play();
    }

    private void cancelPauseTransition() {
        if (pauseTransition != null) {
            pauseTransition.stop();
        }
    }

    @FXML
    public void showbankoption (){
        bank.setVisible(true);
        loanOptions.setVisible(false);
    }

    /*@FXML
    public void showServicesOptions() {
        loanOptions.setVisible(false);
        contactOptions.setVisible(false);
        bank.setVisible(false);
    }*/

    @FXML
    public void showLoanOptions() {
        loanOptions.setVisible(true);
        bank.setVisible(false);
    }

    @FXML
    public void hideLoanOptions() {
        startPauseTransition(() -> loanOptions.setVisible(false));
    }


    @FXML
    public void hidebankoption(){
        startPauseTransition(() -> bank.setVisible(false));
    }

    @FXML
    public void hideAllOptions() {
        bank.setVisible(false);
        loanOptions.setVisible(false);
    }



    @FXML
    public void handleLoanOption() {
        System.out.println("Loan option clicked!");
    }

    @FXML
    public void keepbankoption(){
        bank.setVisible(true);
    }

    @FXML
    public void keepLoanOptionsVisible() {
        loanOptions.setVisible(true);
    }

    @FXML
    private void bank1(){
        ancktext.setVisible(true);
        bank1.setVisible(true);
        bank11.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank2(){
        ancktext.setVisible(true);
        bank2.setVisible(true);
        bank1.setVisible(false);
        bank11.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank3(){
        ancktext.setVisible(true);
        bank3.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank11.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank4(){
        ancktext.setVisible(true);
        bank4.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank11.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank5(){
        ancktext.setVisible(true);
        bank5.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank11.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank6(){
        ancktext.setVisible(true);
        bank6.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank11.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank7(){
        ancktext.setVisible(true);
        bank7.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank11.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank8(){
        ancktext.setVisible(true);
        bank8.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank11.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank9(){
        ancktext.setVisible(true);
        bank9.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank11.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank10(){
        ancktext.setVisible(true);
        bank10.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank11.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank11(){
        ancktext.setVisible(true);
        bank11.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank12(){
        ancktext.setVisible(true);
        bank12.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank11.setVisible(false);
    }
    public void openNewWindow(String fxmlFile, String title, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 1535, 790);


            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();


            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HomeToLoginpage(ActionEvent actionEvent) {
        openNewWindow("loginpage.fxml","loginpage",actionEvent);
    }

    public void profile(ActionEvent actionEvent) {
        if(loginpage.loginID) {
            openNewWindow("profile1.fxml", "profile", actionEvent);
        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("please first login");
            alert.showAndWait();
            openNewWindow("loginpage.fxml", "loginpage", actionEvent);
        }
    }
}