package com.example.bank;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.scene.control.Label;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class loginpage{

    @FXML
    private Hyperlink si_forgotpass;
    @FXML
    private TextField si_emailLogin;

    @FXML
    private Button si_loginbtn;

    @FXML
    public  Label txtName;
    @FXML
    public Label txtUsername;
    @FXML
    public Label txtPostcode;
    @FXML
    public Label txtAddress;
    @FXML
    public Label txtEmail;
    @FXML
    private Button si_viziblepass;
    @FXML
    private AnchorPane si_loginform;

    @FXML
    private Button si_help;
    @FXML
    private TextField si_emailforgot;


    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;
    @FXML
    public static String username;
    @FXML
    public static String resi_password;
    @FXML
    public static String resi_postcode;
    @FXML
    public static String resi_name;
    @FXML
    public static String resi_address;
    @FXML
    public static String resi_email;
    @FXML
    private Button side_createBtn;
    @FXML
    private AnchorPane side_changepass;

    @FXML
    private AnchorPane side_loginCodeEmail;
    @FXML
    private AnchorPane side_signUp;

    @FXML
    private AnchorPane side_form;

    @FXML
    private PasswordField su_password;
    @FXML
    private PasswordField su_cpassword;

    @FXML
    private TextField su_nationcode;

    @FXML
    private AnchorPane su_signUPForm;

    @FXML
    private Button su_signupBtn;

    @FXML
    private TextField su_username;

    @FXML
    private TextField si_newpassword;
    @FXML
    private TextField si_cnewpassword;
    @FXML
    private Button side_AlreadyHave;
    @FXML
    private Button si_sendcode;
    @FXML
    private Button si_entercode;

    @FXML
    private TextField su_emailsign1;

    @FXML
    private TextField su_emailsign11;

    @FXML
    private TextField si_textpassword;
    @FXML
    private TextField si_textpassword1;
    @FXML
    private TextField su_address;

    @FXML
    private TextField su_emailforgot;

    @FXML
    private TextField si_emailforgot11;
    @FXML
    private TextField su_number;

    @FXML
    private Connection connect;

    @FXML
    public PreparedStatement prepare;

    @FXML
    public ResultSet result;


    @FXML
    private AnchorPane side_forgotemail;

    @FXML
    private AnchorPane side_login;
    @FXML
    private TextField txt_cnewpassword;

    @FXML
    private TextField txt_coldpassword;

    @FXML
    private TextField txt_newpassword;

    @FXML
    private TextField txt_oldpassword;
    @FXML
    private Label label_resilt_change;
    @FXML
    private VBox vbox_changepass;

    @FXML
    private VBox vbox_profile;
    static boolean mm = false;
    static boolean loginID = false;
    static String nameCartSet = "";

    @FXML
    private VBox vbox_changeuser1;
    @FXML
    private TextField txt_changeuser1;

    @FXML
    private TextField txt_changenewuser1;

    @FXML
    private VBox vbox_change1;
    @FXML
    private TextField txt_change1;

    public Integer Credit = 0 ;
    public BigInteger cardNumber = BigInteger.valueOf(0);


    @FXML
    private Label cardnumberProfile;

    @FXML
    private Label CreditProfile;

    Random random = new Random();

    public static Integer IDCard;

    @FXML
    public void initialize() {
        System.out.println("FXML components initialized.");
    }
    @FXML
    public ComboBox<String> com1;
    ObservableList<String> list = FXCollections.observableArrayList("لپ تاپ ها","تبلت ها و لوازم جانبی", "لوازم جانبی");


    @FXML
    public void switchForm(javafx.event.ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == side_createBtn) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((event1) -> {
                side_AlreadyHave.setVisible(true);
                side_createBtn.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == side_AlreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((event1) -> {
                side_AlreadyHave.setVisible(false);
                side_createBtn.setVisible(true);
            });

            slider.play();
        }
    }

    private Alert alert;

    public void helplogin(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("if you see any errors, please email us " +
                " |@ElectroShop.gamil.com|");
        alert.showAndWait();
    }

    public void forgotpass(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

            slider.setNode(side_forgotemail);

            slider.setOnFinished((event1) -> {
                side_forgotemail.setVisible(true);
                side_login.setVisible(false);
            });

            slider.play();
    }

    public void backforgot(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(side_login);

        slider.setOnFinished((event1) -> {
            side_login.setVisible(true);
            side_forgotemail.setVisible(false);
        });

        slider.play();
    }

    public void backforgot1(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(side_login);

        slider.setOnFinished((event1) -> {
            side_forgotemail.setVisible(true);
            side_changepass.setVisible(false);
        });

        slider.play();
    }
    public void viziblepass(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(su_password);

        if (su_password.isVisible()) {
            String memory = su_password.getText();
            String memory1 = su_cpassword.getText();

            slider.setOnFinished((event1) -> {
                su_password.setVisible(false);
                su_cpassword.setVisible(false);
                si_textpassword.setVisible(true);
                si_textpassword1.setVisible(true);
            });

            slider.play();
            si_textpassword.setText(memory);
            si_textpassword1.setText(memory1);

        } else {
            String memory = si_textpassword.getText();
            String memory1 = su_cpassword.getText();

            slider.setNode(si_textpassword);

            slider.setOnFinished((event1) -> {
                si_textpassword.setVisible(false);
                si_textpassword1.setVisible(false);
                su_password.setVisible(true);
                su_cpassword.setVisible(true);
            });

            slider.play();
            si_textpassword.setText("");
            si_textpassword1.setText("");
            su_password.setText(memory);
            su_cpassword.setText(memory1);


        }
    }

    public static void sendEmail(String to, String code) {
        String from = "asadpour808@gmail.com"; // ایمیل فرستنده
        String password = "kqmy ljbs abpy tdbs"; // رمز عبور ایمیل

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your Verification Code");
            message.setText(" Hi Your Welcome \n  Here is your verification code:" + code);

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String verifyCode1;


    public void sendcode(ActionEvent event) {
        if(si_emailforgot.getText().length() < 3 ){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(" email  must be at least 3 characters ");
            alert.showAndWait();

        }
        else{
            String selectdata = "SELECT email FROM employee WHERE email = ?";

            connect = DataBase1.connectDB();

            try {

                prepare = connect.prepareStatement(selectdata);
                prepare.setString(1, si_emailforgot.getText());
                result = prepare.executeQuery();

                if(result.next()){
                    verifyCode1 = String.valueOf( (int)(Math.random() * 900000) + 100000);
                    sendEmail(si_emailforgot.getText(), verifyCode1);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Seccessfully");
                    alert.showAndWait();

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(si_sendcode);

                    slider.setOnFinished((event1) -> {
                        si_entercode.setVisible(true);
                        si_sendcode.setVisible(false);
                        si_emailforgot11.setVisible(true);
                    });

                    slider.play();

                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password");
                    alert.showAndWait();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }


    }

    @FXML
    public void entercode(ActionEvent event) {
        if(si_emailforgot.getText().length() < 3  || !verifyCode1.equals(si_emailforgot11.getText())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(" email  must be at least 3 characters ");
            alert.showAndWait();

        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Seccesfully");
            alert.showAndWait();

            TranslateTransition slider = new TranslateTransition();

            slider.setNode(si_entercode);

            slider.setOnFinished((event1) -> {
                si_sendcode.setVisible(true);
                si_entercode.setVisible(false);
                side_changepass.setVisible(true);
                side_forgotemail.setVisible(false);

            });

            slider.play();

        }

    }
    public void changepassword(ActionEvent event) {
        if(si_newpassword.getText().length() < 8 || !(si_newpassword.getText().matches(".*[A-Z].*"))|| !(si_newpassword.equals(si_cnewpassword))){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
        }
        else{
            String selectdata = "UPDATE employee SET password =? WHERE email = ?";

            connect = DataBase1.connectDB();

            try{
                prepare = connect.prepareStatement(selectdata);
                prepare.setString(1, si_newpassword.getText());
                prepare.setString(2, si_emailforgot.getText());
                int result = prepare.executeUpdate();



                if(result > 0){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Please back and login Account");
                    alert.showAndWait();

                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect  password");
                    alert.showAndWait();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }

    }


    public void loginBtn(ActionEvent event) {
        if(si_username.getText().length() < 3 ||  si_password.getText().length() < 8 || !(si_password.getText().matches(".*[A-Z].*"))){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(" username must be at least 3 characters and password must be at least 8 characters");
            alert.showAndWait();
        }
        else{ username=si_username.getText();
            String selectdata = "SELECT * FROM employee WHERE username= ? AND password= ?";

            connect = DataBase1.connectDB();
            if (this.connect == null) {
                System.out.println("Database connection failed!");
                return;
            }

            try{

                assert connect != null;
                prepare = connect.prepareStatement(selectdata);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());
                result = prepare.executeQuery();
                if(result.next()){
                    loginpage.mm = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bank/profile1.fxml"));
                    Scene scene = new Scene(loader.load(), 1535, 790);

                    // ایجاد و نمایش صفحه جدید
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("profile");
                    stage.show();

                    // بستن صفحه فعلی
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    loginID = true;

                    loginpage controller = loader.getController();
                    controller.txtName.setText(result.getString("name"));
                    controller.txtUsername.setText(result.getString("username"));
                    controller.txtEmail.setText(result.getString("email"));
                    controller.txtPostcode.setText(result.getString("postcode"));
                    controller.txtAddress.setText(result.getString("address"));
                    cardnumberProfile.setText(String.valueOf(cardNumber));
                    CreditProfile.setText(String.valueOf(cardNumber));
                    IDCard= Integer.valueOf(result.getString("IDCard"));
                    username=result.getString("username");resi_name=result.getString("name");
                    resi_email=result.getString("email");resi_postcode=result.getString("postcode");resi_address=result.getString("address");



                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/com/example/site/sabad.fxml"));
                    URL url = getClass().getResource("/com/example/site/sabad.fxml");
                    System.out.println("FXML URL: " + url);
                    Parent root = loader1.load();
                    /*sabad sabadController = loader1.getController();
                    System.out.println(sabadController);
                    System.out.println(loader1);
                    System.out.println(result.getString("name"));
                    sabadController.setLableNameCart(result.getString("name"));*/

                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password");
                    alert.showAndWait();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
            File Sefareshat=new File("Sefareshat.txt");
            String username1 = username; // مقدار مورد مقایسه
            String username2 = "."+username;
            File buyer = new File("buyer.txt");
            try {
                // اگر فایل buyer وجود ندارد، ایجاد شود
                if (!buyer.exists()) {
                    buyer.createNewFile();
                }

                BufferedReader reader = new BufferedReader(new FileReader(Sefareshat));
                BufferedWriter writer = new BufferedWriter(new FileWriter(buyer, true)); // افزودن به فایل

                String currentLine;
                boolean nextLineShouldBeWritten = false;

                while ((currentLine = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(currentLine, "/\n"); // تجزیه بر اساس فاصله، اسلش و کاما

                    boolean containsUsername = false;

                    // بررسی هر کلمه در خط
                    while (tokenizer.hasMoreTokens()) {
                        if (tokenizer.nextToken().equals(username1)) {
                            containsUsername = true;
                            break;
                        }
                    }// تنظیم وضعیت برای خط بعدی
                    nextLineShouldBeWritten = containsUsername;

                    // اگر خط قبل شامل username بود، این خط را به فایل انتقال بده
                    if (nextLineShouldBeWritten) {
                        writer.write(tokenizer.nextToken());
                        writer.newLine();
                        System.out.println("خط اضافه شد: " + currentLine);
                    }
                }

                reader.close();
                writer.close();

                System.out.println("انتقال اطلاعات انجام شد.");

            } catch (IOException e) {
                e.printStackTrace();
            }
            File price = new File("price.txt");
            try {
                // اگر فایل buyer وجود ندارد، ایجاد شود
                if (!price.exists()) {
                    price.createNewFile();
                }

                BufferedReader reader = new BufferedReader(new FileReader(Sefareshat));
                BufferedWriter writer = new BufferedWriter(new FileWriter(price, true)); // افزودن به فایل

                String currentLine1;
                boolean nextLineShouldBeWritten1 = false;

                while ((currentLine1 = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(currentLine1, "/\n"); // تجزیه بر اساس فاصله، اسلش و کاما

                    boolean containsUsername1 = false;

                    // بررسی هر کلمه در خط
                    while (tokenizer.hasMoreTokens()) {
                        if (tokenizer.nextToken().equals(username2)) {
                            containsUsername1 = true;
                            break;
                        }
                    }// تنظیم وضعیت برای خط بعدی
                    nextLineShouldBeWritten1 = containsUsername1;

                    // اگر خط قبل شامل username بود، این خط را به فایل انتقال بده
                    if (nextLineShouldBeWritten1) {
                        writer.write(tokenizer.nextToken());
                        writer.newLine();
                        System.out.println("خط اضافه شد: " + currentLine1);
                    }
                }

                reader.close();
                writer.close();

                System.out.println("انتقال اطلاعات انجام شد.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    String regData;


    @FXML
    public void SignupBtn(ActionEvent event) {
        if (su_address.getText().length() < 3 || su_number.getText().length() < 3 || su_username.getText().length() < 3  || su_password.getText().length() < 8
                || !(su_password.getText().equals(su_cpassword.getText()))  ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name and username and Address and email must be at least 3 characters and password must be at least 8 characters");
            alert.showAndWait();
        } else { username=su_username.getText();resi_name=su_number.getText();
            resi_email=su_emailsign1.getText();resi_postcode=su_nationcode.getText();resi_address=su_address.getText();
            connect = DataBase1.connectDB();
            if(connect==null){
                System.out.println("Connect Error");
            }

            try {

                String checkUserOrPassword = "SELECT * FROM employee WHERE username = ? OR password = ? OR email = ?";
                prepare = connect.prepareStatement(checkUserOrPassword);
                prepare.setString(1, su_username.getText());
                prepare.setString(2, su_password.getText());
                prepare.setString(3, su_emailsign1.getText());
                result = prepare.executeQuery();
                System.out.println(result);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("please choose new username or new password");
                    alert.showAndWait();

                }
                else {
                    verifyCode1 = String.valueOf((int) (Math.random() * 900000) + 100000);
                    sendEmail(su_emailsign1.getText(),verifyCode1);

                    side_signUp.setVisible(false);
                    side_loginCodeEmail.setVisible(true);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error22: " + e.getMessage());
            }

        }



    }
    public void entercodelogin(ActionEvent actionEvent) {
        if(!verifyCode1.equals(si_emailLogin.getText())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("code incorrect");
            alert.showAndWait();
        }
        else {
            try {

                regData = "INSERT INTO employee (name ,username ,password ,postcode ,address ,email,IDcard,credit,cvv2,engeza) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";
                connect = DataBase1.connectDB();

                /*LocalDate now = LocalDate.now(ZoneId.of("Asia/Tehran"));
                int year = now.getYear();
                int month = now.getMonthValue();
                int day = now.getDayOfMonth();

                // تبدیل میلادی به شمسی (تخمینی)
                int persianYear = year - ((month < 3) || (month == 3 && day < 21) ? 622 : 621);

                int persianMonth;
                if ((month == 3 && day >= 21) || month == 4 || (month == 5 && day <= 21)) persianMonth = 1;  // فروردین
                else if ((month == 5 && day >= 22) || month == 6 || (month == 7 && day <= 22)) persianMonth = 2;  // اردیبهشت
                else if ((month == 7 && day >= 23) || month == 8 || (month == 9 && day <= 22)) persianMonth = 3;  // خرداد
                else if ((month == 9 && day >= 23) || month == 10 || (month == 11 && day <= 22)) persianMonth = 4;  // تیر
                else if ((month == 11 && day >= 23) || month == 12 || (month == 1 && day <= 19)) persianMonth = 5;  // مرداد
                else if ((month == 1 && day >= 20) || month == 2 || (month == 3 && day <= 20)) persianMonth = 6;  // شهریور
                else if ((month == 3 && day >= 21) || month == 4 || (month == 5 && day <= 21)) persianMonth = 7;  // مهر
                else if ((month == 5 && day >= 22) || month == 6 || (month == 7 && day <= 22)) persianMonth = 8;  // آبان
                else if ((month == 7 && day >= 23) || month == 8 || (month == 9 && day <= 22)) persianMonth = 9;  // آذر
                else if ((month == 9 && day >= 23) || month == 10 || (month == 11 && day <= 22)) persianMonth = 10; // دی
                else if ((month == 11 && day >= 23) || month == 12 || (month == 1 && day <= 19)) persianMonth = 11; // بهمن
                else persianMonth = 12; // اسفند

                String yearPer = String.format("%02d", persianYear % 100);

                String monthPer = String.format("%02d", persianMonth);

                String yyMM = String.valueOf(yearPer + monthPer);

                BigInteger randomBigInt = new BigInteger(33, random).add(new BigInteger("1000000000"));

                String BigNumberString = "504412" + randomBigInt.toString() ;

                int cvv2 = random.nextInt(100,1000);*/

                assert connect != null;
                prepare = connect.prepareStatement(regData);
                prepare.setString(1, su_number.getText());
                prepare.setString(2, su_username.getText());
                prepare.setString(3, su_password.getText());
                prepare.setString(4, su_nationcode.getText());
                prepare.setString(5, su_address.getText());
                prepare.setString(6, su_emailsign1.getText());
                int rowsAffected = prepare.executeUpdate();
                System.out.println("Rows inserted: " + rowsAffected);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Seccessfully");
                alert.showAndWait();

                su_number.setText("");
                su_username.setText("");
                su_nationcode.setText("");
                su_address.setText("");
                su_password.setText("");
                su_cpassword.setText("");
                su_emailsign1.setText("");

                TranslateTransition slider = new TranslateTransition();

                slider.setNode(side_form);
                slider.setToX(0);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((event1) -> {
                    side_AlreadyHave.setVisible(false);
                    side_createBtn.setVisible(true);
                });
                slider.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //اگر منفی باشد کم می کند و اگر + باشد اضافه می کند
    public int updateCredit(int IDCard, int budget) throws SQLException {
        String selectCredit = "SELECT credit FROM employee WHERE IDCard = ?";
        String updateCredit = "UPDATE employee SET credit = credit + ? WHERE IDCard = ?";
        String updateCreditDeduct = "UPDATE employee SET credit = credit - ? WHERE IDCard = ?";  // برای کم کردن اعتبار


        connect = DataBase1.connectDB();

        prepare = connect.prepareStatement(selectCredit);
        prepare.setString(1, String.valueOf(IDCard));
        result = prepare.executeQuery();

        if (result.next()) {
            int currentCredit = result.getInt("credit");

            if (budget < 0) {
                if (Math.abs(budget) > currentCredit) {
                    return -1;
                }
                prepare = connect.prepareStatement(updateCreditDeduct);
            }
            else {
                prepare = connect.prepareStatement(updateCredit);
            }

            prepare = connect.prepareStatement(updateCredit);
            prepare.setString(1, String.valueOf(budget));
            prepare.setString(2, String.valueOf(IDCard));
            prepare.executeUpdate();

            return currentCredit - budget;
        }

        return -1;
    }


    public void mainpage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 1535, 790);

            // ایجاد و نمایش صفحه جدید
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("لنوو مارکت");
            stage.show();

            // بستن صفحه فعلی
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void shopcartprofile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shopcart.fxml"));
            Scene scene = new Scene(loader.load(), 1535, 790);

            // ایجاد و نمایش صفحه جدید
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("لنوو مارکت");
            stage.show();

            // بستن صفحه فعلی
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void why(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/site/why.fxml"));
            Scene scene = new Scene(loader.load(), 1535, 790);

            // ایجاد و نمایش صفحه جدید
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("چرا لنوو");
            stage.show();

            // بستن صفحه فعلی
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Changepass2(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(true);
            vbox_changeuser1.setVisible(false);
            vbox_change1.setVisible(false);
        });
        slider.play();
    }

    public void Changepass3(ActionEvent actionEvent) {
        if(txt_oldpassword.isVisible()){
            System.out.println(!(txt_oldpassword.equals(txt_coldpassword)));
            if(txt_oldpassword.getText().length() < 8 || !(txt_oldpassword.getText().matches(".*[A-Z].*")) || !(txt_oldpassword.getText().equals(txt_coldpassword.getText()))){
                label_resilt_change.setText("password must be at least 8 characters");
            }
            else{
                String selectdata = "SELECT * FROM employee WHERE password= ?";

                connect = DataBase1.connectDB();

                try{
                    prepare = connect.prepareStatement(selectdata);
                    prepare.setString(1, txt_oldpassword.getText());
                    result = prepare.executeQuery();

                    if(result.next()){
                        label_resilt_change.setText("");
                        label_resilt_change.setText("Seccessfully");

                        TranslateTransition slider = new TranslateTransition();

                        slider.setNode(si_sendcode);

                        slider.setOnFinished((event1) -> {
                            txt_oldpassword.setVisible(false);
                            txt_coldpassword.setVisible(false);
                            txt_newpassword.setVisible(true);
                            txt_cnewpassword.setVisible(true);
                        });

                        slider.play();

                    }
                    else{
                        label_resilt_change.setText("");
                        label_resilt_change.setText("Inccrect passwords");

                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        else if(txt_newpassword.isVisible()){
            if(txt_newpassword.getText().length() < 8 || !(txt_newpassword.getText().matches(".*[A-Z].*")) || !(txt_newpassword.getText().equals(txt_cnewpassword.getText()))){
                label_resilt_change.setText("password must be at least 8 characters");
            }
            else {
                String selectdata = "UPDATE employee SET password =? WHERE password = ?";

                connect = DataBase1.connectDB();

                try {
                    prepare = connect.prepareStatement(selectdata);
                    prepare.setString(1, txt_newpassword.getText());
                    prepare.setString(2, txt_oldpassword.getText());
                    int result = prepare.executeUpdate();


                    if (result > 0) {
                        label_resilt_change.setText("");
                        label_resilt_change.setText("Seccessfully");
                    } else {
                        label_resilt_change.setText("");
                        label_resilt_change.setText("Incorrect  password");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

    public void backLoginTo(ActionEvent actionEvent) {openNewWindow("main.fxml","lenoShop",actionEvent);}

    String infor = null;
    @FXML
    private Button buttonChange;

    public void changeUser1(ActionEvent actionEvent) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(false);
            vbox_changeuser1.setVisible(true);
            vbox_change1.setVisible(false);
        });
        slider.play();
        infor = "username";
        txt_oldusername1.setPromptText("نام کاربری قبلی");
        txt_newusername1.setPromptText("نام کاربری جدید");
        buttonChange.setText("تغییر نام کاربری");

    }

    public void changeName1(ActionEvent actionEvent) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(false);
            vbox_changeuser1.setVisible(true);
            vbox_change1.setVisible(false);
        });
        slider.play();
        infor = "name";
        txt_oldusername1.setPromptText("نام قبلی");
        txt_newusername1.setPromptText("نام  جدید");
        buttonChange.setText("تغییر نام ");

    }

    public void changePostcode1(ActionEvent actionEvent) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(false);
            vbox_changeuser1.setVisible(true);
            vbox_change1.setVisible(false);
        });
        slider.play();
        infor = "postcode";
        txt_oldusername1.setPromptText("پست کد قبلی");
        txt_newusername1.setPromptText("پست کد جدید");
        buttonChange.setText("تغییر پست کد ");

    }

    public void changeEmail1(ActionEvent actionEvent) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(false);
            vbox_changeuser1.setVisible(true);
            vbox_change1.setVisible(false);
        });
        slider.play();
        infor = "email";
        txt_oldusername1.setPromptText("ایمیل قبلی");
        txt_newusername1.setPromptText("ایمیل جدید");
        buttonChange.setText("ایمیل کاربری");

    }

    public void changeAddress1(ActionEvent actionEvent) {
        TranslateTransition slider = new TranslateTransition();

        slider.setNode(vbox_changepass);

        slider.setOnFinished((event1) -> {
            vbox_profile.setVisible(false);
            vbox_changepass.setVisible(false);
            vbox_changeuser1.setVisible(true);
            vbox_change1.setVisible(false);
        });
        slider.play();
        infor = "address";
        txt_oldusername1.setPromptText("ادرس قبلی");
        txt_newusername1.setPromptText("ادرس جدید");
        buttonChange.setText("ادرس کاربری");

    }

    public void backchangeToprofile(ActionEvent actionEvent) {openNewWindow("profile1.fxml","Profile",actionEvent);}

    @FXML
    private TextField txt_oldusername1;

    @FXML
    private TextField txt_newusername1;

    public void Changeuser1(ActionEvent actionEvent) {
        ChangeuserCls(infor ,txt_oldusername1 ,txt_newusername1 ,actionEvent);
    }

    public void ChangeuserCls(String name11,TextField old1 , TextField new1,ActionEvent actionEvent) {
        if(old1.isVisible()){
        if(old1.getText().length() < 3 ){
            label_resilt_change.setText( name11+" must be at least 3 characters");
        }
        else{username=name11;
            String selectdata = "SELECT * FROM employee WHERE "+name11+"= ?";

            connect = DataBase1.connectDB();

            try{
                prepare = connect.prepareStatement(selectdata);
                prepare.setString(1, old1.getText());
                result = prepare.executeQuery();

                if(result.next()){
                    label_resilt_change.setText("");
                    label_resilt_change.setText("Seccessfully");

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(si_sendcode);

                    slider.setOnFinished((event1) -> {
                        old1.setVisible(false);
                        new1.setVisible(true);
                    });

                    slider.play();

                }
                else{
                    label_resilt_change.setText("");
                    label_resilt_change.setText("Inccrect username");

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    else if(new1.isVisible()){
        if(new1.getText().length() < 3 ){
            label_resilt_change.setText("username must be at least 3 characters");
        }
        else {
            String selectdata = "UPDATE employee SET "+name11+"=? WHERE "+name11+"= ?";

            connect = DataBase1.connectDB();

            try {
                prepare = connect.prepareStatement(selectdata);
                prepare.setString(1, new1.getText());
                prepare.setString(2, old1.getText());
                int result = prepare.executeUpdate();
                openNewWindow("profile1.fxml","Profile",actionEvent);


                if (result > 0) {
                    label_resilt_change.setText("");
                    label_resilt_change.setText("Seccessfully");
                } else {
                    label_resilt_change.setText("");
                    label_resilt_change.setText("Incorrect  username");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    }

    public void sabadpageProfile(ActionEvent actionEvent) {
        openNewWindow("main.fxml","Cart",actionEvent);
    }

    public void logOutProfile(ActionEvent actionEvent) {
        openNewWindow("loginpage.fxml","Loginpage",actionEvent);
        loginID = false;
        File Sefareshat=new File("Sefareshat.txt");
        File buyer=new File("buyer.txt");
        File price = new File("price.txt");
        String filePath2="Sefareshat.txt";
        String filePath = "buyer.txt";
        String filePath1 = "price.txt";
        try {
            // خواندن فایل
            File file2=new File(filePath2);
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            // پردازش هر خط از فایل
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "/\n"); // جداسازی کلمات با فاصله
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    System.out.println(word);
                    content.append("/").append(username).append("/").append(word).append("\n");
                }
            }
            reader.close();
            // بازنویسی فایل
            BufferedWriter writer = new BufferedWriter(new FileWriter(file2,true));
            writer.write(content.toString().trim()); // نوشتن محتوای جدید
            writer.close();

            System.out.println("فایل با موفقیت بازنویسی شد.");
        } catch (FileNotFoundException e) {
            System.out.println("خطا در پردازش فایل: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            // خواندن فایل
            File file2=new File(filePath2);
            File file1 = new File(filePath1);
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            StringBuilder content = new StringBuilder();
            String line1;

            // پردازش هر خط از فایل
            while ((line1 = reader.readLine()) != null) {
                StringTokenizer tokenizer1 = new StringTokenizer(line1, "/\n"); // جداسازی کلمات با فاصله
                while (tokenizer1.hasMoreTokens()) {
                    String word = tokenizer1.nextToken();
                    content.append("/.").append(username).append("/").append(word).append("\n");

                }
            }
            reader.close();
            // بازنویسی فایل
            BufferedWriter writer = new BufferedWriter(new FileWriter(file2,true));
            writer.write(content.toString().trim()); // نوشتن محتوای جدید
            writer.close();

            System.out.println("فایل با موفقیت بازنویسی شد.");
        } catch (FileNotFoundException e) {
            System.out.println("خطا در پردازش فایل: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(buyer.delete()&& price.delete()){
            System.out.println("deleted the File:"+buyer.getName());
            System.out.println("deleted the File:"+price.getName());
        }else{
            System.out.println("Failed to delete the File");
        }
    }

    public void backToSignup(ActionEvent actionEvent) {
        side_signUp.setVisible(true);
        side_loginCodeEmail.setVisible(false);
    }


    public class why1 implements Initializable {
        @FXML
        public ComboBox<String> com1;
        ObservableList<String> list = FXCollections.observableArrayList("لپ تاپ ها","تبلت ها", "لوازم جانبی");

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            com1.setItems(list);
            com1.setOnAction(this::handleComboBoxAction);
        }
        public void handleComboBoxAction(ActionEvent event) {
            String selectedOption = com1.getValue();
            if("لپ تاپ ها".equals(selectedOption)){
                openNewWindow("lap.fxml","لپتاپ ها",event);
            }
            if ("لوازم جانبی".equals(selectedOption)) {
                openNewWindow("janebi.fxml", "لوازم جانبی", event);
            }
            if ("تبلت ها و لوازم جانبی".equals(selectedOption)) {
                openNewWindow("tablet.fxml", "تبلت ها و لوازم جانبی", event);
            }
        }
        public void openNewWindow(String fxmlFile, String title, ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Scene scene = new Scene(loader.load(), 1535, 790);

                // ایجاد و نمایش صفحه جدید
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle(title);
                stage.show();

                // بستن صفحه فعلی
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        




    }


}