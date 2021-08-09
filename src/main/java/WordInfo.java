public class WordInfo {
    private final String word;
    private final int count;

    public WordInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

}
