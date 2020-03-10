import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵洪苛
 * @date 2020/3/10 16:53
 * @description 词袋
 */
public class WordBag implements Cloneable {

    private List<Word> words = new ArrayList<>();

    private int totalWords;

    public int getTotalWords() {
        words.forEach(temp -> totalWords += temp.getWeight());
        return totalWords;
    }

    public List<Word> getWords() {
        return words;
    }

    public Word findWordByName(String name) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().equals(name)) {
                return words.get(i);
            }
        }
        return null;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void insertWord(Word word) {
        words.add(word);
    }

    public String getWordBagString() {
        StringBuffer out = new StringBuffer();
        words.forEach(word -> out.append(word.toString()));
        return out.toString();
    }

    public String getWordAndWeight() {
        StringBuffer out = new StringBuffer();
        words.forEach(word -> out.append(word.getWordAndWeight()));
        return out.toString();
    }

    @Override
    public Object clone() {
        WordBag tempWordBag = new WordBag();
        List<Word> tempWords = new ArrayList<>();
        words.forEach(temp -> {
            Word newWord = (Word) temp.clone();
            tempWords.add(newWord);
        });
        tempWordBag.words = tempWords;
        return tempWordBag;
    }
}
