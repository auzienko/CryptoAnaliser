package ru.javarush.cryptoanalyser.uzienko.commands;

import ru.javarush.cryptoanalyser.uzienko.constants.Strings;
import ru.javarush.cryptoanalyser.uzienko.entity.Result;
import ru.javarush.cryptoanalyser.uzienko.entity.ResultCode;
import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;
import ru.javarush.cryptoanalyser.uzienko.util.PathFinder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String encryptedFile = PathFinder.getRoot() + parameters[0];
        String decryptedFile = PathFinder.getRoot() + parameters[1];
        AlphabetConverter alphabetConverter = new AlphabetConverter(Strings.ALPHABET, -Integer.parseInt(parameters[2]));
        try (FileReader fileReader = new FileReader(encryptedFile);
             FileWriter fileWriter = new FileWriter(decryptedFile)) {
            for (int i = fileReader.read(); i > 0; i = fileReader.read()) {
                fileWriter.write(alphabetConverter.convert((char) i));
            }
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all bytes from " + encryptedFile);
    }
}
