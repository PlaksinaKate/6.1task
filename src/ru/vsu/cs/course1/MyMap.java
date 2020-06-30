package ru.vsu.cs.course1;

import java.util.ArrayList;

public class MyMap {

    public static ArrayList<Integer> findingWords(int s, int n, String text) {
        ArrayList<String> words = new ArrayList<>();
        reading(text, words);
        SimpleMap<Integer, String> word = new SimpleMap<>();
        for (String i : words) {
            if (checkingLettersInAWord(i, s)) {
                findingAndPutDuplicateWords(i, word, words);
            }
        }
        ArrayList<Integer> keys = new ArrayList<>(word.keySet());

        for (Integer i = 1; i < keys.size() - n + 1; i++) {
            word.remove(i);
        }

        return word.values();
    }

    public static  void reading(String text, ArrayList<String> words) {
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

    public static SimpleMap<Integer, String> findingAndPutDuplicateWords(String value, SimpleMap<Integer, String> word, ArrayList<String> words) {
        int t = 1;
        int num = 1;
        while (t != words.size() - words.indexOf(value) - 2) {
            if (value.equals(words.get(words.indexOf(value) + t))) {
                num++;
            }
            t++;
        }
        word.add(num, value);
        return word;
    }

    public static boolean checkingLettersInAWord(String i, int s) {
        if (i.length() == s) {
            return true;
        } else {
            return false;
        }
    }
}
