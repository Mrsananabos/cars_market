package ru.job4j.converterXML;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.*;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class StoreXML {

    public void convert(List<XmlEntries.Field> entries, String directory) throws Exception {
        File file = new File(directory);
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlEntries.Entry.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new XmlEntries.Entry(entries), file);
    }

    public long sumField(String pathName) {
       SAXParserFactory parser = SAXParserFactory.newInstance();
       Handler handler = new Handler();
       try {
           SAXParser saxParser = parser.newSAXParser();
           saxParser.parse(new File(pathName), handler);
       } catch (ParserConfigurationException | SAXException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return handler.getSum();
    }
}
