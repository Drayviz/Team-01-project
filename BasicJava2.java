public class BasicJava2 {
  public static boolean match(char lowerCaseChar, char upperCaseChar){
    boolean match = false;
    if (lowerCaseChar >= 'a' && lowerCaseChar <= 'z') {
      if (upperCaseChar >= 'A' && upperCaseChar <= 'Z'){
        if (upperCaseChar == lowerCaseChar){
          match = true;
        }
      }
        else{
          match = false;
        }
    }
    return match;
  }
  public static long ceiling(double num) {

    return 0;
  }
  public static int count(String str, String chars) {
    return 0;
  }
  public static int addHexDigits(int num){
    return 0;
  }
}
