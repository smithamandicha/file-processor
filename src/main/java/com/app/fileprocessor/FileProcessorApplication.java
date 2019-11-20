package com.app.fileprocessor;

import com.app.fileprocessor.service.FileProcessor;

import java.util.Scanner;

public class FileProcessorApplication {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Please enter all the mandatory parameters: File Type, Search String, Replace String");
            return;
        }

        String fileType = args[0];
        String searchString = args[1];
        String replaceString = args[2];

        FileType type = FileType.valueOfLabel(fileType);
        if (type == null) {
            System.out.println("Unsupported File Type");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Input File Path:");
        String inputFilePath = scanner.next();
        System.out.println("Enter Output File Path:");
        String outputFilePath = scanner.next();

        ProcessorFactory factory = new ProcessorFactory();
        FileProcessor processor = factory.createProcessor(type);

        processor.setSearchString(searchString);
        processor.setReplaceString(replaceString);
        processor.setInputFilePath(inputFilePath);
        processor.setOutputFilePath(outputFilePath);

        processor.replaceText();

        System.out.println("Processed Successfully. Output file :" + outputFilePath);
    }
}
