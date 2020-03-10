import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 赵洪苛
 * @date 2020/3/10 17:02
 * @description 词袋分析
 */
public class Analyzer {

    private String document;
    private List<String> sentences;
    private List<Sentence> sentenceList = new ArrayList<>();
    private WordBag wordBag = new WordBag();

    public void analyze() {
        splitIntoSentence();
        splitIntoWord();
    }

    public void calculateTF_IDF() {
        for (Sentence temp : sentenceList) {
            for (Word word : temp.getWordBag().getWords()) {
                if (word.getWeight() == 0) {
                    continue;
                }
                float TF = wordBag.findWordByName(word.getWord()).getWeight() / wordBag.getTotalWords();
                int i = 0;
                for (String s : sentences) {
                    if (s.contains(word.getWord())) {
                        i++;
                    }
                }
                float IDF = (float) Math.log(sentenceList.size() / (double) (i + 1));
                word.setWeight(TF * IDF);
            }
        }
    }

    private void splitIntoSentence() {
        // 把一个文档分成多句话
        sentences = new ArrayList<>(Arrays.asList(document.split("\\.")));
    }

    private void splitIntoWord() {
        List<String> list = new ArrayList<>();
        // 把每句话分成单词，合并
        sentences.forEach(temp -> list.addAll(new ArrayList<>(Arrays.asList(temp.split(" ")))));
        AtomicInteger i = new AtomicInteger();
        // 建立总词袋
        list.stream().distinct()
                .forEach(temp -> {
                    Word word = new Word();
                    word.setWord(temp);
                    word.setNum(i.getAndIncrement());
                    wordBag.insertWord(word);
                });
        // 对每句话建立对象和对应的词袋
        sentences.forEach(temp -> {
            Sentence sentence = new Sentence();
            sentence.setSentence(temp);
            sentence.setWordBag((WordBag) wordBag.clone());
            sentence.analyze();
            sentenceList.add(sentence);
        });
        Map<String, Integer> map = new HashMap<>();
        // 计算文档中该单词出现的次数
        list.forEach(temp -> {
            int n = 1;
            if (map.get(temp) != null) {
                n = map.get(temp) + 1;
            }
            map.put(temp, n);
        });
        // 填入单词的权重信息
        list.stream().distinct().forEach(temp -> wordBag.findWordByName(temp).setWeight(map.get(temp)));
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public WordBag getWordBag() {
        return wordBag;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
