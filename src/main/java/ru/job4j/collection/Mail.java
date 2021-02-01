package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Mail {

    public static HashMap<String, String[]> merge(HashMap<String, String[]> mails) {
        if (mails.size() < 2) {
            return mails;
        }
        HashMap<String, String[]> temp = new HashMap<>();
        for (String key : mails.keySet()) {
            int f = 0;
            if (temp.size() == 0) {
                temp.put(key, mails.get(key));
            } else {
                for (String tempKey : temp.keySet()) {
                    if (containsAny(temp.get(tempKey), mails.get(key))) {
                        temp.put(tempKey, Stream
                                .concat(Arrays.stream(temp.get(tempKey)), Arrays.stream(mails.get(key)))
                                .distinct()
                                .toArray(String[]::new));
                        f++;
                    }
                }
            }
            if (f == 0) {
                temp.put(key, mails.get(key));
            }
        }
        return temp;
    }

    private static boolean containsAny(String[] arr1, String[] arr2) {
        for (String value : arr1) {
            for (String s : arr2) {
                if (value.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

}
