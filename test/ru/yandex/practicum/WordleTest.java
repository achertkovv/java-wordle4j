package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleTest {
    private static WordleDictionary wordleDictionary;
    private static WordleGame wordleGame;

    @BeforeAll
    static void testGetWordleDictionary() {
        WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader("words_ru.txt");
        wordleDictionary = wordleDictionaryLoader.createWordleDictionary();
        wordleGame = new WordleGame(wordleDictionary, 6);
    }

    @Test
    void testWordleGame() throws WordLengthIsNotValid, WordNotFoundInDictionary {
        //Проверить, сколько всего шагов (должно быть 6)
        assertEquals(6, wordleGame.getSteps());

        //Проверить, сколько по-умолчанию подсказок (должно быть 0)
        assertEquals(0, wordleGame.getHintWords().size());

        //Устанавливаем слово
        wordleGame.setAnswer("аббат");

        //Проверить корректность проверки на правильность ответа
        assertTrue(wordleGame.compareWord("аббат"));

        //Проверить корректность проверки слов
        assertFalse(wordleGame.compareWord(""));

        //Проверить, что правильно генерируется шаблон строки после анализа
        assertEquals("++---", wordleGame.analyzeWord("абвер"));

        wordleGame.fillHintWords("абвер");

        //Проверить, что подсказка заполнились, после передачи слова (должно быть не 0)
        assertNotEquals("", wordleGame.fillHintWords(""));

        //Проверить, кол-во подсказок изменилось (не должно быть равно 0)
        assertNotEquals(0, wordleGame.getHintWords().size());

        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 5)
        assertEquals(5, wordleGame.getSteps());
        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 4)
        assertEquals(4, wordleGame.getSteps());
        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 3)
        assertEquals(3, wordleGame.getSteps());
        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 2)
        assertEquals(2, wordleGame.getSteps());
        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 1)
        assertEquals(1, wordleGame.getSteps());
        //Уменьшаем счетчик шагов
        wordleGame.checkWord("абвер");
        //Проверить, сколько что число шагов уменьшилось на 1 (должно стать 0)
        assertEquals(0, wordleGame.getSteps());
    }

    @Test
    void testWordleDictionary() {
        //Проверить, сколько всего слов в словаре
        assertNotEquals(0, wordleDictionary.getWords().size());

        //Проверить, что есть слово, например "аббат"
        assertTrue(wordleDictionary.containsWord("аббат"));

        //Проверить, что нет слов на английском, например "abbot"
        assertFalse(wordleDictionary.containsWord("abbot"));

    }
}
