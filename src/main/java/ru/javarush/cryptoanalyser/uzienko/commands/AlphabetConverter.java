package ru.javarush.cryptoanalyser.uzienko.commands;

import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;

import java.util.HashMap;
import java.util.Map;

public class AlphabetConverter {
    private final String alphabet;
    private final int offset;
    private final Map<Character, Integer> alphabetMap;

    public AlphabetConverter(String alphabet, int offset) {
        this.alphabet = alphabet;
        if (alphabet.isEmpty()) {
            throw new ApplicationException("Alphabet can't be empty");
        }
        this.offset = offset % alphabet.length();
        alphabetMap = of(alphabet);
    }

    private Map<Character, Integer> of(String alphabet) {
        Map<Character, Integer> result = new HashMap<>();
        char[] chars = alphabet.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            result.put(chars[i], i);
        }
        return result;
    }

    public Character convert(Character character) {
        Integer val = alphabetMap.get(character);
        return val == null ?
                character : alphabet.charAt((alphabet.length() + val + offset) % alphabet.length());
    }
}
