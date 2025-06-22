package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TodoList list = new TodoList();

        list.add("Buy milk", List.of("food"));
        list.add("Buy eggs", List.of("food"));
        list.add("Prepare a lesson for CSC 122");  // no tags
        list.add("Sow beet seeds", List.of("food", "garden", "spring"));

        list.complete("Buy eggs");

        System.out.println("\nAll Tasks:");
        list.all();

        System.out.println("\nCompleted Tasks:");
        list.complete();

        System.out.println("\nIncomplete Tasks:");
        list.incomplete();

        System.out.println("\nTasks Tagged 'food':");
        list.taggedWith("food");

        System.out.println("\nTasks Tagged 'music':");
        list.taggedWith("music");

        System.out.println("\nTrying to add blank task:");
        list.add("");

        System.out.println("\nTrying to add duplicate incomplete task:");
        list.add("Buy milk");

        System.out.println("\nClearing list:");
        list.clear();

        System.out.println("\nAll Tasks After Clear:");
        list.all();
    }
}
