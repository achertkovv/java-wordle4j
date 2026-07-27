package ru.yandex.practicum;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    private static final String logFileName = "Errors.log";
    private static final String wordsFileName = "words_ru.txt";

    public static void main(String[] args) throws IOException {
        //Создайте и используйте лог-файл, запись в который производится через специальный класс PrintWriter.
        PrintWriter printWriter = new PrintWriter(logFileName);
        // Для отлова всех ошибок и их вывода в лог-файл выберите в коде метод,
        // который будет целиком охвачен try .. catch, например метод main.
        // Он должен обеспечивать возможность перехвата всех ошибок, возникающих
        // в ваших классах.
        try {
            // создать загрузчик словарей WordleDictionaryLoader
            WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader(wordsFileName);
            // загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
            WordleDictionary wordleDictionary = wordleDictionaryLoader.createWordleDictionary();
            // затем создать игру WordleGame и передать ей словарь
            // Количество оставшихся шагов устанавливается равным шести.
            WordleGame wordleGame = new WordleGame(wordleDictionary, 6);
            // вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
            mainWordleGameCycle(wordleGame);
            //  в конце для информации выводится загаданное слово.
            if (wordleGame.getSteps() == 0) // у игрока закончились шаги, а слово ещё не отгадано (это проигрыш).
                System.out.println("Загаданное слово было: " + wordleGame.getAnswer());
        } catch (Exception e) {
            printWriter.write(e);
        }
    }

    private static void mainWordleGameCycle(WordleGame wordleGame) throws IOException {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int randomIndex = random.nextInt(wordleGame.getDictionary().getWords().size());
        // В первую очередь компьютер скрытно от пользователя «загадывает» слово —
        // существительное в единственном числе в именительном падеже. Используйте для
        // этого словарь русских слов, состоящих из пяти букв.
        String randomWord = wordleGame.getDictionary().getWords().get(randomIndex);
        wordleGame.setAnswer(randomWord);
        System.out.println("Загадал слово!");
        // Игроку доступно шесть попыток (закончились ходы - игра завершается)
        while (wordleGame.getSteps() > 0) {
            try {
                System.out.print("Угадайте слово: ");
                String word = sc.nextLine();
                // Дополнительно вам нужно привести слова к единой
                // форме в нижнем регистре и заменить букву ё на букву е
                word = word.toLowerCase().replace("ё", "e");
                // Дополнительно программа проверяет, что слово соответствует правилам:
                // состоит из пяти букв и присутствует в словаре. Если слово корректное,
                // ход засчитывается, иначе программа будет повторно ожидать ввод
                // правильного слова, и ход засчитан не будет.
                wordleGame.checkWord(word);

                String hintWord = wordleGame.fillHintWords(word);
                if (word.isBlank()) {
                    word = hintWord;
                    printHintWords(wordleGame);
                }

                if (wordleGame.compareWord(word)) {
                    System.out.println("Вы угадали слово и выиграли!");
                    break; // Если слово отгадано, игра завершается. Игрок отгадал слово (это выигрыш);
                }
                System.out.println(word);
                System.out.println(wordleGame.analyzeWord(word));
            } catch (WordNotFoundInDictionary | WordLengthIsNotValid e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printHintWords(WordleGame wordleGame) {
        int maxValue = Collections.max(wordleGame.getHintWords().values());
        System.out.println("Подсказка: ");
        for (Map.Entry<String, Integer> entry : wordleGame.getHintWords().entrySet()) {
            if (entry.getValue() == maxValue) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

}
