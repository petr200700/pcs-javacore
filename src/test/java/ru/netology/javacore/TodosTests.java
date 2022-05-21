package ru.netology.javacore;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TodosTests {

    Todos sut;

    @BeforeEach
    public void init() {

        System.out.println("test started");
        sut = new Todos();
    }

    @BeforeAll
    public static void started() {
        System.out.println("tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("tests completed");
    }

    @Test
    public void testAddTask() {

        sut.addTask("task #A");

        boolean condition = sut.getAllTasks().contains("task #A");

        Assertions.assertTrue(condition);
    }

    @Test
    public void testAddTaskLexicographicOrder() {

        String task1 = "task #B";
        String task2 = "task #C";
        String task3 = "task #A";
        String expected = "task #A task #B task #C";

        sut.addTask(task1);
        sut.addTask(task2);
        sut.addTask(task3);
        String result = sut.getAllTasks();

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testRemoveTask() {

        sut.addTask("task #A");
        sut.removeTask("task #A");

        boolean condition = sut.getAllTasks().contains("task #A");

        Assertions.assertFalse(condition);
    }
}
