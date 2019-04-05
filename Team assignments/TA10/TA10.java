public class TA10 {

    /**
     * This method removes the vowels and returns the concatenated consonants as a string.
     * @param phrase This is the phrase that we would like to remove vowels from.
     * @return The concatenated consonants as a string.
     */
    public static String removeVowels(String phrase) {
        
        String newPhrase = ""; //instantiates phrase

        if (phrase.length() == 0) {
            return ""; //if phrase has no letters, return empty string
        }
        if (phrase.charAt(0) == 'A' || phrase.charAt(0) == 'E' || phrase.charAt(0) == 'I' || phrase.charAt(0) == 'O' || phrase.charAt(0) == 'U' || phrase.charAt(0) == 'a' || phrase.charAt(0) == 'e' || phrase.charAt(0) == 'i' || phrase.charAt(0) == 'o' || phrase.charAt(0) == 'u') {
            newPhrase = removeVowels(phrase.substring(1)); //if the character the method is looking at is a vowel, when the recursive call occurs the vowel is not included in concatenation
        }
        else {
            newPhrase = phrase.charAt(0) + removeVowels(phrase.substring(1)); //if the character the method is looking at is a consonant, when the recursive call occurs the consonant is included in concatenation
        }
        return newPhrase; //final return statement
    }

/*     public static void main(String[] args) {
        String bobby = removeVowels("YEEEEEEEEEEEEEEEEEEEEEEEEET");
        System.out.println(bobby);
    } */
}