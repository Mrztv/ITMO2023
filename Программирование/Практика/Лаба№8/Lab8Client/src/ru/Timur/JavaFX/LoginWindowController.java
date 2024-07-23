package ru.Timur.JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.Timur.Command.AuthCommand;
import ru.Timur.Exceptions.WrongPasswordExeption;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {
    @FXML
    public Button signInButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("LoginWindow");
        stage.show();
    }

    public void loginEvent(){
        try {
            Stage loginStage;
            loginStage = (Stage) passwordField.getScene().getWindow();
            AuthCommand authCommand = new AuthCommand();

            //throw WrongPasswordException
            authCommand.auth(loginField.getText(), passwordField.getText());
            loginStage.hide();

            FXMLLoader tableLoader = new FXMLLoader(Paths.get("./src/resource/ru/Timur/TableView.fxml").toUri().toURL());
            Stage tableViewStage = tableLoader.load();
            //tableStage.show();

        } catch (IOException e){
            System.out.println(e);
        } catch (WrongPasswordExeption e){
            passwordField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT))) ;
            System.out.println(e);
        }
    }

}
