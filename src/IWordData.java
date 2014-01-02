
public interface IWordData {

    /**
     * Returns the number of times the word occurred in the analyzed text
     *
     * @return
     */
    long getFrequencyCount();

    /**
     * Returns the word text
     *
     * @return
     */
    String getText();

}
