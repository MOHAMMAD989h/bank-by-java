package com.example.bank;

import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.awt.*;
import javafx.scene.control.ScrollPane;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.bank.loginpage.loginID;

public class Controller {
    @FXML
    private AnchorPane ancktext;
    @FXML
    private Text bank1;
    @FXML
    private Text bank2;
    @FXML
    private Text bank3;
    @FXML
    private Text bank4;
    @FXML
    private Text bank5;
    @FXML
    private Text bank6;
    @FXML
    private Text bank7;
    @FXML
    private Text bank8;
    @FXML
    private Text bank9;
    @FXML
    private Text bank10;
    @FXML
    private Text bank11;
    @FXML
    private Text bank12;

    @FXML
    private GridPane loanOptions;

    @FXML
    private StackPane optionsStackPane;

    @FXML
    private Label timeLabel;

    @FXML
    private GridPane bank;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button bank1btn;

    @FXML
    private Button bank2btn;

    @FXML
    private Button bank3btn;

    @FXML
    private Button bank4btn;

    @FXML
    private Button bank5btn;

    @FXML
    private Button bank6btn;

    @FXML
    private Button bank7btn;

    @FXML
    private Button bank8btn;

    @FXML
    private Button bank9btn;

    @FXML
    private Button bank10btn;

    @FXML
    private Button bank11btn;

    @FXML
    private Button bank12btn;

    @FXML
    private Button soal1;

    @FXML
    private Button soal2;

    @FXML
    private Button soal3;

    @FXML
    private Button soal4;

    @FXML
    private Button soal5;

    @FXML
    private Button soal6;

    @FXML
    private Accordion acc1;

    @FXML
    private Accordion acc2;

    @FXML
    private Accordion acc3;

    @FXML
    private Accordion acc4;

    @FXML
    private Accordion acc5;

    @FXML
    private Accordion acc6;

    @FXML
    private ScrollPane scrol;

    @FXML
    private Button updatebtn;

    @FXML
    private Label dollor;

    @FXML
    private Label Euro;

    String Dolarstring;
    String Eurostring;
    int Dolarint;
    int Euroint;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private Label message;

    @FXML
    private Button dtor;

    @FXML
    private Button changebtn;

    @FXML
    private Button telbtn;

    @FXML
    private Button instabtn;

    @FXML
    private Button phonebtn;

    @FXML
    private ListView<String> listTopics;

    private HashMap<String, String> topicFXMLMap = new HashMap<>();

    @FXML
    private MediaView mediaView;
    @FXML
    private MediaView media2;
    @FXML
    private AnchorPane chat;
    @FXML
    private Button btnm;
    @FXML
    private Circle circle;
    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatArea;

    @FXML
    private Button sendButton;

    private MediaPlayer mediaPlayer;



    private AnimationTimer timer;
    private PauseTransition pauseTransition;
    Alert alert;


    loginpage login = new loginpage();

