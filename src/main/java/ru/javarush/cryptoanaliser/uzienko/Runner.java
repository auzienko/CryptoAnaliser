package ru.javarush.cryptoanaliser.uzienko;

import ru.javarush.cryptoanaliser.uzienko.app.Application;
import ru.javarush.cryptoanaliser.uzienko.controllers.MainController;

public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);
    }
}
