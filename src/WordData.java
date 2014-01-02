
public class WordData implements IWordData {

    private final String analyzedWord;
    private long count;

    public WordData(long count, String text) {
        this.count = count;
        this.analyzedWord = text;
    }

    //Method to update the count if the key already exists in the map
    public void updateFrequencyCount() {
        //Always increments by 1 so there is no need for a parameter to specify a number
        this.count++;
    }

    @Override
    public long getFrequencyCount() {
        //Return the frequency count specific to this object
        return count;
    }

    @Override
    public String getText() {
        //Return the word specific to this object
        return analyzedWord;
    }

}
