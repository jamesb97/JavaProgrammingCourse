public class ArrayAccessings {
    public static void main(String s[]) {
        int num_args = s.length;
        System.out.println("the length of the array is " + num_args);
        for (int i = 0; i < num_args; i++) {
            System.out.println(s[i]);
        }
    }
}