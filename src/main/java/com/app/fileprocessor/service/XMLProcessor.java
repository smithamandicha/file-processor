package com.app.fileprocessor.service;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * XML file processor.
 */
public class XMLProcessor extends FileProcessor {

    /**
     * Used to search and replace text phrases. Uses Streaming API for XML (StAX) to process large files in a memory efficient way.
     *
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    @Override
    public void replaceText() throws FileNotFoundException, XMLStreamException {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        XMLInputFactory inFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = inFactory.createXMLEventReader(new FileInputStream(inputFile));
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        boolean isChanged;

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            isChanged = false;

            if (event.getEventType() == XMLEvent.START_ELEMENT) {
                StartElement elemEvent = event.asStartElement();

                List<Attribute> newAttrs = new ArrayList<>();
                Iterator<Attribute> existingAttrs = elemEvent.getAttributes();
                while (existingAttrs.hasNext()) {
                    Attribute existing = existingAttrs.next();
                    if (existing.getValue().contains("trace")) {
                        newAttrs.add(eventFactory.createAttribute(existing.getName().getLocalPart(), replaceAll(existing.getValue())));
                    } else {
                        newAttrs.add(existing);
                    }
                }
                isChanged = true;
                writer.add(eventFactory.createStartElement(elemEvent.getName(), newAttrs.iterator(), elemEvent.getNamespaces()));

            }
            if (!isChanged) {
                writer.add(event);
            }
        }
        writer.close();
    }
}
