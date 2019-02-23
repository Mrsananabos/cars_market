package ru.job4j.threads;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int mainDirectionsX = 1;
    private int mainDirectionsY = -1;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        boolean a = true;
        while (a) {
            this.rect.setX(this.rect.getX() + mainDirectionsX);
            this.rect.setY(this.rect.getY() + mainDirectionsY);
            if (this.rect.getY() < 0) {
                this.mainDirectionsY = 1;
            }
            if (this.rect.getX() > 300) {
                this.mainDirectionsX = -1;
            }
            if (this.rect.getY() > 300) {
                this.mainDirectionsY = -1;
            }
            if (this.rect.getX() < 0) {
                this.mainDirectionsX = 1;
            }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                if (currentThread.isInterrupted()) {
                        System.out.println("Поток прерван");
                        a = false;
                        break;
                    }
                }

            }
    }
}