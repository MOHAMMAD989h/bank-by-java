package com.example.bank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class entegal {
    @FXML
    private Label message;
    @FXML
    private TextField pool;

    @FXML
    private TextField month;

    @FXML
    private TextField year;

    @FXML
    private TextField cvv;

    @FXML
    private HBox profileoption;

    @FXML
    private GridPane servicesOptions;

    @FXML
    private GridPane loanOptions;

    @FXML
    private HBox contactOptions;

    @FXML
    private StackPane optionsStackPane;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView backgroundImage;

    private AnimationTimer timer;
    private PauseTransition pauseTransition;
    @FXML
    public Label text2;
    @FXML
    public TextField text3;
    @FXML
    public Label text4;
    @FXML
    public TextField text1;
    @FXML
    public ImageView img1;
    @FXML
    public ImageView img2;
    @FXML
    public  Label text5;
    public Integer randomnumber;
    @FXML
    private TextField PasswordPoya;
    @FXML
    private Button profile;




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
        text1.textProperty().addListener((observable, oldValue, newValue) -> {
            // چک کردن اینکه فقط اعداد وارد شوند و حداکثر ۱۶ رقم باشد
            if (!newValue.matches("\\d*")) {
                text1.setText(oldValue); // جلوگیری از ورود کاراکتر غیر عددی
            } else if (newValue.length() > 16) {
                text1.setText(oldValue); // جلوگیری از ورود بیش از ۱۶ رقم
            }

            // نمایش پیام خطا اگر ورودی کمتر یا بیشتر از ۱۶ رقم باشد
            if (text1.getText().length() != 16) {
                text2.setText("شماره کارت مبدا را درست وارد کنید!");
            } else {
                text2.setText(""); // اگر ورودی صحیح بود، پیام خطا حذف شود
            }
            if(text1.getText().length() == 16) {
                long amount = Long.parseLong(text1.getText());
                long amount2= amount/10000000000L;
                System.out.println("شماره کارت ورودی: " + text1.getText());
                System.out.println("6 رقم اول: " + (Long.parseLong(text1.getText()) / 10000000000L));

                try{
                    if(amount2==610433){
                        String imagePath = "/images/mellat.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if(amount2==502806){
                        String imagePath = "/images/shahr.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==621986){
                        String imagePath = "/images/saman.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==628023){
                        String imagePath = "/images/maskan.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==589463){
                        String imagePath = "/images/refah.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==603799){
                        String imagePath = "/images/melli.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==585983){
                        String imagePath = "/images/tejarat.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==639370){
                        String imagePath = "/images/mehr.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==603770){
                        String imagePath = "/images/kesh.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                    else if (amount2==589210){
                        String imagePath = "/images/sepah.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img1.setImage(img);
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("خطا");
                }

            }

        });
        text3.textProperty().addListener((observable, oldValue, newValue) -> {
            // چک کردن اینکه فقط اعداد وارد شوند و حداکثر ۱۶ رقم باشد
            if (!newValue.matches("\\d*")) {
                text3.setText(oldValue); // جلوگیری از ورود کاراکتر غیر عددی
            } else if (newValue.length() > 16) {
                text3.setText(oldValue); // جلوگیری از ورود بیش از ۱۶ رقم
            }

            // نمایش پیام خطا اگر ورودی کمتر یا بیشتر از ۱۶ رقم باشد
            if (text3.getText().length() != 16) {
                text4.setText("شماره کارت مقصد را درست وارد کنید!");
            } else {
                text4.setText(""); // اگر ورودی صحیح بود، پیام خطا حذف شود
            }
            //ppppppppppppppppppppppppppp
            if(text3.getText().length() == 16) {
                long amount3 = Long.parseLong(text3.getText());
                long amount4= amount3/10000000000L;
                System.out.println("شماره کارت ورودی: " + text3.getText());
                System.out.println("6 رقم اول: " + (Long.parseLong(text3.getText()) / 10000000000L));

                try{
                    if(amount4==610433){
                        String imagePath = "/images/mellat.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if(amount4==502806){
                        String imagePath = "/images/shahr.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==621986){
                        String imagePath = "/images/saman.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==628023){
                        String imagePath = "/images/maskan.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==589463){
                        String imagePath = "/images/refah.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==603799){
                        String imagePath = "/images/melli.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==585983){
                        String imagePath = "/images/tejarat.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==639370){
                        String imagePath = "/images/mehr.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==603770){
                        String imagePath = "/images/kesh.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                    else if (amount4==589210){
                        String imagePath = "/images/sepah.png";
                        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
                        img2.setImage(img);
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("خطا");
                }

            }
        });
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
        // تنظیم رویدادهای موس برای profile
        profile.setOnMouseEntered(event -> {
            profileoption.setVisible(true);
            cancelPauseTransition();
        });

        profile.setOnMouseExited(event -> {
            startPauseTransition(() -> profileoption.setVisible(false));
        });

        // تنظیم رویدادهای موس برای contactOptions
        contactOptions.setOnMouseEntered(event -> {
            contactOptions.setVisible(true);
            cancelPauseTransition();
        });

        contactOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> contactOptions.setVisible(false));
        });
        profileoption.setOnMouseEntered(event -> {
            profileoption.setVisible(true);
            cancelPauseTransition();
        });

        profileoption.setOnMouseExited(event -> {
            startPauseTransition(() -> profileoption.setVisible(false));
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
        profileoption.setVisible(false);
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
        profileoption.setVisible(false);
    }

    @FXML
    public void hideLoanOptions() {
        startPauseTransition(() -> loanOptions.setVisible(false));
    }

    @FXML
    public void showprofileoption() {
        profileoption.setVisible(true);
        loanOptions.setVisible(false);
        servicesOptions.setVisible(false);
        contactOptions.setVisible(false);
    }

    @FXML
    public void hideprofileoption() {
        startPauseTransition(() -> profileoption.setVisible(false));
    }

    @FXML
    public void showContactOptions() {
        contactOptions.setVisible(true);
        servicesOptions.setVisible(false);
        loanOptions.setVisible(false);
        profileoption.setVisible(false);
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
        profileoption.setVisible(false);
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
    public void keepproofileoption(){
        profileoption.setVisible(true);}

    @FXML
    public void hideproofileoption() {
        startPauseTransition(() -> profileoption.setVisible(false));
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
    public void poya (ActionEvent event) {
        PasswordPoya.setVisible(true);
        Random rand = new Random();
        randomnumber = rand.nextInt(100000,999999);
        text5.setText(String.valueOf(randomnumber));
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                randomnumber =null;
                Platform.runLater(() -> {
                    text5.setText(String.valueOf(randomnumber));
                    System.out.println(randomnumber);
                    //PasswordPoya.setVisible(false);
                    text5.setVisible(false);

                });
            }
        };
        timer.schedule(task, 10000);
    }

    public void Dargah (ActionEvent event) {
        try {
                if (text1.getText().isEmpty() && text3.getText().isEmpty() && pool.getText().isEmpty() && cvv.getText().isEmpty() && month.getText().isEmpty() && year.getText().isEmpty() ) {
                    message.setText("لطفا فیلد هارا به درستی وارد کنید");
                }
            if (PasswordPoya.getText().equals(String.valueOf(randomnumber)) && message.getText().isEmpty()) {
                openNewWindow("main.fxml", "Home", event);
            }
        }
        catch (Exception e) {
            System.out.println("eror");
        }

    }

    public void backToHomeFromEntegal(ActionEvent actionEvent) {
        openNewWindow("main.fxml","home",actionEvent);
    }
}
