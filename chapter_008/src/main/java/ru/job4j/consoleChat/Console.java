package ru.job4j.consoleChat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class Console {
    private static final Logger LOGGER = LogManager.getLogger(Console.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private static final Marker BOT = MarkerManager.getMarker("BOT");
    private static final String TEMPLATE_MSG = "{} : {}";
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
                LOGGER.info(TEMPLATE_MSG, USER, usersWord);
                chechWord(usersWord);
                if (this.answer) {
                    String answerWord = phraseProducer.getPhrase();
                    LOGGER.info(TEMPLATE_MSG, BOT, answerWord);
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
