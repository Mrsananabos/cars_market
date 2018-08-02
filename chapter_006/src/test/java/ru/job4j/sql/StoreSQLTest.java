package ru.job4j.sql;

import org.junit.Test;

import javax.xml.transform.TransformerException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void show() {
        Config config = new Config("jdbc:sqlite:test1.db");
        StoreSQL s = new StoreSQL(config);
        s.generate(1000);
        StoreXML sx = new StoreXML();
        ConvertXSQT convertXSQT = new ConvertXSQT();
        String root = "C:\\projects\\ashveytser\\chapter_006\\src\\main\\java\\ru\\job4j\\sql\\firstXML.xml";
        String rootXSL = "C:\\projects\\ashveytser\\chapter_006\\src\\main\\java\\ru\\job4j\\sql\\xslt.xsl";
        String newXml = "C:\\projects\\ashveytser\\chapter_006\\src\\main\\java\\ru\\job4j\\sql\\secondXML.xml";
        int result = 0;
        try {
            sx.convert(s.readDate(), root);
            convertXSQT.convert(root, rootXSL, newXml);
            result = sx.sumField(newXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(28));
        config.closeConnection();
    }

}