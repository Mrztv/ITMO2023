//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import ru.Timur.ExecuteQueue;
import ru.Timur.Network;
import ru.Timur.StreamReader;
import ru.Timur.Exceptions.ExitException;

public class Invoker {
    static Map<String, Command> commands;
    private InputStream inputStream;

    public Invoker(InputStream inputStream) {
        this.inputStream = inputStream;
        StreamReader.changeStreamReader(inputStream);
    }

    public void readStream(Validation validation) throws RuntimeException {
        commands = new HashMap();
        commands.put("add", new AddCommand(validation));
        commands.put("info", new InfoCommand(validation));
        commands.put("show", new ShowCommand(validation));
        commands.put("remove_by_id", new RemoveByIdCommand(validation));
        commands.put("update", new UpdateCommand(validation));
        commands.put("help", new HelpCommand(validation));
        commands.put("exit", new ExitCommand(validation));
        commands.put("clear", new ClearCommand(validation));
        commands.put("execute_script", new ExecuteScriptCommand(validation));
        commands.put("add_if_min", new AddIfMinCommand(validation));
        commands.put("remove_greater", new RemoveGreaterCommand(validation));
        commands.put("remove_lower", new RemoveLowerCommand(validation));
        commands.put("average_of_health", new AverageOfHealthCommand(validation));
        commands.put("count_greater_than_health", new CountGreaterThanHealthCommand(validation));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(validation));

        try {
            while(true) {
                String input;
                do {
                    do {
                        ExecuteQueue.clear();
                        if (this.inputStream == System.in) {
                            System.out.println("Введите команду:");
                        }

                        input = StreamReader.readLine();
                    } while(input.isEmpty());
                } while(input.isBlank());

                if (input.equals(-1)) {
                    break;
                }

                String[] words = input.trim().split(" ");

                for(int i = 1; words.length > 1 && i < words.length; ++i) {
                    Arguments.addArg(words[i]);
                }

                if (commands.containsKey(words[0])) {
                    Network network = new Network(9999, InetAddress.getLocalHost());
                    if (words[0] == "exit") {
                        validation.exit();
                    }

                    if (((Command)commands.get(words[0])).execute()) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(commands.get(words[0]));
                        network.send(byteArrayOutputStream);
                        network.recive();
                    }

                    Arguments.clearArgs();
                } else if (this.inputStream != System.in) {
                    throw new RuntimeException("Неправильная комманда");
                }
            }
        } catch (NoSuchElementException var7) {
            if (this.inputStream != System.in) {
                throw var7;
            }

            System.out.println("Остановка приложения");
        } catch (NumberFormatException var8) {
            if (this.inputStream != System.in) {
                throw var8;
            }

            System.out.println("Неправильный аргумент, попробуйте ещё раз");
        } catch (ExitException var9) {
            if (this.inputStream == System.in) {
                System.out.println("Выход из приложения");
            }

            System.exit(1);
        } catch (NullPointerException var10) {
            StreamReader.changeStreamReader(System.in);
            return;
        } catch (UnknownHostException var11) {
            System.out.println(var11.toString());
        } catch (IOException var12) {
            System.out.println(var12.toString());
        }

    }
}
