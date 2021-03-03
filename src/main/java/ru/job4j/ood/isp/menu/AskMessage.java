package ru.job4j.ood.isp.menu;

public class AskMessage implements Action {
    @Override
    public void doSomething() {
        System.out.println("Asking...");
    }
}
