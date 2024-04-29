package ru.Timur.Command;

import ru.Timur.Exceptions.ExitException;
import ru.Timur.ExecuteQueue;
import ru.Timur.Network;
import ru.Timur.OpenedFileSet;
import ru.Timur.StreamReader;

import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Класс вызывающий команды у ресивера
 * @author timur
 */
public class Invoker{
    /**
     * {@link Map} Для хранения и вызова команд
     */
    static Map<String, Command> commands;

    private InputStream inputStream;

    /**
     * Конструктор
     * @param inputStream откуда вводится информация
     */
    public Invoker(InputStream inputStream){
        this.inputStream = inputStream;
        StreamReader.changeStreamReader(inputStream);
    }

    /**
     * Чтение потока и вызов команд
     * @throws RuntimeException
     */
    public void readStream(Validation validation) throws RuntimeException{
        commands = new HashMap<String, Command>();
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


        /*if(OpenedFileSet.isStart()){
            try{
                while(OpenedFileSet.isStart()){
                    if(inputStream == System.in) System.out.println("Введите команду:");
                    String input = StreamReader.readLine();
                    String[] words;
                    if(input.isEmpty() || input.isBlank()){
                        continue;
                    }else if(input.equals(-1)){
                        break;
                    }else{
                        words = input.trim().split(" ");
                    }
                    //сделать проверку на второй элемент и делать set arg (мб сделать static поле general и об него задавать аргументы)
                    for(int i  = 1; words.length > 1 && i < words.length; i ++){
                        Arguments.addArg(words[i]);
                    }

                    //выполнение команды
                    if(commands.containsKey(words[0])){
                        //здесь код, который отправляет запрос на сервер и выводит на экран результат
                        if(commands.get(words[0]).execute()){
                            ExecuteQueue.addInQueue(commands.get(words[0]));
                            break;
                        }
                    }
                    else {
                        if(inputStream != System.in){
                            throw new RuntimeException("Неправильная комманда");
                        }
                    }
                }
            }catch (NoSuchElementException e){ //ctrl + D
                if(inputStream == System.in) System.out.println("Остановка приложения");
                else throw e;
            }catch (NumberFormatException e){ //
                if(inputStream == System.in) System.out.println("Неправильный аргумент, попробуйте ещё раз");
                else throw e;
            }catch (ExitException e) {
                if (inputStream == System.in) System.out.println("Выход из приложения");
                System.exit(1);
            }catch (NullPointerException e){
                StreamReader.setBufferedReader(System.in);
                return;
            }catch (Exception e){
                System.out.println(e.toString());
                throw new RuntimeException();
            }
        }
*/



        try {
            while(true){
                ExecuteQueue.clear();
                if(inputStream == System.in) System.out.println("Введите команду:");
                String input = StreamReader.readLine();
                String[] words;
                if(input.isEmpty() || input.isBlank()){
                    continue;
                }else if(input.equals(-1)){
                    break;
                }else{
                    words = input.trim().split(" ");
                }
                //сделать проверку на второй элемент и делать set arg (мб сделать static поле general и об него задавать аргументы)
                for(int i  = 1; words.length > 1 && i < words.length; i ++){
                    Arguments.addArg(words[i]);
                }

                //выполнение команды
                if(commands.containsKey(words[0])){
                    //здесь код, который отправляет запрос на сервер и выводит на экран результат

                    Network network = new Network(65125, InetAddress.getLocalHost());

                    if(words[0] == "exit"){
                        validation.exit();
                    }

                    if(commands.get(words[0]).execute()){
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(commands.get(words[0]));

                        network.send(byteArrayOutputStream);

                        network.recive();
                    };
                    Arguments.clearArgs();



                }
                else {
                    if(inputStream != System.in){
                        throw new RuntimeException("Неправильная комманда");
                    }
                }
            }
        }catch (NoSuchElementException e){ //ctrl + D
            if(inputStream == System.in) System.out.println("Остановка приложения");
            else throw e;
        }catch (NumberFormatException e){ //
            if(inputStream == System.in) System.out.println("Неправильный аргумент, попробуйте ещё раз");
            else throw e;
        }catch (ExitException e) {
            if (inputStream == System.in) System.out.println("Выход из приложения");
            System.exit(1);
        }catch (NullPointerException e){
            StreamReader.changeStreamReader(System.in);
            return;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }



}
