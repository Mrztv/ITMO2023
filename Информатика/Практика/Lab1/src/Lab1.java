import java.util.ArrayList;
import java.util.HashMap;

public class Lab1
{
    static final double berg = 1.618033988749895;
    public static void main(String[] args)
    {
        String Num = "20{^3}1";
        String StartSystem = "7C";
        String EndSystem = "10";
        //String Num = args[0];
        //String StartSystem = args[1];
        //String EndSystem = args[2];
        double answer = toDec(Num, StartSystem);
        String result = fromDec(answer, EndSystem);

        System.out.printf(result);
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
        
        //char[] array = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
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
                if(Num.toCharArray()[i] >= '0' && Num.toCharArray()[i]<='9')
                {
                    forSimNumbers.add( (int)(Num.toCharArray()[i] - '0') * (int)Math.pow(-1, sim?1 : 0));
                }
            }


            /*for(int i :forSimNumbers)
            {
                System.out.print(i + " ");
            }
            System.out.println();*/

            String sys = new String(StartSystem.toCharArray(), 0, StartSystem.length()-1);
            for(int i = 0; i<forSimNumbers.toArray().length; i++)
            {
                int power = forSimNumbers.toArray().length - 1 - i;
                result += (int)forSimNumbers.toArray()[i] * (int)Math.pow(StringToDouble(sys), power);
            }
        }
        else
        {
            Double SystemN = StringToDouble(StartSystem);
            if(StartSystem == "Berg")
            {
                SystemN = berg;
            }
            int Integer = (int)Math.floor(NewNum);
            double Fractional = NewNum-Integer;
            int countInteger = 0;
            int countFractional = 0;

            ArrayList<Character> forNum = new ArrayList<Character>(); //Лист для перевода числа

            boolean afterDot = false;

            for (char ch : Num.toCharArray())
            {
                if (ch!= '.' && ch !=',')
                {
                    forNum.add(ch);
                }
                if(ch == '.' || ch == ',')
                {
                    afterDot = true;
                }
                if(!afterDot)
                {
                    countInteger++;
                }
                else if(ch!= '.' && ch !=',')
                {
                    countFractional++;
                }

            }

            for(int k = countInteger-1, i=0; k >= -1*countFractional; k--, i++)
            {

                result += Math.pow(SystemN, k) * (int) numbers.get(forNum.toArray()[i]);
                System.out.print((double) (Math.pow(SystemN, k) * (int) numbers.get(forNum.toArray()[i])) + " + ");
            }
            System.out.println("");


        }
        return result;

    }

    private static String fromDec(double Num, String EndSystem)
    {
        String result = "";
        double SystemN = StringToDouble(EndSystem);
        HashMap<Double, Character> numbers = new HashMap<Double, Character>();
        numbers.put(0.0, '0');
        numbers.put(1.0, '1');
        numbers.put(2.0, '2');
        numbers.put(3.0, '3');
        numbers.put(4.0, '4');
        numbers.put(5.0, '5');
        numbers.put(6.0, '6');
        numbers.put(7.0, '7');
        numbers.put(8.0, '8');
        numbers.put(9.0, '9');
        numbers.put(10., 'A');
        numbers.put(11., 'B');
        numbers.put(12., 'C');
        numbers.put(13., 'D');
        numbers.put(14., 'E');
        numbers.put(15., 'F');


        if( (int)StringToDouble(EndSystem) == 10 )
        {
            return Double.toString(Num);
        }
        else if( EndSystem.contains("C") )
        {
            return "";
        }
        else if ( EndSystem.contains("Fib"))
        {
            int _Num = (int)Num;
            ArrayList<Integer> Fib = new ArrayList<Integer>();
            Fib.add(1);
            Fib.add(1);
            while((int)Fib.toArray()[0] < Num)
            {
                Fib.add(0, (int)Fib.toArray()[0] + (int)Fib.toArray()[1] );
            }
            Fib.remove(0);
            Fib.remove( Fib.toArray().length -1 );

            for (int elem: Fib) {
                if(elem<=_Num)
                {
                    _Num-=elem;
                    result += '1';
                }
                else
                {
                    result += '0';
                }
            }

            return result;
        }
        else
        {
            String _Num = Double.toString(Num);
            int _Integer = (int)Math.floor(Num);
            double Fractional = 0.0;

            String forInteger = "";
            String forFractional = "";
            for(int i = _Num.length()-1; i >=0; i--)
            {
                if(_Num.toCharArray()[i] == '.' || _Num.toCharArray()[i]==',')
                {
                    break;
                }
                Fractional += (double) (_Num.toCharArray()[i]-'0') / 10;
                Fractional /= 10;
            }
            Fractional *= 10;

            //System.out.println(_Integer);
            //System.out.println(Fractional);

            while(_Integer != 0)
            {
                forInteger = numbers.get(_Integer%SystemN) + forInteger;
                _Integer /= SystemN;
            }

            while(forFractional.length() < 5 && Fractional!=0.0)
            {
                forFractional += numbers.get(Math.floor(SystemN * Fractional));
                Fractional *= SystemN;
                Fractional -= Math.floor(Fractional);
            }

            result = forInteger + '.' + forFractional;
            return result;
        }
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
            if(ch == '.' || ch == ',')
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
