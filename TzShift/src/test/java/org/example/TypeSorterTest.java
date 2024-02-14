package org.example;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TypeSorterTest {
    private static TypeSorter typeSorter;
    private static int linesWritten = 0;
    private static int linesToGenerate;

    @BeforeAll
    public static void setUp() {
        // Initialize TypeSorter with some default values for testing
        linesToGenerate = 100000;
        typeSorter = new TypeSorter("src/test/resources/", "test_", StatisticsType.SHORT, false);
        generateTestData("src/test/resources/test_input.txt", linesToGenerate);
    }

    @Test
    void testSortItemsFromFile() {
        // Assuming you have a test file named "test_input.txt" in your test resources directory
        String testInputFilePath = getClass().getClassLoader().getResource("test_input.txt").getPath();

        try {
            typeSorter.sortItemsFromFile(testInputFilePath);
        } catch (IOException e) {
            fail("IOException occurred during sorting: " + e.getMessage());
        }

        // Assert that files have been created
        assertTrue(new File("src/test/resources/test_strings.txt").exists());
        assertTrue(new File("src/test/resources/test_floats.txt").exists());
        assertTrue(new File("src/test/resources/test_integers.txt").exists());
        assertEquals(linesWritten, linesToGenerate);
    }

    public static void generateTestData(String fileName, int numLines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < numLines; i++) {
                int dataType = random.nextInt(3); // 0: string, 1: float, 2: integer
                switch (dataType) {
                    case 0:
                        writer.write(generateRandomString(random.nextInt(20) + 1));
                        linesWritten++;
                        break;
                    case 1:
                        writer.write(String.format("%.2f", random.nextFloat() * 100));
                        linesWritten++;
                        break;
                    case 2:
                        writer.write(Integer.toString(random.nextInt(1000)));
                        linesWritten++;
                        break;
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789абвгдеёжзиклмнопрстеегзацвсьцрмк";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
