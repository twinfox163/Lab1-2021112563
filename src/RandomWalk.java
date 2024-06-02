import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalk {
    //IDE: 修改一些内容
    public static List<String> randomWalk (int[][] weight, List<String> word_set_list) {
        List<String> walk_result = new ArrayList<>();
        int word_set_size = word_set_list.size();
        int[][] passed_flag = new int[word_set_size][word_set_size];

        for (int i = 0; i < word_set_size; i++) {
            for (int j = 0; j < word_set_size; j++) {
                passed_flag[i][j] = 0;
            }
        }

        boolean run = true;

        Random random = new Random();

        int random_index = random.nextInt(word_set_size);
        //int random_index = 8;

        walk_result.add(word_set_list.get(random_index));

        int last_word_index = random_index;

        while (run) {
            List<Integer> next_list = new ArrayList<>();
            for (int i = 0; i < word_set_size; i++) {
                if (weight[last_word_index][i] > 0) {
                    next_list.add(i);
                }
            }

            int next_list_size = next_list.size();
            if (next_list_size == 0) {
                run = false;
            }
            else {
                random_index = random.nextInt(next_list_size);
                walk_result.add(word_set_list.get(next_list.get(random_index)));

                if (passed_flag[last_word_index][next_list.get(random_index)] == 0) {
                    passed_flag[last_word_index][next_list.get(random_index)] = 1;
                    last_word_index = next_list.get(random_index);
                }
                else {
                    run = false;
                }
            }
        }

        return walk_result;
    }

}
