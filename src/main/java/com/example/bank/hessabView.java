package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.example.bank.loginpage.username;

public class hessabView {
    loginpage login = new loginpage();
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
    private Button btn8;
    @FXML
    private ImageView imge;
    @FXML
    private Button change;
    String url;
    @FXML
    private Label name;
    @FXML
    private Label mojodilabel;
    @FXML
    private Label mojodi;
    @FXML
    private Label cvv;
    @FXML
    private Label engeza;
    @FXML
    private Label cart;
    @FXML
    private Label userlabel;

    @FXML
    private Label codelabel;

    @FXML
    private Label numlabel;

    @FXML
    public void initialize() {
        applyHoverEffect(btn1);
        applyHoverEffect(btn2);
        applyHoverEffect(btn3);
        applyHoverEffect(btn4);
        applyHoverEffect(btn5);
        applyHoverEffect(btn6);
        applyHoverEffect(btn7);
        applyHoverEffect(btn8);
    }

    void applyHoverEffect(Button button) {
        Timeline hoverIn = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(button.translateYProperty(), -5),
                        new KeyValue(button.scaleXProperty(), 1.1),
                        new KeyValue(button.scaleYProperty(), 1.1))
        );

        Timeline hoverOut = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(button.translateYProperty(), 0),
                        new KeyValue(button.scaleXProperty(), 1),
                        new KeyValue(button.scaleYProperty(), 1))
        );
        //وقتی ماوس میره روش متد hoverIn اجرا میشه و وقتی خارج می شود متد hoverOut اجرا می شود
        button.setOnMouseEntered(e -> hoverIn.play());
        button.setOnMouseExited(e -> hoverOut.play());
    }

    @FXML
    private void yellow() {
        url = "/Images/photo_2025-03-26_03-03-08.png";
        String imagePath = "/Images/photo_2025-03-26_03-03-08.png";
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        imge.setImage(img);
        change.setVisible(true);
        name.setVisible(true);
        mojodilabel.setVisible(true);
        mojodi.setVisible(true);
        cart.setVisible(false);
        engeza.setVisible(false);
        cvv.setVisible(false);
    }

    @FXML
    private void pink() {
        url = "/Images/4242.png";
        String imagePath = "/Images/4242.png";
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        imge.setImage(img);
        change.setVisible(true);
        name.setVisible(true);
        mojodilabel.setVisible(true);
        mojodi.setVisible(true);
        cart.setVisible(false);
        engeza.setVisible(false);
        cvv.setVisible(false);
    }

    @FXML
    private void gray() {
        url = "/Images/4545.png";
        String imagePath = "/Images/4545.png";
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        imge.setImage(img);
        change.setVisible(true);
        name.setVisible(true);
        mojodilabel.setVisible(true);
        mojodi.setVisible(true);
        cart.setVisible(false);
        engeza.setVisible(false);
        cvv.setVisible(false);
    }

    @FXML
    private void green() {
        url = "/Images/755.png";
        String imagePath = "/Images/755.png";
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        imge.setImage(img);
        change.setVisible(true);
        name.setVisible(true);
        mojodilabel.setVisible(true);
        mojodi.setVisible(true);
        cart.setVisible(false);
        engeza.setVisible(false);
        cvv.setVisible(false);
    }

    @FXML
    private void changee() {
        if (url == "/Images/photo_2025-03-26_03-03-08.png") {
            String imagePath = "/Images/545.png";
            url="/Images/545.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(false);
            name.setVisible(false);
            mojodilabel.setVisible(false);
            mojodi.setVisible(false);
            cart.setVisible(true);
            engeza.setVisible(true);
            cvv.setVisible(true);
            change.setVisible(true);
        }
        else if (url == "/Images/4242.png") {
            String imagePath = "/Images/6353.png";
            url="/Images/6353.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(false);
            name.setVisible(false);
            mojodilabel.setVisible(false);
            mojodi.setVisible(false);
            cart.setVisible(true);
            engeza.setVisible(true);
            cvv.setVisible(true);
            change.setVisible(true);
        }
        else if (url == "/Images/4545.png") {
            String imagePath = "/Images/45745.png";
            url="/Images/45745.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(false);
            name.setVisible(false);
            mojodilabel.setVisible(false);
            mojodi.setVisible(false);
            cart.setVisible(true);
            engeza.setVisible(true);
            cvv.setVisible(true);
            change.setVisible(true);
        }
        else if (url == "/Images/755.png") {
            String imagePath = "/Images/4575.png";
            url="/Images/4575.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(false);
            name.setVisible(false);
            mojodilabel.setVisible(false);
            mojodi.setVisible(false);
            cart.setVisible(true);
            engeza.setVisible(true);
            cvv.setVisible(true);
            change.setVisible(true);
        }
        else if (url == "/Images/545.png") {
            String imagePath = "/Images/photo_2025-03-26_03-03-08.png";
            url="/Images/photo_2025-03-26_03-03-08.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(true);
            name.setVisible(true);
            mojodilabel.setVisible(true);
            mojodi.setVisible(true);
            cart.setVisible(false);
            engeza.setVisible(false);
            cvv.setVisible(false);
            change.setVisible(true);

        }
        else if (url == "/Images/6353.png") {
            String imagePath = "/Images/4242.png";
            url="/Images/4242.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(true);
            name.setVisible(true);
            mojodilabel.setVisible(true);
            mojodi.setVisible(true);
            cart.setVisible(false);
            engeza.setVisible(false);
            cvv.setVisible(false);
            change.setVisible(true);
        }
        else if (url == "/Images/45745.png") {
            String imagePath = "/Images/4545.png";
            url="/Images/4545.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(true);
            name.setVisible(true);
            mojodilabel.setVisible(true);
            mojodi.setVisible(true);
            cart.setVisible(false);
            engeza.setVisible(false);
            cvv.setVisible(false);
            change.setVisible(true);
        }
        else if (url == "/Images/4575.png") {
            String imagePath = "/Images/755.png";
            url="/Images/755.png";
            Image img = new Image(getClass().getResource(imagePath).toExternalForm());
            imge.setImage(img);
            change.setVisible(true);
            name.setVisible(true);
            mojodilabel.setVisible(true);
            mojodi.setVisible(true);
            cart.setVisible(false);
            engeza.setVisible(false);
            cvv.setVisible(false);
            change.setVisible(true);
        }
    }
    @FXML
    private void handleServiceOption1(ActionEvent event) {
        Controller manager = new Controller();
        manager.openNewWindow("entegal.fxml","انتقال وجه",event);
    }
    @FXML
    private void handleServiceOption3(ActionEvent actionEvent) {
        Controller manager = new Controller();
        manager.openNewWindow("charge.fxml","charge",actionEvent);
    }
    @FXML
    private void handleServiceOption2(ActionEvent actionEvent) {
        Controller manager = new Controller();
        manager.openNewWindow("gabz.fxml","gabz",actionEvent);}
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    ResultSet rs;

    String infor = null;
    private List<productVam> products = new ArrayList<>();
    public void setMethod(String method) {
        try {
            connect = DataBase1.connectDB();
            String data = "SELECT * FROM cards WHERE username = ?";

            String selectdata = "SELECT * FROM employee WHERE username = ?";

            prepare = connect.prepareStatement(selectdata);
            prepare.setString(1, username);
            rs = prepare.executeQuery();
            String numberq = "";
            while (rs.next()) {
                numberq = rs.getString("name");
                userlabel.setText(numberq);
                codelabel.setText(rs.getString("nationcode"));
                numlabel.setText(rs.getString("numberphone"));
            }

            prepare = connect.prepareStatement(data);
            prepare.setString(1,username);
            result = prepare.executeQuery();
            products.clear();
            while (result.next()) {
                String number = result.getString("numbercard");
                String time = result.getString("engeza");
                String cvv2 = result.getString("cvv2");
                String bankname = result.getString("bankname");
                String money = result.getString("money");
                if(number.trim().equals(method)){
                    cart.setText(number);
                    name.setText(numberq);
                    cvv.setText(cvv2);
                    engeza.setText(time);
                    mojodi.setText(money);
                }
            }
        }
        catch (Exception e) {e.printStackTrace();
        }
    }

    public void backtoHesab(ActionEvent event) {
        login.openNewWindow("hesab.fxml","myaccounts",event);
    }
}
