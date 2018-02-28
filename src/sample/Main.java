package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

public class Main extends Application {
    private static int index = 0;
    private static String access_token = null;

    //***************
    private static int logout_time = 60000;
    private static long currentTime = System.currentTimeMillis();
    private static long endTime = System.currentTimeMillis() + logout_time;
    //***************

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group group = new Group();

        Rectangle rectangle1 = new Rectangle(0, 0, 600, 700);
        rectangle1.setFill(Color.rgb(139, 58, 58));

        Rectangle rectangle2 = new Rectangle(610, 0, 300, 700);
        rectangle2.setFill(Color.rgb(28, 28, 28));

        Rectangle rectangle3 = new Rectangle(100, 200, 400, 200);
        rectangle3.setFill(Color.rgb(128, 0, 0));

        Text text1 = new Text(220, 300, "REED &");
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
        text1.setFill(Color.WHITE);

        Text text2 = new Text(220, 320, "GREENOUGH");
        text2.setFont(Font.font("Arial Black", 20));
        text2.setFill(Color.WHITE);

        Path path = new Path();
        MoveTo moveTo = new MoveTo(210, 270);
        LineTo line1 = new LineTo(375, 270);
        LineTo line2 = new LineTo(375, 330);
        LineTo line3 = new LineTo(210, 330);
        LineTo line4 = new LineTo(210, 270);
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4);
        path.setStroke(Color.WHITE);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300, 500);
        gridPane.setPadding(new Insets(100, 0, 10, 630));
        gridPane.setVgap(2);
        gridPane.setHgap(2);
        gridPane.setAlignment(Pos.CENTER);

        GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);

        TextField userTextField = new TextField();
        userTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    userTextField.setText(newValue.replaceAll("[^\\d]", ""));
                    index--;
                } else index++;
            }
        });

        userTextField.setStyle(
                "-fx-background-color: black; " +
                        "-fx-fill-height: 1; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 0;" +
                        "-fx-border-color: dimgray;"
        );

        Button btn0 = new Button("    0    ");
        Button btn1 = new Button("    1    ");
        Button btn2 = new Button("    2    ");
        Button btn3 = new Button("    3    ");
        Button btn4 = new Button("    4    ");
        Button btn5 = new Button("    5    ");
        Button btn6 = new Button("    6    ");
        Button btn7 = new Button("    7    ");
        Button btn8 = new Button("    8    ");
        Button btn9 = new Button("    9    ");

        Button clear = new Button("        CLEAR       ");
        Button backspace = new Button("   BACKSPACE   ");

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userTextField.clear();
                index = 0;
            }
        });

        clear.setStyle(
                "-fx-background-color: dimgray; " +
                        "-fx-fill-height: 1; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 0;" +
                        "-fx-border-color: black;");

        backspace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (index != 0) {
                    userTextField.deleteText(index - 1, index);
                    index -= 2;
                }
            }
        });

        backspace.setStyle(
                "-fx-background-color: dimgray; " +
                        "-fx-fill-height: 1; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 0;" +
                        "-fx-border-color: black;");

        List<Button> listButton = new ArrayList<>();
        listButton.add(btn0);
        listButton.add(btn1);
        listButton.add(btn2);
        listButton.add(btn3);
        listButton.add(btn4);
        listButton.add(btn5);
        listButton.add(btn6);
        listButton.add(btn7);
        listButton.add(btn8);
        listButton.add(btn9);

        for (Button btn : listButton) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    userTextField.insertText(index, Integer.toString(listButton.indexOf(btn)));
                }
            });

            btn.setStyle(
                    "-fx-background-color: grey; " +
                            "-fx-fill-height: 1; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 20px; " +
                            "-fx-border-width: 2; " +
                            "-fx-background-radius: 0;" +
                            "-fx-font-weight: bold;");
        }

        gridPane.add(userTextField, 1, 1, 3, 1);
        gridPane2.add(clear, 1, 1);
        gridPane2.add(backspace, 2, 1);
        gridPane.add(gridPane2, 1, 2, 3, 1);
        gridPane.add(btn1, 1, 3);
        gridPane.add(btn2, 2, 3);
        gridPane.add(btn3, 3, 3);
        gridPane.add(btn4, 1, 4);
        gridPane.add(btn5, 2, 4);
        gridPane.add(btn6, 3, 4);
        gridPane.add(btn7, 1, 5);
        gridPane.add(btn8, 2, 5);
        gridPane.add(btn9, 3, 5);
        gridPane.add(btn0, 2, 6);

        Label time = new Label("TIME");
        Label hourLabel = new Label();
        Label minLabel = new Label();
        Label secLabel = new Label();
        Label marker = new Label();

        GridPane gridPane3 = new GridPane();
        gridPane3.setMinSize(300, 50);
        gridPane3.setPadding(new Insets(10, 0, 10, 630));
        gridPane3.setVgap(2);
        gridPane3.setHgap(2);
        gridPane3.setAlignment(Pos.CENTER);

        hourLabel.setStyle(
                "-fx-font-size: 40px; " +
                        "-fx-text-fill: lightgray; " +
                        "-fx-font-weight: bold;"
        );

        minLabel.setStyle(
                "-fx-font-size: 40px; " +
                        "-fx-text-fill: lightgray; " +
                        "-fx-font-weight: bold;"
        );

        secLabel.setStyle(
                "-fx-font-size: 40px; " +
                        "-fx-text-fill: dimgray; " +
                        "-fx-font-weight: bold;"
        );

        marker.setStyle(
                "-fx-font-size: 20px; " +
                        "-fx-text-fill: lightgray; " +
                        "-fx-font-weight: bold;"
        );

        time.setStyle("-fx-text-fill: gray;");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Date date = new Date();
                        SimpleDateFormat formatForTimeNow = new SimpleDateFormat("hh mm ss a");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                String[] dateAr = formatForTimeNow.format(date).split(" ");
                                hourLabel.setText(dateAr[0] + " : ");
                                minLabel.setText(dateAr[1] + " : ");
                                secLabel.setText(dateAr[2] + " ");
                                marker.setText(dateAr[3]);
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        gridPane3.add(time, 1, 1);
        gridPane3.add(hourLabel, 1, 2);
        gridPane3.add(minLabel, 2, 2);
        gridPane3.add(secLabel, 3, 2);
        gridPane3.add(marker, 4, 2);
        thread.setDaemon(true);
        thread.start();

        GridPane gridPane4 = new GridPane();
        gridPane4.setMinSize(300, 50);
        gridPane4.setPadding(new Insets(490, 0, 10, 630));
        gridPane4.setVgap(10);
        gridPane4.setHgap(2);
        gridPane4.setAlignment(Pos.CENTER);

        Button facebookButton = new Button("LOG IN WITH FACEBOOK");

        facebookButton.setStyle(
                "-fx-background-color: royalblue; " +
                        "-fx-fill-height: 1; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 20px; " +
                        "-fx-border-width: 2; " +
                        "-fx-background-radius: 0;" +
                        "-fx-font-weight: bold;");

        //String facebookID = "182134619012243";
        facebookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final WebView webView = new WebView();
                final WebView webView2 = new WebView();
                final WebEngine webEngine = webView.getEngine();
                final WebEngine webEngine2 = webView2.getEngine();
                StringBuilder userId = new StringBuilder();
                //StringBuilder uName = new StringBuilder();
                StringBuilder userName = new StringBuilder();
                StringBuilder userBurthday = new StringBuilder();
                StringBuilder userGender = new StringBuilder();
                StringBuilder userPicture = new StringBuilder();
                final String url = "https://www.facebook.com/v2.10/dialog/oauth?client_id=182134619012243&response_type=token&redirect_uri=https://www.facebook.com/connect/login_success.html";

                String REDIRECT_URL = "https://www.facebook.com/connect/login_success.html";

                webEngine.load(url);

                webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
                    public void handle(WebEvent<String> event) {
                        if (event.getSource() instanceof WebEngine) {
                            WebEngine we = (WebEngine) event.getSource();
                            String location = we.getLocation();
                            if (location.startsWith(REDIRECT_URL) && location.contains("access_token")) {
                                try {
                                    URL url = new URL(location);
                                    String[] params = url.getRef().split("&");
                                    Map<String, String> map = new HashMap<String, String>();
                                    for (String param : params) {
                                        String name = param.split("=")[0];
                                        String value = param.split("=")[1];
                                        map.put(name, value);
                                    }
                                    //System.out.println("The access token: " + map.get("access_token"));
                                    access_token = map.get("access_token");
                                    webEngine2.load("https://graph.facebook.com/v2.5/me?fields=id,name,birthday,gender,picture&access_token=" + access_token);

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

                webEngine2.getLoadWorker().stateProperty().addListener(
                        new ChangeListener<Worker.State>() {
                            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

                                if (newState == Worker.State.SUCCEEDED) {

                                    //***************
                                    Thread logoutThread = new Thread() {
                                        @Override
                                        public void run() {
                                            //System.out.println("logoutThread1");
                                            while (currentTime <= endTime) currentTime = System.currentTimeMillis();
                                            currentTime = System.currentTimeMillis();
                                            if (currentTime > endTime) {
                                                //System.out.println("logoutThread2");
                                                access_token = null;
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        File file = new File("file.txt");
                                                        webView.getEngine().loadContent(
                                                                "<html><body bgcolor=\"#FFFFE0\">" +
                                                                        "<br><br><h1>YOU ARE LOGOUT!!!</h1>" +
                                                                        "</body></html>", "text/html");
                                                    }
                                                });
                                            }
                                        }
                                    };
                                    logoutThread.setDaemon(true);
                                    logoutThread.start();
                                    //*************

                                    Document doc = webEngine2.getDocument();
                                    try {
                                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                                        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                                        /*transformer.transform(new DOMSource(doc),
                                                new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));*/
                                        transformer.transform(new DOMSource(doc),
                                                new StreamResult(new OutputStreamWriter(new FileOutputStream(new File("file.txt")), "UTF-8")));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        FileInputStream file = new FileInputStream("file.txt");
                                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
                                        StringBuilder lineBuf = new StringBuilder();
                                        String line;
                                        int lineIndex = 0;
                                        while ((line = bufReader.readLine()) != null) {
                                            if ((line != null) && ((lineIndex >= 5) && (lineIndex <= 8))) {
                                                lineBuf.append(line.replace("\"", "")
                                                        .replace(":", "")
                                                        .replace(",", "")
                                                        .trim())
                                                        .append(System.lineSeparator());
                                            }
                                            if ((line != null) && (lineIndex == 12)) {
                                                lineBuf.append(line.replace("\"", "")
                                                        .replace(",", "")
                                                        .trim())
                                                        .append(System.lineSeparator());
                                            }
                                            lineIndex++;
                                        }

                                        //System.out.println(lineBuf);
                                        String[] lines = (lineBuf.toString()).split("\\r?\\n");

                                        for (int i = 0; i < lines.length; i++) {
                                            String[] words = lines[i].split(" ");
                                            if (i == 0) userId.append(words[1]);
                                            if (i == 1) {
                                                ConverterToUnicode c = new ConverterToUnicode();
                                                userName.append(c.convert(words[1])).append(" ").append(c.convert(words[2]));
                                                //System.out.println(userName.toString());
                                            }
                                            if (i == 2) userBurthday.append(words[1]);
                                            if (i == 3) userGender.append(words[1]);
                                            if (i == 4) userPicture.append(words[1]);
                                        }
                                        webView.getEngine().loadContent(
                                                "<html><body bgcolor=\"#FFFFE0\">" +
                                                        "<p><img src=" + userPicture + " align=right border=\"0\" width=\"200\" height=\"200\"></p>" +
                                                        "<br><br><h3>user_Id : " + userId + "</h3>" +
                                                        "<br><h3>user_Name : " + userName + "</h3>" +
                                                        "<br><h3>user_BurthDay : " + userBurthday + "</h3>" +
                                                        "<br><h3>user_Gender : " + userGender + "</h3>" +
                                                        "</body></html>", "text/html");
                                        //System.out.println(userId + " " + userName + " " + userBurthday + " " + userGender);
                                        bufReader.close();
                                        //file = new FileInputStream("file.txt");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                final BorderPane root = new BorderPane();
                root.setCenter(webView);
                primaryStage.setScene(new Scene(root, 800, 700));
                primaryStage.show();
            }
        });

        Image imageClock = new Image("sample\\clock.png");

        Button clockButton = new Button("    CLOCK IN/OUT       ");
        clockButton.graphicProperty().setValue(new ImageView(imageClock));
        clockButton.setStyle(
                "-fx-background-color: seagreen; " +
                        "-fx-fill-height: 1; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 20px; " +
                        "-fx-border-width: 2; " +
                        "-fx-background-radius: 0;" +
                        "-fx-font-weight: bold;");

        clockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(userTextField.getText().equals("")))
                    logout_time = Integer.valueOf(userTextField.getText());
                else logout_time = 60000;

                //***************
                endTime = System.currentTimeMillis() + logout_time;
                //***************
            }
        });

        gridPane4.add(facebookButton, 1, 10, 4, 1);
        gridPane4.add(clockButton, 1, 11, 4, 1);

        ObservableList list = group.getChildren();
        list.add(rectangle1);
        list.add(rectangle2);
        list.add(rectangle3);
        list.add(text1);
        list.add(text2);
        list.add(path);
        list.add(gridPane4);

        list.add(gridPane);
        list.add(gridPane3);

        group = new Group(list);
        Scene scene = new Scene(group, 910, 700);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("Example with JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
