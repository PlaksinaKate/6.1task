package  ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NotMyMap {
    public static Collection<String> findingWord(int s, int n, String text) {
        ArrayList<String> words = new ArrayList<>();
        reading(text,words);
        HashMap<Integer, String> word = new HashMap<>();
        for (String i : words) {
            if (checkingLettersInAWord(s, i)) {
                findingDuplicateWords(i, word, words);
            }
        }

        ArrayList<Integer> keys = new ArrayList<>(word.keySet());
        for (Integer i = 1; i < keys.size() - n + 1; i++) {
            word.remove(i);
        }
        return word.values();

    }
    public static void reading(String text, ArrayList<String> words) {
        text = text.replaceAll("\n", " ");
        text = text.replaceAll("\r", " ");
        text = text.replaceAll("—", " ");
        String[] txt = text.split(" ");

        for (String t : txt) {
            if (!(t.equals("") || t.equals("-"))){
                words.add(t);
            }
        }

    }


    //нахождение повторяющихся слов
    public static HashMap<Integer, String> findingDuplicateWords(String i, HashMap<Integer, String> word, ArrayList<String> words) {
        int t = 1;
        int num = 1;
        while (t != words.size() - words.indexOf(i) - 2) {
            if (i.equals(words.get(words.indexOf(i) + t))) {
                num++;
            }
            t++;
        }
        word.put(num, i);
        return word;
    }

    public static boolean checkingLettersInAWord(int s, String words) {
        if (words.length() == s) {
            return true;
        } else {
            return false;
        }
    }
}
