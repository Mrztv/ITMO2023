// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        double num = -68;
        double Sys = 5;
        String result = "";
        while(num > 0)
        {
            System.out.print((int)num + " / " + (int)Sys + " = " + (int)Math.floor(num/Sys) + " ost " + (int)(num%Sys) + '\n');
            result = (int)(num%Sys) + result;
            num /= Sys;
            num = Math.floor(num);

        }

        System.out.println(result);
    }
}