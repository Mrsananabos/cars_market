package ru.job4j.start;

import ru.job4j.start.Input;
import ru.job4j.start.Item;
import ru.job4j.start.Tracker;

class EditItem extends BaseAction { //Действие "обновить заявку" с ключом ввода "2"

    EditItem(String name, int key) {
        super("Edit Item", 2);
    }

    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, enter the task's ID that you want to edit : ");
        String newName = input.ask("Please, enter the new task's name : ");
        String newDescription = input.ask("Please, enter the new task's Description : ");
        String newCreated = input.ask("Please, enter the new task's Created : ");
        String newComment = input.ask("Please, enter the new task's comment : ");
        Item newItem = new Item(newName, newDescription, newCreated, newComment);
        newItem.setId(id);
        tracker.update(newItem);
    }

    public String info() {
        return inform();
    } // сообщает пользователю о данном действии
}


public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }


    public int[] ranges = new int[actions.length];

    public int[] fillRanges() {
        for (int i = 0; i < ranges.length; i++) {
            ranges[i] = i;
        }
        return ranges;
    }

    public void fillActions() {                             //метод заполняет массив действий
        this.actions[0] = this.new AddItem("Add Item", 0);
        this.actions[1] = new MenuTracker.ShowItem("Show Items", 1);
        this.actions[2] = new EditItem("Edit Item", 2);
        this.actions[3] = this.new DeleteItem("Delete Item", 3);
        this.actions[4] = this.new FindbyID("Find by ID", 4);
        this.actions[5] = this.new FindbyName("Find by Name", 5);
        this.actions[6] = this.new ExitMenu("Exit", 6);
    }

    public void select(int key) {
        this.actions[key].execute(this.input, tracker);
    }


    public void show() {       //Сообщает пользователю о всех возможных действиях
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.inform());
            }
        }
    }


    private class AddItem extends BaseAction { //Действие "добавить заявку" с ключом ввода "0"
        AddItem(String name, int key) {
            super("Add Item", 0);
        }

        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {  //реализация самого действия
            String name = input.ask("Please, enter the task's name : ");
            String description = input.ask("Please, enter the task's Description : ");
            String created = input.ask("Please, enter the task's Created : ");
            String comment = input.ask("Please, enter the task's comment : ");
            tracker.add(new Item(name, description, created, comment));

        }

        public String info() {
            return inform();
        } //Сообщает пользователю о данном действии
    }

    private static class ShowItem extends BaseAction { //Действие "показать зявки" с ключом ввода "1"
        ShowItem(String name, int key) {
            super("Show Item", 1);
        }

        public int key() {
            return 1;
        }


        public void execute(Input input, Tracker tracker) { //реализация самого действия
            for (Item item : tracker.getAll()) {
                if (item != null) {
                    System.out.println(item.getName());
                }
            }
        }


        public String info() {
            return inform();
        } // Сообщает пользователю о даном действии
    }

    private class DeleteItem extends BaseAction { //Действие "удаление заявки" с ключом ввода "3"
        DeleteItem(String name, int key) {
            super("Delete Item", 3);
        }

        public int key() {
            return 3;
        }


        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's id that you want to delete : ");
            tracker.delete(id);
        }


        public String info() {
            return inform();
        }
    }

    private class FindbyID extends BaseAction {

        FindbyID(String name, int key) {
            super("Find by ID", 4);
        }

        public int key() {
            return 4;
        }


        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's ID : ");
            Item result = tracker.findById(id);
            if (result != null) {
                System.out.println("Name : " + (tracker.findById(id).getName() + " Desc : " + tracker.findById(id).getDesc() + " Created : " + tracker.findById(id).getCreated() + " Comment : " + tracker.findById(id).getComment()));
            } else {
                System.out.println("Sorry, Item with this id not found ");
            }

        }


        public String info() {
            return inform();
        }
    }

    private class FindbyName extends BaseAction { //Действие "найти заявку по имени" с ключом ввода "5"
        FindbyName(String name, int key) {
            super("Find by name", 5);
        }

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task's name : ");
            Item[] findedItems = tracker.findByName(name);
            for (int i = 0; i < findedItems.length; i++) {
                System.out.println("Name : " + findedItems[i].getName() + " Desc " + findedItems[i].getDesc() + " Created : " + findedItems[i].getCreated() + " Comment : " + findedItems[i].getComment());
            }
        }

        public String info() {
            return inform();
        }
    }

    private class ExitMenu extends  BaseAction {

        public ExitMenu(String name, int key) {
            super("Exit", 6);
        }

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
                String answer = input.ask("Are you really want to exit? (Y/N or y/n): ");
                if ("y".equals(answer.toLowerCase())) {
                    System.out.println("Exit program, bye!");
                } else {
                    System.out.println("Please, enter again.");
                }
            }



    }


}