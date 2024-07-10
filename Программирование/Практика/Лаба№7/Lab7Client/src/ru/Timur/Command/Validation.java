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
import ru.Timur.AstartesCategory;
import ru.Timur.Chapter;
import ru.Timur.Coordinates;
import ru.Timur.OpenedFileSet;
import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;
import ru.Timur.Exceptions.NonValidFileElementException;

public class Validation implements Serializable {
    static final long serialVersionUID = 1L;
    private boolean isSystemIn = true;

    public Validation() {
    }

    public SpaceMarine add() throws NonValidFileElementException, IOException, InputMismatchException {
        SpaceMarine spaceMarine = this.createElemFromInput();
        return spaceMarine != null ? spaceMarine : null;
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
                        Invoker invoker = new Invoker(inputStream);
                        invoker.readStream(this);
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
        String name = null;
        Coordinates coordinates = null;
        Float health = null;
        boolean loyal = false;
        String achievements = null;
        AstartesCategory category = null;
        Chapter chapter = null;

        while(true) {
            while(true) {
                String chapterName;
                try {
                    if (this.isSystemIn) {
                        System.out.println("Введите имя:");
                    }

                    chapterName = StreamReader.readLine();
                    if (this.isSystemIn && chapterName.equals("null")) {
                        System.out.println("Неправильный ввод");
                        throw new NonValidFileElementException("name value");
                    }

                    if (chapterName.equals("s")) {
                        name = "Timur";
                        coordinates = new Coordinates(123, 312.0);
                        health = 123.0F;
                        loyal = true;
                        achievements = "achievement";
                        category = AstartesCategory.ASSAULT;
                        chapter = new Chapter("first", 400L);
                        return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);
                    }

                    if (chapterName == null || chapterName.trim().isEmpty()) {
                        if (this.isSystemIn) {
                            System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                            continue;
                        }

                        if (!chapterName.isBlank() && !chapterName.isEmpty()) {
                            continue;
                        }

                        throw new InputMismatchException();
                    }

                    name = chapterName;
                } catch (InputMismatchException var22) {
                    if (this.isSystemIn) {
                        System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                        if (this.isSystemIn) {
                            System.out.println("Введите имя:");
                        }
                        continue;
                    }

                    throw new InputMismatchException();
                }

                Integer coordX = null;
                Double coordY = null;
                boolean successName = false;

                do {
                    try {
                        if (this.isSystemIn) {
                            System.out.println("Введите координату X:");
                        }

                        Integer inputX = Integer.parseInt(StreamReader.readLine());
                        coordX = inputX;
                        successName = true;
                    } catch (NumberFormatException | InputMismatchException var21) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("X coord");
                        }

                        System.out.println("Неправильный ввод координаты X, попробуйте ещё раз");
                    }
                } while(!successName);

                boolean successCount = false;

                do {
                    if (this.isSystemIn) {
                        System.out.println("Введите координату Y:");
                    }

                    try {
                        Double inputY = Double.parseDouble(StreamReader.readLine());
                        if (inputY > -162.0) {
                            coordY = inputY;
                            successCount = true;
                        } else if (this.isSystemIn) {
                            System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                        }
                    } catch (NumberFormatException | InputMismatchException var20) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("Y coord");
                        }

