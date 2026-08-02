package ru.yandex.practicum;

public class WordNotFoundInDictionary extends Exception {
    public WordNotFoundInDictionary(String word) {
        super("Введенное слово " + word + " не присутствует в словаре!");
    }
}
