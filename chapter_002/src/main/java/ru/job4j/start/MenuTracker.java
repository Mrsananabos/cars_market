package ru.job4j.Strategy;

import ru.job4j.start.Input;
import ru.job4j.start.Item;
import ru.job4j.start.Tracker;

class EditItem implements UserAction {
    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, enter the task's ID that you want to edit : ");
        String newName = input.ask("Please, enter the new task's name : ");
        String newDescription = input.ask("Please, enter the new task's Description : ");
        String newCreated = input.ask("Please, enter the new task's Created : ");
        String newComment = input.ask("Please, enter the new task's comment : ");
        Item NewItem = new Item(newName, newDescription, newCreated, newComment);
        NewItem.setId(id);
        tracker.update(NewItem);

    }


    public String info() {
        return String.format("%s. %s", this.key(), "Edit the new Item.");
    }
}

public class MenuTracker{

    private Input input;
    private Tracker tracker;
    private UserAction[] actions=new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input=input;
        this.tracker=tracker;
    }
public void fillActions(){
this.actions[0]= this.new AddItem();
this.actions[1] = new MenuTracker.ShowItem();
this.actions[2]=new EditItem();
this.actions[3]= this.new DeleteItem();
this.actions[4]=this.new FindbyID();
this.actions[5]=this.new FindbyName();
}

public void select (int key) {
    this.actions[key].execute(this.input, tracker);
}

public void show() {
    for (UserAction action :this.actions) {
        if (action != null) {
            System.out.println(action.info());
        }
    }
}

private class AddItem implements UserAction{
    public int key() {
        return 0;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Please, enter the task's name : ");
        String Description = input.ask("Please, enter the task's Description : ");
        String  Created = input.ask("Please, enter the task's Created : ");
        String Comment = input.ask("Please, enter the task's comment : ");
        tracker.add(new Item(name, Description, Created, Comment));

    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Add the new Item.");
    }
}

    private static class ShowItem implements UserAction{
        public int key() {
            return 1;
        }


        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                if (item != null) {
                    System.out.println(item.getName());
                }
            }
        }


        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    private class DeleteItem implements UserAction{
        public int key() {
            return 3;
        }


        public void execute(Input input, Tracker tracker) {
            String ID = input.ask("Please, enter the task's id that you want to delete : ");
            tracker.delete(ID);
        }


        public String info() {
            return String.format("%s. %s", this.key(), "Delete the new Item");
        }
    }

    private class FindbyID implements UserAction {
        public int key() {
            return 4;
        }


        public void execute(Input input, Tracker tracker) {
            String Id = input.ask("Please, enter the task's ID : ");
            Item result = tracker.findById(Id);
            if (result != null) {
                System.out.println("Name : " + (tracker.findById(Id).getName() + " Desc : " + tracker.findById(Id).getDesc() + " Created : " + tracker.findById(Id).getCreated() + " Comment : " + tracker.findById(Id).getComment()));
            } else {
                System.out.println("Sorry, Item with this id not found ");
            }

        }


        public String info() {
            return String.format("%s. %s", this.key(), "Find the item by ID");
        }
    }

        private class FindbyName implements UserAction {
            public int key() {
                return 5;
            }


            public void execute(Input input, Tracker tracker) {
                String Name = input.ask("Please, enter the task's name : ");
                Item[] FindedItems = tracker.findByName(Name);
                for (int i = 0; i < FindedItems.length; i++) {
                    System.out.println("Name : " + FindedItems[i].getName() + " Desc " + FindedItems[i].getDesc() + " Created : " + FindedItems[i].getCreated() + " Comment : " + FindedItems[i].getComment());
                }
            }

            public String info() {
                return String.format("%s. %s", this.key(), "Find the item by Name");
            }
        }







}