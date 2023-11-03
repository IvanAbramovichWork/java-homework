package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        Person[] persons = new Person[13];
        persons[0] = new Person("Ivan", 23, "male");
        persons[1] = new Person("John", 22, "male");
        persons[2] = new Person("Jack", 32, "male");
        persons[3] = new Person("Anna", 18, "female");
        persons[4] = new Person("Victor", 45, "male");
        persons[5] = new Person("Misha", 14, "male");
        persons[6] = new Person("Igor", 25, "male");
        persons[7] = new Person("Katya", 20, "female");
        persons[8] = new Person("Masha", 27, "female");
        persons[9] = new Person("Dasha", 23, "female");
        persons[10] = new Person("Sam", 56, "male");
        persons[11] = new Person("Bob", 40, "male");
        persons[12] = new Person("Bill", 30, "male");

        Person[] newPersons = sortByAge(persons);
        try (
                PrintWriter writer = new PrintWriter("out.txt");
        ) {
            for (Person p :
                    newPersons) {
                writer.println(p.name + " " + p.age + " " + p.gender);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Person[] sortByAge(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            int j_max = i;
            for (int j = i + 1; j < persons.length; j++) {
                if (persons[j_max].age < persons[j].age) {
                    j_max = j;
                }
            }
            Person temp = persons[i];
            persons[i] = persons[j_max];
            persons[j_max] = temp;
        }
        return persons;
    }

    public static Person[] sortByName(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            int j_max = i;
            for (int j = i + 1; j < persons.length; j++) {
                if (persons[j_max].name.compareTo(persons[j].name) < 0) {
                    j_max = j;
                }
            }
            Person temp = persons[i];
            persons[i] = persons[j_max];
            persons[j_max] = temp;
        }
        return persons;
    }
}
