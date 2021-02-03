package ru.job4j.io;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 6) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String directory() {
        if (valid()) {
            return args[1];
        }
        return null;
    }


    public String exclude() {
        if (valid()) {
            return args[3].substring(2);
        }
        return null;
    }

    public String output() {
        if (valid()) {
            return args[5];
        }
        return null;
    }
}