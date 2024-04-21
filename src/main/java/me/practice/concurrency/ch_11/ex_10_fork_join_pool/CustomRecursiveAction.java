package me.practice.concurrency.ch_11.ex_10_fork_join_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {
    private long workLoad = 0;

    public CustomRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        // if work is above threshold, break tasks up into smaller tasks
        if (this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<CustomRecursiveAction> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());
            for (RecursiveAction subtask : subtasks) {
                subtask.fork();
            }
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<CustomRecursiveAction>();
        subtasks.add(new CustomRecursiveAction(this.workLoad / 2));
        subtasks.add(new CustomRecursiveAction(this.workLoad / 2));
        return subtasks;
    }
}
