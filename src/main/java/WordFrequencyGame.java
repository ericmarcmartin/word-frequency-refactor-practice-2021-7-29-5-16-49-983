import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) return message + " 1";

        try {
            String[] words = message.split(BLANK_SPACE);

            List<WordInfo> wordInfos = Arrays
                    .stream(words)
                    .map(word -> new WordInfo(word, 1))
                    .collect(Collectors.toList());

            //get the map for the next step of sizing the same word
            List<WordInfo> list = new ArrayList<>();
            for (Map.Entry<String, List<WordInfo>> entry : getListMap(wordInfos).entrySet()) {
                list.add(new WordInfo(entry.getKey(), entry.getValue().size()));
            }
            wordInfos = list;

            wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfos) {
                String s = w.getValue() + " " + w.getWordCount();
                joiner.add(s);
            }

            return joiner.toString();
        } catch (Exception e) {

            return "Calculate Error";
        }
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
