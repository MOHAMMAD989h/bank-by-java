package com.example.bank;

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
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.sql.*;
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
    private AnchorPane openAccountProfile;

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

    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    ResultSet rs;
    ResultSet rs1;

    String infor = null;

    public static String lastScene = "main.fxml";
    public static String lastScenetitle = "Home";

    loginpage login = new loginpage();

    public void initialize() {
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

                        //slider.setNode(si_sendcode);

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
    public long transferMoney(String Numbercardfrom,String Numbercardto, long budget) throws SQLException {
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

        long currentCredit = Integer.parseInt(result.getString("money"));

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
