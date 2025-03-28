package com.example.bank;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;
import javafx.scene.control.Label;
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
    private TextField su_name;

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
    private TextField si_emailforgot;


    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;
    @FXML
    public static String username;
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
    private TextField si_textpassword;
    @FXML
    private TextField si_textpassword1;
    @FXML
    private TextField su_address;

    @FXML
    private TextField si_emailforgot11;
    @FXML
    private TextField su_number;

    @FXML
    private Connection connect;

    @FXML
    public PreparedStatement prepare;

    public ResultSet result;

    @FXML
    private AnchorPane side_forgotemail;

    @FXML
    private AnchorPane side_login;
    @FXML
    private Button uploadButton;

    static boolean mm = false;
    static boolean loginID = false;


    public Integer Credit = 0 ;
    public BigInteger cardNumber = BigInteger.valueOf(0);

    Random random = new Random();

    public static BigInteger IDCard;

    private File selectedImageFile;

    private boolean issendphoto = true;

    @FXML
    private ImageView profileImage;

    Image imageuser;

    @FXML
    public void initialize() throws SQLException {}

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
                " |@AuruseBank.gamil.com|");
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
                    controller.txtName.setText(result.getString("numberphone"));
                    controller.txtUsername.setText(result.getString("username"));
                    controller.txtEmail.setText(result.getString("email"));
                    controller.txtPostcode.setText(result.getString("nationcode"));
                    controller.txtAddress.setText(result.getString("address"));
                    Blob imageblob = result.getBlob("imageData");
                    System.out.println(imageblob);
                    InputStream binaryStream = imageblob.getBinaryStream();
                    System.out.println(binaryStream);
                    Image image = new Image(binaryStream);
                    System.out.println(image);
                    profileImage.setImage(image);
                    username=result.getString("username");resi_name=result.getString("name");
                    resi_email=result.getString("email");resi_postcode=result.getString("postcode");resi_address=result.getString("address");



                    /*FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/com/example/site/sabad.fxml"));
                    URL url = getClass().getResource("/com/example/site/sabad.fxml");
                    System.out.println("FXML URL: " + url);
                    Parent root = loader1.load();*/
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
        }
    }
    String regData;


    @FXML
    public void SignupBtn(ActionEvent event) {
        if (su_address.getText().length() < 3 || !su_number.getText().matches("[0-9]{11}") || su_username.getText().length() < 3  || su_password.getText().length() < 8 || !su_nationcode.getText().matches("[0-9]{10}")
            || !su_emailsign1.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")    || !(su_password.getText().equals(su_cpassword.getText())) || issendphoto ) {
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

                regData = "INSERT INTO employee (name,username ,password,email,numberphone,nationcode,address,imageData) " +
                        "VALUES(?,?,?,?,?,?,?,?)";
                connect = DataBase1.connectDB();

                byte[] imageData = Files.readAllBytes(selectedImageFile.toPath());

                assert connect != null;
                prepare = connect.prepareStatement(regData);
                prepare.setString(1, su_name.getText());
                prepare.setString(2, su_username.getText());
                prepare.setString(3, su_password.getText());
                prepare.setString(4, su_emailsign1.getText());
                prepare.setString(5, su_number.getText());
                prepare.setString(6, su_nationcode.getText());
                prepare.setString(7, su_address.getText());
                prepare.setString(8, Arrays.toString(imageData));
                int rowsAffected = prepare.executeUpdate();

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

    public void backToSignup(ActionEvent actionEvent) {
        side_signUp.setVisible(true);
        side_loginCodeEmail.setVisible(false);
    }

    public void uploadButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("انتخاب یک عکس");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("تصاویر", "*.png", "*.jpeg", "*.gif"));

        Stage stage = (Stage) uploadButton.getScene().getWindow();
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

    public class why1 implements Initializable {
        @FXML
        public ComboBox<String> com1;
        ObservableList<String> list = FXCollections.observableArrayList("لپ تاپ ها", "تبلت ها", "لوازم جانبی");

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            com1.setItems(list);
            com1.setOnAction(this::handleComboBoxAction);
        }

        public void handleComboBoxAction(ActionEvent event) {
            String selectedOption = com1.getValue();
            if ("لپ تاپ ها".equals(selectedOption)) {
                openNewWindow("lap.fxml", "لپتاپ ها", event);
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