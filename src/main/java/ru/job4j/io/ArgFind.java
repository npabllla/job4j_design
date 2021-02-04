package ru.job4j.io;

public class ArgFind {
    private final String[] args;
    private final ArgsName argsName;

    public ArgFind(String[] args) {
        this.args = args;
        this.argsName = ArgsName.of(args);
    }

    public boolean valid() {
        return !argsName.get("d").isEmpty() && !argsName.get("n").isEmpty()
                && !argsName.get("t").isEmpty() && !argsName.get("o").isEmpty();
    }

    public String directory() {
        return argsName.get("d");
    }

    public String name() {
        return argsName.get("n");
    }

    public String type() {
        return argsName.get("t");
    }

    public String output() {
        return argsName.get("o");
    }
}
