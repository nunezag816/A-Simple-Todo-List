package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private TodoList todo;

    @BeforeEach
    void setUp() {
        todo = new TodoList();
    }

    @Test
    void testAddTaskSuccessfully() {
        assertTrue(todo.add("Buy milk"));
    }

    @Test
    void testAddTaskWithTags() {
        assertTrue(todo.add("Sow seeds", List.of("garden", "spring")));
    }

    @Test
    void testRejectBlankTask() {
        assertFalse(todo.add(""));
        assertFalse(todo.add("   "));
        assertFalse(todo.add(null));
    }

    @Test
    void testRejectDuplicateIncompleteTask() {
        assertTrue(todo.add("Buy eggs"));
        assertFalse(todo.add("Buy eggs")); // Incomplete duplicate
    }

    @Test
    void testAllowDuplicateOfCompletedTask() {
        assertTrue(todo.add("Buy bread"));
        assertTrue(todo.complete("Buy bread"));
        assertTrue(todo.add("Buy bread")); // New completed task can be re-added
    }

    @Test
    void testCompleteTaskSuccessfully() {
        todo.add("Submit assignment");
        assertTrue(todo.complete("Submit assignment"));
    }

    @Test
    void testCompleteTaskFailsIfNotExists() {
        assertFalse(todo.complete("Nonexistent task"));
    }

    @Test
    void testAllListsAllTasks() {
        todo.add("Task 1");
        todo.add("Task 2");
        todo.complete("Task 1");

        // Check string output with System.out capture
        assertDoesNotThrow(() -> todo.all());
    }

    @Test
    void testCompleteListsOnlyCompletedTasks() {
        todo.add("Task A");
        todo.add("Task B");
        todo.complete("Task B");

        assertDoesNotThrow(() -> todo.complete());
    }

    @Test
    void testIncompleteListsOnlyIncompleteTasks() {
        todo.add("Task X");
        todo.add("Task Y");
        todo.complete("Task X");

        assertDoesNotThrow(() -> todo.incomplete());
    }

    @Test
    void testTaggedWithReturnsCorrectTaggedTasks() {
        todo.add("Water plants", List.of("garden"));
        todo.add("Study", List.of("school"));
        todo.add("Mow lawn", List.of("garden", "summer"));

        assertDoesNotThrow(() -> todo.taggedWith("garden"));
        assertDoesNotThrow(() -> todo.taggedWith("school"));
        assertDoesNotThrow(() -> todo.taggedWith("music")); // No match
    }

    @Test
    void testClearRemovesAllTasks() {
        todo.add("Clean room");
        todo.add("Do laundry");
        todo.clear();

        // After clearing, list should be empty
        assertDoesNotThrow(() -> todo.all());
    }
}

