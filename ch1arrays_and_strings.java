import java.util.HashSet;
import java.util.Set;
// import java.util.LinkedList;
public class ch1arrays_and_strings {
    public static boolean debug = false;
    public static void main(String[] args){
        // System.out.println(isUniqueWithDataStructure("abbcd"));
        // System.out.println(isUniqueWithoutDataStructure("abccd"));
        System.out.println(checkPermutation("abca", "ba"));
    }

    /**
     * 1.1 Is Unique: 
     *  Implement an algorithm to determine if a string has all unique characters.
     *  What if you cannot use additional data structures? 
     */
    // Using addition data structure (Set)
    public static boolean isUniqueWithDataStructure(String someString){
        Set<Character> str = new HashSet<Character>();
        int someStringLength = someString.length();
        for(int i = 0; i < someStringLength; i++){
            if(!str.add(someString.charAt(i))){
                return false;
            }
        }
        return true;
    }
    // Without using an additional data structure
    public static boolean isUniqueWithoutDataStructure(String someString){
        // since int is a data type not a data structure
        int someStringLength = someString.length();
        for(int i = 0; i < someStringLength; i++){
            boolean unique = false;
            if(debug) System.out.println(i);
            for(int j = 0; j < someStringLength; j++){
                if(debug) System.out.println(someString.charAt(j)+" - "+someString.indexOf(someString.charAt(j)));
                if(someString.indexOf(someString.charAt(j)) == i){
                    unique = true;
                    break;
                }
            }
            if(!unique){
                return false;
            }
        }
        return true;
    }

    /**
     * 1.2 Check Permutation: 
     *  Given two strings, write a method to decide if one is a permutation of the other.
     *  Note: Permutation has same characters but the order is different
     */
    public static boolean checkPermutation(String s1, String s2){
        
        return true;
    }

    /**
     * 1.3 URLify: 
     *  Write a method to replace all spaces in a string with '%20'. 
     *  You may assume that the string has sufficient space at the end 
     *  to hold the additional characters, and that you are given the "true"
     *  length of the string. 
     *  (Note: If implementing in Java, please use a character array so that 
     *  you can perform this operation in place.)
     *  EXAMPLE
     *  Input: "Mr John Smith ", 13
     *  Output: "Mr%20John%20Smith"
     */
    public static void URLify(){
    }

    /**
     * 1.4 Palindrome Permutation: 
     *  Given a string, write a function to check if it is a permutation of a 
     *  palindrome. A palindrome is a word or phrase that is the same forwards 
     *  and backwards. A permutation is a rearrangement of letters. 
     *  The palindrome does not need to be limited to just dictionary words.
     *  EXAMPLE
     *  Input: Tact Coa
     *  Output: True (permutations: "taco cat", "atco eta", etc.)
     */
    public static void palindromePermutation(){
    }

    /**
     * One Away: 
     *  There are three types of edits that can be performed on strings: 
     *  insert a character, remove a character, or replace a character. 
     *  Given two strings, write a function to check if they are 
     *  one edit (or zero edits) away.
     *  EXAMPLE
     *  pale, ple -> true
     *  pales, pale -> true
     *  pale, bale -> true
     *  pale, bake -> false
     */
    public static void oneAway(){
    }

}
