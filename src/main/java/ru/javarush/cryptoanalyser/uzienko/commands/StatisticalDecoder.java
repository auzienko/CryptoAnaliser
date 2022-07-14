package ru.javarush.cryptoanalyser.uzienko.commands;

import ru.javarush.cryptoanalyser.uzienko.entity.Result;
import ru.javarush.cryptoanalyser.uzienko.entity.ResultCode;
import ru.javarush.cryptoanalyser.uzienko.exceptions.ApplicationException;
import ru.javarush.cryptoanalyser.uzienko.util.FileCharsStat;
import ru.javarush.cryptoanalyser.uzienko.util.PathFinder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StatisticalDecoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        double epsilon = 0.01d;
        String encryptedFileName = PathFinder.getRoot() + parameters[0];
        String sourceFileName = PathFinder.getRoot() + parameters[1];
        String resultFileName = PathFinder.getRoot() + parameters[2];

        Map<Character, Double> encryptedStat = FileCharsStat.of(encryptedFileName);
        Map<Character, Double> sourceStat = FileCharsStat.of(sourceFileName);

        List<Map.Entry<Character,Double>> mapByRate = new ArrayList<>(sourceStat.entrySet());
        mapByRate.sort(Comparator.comparingDouble(Map.Entry::getValue));

        try (FileReader fileReader = new FileReader(encryptedFileName);
             FileWriter fileWriter = new FileWriter(resultFileName)) {
            for (int i = fileReader.read(); i > 0; i = fileReader.read()) {
                Double d = encryptedStat.get((char) i);
                for (Map.Entry<Character, Double> e: mapByRate) {
                    if (Math.abs(d - e.getValue()) < epsilon) {
                        i = e.getKey();
                        break;
                    }
                }
                fileWriter.write((char) i);
            }
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all bytes from ");
    }
}
