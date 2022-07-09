package ru.javarush.cryptoanalyser.uzienko.commands;

import ru.javarush.cryptoanalyser.uzienko.entity.Result;
import ru.javarush.cryptoanalyser.uzienko.entity.ResultCode;
import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;
import ru.javarush.cryptoanalyser.uzienko.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Encoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        //TODO need dev logic encrypt
        String txtFile = PathFinder.getRoot() + parameters[0];
        String encryptedFile = PathFinder.getRoot() + parameters[1];
        Path path = Path.of(txtFile);
        try {
            List<String> strings = Files.readAllLines(path);
            strings.forEach(System.out::println);
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all bytes from " + txtFile);
    }
}