    @FXML
    public void initialize() {

        sendButton.setOnAction(event -> sendMessage());
        chatArea.setEditable(false);
        Platform.runLater(() -> {
            sendButton.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    sendButton.fire(); // مثل کلیک کردن دکمه
                }
            });
        });


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTime();
            }
        };
        timer.start();

        //ویدیو
        try {
            String videoPath = getClass().getResource("/main.mp4").toExternalForm();
            profile obj2 = new profile();
            obj2.playMedia(videoPath, mediaView, 1478, 700);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String videoPath2 = getClass().getResource("/chat.mp4").toExternalForm();
            profile obj2 = new profile();
            obj2.playMedia(videoPath2, media2, 50, 50);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Tooltip tooltip = new Tooltip("ارتباط با هوش مصنوعی بانکی");
        btnm.setTooltip(tooltip);



        hessabView obj = new hessabView();
        obj.applyHoverEffect(bank1btn);
        obj.applyHoverEffect(bank2btn);
        obj.applyHoverEffect(bank3btn);
        obj.applyHoverEffect(bank4btn);
        obj.applyHoverEffect(bank5btn);
        obj.applyHoverEffect(bank6btn);
        obj.applyHoverEffect(bank7btn);
        obj.applyHoverEffect(bank8btn);
        obj.applyHoverEffect(bank9btn);
        obj.applyHoverEffect(bank10btn);
        obj.applyHoverEffect(bank11btn);
        obj.applyHoverEffect(bank12btn);
        obj.applyHoverEffect(soal1);
        obj.applyHoverEffect(soal2);
        obj.applyHoverEffect(soal3);
        obj.applyHoverEffect(soal4);
        obj.applyHoverEffect(soal5);
        obj.applyHoverEffect(soal6);
        obj.applyHoverEffect(dtor);
        applyHoverEffect(instabtn);
        applyHoverEffect(phonebtn);
        applyHoverEffect(telbtn);

        scrol.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    scrol.setVvalue(scrol.getVvalue() - 0.1);
                    break;
                case DOWN:
                    scrol.setVvalue(scrol.getVvalue() + 0.1);
                    break;
            }
        });

        loadTopics();

        listTopics.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                openFXML(newVal);
            }
        });


        // استفاده از Platform.runLater برای اطمینان از تنظیم Scene
        /*Platform.runLater(() -> {
            bindImageSizeToWindow();
        });*/

        // تنظیم رویدادهای موس برای گرید پین‌ها
        setupGridPaneHoverBehavior();
    }
    private void sendMessage() {
        String userMessage = inputField.getText();
        if (!userMessage.isEmpty()) {
            chatArea.appendText("شما: " + userMessage + "\n");



            try {
                String botResponse = ChatbotService.getAIResponse(userMessage);
                chatArea.appendText("چت‌بات: " + botResponse + "\n");
            } catch (Exception e) {
                chatArea.appendText("⚠ خطا در ارتباط با چت‌بات!\n");
            }

            inputField.clear();
        }
    }



    /*private void bindImageSizeToWindow() {
        // بررسی null نبودن Scene و Window
        if (backgroundImage.getScene() != null && backgroundImage.getScene().getWindow() != null) {
            Stage stage = (Stage) backgroundImage.getScene().getWindow();

            // اتصال fitHeight و fitWidth از ImageView به ارتفاع و عرض Stage
            backgroundImage.fitHeightProperty().bind(stage.heightProperty());
            backgroundImage.fitWidthProperty().bind(stage.widthProperty());
            backgroundImage.setPreserveRatio(false); // این خط را اضافه کنید
        } else {
            System.err.println("Scene یا Window هنوز تنظیم نشده است.");
        }
    }*/

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText(now.format(formatter));
    }

    private void setupGridPaneHoverBehavior() {
        // تنظیم رویدادهای موس برای loanOptions
        loanOptions.setOnMouseEntered(event -> {
            loanOptions.setVisible(true);
            cancelPauseTransition();
        });

        loanOptions.setOnMouseExited(event -> {
            startPauseTransition(() -> loanOptions.setVisible(false));
        });

        /*bank.setOnMouseEntered(event -> {
            bank.setVisible(true);
            cancelPauseTransition();
        });*/

        /*bank.setOnMouseExited(event -> {
            startPauseTransition(() -> bank.setVisible(false));
            startPauseTransition(() -> ancktext.setVisible(false));
            startPauseTransition(() -> bank1.setVisible(false));
            startPauseTransition(() -> bank2.setVisible(false));
            startPauseTransition(() -> bank3.setVisible(false));
            startPauseTransition(() -> bank4.setVisible(false));
            startPauseTransition(() -> bank5.setVisible(false));
            startPauseTransition(() -> bank6.setVisible(false));
            startPauseTransition(() -> bank7.setVisible(false));
            startPauseTransition(() -> bank8.setVisible(false));
            startPauseTransition(() -> bank9.setVisible(false));
            startPauseTransition(() -> bank10.setVisible(false));
            startPauseTransition(() -> bank11.setVisible(false));
            startPauseTransition(() -> bank12.setVisible(false));
        });*/
    }

    private void startPauseTransition(Runnable action) {
        pauseTransition = new PauseTransition(Duration.seconds(0.5)); // تأخیر 0.5 ثانیه
        pauseTransition.setOnFinished(event -> action.run());
        pauseTransition.play();
    }

    private void cancelPauseTransition() {
        if (pauseTransition != null) {
            pauseTransition.stop();
        }
    }

    /*@FXML
    public void showbankoption (){
        bank.setVisible(true);
        loanOptions.setVisible(false);
    }*/

    /*@FXML
    public void showServicesOptions() {
        loanOptions.setVisible(false);
        contactOptions.setVisible(false);
        bank.setVisible(false);
    }*/

    @FXML
    public void showLoanOptions() {
        loanOptions.setVisible(true);
    }

    @FXML
    public void hideLoanOptions() {
        startPauseTransition(() -> loanOptions.setVisible(false));
    }


    /*@FXML
    public void hidebankoption(){
        startPauseTransition(() -> bank.setVisible(false));
    }*/

    @FXML
    public void hideAllOptions() {
        loanOptions.setVisible(false);
    }



    @FXML
    public void handleLoanOption() {
        System.out.println("Loan option clicked!");
    }

    /*@FXML
    public void keepbankoption(){
        bank.setVisible(true);
    }*/

    @FXML
    public void keepLoanOptionsVisible() {
        loanOptions.setVisible(true);
    }

    @FXML
    private void bank1(){
        ancktext.setVisible(true);
        bank1.setVisible(true);
        bank11.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank2(){
        ancktext.setVisible(true);
        bank2.setVisible(true);
        bank1.setVisible(false);
        bank11.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank3(){
        ancktext.setVisible(true);
        bank3.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank11.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank4(){
        ancktext.setVisible(true);
        bank4.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank11.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank5(){
        ancktext.setVisible(true);
        bank5.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank11.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank6(){
        ancktext.setVisible(true);
        bank6.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank11.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank7(){
        ancktext.setVisible(true);
        bank7.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank11.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank8(){
        ancktext.setVisible(true);
        bank8.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank11.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank9(){
        ancktext.setVisible(true);
        bank9.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank11.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank10(){
        ancktext.setVisible(true);
        bank10.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank11.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank11(){
        ancktext.setVisible(true);
        bank11.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank12.setVisible(false);
    }
    @FXML
    private void bank12(){
        ancktext.setVisible(true);
        bank12.setVisible(true);
        bank1.setVisible(false);
        bank2.setVisible(false);
        bank3.setVisible(false);
        bank4.setVisible(false);
        bank5.setVisible(false);
        bank6.setVisible(false);
        bank7.setVisible(false);
        bank8.setVisible(false);
        bank9.setVisible(false);
        bank10.setVisible(false);
        bank11.setVisible(false);
    }
    public void openNewWindow(String fxmlFile, String title, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 1535, 790);


            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();


            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HomeToLoginpage(ActionEvent actionEvent) {
        openNewWindow("loginpage.fxml","loginpage",actionEvent);
    }

    public void profile(ActionEvent actionEvent) {
        if(loginID) {
            openNewWindow("profile1.fxml", "profile", actionEvent);
        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("please first login");
            alert.showAndWait();
            openNewWindow("loginpage.fxml", "loginpage", actionEvent);
        }
    }
    @FXML
    private void tel (){
        String url = "https://t.me/AureousBank";
        try{Desktop.getDesktop().browse(new URI(url));}
        catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private void insta() {
        String urlinsta = "https://www.instagram.com/auruse_bank?igsh=MTUwNTRxdDk2cnpzcg==";
        try{Desktop.getDesktop().browse(new URI(urlinsta));}
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void btn1(){
        acc1.setVisible(true);
        acc2.setVisible(false);
        acc3.setVisible(false);
        acc4.setVisible(false);
        acc5.setVisible(false);
        acc6.setVisible(false);
    }
    @FXML
    private void btn2(){
        acc1.setVisible(false);
        acc2.setVisible(true);
        acc3.setVisible(false);
        acc4.setVisible(false);
        acc5.setVisible(false);
        acc6.setVisible(false);
    }
    @FXML
    private void btn3(){
        acc1.setVisible(false);
        acc2.setVisible(false);
        acc3.setVisible(true);
        acc4.setVisible(false);
        acc5.setVisible(false);
        acc6.setVisible(false);
    }
    @FXML
    private void btn4(){
        acc1.setVisible(false);
        acc2.setVisible(false);
        acc3.setVisible(false);
        acc4.setVisible(true);
        acc5.setVisible(false);
        acc6.setVisible(false);
    }
    @FXML
    private void btn5(){
        acc1.setVisible(false);
        acc2.setVisible(false);
        acc3.setVisible(false);
        acc4.setVisible(false);
        acc5.setVisible(true);
        acc6.setVisible(false);
    }
    @FXML
    private void btn6(){
        acc1.setVisible(false);
        acc2.setVisible(false);
        acc3.setVisible(false);
        acc4.setVisible(false);
        acc5.setVisible(false);
        acc6.setVisible(true);
    }
    @FXML
    private void FAQ(){
        double position = 9.5/10.0;
        scrol.setVvalue(position);
    }
    @FXML
    private void update (){
        try{
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), updatebtn);
            rotateTransition.setByAngle(360);
            rotateTransition.setCycleCount(5);
            rotateTransition.play();
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String url = "https://www.tgju.org/%D9%82%DB%8C%D9%85%D8%AA-%D8%AF%D9%84%D8%A7%D8%B1";
                    Document doc = Jsoup.connect(url).get();
                    Elements elements = doc.select("td.nf");
                    String dollar = elements.get(0).text();
                    Dolarstring = elements.get(0).text().replace(",","");
                    Dolarint = Integer.parseInt(Dolarstring);
                    Platform.runLater(() -> dollor.setText(dollar));

                    String url1 = "https://www.tgju.org/profile/price_eur";
                    Document doc1 = Jsoup.connect(url1).get();
                    Elements elements1 = doc1.select("td.text-left");
                    String euro = elements1.get(0).text();
                    Eurostring = elements1.get(0).text().replace(",","");
                    Euroint = Integer.parseInt(Eurostring);
                    Platform.runLater(() -> Euro.setText(euro));
                    return null;
                }
            };
            new Thread(task).start();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private void change(){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), changebtn);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
        if(Dolarint>0){
            message.setVisible(false);
            if(!text1.getText().isEmpty() && text2.getText().isEmpty()){
                int tex1int = Integer.parseInt(text1.getText());
                int result1 = tex1int * Dolarint ;
                String res1 = String.valueOf(result1);
                text2.setText(res1);
            }
            if(text1.getText().isEmpty() && !text2.getText().isEmpty()){
                int tex2int = Integer.parseInt(text2.getText());
                int result2 = tex2int/Dolarint ;
                String res2 = String.valueOf(result2);
                text1.setText(res2);
            }
        }
        else{
            message.setVisible(true);
        }
    }
    //انیمیشن برای کوچک شدن
    private void applyHoverEffect(Button button) {
        Timeline hoverIn = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(button.translateYProperty(), +5),
                        new KeyValue(button.scaleXProperty(), 0.9),
                        new KeyValue(button.scaleYProperty(), 0.9))
        );

        Timeline hoverOut = new Timeline(
                new KeyFrame(Duration.millis(200),
                        new KeyValue(button.translateYProperty(), 0),
                        new KeyValue(button.scaleXProperty(), 1),
                        new KeyValue(button.scaleYProperty(), 1))
        );
        button.setOnMouseEntered(e -> hoverIn.play());
        button.setOnMouseExited(e -> hoverOut.play());
    }
    //اطلاعیه ها
    private void loadTopics() {
        try {
            Scanner scan = new Scanner(new File("topics.txt"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, ",");
                if (tokens.hasMoreTokens()) {
                    String topic = tokens.nextToken();
                    String fxmlFile = tokens.hasMoreTokens() ? tokens.nextToken() : "";
                    listTopics.getItems().add(topic);
                    topicFXMLMap.put(topic, fxmlFile);
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("خطا در خواندن فایل موضوعات.");
        }
    }

    private void openFXML(String topic) {
        String fxmlFile = topicFXMLMap.get(topic);
        if (fxmlFile == null || fxmlFile.isEmpty()) {
            System.out.println("فایل FXML برای این موضوع مشخص نشده است.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setTitle(topic);
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            System.out.println("خطا در باز کردن FXML: " + e.getMessage());
        }
    }

    public void loanBtn(ActionEvent actionEvent) {
        if(loginID){
            openNewWindow("Vam.fxml","Loan",actionEvent);
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("please login first");
            alert.showAndWait();
        }
    }
    @FXML
    private void start(){
        circle.setVisible(false);
        btnm.setVisible(false);
        chat.setVisible(true);
    }
    @FXML
    private void close(){
        circle.setVisible(true);
        btnm.setVisible(true);
        chat.setVisible(false);
    }
}