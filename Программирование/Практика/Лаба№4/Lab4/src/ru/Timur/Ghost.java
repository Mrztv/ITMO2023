package ru.Timur;

public class Ghost extends Child {
    Engine engine = new Engine();
    boolean isFly = false;
    public Ghost(String name){
        super(name);
    }

    static public class Engine{
        private boolean isStart = false;

        public Engine(){}
        public void startEngine(){

            isStart = true;
            System.out.println("Двигатель запущен");
        }
        public void offEngine(){
            isStart = false;
            System.out.println("Двигатель выключен");
        }
        public boolean getStart(){
            return isStart;
        }

        public void setStart(boolean isStart){
            this.isStart = isStart;
        }

    }

    public void breakEngine(Engine engine){
        engine.offEngine();
    }

    public void fly() throws EngineException {
        if(engine.getStart()){
            System.out.println("Привидение порхает");
            isFly = true;
        }else{
            throw new EngineException("Двигатель не включен");
        }
    }

     public class Sound{
        String typeOfSound = null;

        public Sound(String type){
            typeOfSound = type;
        }

        public String getTypeOfSound(){
            return typeOfSound;
        }
    }

    public void makeSound(Sound sound){
        System.out.println("Приведение издовало:" + sound.getTypeOfSound());
    }

    public void flyTo(String location){
        System.out.println("устремилось в " + location);
    }

    public Engine getEngine() {
        return engine;
    }
}
