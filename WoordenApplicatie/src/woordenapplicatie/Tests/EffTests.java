package woordenapplicatie.Tests;

import org.junit.BeforeClass;
import org.junit.Test;
import woordenapplicatie.gui.WoordenController;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EffTests {
    WoordenController controller;
    static String[] wordsTenThousand;
    static String[] wordsMillion;
    static ArrayList<String[]> wordLinesThousend;
   static ArrayList<String[]> wordLinesMillion;


    public EffTests(){
        controller = new WoordenController();
    }

    @BeforeClass
    public static void Setup() {
        String path = Paths.get("C:\\Users\\20182942\\Documents\\Fontys\\S4 EFF\\WoordenApplicatie").toAbsolutePath().normalize().toString();
        Path fileTenThousand = Paths.get(path + "/tenThousand.txt");

        try {
            List<String> words = Files.readAllLines(fileTenThousand);
            wordsTenThousand = words.toArray(new String[words.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path fileMillion = Paths.get(path + "/million.txt");
        try {
            List<String> words = Files.readAllLines(fileMillion);
            wordsMillion = words.toArray(new String[words.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path fileLinesTHousend = Paths.get(path + "/linesTenThousand.txt");
        try {
            wordLinesThousend = new ArrayList<>();
            FileInputStream stream = new FileInputStream(fileLinesTHousend.toString());
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().replaceAll("^[,\\s]+", "").split("[,\\s]+");
                wordLinesThousend.add(words);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path fileLinesMillion = Paths.get(path + "/linesMillion.txt");
        try {
            wordLinesMillion = new ArrayList<>();
            FileInputStream stream = new FileInputStream(fileLinesMillion.toString());
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().replaceAll("^[,\\s]+", "").split("[,\\s]+");
                wordLinesMillion.add(words);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countTenThousendTest(){
        long startTime = System.nanoTime();
        controller.countsFunction(wordsTenThousand);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalTenThousendHashSet: " + (endTime-startTime));
    }

    @Test
    public void countMillionTest(){
        long startTime = System.nanoTime();
        controller.countsFunction(wordsMillion);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalMillionHashSet: " + (endTime-startTime));
    }

    @Test
    public void sorteerTenThousendTest(){
        long startTime = System.nanoTime();
        controller.sorteerFunction(wordsTenThousand);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalTenThousendTreeSet: " + (endTime-startTime));
    }

    @Test
    public void sorteerMillionTest(){
        long startTime = System.nanoTime();
        controller.sorteerFunction(wordsMillion);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalMillionTreeSet: " + (endTime-startTime));
    }

    @Test
    public void frequentieTenThousendTest(){
        long startTime = System.nanoTime();
        controller.frequantieFunction(wordsTenThousand);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalTenThousendTreeMap: " + (endTime-startTime));
    }

    @Test
    public void frequentieMillionTest(){
        long startTime = System.nanoTime();
        controller.frequantieFunction(wordsMillion);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalMillionTreeMap: " + (endTime-startTime));
    }

    @Test
    public void concordatieTenThousendTest(){
        long startTime = System.nanoTime();
        controller.concordatieFunction(wordLinesThousend);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalTenThousendHashMap: " + (endTime-startTime));
    }

    @Test
    public void concordatieMillionTest(){
        long startTime = System.nanoTime();
        controller.concordatieFunction(wordLinesThousend);
        long endTime = System.nanoTime();
        System.out.println("Time in nanoseconds aantalMillionHashMap: " + (endTime-startTime));
    }

}
