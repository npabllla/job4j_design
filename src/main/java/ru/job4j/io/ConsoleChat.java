package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.path))) {
            String userPhrase = in.nextLine();
            String botAnswer = getBotAnswers(this.botAnswers);
            System.out.println(botAnswer);
            br.write("User: " + userPhrase + System.lineSeparator());
            br.write("Bot: " + botAnswer + System.lineSeparator());
            while (!userPhrase.equals(OUT)) {
                botAnswer = getBotAnswers(this.botAnswers);
                userPhrase = in.nextLine();
                if (userPhrase.equals(STOP)) {
                    br.write("User: " + userPhrase + System.lineSeparator());
                    while (!userPhrase.equals(CONTINUE)) {
                        userPhrase = in.nextLine();
                        if (!userPhrase.equals(CONTINUE)) {
                            br.write("User: " + userPhrase + System.lineSeparator());
                        }
                    }
                }
                br.write("User: " + userPhrase + System.lineSeparator());
                if (!userPhrase.equals(OUT)) {
                    System.out.println(botAnswer);
                    br.write("Bot: " + botAnswer + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getBotAnswers(String path) throws IOException {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random index = new Random();
        return answers.get(index.nextInt(answers.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/botAnswers.txt");
        cc.run();
    }
}
