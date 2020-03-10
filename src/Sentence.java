import java.util.*;

/**
 * @author 赵洪苛
 * @date 2020/3/10 17:36
 * @description 句子
 */
public class Sentence {

    // 句子原句
    private String sentence;
    // 针对该句的总体词袋
    private WordBag wordBag = new WordBag();
    // 该句分解后的词
    private List<String> allWords = new ArrayList<>();

    public void analyze() {
        // 把一个句子分解成单词
        allWords.addAll(new ArrayList<>(Arrays.asList(sentence.split(" "))));
        Map<String, Integer> map = new HashMap<>();
        // 计算单词出现的次数
        allWords.forEach(temp -> {
            int n = 1;
            if (map.get(temp) != null) {
                n = map.get(temp) + 1;
            }
            map.put(temp, n);
        });
        // 填入单词的权重信息
        allWords.stream()
                .distinct()
                .forEach(temp -> wordBag.findWordByName(temp).setWeight(map.get(temp)));
    }

    public void setWordBag(WordBag wordBag) {
        this.wordBag = wordBag;
    }

    public WordBag getWordBag() {
        return wordBag;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
