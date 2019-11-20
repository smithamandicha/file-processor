package com.app.fileprocessor.service;

public abstract class FileProcessor {

    String inputFilePath;
    String outputFilePath;
    String searchString;
    String replaceString;

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getReplaceString() {
        return replaceString;
    }

    public void setReplaceString(String replaceString) {
        this.replaceString = replaceString;
    }

    public abstract void replaceText() throws Exception;

    String replaceAll(String input) {
        return input != null ? input.replaceAll(searchString, replaceString) : null;

    }
}
