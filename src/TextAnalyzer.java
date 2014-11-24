import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer implements ITextAnalyzer {

    private Map<String, IWordData> words;

    //Specify what kind of map you want to use in the constructor
    public TextAnalyzer(String mapType) {
        if (mapType.equalsIgnoreCase("hashmap")) {
            words = new HashMap();
        } else if (mapType.equalsIgnoreCase("treemap")) {
            words = new TreeMap();
        } else {
            throw new IllegalArgumentException("Incorrect parameter for the constructor. Expecting \"hash\" or \"tree\"");
        }
    }

    @Override
    public Collection<IWordData> allWordsOrdedByFrequencyCount() {
        //Put all of the values in the map (which are IWordData objects) into a list
        List<IWordData> sortedWords = new LinkedList<>(words.values());
        //Sort the list based on the comparator below. This will sort them in descending order
        Collections.sort(sortedWords, new Comparator<IWordData>() {

            @Override
            public int compare(IWordData o1, IWordData o2) {
                long frequency1 = o1.getFrequencyCount();
                long frequency2 = o2.getFrequencyCount();
                //Descending order
                return (int) (frequency2 - frequency1);
            }
        });
        //Return the sorted list
        return sortedWords;
    }

    @Override
    public Collection<IWordData> allWordsOrderByText() {
        //Put all of the keys (words) in the map into a list
        List<IWordData> sortedText = new LinkedList<>(words.values());

        Collections.sort(sortedText, new Comparator<IWordData>() {
            public int compare(IWordData o1, IWordData o2) {
                return o1.getText().compareTo(o2.getText());
            }
        });
        //Return the sorted list
        return sortedText;
    }

    //Method to remove any special characters that don't form a word from a string
    private String removeSpecialCharacters(String s) {
        return s.replaceAll("[\\!\\@\\[\\]\\.\\,\\:\\;\"\'\\-\\?\\#\\$\\(\\)\\*]", "");
    }

    @Override
    public void analyzeText(String filename) throws FileNotFoundException, IOException {
        //Read the file specified into the scanner
        Scanner inp = new Scanner(new FileReader(filename));
        while (inp.hasNext()) {
            //Store each word in the chunk variable and make sure to remove any special characters
            String chunk = removeSpecialCharacters(inp.next());
            //Do this condition first since we'll be putting more words into the map than updating the frequency count
            if (!words.containsKey(chunk)) {
                //Insert each chunk into the map as the key. The value is the freqency (1 by default) and the text itself
                words.put(chunk, new WordData(1, chunk));
            } else {
                //If the map already contains the key, index into it and increment the count value
                WordData key = (WordData) words.get(chunk);
                key.updateFrequencyCount();
            }
        }
    }

    @Override
    public IWordData findWord(String word) {
        //Check if the map contains the word (which is the key)
        if (words.containsKey(word)) {
            //If it does, then return the IWordData object associated with it
            return words.get(word);
        } else {
            //If the key doesn't exist, return null
            return null;
        }
    }

    @Override
    public long getUniqueWordCount() {
        //Get the number of keys in the map. This is the unique word count
        return words.keySet().size();
    }

    @Override
    public long getWordCount() {
        //Iterate over all the IWordData objects in the map and add up the frequency count to get the total word count
        long totalWordCount = 0;
        for (Map.Entry<String, IWordData> entry : words.entrySet()) {
            //For each IWordData in the map, call it's getFrequencyCount() method and add it back into totalWordCount
            totalWordCount += entry.getValue().getFrequencyCount();
        }
        return totalWordCount;
    }
}
