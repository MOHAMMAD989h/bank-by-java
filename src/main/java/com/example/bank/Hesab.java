package com.example.bank;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.example.bank.loginpage.username;

public class Hesab {
    @FXML
    private Label messagelabel;

    @FXML
    private Label userlabel;

    @FXML
    private Label codelabel;

    @FXML
    private Label numlabel;
    @FXML
    private VBox bigV;

    @FXML
    private void copyuser() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String username = userlabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(username);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }
    @FXML
    private void copycode() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String code = codelabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(code);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }
    @FXML
    private void copynum() {
        messagelabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> messagelabel.setVisible(false));
        delay.play();
        String num = numlabel.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(num);
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }

    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    ResultSet rs;

    String infor = null;
    private List<productVam> products = new ArrayList<>();
    public void initialize() {
        try {
            connect = DataBase1.connectDB();
            String data = "SELECT * FROM cards WHERE username = ?";

            String selectdata = "SELECT * FROM employee";

            prepare = connect.prepareStatement(selectdata);

            rs = prepare.executeQuery(selectdata);
            String numberq = "";
            String nationcode="";
            String numberphone="";
            while (rs.next()) {
                numberq = rs.getString("name");
                userlabel.setText(numberq);
                codelabel.setText(rs.getString("nationcode"));
                numlabel.setText(rs.getString("numberphone"));
                nationcode=rs.getString("nationcode");
                numberphone=rs.getString("numberphone");
            }

            prepare = connect.prepareStatement(data);
            prepare.setString(1,username);
            result = prepare.executeQuery();
            products.clear();
            boolean bankMaskan=false;
            boolean bankAureous=false;
            boolean bankAyandeh=false;
            boolean bankDey=false;
            boolean bankKesh=false;
            boolean bankRefah=false;
            boolean bankMelli=false;
            boolean bankMellat=false;
            boolean bankMehr=false;
            boolean bankSaman=false;
            boolean bankSepah=false;
            boolean bankShahr=false;
            boolean bankTejarat=false;
            while (result.next()) {
                String number = result.getString("numbercard");
                String time = result.getString("engeza");
                String cvv2 = result.getString("cvv2");
                String bankname = result.getString("bankname");
                String money = result.getString("money");
                products.add(new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone));
                if(bankname.trim().equals("بانک مسکن")){
                    if(!bankMaskan){
                        for (productVam product1 : products) {
                            bigV.getChildren().add(createHBox("mas",product1));
                        }bankMaskan=true;
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_mas");
                    if (foundHBox != null) {
                        for (productVam product1 : products) {
                            foundHBox.getChildren().add(createVBoxWithPane(product1));
                        }
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("Aureous Bank")){int tedadHesab=0;
                    if(!bankAureous){
                        for (productVam product1 : products) {
                            bigV.getChildren().add(createHBox("Aur",product1));
                        }bankAureous=true;
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Aur");
                    if (foundHBox != null) {
                        for (productVam product1 : products) {tedadHesab++;
                            foundHBox.getChildren().add(createVBoxWithPane(product1));
                        }Label foundlabel = (Label) foundHBox.lookup("#hbox_Aur");
                        foundlabel.setText(""+tedadHesab);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
            }
        }
        catch (Exception e) {e.printStackTrace();
        }
    }

    public void HomeToLoginpage(ActionEvent actionEvent) {
    }

    public void profile(ActionEvent actionEvent) {

    }
    public HBox createHBox(String HId,productVam product) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(251.0);
        hBox.setPrefWidth(1382.0);
        String uniqueId = "hbox_" + HId;
        hBox.setId(uniqueId);
        hBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");

        VBox vBox = new VBox();
        vBox.setPrefHeight(251.0);
        vBox.setPrefWidth(125.0);

        // ایجاد Label اول
        Label label1 = new Label("  نام بانک : "+product.getPrice());
        label1.setPrefHeight(37.0);
        label1.setPrefWidth(128.0);

        // ایجاد ImageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(98.0);
        imageView.setFitWidth(124.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // ایجاد Label دوم
        Label label2 = new Label(" cvv2 :"+product.getSood());
        label2.setPrefHeight(48.0);
        label2.setPrefWidth(133.0);
        label2.setId(uniqueId);

        // افزودن اجزا به VBox
        vBox.getChildren().addAll(label1, imageView, label2);
        hBox.getChildren().add(vBox);
        // در صورتی که بخواهید VBox را در GridPane نمایش دهید
        return hBox;
    }


    public VBox createVBoxWithPane(productVam product) {
        // ایجاد VBox
        VBox vBox = new VBox();
        vBox.setPrefHeight(251.0);
        vBox.setPrefWidth(256.0);

        // ایجاد Pane
        Pane pane = new Pane();
        pane.setPrefHeight(254.0);
        pane.setPrefWidth(256.0);

        // ایجاد لیبل‌ها
        Label labelCardNumber = new Label("شماره کارت : "+product.getName());
        labelCardNumber.setLayoutX(23.0);
        labelCardNumber.setLayoutY(41.0);
        labelCardNumber.setPrefHeight(28.0);
        labelCardNumber.setPrefWidth(188.0);

        Label labelAccountNumber = new Label("شماره حساب");
        labelAccountNumber.setLayoutX(24.0);
        labelAccountNumber.setLayoutY(73.0);
        labelAccountNumber.setPrefHeight(20.0);
        labelAccountNumber.setPrefWidth(148.0);

        Label labelPersonName = new Label(product.getPagePath());
        labelPersonName.setAlignment(Pos.CENTER_RIGHT);
        labelPersonName.setLayoutX(98.0);
        labelPersonName.setLayoutY(96.0);
        labelPersonName.setPrefHeight(28.0);
        labelPersonName.setPrefWidth(121.0);

        Label labelBalance = new Label("موجودی:");
        labelBalance.setAlignment(Pos.CENTER_RIGHT);
        labelBalance.setLayoutX(28.0);
        labelBalance.setLayoutY(168.0);
        labelBalance.setPrefHeight(28.0);
        labelBalance.setPrefWidth(95.0);

        Label labelCvv2 = new Label("Cvv2:"+product.getSood());
        labelCvv2.setLayoutX(33.0);
        labelCvv2.setLayoutY(148.0);
        labelCvv2.setPrefHeight(20.0);
        labelCvv2.setPrefWidth(69.0);

        Label labelExpirationDate = new Label("تاریخ انقضاء:"+product.getTime());
        labelExpirationDate.setAlignment(Pos.CENTER_RIGHT);
        labelExpirationDate.setLayoutX(38.0);
        labelExpirationDate.setLayoutY(199.0);
        labelExpirationDate.setPrefHeight(28.0);
        labelExpirationDate.setPrefWidth(127.0);


        Button button1 = createButtonWithImage(202.0, 0.0);
        Button button2 = createButtonWithImage(183.0, 43.0);
        Button button3 = createButtonWithImage(160.0, 70.0);

        // اضافه کردن لیبل‌ها به Pane
        pane.getChildren().addAll(labelCardNumber, labelAccountNumber, labelPersonName, labelBalance, labelCvv2);

        // اضافه کردن Pane به VBox
        vBox.getChildren().add(pane);

        return vBox;
    }
    private Button createButtonWithImage(double x, double y) {
        Button button = new Button();
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setMnemonicParsing(false);
        button.setPrefHeight(20.0);
        button.setPrefWidth(34.0);

        // ایجاد ImageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه
        button.setGraphic(imageView);

        return button;
    }

}
