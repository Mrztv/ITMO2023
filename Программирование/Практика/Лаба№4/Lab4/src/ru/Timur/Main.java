package ru.Timur;

import java.util.prefs.PreferenceChangeListener;

class Main {
    public static void main(String[] args) {
        Robber fille = new Robber("Fille");
        Robber rulle = new Robber("Rulle");
        Child malush = new Child("Malush");
        Ghost karlson = new Ghost("Karlson");
        Child gunilla = new Child("Gunilla");
        Child crister = new Child("Crister");

        Child[] children = {malush, karlson, gunilla, crister};
        Robber[] robbers = {rulle, fille};

        karlson.speak("Давайте тихонько пойдём в столовую — наверно, там твой отец хранит золотые слитки и бриллианты", "karlson");

        for (Child character : children) {
            String location = "столовую";
            if(!location.equals("столовую")){
                throw new LocationException();
            }else {
                character.sneak(location);
            }
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
            String location = "столовую";
            if(!location.equals("столовую")){
                throw new LocationException();
            }else {
                character.sneak(location);

            }
        }

        rulle.search(secretaire);
        rulle.whistle();
        malush.think("Наверно, нашел деньги");
        rulle.stopWhistle();
        do {
            try {
                karlson.fly();
            } catch (EngineException e) {
                System.out.println("Попытка запустить двигатель");
                karlson.engine.startEngine();
            }
        }while(!karlson.isFly);

        buffet.open();

        karlson.makeSound(karlson.new Sound("страшный стон"));

        rulle.lookBack();

        rulle.makeSound("хрюкнул");

        rulle.drop("всё");

        try{
            karlson.fly();
        }catch (EngineException e)
        {
            //do nothing
        }

        karlson.makeSound(karlson.new Sound("стон и вздох"));

        karlson.flyTo("кухню");





        karlson.breakEngine(new Ghost.Engine(){
            @Override
            public void offEngine(){
                this.setStart(false);
                System.out.println("Двигатель взорвался");
            }
        });
    }

    static void printContains(Interior thing) {
        System.out.print(thing.getName() + " содержит: ");
        for (String a : thing.getContains()) {
            System.out.print(a + " ");
        }
        System.out.println();
    }



}
