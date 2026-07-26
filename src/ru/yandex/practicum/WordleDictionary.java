package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Integer> getMapOfWords(String word, String format) {
        Map<String, Integer> mapOfWords = new LinkedHashMap<>();
        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) == '+') {
            }
        }
        return mapOfWords;
    }
}
