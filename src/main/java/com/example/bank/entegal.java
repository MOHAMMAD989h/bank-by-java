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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

import static com.example.bank.loginpage.*;

public class entegal {
    loginpage login =new loginpage();
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
    private GridPane loanOptions;

    @FXML
    private HBox contactOptions;

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

    profile pro = new profile();

    Connection connect = null;

    PreparedStatement prepare = null;

    ResultSet result = null;
    ResultSet rs = null;

    Alert alert;

    @FXML
    private Button sendPoyaPassword;
    @FXML
    private Button Dargah;

    public static boolean isupdatecredit;
    public static boolean is;

    @FXML
    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTime();
            }
        };
        timer.start();

        applyHoverEffect(text1);
        applyHoverEffect(text3);
        applyHoverEffect(pool);
        applyHoverEffect(cvv);
        applyHoverEffect(month);
        applyHoverEffect(year);
        applyHoverEffect(PasswordPoya);



        // استفاده از Platform.runLater برای اطمینان از تنظیم Scene
        /*Platform.runLater(() -> {
            bindImageSizeToWindow();
        });*/

        // تنظیم رویدادهای موس برای گرید پین‌ها
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

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText(now.format(formatter));
    }

    /*private void setupGridPaneHoverBehavior() {

        // تنظیم رویدادهای موس برای profile
        profile.setOnMouseEntered(event -> {
            profileoption.setVisible(true);
            cancelPauseTransition();
        });

        profile.setOnMouseExited(event -> {
            startPauseTransition(() -> profileoption.setVisible(false));
        });

    }*/

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
    /*public void poya (ActionEvent event) {
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
    }*/

    public void Dargah (ActionEvent event) throws SQLException, IOException {
        if(verifyCode1.equals(PasswordPoya.getText())) {
            long resulttransfer = pro.transferMoney(text1.getText(), text3.getText(), Integer.parseInt(pool.getText()));
            if (resulttransfer < 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("موجودی کم است");
                alert.showAndWait();
            } else {
                pro.fileTransfer(text1.getText(),text3.getText(), -Long.parseLong(pool.getText()),"کارت به کارت");
                pro.fileTransfer(text3.getText(),text1.getText(), +Long.parseLong(pool.getText()),"کارت به کارت");
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("موفقیت امیز بود");
                alert.showAndWait();
                openNewWindow("main.fxml", "Home", event);
            }
        }
    }
    String email;

    public void backToHomeFromEntegal(ActionEvent actionEvent) {
        openNewWindow("main.fxml","home",actionEvent);
    }

    public void sendPoyaPassword(ActionEvent actionEvent) throws SQLException {
        text1.setEditable(false);
        text3.setEditable(false);
        cvv.setEditable(false);
        year.setEditable(false);
        month.setEditable(false);
        connect = DataBase1.connectDB();
        String selectEmailByCardNumber = "SELECT e.email, c.engeza, c.cvv2, c.numbercard FROM employee e JOIN cards c ON e.username = c.username WHERE c.numbercard = ?";

        String time = "", cvv2 = "", numbercard = "", email = "";

        assert connect != null;

        PreparedStatement prepare = connect.prepareStatement(selectEmailByCardNumber);
        prepare.setString(1, text1.getText().trim());
        rs = prepare.executeQuery();
        if (rs.next()) {
            email = rs.getString("email");
            time = rs.getString("engeza");
            cvv2 = rs.getString("cvv2");
            numbercard = rs.getString("numbercard");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("اطلاعات کارت وارد شده معتبر نیست.");
            alert.showAndWait();
            return;
        }

        if (text3.getText().isEmpty() || !numbercard.equals(text1.getText().trim()) || !cvv2.equals(cvv.getText().trim())
                || Integer.parseInt(time) != Integer.parseInt(String.valueOf(year.getText()).trim() + String.valueOf(month.getText()).trim())) {
            System.out.println(String.valueOf(year.getText()).trim() + String.valueOf(month.getText()).trim());
            System.out.println(time);
            System.out.println(cvv.getText().trim());
            System.out.println(cvv2);
            System.out.println(numbercard);
            System.out.println(text1.getText().trim());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("اطلاعات همخوانی ندارد");
            alert.showAndWait();
        } else {
            verifyCode1 = String.valueOf((int) (Math.random() * 900000) + 100000);
            sendEmail(email, "رمز پویا :"+verifyCode1);
            Dargah.setVisible(true);
            sendPoyaPassword.setVisible(false);
        }
    }

    public void backtoHessabView(ActionEvent event) {
        login.openNewWindow("hesab.fxml","نمایش حساب",event);
    }

    void applyHoverEffect(TextField text) {
        Timeline hoverIn = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(text.translateYProperty(), -5),
                        new KeyValue(text.scaleXProperty(), 1.1),
                        new KeyValue(text.scaleYProperty(), 1.1))
        );

        Timeline hoverOut = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(text.translateYProperty(), 0),
                        new KeyValue(text.scaleXProperty(), 1),
                        new KeyValue(text.scaleYProperty(), 1))
        );
        //وقتی ماوس میره روش متد hoverIn اجرا میشه و وقتی خارج می شود متد hoverOut اجرا می شود
        text.setOnMouseEntered(e -> hoverIn.play());
        text.setOnMouseExited(e -> hoverOut.play());
    }
    public void setMethod(String method) {
        text1.setText(method);
    }
}
