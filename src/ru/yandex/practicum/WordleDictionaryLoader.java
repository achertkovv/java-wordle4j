package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.nio.charset.StandardCharsets.UTF_8;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    String fileName;

    public WordleDictionaryLoader(String fileName) {
        this.fileName = fileName;
    }

    // Поэтому вам придётся прочитать файл целиком.
    private List<String> loadWordsFromFileToList() throws IOException {
        List<String> result = new ArrayList<>();
        // Для чтения строк вам пригодится класс FileReader, настроенный на кодировку UTF-8
        // А для чтения строк из файла рекомендуем использовать BufferedReader.
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName, UTF_8))) {
            String line;
            while ((line = fileReader.readLine()) != null) { // Читаем строки до конца файла
                result.add(line); // Добавляем строку в список
            }
        }
        return result;
    }

    // и только после этого выбрать из словаря те слова, которые подходят для игры.
    public WordleDictionary createWordleDictionary() {
        WordleDictionary wordleDictionary = new WordleDictionary();
        try {
            List<String> wordsFromFile = loadWordsFromFileToList();
            for (String word : wordsFromFile) {
                //  выбрать из словаря те слова, которые подходят для игры
                if (word.length() == 5) {
                    // Дополнительно вам нужно привести слова к единой
                    // форме в нижнем регистре и заменить букву ё на букву е
                    wordleDictionary.addWord(word.toLowerCase().replace("ё", "e"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wordleDictionary;
    }
}
