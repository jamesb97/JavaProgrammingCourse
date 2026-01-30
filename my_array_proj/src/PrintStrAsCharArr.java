public class PrintStrAsCharArr {
    public static void main(String s[]) {
        String myStr = "The quick brown fox jumped over the lazy dog";

        char[] chrArr = myStr.toCharArray();
        for (int i = 0; i < chrArr.length; i++) {
            System.out.print(chrArr[i]);
        }
    }
}