package ru.job4j.sql.converterXML;

import org.junit.Test;
import ru.job4j.converterXML.Config;
import ru.job4j.converterXML.ConvertXSQT;
import ru.job4j.converterXML.StoreSQL;
import ru.job4j.converterXML.StoreXML;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreSQLTest {

    @Test
    public void show() {
        Config config = new Config("jdbc:sqlite:test1.db");
        StoreSQL s = new StoreSQL(config);
        s.generate(1000000);
        StoreXML sx = new StoreXML();
        ConvertXSQT convertXSQT = new ConvertXSQT();
        String root = this.getClass().getClassLoader().getResource("firstXML.xml").getFile();
        String rootXSL = this.getClass().getClassLoader().getResource("xslt.xsl").getFile();
        String newXml = this.getClass().getClassLoader().getResource("secondXML.xml").getFile();
        long result = 0;
        try {
            sx.convert(s.readDate(), root);
            convertXSQT.convert(root, rootXSL, newXml);
            result = sx.sumField(newXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(500000500000L));
        config.closeConnection();
    }

}