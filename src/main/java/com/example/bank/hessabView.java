package com.example.bank;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
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

    public static boolean isTransferShort;
    @FXML
    private VBox vboxsimcard;
    @FXML
    private Label numlabel;
    File file = new File("gabz.dat");
    @FXML
    private VBox vboxGabz;
    private List<productCharge> productGabz = new ArrayList<productCharge>();
    @FXML
    private VBox vboxInternet;
    private List<productCharge> productInternet = new ArrayList<productCharge>();
    private List<productCharge> productsimcard = new ArrayList<productCharge>();


    private String input;
    private String[] inputs;
    Random random = new Random();
    private static String numbercard;
    profile pro = new profile();

    File file1 = new File("charge.txt");

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

        //charge
        fileCharge();

        //internet
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت هفتگی","15000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول دو گیگ اینترنت هفتگی","18000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت هفتگی","22000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول پنچ گیگ اینترنت هفتگی","25000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول یک گیگ اینترنت ماهانه","19000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول سه گیگ اینترنت ماهانه","24000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول پنج گیگ اینترنت ماهانه","28000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("همراه اول هفت گیگ اینترنت ماهانه","33000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل اول یک گیگ اینترنت هفتگی","15000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول دو گیگ اینترنت هفتگی","18000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت هفتگی","22000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول پنچ گیگ اینترنت هفتگی","25000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول یک گیگ اینترنت ماهانه","19000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول سه گیگ اینترنت ماهانه","24000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول پنج گیگ اینترنت ماهانه","28000"+"قیمت (تومان) :  "));
        productInternet.add(new productCharge("ایرانسل  اول هفت گیگ اینترنت ماهانه","33000"+"قیمت (تومان) :  "));

        //gabz
        filegabz();
        input = Files.readString(file.toPath());
        inputs = input.split(",|\\n");
        int  k = random.nextInt(inputs.length - 5);
        if(inputs[k].length() < 10) {
            k++;
        }
        productGabz.add(new productCharge(inputs[k],inputs[k+1]+"قیمت (تومان) :  "));
        productGabz.add(new productCharge(inputs[k+2],inputs[k+3]+"قیمت (تومان) :  "));
        productGabz.add(new productCharge(inputs[k+4],inputs[k+5]+"قیمت (تومان) :  "));
        productGabz.add(new productCharge(inputs[k+6],inputs[k+7]+"قیمت (تومان) :  "));
        productGabz.add(new productCharge(inputs[k+8],inputs[k+9]+"قیمت (تومان) :  "));

        input = Files.readString(file1.toPath());
        inputs = input.split(",|\\n");
        k = random.nextInt(inputs.length - 5);

        productsimcard.add(new productCharge(inputs[k],"شماره : "));
        productsimcard.add(new productCharge(inputs[k+1],"شماره : "));
        productsimcard.add(new productCharge(inputs[k+2],"شماره : "));
        productsimcard.add(new productCharge(inputs[k+3],"شماره : "));
        productsimcard.add(new productCharge(inputs[k+4],"شماره : "));

        for (productCharge p : productInternet) {
            vboxInternet.getChildren().add(createproductpane(p));
        }
        for (productCharge p : productGabz) {
            vboxGabz.getChildren().add(createproductpane(p));
        }
        for (productCharge p : productsimcard) {
            vboxsimcard.getChildren().add(createproductpane(p));
        }

        btn6.setOnAction(event -> {
            isTransferShort = true;
            System.out.println(isTransferShort);
            pro.openNewWindow("transferRec.fxml","Transfer",event);
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
        Label PriceLbl = new Label(p.getPrice());
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
        openNewWindow2("entegal.fxml","انتقال وجه",numbercard,event);
    }
    @FXML
    private void handleServiceOption3(ActionEvent actionEvent) {
        openNewWindow2("charge.fxml","charge",numbercard,actionEvent);
    }
    @FXML
    private void handleServiceOption2(ActionEvent actionEvent) {
        openNewWindow2("gabz.fxml","gabz",numbercard,actionEvent);}


    String infor = null;
    private List<productVam> products = new ArrayList<>();
    public void setMethod(String method) {
        try {DataBase1 Select=new DataBase1();
            try (FileInputStream fileIn = new FileInputStream("userData.dat");
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                Select = (DataBase1) in.readObject();

                System.out.println("Object loaded successfully!");
                // حالا می‌تونی با loadedObject کار کنی:
                // مثلاً:
                // System.out.println(loadedObject.getUserName());

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String numberq = "";
            String nationcode="";
            String numberphone="";
            while (Select.isdataimportvalid(username,"employee","username")) {
                numberq = Select.getName();
                userlabel.setText(numberq);
                codelabel.setText(Select.getNationcode());
                numlabel.setText(Select.getNumberphone());
            }
            DataBase1 getting=new DataBase1();
            products.clear();
            while (getting.isdataimportvalid1(username,"cards","username")) {
                numbercard=getting.getCartNum();
                String number =getting.getCartNum();
                String time = getting.getYyengeza();
                String cvv2 = getting.getCvv2();
                String bankname = getting.getBankName();
                String money = getting.getMoney();
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
        String line = Files.readString(file.toPath());

        if(line.isEmpty()) {
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
    private void fileCharge() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 1000; i++) {
            long numberCharge = random.nextLong(10000000, 99999999);
            int pishnum = random.nextInt(1, 3);
            long number = Long.parseLong("09"+String.valueOf(pishnum) + String.valueOf(numberCharge));
            fw.write(String.valueOf(number) + ',');
        }


    }
    public void openNewWindow2 (String fxmlFile, String title, String method, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 1535, 790);

            //ارسال متد

            hessabView controller = loader.getController();
            controller.setMethod(method);

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
