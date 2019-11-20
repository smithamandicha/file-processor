package com.app.fileprocessor.service;

import org.junit.Before;
import org.junit.Test;
import org.xmlunit.builder.Input;

import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;

public class XMLProcessorTest {

    private FileProcessor xmlProcessor;

    public XMLProcessorTest() {
        xmlProcessor = new XMLProcessor();
    }

    @Before
    public void setup() {
        xmlProcessor.setInputFilePath("src/test/resources/input.xml");
        xmlProcessor.setOutputFilePath("src/test/resources/output.xml");
        xmlProcessor.setSearchString("trace");
        xmlProcessor.setReplaceString("error");
    }

    @Test
    public void testReplaceText() throws Exception {
        String expectedOutputXml = "<configuration>\n" +
                "<properties>\n" +
                "<log level=\"error\"><file name=\"error-20180101.log\"></file></log>\n" +
                "<comment>Level can be either \"trace\", \"info\" or \"error\".</comment>\n" +
                "</properties>\n" +
                "</configuration>";
        xmlProcessor.replaceText();
        assertThat(Input.fromString(expectedOutputXml), isSimilarTo(Input.fromFile("src/test/resources/output.xml")));
    }
}
