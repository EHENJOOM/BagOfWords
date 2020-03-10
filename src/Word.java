/**
 * @author 赵洪苛
 * @date 2020/3/10 16:53
 * @description 词袋里的词
 */
public class Word implements Cloneable {

    private String word;
    private int num;
    private float weight;

    @Override
    public Object clone() {
        Word temp = new Word();
        temp.word = this.word;
        temp.num = this.num;
        temp.weight = this.weight;
        return temp;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "\"" + word + "\":" + num + ",";
    }

    public String getWordAndWeight() {
        return word + " " + weight + " ,";
    }
}
