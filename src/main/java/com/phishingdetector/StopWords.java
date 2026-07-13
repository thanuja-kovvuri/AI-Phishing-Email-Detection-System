package com.phishingdetector;

import java.util.HashSet;

public class StopWords {

    public static HashSet<String> words = new HashSet<>();

    static {

        String[] stop = {

            "a","an","the","is","are","was","were",

            "to","of","for","from","on","in","at",

            "with","without","by","this","that",

            "these","those","it","its","be","been",

            "being","and","or","as","if","into",

            "about","after","before","than","then",

            "you","your","our","their","they","he",

            "she","we","i","me","my","us"

        };

        for(String s : stop)
            words.add(s);
    }

}