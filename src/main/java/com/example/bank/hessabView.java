package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class hessabView {

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

    private void applyHoverEffect(Button button) {
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
}
