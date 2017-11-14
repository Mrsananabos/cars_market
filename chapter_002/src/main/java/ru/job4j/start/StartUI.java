package ru.job4j.start;

import ru.job4j.start.MenuTracker;

public class StartUI {
  private Input input;


  public StartUI(Input input) {

    this.input = input;
  }

  public void init() {
    Tracker tracker = new Tracker();
    MenuTracker menu = new MenuTracker(this.input, tracker);
    menu.fillActions();
    do {                        // цикл, по которому спрашиваем у пользовтеля, выходить из программы или нет
      menu.show();
      int key = Integer.valueOf(input.ask("Select: "));
      menu.select(key);
    } while (!"y".equals(this.input.ask("Exit? y")));
  }


  public static void main(String[] args) {
    Input input = new ConsoleInput();
    new StartUI(input).init();

  }
}


