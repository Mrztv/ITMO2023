import java.util.ArrayList;
import java.util.HashMap;

public class Lab
{
    public static void main(String[] args)
    {
        String Num = args[0];
        String StartSystem = args[1];
        //String EndSystem = args[2];
        double answer = toDec(Num, StartSystem);
        if(answer == Math.floor(answer))
        {
            System.out.println((int)answer);
        }
        else 
        {
            System.out.println(answer);
        }
    }

    private static double toDec(String Num, String StartSystem)
    {
        double result = 0;
        double NewNum = StringToDouble(Num);

        //Создание HashMap для превода из 16-ти СС и меньших
        HashMap<Character, Integer> numbers = new HashMap<>();
        numbers.put('0', 0);
        numbers.put('1', 1);
        numbers.put('2', 2);
        numbers.put('3', 3);
        numbers.put('4', 4);
        numbers.put('5', 5);
        numbers.put('6', 6);
        numbers.put('7', 7);
        numbers.put('8', 8);
        numbers.put('9', 9);
        numbers.put('A',10);
        numbers.put('B',11);
        numbers.put('C',12);
        numbers.put('D',13);
        numbers.put('E',14);
        numbers.put('F',15);
        
        //char[] array = {'0', '1', '2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        if(StringToDouble(StartSystem) == 10)
        {
            return NewNum;
        }
        else if(StartSystem.contains("C"))
        {
            //System.out.println("sim"); //для отладки
            ArrayList<Integer> forSimNumbers = new ArrayList<Integer>();
           boolean sim = false;
            for(int i=0; i<Num.length(); i++)
            {
                if(Num.toCharArray()[i] == '{')
                {
                    sim = true;
                }
                if(Num.toCharArray()[i] == '}')
                {
                    sim = false;
                }
                if(Num.toCharArray()[i] >= '0' && Num.toCharArray()[i]<=9)
                {
                    forSimNumbers.add( (int)(Num.toCharArray()[i] - '0') * (int)Math.pow(-1, sim?1 : 0));
                }
            }


            for(int i :forSimNumbers)
            {
                System.out.print(i + " ");
            }
        }
        else
        {
            int SystemN = (int)StringToDouble(StartSystem);
            int Integer = (int)Math.floor(NewNum);
            double Fractional = NewNum-Integer;
            String rNum = "";
            for(int i=0; i<Num.length(); i++)
            {
                char ch = Num.charAt(i);
                rNum = ch + rNum;
            }

            int k = 0;
            while(k<rNum.length())
            {
                result += numbers.get(rNum.charAt(k)) * Math.pow(SystemN, k);
                k++;
            }


        }
        return result;
    }


    private static double StringToDouble(String _Num)
    {
        double result=0;
        boolean flagDot = false;
        int afterDot = 0;
        String Num = _Num;

        for(char ch : Num.toCharArray())
        {
            if(flagDot)
            {
                afterDot++;
            }
            if(ch == '.')
            {
                flagDot=true;
                continue;
            }
            result *= 10;
            result += ch - '0';
        }
        result /= Math.pow(10, afterDot);


        return result;
    }
}