import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //R3:修改一些内容
        String filepath = "./data/test1.txt";
        String sentence_path = "./data/sentence.txt";

        List<String> word_list = HandleString.getStringList(filepath);
        List<String> word_set_list = CreateGraph.getWordSet(word_list);

        int word_set_size = word_set_list.size();
        int[][] weight = CreateGraph.getMatrix(word_list, word_set_list);


        // 功能2
        System.out.print("1. 展示邻接矩阵：\n");
        CreateGraph.showDirectedGraph(word_set_list, weight);
        System.out.print("\n");


        // 功能3
        System.out.print("2. 输入起始点 --> ");
        Scanner from_scan = new Scanner(System.in);
        String from_word = from_scan.next();

        System.out.print("   输入终结点 --> ");
        Scanner to_scan = new Scanner(System.in);
        String to_word = from_scan.next();

        from_scan.close();
        to_scan.close();

        System.out.print("   输出结果： ");
        List<String> bridge_words = BridgeWord.queryBridgeWords(weight, word_set_list, from_word, to_word);
        System.out.print("\n\n");


        // 功能4
        System.out.print("3. 输出新文本 --> ");
        System.out.print(BridgeWord.generateNewText(sentence_path, weight, word_set_list) + "\n");
        System.out.print("\n");


        // 功能5
        System.out.print("4. 随机游走  --> ");
        List<String> res = RandomWalk.randomWalk(weight, word_set_list);

        for (String str: res) {
            System.out.print(str + " ");
        }


        // 功能2
        String from_word_ = "to";
        String to_word_   = "and";

        int from_index = word_set_list.indexOf(from_word_);
        int to_index   = word_set_list.indexOf(to_word_);

        System.out.print("\n\n");
        System.out.printf("5. 从\"%s\"到\"%s\"最短路径: \n", word_set_list.get(from_index), word_set_list.get(to_index));
        List<String> path = DijkstraMethod.calcShortestPath(word_set_list, weight, word_set_size, from_index, to_index);
        if (path == null) {
            System.out.print("不可达");
        }
        else {
            System.out.printf("   %s", word_set_list.get(from_index));
            for (String word: path) {
                System.out.printf(" --> %s", word);
            }
        }


        String single_word = "to";
        int single_index = word_set_list.indexOf(single_word);

        System.out.print("\n\n");
        System.out.printf("6. 从\"%s\"到各点的最短路径: \n", word_set_list.get(single_index));
        for (int i = 0; i < word_set_size && i != single_index; i++) {
            System.out.printf("   到点\"%s\": ", word_set_list.get(i));

            path = DijkstraMethod.calcShortestPath(word_set_list, weight, word_set_size, single_index, i);
            if (path == null) {
                System.out.print("不可达\n");
            }
            else {
                System.out.printf("%s", word_set_list.get(single_index));
                for (String word: path) {
                    System.out.printf(" --> %s", word);
                }
            }
            System.out.print("\n");
        }
    }
}