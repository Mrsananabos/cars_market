package ru.job4j.start;

import ru.job4j.start.MenuTracker;

public class StartUI {
  private Input input;
    int[] ranges;
    Tracker tracker;


    public void setRanges() {
      ranges = new int[new MenuTracker(this.input, this.tracker).ranges.length];
      for (int i = 0; i < ranges.length; i++) {
        ranges[i] = i;
      }
    }


    public StartUI(Input input, Tracker tracker) {
      this.input = input;
      this.tracker = tracker;
    }


    public void init() {
      MenuTracker menu = new MenuTracker(this.input, this.tracker);
      menu.fillActions();
      setRanges();
      do {
        menu.show();
        menu.select(this.input.ask("Select: ", ranges));
      } while (!"y".equals(this.input.ask("Exit?(y): ")));
      menu.getTracker().getControlPSQL().closeConnection();
    }

}


