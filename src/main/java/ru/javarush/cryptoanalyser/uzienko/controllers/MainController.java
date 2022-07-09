package ru.javarush.cryptoanalyser.uzienko.controllers;

import ru.javarush.cryptoanalyser.uzienko.commands.Action;
import ru.javarush.cryptoanalyser.uzienko.entity.Result;

public class MainController {
    public Result execute(String command, String[] parameters) {
        Action action = Actions.find(command);
        Result result = action.execute(parameters);
        return result;
    }
}
