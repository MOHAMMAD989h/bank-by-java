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
    private Button gabzPayment;

    @FXML
    private TextField gabzTextfield;

    @FXML
    private Button gabznumber;

    @FXML
    private Label gabz1;

    @FXML
    private Label gabz2;

    @FXML
    private Label gabz3;

    @FXML
    private Label gabz4;

    @FXML
    private Label gabz5;

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

    Connection connect = null;
    PreparedStatement prepare = null;

    public void initialize() throws IOException, SQLException {
        filegabz();
        input = Files.readString(file.toPath());
        inputs = input.split(",");
        int  k = random.nextInt(inputs.length);
        if(gabzTextfield.getText().length() < 10) {
            k++;
        }
        gabz1.setText(inputs[k]);
        gabz2.setText(inputs[k+2]);
        gabz3.setText(inputs[k+4]);
        gabz4.setText(inputs[k+6]);
        gabz5.setText(inputs[k+8]);
    }

    @FXML
    void gabzPayment(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        int result = pro.updateCredit(numbercardGetter.getText(), -payment);
        if (result == -1) {
            organGabz.setText("پرداخت نشد");
            gabzTextfield.setText("");
            moneyGabz.setText("");
        } else {
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
            for (int i = 0; i < inputs.length; i=i+2) {
                if (gabzTextfield.getText().equals(inputs[i])) {
                    payment = Integer.parseInt(inputs[i+1]);
                    moneyGabz.setText("" + payment);
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
            moneyGabz.setText("first open account");
        }
    }

    private void filegabz() throws IOException, SQLException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (int i = 0; i < 20000; i++) {
            int pricegabz = random.nextInt(20000, 2000000);
            long numbergabz = random.nextLong(10000000, 999999999);
            int pishnum = random.nextInt(1, 9);
            long number = Long.parseLong(String.valueOf(pishnum) + String.valueOf(numbergabz));
            oos.writeObject(number + "," + pricegabz);  // نوشتن داده‌ها به صورت باینری
        }

        oos.close();
    }

    private void updateGabzFile(String gabzNumber) throws IOException, ClassNotFoundException {
        // خواندن فایل به صورت باینری
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<String> updatedContent = new ArrayList<>();
        String line;

        while (fis.available() > 0) {
            line = (String) ois.readObject();
            String[] data = line.split(",");
            if (data[0].equals(gabzNumber)) {
                data[1] = "0";  // مقدار را صفر کن
            }
            updatedContent.add(String.join(",", data));
        }

        ois.close();
        fis.close();

        // نوشتن مجدد فایل باینری
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (String updatedLine : updatedContent) {
            oos.writeObject(updatedLine);
        }

        oos.close();
    }

    public void backtomainFromGabz(ActionEvent actionEvent) {
        login.openNewWindow("main.fxml", "Home", actionEvent);
    }
}
