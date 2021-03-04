package ru.job4j.ood.isp.menu;

import java.util.List;

public class MenuUsage {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        Element task1 = new Element("Task 1.", List.of(new AskMessage(), new SendMessage()));
        Element task11 = new Element("Task 1.1", List.of(new SendMessage()));
        Element task111 = new Element("Task 1.1.1", List.of(new SendMessage()));
        Element task112 = new Element("Task 1.1.2", List.of(new AskMessage()));
        Element task12 = new Element("Task 1.2", List.of(new SendMessage()));
        Element task2 = new Element("Task 2", List.of(new SendMessage()));
        task1.addChild(task11);
        task11.addChild(task111);
        task11.addChild(task112);
        task1.addChild(task12);
        menu.add(task1);
        menu.add(task2);
        menu.print();
        System.out.println(task2.getChildren());
        menu.choose("Task 1.").forEach(Action::doSomething);
    }
}
