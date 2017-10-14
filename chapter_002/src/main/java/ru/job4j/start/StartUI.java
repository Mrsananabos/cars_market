package ru.job4j.start;

public class StartUI {
  private Input input;
  private String EXIT = "0";

  public StartUI(Input input) {
    this.input = input;
  }

  public void init() {
    Tracker tracker = new Tracker();
    while (!EXIT.equals("6")) {
      EXIT = input.ask("0. Add new Item\n" +
              "1. Show all items\n" +
              "2. Edit item\n" +
              "3. Delete item\n" +
              "4. Find item by Id\n" +
              "5. Find items by name\n" +
              "6. Exit Program\n" +
              "Select: ");
      switch (EXIT) {


        case "0":
          String name = input.ask("Please, enter the task's name : ");
          tracker.add(new Item(name, "first desc", 10102017, "comments"));
          break;


        case "1":
          for (Item item : tracker.getAll()) {
            if (item != null) {
              System.out.println(item.getName());
            }

          }
          break;

        case "2":
          String id = input.ask("Please, enter the task's ID that you want to edit : ");
          String newName = input.ask("Please, enter the new task's name : ");
          String newDescription = input.ask("Please, enter the new task's Description : ");
          Long newCreated = input.askLong("Please, enter the new task's Created : ");
          String newComment = input.ask("Please, enter the new task's comment : ");
          Item NewItem = new Item(newName, newDescription, newCreated, newComment);
          NewItem.setId(id);
          tracker.update(NewItem);
          break;





        case "3":
          String ID = input.ask("Please, enter the task's id that you want to delete : ");
          tracker.delete(ID);
          break;


        case "4":
          String Id = input.ask("Please, enter the task's ID : ");
          Item result = tracker.findById(Id);
          if (result != null) {
            System.out.println("Name : " + (tracker.findById(Id).getName() + " Desc : " + tracker.findById(Id).getDesc() + " Created : " + tracker.findById(Id).getCreated() + " Comment : " + tracker.findById(Id).getComment()));
          break;
        } else {
            System.out.println("Sorry, Item with this id not found ");
            break;
          }


        case "5" :
          String Name = input.ask("Please, enter the task's name : ");
          Item[] FindedItems = tracker.findByName(Name);
          for (int i = 0; i<FindedItems.length; i++) {
            System.out.println("Name : "+FindedItems[i].getName()+" Desc "+FindedItems[i].getDesc()+" Created : "+FindedItems[i].getCreated()+" Comment : "+FindedItems[i].getComment());
          }
          break;


        case "6":
          break;
      }


    }
    }



  public static void main(String[] args) {
    Input input = new ConsoleInput();
    new StartUI(input).init();

  }
}

