package ru.job4j.start;
import org.junit.Test;

public class StubInputTest {
    @Test
    public void show() {
        Tracker tracker = new Tracker("jdbc:postgresql://localhost:5432/java_a_from_z", "postgres", "a");
        Input input = new StubInput(new String[]{"0", "item1", "desc1", "comment1", "n", "0", "item2", "desc2", "comment2", "n", "1", "y"});
        new StartUI(input, tracker).init();
    }
}


