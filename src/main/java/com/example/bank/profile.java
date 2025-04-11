package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import static com.example.bank.loginpage.username;


public class profile {


    @FXML
    private Button buttonChange;

    @FXML
    private Label label_resilt_change;

    @FXML
    private Label label_resilt_change1;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label txtAddress;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtName;

    @FXML
    private Label txtNationcode;

    @FXML
    private Label txtUsername;

    @FXML
    private TextField txt_cnewpassword;

    @FXML
    private TextField txt_coldpassword;

    @FXML
    private TextField txt_newpassword;

    @FXML
    private TextField txt_newusername1;

    @FXML
    private TextField txt_oldpassword;

    @FXML
    private TextField txt_oldusername1;

    @FXML
    private VBox vbox_changepass;

    @FXML
    private VBox vbox_changeuser1;

    @FXML
    private VBox vbox_profile;

    @FXML
    private VBox vboxshowcard;
    @FXML
    private VBox vbox_change1;
    @FXML
    private Label txtNumberphone;
    @FXML
    private Button welcomeImage;

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private MediaView media1;
    @FXML
    private MediaView media2;

    private MediaPlayer mediaPlayer;
    Random random = new Random();



    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    ResultSet rs;
    ResultSet rs1;

    Timeline timeline;

    String infor = null;

    File file1 = new File("filetransfer.txt");
    FileWriter fileWriter1 = null;

    public static String lastScene = "main.fxml";
    public static String lastScenetitle = "Home";

