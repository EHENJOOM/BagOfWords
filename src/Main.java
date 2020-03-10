/**
 * @author 赵洪苛
 * @date 2020/3/10 17:17
 * @description 主类
 */
public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        analyzer.setDocument("I come to China to study.This is a famous building in China.I love tea and apple.I study management in China.");
        analyzer.analyze();
        System.out.println("{" + analyzer.getWordBag().getWordBagString() + "}\n");
        analyzer.getSentenceList().forEach(temp -> System.out.println(temp.getWordBag().getWordAndWeight()));
        analyzer.calculateTF_IDF();
        analyzer.getSentenceList().forEach(temp -> System.out.println(temp.getWordBag().getWordAndWeight()));
    }

}
