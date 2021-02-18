package ru.job4j.gc;

public class User {
    private int age;
    private String name;
    private char gender;

    public User() {

    }
    public User(int age, String name, char gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s %c%n", age, name, gender);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }


    public char getGender() {
        return gender;
    }

}
