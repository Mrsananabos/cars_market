package ru.job4j.consoleChat;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class Console {
    private static final Logger LOGGER = Logger.getLogger(PhraseProducer.class);
    private final PhraseProducer phraseProducer;
    private boolean working = true;
    private boolean answer = true;

    public Console(String filePath) {
        phraseProducer = new PhraseProducer(filePath);
    }

    void start() {
        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            while (working) {
                String usersWord = br.readLine();
                LOGGER.info("User: " + usersWord);
                chechWord(usersWord);
                if (this.answer) {
                    String answerWord = phraseProducer.getPhrase();
                    LOGGER.info("Bot: " + answerWord);
                }
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void chechWord(String word) {
        switch (word) {
            case ("стоп"): {
                this.answer = false;
                break;
            }
            case ("продолжить"): {
                this.answer = true;
                break;
            }
            case ("закончить"): {
                this.answer = false;
                this.working = false;
                break;
            }
        }
    }


    public static void main(String[] args) {
        final String path = "C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/consoleChat/test.txt";
        Console console = new Console(path);
        console.start();
    }
}
