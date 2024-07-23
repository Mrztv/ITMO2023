//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.Timur.AstartesCategory;
import ru.Timur.Chapter;
import ru.Timur.Coordinates;
import ru.Timur.JavaFX.TableViewController;
import ru.Timur.OpenedFileSet;
import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;
import ru.Timur.Exceptions.NonValidFileElementException;

public class Validation implements Serializable {
    static final long serialVersionUID = 1L;
    private boolean isSystemIn = true;

    private AtomicBoolean isDone = new AtomicBoolean(false);

    private boolean validationReady = false;

    public Validation() {
    }

    public SpaceMarine add() throws NonValidFileElementException, IOException, InputMismatchException {
        try{
            SpaceMarine spaceMarine = createElemFromInput();
            while(!isDone.get());
            return spaceMarine != null ? spaceMarine : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Long remove_by_id() {
        if (Arguments.getSize() == 1) {
            try {
                Long id = Long.parseLong(Arguments.getArg());
                return id;
            } catch (NumberFormatException var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public SpaceMarine update() throws IOException, NonValidFileElementException {
        Long id = null;
        if (Arguments.getSize() == 1) {
            try {
                id = Long.parseLong(Arguments.getArg());
            } catch (NumberFormatException var3) {
                return null;
            }
        }

        SpaceMarine spaceMarine = this.createElemFromInput();
        spaceMarine.setId(id);
        return spaceMarine;
    }

    public void exit() {
        System.exit(1);
    }

    public String execute_script() {
        try {
            if (Arguments.getSize() == 1) {
                String filePath = Arguments.getArg();
                Path path = Paths.get(filePath);
                if (Files.exists(path, new LinkOption[0]) && Files.isReadable(path)) {
                    if (OpenedFileSet.inSet(path)) {
                        System.out.println("Файл \"" + path.toAbsolutePath().toString() + "\" не выполнен");
                        return null;
                    } else {
                        OpenedFileSet.add(path);
                        this.isSystemIn = false;
                        InputStream inputStream = new FileInputStream(filePath);
                        BufferedReader bufferedReader = StreamReader.getBufferedReader();
                        Invoker invoker = new Invoker(inputStream, this);
                        invoker.readStream();
                        StreamReader.setBufferedReader(bufferedReader);
                        OpenedFileSet.remove(path);
                        this.isSystemIn = true;
                        return filePath;
                    }
                } else {
                    System.out.println("Файл не найден");
                    return null;
                }
            } else {
                System.out.println("Неправильный аргумент");
                return null;
            }
        } catch (RuntimeException | FileNotFoundException var6) {
            System.out.println(var6.getMessage());
            return null;
        }
    }

    public SpaceMarine createElemFromInput() throws NoSuchElementException, IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Paths.get("./src/resource/ru/Timur/CreateNewElementWindow.fxml").toUri().toURL());
        Parent root = fxmlLoader.load();
        Stage createElementStage = new Stage();
        createElementStage.setTitle("New Element");
        Scene createElementScene = new Scene(root);

        ChoiceBox<String> categoryChoiceBox = (ChoiceBox<String>) createElementScene.lookup("#categoryChoiceBox");
        categoryChoiceBox.getItems().addAll("ASSAULT", "INCEPTOR", "TERMINATOR", "CHAPLAIN", "APOTHECARY");
        categoryChoiceBox.setValue("ASSAULT");

        createElementStage.setScene(createElementScene);


        AtomicReference<SpaceMarine> spaceMarine = new AtomicReference<SpaceMarine>();

        Button readyButton = (Button) createElementScene.lookup("#confirmButton");

        Border errorBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT));

        AtomicBoolean flag = new AtomicBoolean(false);
        
        readyButton.setOnAction((actionEvent) -> {
            flag.set(false);
            String bName = null;
            int bCoordinatesX = 0;
            double bCoordinatesY = 0d;
            float bHeath = 0f;
            boolean bLoyal = false;
            String bAchievement = null;
            AstartesCategory bCategory = null;
            String bChapterName = null;
            long bChapterMarinesCount = 0L;

            TextField bNameField = (TextField) createElementScene.lookup("#nameField");
            TextField bCoordinatesXField = (TextField) createElementScene.lookup("#coordinatesXField");
            TextField bCoordinatesYField = (TextField) createElementScene.lookup("#coordinatesYField");
            TextField bHeathField = (TextField) createElementScene.lookup("#healthField");
            CheckBox bLoyalBox = (CheckBox) createElementScene.lookup("#loyalCheckBox");
            TextField bAchievementField = (TextField) createElementScene.lookup("#achievementField");
            ChoiceBox<String> bCategoryBox = (ChoiceBox<String>) createElementScene.lookup("#categoryChoiceBox");
            TextField bChapterNameField = (TextField) createElementScene.lookup("#chapterNameField");
            TextField bChapterMarinesCountField = (TextField) createElementScene.lookup("#chapterMarinesCountField");

            bName = ((TextField) createElementScene.lookup("#nameField")).getText();
            try {
                bCoordinatesX = Integer.parseInt(((TextField) createElementScene.lookup("#coordinatesXField")).getText());

            } catch (NumberFormatException e) {
                bCoordinatesXField.setBorder(errorBorder);
                flag.set(true);
            }
            try {
                bCoordinatesY = Double.parseDouble(((TextField) createElementScene.lookup("#coordinatesYField")).getText());

            } catch (NumberFormatException e) {
                bCoordinatesYField.setBorder(errorBorder);
                flag.set(true);
            }
            try {
                bHeath = Float.parseFloat(((TextField) createElementScene.lookup("#healthField")).getText());

            } catch (NumberFormatException e) {
                bHeathField.setBorder(errorBorder);
                flag.set(true);
            }
            bLoyal = ((CheckBox) createElementScene.lookup("#loyalCheckBox")).isSelected();
            bAchievement = ((TextField) createElementScene.lookup("#achievementField")).getText();
            String bCategoryText = ((ChoiceBox<String>) createElementScene.lookup("#categoryChoiceBox")).getValue();
            bCategory = null;
            bChapterName = ((TextField) createElementScene.lookup("#chapterNameField")).getText();

            try {
                bChapterMarinesCount = Long.parseLong(((TextField) createElementScene.lookup("#chapterMarinesCountField")).getText());

            } catch (NumberFormatException e) {
                bChapterMarinesCountField.setBorder(errorBorder);
                flag.set(true);
            }


            if (bName.equals("null") || bName.isEmpty()) {
                System.out.println("Неправильный ввод");
                bNameField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            if (bCoordinatesX == 0) {
                System.out.println("Неправильный ввод");
                bCoordinatesXField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            if (bCoordinatesY == 0 || bCoordinatesY < -162) {
                System.out.println("Неправильный ввод");
                bCoordinatesYField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            if (bHeath < 0) {
                System.out.println("Неправильный ввод");
                bHeathField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            switch (bCategoryText) {
                case "ASSAULT":
                    bCategory = AstartesCategory.ASSAULT;
                    break;
                case "INCEPTOR":
                    bCategory = AstartesCategory.INCEPTOR;
                    break;
                case "TERMINATOR":
                    bCategory = AstartesCategory.TERMINATOR;
                    break;
                case "CHAPLAIN":
                    bCategory = AstartesCategory.CHAPLAIN;
                    break;
                case "APOTHECARY":
                    bCategory = AstartesCategory.APOTHECARY;
                    break;
                default:
                    bCategoryBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    flag.set(true);
            }

            if (bChapterName.equals("null") || bChapterName.isEmpty() || bChapterName.isBlank()) {
                System.out.println("Неправильный ввод");
                bChapterNameField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            if (bChapterMarinesCount < 0L || bChapterMarinesCount > 1000L) {
                System.out.println("Неправильный ввод");
                bChapterMarinesCountField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                flag.set(true);
            }
            if(flag.get()){
                return;
            }

            spaceMarine.set(new SpaceMarine(bName, new Coordinates(bCoordinatesX, bCoordinatesY), bHeath, bLoyal, bAchievement, bCategory, new Chapter(bChapterName, bChapterMarinesCount)));
            isDone.set(true);
            createElementStage.close();

        });

        createElementStage.showAndWait();
        return spaceMarine.get();

    }




    public SpaceMarine addIfMin() throws IOException {
        return this.createElemFromInput();
    }

    public SpaceMarine removeGreater() throws IOException {
        return this.createElemFromInput();
    }

    public SpaceMarine removeLower() {
        try {
            return this.createElemFromInput();
        } catch (IOException var2) {
            System.out.println(var2.toString());
            return null;
        }
    }

    public Float countGreaterThanHealth() throws NumberFormatException {
        if (Arguments.getSize() == 1) {
            try {
                Float health = Float.parseFloat(Arguments.getArg());
                return health;
            } catch (NumberFormatException var3) {
                return null;
            }
        } else {
            return null;
        }
    }



    public String filterStartsWithName() {
        return Arguments.getSize() == 1 ? Arguments.getArg() : null;
    }

    public boolean help() {
        return true;
    }
}
