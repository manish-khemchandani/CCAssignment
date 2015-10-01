package chapter10;

import java.util.*;

// Write a method to sort an array of strings so that all the anagrams are next to each other.
public class Solution02 {
    public static void groupAnagrams(String[] words) {
        if(words == null) {
            return;
        }

        Map<String, List<String>> wordToAnagrams = new HashMap<>();
        for(String word : words) {
            String sortedWord = sortChars(word);
            List<String> anagrams = wordToAnagrams.get(sortedWord);
            if(anagrams == null) {
                anagrams = new ArrayList<>();
            }
            anagrams.add(word);
            wordToAnagrams.put(sortedWord, anagrams);
        }

        int wordCounter = 0;
        for(String sortedWord : wordToAnagrams.keySet()) {
            List<String> anagrams = wordToAnagrams.get(sortedWord);
            for(String anagram : anagrams) {
                words[wordCounter] = anagram;
                wordCounter++;
            }
        }
    }

    private static String sortChars(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

    public static void main(String[] args) {
        String[] words = new String[8];
        words[0] = "care";
        words[1] = "ogre";
        words[2] = "rife";
        words[3] = "door";
        words[4] = "gore";
        words[5] = "acre";
        words[6] = "race";
        words[7] = "fire";
        for(String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();

        groupAnagrams(words);
        for(String word : words) {
            System.out.print(word + " ");
        }
    }
}
