package ru.job4j.sql;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ConvertXSQT {
    public void convert(String sourceXML, String pathScheme, String pathNewXML) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(pathScheme));
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(new File(sourceXML));
        transformer.transform(text, new StreamResult(new File(pathNewXML)));
    }
}
