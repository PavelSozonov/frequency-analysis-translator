package text.processor;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Analyzer {

    private final String song;
    private final String langs;
    private final WordsCounterConsumer wordsCounterConsumer = new WordsCounterConsumer();

    Analyzer(String resource, String langs) throws IOException {
        URL url = Resources.getResource(resource);
        song = Resources.toString(url, Charsets.UTF_8);
        this.langs = langs;
    }

    Analyzer analyze() {
        String[] words = song
                .replaceAll("\\p{Punct}", "")
                .replace("\n", " ")
                .toLowerCase().split(" ");
        Arrays.stream(words).filter(x -> !x.equals("")).forEach(wordsCounterConsumer);
        return this;
    }

    void printAsCsv() {
        wordsCounterConsumer.getCounter().entrySet().stream().sorted(new FrequencyAlphabeticalComparator())
                .forEach(x -> System.out.println(String.format("%s; %s; %s",
                        x.getKey(), x.getValue(), translate(x.getKey()))));
    }

    private String translate(String word) {
        return executeShellCommand(String.format("./trans.sh %s %s | tail -1 | awk '{$1=$1};1'", langs, word))
                .replace("\n", "");
    }

    private String executeShellCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command);
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitVal = getExitVal(process);
            if (exitVal == 0) {
                return output.toString();
            }
            throw new TranslationException(String.format("Exit code: %s, Command: %s", exitVal, command));
        } catch (IOException e) {
            throw new TranslationException(e);
        }
    }

    private int getExitVal(Process process) {
        int exitVal = 0;
        try {
            exitVal = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return exitVal;
    }

    /*
     * Sort by frequency
     * If frequencies are equal sort by alphabet
     */
    private class FrequencyAlphabeticalComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> x, Map.Entry<String, Integer> y) {
            int compareValues = y.getValue().compareTo(x.getValue());
            return compareValues == 0 ? x.getKey().compareTo(y.getKey()) : compareValues;
        }
    }

    @Getter
    private class WordsCounterConsumer implements Consumer<String> {

        Map<String, Integer> counter = new HashMap<>();

        @Override
        public void accept(String word) {
            Integer value = counter.get(word);
            if (value == null) {
                counter.put(word, 1);
            } else {
                counter.put(word, ++value);
            }
        }
    }
}
