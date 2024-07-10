package ru.Timur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.Flow;

public class MainTest extends Application{
    public static void main(String[] args) {

        System.out.println("Test");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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



        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
        stage.setMinWidth(200);
        stage.setMinHeight(200);

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
        stage.setScene(scene);
        stage.show();

        push_me_button.setOnAction(eh -> label.setText(Math.round(stage.getWidth()) + "   " + Math.round(stage.getHeight())));
        */
    }
}
