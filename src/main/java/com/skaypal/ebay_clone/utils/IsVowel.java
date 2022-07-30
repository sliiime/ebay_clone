package com.skaypal.ebay_clone.utils;

import java.util.Set;

public class IsVowel {

    private static Set<Character> vowels = Set.of('A','a','E','e','I','i','O','o','U','u');

    public static boolean isVowel(char c){
        return vowels.contains(c);
    }
}
