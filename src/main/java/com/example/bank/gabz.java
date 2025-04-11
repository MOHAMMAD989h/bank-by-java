package com.example.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    Connection connect = null;
    PreparedStatement prepare = null;

    public void initialize() throws IOException, SQLException {
        filegabz();
        input = Files.readString(file.toPath());
        inputs = input.split(",|\\n");
        for (int i=0;i<inputs.length;i++) {
            inputs[i] = inputs[i].trim();
        }
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i]);
        }
    }

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
            alert.setContentText("successfully");
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

    @FXML
    void gabznumber(ActionEvent event) throws IOException {
        if(loginID){
            System.out.println(gabzTextfield.getText().trim());
            for (int i = 0; i < inputs.length; i+=2) {
                gabznumber.setVisible(false);
                gabzPayment.setVisible(true);
                if (gabzTextfield.getText().trim().equals(inputs[i].trim()) && gabzTextfield.getText().trim().equals(inputs[i+1].trim()) ) {
                    gabznumber.setVisible(false);
                    gabzPayment.setVisible(true);
                    payment = Integer.parseInt(inputs[i+1]);
                    System.out.println(inputs[i+1]);
                    moneyGabz.setText("" + payment);
                    System.out.println(String.valueOf(inputs[i]).trim().charAt(0));
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
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please login first");
            alert.setTitle("ERROR");
            alert.showAndWait();
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
    }

    public void backtomainFromGabz(ActionEvent actionEvent) {
        login.openNewWindow("hesab.fxml", "Account", actionEvent);
    }
}
