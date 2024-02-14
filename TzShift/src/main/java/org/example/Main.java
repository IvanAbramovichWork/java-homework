package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String outputPath = parseOutputPath(args);
        String[] inputFiles = parseInputFiles(args);
        if (inputFiles.length == 0) {
            System.out.println("There is no input files provided");
            System.exit(1);
        }
        String prefix = parsePrefix(args);
        boolean isAppend = parseAppend(args);
        StatisticsType statisticsType = parseStatistics(args);

        try {
            TypeSorter typeSorter = new TypeSorter(outputPath, prefix, statisticsType, isAppend);
            for (var inputFile : inputFiles) {
                typeSorter.sortItemsFromFile(inputFile);
            }
            typeSorter.printStatistic();
        } catch (IOException e) {
            System.out.println("Something wrong with files");
        }

    }

    public static String parseOutputPath(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                String pathToFile = args[i + 1];
                if (!pathToFile.endsWith("/")) {
                    pathToFile += "/";
                }
                return pathToFile;
            }
        }
        return "./";
    }

    public static String parsePrefix(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                return args[i + 1];
            }
        }
        return "";
    }

    public static String[] parseInputFiles(String[] args) {
        List<String> inputs = new ArrayList<>();
        for (var i : args) {
            if (i.split("\\.").length > 1  && i.split("\\.")[1].equals("txt")) {
                inputs.add(i);
            }
        }
        return inputs.toArray(new String[0]);
    }

    public static boolean parseAppend(String[] args) {
        for (var i : args) {
            if (i.equals("-a")) {
                return true;
            }
        }
        return false;
    }

    public static StatisticsType parseStatistics(String[] args) {
        for (var i : args) {
            if (i.equals("-s")) {
                return StatisticsType.SHORT;
            }
            if (i.equals("-f")) {
                return StatisticsType.FULL;
            }
        }
        return StatisticsType.NONE;
    }

}
