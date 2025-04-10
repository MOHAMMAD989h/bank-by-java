package com.example.bank;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    loginpage login = new loginpage();
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

            String selectdata = "SELECT * FROM employee WHERE username = ?";

            prepare = connect.prepareStatement(selectdata);
            prepare.setString(1, username);
            rs = prepare.executeQuery();
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
            int bankMaskan=0;
            int bankAureous=0;
            int bankAyandeh=0;
            int bankDey=0;
            int bankKesh=0;
            int bankRefah=0;
            int bankMelli=0;
            int bankMellat=0;
            int bankMehr=0;
            int bankSaman=0;
            int bankSepah=0;
            int bankShahr=0;
            int bankTejarat=0;
            while (result.next()) {
                String number = result.getString("numbercard");
                String time = result.getString("engeza");
                String cvv2 = result.getString("cvv2");
                String bankname = result.getString("bankname");
                String money = result.getString("money");
                products.add(new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone));
                if(bankname.trim().equals("Aureous Bank")){String HboxName="#hbox_Aur";
                    if(bankAureous==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Aur",product1,true));
                    }else if((bankAureous+1)%5==0){int shoaresatr=(bankAureous+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            System.out.println(HboxName3);
                            System.out.println(HboxName);
                            System.out.println(HboxName2);
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Aur"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankAureous>4){int test=bankAureous/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Aur");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankAureous++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Aurlab");
                        foundlabel.setText("تعداد حساب ها:"+bankAureous);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک مسکن")){String HboxName="#hbox_Mas";
                    if(bankMaskan==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Mas",product1,true));
                    }else if((bankMaskan+1)%5==0){int shoaresatr=(bankMaskan+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Mas"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankAureous>4){int test=bankAureous/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mas");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankMaskan++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Maslab");
                        foundlabel.setText("تعداد حساب ها:"+bankMaskan);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک دی")){String HboxName="#hbox_Dey";
                    if(bankDey==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Dey",product1,true));
                    }else if((bankDey+1)%5==0){int shoaresatr=(bankDey+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Dey"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankDey>4){int test=bankDey/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Dey");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankDey++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Deylab");
                        foundlabel.setText("تعداد حساب ها:"+bankDey);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک کشاورزی")){String HboxName="#hbox_Kesh";
                    if(bankKesh==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Kesh",product1,true));
                    }else if((bankKesh+1)%5==0){int shoaresatr=(bankKesh+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Kesh"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankKesh>4){int test=bankKesh/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Kesh");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankKesh++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Keshlab");
                        foundlabel.setText("تعداد حساب ها:"+bankKesh);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک رفاه")){String HboxName="#hbox_Ref";
                    if(bankRefah==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Ref",product1,true));
                    }else if((bankRefah+1)%5==0){int shoaresatr=(bankRefah+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Ref"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankRefah>4){int test=bankRefah/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Ref");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankRefah++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Reflab");
                        foundlabel.setText("تعداد حساب ها:"+bankRefah);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک ملی")){String HboxName="#hbox_Mli";
                    if(bankMelli==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Mli",product1,true));
                    }else if((bankMelli+1)%5==0){int shoaresatr=(bankMelli+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            System.out.println(HboxName3);
                            System.out.println(HboxName);
                            System.out.println(HboxName2);
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Mli"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMelli>4){int test=bankMelli/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mli");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankMelli++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Mlilab");
                        foundlabel.setText("تعداد حساب ها:"+bankMelli);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک ملت")){String HboxName="#hbox_Mlat";
                    if(bankMellat==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Mlat",product1,true));
                    }else if((bankMellat+1)%5==0){int shoaresatr=(bankMellat+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Mlat"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMellat>4){int test=bankMellat/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mlat");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankAureous++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Mlatlab");
                        foundlabel.setText("تعداد حساب ها:"+bankMellat);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک سامان")){String HboxName="#hbox_Sam";
                    if(bankSaman==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Sam",product1,true));
                    }else if((bankSaman+1)%5==0){int shoaresatr=(bankSaman+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Sam"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankSaman>4){int test=bankSaman/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Sam");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankSaman++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Samlab");
                        foundlabel.setText("تعداد حساب ها:"+bankSaman);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک سپه")){String HboxName="#hbox_Sep";
                    if(bankSepah==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Sep",product1,true));
                    }else if((bankSepah+1)%5==0){int shoaresatr=(bankSepah+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Sep"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankSepah>4){int test=bankSepah/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Sep");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankSepah++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Seplab");
                        foundlabel.setText("تعداد حساب ها:"+bankSepah);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک تجارت")){String HboxName="#hbox_Tej";
                    if(bankTejarat==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Tej",product1,true));
                    }else if((bankTejarat+1)%5==0){int shoaresatr=(bankTejarat+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Tej"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankTejarat>4){int test=bankTejarat/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Tej");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankTejarat++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Tejlab");
                        foundlabel.setText("تعداد حساب ها:"+bankTejarat);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک مهر")){String HboxName="#hbox_Meh";
                    if(bankMehr==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Meh",product1,true));
                    }else if((bankMehr+1)%5==0){int shoaresatr=(bankMehr+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Meh"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMehr>4){int test=bankMehr/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Meh");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankMehr++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Mehlab");
                        foundlabel.setText("تعداد حساب ها:"+bankMehr);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک شهر")){String HboxName="#hbox_Shr";
                    if(bankShahr==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bigV.getChildren().add(createHBox("Shr",product1,true));
                    }else if((bankShahr+1)%5==0){int shoaresatr=(bankShahr+1)/5;
                        ObservableList<Node> children = bigV.getChildren();

                        int insertIndex = 0;
                        for (int i = 0; i < children.size(); i++) {
                            String HboxName2=HboxName;
                            if(shoaresatr>1){HboxName2=HboxName + String.valueOf(shoaresatr-1);}
                            Node current = children.get(i);
                            String HboxName3="#"+current.getId();
                            if (current.getId() != null && HboxName3.equals(HboxName2)) {
                                insertIndex = i + 1;
                                System.out.println("وارد سد");
                                break; // خروج از حلقه
                            }
                        }
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        String aur="Shr"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankShahr>4){int test=bankShahr/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Shr");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone);
                        bankShahr++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Shrlab");
                        foundlabel.setText("تعداد حساب ها:"+bankShahr);
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

    public void HomeToLoginpage(ActionEvent event) {
        login.openNewWindow("loginpage.fxml","login",event);
    }
    public void hessabView(String hessabNum,ActionEvent event) {
        openNewWindow2("hessabView.fxml","نمایش حساب",hessabNum,event);
    }
    public void profile(ActionEvent event) {
        login.openNewWindow("profile1.fxml","login",event);
    }
    public HBox createHBox(String HId,productVam product,boolean test) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(260.0);
        hBox.setPrefWidth(1382.0);
        String uniqueId = "hbox_" + HId;
        hBox.setId(uniqueId);
        hBox.setStyle("-fx-background-color: #f7dc84; -fx-border-color: #fff; -fx-padding: 15px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
        hBox.setVisible(true);
        if (test) {Pane pane =new Pane();
            pane.setPrefHeight(10.0);
            pane.setPrefWidth(1382.0);
            VBox vBox = new VBox();
            vBox.setPrefHeight(251.0);
            vBox.setPrefWidth(125.0);
            vBox.setStyle("-fx-background-color: #1E8E73; -fx-border-color: #fff; -fx-padding: 15px; -fx-background-radius: 15px; -fx-border-radius: 15px; ");

            // ایجاد Label اول
            Label label1 = new Label(product.getPrice());
            label1.setPrefHeight(37.0);
            label1.setPrefWidth(128.0);

            // ایجاد ImageView
            ImageView imageView = new ImageView();
            imageView.setFitHeight(98.0);
            imageView.setFitWidth(124.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            // ایجاد Label دوم
            Label label2 = new Label();
            label2.setPrefHeight(48.0);
            label2.setPrefWidth(133.0);
            label2.setId(uniqueId+"lab");

            // افزودن اجزا به VBox
            vBox.getChildren().addAll(label1, imageView, label2,pane);
            hBox.getChildren().add(vBox);
        }
        return hBox;
    }


    public VBox createVBoxWithPane(productVam product) {
        // ایجاد VBox
        VBox vBox = new VBox();
        vBox.setPrefHeight(251.0);
        vBox.setPrefWidth(256.0);
        vBox.setStyle("-fx-background-color: #4A80D0; -fx-border-color: #fff; -fx-padding: 15px; -fx-background-radius: 15px; -fx-border-radius: 15px");

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


        Button button = new Button();
        button.setLayoutX(202.0);
        button.setLayoutY(0.0);
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

        Button button1 = new Button();
        button.setLayoutX(183.0);
        button.setLayoutY(43.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(20.0);
        button.setPrefWidth(34.0);

        // ایجاد ImageView
        ImageView imageView1 = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه
        button1.setGraphic(imageView1);

        Button button2 = new Button();
        button.setLayoutX(160.0);
        button.setLayoutY(70.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(20.0);
        button.setPrefWidth(34.0);

        // ایجاد ImageView
        ImageView imageView2 = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه
        button2.setGraphic(imageView2);

        // اضافه کردن لیبل‌ها به Pane
        pane.getChildren().addAll(labelCardNumber, labelAccountNumber, labelPersonName, labelBalance, labelCvv2,button,button1,button2);
        Button mainButton = new Button();
        mainButton.setPrefSize(256.0, 254.0);
        mainButton.setGraphic(pane);
        mainButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ccc;");
        mainButton.setOnAction(event -> hessabView(product.getName(),event));

        // اضافه کردن دکمه به VBox
        vBox.getChildren().add(mainButton);
        return vBox;
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
