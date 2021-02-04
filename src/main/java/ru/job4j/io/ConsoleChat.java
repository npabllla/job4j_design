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
        List<String> botAns = getBotAnswers(this.botAnswers);
        Random index = new Random();
        List<String> log = new ArrayList<>();
        String userPhrase = in.nextLine();
        String botAnswer = botAns.get(index.nextInt(botAns.size()));
        System.out.println(botAnswer);
        log.add("User: " + userPhrase + System.lineSeparator());
        log.add("Bot: " + botAnswer + System.lineSeparator());
        while (!userPhrase.equals(OUT)) {
            botAnswer = botAns.get(index.nextInt(botAns.size()));
            userPhrase = in.nextLine();
            if (userPhrase.equals(STOP)) {
                log.add("User: " + userPhrase + System.lineSeparator());
                while (!userPhrase.equals(CONTINUE)) {
                    userPhrase = in.nextLine();
                    if (!userPhrase.equals(CONTINUE)) {
                        log.add("User: " + userPhrase + System.lineSeparator());
                    }
                }
            }
            log.add("User: " + userPhrase + System.lineSeparator());
            if (!userPhrase.equals(OUT)) {
                System.out.println(botAnswer);
                log.add("Bot: " + botAnswer + System.lineSeparator());
            }
        }
        writeLog(log, this.path);
    }

    private static void writeLog(List<String> log, String path) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            for (String st : log) {
                br.write(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getBotAnswers(String path) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/botAnswers.txt");
        cc.run();
    }
}
