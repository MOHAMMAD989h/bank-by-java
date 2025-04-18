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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        try {DataBase1 Select=new DataBase1();
            String numberq = "";
            String nationcode="";
            String numberphone="";
            System.out.println("(((((");
            ArrayList<String> data2 =Select.isdataimportvalid2(username,"employee","username");
            for (int c = 0; c < data2.size(); c+=3) {
                numberq = data2.get(0);
                userlabel.setText(numberq);
                codelabel.setText(data2.get(1));
                numlabel.setText(data2.get(2));
                nationcode = data2.get(1);
                numberphone = data2.get(2);
            }


            DataBase1 getting=new DataBase1();
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
            System.out.println("8888");
            ArrayList<String> data11 =getting.isdataimportvalid1(username,"cards","username");
            for (int c = 0; c < data11.size(); c+=5) {
                String number =data11.get(c);
                String time = data11.get(c+1);
                String cvv2 = data11.get(c+2);
                String bankname = data11.get(c+3);
                String money = data11.get(c+4);
                products.add(new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money));
                if(bankname.trim().equals("Aureous Bank")){String HboxName="#hbox_Aur";
                    if(bankAureous==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Aur"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankAureous>4){int test=bankAureous/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Aur");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankMaskan==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Mas"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankAureous>4){int test=bankAureous/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mas");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankDey==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Dey"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankDey>4){int test=bankDey/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Dey");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankKesh==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Kesh"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankKesh>4){int test=bankKesh/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Kesh");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankRefah==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Ref"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankRefah>4){int test=bankRefah/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Ref");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        bankRefah++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Reflab");
                        foundlabel.setText("تعداد حساب ها:"+bankRefah);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک آینده")){String HboxName="#hbox_Aya";
                    if(bankAyandeh==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        bigV.getChildren().add(createHBox("Aya",product1,true));
                    }else if((bankAyandeh+1)%5==0){int shoaresatr=(bankAyandeh+1)/5;
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Aya"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankAyandeh>4){int test=bankAyandeh/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Aya");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        bankAyandeh++;
                        foundHBox1.getChildren().add(createVBoxWithPane(product1));
                        Label foundlabel = (Label) foundHBox.lookup("#hbox_Ayalab");
                        foundlabel.setText("تعداد حساب ها:"+bankAyandeh);
                        // اضافه کردن نود جدید
                    } else {
                        System.out.println("HBox پیدا نشد!");
                    }
                }
                if(bankname.trim().equals("بانک ملی")){String HboxName="#hbox_Mli";
                    if(bankMelli==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Mli"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMelli>4){int test=bankMelli/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mli");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankMellat==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Mlat"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMellat>4){int test=bankMellat/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Mlat");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankSaman==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Sam"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankSaman>4){int test=bankSaman/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Sam");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankSepah==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Sep"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankSepah>4){int test=bankSepah/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Sep");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankTejarat==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Tej"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankTejarat>4){int test=bankTejarat/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Tej");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankMehr==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Meh"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankMehr>4){int test=bankMehr/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Meh");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                    if(bankShahr==0){productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
                        productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
                        String aur="Shr"+String.valueOf(shoaresatr);
                        bigV.getChildren().add(insertIndex,createHBox(aur,product1,false));
                        HboxName=HboxName+String.valueOf(shoaresatr);
                    }else if(bankShahr>4){int test=bankShahr/5;
                        HboxName=HboxName+String.valueOf(test);
                    }
                    HBox foundHBox = (HBox) bigV.lookup("#hbox_Shr");
                    HBox foundHBox1 = (HBox) bigV.lookup(HboxName);
                    if (foundHBox != null) {productVam product1 =new productVam(number,bankname , cvv2, time, numberq,nationcode,numberphone,money);
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
        vBox.setStyle("-fx-background-color: #4A80D0; -fx-border-color: #fff; -fx-padding: 15px; -fx-background-radius: 15px; -fx-border-radius: 15px;-fx-cursor: hand;");

        // ایجاد Pane
        Pane pane = new Pane();
        pane.setPrefHeight(254.0);
        pane.setPrefWidth(256.0);

        // ایجاد لیبل‌ها
        Label labelCardNumber = new Label("شماره کارت : "+product.getName());
        labelCardNumber.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'B Nazanin'; " +
                        "-fx-font-size: 12px;"
        );
        labelCardNumber.setLayoutX(23.0);
        labelCardNumber.setLayoutY(41.0);
        labelCardNumber.setPrefHeight(28.0);
        labelCardNumber.setPrefWidth(205.00);

        Label labelPersonName = new Label(product.getPagePath());
        labelPersonName.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'B Nazanin'; " +
                        "-fx-font-size: 15px;"
        );
        labelPersonName.setAlignment(Pos.CENTER_RIGHT);
        labelPersonName.setLayoutX(85.00);
        labelPersonName.setLayoutY(96.0);
        labelPersonName.setPrefHeight(28.0);
        labelPersonName.setPrefWidth(121.0);

        Label labelBalance = new Label("موجودی:"+product.getMojudi());
        labelBalance.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'B Nazanin'; " +
                        "-fx-font-size: 15px;"
        );
        labelBalance.setAlignment(Pos.CENTER);
        labelBalance.setLayoutX(60.00);
        labelBalance.setLayoutY(158.0);
        labelBalance.setPrefHeight(28.0);
        labelBalance.setPrefWidth(150.00);

        Label labelCvv2 = new Label("Cvv2:"+product.getSood());
        labelCvv2.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'B Nazanin'; " +
                        "-fx-font-size: 15px;"
        );
        labelCvv2.setAlignment(Pos.CENTER);
        labelCvv2.setLayoutX(10.00);
        labelCvv2.setLayoutY(148.0);
        labelCvv2.setPrefHeight(20.0);
        labelCvv2.setPrefWidth(90.00);

        Label labelExpirationDate = new Label("تاریخ انقضاء:"+product.getTime());
        labelExpirationDate.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'B Nazanin'; " +
                        "-fx-font-size: 15px;"
        );
        labelExpirationDate.setAlignment(Pos.CENTER_RIGHT);
        labelExpirationDate.setLayoutX(85.0);
        labelExpirationDate.setLayoutY(199.0);
        labelExpirationDate.setPrefHeight(28.0);
        labelExpirationDate.setPrefWidth(127.0);

        // ایجاد ImageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه

        // ایجاد ImageView
        ImageView imageView1 = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه

        // ایجاد ImageView
        ImageView imageView2 = new ImageView();
        imageView.setFitHeight(17.0);
        imageView.setFitWidth(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // افزودن تصویر به دکمه

        // اضافه کردن لیبل‌ها به Pane
        pane.getChildren().addAll(labelCardNumber, labelPersonName, labelBalance, labelCvv2);
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
