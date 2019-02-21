package ru.job4j.socket.fileManager.server;

import java.util.ArrayList;
import java.util.List;

public class MenuAction {
    private Input input;
    private Output output;
    private Managing managing;
    private List<UserAction> actions = new ArrayList<>();
    private String answer = "";

    public MenuAction(Input input, Output output, Managing managing) {
        this.input = input;
        this.output = output;
        this.managing = managing;
        this.fillActions();
    }

    private void fillActions() {
        actions.add(new Exit("Exit", 0));
        actions.add(new GoHome("Go home", 1));
        actions.add(new GoTo("Go to", 2));
        actions.add(new DownloadFile("Download file", 3));
        actions.add(new PushFile("Push file", 4));
    }

    public void select(int key) {
        String answer;
        if (key >= 0 && this.actions.size() >= key) {
            answer = this.actions.get(key).execute(this.input, this.managing);
        } else {
            System.out.println(key);
            answer = "Choose an action from the list";
        }
        this.output.answer(answer);
    }

    public void show() {
        this.actions.forEach(userAction -> System.out.println(userAction.inform()));
    }

    private class GoHome extends BaseAction {
        GoHome(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return this.key();
        }

        @Override
        public String execute(Input input, Managing managing) {
            answer = managing.goHome();
            return answer;
        }
    }

    private class GoTo extends BaseAction {
        GoTo(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return this.key();
        }

        @Override
        public String execute(Input input, Managing managing) {
            String path = input.askText();
            answer = managing.goTo(path);
            return answer;
        }
    }

    private class DownloadFile extends BaseAction {
        DownloadFile(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return this.key();
        }

        @Override
        public String execute(Input input, Managing managing) {
            String fileName = input.askText();
            answer = managing.downloadFile(fileName);
            return answer;
        }
    }

    private class PushFile extends BaseAction {
        PushFile(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return this.key();
        }

        @Override
        public String execute(Input input, Managing managing) {
            String fileName = input.askText();
            String text = input.askText();
            answer = managing.pushFile(fileName, text);
            return answer;
        }
    }

    private class Exit extends BaseAction {

        public Exit(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String execute(Input input, Managing managing) {
            return "Bye";
        }
    }


}
