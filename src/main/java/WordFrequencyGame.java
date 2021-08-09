import java.util.*;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) return message + " 1";

        try {
            String[] words = message.split(BLANK_SPACE);

            List<WordInfo> wordInfoList = new ArrayList<>();
            for (String s : words) {
                WordInfo wordInfo = new WordInfo(s, 1);
                wordInfoList.add(wordInfo);
            }

            //get the map for the next step of sizing the same word
            List<WordInfo> list = new ArrayList<>();
            for (Map.Entry<String, List<WordInfo>> entry : getListMap(wordInfoList).entrySet()) {
                list.add(new WordInfo(entry.getKey(), entry.getValue().size()));
            }
            wordInfoList = list;

            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfoList) {
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
