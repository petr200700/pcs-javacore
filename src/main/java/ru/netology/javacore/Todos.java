package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;


public class Todos {

    private final List<String> allTasks;

    public Todos() {
        allTasks = new ArrayList<>();
    }

    public void addTask(String task) {
        allTasks.add(task);
    }

    public void removeTask(String task) {
        allTasks.remove(task);
    }

    public String getAllTasks() {
        return allTasks.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todos todos = (Todos) o;
        return getAllTasks().equals(todos.getAllTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAllTasks());
    }

}