                        System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                    }
                } while(!successCount);

                coordinates = new Coordinates(coordX, coordY);

                while(true) {
                    if (this.isSystemIn) {
                        System.out.println("Введите здоровье:");
                    }

                    try {
                        Float input = Float.parseFloat(StreamReader.readLine());
                        if (input != null && input > 0.0F) {
                            health = input;
                            break;
                        }

                        if (this.isSystemIn) {
                            System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                        }
                    } catch (NumberFormatException | InputMismatchException var19) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("health value");
                        }

                        System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                    }
                }

                while(true) {
                    try {
                        if (this.isSystemIn) {
                            System.out.println("Ответе \"Да(true)\" или \"Нет(false)\" на то союзный ли космодесантник");
                        }

                        chapterName = StreamReader.readLine();
                        if (this.isSystemIn && chapterName.equals("null")) {
                            System.out.println("Неправильный ввод");
                            throw new NonValidFileElementException("неправильный элемент в файле");
                        }

                        if (chapterName.equals("Да") || chapterName.equals("true")) {
                            loyal = true;
                            break;
                        }

                        if (chapterName.equals("Нет") || chapterName.equals("false")) {
                            loyal = false;
                            break;
                        }

                        if (this.isSystemIn) {
                            System.out.println("Неправильный ввод, попробуйте ещё раз");
                        }
                    } catch (InputMismatchException var18) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("loyal value");
                        }

                        System.out.println("Неправильный ввод, попробуйте ещё раз");
                    }
                }

                while(true) {
                    try {
                        if (this.isSystemIn) {
                            System.out.println("Введите достижения Космического десантника");
                        }

                        chapterName = StreamReader.readLine();
                        achievements = chapterName;
                        break;
                    } catch (InputMismatchException var17) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("achievements value");
                        }

                        System.out.println("Неправильный ввод, попробуйте ещё раз");
                    }
                }

                label240:
                while(true) {
                    chapterName = null;
                    if (this.isSystemIn) {
                        System.out.println("Выберите тип космодесантника из:\n   1)ASSAULT,\n   2)INCEPTOR,\n   3)TERMINATOR,\n   4)CHAPLAIN,\n   5)APOTHECARY");
                    }

                    try {
                        chapterName = StreamReader.readLine();
                        if (this.isSystemIn && chapterName.equals("null")) {
                            System.out.println("Неправильный ввод");
                            throw new NonValidFileElementException("неправильный элемент в файле");
                        }

                        int index = Integer.parseInt(chapterName);
                        switch (index) {
                            case 1:
                                category = AstartesCategory.ASSAULT;
                                break label240;
                            case 2:
                                category = AstartesCategory.INCEPTOR;
                                break label240;
                            case 3:
                                category = AstartesCategory.TERMINATOR;
                                break label240;
                            case 4:
                                category = AstartesCategory.CHAPLAIN;
                                break label240;
                            case 5:
                                category = AstartesCategory.APOTHECARY;
                                break label240;
                            default:
                                if (this.isSystemIn) {
                                    System.out.println("Неправильный ввод, попробуйте ещё раз");
                                }
                        }
                    } catch (NumberFormatException | InputMismatchException var16) {
                        AstartesCategory[] var27 = AstartesCategory.values();
                        int var29 = var27.length;

                        for(int var30 = 0; var30 < var29; ++var30) {
                            AstartesCategory astartesCategory = var27[var30];
                            if (chapterName.toLowerCase().equals(astartesCategory.name().toLowerCase())) {
                                category = astartesCategory;
                                break label240;
                            }
                        }

                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("category value");
                        }

                        System.out.println("Неправильный ввод, попробуйте ещё раз");
                    }
                }

                chapterName = null;
                Long chapterCount = null;
                successName = false;

                do {
                    try {
                        if (this.isSystemIn) {
                            System.out.println("Введите название части");
                        }

                        String inputName = StreamReader.readLine();
                        if (this.isSystemIn && inputName.equals("null")) {
                            System.out.println("Неправильный ввод");
                            throw new NonValidFileElementException("неправильный элемент в файле");
                        }

                        if (inputName != null && !inputName.isEmpty()) {
                            chapterName = inputName;
                            successName = true;
                        } else if (this.isSystemIn) {
                            System.out.println("Непрвильный ввод, попробуйте снова");
                        }
                    } catch (InputMismatchException var15) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("category value");
                        }

                        System.out.println("Неправильный ввод, попробуйте снова");
                    }
                } while(!successName);

                successCount = false;

                do {
                    try {
                        if (this.isSystemIn) {
                            System.out.println("Введите количество морпехов");
                        }

                        Long inputCount = Long.parseLong(StreamReader.readLine());
                        if (inputCount > 0L && inputCount < 1000L) {
                            chapterCount = inputCount;
                            successCount = true;
                        } else if (this.isSystemIn) {
                            System.out.println("Неправильный ввод, попробуйте снова");
                        }
                    } catch (NumberFormatException | InputMismatchException var14) {
                        if (!this.isSystemIn) {
                            throw new NonValidFileElementException("category value");
                        }

                        System.out.println("Неправильный ввод, попробуйте снова");
                    }
                } while(!successCount);

                chapter = new Chapter(chapterName, chapterCount);
                return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);
            }
        }
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
