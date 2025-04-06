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


    private List<productVam> products = new ArrayList<>();

    public void initialize() {
        products.add(new productVam("وام یک","10000000","14","30","disVam.fxml","ffffhhhh",""));
        products.add(new productVam("2","10000000","4","30","disVam.fxml","ffff",""));

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
        soodLabel.setLayoutX(150);
        soodLabel.setLayoutY(20);

        //time
        Label timeLabel = new Label( "  زمان(ماه) :  "+product.getTime());
        timeLabel.setLayoutX(250);
        timeLabel.setLayoutY(20);

        //montly payment
        Label monthlyPayment = new Label(" پرداخت ماهانه(تومان) : " + soodVam(product.getPrice(),product.getTime(),product.getSood()));
        monthlyPayment.setLayoutX(400);
        monthlyPayment.setLayoutY(20);

        //price
        Label priceLabel = new Label("تومان : " + product.getPrice());
        priceLabel.setLayoutX(700);
        priceLabel.setLayoutY(20);

        //discription
        Label discriptionLbl = new Label(product.getDiscription());
        discriptionLbl.setLayoutX(70);
        discriptionLbl.setLayoutY(50);

        //button
        Button vambutton = new Button("دریافت وام");
        vambutton.setLayoutX(1200);
        vambutton.setLayoutY(20);
        vambutton.setStyle("    -fx-background-color: linear-gradient(to bottom right ,#FFD700, #d3005f );\n" +
                "    -fx-background-radius: 5px;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-text-fill: #fff;\n" +
                "    -fx-font-size: 14px;");

        pane.getChildren().addAll(nameLabel,soodLabel, priceLabel,timeLabel,monthlyPayment,vambutton,discriptionLbl);

        //set on action vambutton
        vambutton.setOnAction(event -> {
            product.getTime();
            try {
                nameLoan = product.getName();
                timeLoan = Integer.parseInt(product.getTime());
                soodLoan = Double.parseDouble(product.getSood());
                priceLoan = Long.valueOf(product.getPrice());
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

}
