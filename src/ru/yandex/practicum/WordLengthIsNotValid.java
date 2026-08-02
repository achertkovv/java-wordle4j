package ru.yandex.practicum;

public class WordLengthIsNotValid extends Exception {
    public WordLengthIsNotValid(String word) {
        super("Введенное слово " + word + " не соответствует правилам по его длине " + word.length());
    }
}
