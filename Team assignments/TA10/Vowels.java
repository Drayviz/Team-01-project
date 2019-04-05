public class Vowels{

    public static int numOfVowels(String phrase){
        int number = 0;
        if (phrase.length() == 0) {
            return 0;
        }
        if (phrase.charAt(0) == 'A' || phrase.charAt(0) == 'E' || phrase.charAt(0) == 'I' || phrase.charAt(0) == 'O' || phrase.charAt(0) == 'U' || phrase.charAt(0) == 'a' || phrase.charAt(0) == 'e' || phrase.charAt(0) == 'i' || phrase.charAt(0) == 'o' || phrase.charAt(0) == 'u') {
            number = 1 + numOfVowels(phrase.substring(1));
        }
        else {
            number = 0 + numOfVowels(phrase.substring(1));
        }
        return number;
    }
}

