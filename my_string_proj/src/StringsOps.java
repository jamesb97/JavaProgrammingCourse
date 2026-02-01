public class StringsOps {
    public static void main(String s[]) {
        String s1 = "Washington";
        String s2 = " DC";
        s1 = s1.concat(s2);
        System.out.println("s1 " + s1);
        s1 = s1.substring(0, 10);
        System.out.println("s1 " + s1);

        System.out.println("s1.substring(7,10) " + s1.substring(7, 10));
        System.out.println("s1.substring(7) " + s1.substring(7));
    }
}