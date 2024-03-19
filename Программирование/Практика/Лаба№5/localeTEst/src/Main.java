import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale loc = new Locale("Cyrl");
        System.out.println(loc.toLanguageTag());
    }

}