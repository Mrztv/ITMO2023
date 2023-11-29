class A {
    public static char method(){ return 'a';}
}

class B extends A{
    public static char method() { return 'b';}
}

public class Test{
    public static void main(String[] args) {
        A aa = new A();
        A ab = new B();
        B bb = new B();
        B ba = new A();

        System.out.println(aa.method());
        System.out.println(ab.method());
        System.out.println(bb.method());
        System.out.println(ba.method());

    }
}
