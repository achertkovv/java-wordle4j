package ru.yandex.practicum;

import java.util.LinkedHashMap;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer;

    private int steps;

    private final WordleDictionary dictionary;

    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    public WordleGame(WordleDictionary dictionary, int steps) {
        this.dictionary = dictionary;
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public WordleDictionary getDictionary() {
        return dictionary;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean checkWord(String word) {
        if (word.length() != 5 || !dictionary.containsWord(word)) return false;
        steps--; //Если слово подходит под правила игры, то количество попыток уменьшается
        return true;
    }

    public String analyzeWord(String word) {
        StringBuilder sb = new StringBuilder();
        // Для анализа символов строки используйте методы String для извлечения
        // отдельных символов.
        for (int i = 0; i < word.length(); i++) {
            if (answer.charAt(i) == word.charAt(i)) sb.append("+");
            else if (answer.contains(String.valueOf(word.charAt(i)))) sb.append("^");
            else sb.append("-");
        }
        return sb.toString();
    }

    public String hintWord() {
        String result = "";
        if (map.isEmpty()) result = dictionary.getWords().getFirst();
        else result = map.keySet().
        map.put(result, dictionary.containsLetter())

        return result;
    }

    public boolean compareWord(String word) {
        return answer.equals(word);
    }
}
