package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        char gender = 'M';
        long someInfo = 1953L;
        byte children = 2;
        double height = 1.84;
        float weight = 80.4f;
        short something = 13;
        boolean status = true;
        LOG.debug("User info name : {}, age : {}, gender : {}, someInfo : {}, children : {}, height : {}, weight : {}, something : {}, status : {}",
                name, age, gender, someInfo, children, height, weight, something, status);
    }
}