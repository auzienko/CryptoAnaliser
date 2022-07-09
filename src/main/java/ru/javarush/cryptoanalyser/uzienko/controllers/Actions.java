package ru.javarush.cryptoanalyser.uzienko.controllers;

import ru.javarush.cryptoanalyser.uzienko.commands.Action;
import ru.javarush.cryptoanalyser.uzienko.commands.Decoder;
import ru.javarush.cryptoanalyser.uzienko.commands.Encoder;
import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    ;

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        try {
            return Actions.valueOf(command.toUpperCase()).action;
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("No enum constant " + command, e);
        }
    }

}
