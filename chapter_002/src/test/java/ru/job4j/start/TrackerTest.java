package ru.job4j.start;

import org.junit.Test;
import ru.job4j.start.Item;
import ru.job4j.start.Tracker;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", "123L", "Comments");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", "1234", "Комметарий");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.update(next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }


    @Test
    public void WhenDeletedItems() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", "123", "Comments");
        tracker.add(previous);
        Item previous1 = new Item("test2", "testDescription", "123", "Comments");
        tracker.add(previous1);
        Item previous2 = new Item("test3", "testDescription", "123" , "Comments");
        tracker.add(previous2);
        Item previous3 = new Item("test4", "testDescription", "123", "Comments");
        tracker.add(previous3);
        tracker.delete(previous.getId());
        //tracker.delete(previous3.getId());
        Item[] result = tracker.getAll();
        Item[] expect = {previous1, previous2, previous3, null};
        assertThat(result, is(expect));
    }



    @Test
    public void WhenCreateThreeSameItemsNameThenReturnThisItems() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", "123", "Comments");
        tracker.add(previous);
        Item previous1 = new Item("test2", "testDescription", "123", "Comments");
        tracker.add(previous1);
        Item previous2 = new Item("test2", "testDescription", "123", "Comments");
        tracker.add(previous2);
        Item previous3 = new Item("test2", "testDescription", "123", "Comments");
        tracker.add(previous3);
        Item[] result = tracker.findByName(previous1.getName());
        Item[] expect = {previous1, previous2, previous3};
        assertThat(result, is(expect));
    }




}
