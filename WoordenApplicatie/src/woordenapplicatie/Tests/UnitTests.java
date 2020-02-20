package woordenapplicatie.Tests;

import org.junit.Test;
import woordenapplicatie.gui.WoordenController;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class UnitTests {
    WoordenController controller = new WoordenController();
    String[] words = {"arjen", "thijn", "cesar", "michiel", "danny", "nick"};
    String[] words2 = {"rens", "rick", "sjaak", "sjaak"};

    @Test
    public void countTest(){
        int[] output = controller.countsFunction(words);

        assertEquals(6, output[0]);
        assertEquals(6, output[1]);
    }

    @Test
    public void countTest2(){
        int[] output = controller.countsFunction(words2);

        assertEquals(4, output[0]);
        assertEquals(3, output[1]);
    }

    @Test
    public void sorteerTest(){
        String[] expected = {"thijn", "nick", "michiel", "danny", "cesar", "arjen"};

        TreeSet<String> output = controller.sorteerFunction(words);
        Object[] outputArray = output.toArray();


        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], outputArray[i]);
        }
    }

    @Test
    public void sorteerTest2(){
        String[] expected = {"sjaak", "rick", "rens"};

        TreeSet<String> output = controller.sorteerFunction(words2);
        Object[] outputArray = output.toArray();


        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], outputArray[i]);
        }
    }

    @Test
    public void frequentieTest(){
        String[] expectedKey = {"arjen", "cesar", "danny", "michiel", "nick", "thijn"};
        int[] expectedValue = {1, 1, 1, 1, 1, 1};

        TreeMap<String, Integer> output = controller.frequantieFunction(words);

        int i = 0;
        for(Map.Entry<String,Integer> entry : output.entrySet())
        {
            String key = entry.getKey();
            int value = entry.getValue();

            assertEquals(expectedKey[i], key);
            assertEquals(expectedValue[i], value);
            i++;
        }
    }

    @Test
    public void frequentieTest2(){
        String[] expectedKey = {"rens", "rick", "sjaak"};
        int[] expectedValue = {1, 1, 2};

        TreeMap<String, Integer> output = controller.frequantieFunction(words2);

        int i = 0;
        for(Map.Entry<String,Integer> entry : output.entrySet())
        {
            String key = entry.getKey();
            int value = entry.getValue();

            assertEquals(expectedKey[i], key);
            assertEquals(expectedValue[i], value);
            i++;
        }
    }

    @Test
    public void concordatieTest(){
        String[] expectedKey = {"arjen", "cesar", "danny", "michiel", "nick", "rens", "rick", "sjaak" ,"thijn"};
        ArrayList<ArrayList<Integer>> expectedValue = new ArrayList<ArrayList<Integer>>();
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //a
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //c
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //d
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //m
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //n
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //re
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //ri
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2, 2) )); //s
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //t

        ArrayList<String[]> wordsPerline = new ArrayList<String[]>();
        wordsPerline.add(words);
        wordsPerline.add(words2);

        TreeMap<String, ArrayList<Integer>> output = controller.concordatieFunction(wordsPerline);

        int i = 0;
        for(Map.Entry<String, ArrayList<Integer>> entry : output.entrySet())
        {
            String key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();

            assertEquals(expectedKey[i], key);
            assertEquals(expectedValue.toArray()[i], value);
            i++;
        }
    }

    @Test
    public void concordatieTest2(){
        String[] expectedKey = {"arjen", "cesar", "danny", "michiel", "nick", "rens", "rick", "sjaak" ,"thijn"};
        ArrayList<ArrayList<Integer>> expectedValue = new ArrayList<ArrayList<Integer>>();
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //a
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //c
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //d
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //m
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //n
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //re
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1) )); //ri
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(1, 1) )); //s
        expectedValue.add(new ArrayList<Integer>( Arrays.asList(2) )); //t

        ArrayList<String[]> wordsPerline = new ArrayList<String[]>();
        wordsPerline.add(words2);
        wordsPerline.add(words);

        TreeMap<String, ArrayList<Integer>> output = controller.concordatieFunction(wordsPerline);

        int i = 0;
        for(Map.Entry<String, ArrayList<Integer>> entry : output.entrySet())
        {
            String key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();

            assertEquals(expectedKey[i], key);
            assertEquals(expectedValue.toArray()[i], value);
            i++;
        }
    }
}
