package ru.job4j.io;

public class Shell {
    private String result = "";

    public void cd(String path) {
        StringBuilder sb = new StringBuilder();
        if (path.equals("/") || path.contains("..")) {
            result = "/";
        } else {
            sb.append(result).append("/").append(path);
            result = sb.toString();
        }
    }

    public String pwd() {
        return result;
    }
}
