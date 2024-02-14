package org.example;

import java.io.*;
import java.math.BigDecimal;

public class TypeSorter {
    private String prefix = "";
    private StatisticsType statisticsType = StatisticsType.NONE;
    private boolean append = false;
    private final Statistic statistic = new Statistic();
    private int fileCounter = 0;
    private String outputPath = "./";

    public TypeSorter(String outputPath, String prefix, StatisticsType statisticsType, boolean append) {
        this.outputPath = outputPath;
        this.append = append;
        this.prefix = prefix;
        this.statisticsType = statisticsType;
    }

    public TypeSorter(String outputPath, StatisticsType statisticsType, boolean append) {
        this.outputPath = outputPath;
        this.statisticsType = statisticsType;
        this.append = append;
    }

    public TypeSorter(String outputPath, StatisticsType statisticsType) {
        this.outputPath = outputPath;
        this.statisticsType = statisticsType;
    }

    public void sortItemsFromFile(String inputFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split("\\s+");
                for (var token : tokens) {
                    switch (returnType(token)) {
                        case STRING -> {
                            if (statistic.getStringWritten() == 0) {
                                writeToFile(token, outputPath + prefix + "strings.txt", this.append);
                            } else {
                                writeToFile(token, outputPath + prefix + "strings.txt", true);
                            }
                            statistic.addString(token);
                        }
                        case FLOAT -> {
                            if (statistic.getFloatsWritten() == 0) {
                                writeToFile(token, outputPath + prefix + "floats.txt", this.append);
                            } else {
                                writeToFile(token, outputPath + prefix + "floats.txt", true);
                            }
                            statistic.addFloat(Double.parseDouble(token));
                        }
                        case INTEGER -> {
                            if (statistic.getIntegersWritten() == 0) {
                                writeToFile(token, outputPath + prefix + "integers.txt", this.append);
                            } else {
                                writeToFile(token, outputPath + prefix + "integers.txt", true);
                            }
                            statistic.addInteger(Long.parseLong(token));
                        }
                    }
                }

                line = br.readLine();
            }
            fileCounter++;
        }
    }

    private Types returnType(String obj) {
        try {
            Long ln = Long.parseLong(obj);
            return Types.INTEGER;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(obj);
                return Types.FLOAT;
            } catch (NumberFormatException e2) {
                return Types.STRING;
            }
        }
    }

    private void writeToFile(String str, String outputFileName, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, append));
        writer.write(str + "\n");
        writer.close();
    }

    public void printStatistic() {
        if (statisticsType.equals(StatisticsType.FULL)) {
            statistic.printFullStatistic();
        } else if (statisticsType.equals(StatisticsType.SHORT)) {
            statistic.printShortStatistic();
        } else {
            System.out.println("Sorted successfully");
        }
    }
}
