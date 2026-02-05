public class Sample {
  public static void main(String s[]) {
    String s1 = "Hello";
    s1.concat(" World!");
    System.out.println(s1);

    //Fixing the error by assigning back the result
    // String s2 = "Hello";
    //s2 += " World!";
    //System.out.println(s2);

    //String s1 = "Hello";
    //s1 = s1.concat(" World!");  // Assign the result back to s1
    //System.out.println(s1);
  }
}
