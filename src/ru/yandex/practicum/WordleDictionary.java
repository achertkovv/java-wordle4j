package ru.yandex.practicum;

import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private final List<String> words;

    public WordleDictionary() {
        this.words = new ArrayList<>();
    }

    public List<String> getWords() {
        return words;
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public void addWord(String word) {
        words.add(word);
    }

    public static List<String> containsCharsInTheList(List<String> list, String chars) {
        List<String> result = new ArrayList<>();
        Set<Character> distinctChars = new HashSet<>();
        for (char ch : chars.toCharArray()) {
            distinctChars.add(ch);
        }
        for (char c : distinctChars) {
            for (String word : list) {
                if (word.contains(String.valueOf(c)))
                    result.add(word);
            }
        }
        return result;
    }

    public static List<String> containsCharsInTheListByIndex(List<String> list, char c, int i) {
        List<String> result = new ArrayList<>();
        for (String word : list) {
            if (word.charAt(i) == c) {
                result.add(word);
            }
        }
        return result;
    }
}
