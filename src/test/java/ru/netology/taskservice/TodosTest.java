package ru.netology.taskservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    Todos todos = new Todos();
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchQueryToSimpleTask() {
        todos.add(simpleTask);
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotMatchQueryToSimpleTask() {
        todos.add(simpleTask);
        Task[] expected = {};
        Task[] actual = todos.search("Написать");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchQueryToMeeting() {
        todos.add(meeting);
        Task[] expectedTopic = {meeting};
        Task[] actualTopic = todos.search("Выкатка");

        Task[] expectedProject = {meeting};
        Task[] actualProject = todos.search("НетоБанк");

        Assertions.assertArrayEquals(expectedTopic, actualTopic);
        Assertions.assertArrayEquals(expectedProject, actualProject);
    }

    @Test
    public void shouldNotMatchQueryToMeeting() {
        todos.add(meeting);
        Task[] expectedTopic = {};
        Task[] actualTopic = todos.search("Обновление");

        Task[] expectedProject = {};
        Task[] actualProject = todos.search("АльфаБанк");

        Assertions.assertArrayEquals(expectedTopic, actualTopic);
        Assertions.assertArrayEquals(expectedProject, actualProject);
    }

    @Test
    public void shouldMatchQueryToEpic() {
        todos.add(epic);
        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotMatchQueryToEpic() {
        todos.add(epic);
        Task[] expected = {};
        Task[] actual = todos.search("Ананас");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchQueryToThreeTask() {
        todos.add(simpleTask);
        todos.add(meeting);
        todos.add(epic);

        Task[] expectedTitle = {simpleTask};
        Task[] actualTitle = todos.search("Позвонить");

        Task[] expectedProject = {meeting};
        Task[] actualProject = todos.search("НетоБанк");

        Task[] expectedSubtasks = {epic};
        Task[] actualSubtasks = todos.search("Хлеб");

        Assertions.assertArrayEquals(expectedTitle, actualTitle);
        Assertions.assertArrayEquals(expectedProject, actualProject);
        Assertions.assertArrayEquals(expectedSubtasks, actualSubtasks);
    }

    @Test
    public void shouldNotMatchQueryTask() {
        todos.add(meeting);
        todos.add(simpleTask);
        todos.add(epic);

        Task[] expected = {};
        Task[] actual = todos.search("Арбуз");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchQueryOneTask() {
        todos.add(simpleTask);
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("родит");
        Assertions.assertArrayEquals(expected, actual);
    }
}
