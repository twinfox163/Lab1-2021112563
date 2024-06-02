import java.util.ArrayList;
import java.util.List;

public class DijkstraMethod {
    //b2:修改的第一个文件
    public static List<String> calcShortestPath (List<String> word_set_list, int[][] weight, int size, int from_index, int to_index) {
        int[] path = new int[size];
        int[] path_index = new int[size];
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (weight[from_index][i] == 0) {
                path[i] = -1;
            }
            else {
                path[i] = weight[from_index][i];
                path_index[i] = from_index;
            }

            visited[i] = false;
        }

        path[from_index] = 0;
        visited[from_index] = true;

        List<String> path_word = new ArrayList<>();

        for (int i = 0; i < size - 1; i++) {
            int min = 100, min_index = -1;
            for (int j = 0; j < size; j++) {
                if (path[j] < min && path[j] > 0 && !visited[j]) {
                    min = path[j];
                    min_index = j;
                }
            }
            if (min_index == -1) {
                break;
            }
            visited[min_index] = true;

            for (int j = 0; j < size; j++) {
                if (!visited[j]) {
                    if (path[j] == -1 && weight[min_index][j] > 0) {
                        path[j] = weight[min_index][j] + path[min_index];
                        path_index[j] = min_index;
                    }
                    else if (path[j] > 0 && weight[min_index][j] > 0
                            && path[j] > path[min_index] + weight[min_index][j]) {
                        path[j] = path[min_index] + weight[min_index][j];
                        path_index[j] = min_index;
                    }
                }
            }
        }

        if (from_index == to_index) {
            path_word.add(word_set_list.get(from_index));
        }
        else if (path[to_index] == -1) {
            path_word = null;
        }
        else {
            int to = to_index;
            while (from_index != to) {
                path_word.add(word_set_list.get(to));
                to = path_index[to];
            }
        }

        List<String> res = new ArrayList<>();
        if (path_word != null) {
            int path_word_size = path_word.size();
            for (int i = path_word_size - 1; i >= 0; i--) {
                res.add(path_word.get(i));
            }
            return res;
        }
        else {
            return null;
        }

    }

}
