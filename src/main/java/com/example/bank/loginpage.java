package com.example.bank;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    public void initialize() throws SQLException {
        hessabView obj = new hessabView();
        obj.applyHoverEffect(side_createBtn);
        obj.applyHoverEffect(side_AlreadyHave);
    }

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
            message.setText(code);

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
        else{DataBase1 Select=new DataBase1();
            try {
                if(Select.isdataimportvalid(si_emailforgot.getText(),"employee","email")){
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
        else{DataBase1 UpdateTheInserted=new DataBase1();
            try (FileInputStream fileIn = new FileInputStream("userData.bin");
                  ObjectInputStream in = new ObjectInputStream(fileIn)) {

            UpdateTheInserted = (DataBase1) in.readObject();

            System.out.println("Object loaded successfully!");
            // حالا می‌تونی با loadedObject کار کنی:
            // مثلاً:
            // System.out.println(loadedObject.getUserName());

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try{UpdateTheInserted.DataBase11(-2,si_newpassword.getText());
                if(UpdateTheInserted.getIsSuccessFul()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Please back and login Account");
                    alert.showAndWait();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect  password");
                    alert.showAndWait();
                }UpdateTheInserted.DataBase11(-3,si_emailforgot.getText());
                if(UpdateTheInserted.getIsSuccessFul()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Please back and login Account");
                    alert.showAndWait();

                }else{
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
            try{DataBase1 Select=new DataBase1();
                try (FileInputStream fileIn = new FileInputStream("userData.bin");
                     ObjectInputStream in = new ObjectInputStream(fileIn)) {

                Select = (DataBase1) in.readObject();

                System.out.println("Object loaded successfully!");
                // حالا می‌تونی با loadedObject کار کنی:
                // مثلاً:
                // System.out.println(loadedObject.getUserName());


                } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                }
                if(Select.isdataimportvalid(si_username.getText(),"employee","username") || Select.isdataimportvalid( si_password.getText(),"employee","password")){
                    loginpage.mm = true;
                    openNewWindow("profile1.fxml","profile",event);
                    profile.lastScene = "loginpage.fxml";
                    profile.lastScenetitle = "profile";
                    username = si_username.getText();
                    loginID = true;

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
        } else {
            connect = DataBase1.connectDB();
            if(connect==null){
                System.out.println("Connect Error");
            }
            DataBase1 Select=new DataBase1();
            try {System.out.println(Select.finddataimport(su_username.getText(),"employee","username","username")+Select.finddataimport(su_emailsign1.getText(),"employee","username","emal"));

                if (Select.isdataimportvalid(su_username.getText(),"employee","username")&&Select.isdataimportvalid(su_password.getText(),"employee","password")&&Select.isdataimportvalid(su_emailsign1.getText(),"employee","email")) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("please choose new username or new password");
                    alert.showAndWait();
                }
                else {
                    su_signupBtn.setDisable(true);

                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            verifyCode1 = String.valueOf((int) (Math.random() * 900000) + 100000);
                            sendEmail(su_emailsign1.getText(),verifyCode1);

                            return null;
                        }

                        @Override
                        protected void succeeded() {
                            super.succeeded();
                            System.out.println("کار با موفقیت به پایان رسید و صفحه جدید باز شد!");
                        }

                        @Override
                        protected void failed() {
                            super.failed();
                            System.err.println("خطا در انجام کار یا باز کردن صفحه جدید!");
                            getException().printStackTrace();
                        }
                    };

                    Thread thread = new Thread(task);
                    thread.setDaemon(true);
                    thread.start();

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
                DataBase1 Insert=new DataBase1(su_name.getText(),su_username.getText(),su_password.getText(),su_emailsign1.getText(),su_number.getText(),su_nationcode.getText(),su_address.getText(),selectedImageFile);
                try (FileOutputStream fileOut = new FileOutputStream("userData.dat");
                     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(Insert);
                    System.out.println("Object saved successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void backLoginTo(ActionEvent actionEvent) {openNewWindow("main.fxml","Home",actionEvent);}

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