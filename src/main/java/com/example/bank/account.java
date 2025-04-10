package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.*;
import java.time.LocalTime;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.time.*;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.bank.loginpage.username;

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
    @FXML
    public ComboBox<String> com2;
    @FXML
    private ComboBox<String> comaccount;
    @FXML
    private ComboBox<String> comaccountcreate;
    @FXML
    private ScrollPane myScrollPane1;
    ObservableList<String> list1 = FXCollections.observableArrayList("ساخت حساب","معرفی حساب");

    ObservableList<String> listaccount = FXCollections.observableArrayList("حساب جاری","حساب سپرده");
    ObservableList<String> listaccountcreate = FXCollections.observableArrayList("حساب جاری","حساب سپرده");
    File selectedImageFile = null;
    private  boolean issendphoto =true;

    @FXML
    private Button sendnationcardphoto;
    @FXML
    private Button sendnationcardphoto1;
    Image imageuser;

    @FXML
    private TextField homeNumberGet;
    @FXML
    private TextField accountPassword;
    @FXML
    private TextField tekrarRamz;
    String regdata;

    private Connection connect;
    private PreparedStatement prepare;

    Random random = new Random();
    Alert alert;
    loginpage login = new loginpage();

    @FXML
    private TextField cartNumGetter;
    @FXML
    private PasswordField accountPassword1;
    @FXML
    private PasswordField Cvv2Getter;
    @FXML
    private PasswordField yearofExpire;
    @FXML
    private PasswordField monthofExpire ;
    @FXML
    private TextField phonehome;

    @FXML
    private Label showName;
    @FXML
    private Label showName1;
    @FXML
    private Label showPhoneNumber;
    @FXML
    private Label showPhoneNumber1;
    @FXML
    private Label showNationcode;
    @FXML
    private Label showNationcode1;
    @FXML
    private TextField deposidMoney;

    @FXML
    private Slider deposidMonth;

    @FXML
    private Label deposidSodd;
    @FXML
    private ImageView logoShow1;
    ObservableList<String> list = FXCollections.observableArrayList("بانک تجارت","بانک رفاه", "بانک ملی","بانک ملت","بانک شهر","بانک مسکن","بانک مهر","بانک سامان","بانک کشاورزی","بانک اینده","بانک سپه","بانک دی");

    Timeline timeline = new Timeline();
    private Long deposidMonthnum;
    @FXML
    private ComboBox<String> comdeposidtype;
    ObservableList<String> listdeposid = FXCollections.observableArrayList("عادی","قرض الحسنه");
    @FXML
    private AnchorPane vboxDeposid;
    @FXML
    private TextField numbercardDeposid;
    String BigNumberString;
    byte[] imageData;

    profile pro = new profile();
    boolean iscreateaccount=false;


    int persianMonth;
    int persianYear;
    private ResultSet result;
    private ResultSet rs;


    public void initialize(URL location, ResourceBundle resources) {
        com1.setItems(list);
        com2.setItems(list1);
        com2.setOnAction(this::switchForm);
        comaccount.setItems(listaccount);
        comaccountcreate.setItems(listaccountcreate);
        comdeposidtype.setItems(listdeposid);
        scheduleDailyInterestCheck();

        deposidMonth.setMin(3);
        deposidMonth.setMax(12);
        deposidMonth.setValue(3);
        deposidMonth.setBlockIncrement(1);


        // تنظیم اسلایدر برای انتخاب تنها اعداد صحیح
        deposidMonth.setMajorTickUnit(1);
        deposidMonth.setMinorTickCount(0);
        deposidMonth.setSnapToTicks(true);
        deposidMonth.setShowTickMarks(true);
        deposidMonth.setShowTickLabels(true);
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            deposidMonthnum =  Math.round(deposidMonth.getValue());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // فعال‌سازی انیمیشن هنگام کشیدن اسلایدر
        deposidMonth.valueProperty().addListener((observable, oldValue, newValue) -> {
            timeline.playFromStart(); // اجرای انیمیشن هنگام تغییر مقدار
        });

        // به‌روز رسانی برچسب با استفاده از lambda expression هنگام تغییر مقدار اسلایدر
        deposidMonth.valueProperty().addListener((observable, oldValue, newValue) ->{
                    deposidMonthnum = (long) newValue.intValue();
                }
        );

        deposidSodd.setText("سود :"+deposidMonth.getValue() + 3);
        try {
            connect = DataBase1.connectDB();
            String selectdata = "SELECT * FROM employee";
            assert connect != null;
            prepare = connect.prepareStatement(selectdata);
            rs = prepare.executeQuery(selectdata);

            while (rs.next()) {
                showName.setText(rs.getString("name"));
                showName1.setText(rs.getString("name"));
                showPhoneNumber.setText(rs.getString("numberphone"));
                showPhoneNumber1.setText(rs.getString("numberphone"));
                showNationcode.setText(rs.getString("nationcode"));
                showNationcode1.setText(rs.getString("nationcode"));
            }
        }
        catch (Exception e){e.printStackTrace();}
        com1.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(com1.getValue().equals("بانک تجارت")){
                Image image = new Image("../images/tejarat.png");
                logoShow1.setImage(image);
            } else if (com1.getValue().equals("بانک رفاه")) {
                Image image = new Image("../images/refah.png");
                logoShow1.setImage(image);
            } else if (com1.getValue().equals("بانک ملی")) {
                Image image = new Image("../images/melli.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک ملت")) {
                Image image = new Image("../images/mellat.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک شهر")) {
                Image image = new Image("../images/shahr.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک شهر")) {
                Image image = new Image("../images/shahr.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک مسکن")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک مهر")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک سامان")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک کشاورزی")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک اینده")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک سپه")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
            else if (com1.getValue().equals("بانک دی")) {
                Image image = new Image("../images/maskan.png");
                logoShow1.setImage(image);
            }
        });
        timeline = new Timeline(new KeyFrame(Duration.hours(720), event -> {

        }));

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
    public void helplogin(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("if you see any errors, please email us " +
                " |@AuruseBank.gamil.com|");
        alert.showAndWait();
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

    public void createbankaccount(ActionEvent actionEvent) throws IOException, SQLException {
        if(homeNumberGet.getText().length() < 3|| !accountPassword.getText().matches("[0-9]{4}") ||
        !tekrarRamz.getText().equals(accountPassword.getText()) || issendphoto  || comaccount.getValue().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("please fill blanks");
            alert.showAndWait();

        }else {
            imageData = Files.readAllBytes(selectedImageFile.toPath());

            LocalDate now = LocalDate.now(ZoneId.of("Asia/Tehran"));
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();

            BigInteger randomBigInt = new BigInteger(33, random).add(new BigInteger("1000000000"));

            BigNumberString = "504412" + randomBigInt.toString();

            // تبدیل میلادی به شمسی (تخمینی)
            persianYear = year - ((month < 3) || (month == 3 && day < 21) ? 622 : 621) + 10;

            if ((month == 3 && day >= 21) || month == 4 || (month == 5 && day <= 21)) persianMonth = 1;  // فروردین
            else if ((month == 5 && day >= 22) || month == 6 || (month == 7 && day <= 22))
                persianMonth = 2;  // اردیبهشت
            else if ((month == 7 && day >= 23) || month == 8 || (month == 9 && day <= 22))
                persianMonth = 3;  // خرداد
            else if ((month == 9 && day >= 23) || month == 10 || (month == 11 && day <= 22))
                persianMonth = 4;  // تیر
            else if ((month == 11 && day >= 23) || month == 12 || (month == 1 && day <= 19))
                persianMonth = 5;  // مرداد
            else if ((month == 1 && day >= 20) || month == 2 || (month == 3 && day <= 20))
                persianMonth = 6;  // شهریور
            else if ((month == 3 && day >= 21) || month == 4 || (month == 5 && day <= 21)) persianMonth = 7;  // مهر
            else if ((month == 5 && day >= 22) || month == 6 || (month == 7 && day <= 22))
                persianMonth = 8;  // آبان
            else if ((month == 7 && day >= 23) || month == 8 || (month == 9 && day <= 22)) persianMonth = 9;  // آذر
            else if ((month == 9 && day >= 23) || month == 10 || (month == 11 && day <= 22))
                persianMonth = 10; // دی
            else if ((month == 11 && day >= 23) || month == 12 || (month == 1 && day <= 19))
                persianMonth = 11; // بهمن
            else persianMonth = 12; // اسفند

            String yearPer = String.format("%02d", persianYear % 100);

            String monthPer = String.format("%02d", persianMonth);

            String yyMM = String.valueOf(yearPer + monthPer);


            int cvv2 = random.nextInt(100, 1000);

            connect = DataBase1.connectDB();
            if(comaccount.getValue().equals("حساب جاری")) {

                regdata = "INSERT INTO cards (username,money,credit,numbercard,cvv2,engeza,bankname,phonenumberhome,password,imagecard) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";


                assert connect != null;

                prepare = connect.prepareStatement(regdata);
                prepare.setString(1, username);
                prepare.setString(2, "0");
                prepare.setString(3, "0");
                prepare.setString(4, BigNumberString);
                prepare.setString(5, String.valueOf(cvv2));
                prepare.setString(6, yyMM);
                prepare.setString(7, "Aureous Bank");
                prepare.setString(8, homeNumberGet.getText());
                prepare.setString(9, accountPassword.getText());
                prepare.setString(10, Arrays.toString(imageData));
                int rowsAffected = prepare.executeUpdate();
            } else if (comaccount.getValue().equals("حساب سپرده")) {
                maker.setVisible(false);
                introducer.setVisible(false);
                vboxDeposid.setVisible(true);
            }

            homeNumberGet.clear();
            accountPassword.clear();
            tekrarRamz.clear();

            if(comaccount.getValue().equals("حساب جاری")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Seccessfully");
                alert.showAndWait();

                login.openNewWindow("profile1.fxml", "Profile", actionEvent);
            }
        }

    }
    public void estelamBank(ActionEvent actionEvent) throws IOException, SQLException {
        if(!cartNumGetter.getText().matches("[0-9]{16}")  ||  !accountPassword1.getText().matches("[0-9]{4}")
        ||  Cvv2Getter.getText().length() < 3 || !yearofExpire.getText().matches("[0-9]{2}")  || !monthofExpire.getText().matches("[0-9]{2}")
        || phonehome.getText().length() < 4 || issendphoto || com1.getValue() == null || persianMonth > 12 || Integer.parseInt(yearofExpire.getText()) < persianYear  || comaccountcreate.getValue().equals("")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("please fill blanks");
            alert.showAndWait();
        }
        else {
            iscreateaccount = true;

            String selectdata = "SELECT * FROM cards WHERE numbercard =?";

            connect = DataBase1.connectDB();

            assert connect != null;
            prepare = connect.prepareStatement(selectdata);
            prepare.setString(1, cartNumGetter.getText());
            result = prepare.executeQuery();
            if (!result.next()) {
                if(comaccountcreate.getValue().equals("حساب جاری")) {

                    regdata = "INSERT INTO cards (username,money,credit,numbercard,cvv2,engeza,bankname,phonenumberhome,password,imagecard) " +
                            "VALUES(?,?,?,?,?,?,?,?,?,?)";

                    assert connect != null;

                    BigInteger randomBigInt = new BigInteger(23, random).add(new BigInteger("1000000"));

                    byte[] imageData = Files.readAllBytes(selectedImageFile.toPath());
                    String yyMM = String.valueOf(yearofExpire.getText()) + String.valueOf(monthofExpire.getText());

                    prepare = connect.prepareStatement(regdata);
                    prepare.setString(1, username);
                    prepare.setString(2, String.valueOf(randomBigInt));
                    prepare.setString(3, String.valueOf(randomBigInt));
                    prepare.setString(4, cartNumGetter.getText());
                    prepare.setString(5, Cvv2Getter.getText());
                    prepare.setString(6, yyMM);
                    prepare.setString(7, com1.getValue());
                    prepare.setString(8, phonehome.getText());
                    prepare.setString(9, accountPassword1.getText());
                    prepare.setString(10, Arrays.toString(imageData));
                    int rowsAffected = prepare.executeUpdate();
                }
                else if(comaccountcreate.getValue().equals("حساب سپرده")){
                    maker.setVisible(false);
                    introducer.setVisible(false);
                    vboxDeposid.setVisible(true);
                }


                homeNumberGet.clear();
                accountPassword.clear();
                tekrarRamz.clear();

                if(comaccountcreate.getValue().equals("حساب جاری")) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Seccessfully");
                    alert.showAndWait();
                    login.openNewWindow("profile1.fxml", "Profile", actionEvent);
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You are already logged in");
                alert.showAndWait();
            }
        }
    }
    @FXML
    public void deposidBtn(ActionEvent actionEvent) throws SQLException, IOException {
        if(deposidMoney.getText().isEmpty() || numbercardDeposid.getText().isEmpty() || comdeposidtype.getValue().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("please fill blanks");
            alert.showAndWait();
        }
        else{
            String data = "SELECT * FROM cards WHERE username =?";
            connect = DataBase1.connectDB();
            assert connect != null;
            prepare = connect.prepareStatement(data);
            prepare.setString(1, username);
            result = prepare.executeQuery();
            if (!result.next()) {
                if (!numbercardDeposid.getText().equals(result.getString("numbercard"))) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("numbercard doesn't match");
                    alert.showAndWait();
                    return;
                }
            }
            int n = pro.updateCredit(numbercardDeposid.getText(), -Integer.parseInt(deposidMoney.getText()));
            LocalDate now = null;
            double sood = 0;

            if (n > 0) {
                if(comdeposidtype.getValue().equals("عادی")){
                    sood = deposidMonth.getValue() + 3;
                }
                regdata = "INSERT INTO blockedcard (username,money,sood,numbercard,numbercardto,lastdate,startdate,engeza,bankname,phonenumberhome,password,imagecard) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

                connect = DataBase1.connectDB();

                now = LocalDate.now();

                if (!iscreateaccount) {


                    prepare = connect.prepareStatement(regdata);
                    prepare.setString(1, username);
                    prepare.setString(2, deposidMoney.getText());
                    prepare.setString(3, String.valueOf(sood));
                    prepare.setString(4, BigNumberString);
                    prepare.setString(5, numbercardDeposid.getText());
                    prepare.setDate(6, Date.valueOf(now));
                    prepare.setDate(7, Date.valueOf(now));
                    prepare.setString(8, String.valueOf(deposidMonth.getValue()));
                    prepare.setString(9, "Aureous Bank");
                    prepare.setString(10, phonehome.getText());
                    prepare.setString(11, accountPassword1.getText());
                    prepare.setString(12, Arrays.toString(imageData));
                    int rowsAffected = prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Seccessfully");
                    alert.showAndWait();
                    pro.fileTransfer(numbercardDeposid.getText(),"", -Long.valueOf(deposidMoney.getText()),"میزان انتقال به سپرده");
                    pro.openNewWindow("profile1.fxml", "Profile", actionEvent);
                }
            } else {

                assert connect != null;

                byte[] imageData = Files.readAllBytes(selectedImageFile.toPath());


                prepare = connect.prepareStatement(regdata);
                prepare.setString(1, username);
                prepare.setString(2, deposidMoney.getText());
                prepare.setString(3, String.valueOf(sood));
                prepare.setString(4, cartNumGetter.getText());
                prepare.setString(5, numbercardDeposid.getText());
                prepare.setDate(6, Date.valueOf(now));
                prepare.setDate(7, Date.valueOf(now));
                prepare.setString(8, String.valueOf(deposidMonth.getValue()));
                prepare.setString(9, com1.getValue());
                prepare.setString(10, phonehome.getText());
                prepare.setString(11, accountPassword1.getText());
                prepare.setString(12, Arrays.toString(imageData));
                int rowsAffected = prepare.executeUpdate();


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Seccessfully");
                alert.showAndWait();
                pro.fileTransfer(numbercardDeposid.getText(),"", -Long.valueOf(deposidMoney.getText()),"میزان انتقال به سپرده");
                pro.openNewWindow("profile1.fxml", "Profile", actionEvent);
            }
        }
    }

    public void sendnationcardphoto(ActionEvent actionEvent) {uploadimage();}

    public void sendnationcardphoto1(ActionEvent actionEvent) {uploadimage();}
    public void uploadimage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("انتخاب یک عکس");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("تصاویر", "*.png", "*.jpeg", "*.gif"));

        Stage stage = (Stage) sendnationcardphoto.getScene().getWindow();
        selectedImageFile = fileChooser.showOpenDialog(stage);

        if (selectedImageFile != null) {
            issendphoto = false;
            try {
                FileInputStream fis = new FileInputStream(selectedImageFile);
                imageuser = new Image(fis);
                fis.close();
                System.out.println("عکس انتخاب شده: " + selectedImageFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                System.err.println("فایل پیدا نشد: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("خطا در خواندن فایل: " + e.getMessage());
            }
        } else {
            System.out.println("کاربر هیچ عکسی انتخاب نکرد.");
        }
    }
    public void applyMonthlyintrest() throws SQLException, IOException {
        String data = "SELECT * FROM blockedcard";
        connect = DataBase1.connectDB();
        assert connect != null;
        prepare = connect.prepareStatement(data);
        result = prepare.executeQuery();

        while (result.next()) {
            String id = result.getString("username");
            String numbercardto = result.getString("numbercardto");
            double amount = Double.parseDouble(result.getString("money"));
            double rate = Double.parseDouble(result.getString("sood"));
            LocalDate startdate = result.getDate("startdate").toLocalDate();
            LocalDate lastdate = result.getDate("enddate").toLocalDate();
            double duration = Double.parseDouble(result.getString("engeza"));

            LocalDate now = LocalDate.now();
            long monthsPassed = ChronoUnit.MONTHS.between(startdate, now);


            if (monthsPassed >= duration) {
                // غیرفعال کردن سپرده
                pro.updateCredit(numbercardto, (int) amount);
                String update = "DELETE FROM blockedcard WHERE username = ?";
                prepare = connect.prepareStatement(update);
                prepare.setString(1, id);
                prepare.executeUpdate();
                continue;
            }

            // آیا یک ماه از آخرین سود گذشته؟
            if (ChronoUnit.MONTHS.between(lastdate, now) >= 1) {
                double newAmount = amount * (1 + rate/100);
                String update = "UPDATE blockedcard SET money = ?, lastdate = ?";
                prepare = connect.prepareStatement(update);
                prepare.setDouble(1, newAmount);
                prepare.setDate(2, java.sql.Date.valueOf(now));
                prepare.executeUpdate();
                pro.fileTransfer(numbercardto,"", (long) +newAmount,"سود سپرده");
            }
        }

    }
    private void scheduleDailyInterestCheck() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // محاسبه مدت تا ساعت مورد نظر (مثلاً 2 صبح)
        LocalTime now = LocalTime.now();
        LocalTime targetTime = LocalTime.of(2, 0); // ساعت 2 صبح
        long initialDelay = ChronoUnit.MINUTES.between(now, targetTime);
        if (initialDelay < 0) initialDelay += 24 * 60; // اگر گذشته، فردا اجرا بشه

        scheduler.scheduleAtFixedRate(() -> {
            try {
                applyMonthlyintrest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, 24 * 60, TimeUnit.MINUTES); // هر 24 ساعت
    }

    public void toIntroduceMeno(ActionEvent event) {login.openNewWindow("main.fxml","Banking App",event);}

    public void toAccounts(ActionEvent event) {
        login.openNewWindow("hesab.fxml","حساب های شما",event);
    }

    public void toProfile(ActionEvent event) {
        login.openNewWindow("profile1.fxml","profile",event);
    }
}
