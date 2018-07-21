package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;

import java.sql.Connection;
import java.util.Random;

public interface AutoCloseable {
    void makeConnectionSQL();
    void closeConnectionSQL();
}
