package ru.job4j.ood.srp.design;

public interface Serialize<T, U> {
    T serialize(U object);
}
