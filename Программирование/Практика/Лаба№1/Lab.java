import java.util.Random;

public class Lab
{
    static final short lengthOfFirstArray = 19, lengthOfSecondArray = 12, secondArrayMinValue = -4, secondArrayMaxValue = 13;
    static int[] c1 = new int[lengthOfFirstArray];
    static float[] x = new float[lengthOfSecondArray];
    static double[][] c2 = new double[lengthOfFirstArray][lengthOfSecondArray];
    static Random rand = new Random();
    public static void main(String[] args)
    {
        for(int i = 0; i<lengthOfFirstArray; i++)
        {
            c1[i] = lengthOfFirstArray-i;
        }

        for(int i = 0; i<lengthOfSecondArray; i++ )
        {
            x[i] = rand.nextFloat(secondArrayMaxValue - secondArrayMinValue) + secondArrayMinValue;
        }



        for(int i = 0; i<lengthOfFirstArray; i++)
        {
            for(int j=0; j<lengthOfSecondArray; j++)
            {
                float tmp_x = x[j];

                switch(c1[i])
                {
                    case 11:
                        c2[i][j] = Math.sin(Math.pow((Math.pow(0.5/tmp_x ,2)*(Math.exp(tmp_x)+1)), 3));
                        break;
                    case 2, 4, 5, 6, 7, 8, 17, 18, 19:
                        c2[i][j] = Math.asin(Math.pow(Math.sin(tmp_x), 2));
                        break;
                    default:
                        c2[i][j] = Math.pow(0.5 + 2*Math.sin(Math.log(Math.abs(tmp_x))), 2);

                }
                if(c1[i] == 11)
                {
                    c2[i][j] = Math.sin(Math.pow((Math.pow(0.5/tmp_x ,2)*(Math.exp(tmp_x)+1)), 3));
                }
                
                
            }


        }




        for(int i = 0; i < lengthOfFirstArray; i++)
        {
            for(int j = 0; j<lengthOfSecondArray; j++)
            {
                System.out.printf("%.5f ", c2[i][j]);

            }
            System.out.print('\n');
        }

    }


}