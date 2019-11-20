package com.app.fileprocessor.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Text file processor
 */
public class TextProcessor extends FileProcessor {

    /**
     * Used to search and replace text phrases. Uses Streams to process large files in a memory efficient way.
     *
     * @throws IOException
     */
    @Override
    public void replaceText() throws IOException {
        Stream<String> inputStream = Files.lines(Paths.get(inputFilePath));
        List<String> output = inputStream.map(this::replaceAll).collect(Collectors.toList());
        Files.write(Paths.get(outputFilePath), output);
    }
}
