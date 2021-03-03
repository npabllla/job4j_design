package ru.job4j.ood.isp.menu;

public class SendMessage implements Action {
    @Override
    public void doSomething() {
        System.out.println("Sending something...");
    }
}
