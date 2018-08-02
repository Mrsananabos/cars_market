package ru.job4j.sql;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
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

    public int sumField(String pathName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(pathName);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        NodeList list = doc.getElementsByTagName("entries");
        NodeList childNodes = list.item(0).getChildNodes();
        int sum = 0;
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getAttributes() != null) {
                sum = sum + Integer.parseInt(childNodes.item(i).getAttributes().getNamedItem("field").getNodeValue());
            }
        }
        System.out.println(sum);
        return sum;
    }
}
