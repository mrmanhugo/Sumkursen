import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class javaclass {
import java.util.*;

    public class Main {
        public static void main(String[] args) {
            Quiz quiz = new Quiz();
            quiz.start();
        }
    }

    class Glossary {
        private final Map<String, String> words;

        public Glossary() {
            words = new HashMap<>();
            words.put("hund", "dog");
            words.put("katt", "cat");
            words.put("bil", "car");
            words.put("hus", "house");
            words.put("bok", "book");
            words.put("äpple", "apple");
            words.put("penna", "pen");
            words.put("bord", "table");
            words.put("stol", "chair");
            words.put("dator", "computer");
        }

        public Map<String, String> getWords() {
            return words;
        }
    }

    class Quiz {
        private final Glossary glossary;
        private final WordChecker wordChecker;
        private final Statistics statistics;
        private final Scanner scanner;

        public Quiz() {
            glossary = new Glossary();
            wordChecker = new WordChecker();
            statistics = new Statistics();
            scanner = new Scanner(System.in);
        }

        public void start() {
            System.out.println("Welcome to the Swedish-English spelling quiz!");
            System.out.println("Type 'Q' to quit at any time.\n");

            int count = 0;
            for (Map.Entry<String, String> entry : glossary.getWords().entrySet()) {
                if (count >= 10) break;

                String swedishWord = entry.getKey();
                String correctEnglishWord = entry.getValue();

                System.out.print("Translate the word '" + swedishWord + "': ");
                String userAnswer = scanner.nextLine().trim();

                if (userAnswer.equalsIgnoreCase("Q")) {
                    break;
                }

                if (wordChecker.isCorrect(userAnswer, correctEnglishWord)) {
                    System.out.println("Correct!");
                    statistics.incrementCorrect();
                } else if (wordChecker.isAlmostCorrect(userAnswer, correctEnglishWord)) {
                    System.out.println("Almost correct!");
                } else {
                    System.out.println("Incorrect. The correct answer is '" + correctEnglishWord + "'.");
                }

                count++;
            }

            System.out.println("\nQuiz finished!");
            System.out.println("You got " + statistics.getCorrectAnswers() + " out of " + count + " correct.");
        }
    }

    class WordChecker {
        public boolean isCorrect(String userAnswer, String correctWord) {
            return userAnswer.equalsIgnoreCase(correctWord);
        }

        public boolean isAlmostCorrect(String userAnswer, String correctWord) {
            int correctLetterCount = 0;
            int length = Math.min(userAnswer.length(), correctWord.length());

            for (int i = 0; i < length; i++) {
                if (userAnswer.charAt(i) == correctWord.charAt(i)) {
                    correctLetterCount++;
                }
            }

            return correctLetterCount >= (correctWord.length() / 2.0);
        }
    }

    class Statistics {
        private int correctAnswers;

        public void incrementCorrect() {
            correctAnswers++;
        }

        public int getCorrectAnswers() {
            return correctAnswers;
        }
    }
}
