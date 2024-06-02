import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class HandleString {
    public static List<String> readString (String filepath) {
        File file = new File(filepath);
        List<String> strings = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }

    public static List<String> handleString (List<String> target_string) {
        List<String> original_words = new ArrayList<>();
        List<String> words = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (String sentence: target_string) {
            String[] handled_sentence = sentence.split(" ");
            original_words.addAll(Arrays.asList(handled_sentence));
        }

        for (String word: original_words) {
            String new_word = word.replaceAll("[^A-Za-z]", "");
            new_word = new_word.toLowerCase();
            words.add(new_word);
        }

        for (String word: words) {
            if (!Objects.equals(word, "")) {
                result.add(word);
            }
        }
        return result;
    }

    public static List<String> getStringList (String filepath) {
        List<String> string = readString(filepath);
        return handleString(string);
    }

}
