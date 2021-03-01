package ru.job4j.ood.srp.design;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}