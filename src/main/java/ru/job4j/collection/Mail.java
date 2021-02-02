package ru.job4j.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mail {
    public static Map<String, Set<String>> merge(Map<String, Set<String>> mails) {
        if (mails.size() < 2) {
            return mails;
        }
        Map<String, Set<String>> temp = new HashMap<>();
        for (String key : mails.keySet()) {
            int f = 0;
            if (temp.size() == 0) {
                temp.put(key, mails.get(key));
            } else {
                for (String tempKey : temp.keySet()) {
                    if (containsAny(temp.get(tempKey), mails.get(key))) {
                        Set<String> tmp = new HashSet<>(temp.get(tempKey));
                        tmp.addAll(mails.get(key));
                        temp.put(tempKey, tmp);
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

    private static boolean containsAny(Set<String> arr1, Set<String> arr2) {
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
