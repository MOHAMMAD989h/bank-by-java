package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vam {
    @FXML
    private VBox vboxVam;

    public static String nameLoan;
    public static Long priceLoan;
    public static double soodLoan;
    public static int timeLoan;
    public static double soodVammonth;


    private List<productVam> products = new ArrayList<>();

    public void initialize() {
        products.add(new productVam("وام عادی","1000000000","24","30","disVam.fxml","این وام عادی بانک یزرگ آرئوس است که با هر سابقه ای به مشتریان بانک می دهد.",""));
        products.add(new productVam("وام قرض الحسنه","50000000","4","60","disVam.fxml","این وام یک وام فوری برای کسانی که به پول نیاز دارند هست . به صورت سود چهار درست بوده تا به راحتی بتوانند برگردانند",""));
        products.add(new productVam("وام اشتغال","100000000","14","24","disVam.fxml","این وام برای شروع یک کسب و کار و کمک به استغال کشور درست شده و دارای شرایط اسثسنایی است.",""));
        products.add(new productVam("وام طرح نوروز aureuos bank","250000000","9","25","disVam.fxml","این وام برای نوروز و مشتریان این بانک فعال شده و هدف کمک به توسعه بانک و مردم برای پیشرفت در سال جدید است.",""));
        products.add(new productVam("وام طرح ده سالگی آرئوس بانک","400000000","12","35","disVam.fxml","این وام در ده سالگی این بانک به مشتریان داده می شود تا هدیه ای برای ده سال کار درست و خدمت باشد.",""));
        products.add(new productVam("وام تحصیل ","100000000","7","50","disVam.fxml","این وام برای تحصیل به همه افراد داده می شود تا تحصیل کرده و علم بیاموزند و به کشور کمک کنند.",""));



        for (productVam product1 : products) {
            vboxVam.getChildren().add(createProductPane(product1));
        }
    }

    private AnchorPane createProductPane(productVam product) {
        AnchorPane pane = null;

        pane = new AnchorPane();
        pane.setPrefHeight(300);
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-padding: 10px;");
        vboxVam.setStyle("-fx-max-height: Infinity;-fx-pref-height: USE_COMPUTED_SIZE;");

        //name
        Label nameLabel = new Label( "  نام :  "+product.getName());
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(20);

        //sood
        Label soodLabel = new Label( "  سود :  "+product.getSood());
        soodLabel.setLayoutX(300);
        soodLabel.setLayoutY(20);

        //time
        Label timeLabel = new Label( "  زمان(ماه) :  "+product.getTime());
        timeLabel.setLayoutX(500);
        timeLabel.setLayoutY(20);

        //montly payment
        Label monthlyPayment = new Label(" پرداخت ماهانه(تومان) : " + soodVam(product.getPrice(),product.getTime(),product.getSood()));
        monthlyPayment.setLayoutX(700);
        monthlyPayment.setLayoutY(20);

        //price
        Label priceLabel = new Label("تومان : " + product.getPrice());
        priceLabel.setLayoutX(1000);
        priceLabel.setLayoutY(20);

        //discription
        Label discriptionLbl = new Label(product.getDiscription());
        discriptionLbl.setLayoutX(70);
        discriptionLbl.setLayoutY(50);

        //button
        Button vambutton = new Button("دریافت وام");
        vambutton.setLayoutX(1200);
        vambutton.setLayoutY(20);
        vambutton.setStyle("    -fx-font-family: \"B Nazanin\";\n" +
                "    -fx-font-size: 18;\n" +
                "    -fx-font-weight: bold;\n" +
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

        pane.getChildren().addAll(nameLabel,soodLabel, priceLabel,timeLabel,monthlyPayment,vambutton,discriptionLbl);

        //set on action vambutton
        vambutton.setOnAction(event -> {
            product.getTime();
            try {
                nameLoan = product.getName();
                timeLoan = Integer.parseInt(product.getTime());
                soodLoan = Double.parseDouble(product.getSood());
                priceLoan = Long.valueOf(product.getPrice());
                soodVammonth = soodVam(product.getPrice(),product.getTime(),product.getSood());
                openNewWindow("disVam.fxml","Loan",event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return pane;

    }

    public int soodVam(String loanAmount, String months, String annualInterestRate) {
        double monthlyInterestRate = (Double.parseDouble(annualInterestRate) / 100) / 12;

        double monthlyPayment = (Double.parseDouble(loanAmount) * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -Integer.parseInt(months)));

        return (int) monthlyPayment;
    }
    public void openNewWindow(String Path , String title, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/com/example/bank/"+Path));
        Scene scene = new Scene(loader.load(), 1535, 790);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        System.out.println("Opening: " + Path);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void exitLoan(ActionEvent actionEvent) throws IOException {
        openNewWindow("hesab.fxml","Hesab",actionEvent);
    }
}
