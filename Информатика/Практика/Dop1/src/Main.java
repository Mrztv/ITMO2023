public class Main {
    static final int Sys = 7; //Какая симметричная система
    public static void main(String[] args)
    {

        double input = StringToDouble(args[0]); //Перевод входных аргументов в тип double
        String result = "";//Переменная для вывода

        /*Основной цикл программы. В нем проверяется является ли остаток от деления больше чем макмимальная кодировочная цифра в заданной системе счисления.
        Если остаток больше, то мы отнимаем от него число системы счисления и записываем в начало result, также увеличиваем неполное часное на 1
        Если остаток меньше, то действуем стандартно
         */


        while(input > 0)
        {
            if(input%Sys > Math.floor( (double)Sys /2))
            {
                result = "{^" + Math.abs((int)(input%Sys - Sys)) + "}" + result;
                input = (int)input/Sys + 1;
            }
            else
            {
                result = (int)(input%Sys) + result;
                input = (int)input / Sys;
            }

        }
        System.out.println(result); //Вывод

    }

    private static double StringToDouble(String _Num)
    {
        double result=0; //переменная для возврата
        boolean flagDot = false; //флаг для определения целая или дробная часть в цикле
        int afterDot = 0; //Счетчик для цифр после точки
        String Num = _Num; //копия строки для изменений её

        for(char ch : Num.toCharArray())
        {
            if(flagDot)
            {
                afterDot++;//если дробная часть то увеличиваем счетчик
            }
            if(ch == '.' || ch == ',')
            {
                flagDot=true;//определение разделения дробной и целой частей
                continue;
            }
            result *= 10;
            result += ch - '0'; //изменение результата
        }
        result /= Math.pow(10, afterDot); //Получаем дробную часть избегая неточностей сложения вещественных типов данных


        return result;
    }


}