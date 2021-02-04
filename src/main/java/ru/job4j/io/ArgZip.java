package ru.job4j.io;

public class ArgZip {
    private final String[] args;
    private final ArgsName argsName;

    public ArgZip(String[] args) {
        this.args = args;
        this.argsName = ArgsName.of(args);
    }

    public boolean valid() {
        return !argsName.get("d").isEmpty() && !argsName.get("e").isEmpty() && !argsName.get("o").isEmpty();
    }

    public String directory() {
        return argsName.get("d");
    }


    public String exclude() {
        return argsName.get("e");
    }

    public String output() {
        return argsName.get("o");
    }
}