    loginpage login = new loginpage();

    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            welcomeImage.setVisible(false);
        }));
        timeline.setCycleCount(1);
        timeline.play();

        hessabView obj = new hessabView();
        obj.applyHoverEffect(btn1);
        obj.applyHoverEffect(btn2);
        obj.applyHoverEffect(btn3);
        obj.applyHoverEffect(btn4);
        obj.applyHoverEffect(btn5);
        obj.applyHoverEffect(btn6);
        obj.applyHoverEffect(btn7);

        //ویدیو
        String videoPath1 = getClass().getResource("/budget_15579057.mp4").toExternalForm();
        playMedia(videoPath1,media1,80,80);
        String videoPath2 = getClass().getResource("/login_18986466.mp4").toString();
        playMedia(videoPath2,media2,80,80);




        try {
            connect = DataBase1.connectDB();
            String selectdata = "SELECT * FROM employee WHERE username = ?";

            prepare = connect.prepareStatement(selectdata);
            prepare.setString(1, username);
            rs = prepare.executeQuery();
            String numberq = "";

            while (rs.next()) {
                numberq = rs.getString("name");
                txtName.setText(numberq);
                txtEmail.setText(rs.getString("email"));
                txtAddress.setText(rs.getString("address"));
                txtUsername.setText(rs.getString("username"));
                txtNationcode.setText(rs.getString("nationcode"));
                txtNumberphone.setText(rs.getString("numberphone"));
                byte[] imagedata = rs.getBytes("imageData");
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imagedata);
                Image image = new Image(inputStream);
                profileImage.setImage(image);
            }
        }
        catch (Exception e) {e.printStackTrace();}

    }
    public void playMedia(String videoPath,MediaView media1,int W,int H) {
        try {
            Media media = new Media(videoPath);
            MediaPlayer playmedia2 = new MediaPlayer(media);

            // اتصال MediaPlayer به MediaView
            media1.setMediaPlayer(playmedia2);

            // فقط وقتی آماده شد، پخش کن
            playmedia2.setOnReady(() -> {
                playmedia2.play();
                System.out.println("playmedia2 played");
            });
            playmedia2.setOnError(() -> {
                System.out.println("Error in playmedia: " + playmedia2.getError());
                media1.setMediaPlayer(null); // unlink
                playmedia2.dispose(); // cleanup
                try{
                    playMedia(videoPath,media1,W,H);
                }
                catch (Exception e) {
                    System.out.println("Error in playmedia: " + e.getMessage());
                }
            });

            media.setOnError(() -> {
                System.out.println("Error in media1: " + media.getError());
                media1.setMediaPlayer(null); // unlink
                playmedia2.dispose(); // cleanup
            });

            // تکرار خودکار ویدیو پس از پایان
            playmedia2.setOnEndOfMedia(() -> {
                playmedia2.seek(javafx.util.Duration.ZERO);
                playmedia2.play();
            });

            // تنظیم سایز MediaView
            media1.setFitWidth(W);
            media1.setFitHeight(H);
            media1.setPreserveRatio(false);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void mainpage(ActionEvent actionEvent) {
        openNewWindow("main.fxml","Home",actionEvent);
    }
    public void openAccount(ActionEvent actionEvent) {
        openNewWindow("account.fxml","openAurusecount",actionEvent);
        lastScene = "profile1.fxml";
        lastScenetitle = "profile";
    }

    public void logOutProfile(ActionEvent actionEvent) {
        openNewWindow("loginpage.fxml","Loginpage",actionEvent);
        loginpage.loginID = false;
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
        infor = "numberphone";
        txt_oldusername1.setPromptText("پست کد قبلی");
        txt_newusername1.setPromptText("پست کد جدید");
        buttonChange.setText("تغییر پست کد ");
    }

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

    public void Changepass2(ActionEvent actionEvent) {
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

    public void Changeuser1(ActionEvent actionEvent) {ChangeuserCls(infor, txt_oldusername1, txt_newusername1, actionEvent);}

    public void backchangeToprofile(ActionEvent actionEvent) {openNewWindow("profile1.fxml","Profile",actionEvent);}

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

                        slider.setNode(txt_oldpassword);

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

                        TranslateTransition slider = new TranslateTransition();

                        slider.setOnFinished(event1 -> {
                            openNewWindow("main.fxml","Home",actionEvent);
                        });

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
    public void ChangeuserCls(String name11,TextField old1 , TextField new1,ActionEvent actionEvent) {
        if(old1.isVisible()){
            if(old1.getText().length() < 3 ){
                label_resilt_change.setText( name11+" must be at least 3 characters");
            }
            else{
                username =name11;
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
    public int updateCredit(String Numbercard, int budget) throws SQLException {
        if(budget == 0){
            System.out.println("مقدار صفر دارد");
            return -1;
        }
        String selectCredit = "SELECT * FROM cards WHERE numbercard  = ?";
        String updateCredit = "UPDATE cards SET money = money + ?,credit = credit + ? WHERE numbercard  = ?";
        String updateCreditDeduct = "UPDATE cards SET money = money - ?,credit = credit + ? WHERE numbercard  = ?";


        connect = DataBase1.connectDB();

        assert connect != null;
        prepare = connect.prepareStatement(selectCredit);
        prepare.setString(1, Numbercard);
        result = prepare.executeQuery();

        if (result.next()) {
            int currentCredit = Integer.parseInt(result.getString("money"));

            if (budget < 0) {
                if (Math.abs(budget) > currentCredit) {
                    return -1;
                }
                prepare = connect.prepareStatement(updateCreditDeduct);
            } else {
                prepare = connect.prepareStatement(updateCredit);
            }

            prepare.setInt(1, Math.abs(budget));
            prepare.setInt(2, Math.abs(budget));
            prepare.setString(3, Numbercard);
            prepare.executeUpdate();

            return currentCredit-budget;
        }

        return -1;
    }
    public int updateCreditblocked(String Numbercard, int budget) throws SQLException {
        if(budget == 0){
            System.out.println("مقدار صفر دارد");
            return -1;
        }
        String selectCredit = "SELECT * FROM blockedcard WHERE numbercard  = ?";
        String updateCredit = "UPDATE blockedcard SET money = money + ? WHERE numbercard  = ?";
        String updateCreditDeduct = "UPDATE blockedcard SET money = money - ? WHERE numbercard  = ?";


        connect = DataBase1.connectDB();

        assert connect != null;
        prepare = connect.prepareStatement(selectCredit);
        prepare.setString(1, Numbercard);
        result = prepare.executeQuery();

        if (result.next()) {
            int currentCredit = Integer.parseInt(result.getString("money"));

            if (budget < 0) {
                if (Math.abs(budget) > currentCredit) {
                    return -1;
                }
                prepare = connect.prepareStatement(updateCreditDeduct);
            } else {
                prepare = connect.prepareStatement(updateCredit);
            }

            prepare.setInt(1, Math.abs(budget));
            prepare.setString(2, Numbercard);
            prepare.executeUpdate();

            return currentCredit-budget;
        }

        return -1;
    }
    public long transferMoney(String Numbercardfrom,String Numbercardto, long budget) throws SQLException {
        if(budget == 0){
            System.out.println("مقدار صفر دارد");
            return -1;
        }
        String selectCredit = "SELECT money FROM cards WHERE numbercard = ?";
        String updateCreditAdd = "UPDATE cards SET money = money + ?, credit = credit + ? WHERE numbercard = ?";
        String updateCreditDeduct = "UPDATE cards SET money = money - ?,credit = credit + ? WHERE numbercard = ?";

        connect = DataBase1.connectDB();

        assert connect != null;

        prepare = connect.prepareStatement(selectCredit);
        prepare.setString(1, Numbercardto);
        result = prepare.executeQuery();

        if (!result.next()) {
            System.out.println("شماره کارت مقصد نامعتبر است."); // شماره کارت مقصد نامعتبر است
            return -3;
        }

        prepare = connect.prepareStatement(selectCredit);
        prepare.setString(1, Numbercardfrom);
        result = prepare.executeQuery();

        if (!result.next()) {
            System.out.println("شماره کارت مبدا نامعتبر است."); // شماره کارت مبدا نامعتبر است
            return -1;
        }

        long currentCredit = Long.parseLong(result.getString("money"));

        if (Math.abs(budget) > currentCredit) {
            System.out.println("موجودی کارت مبدا کافی نیست."); // موجودی کارت مبدا کافی نیست
            return -2;
        }

        prepare = connect.prepareStatement(updateCreditDeduct);
        prepare.setString(1, String.valueOf(Math.abs(budget)));
        prepare.setString(2, String.valueOf(Math.abs(budget)));
        prepare.setString(3, Numbercardfrom);
        prepare.executeUpdate();

        prepare = connect.prepareStatement(updateCreditAdd);
        prepare.setString(1, String.valueOf(Math.abs(budget)));
        prepare.setString(2, String.valueOf(Math.abs(budget)));
        prepare.setString(3, Numbercardto);
        prepare.executeUpdate();

        return  (currentCredit - Math.abs(budget));
    }
    public void fileTransfer(String numbercard1,String numbercard2,Long budget,String name) throws IOException {
        Long rand = random.nextLong(100000000,999999999);
        LocalDate now1 = LocalDate.now();

        try {
            fileWriter1 = new FileWriter(file1, true);
            fileWriter1.write(numbercard1 + ',' + name+',' + numbercard2 + ',' + budget + ',' + rand + "," + now1);
            fileWriter1.close();
        }
        catch (IOException e) {e.printStackTrace();}
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

    public void openmyaccount(ActionEvent actionEvent) {openNewWindow("hesab.fxml","myaccount",actionEvent);
    }
}
