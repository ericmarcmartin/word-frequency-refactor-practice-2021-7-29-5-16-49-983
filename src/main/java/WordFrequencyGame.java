import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String message) {
        List<WordInfo> wordInfos = retrieveEachWordInfo(message);

        return generateWordFrequency(wordInfos);
    }

    private List<WordInfo> retrieveEachWordInfo(String message) {
        List<WordInfo> wordInfo = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : generateEachWord(message)) {
            wordInfo.add(new WordInfo(entry.getKey(), entry.getValue().size()));
        }
        wordInfo.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordInfo;
    }

    private String generateWordFrequency(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfos.stream().map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .forEachOrdered(joiner::add);
        return joiner.toString();
    }

    private Set<Map.Entry<String, List<WordInfo>>> generateEachWord(String message) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : Arrays
                .stream(message.split(BLANK_SPACE))
                .map(word -> new WordInfo(word, 1))
                .collect(Collectors.toList())) {
            if (mapDoesNotContainWordFromWordInfo(map, wordInfo)) {
                List<WordInfo> temporaryWordInfo = new ArrayList<>();
                temporaryWordInfo.add(wordInfo);
                map.put(wordInfo.getWord(), temporaryWordInfo);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }

        return map.entrySet();
    }

    private boolean mapDoesNotContainWordFromWordInfo(Map<String, List<WordInfo>> map, WordInfo wordInfo) {
        return !map.containsKey(wordInfo.getWord());
    }
}
