package ru.Timur;

class Main {
    public static void main(String[] args) {
        Robber fille = new Robber("Fille");
        Robber rulle = new Robber("Rulle");
        Child malush = new Child("Malush");
        Child karlson = new Child("Karlson");
        Child gunilla = new Child("Gunilla");
        Child crister = new Child("Crister");

        Child[] children = {malush, karlson, gunilla, crister};
        Robber[] robbers = {rulle, fille};

        for (Child character : children) {
            character.sneak("столовую");
        }
        Interior buffet = Interior.BUFFET;
        Interior some = Interior.SOME;
        Interior sofa = Interior.SOFA;
        Interior secretaire = Interior.SECRETAIRE;

        malush.hide(sofa);
        gunilla.hide(some);
        crister.hide(some);
        karlson.hide(buffet);

        buffet.setContains(new String[]{"салфетки", "скатерти"});
        secretaire.setContains(new String[]{"деньги", "кольцо", "брошки"});
        printContains(buffet);

        for (Robber character : robbers) {
            character.sneak("столовую");
        }

        rulle.search(secretaire);
        rulle.whistle();
        malush.think("Наверно, нашел деньги");

    }

    static void printContains(Interior thing) {
        System.out.print(thing.getName() + " содержит: ");
        for (String a : thing.getContains()) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

}
