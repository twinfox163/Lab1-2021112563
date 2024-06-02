import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BridgeWord {
    //c4:修改的第一个文件
    public static List<String> queryBridgeWords (int[][] weight, List<String> word_set, String from_word, String to_word) {
        int from_index = word_set.indexOf(from_word);
        int to_index = word_set.indexOf(to_word);

        if (from_index == -1 && to_index == -1) {
            System.out.printf("No \"%s\" and \"%s\" in the graph", from_word, to_word);
            return null;
        }
        else if (from_index == -1) {
            System.out.printf("No \"%s\" in the graph", from_word);
            return null;
        }
        else if (to_index == -1) {
            System.out.printf("No \"%s\" in the graph", to_word);
            return null;
        }
        else {
            int word_set_size = word_set.size();
            List<String> from_after_words = new ArrayList<>();
            List<String> to_before_words = new ArrayList<>();
            List<String> bridge_words = new ArrayList<>();

            for (int i = 0; i < word_set_size; i++) {
                if (weight[from_index][i] > 0) {
                    from_after_words.add(word_set.get(i));
                }
                if (weight[i][to_index] > 0) {
                    to_before_words.add(word_set.get(i));
                }
            }

            for (String bridge_word: from_after_words) {
                if (to_before_words.contains(bridge_word)) {
                    bridge_words.add(bridge_word);
                }
            }

            int bridge_words_size = bridge_words.size();
            if (bridge_words_size == 0) {
                System.out.printf("No bridge words from \"%s\" to \"%s\"", from_word, to_word);
                return null;
            }
            else {
                System.out.printf("Bridge words from \"%s\" to \"%s\": ", from_word, to_word);
                for (String word: bridge_words) {
                    System.out.printf("%s  ", word);
                }
                return bridge_words;
            }
        }
    }

    public static List<String> getBridgeWord_no_print (int[][] weight, List<String> word_set, String from_word, String to_word) {
        int from_index = word_set.indexOf(from_word);
        int to_index = word_set.indexOf(to_word);

        if (from_index == -1 && to_index == -1) {
            return null;
        }
        else if (from_index == -1) {
            return null;
        }
        else if (to_index == -1) {
            return null;
        }
        else {
            int word_set_size = word_set.size();
            List<String> from_after_words = new ArrayList<>();
            List<String> to_before_words = new ArrayList<>();
            List<String> bridge_words = new ArrayList<>();

            for (int i = 0; i < word_set_size; i++) {
                if (weight[from_index][i] > 0) {
                    from_after_words.add(word_set.get(i));
                }
                if (weight[i][to_index] > 0) {
                    to_before_words.add(word_set.get(i));
                }
            }

            for (String bridge_word: from_after_words) {
                if (to_before_words.contains(bridge_word)) {
                    bridge_words.add(bridge_word);
                }
            }

            int bridge_words_size = bridge_words.size();
            if (bridge_words_size == 0) {
                return null;
            }
            else {
                return bridge_words;
            }
        }
    }

    public static String generateNewText (String filepath, int[][] weight, List<String> word_set) {
        List<String> word_list = HandleString.getStringList(filepath);
        int word_list_size = word_list.size();

        List<String> result = new ArrayList<>();
        result.add(word_list.get(0));

        for (int i = 0; i < word_list_size - 1; i++) {
            List<String> bridge_words = getBridgeWord_no_print(weight, word_set, word_list.get(i), word_list.get(i + 1));
            if (bridge_words == null) {
                result.add(word_list.get(i + 1));
            }
            else {
                Random random = new Random();
                int random_index = random.nextInt(bridge_words.size());
                result.add(bridge_words.get(random_index));
                result.add(word_list.get(i + 1));
            }
        }

        String res = "";
        for (String str: result) {
            res = res.concat(str + " ");
        }

        return res;
    }

}
