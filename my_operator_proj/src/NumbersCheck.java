public class NumbersCheck {
    public static void main(String s[]) {
        int intArr[] = { 12, 17, 3, 165, 87, 15, 54, 123, 98, 23 };
        for (int i = 0; i < intArr.length - 1; i++) {
            String output = intArr[i] <= intArr[i + 1] ? (intArr[i] < intArr[i + 1] ? " less than " : " equal to ")
                    : " more than ";
            System.out.println(intArr[i] + output + intArr[i + 1]);
        }
    }
}