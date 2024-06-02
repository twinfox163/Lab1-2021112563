import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CreateGraph {
    //c4:修改的第二个文件
    public static List<String> getWordSet (List<String> original_word_list) {
        //b1:修改的第一个文件
        List<String> word_list = new ArrayList<>(original_word_list);
        HashSet<String> words_set = new HashSet<>(word_list);

        return new ArrayList<>(words_set);
    }

    public static int[][] getMatrix (List<String> original_word_list, List<String> word_set) {
        List<String> word_list = new ArrayList<>(original_word_list);

        int word_set_len = word_set.size();
        int word_list_len = word_list.size();

        int[][] weight = new int[word_set_len][word_set_len];
        for (int i = 0; i < word_set_len; i++) {
            for (int j = 0; j < word_set_len; j++) {
                weight[i][j] = 0;
            }
        }

        for (int i = 0; i < word_list_len - 1; i++) {
            int from_index = word_set.indexOf(word_list.get(i));
            int to_index = word_set.indexOf(word_list.get(i + 1));
            weight[from_index][to_index] += 1;
        }

        return weight;
    }

    public static void showDirectedGraph (List<String> word_set, int[][] weight) {
        int size = word_set.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("%5d. %13s  ", i + 1, word_set.get(i));
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", weight[i][j]);
            }
            System.out.print("\n");
        }
    }

}
