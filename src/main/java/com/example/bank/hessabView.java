package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    File file = new File("gabz.dat");
    @FXML
    private VBox vboxGabz;
    private List<productCharge> productGabz = new ArrayList<productCharge>();
    @FXML
    private VBox vboxInternet;
    private List<productCharge> productInternet = new ArrayList<productCharge>();

    private String input;
    private String[] inputs;
    Random random = new Random();
    public static String numbercard;
    profile pro = new profile();

    @FXML
    public void initialize() throws IOException {
        applyHoverEffect(btn1);
        applyHoverEffect(btn2);
        applyHoverEffect(btn3);
        applyHoverEffect(btn4);
        applyHoverEffect(btn5);
        applyHoverEffect(btn6);
        applyHoverEffect(btn7);
        applyHoverEffect(btn8);

        //internet
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت هفتگی","15000"));
        productInternet.add(new productCharge("همراه اول دو گیگ اینترنت هفتگی","18000"));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت هفتگی","22000"));
        productInternet.add(new productCharge("همراه اول پنچ گیگ اینترنت هفتگی","25000"));
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت ماهانه","19000"));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت ماهانه","24000"));
        productInternet.add(new productCharge("همراه اول پنج گیگ اینترنت ماهانه","28000"));
        productInternet.add(new productCharge("همراه اول هفت گیگ اینترنت ماهانه","33000"));
        productInternet.add(new productCharge("ایرانسل اول یک گیگ اینترنت هفتگی","15000"));
        productInternet.add(new productCharge("ایرانسل  اول دو گیگ اینترنت هفتگی","18000"));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت هفتگی","22000"));
        productInternet.add(new productCharge("ایرانسل  اول پنچ گیگ اینترنت هفتگی","25000"));
        productInternet.add(new productCharge("ایرانسل  اول یک گیگ اینترنت ماهانه","19000"));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت ماهانه","24000"));
        productInternet.add(new productCharge("ایرانسل  اول پنج گیگ اینترنت ماهانه","28000"));
        productInternet.add(new productCharge("ایرانسل  اول هفت گیگ اینترنت ماهانه","33000"));

        //gabz
        filegabz();
        input = Files.readString(file.toPath());
        inputs = input.split(",|\\n");
        int  k = random.nextInt(inputs.length);
        if(inputs[k].length() < 10) {
            k++;
        }
        productGabz.add(new productCharge(inputs[k],inputs[k+1]));
        productGabz.add(new productCharge(inputs[k+2],inputs[k+3]));
        productGabz.add(new productCharge(inputs[k+4],inputs[k+5]));
        productGabz.add(new productCharge(inputs[k+6],inputs[k+7]));
        productGabz.add(new productCharge(inputs[k+8],inputs[k+9]));

        for (productCharge p : productInternet) {
            vboxInternet.getChildren().add(createproductpane(p));
        }
        for (productCharge p : productGabz) {
            vboxGabz.getChildren().add(createproductpane(p));
        }

        btn6.setOnAction(event -> {
            pro.openNewWindow("TransferShort.fxml","Transfer",event);
        });
    }

    private AnchorPane createproductpane(productCharge p) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");
        vboxInternet.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;");

        //type
        Label typeLbl = new Label(p.getType());
        typeLbl.setLayoutX(50);
        typeLbl.setLayoutY(20);

        //price
        Label PriceLbl = new Label( "قیمت (تومان) :  "+p.getPrice());
        PriceLbl.setLayoutX(400);
        PriceLbl.setLayoutY(20);

        Button button = new Button("copy");
        button.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                "    -fx-font-size: 12;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-color: transparrent;\n" +
                "    -fx-background-radius: 15px;    -fx-background-color: linear-gradient(to bottom right, #1E8E73,  #1fa784);\n" +
                "    -fx-text-fill:  #4d4d4d;\n" +
                "    -fx-background-color: linear-gradient(to bottom right,#4A80D0 ,#70A0E0,  #90B0C8);\n" +
                "    -fx-translate-y: -3px;\n" +
                "    -fx-scale-x: 1.05;\n" +
                "    -fx-scale-y: 1.05;    -fx-text-fill:  #fff;\n" +
                "    -fx-background-color: linear-gradient(to bottom right,#4A80D0 ,#70A0E0,  #90B0C8);\n" +
                "    -fx-translate-y: -3px;\n" +
                "    -fx-scale-x: 1.05;\n" +
                "    -fx-scale-y: 1.05;    -fx-cursor: hand;\n");
        button.setLayoutX(300);
        button.setLayoutY(20);

        button.setOnAction(e -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(typeLbl.getText());
            clipboard.setContent(content);
        });

        pane.getChildren().addAll(typeLbl, PriceLbl,button);

        return pane;
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
                numbercard = result.getString("numbercard");
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

    public void openInternet(ActionEvent actionEvent) {
        login.openNewWindow("internet.fxml","Internet",actionEvent);
    }

    public void openToVam(ActionEvent actionEvent) {login.openNewWindow("vam.fxml","Loan",actionEvent);}

    public void openTransfer(ActionEvent actionEvent) {login.openNewWindow("transferRec.fxml","Transfer",actionEvent);}

    public void rolesBtn(ActionEvent actionEvent) {
        try {
            File pdfFile = new File(getClass().getResource("/قوانین بانک.pdf").toURI());
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported");
                }
            } else {
                System.out.println("File does not exist");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private void filegabz() throws IOException {
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (int i = 0; i < 1000; i++) {
            int pricegabz = random.nextInt(20000, 2000000);
            long numbergabz = random.nextLong(100000000, 999999999);
            int pishnum = random.nextInt(1, 9);
            long number = Long.parseLong(String.valueOf(pishnum) + String.valueOf(numbergabz));
            bufferedWriter.write(number + "," + pricegabz);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
