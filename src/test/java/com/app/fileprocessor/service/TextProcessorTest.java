package com.app.fileprocessor.service;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextProcessorTest {
    private FileProcessor textProcessor;

    public TextProcessorTest() {
        textProcessor = new TextProcessor();
    }

    @Before
    public void setup() {
        textProcessor.setInputFilePath("src/test/resources/input.txt");
        textProcessor.setOutputFilePath("src/test/resources/output.txt");
        textProcessor.setSearchString("customer");
        textProcessor.setReplaceString("client");
    }

    @Test
    public void testReplaceText() throws Exception {
        textProcessor.replaceText();

        Path path = Paths.get(textProcessor.getOutputFilePath());
        byte[] fileBytes = Files.readAllBytes(path);
        String data = new String(fileBytes);
        assertTrue(data.contains("client"));
        assertFalse(data.contains("customer"));
    }
}
