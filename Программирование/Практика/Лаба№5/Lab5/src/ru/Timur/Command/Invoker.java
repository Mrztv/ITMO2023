package ru.Timur.Command;

import ru.Timur.Exceptions.ExitException;
import ru.Timur.StreamReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Класс вызывающий команды у ресивера
 * @author timur
 * @see Storage
 */
public class Invoker{
    /**
     *Поле для хранения читателя потока команд
     */
    private StreamReader streamReader;
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
        streamReader = new StreamReader(inputStream);
    }

    /**
     * Чтение потока и вызов команд
     * @param storage хранилище коллекции
     * @throws RuntimeException
     */
    public void readStream(Storage storage) throws RuntimeException{
        commands = new HashMap<String, Command>();
        commands.put("add", new AddCommand(storage, streamReader));
        commands.put("info", new InfoCommand(storage));
        commands.put("show", new ShowCommand(storage));
        commands.put("remove_by_id", new RemoveByIdCommand(storage));
        commands.put("update", new UpdateCommand(storage,streamReader));
        commands.put("help", new HelpCommand(storage));
        commands.put("exit", new ExitCommand(storage));
        commands.put("clear", new ClearCommand(storage));
        commands.put("save", new SaveCommand(storage, new File(System.getenv("FileJAVA"))));
        commands.put("execute_script", new ExecuteScriptCommand(storage));
        commands.put("add_if_min", new AddIfMinCommand(storage, streamReader));
        commands.put("remove_greater", new RemoveGreaterCommand(storage, streamReader));
        commands.put("remove_lower", new RemoveLowerCommand(storage, streamReader));
        commands.put("average_of_health", new AverageOfHealthCommand(storage));
        commands.put("count_greater_than_health", new CountGreaterThanHealthCommand(storage));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(storage));



        try {
            while(true){
                    if(inputStream == System.in) System.out.println("Введите команду:");
                    String input = streamReader.readLine();
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

                    if(commands.containsKey(words[0])){
                        commands.get(words[0]).execute();
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
            streamReader.setBufferedReader(System.in);
            return;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }



}
