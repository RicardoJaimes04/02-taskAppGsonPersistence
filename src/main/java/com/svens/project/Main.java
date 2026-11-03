package com.svens.project;

import com.svens.project.task.controller.TaskController;
import com.svens.project.task.model.TaskRepository;
import com.svens.project.task.persistence.TaskPersistence;
import com.svens.project.task.view.TaskView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskController taskController = new TaskController(taskRepository);
        TaskView taskView = new TaskView(taskController);

        taskView.showMenu();

    }
}