package ru.yandex.practicum;

import java.util.*;

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

    // Map для подсчёта уникальных слов и их количества.
    private final LinkedHashMap<String, Integer> mapHintWords = new LinkedHashMap<>();

    public WordleGame(WordleDictionary dictionary, int steps) {
        this.dictionary = dictionary;
        this.steps = steps;
    }

    public LinkedHashMap<String, Integer> getHintWords() {
        return mapHintWords;
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

    public void checkWord(String word) throws WordNotFoundInDictionary, WordLengthIsNotValid {
        if (word.isBlank()) return; // Пустые слова означают, что требуется подсказка
        if (word.length() != 5) throw new WordLengthIsNotValid(word);
        if (!dictionary.containsWord(word)) throw new WordNotFoundInDictionary(word);
        steps--; //Если слово подходит под правила игры, то количество попыток уменьшается
    }

    public String analyzeWord(String word) {
        // Для вывода сложных сообщений о состоянии игры используйте StringBuilder —
        // например, при анализе введённого слова.
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

    public String fillHintWords(String word) {
        if (word.isBlank()) {
            // Исправление замечаний:
            // По ТЗ компьютер подсказывает произвольное слово, у вас же всегда берется result.getFirst(),
            // то есть первое по алфавиту. При повторном запросе игрок будет получать один и тот же вариант.
            Random random = new Random();
            int randomIndex = random.nextInt(dictionary.getWords().size());
            word = dictionary.getWords().get(randomIndex);
            steps--; //Если слово подходит под правила игры, то количество попыток уменьшается
        }
        String pattern = analyzeWord(word);

        // В словаре вы будете встречать слова с буквами, которые уже не подходят к решению, — их нужно отсеять
        StringBuilder excludeChars = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++)
            if (pattern.charAt(i) == '-') excludeChars.append(word.charAt(i));

        List<String> exclude = dictionary.containsCharsInTheList(excludeChars.toString());

        dictionary.removeWords(exclude);

        listToMap();

        // Затем из оставшихся слов надо выбрать те, в которых все необходимые буквы присутствуют.
        StringBuilder includeChars = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++)
            if (pattern.charAt(i) == '+' || pattern.charAt(i) == '^') includeChars.append(word.charAt(i));

        List<String> include = dictionary.containsAllCharsInTheList(includeChars.toString());

        dictionary.setWords(include);

        listToMap();

        // Далее из подходящих слов выбираются те, в которых нужные буквы находятся на нужных местах
        // (если игроку удалось угадать хоть одну такую букву).
        HashMap<Character, Integer> charsPisitionMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '+') {
                charsPisitionMap.put(word.charAt(i), i);
            }
        }

        include = dictionary.containsCharsInTheListByIndex(charsPisitionMap);

        dictionary.setWords(include);

        listToMap();

/*        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(mapHintWords.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });*/

        return word;
    }

    private void listToMap() {
        if (!dictionary.getWords().isEmpty()) {
            mapHintWords.clear();
            for (String s : dictionary.getWords()) {
                mapHintWords.put(s, mapHintWords.getOrDefault(s, 0) + 1);
            }
        }
    }

    public boolean compareWord(String word) {
        return answer.equals(word);
    }
}
