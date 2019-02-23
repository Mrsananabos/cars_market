package ru.job4j.threads;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";
    protected final int limitX = 300;
    protected final int limitY = 300;

    @Override
    public void start(Stage stage) throws InterruptedException {
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect));
        thread.start();
        stage.setScene(new Scene(group, this.limitX, this.limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> thread.interrupt());

    }


}