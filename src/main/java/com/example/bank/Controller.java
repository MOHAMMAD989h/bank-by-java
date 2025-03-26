package com.example.bank;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private GridPane servicesOptions;

    @FXML
    private GridPane loanOptions;

    @FXML
    private VBox contactOptions;

    @FXML
    private StackPane optionsStackPane;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView backgroundImage;

    private AnimationTimer timer;
    private PauseTransition pauseTransition;

    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTime();
            }
        };
        timer.start();

        // استفاده از Platform.runLater برای اطمینان از تنظیم Scene
        Platform.runLater(() -> {
            bindImageSizeToWindow();
        });

        // تنظیم رویدادهای موس برای گرید پین‌ها
        setupGridPaneHoverBehavior();
    }

    private void bindImageSizeToWindow() {
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
    }

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText(now.format(formatter));
    }

    private void setupGridPaneHoverBehavior() {
        // تنظیم رویدادهای موس برای servicesOptions
        servicesOptions.setOnMouseEntered(event -> {
            servicesOptions.setVisible(true);
            cancelPauseTransition();
        });

        servicesOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> servicesOptions.setVisible(false));
        });

        // تنظیم رویدادهای موس برای loanOptions
        loanOptions.setOnMouseEntered(event -> {
            loanOptions.setVisible(true);
            cancelPauseTransition();
        });

        loanOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> loanOptions.setVisible(false));
        });

        // تنظیم رویدادهای موس برای contactOptions
        contactOptions.setOnMouseEntered(event -> {
            contactOptions.setVisible(true);
            cancelPauseTransition();
        });

        contactOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> contactOptions.setVisible(false));
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
    public void showServicesOptions() {
        servicesOptions.setVisible(true);
        loanOptions.setVisible(false);
        contactOptions.setVisible(false);
    }

    @FXML
    public void hideServicesOptions() {
        startPauseTransition(() -> servicesOptions.setVisible(false));
    }

    @FXML
    public void showLoanOptions() {
        loanOptions.setVisible(true);
        servicesOptions.setVisible(false);
        contactOptions.setVisible(false);
    }

    @FXML
    public void hideLoanOptions() {
        startPauseTransition(() -> loanOptions.setVisible(false));
    }

    @FXML
    public void showContactOptions() {
        contactOptions.setVisible(true);
        servicesOptions.setVisible(false);
        loanOptions.setVisible(false);
    }

    @FXML
    public void hideContactOptions() {
        startPauseTransition(() -> contactOptions.setVisible(false));
    }

    @FXML
    public void hideAllOptions() {
        servicesOptions.setVisible(false);
        loanOptions.setVisible(false);
        contactOptions.setVisible(false);
    }

    @FXML
    public void handleServiceOption() {
        System.out.println("Service option clicked!");
    }


    @FXML
    public void handleLoanOption() {
        System.out.println("Loan option clicked!");
    }

    @FXML
    public void handleContactOption() {
        System.out.println("Contact option clicked!");
    }

    @FXML
    public void keepServicesOptionsVisible() {
        servicesOptions.setVisible(true);
    }

    @FXML
    public void keepLoanOptionsVisible() {
        loanOptions.setVisible(true);
    }

    @FXML
    public void keepContactOptionsVisible() {
        contactOptions.setVisible(true);
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
    public void handleServiceOption1(ActionEvent event) {
        openNewWindow("entegal.fxml","انتقال وجه",event);
    }

    public void HomeToLoginpage(ActionEvent actionEvent) {
        openNewWindow("loginpage.fxml","loginpage",actionEvent);
    }

    public void profile(ActionEvent actionEvent) {
        openNewWindow("profile1.fxml","profile",actionEvent);
    }

    public void handleServiceOption2(ActionEvent actionEvent) {openNewWindow("gabz.fxml","gabz",actionEvent);}
}