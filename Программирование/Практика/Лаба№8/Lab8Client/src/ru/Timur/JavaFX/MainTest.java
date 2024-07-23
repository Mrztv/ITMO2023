package ru.Timur.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.Timur.Command.*;
import ru.Timur.Exceptions.WrongPasswordExeption;
import ru.Timur.Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;

public class MainTest extends Application{
    public static void main(String[] args) {

        System.out.println("Test");
        launch();
    }

    @Override
    public void start(Stage loginStage) throws Exception {

        Path path = Paths.get("./src/resource/ru/Timur/LoginWindow.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        loginStage = loader.load();







//        Button loginButton = (Button) loginStage.getScene().lookup("#signInButton");
//        Stage finalLoginStage = loginStage;
//        loginButton.setOnAction(actionEvent -> {
//            try {
//                AuthCommand authCommand = new AuthCommand();
//                authCommand.auth(((TextField) loginScene.lookup("#loginField")).getText(), ((PasswordField) loginScene.lookup("#passwordField")).getText());
//                finalLoginStage.hide();
//                tableStage.show();
//                getObject(tableStage);
//            } catch (IOException e){
//                System.out.println(e);
//            } catch (WrongPasswordExeption e){
//                ((PasswordField) loginScene.lookup("#passwordField")).setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
//                System.out.println(e);
//            }
//        });


        /*

        GridPane body = new GridPane();

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(33);
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        columnConstraints2.setPercentWidth(34);
        ColumnConstraints columnConstraints3 = new ColumnConstraints();
        columnConstraints3.setPercentWidth(33);

        body.getColumnConstraints().addAll(columnConstraints, columnConstraints2, columnConstraints3);
        body.getRowConstraints().addAll(new RowConstraints(), new RowConstraints(), new RowConstraints());



        loginStage.setMaxWidth(600);
        loginStage.setMaxHeight(400);
        loginStage.setMinWidth(200);
        loginStage.setMinHeight(200);

        Pane pane1 = new FlowPane();
        pane1.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Label label = new Label("Hello world");
        pane1.getChildren().add(label);


        Pane flowPane2 = new FlowPane();
        flowPane2.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(4))));
        Button push_me_button = new Button("Push me");
        flowPane2.getChildren().add(push_me_button);

        GridPane.setConstraints(pane1, 1, 1);

        body.getChildren().addAll(pane1, flowPane2);


        Scene scene = new Scene(body, 600, 400);
        loginStage.setScene(scene);
        loginStage.show();

        push_me_button.setOnAction(eh -> label.setText(Math.round(loginStage.getWidth()) + "   " + Math.round(loginStage.getHeight())));
        */
    }



    private void getObject(Stage tableStage){
        TableView tableView = (TableView) tableStage.getScene().lookup("#spacemarinesTable");
        tableView.getColumns();
    }

    private void executeCommand(Stage stage)
    {
        Map<String, Command> commands = new Invoker(System.in, new Validation()).commands;


        String input = ((TextField) stage.getScene().lookup("#commandField")).getText();
        if(!input.isBlank() || !input.isEmpty())
        {
            String[] words = input.trim().split(" ");

            String command = words[0];

            if (words.length > 1) {
                Arguments.addArg(words[1]);
            }

            //start
            try {

                if (commands.containsKey(words[0])) {
                    Network network = new Network(InetAddress.getLocalHost());
                    if (words[0].equals("exit")) {
                        System.exit(111);
                    }

                    if (((Command)commands.get(words[0])).execute()) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(commands.get(words[0]));
                        network.send(byteArrayOutputStream);

                        try {
                            network.recive();
                        } catch (WrongPasswordExeption e){
                            System.out.println(e);
                        }
                    }

                    Arguments.clearArgs();
                }
            } catch (UnknownHostException e){
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);;
            }
        }

    }
}
