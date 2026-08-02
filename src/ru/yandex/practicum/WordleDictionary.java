package ru.yandex.practicum;

import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words;

    public WordleDictionary() {
        this.words = new ArrayList<>();
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> updateWords) {
        if (!updateWords.isEmpty())
            this.words = new ArrayList<>(updateWords);
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public void addWord(String word) {
        words.add(word);
    }

    public void removeWords(List<String> excludeWords) {
        words.removeAll(excludeWords);
    }

    public List<String> containsCharsInTheList(String chars) {
        List<String> result = new ArrayList<>();
        Set<Character> distinctChars = new HashSet<>();
        for (char ch : chars.toCharArray()) {
            distinctChars.add(ch);
        }
        for (char c : distinctChars) {
            for (String word : words) {
                if (word.contains(String.valueOf(c))) result.add(word);
            }
        }
        return result;
    }

    public List<String> containsAllCharsInTheList(String chars) {
        List<String> result = new ArrayList<>();
        Set<Character> distinctChars = new HashSet<>();
        boolean wordContainsAllChars;
        for (char ch : chars.toCharArray()) {
            distinctChars.add(ch);
        }
        for (String word : words) {
            wordContainsAllChars = true;
            for (char c : distinctChars) {
                if (!word.contains(String.valueOf(c))) {
                    wordContainsAllChars = false;
                    break;
                }
            }
            if (wordContainsAllChars) result.add(word);
        }
        return result;
    }

    public  List<String> containsCharsInTheListByIndex(Map<Character, Integer> charsPisitionMap) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            for (Map.Entry<Character, Integer> entry : charsPisitionMap.entrySet()) {
                if (word.charAt(entry.getValue()) == entry.getKey()) {
                    result.add(word);
                }
            }
        }
        return result;
    }
}
