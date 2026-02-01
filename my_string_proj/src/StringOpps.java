public class StringOpps {
    public static void main(String s[]) {
        String s1 = "Washington";
        String s2 = new String("Washington");
        String s3 = "WASHINGTON";

        System.out.println("Equality check s1 and s2 - " + s1.equals(s2));
        System.out.println("Equality check s1 and s3 - " + s1.equals(s3));
        System.out.println("Equality check s1 and s3 ignoring case - " + s1.equalsIgnoreCase(s3));

        System.out.println("s1 in lowercase - " + s1.toLowerCase());
        System.out.println("s3 in lowercase - " + s3.toLowerCase());

        System.out.println("s1 and s3 lowercase equality check - " +
                s1.toLowerCase().equals((s3.toLowerCase())));

        System.out.println("s1 in uppercase - " + s1.toUpperCase());
        System.out.println("s3 in uppercase - " + s3.toUpperCase());

        String s4 = "50F1A";
        System.out.println("s4 in lowercase - " + s4.toLowerCase());

        String regexStr = "^W.*";
        System.out.println("s1 matches regex ^W.* - " + s1.matches(regexStr));
        System.out.println("s3 matches regex ^W.* - " + s3.matches(regexStr));

        String s5 = "     WASHINGTON          ";
        System.out.println("Equality check s3 and s5 - " + s3.equals(s5));
        s5 = s5.strip();
        System.out.println("Equality check after stripping s3 and s5 - " + s3.equals(s5));
    }

}