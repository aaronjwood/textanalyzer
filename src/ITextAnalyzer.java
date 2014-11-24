import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface ITextAnalyzer {

    /**
     * Reads the words from the text file and builds the word count data.
     *
     * @param filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    void analyzeText(String filename) throws FileNotFoundException, IOException;

    /**
     * Returns the total number of words in the analyzed text file
     *
     * @return
     */
    long getWordCount();

    /**
     * Returns the total number of unique words in the analyzed text file
     *
     * @return
     */
    long getUniqueWordCount();

    /**
     * Returns the word data associated with the word. Returns null if the word
     * does nor exist in the analyzed text.
     *
     * @param word
     * @return
     */
    IWordData findWord(String word);

    /**
     * Returns all the unique words in the analyzed text sorted by frequency
     * count in descending order.
     *
     * @return
     */
    Collection<IWordData> allWordsOrdedByFrequencyCount();

    /**
     * Returns all the unique words in the analyzed text sorted alphabetically
     * in ascending order.
     *
     * @return
     */
    Collection<IWordData> allWordsOrderByText();

}
