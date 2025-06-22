import java.util.*;

public class TodoList {
    private static class Task {
        String description;
        boolean isComplete;
        Set<String> tags;

        Task(String description, Set<String> tags) {
            this.description = description;
            this.isComplete = false;
            this.tags = (tags != null) ? tags : new HashSet<>();
        }

        @Override
        public String toString() {
            return (isComplete ? "[✓] " : "[ ] ") + description + (tags.isEmpty() ? "" : " " + tags);
        }
    }

    private final List<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<>();
    }

    public boolean add(String description) {
        return add(description, null);
    }

    public boolean add(String description, Collection<String> tags) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Cannot add a blank task.");
            return false;
        }

        for (Task task : tasks) {
            if (task.description.equals(description) && !task.isComplete) {
                System.out.println("Task already exists and is incomplete.");
                return false;
            }
        }

        tasks.add(new Task(description, tags != null ? new HashSet<>(tags) : null));
        return true;
    }

    public boolean complete(String description) {
        for (Task task : tasks) {
            if (task.description.equals(description)) {
                task.isComplete = true;
                return true;
            }
        }
        System.out.println("Task not found: " + description);
        return false;
    }

    public void all() {
        if (tasks.isEmpty()) {
            System.out.println("Your todo list is empty.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void complete() {
        boolean found = false;
        for (Task task : tasks) {
            if (task.isComplete) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) System.out.println("No completed tasks.");
    }

    public void incomplete() {
        boolean found = false;
        for (Task task : tasks) {
            if (!task.isComplete) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) System.out.println("No incomplete tasks.");
    }

    public void taggedWith(String tag) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.tags.contains(tag)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with tag: " + tag);
        }
    }

    public void clear() {
        tasks.clear();
        System.out.println("Todo list cleared.");
    }
}
