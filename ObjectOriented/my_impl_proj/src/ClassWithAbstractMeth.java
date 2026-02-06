package ObjectOriented.my_impl_proj.src;

public abstract class ClassWithAbstractMeth {
    public abstract int absMeth1(String s);

    public abstract String absMeth2();

    public abstract String absMeth3(int num);

    protected abstract boolean absMeth4(String str);

    abstract float absMeth5(int num, String str);

    public static void main(String[] args) {
        System.out.println("This is a class with abstract methods");
    }
}