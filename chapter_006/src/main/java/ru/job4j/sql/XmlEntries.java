package ru.job4j.sql;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class XmlEntries {
    @XmlRootElement
    public static class Entry {
        private List<Field> entries;
        public Entry() {
        }
        public Entry(List<Field> entries) {
            this.entries = entries;
        }
        public List<Field> getEntries() {
            return entries;
        }
        public void setEntries(List<Field> entries) {
            this.entries = entries;
        }
    }

    @XmlRootElement
    public static class Field {
        private int field;
        public Field() {
        }
        public Field(int value) {
            this.field = value;
        }
        public int getField() {
            return this.field;
        }
        public void setField(int value) {
            this.field = value;
        }
    }
}
