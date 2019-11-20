package com.app.fileprocessor;

import com.app.fileprocessor.service.FileProcessor;
import com.app.fileprocessor.service.TextProcessor;
import com.app.fileprocessor.service.XMLProcessor;

/**
 * Factory to create processors.
 */
public class ProcessorFactory {
    public FileProcessor createProcessor(FileType fileType) {
        switch (fileType) {
            case XML:
                return new XMLProcessor();
            case TEXT:
                return new TextProcessor();
            default:
                throw new IllegalArgumentException("Unsupported File Type");
        }
    }
}
