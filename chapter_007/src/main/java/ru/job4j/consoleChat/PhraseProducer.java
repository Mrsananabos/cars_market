package ru.job4j.consoleChat;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhraseProducer {
    private static final Logger LOGGER = Logger.getLogger(PhraseProducer.class);
    private final String path;
    private final List<Integer> abusesSymbols = new ArrayList<>(Arrays.asList(10, 13, 32));

    public PhraseProducer(String path) {
        this.path = path;
    }

    String getPhrase() {
        String result = "";
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            long randomFilePointer = 1 + (int) (Math.random() * raf.length() - 1);
            raf.seek(randomFilePointer);
            int symbol;
            if ((symbol = raf.read()) != -1) {
                if (abusesSymbols.contains(symbol)) {
                   result = createPhrase(raf.getFilePointer(), raf);
                } else {
                    long newCurPointer = findNextWord(raf.getFilePointer(), raf);
                    result = createPhrase(newCurPointer, raf);
                }

            } else {
                LOGGER.info("File is empty!");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    private Long findNextWord(long curPointer, RandomAccessFile raf) {
        long newCurPointer = curPointer;
        int symbol;
        try {
            while (((symbol = raf.read()) != -1)) {
                if (abusesSymbols.contains(symbol)){
                    newCurPointer = raf.getFilePointer();
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return newCurPointer == curPointer ? 0 : newCurPointer;
    }

    private String createPhrase(long curPointer, RandomAccessFile raf) {
        StringBuilder result = new StringBuilder();
        int numberWords = 2 + (int) (Math.random() * 4);
        int symbol;
        try {
            raf.seek(curPointer);
            while ((numberWords != 0) && ((symbol = raf.read()) != -1)) {
                if (abusesSymbols.contains(symbol)) {
                    result.append(" ");
                    if (symbol == 13) {
                        raf.seek(raf.getFilePointer() + 1);
                    }
                    numberWords--;
                } else {
                    result.append((char) symbol);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result.toString();
    }

}
