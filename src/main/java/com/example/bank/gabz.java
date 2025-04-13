package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static com.example.bank.loginpage.*;

public class gabz {

    loginpage login = new loginpage();
    Random random = new Random();
    File file = new File("gabz.dat");  // فایل باینری

    @FXML
    private TextField gabzTextfield;

    private String input;
    private String[] inputs;
    int payment;
    @FXML
    private Label moneyGabz;

    profile pro = new profile();

    Alert alert ;

    @FXML
    private Label organGabz;

    @FXML
    private TextField numbercardGetter;
    @FXML
    private Button gabznumber;
    @FXML
    private Button gabzPayment;
    @FXML
    private Label inforGabz;

    Connection connect = null;
    PreparedStatement prepare = null;

    @FXML
    void gabzPayment(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        int result = pro.updateCredit(numbercardGetter.getText(), -payment);
        if (result == -1) {
            organGabz.setText("پرداخت نشد");
            gabzTextfield.setText("");
            moneyGabz.setText("");
        } else {
            pro.fileTransfer(numbercardGetter.getText(),"", Long.valueOf(String.valueOf(-payment)),"قبض");
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("با موفقیت انجام شد");
            alert.setTitle("INFORMATION");
            alert.showAndWait();

            // به‌روزرسانی مقدار در فایل باینری
            updateGabzFile(gabzTextfield.getText());

            // مقدار پرداخت را صفر کن
            payment = 0;
            moneyGabz.setText("0");

            login.openNewWindow("main.fxml", "Home", event);
        }
    }
    public void setMethod(String method)throws SQLException, IOException {
        input = Files.readString(file.toPath());
        inputs = input.split(",|\\n");
        for (int i=0;i<inputs.length;i++) {
            inputs[i] = inputs[i].trim();
        }
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i]+"*");
        }
        numbercardGetter.setText(method);
    }
    @FXML
    void gabznumber(ActionEvent event) throws IOException {
        boolean flag = false;
        for (int i = 0; i < inputs.length; i+=2) {
            if (gabzTextfield.getText().trim().equals(inputs[i].trim())) {
                flag = true;
                organGabz.setStyle(" -fx-font-family: \"B Nazanin\";");
                moneyGabz.setStyle(" -fx-font-family: \"B Nazanin\";");
                gabznumber.setVisible(false);
                gabzPayment.setVisible(true);
                payment = Integer.parseInt(inputs[i+1]);
                moneyGabz.setText("قیمت(تومان) : " + payment);
                if(String.valueOf(inputs[i]).trim().charAt(0) == '1'){
                    organGabz.setText(" اداره اب و فاضلاب");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '2') {
                    organGabz.setText("اداره برق");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '3') {
                    organGabz.setText("اداره گاز");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '4') {
                    organGabz.setText("خدمات تلفن ثابت");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '5') {
                    organGabz.setText("خدمات تلفن همراه");
                }else if (String.valueOf(inputs[i]).trim().charAt(0) == '6') {
                    organGabz.setText("عوارض شهرداری");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '7') {
                    organGabz.setText("سازمان مالیات");
                } else if (String.valueOf(inputs[i]).trim().charAt(0) == '8') {
                    organGabz.setText("جرایم رانندگی");
                } else{
                    organGabz.setText("");
                }
            }
        }
        if (!flag) {
            inforGabz.setText("مقادیر معتیر نیست");
            inforGabz.setStyle(" -fx-font-family: \\\"B Nazanin\\\";\"");
        }
    }

    private void updateGabzFile(String gabzNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> updatedContent = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(gabzNumber)) {
                data[1] = "0";
            }
            updatedContent.add(String.join(",", data));
        }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String updatedLine : updatedContent) {
            writer.write(updatedLine);
            writer.newLine();
        }

        writer.close();
    }Hesab pro1 = new Hesab();

    @FXML
    private void backtoHesabFromGabz(ActionEvent event) {
        String method=numbercardGetter.getText();
        hessabView(method,event);
    }public void hessabView(String hessabNum,ActionEvent event) {
        openNewWindow2("hessabView.fxml","نمایش حساب",hessabNum,event);
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
