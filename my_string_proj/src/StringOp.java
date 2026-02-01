public class StringOp {
    public static void main(String s[]) {
        String s1 = "The quick brown fox jumped over the lazy dog";

        System.out.println(s1.length());

        char[] strAsArr = s1.toCharArray();
        System.out.println(strAsArr.length);

        System.out.println(strAsArr);

        System.out.println("The first char of the string is " + strAsArr[0]);
        System.out.println("The last char of the string is " + strAsArr[strAsArr.length - 1]);

        System.out.println("The index of T is " + s1.indexOf('T'));
        System.out.println("The index of g is " + s1.indexOf('g'));
    }
}