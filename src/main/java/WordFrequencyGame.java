import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) return message + " 1";

        try {
            List<WordInfo> wordInfos = retrieveEachWordInfo(message);

            return generateWordFrequency(wordInfos);
        } catch (Exception e) {

            return "Calculate Error";
        }
    }

    private List<WordInfo> retrieveEachWordInfo(String message) {
        List<WordInfo> wordInfos = Arrays
                .stream(message.split(BLANK_SPACE))
                .map(word -> new WordInfo(word, 1))
                .collect(Collectors.toList());
        return getEachWordInfo(wordInfos);
    }

    private String generateWordFrequency(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfos.stream().map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .forEachOrdered(joiner::add);
        return joiner.toString();
    }

    private List<WordInfo> getEachWordInfo(List<WordInfo> wordInfos) {
        List<WordInfo> wordInfo = new ArrayList<>();
        generateWordInfo(wordInfos).forEach((key, value) -> wordInfo.add(new WordInfo(key, value.size())));
        wordInfos = wordInfo;
        wordInfos.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

        return wordInfos;
    }

    private Map<String, List<WordInfo>> generateWordInfo(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        wordInfoList.forEach(wordInfo -> {
            if (mapDoesNotContainWordFromWordInfo(map, wordInfo)) {
                List<WordInfo> temporaryWordInfo = new ArrayList<>();
                temporaryWordInfo.add(wordInfo);
                map.put(wordInfo.getWord(), temporaryWordInfo);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        });

        return map;
    }

    private boolean mapDoesNotContainWordFromWordInfo(Map<String, List<WordInfo>> map, WordInfo wordInfo) {
        return !map.containsKey(wordInfo.getWord());
    }

}
