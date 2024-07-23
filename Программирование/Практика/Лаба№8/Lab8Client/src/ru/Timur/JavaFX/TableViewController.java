package ru.Timur.JavaFX;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.Timur.*;
import ru.Timur.Command.*;
import ru.Timur.Exceptions.WrongPasswordExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TableViewController implements Initializable {

    @FXML
    private TableColumn<SpaceMarine, String> achievementsColumn;

    @FXML
    private TableColumn<SpaceMarine, AstartesCategory> categoryColumn;

    @FXML
    private TableColumn<SpaceMarine, Number> chapterMarinesCountColumn;

    @FXML
    private TableColumn<SpaceMarine, String> chapterNameColumn;

    @FXML
    private TextField commandField;

    @FXML
    private Button commandSendButton;

    @FXML
    private TableColumn<SpaceMarine, Number> coordinateXColumn;

    @FXML
    private TableColumn<SpaceMarine, Number> coordinateYColumn;

    @FXML
    private TableColumn<SpaceMarine, ZonedDateTime> creationDateColumn;

    @FXML
    private TableColumn<SpaceMarine, Number> healthColumn;

    @FXML
    private TableColumn<SpaceMarine, Number> idColumn;

    @FXML
    private TableColumn<SpaceMarine, Boolean> loyalColumn;

    @FXML
    private TableColumn<SpaceMarine, String> nameColumn;

    @FXML
    private TableView<SpaceMarine> spaceMarinesTable;

    @FXML
    private Stage stage;

    @FXML
    private Label userLabel;

    @FXML
    private TableColumn<SpaceMarine, String> usernameColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setTitle("TableView");

        userLabel.setText(User.getUser().getUserName());

        System.out.println(spaceMarinesTable.columnResizePolicyProperty());

        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()));
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        coordinateXColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCoordinates().getX()));
        coordinateYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCoordinates().getY()));

        System.out.println(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(ZonedDateTime.now()));

        creationDateColumn.setCellFactory(column -> new TableCell<SpaceMarine, ZonedDateTime>() {
            @Override
            protected void updateItem(ZonedDateTime item, boolean empty) {
                if(empty) {
                    setText(null);
                }
                else {
                    setText(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z").format(item));
                }
            }
        });

        creationDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreationDate()));

        healthColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getHealth()));
        loyalColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isLoyal()));
        achievementsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAchievements()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<AstartesCategory>(cellData.getValue().getCategory()));
        chapterNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChapter().getName()));
        chapterMarinesCountColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getChapter().getMarinesCount()));

        updateTable();
        stage.show();

    }

    public void updateTable(){
        spaceMarinesTable.getItems().clear();
        Command show = new ShowCommand(new Validation());
        try {
            Network network = new Network(InetAddress.getLocalHost());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(show);
            network.send(byteArrayOutputStream);

            ClientData answer = network.getAnswer();
            int variant = answer.getVariant();

            if (variant == 1){
                Set<SpaceMarine> spaceMarineSet  = answer.getCollection();
                spaceMarinesTable.getItems().addAll(spaceMarineSet);


            }else throw new IOException();



        } catch (IOException | WrongPasswordExeption e) {
            System.out.println(e);
        }
    }




    public void sendEvent(){
        String command = commandField.getText();
        execute(command);
    }

    private void execute(String input)
    {
        Map<String, Command> commands = new Invoker(System.in, new Validation()).commands;

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
                            updateTable();
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
