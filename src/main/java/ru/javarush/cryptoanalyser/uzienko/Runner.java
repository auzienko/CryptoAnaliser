package ru.javarush.cryptoanalyser.uzienko;

import ru.javarush.cryptoanalyser.uzienko.toplevel.Application;
import ru.javarush.cryptoanalyser.uzienko.controllers.MainController;
import ru.javarush.cryptoanalyser.uzienko.entity.Result;

public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);
    }
}
