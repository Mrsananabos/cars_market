package ru.job4j.converterXML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {
    private long sum;
    private String element;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.element = qName;
        if (this.element.equals("entry")) {
            String field = attributes.getValue(0);
            sum += Integer.parseInt(field);
        }
    }

    public long getSum() {
        return sum;
    }


}
