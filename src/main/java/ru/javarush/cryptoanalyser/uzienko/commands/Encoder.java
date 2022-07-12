package ru.javarush.cryptoanalyser.uzienko.commands;

import ru.javarush.cryptoanalyser.uzienko.constants.Strings;
import ru.javarush.cryptoanalyser.uzienko.entity.Result;
import ru.javarush.cryptoanalyser.uzienko.entity.ResultCode;
import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;
import ru.javarush.cryptoanalyser.uzienko.util.PathFinder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String txtFile = PathFinder.getRoot() + parameters[0];
        String encryptedFile = PathFinder.getRoot() + parameters[1];
        AlphabetConverter alphabetConverter = new AlphabetConverter(Strings.ALPHABET, Integer.parseInt(parameters[2]));
        try (FileReader fileReader = new FileReader(txtFile);
             FileWriter fileWriter = new FileWriter(encryptedFile)) {
            for (int i = fileReader.read(); i > 0; i = fileReader.read()) {
                fileWriter.write(alphabetConverter.convert((char) i));
            }
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all bytes from " + txtFile);
    }
}
