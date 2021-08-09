import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) return message + " 1";

        try {
            List<WordInfo> wordInfos = Arrays
                    .stream(message.split(BLANK_SPACE))
                    .map(word -> new WordInfo(word, 1))
                    .collect(Collectors.toList());

            wordInfos = getEachWordInfo(wordInfos);

            wordInfos.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            wordInfos.stream().map(w -> String.format("%s %d", w.getValue(), w.getWordCount())).forEach(joiner::add);

            return joiner.toString();
        } catch (Exception e) {

            return "Calculate Error";
        }
    }

    private List<WordInfo> getEachWordInfo(List<WordInfo> wordInfos) {
        List<WordInfo> wordInfo = new ArrayList<>();
        generateWordInfo(wordInfos).forEach((key, value) -> wordInfo.add(new WordInfo(key, value.size())));
        wordInfos = wordInfo;
        return wordInfos;
    }


    private Map<String, List<WordInfo>> generateWordInfo(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        wordInfoList.forEach(wordInfo -> {
            if (mapDoesNotContainWordFromWordInfo(map, wordInfo)) {
                List<WordInfo> temporaryWordInfo = new ArrayList<>();
                temporaryWordInfo.add(wordInfo);
                map.put(wordInfo.getValue(), temporaryWordInfo);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        });

        return map;
    }

    private boolean mapDoesNotContainWordFromWordInfo(Map<String, List<WordInfo>> map, WordInfo wordInfo) {
        return !map.containsKey(wordInfo.getValue());
    }


}
