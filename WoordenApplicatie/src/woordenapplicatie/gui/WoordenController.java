package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {
    
   private static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Heb je dan geen hoedje meer\n" +
                                                "Maak er één van bordpapier\n" +
                                                "Eén, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "En als het hoedje dan niet past\n" +
                                                "Zetten we 't in de glazenkas\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier";
    
    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }
    
    @FXML
    private void aantalAction(ActionEvent event) {
       String[] words = getWords();

       taOutput.setText(countsToString(countsFunction(words)));
    }

    public String countsToString(int[] counts){

        return "All words: " + counts[0] + "\nUnique words: " + counts[1];
    }

    public int[] countsFunction(String[] words){
        int[] counts = new int[2];
        counts[0] = words.length;  //O(1)

        HashSet<String> hashedWords = new HashSet();
        hashedWords.addAll(Arrays.asList(words)); //O(N)

        counts[1] = hashedWords.size();  //O(1)
        return counts;
    }


    private String[] getWords(){
        return taInput.getText().toLowerCase().replaceAll("^[,\\s]+", "").replaceAll("é", "e").split("[,\\s]+");
    }

    @FXML
    private void sorteerAction(ActionEvent event) {

        taOutput.setText(sorteerToSring(sorteerFunction(getWords())));
    }

    public TreeSet<String> sorteerFunction(String[] words){
        TreeSet<String> treeWords = new TreeSet(Comparator.reverseOrder());

        treeWords.addAll(Arrays.asList(words)); //n log (n)

        return treeWords;
    }

    public String sorteerToSring(TreeSet<String> treeWords){
        String output = "The words are: \n";

        Iterator<String> iterator = treeWords.iterator();

        while (iterator.hasNext()) {
            output += iterator.next() + "\n";
        }
        return output;
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        taOutput.setText(frequentieToString(frequantieFunction(getWords())));
    }

    public TreeMap<String, Integer> frequantieFunction(String[] words){
        TreeMap<String, Integer> wordsTreeMapped = new TreeMap<String, Integer>();

        for (String word: words) {
            wordsTreeMapped.merge(word, 1, Integer :: sum); // n log(n)
        }

        return wordsTreeMapped;
    }

    public String frequentieToString(TreeMap<String, Integer> wordsTreeMapped){
        String output = "";

        for (String word : wordsTreeMapped.keySet()) {
            output += word + "  " + wordsTreeMapped.get(word) + "\n";
        }
        return output;
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(Arrays.asList(taInput.getText().toLowerCase().replaceAll("é", "e").split("\\n")));
        lines.removeAll(Arrays.asList("", null));

        ArrayList<String[]> wordsPerLine = new ArrayList<String[]>();
        for (String line: lines) {
            wordsPerLine.add(line.replaceAll("^[,\\s]+", "").split("[,\\s]+"));
        }

        taOutput.setText(ConcordatieToString(concordatieFunction(wordsPerLine)));
    }

    public TreeMap<String, ArrayList<Integer>> concordatieFunction(ArrayList<String[]> wordsPerLine){
        TreeMap<String, ArrayList<Integer>> mappedTree = new TreeMap<String, ArrayList<Integer>>();

        for (int i = 0; i < wordsPerLine.size(); i++){
            for (String word : wordsPerLine.get(i)){
                final Integer line = i;
                mappedTree.merge(word, new ArrayList<Integer>(Arrays.asList(i + 1)), (oldValue, newValue) -> {
                    oldValue.add(line + 1);
                    return oldValue;
                } ); // O (n log (n))
            }
        }
        return mappedTree;
    }

    public String ConcordatieToString(TreeMap<String, ArrayList<Integer>> mappedTree){
        String output = "";

        for (String word : mappedTree.keySet()) {
            output += word + "   " + mappedTree.get(word) + "\n"; //n log(n)
        }

        return output;
    }

}
