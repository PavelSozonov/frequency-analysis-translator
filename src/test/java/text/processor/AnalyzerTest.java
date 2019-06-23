package text.processor;

import org.junit.Test;

import java.io.IOException;

public class AnalyzerTest {

    @Test
    public void marchenTest() throws IOException {
        new Analyzer("marchen.txt", "de:en").analyze().printAsCsv();
    }

    @Test
    public void ohneDichTest() throws IOException {
        new Analyzer("ohnedich.txt", "de:en").analyze().printAsCsv();
    }
}