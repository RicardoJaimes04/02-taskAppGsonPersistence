package com.svens.project.task.controller;

import com.svens.project.task.exception.InvalidDataTaskException;
import com.svens.project.task.exception.TaskException;
import com.svens.project.task.model.Task;
import com.svens.project.task.model.TaskRepository;


import java.util.List;

//Esta parte del controlador valida los datos recibidos y coordina las operaciones enviadas por la vista.
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskException, InvalidDataTaskException {
        validateData(id, title, description, completed);
        this.taskRepository.save(new Task(id, title, description, completed));
    }

    public void removeTask(String id) throws TaskException {

        this.taskRepository.remove(id);

    }

    public List<Task> showTask() throws TaskException {
        return this.taskRepository.findAll();
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskException, InvalidDataTaskException {
        validateData(id, title, description, completed);
        Task updatedTask = new Task(id, title, description, completed);
        this.taskRepository.updateTask(updatedTask);
    }

    public void updateTaskCompleted(String id, Boolean complete) throws TaskException, InvalidDataTaskException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidDataTaskException("No se puede ingresar un Id vacío");
        }
        if (complete == null) {
            throw new InvalidDataTaskException("No se puede ingresar un boolean vacío");
        }

        if (!taskRepository.validateIdIfExist(id)) {
            throw new TaskException("No existe el Id");
        }


        Task taskRepo = taskRepository.findById(id);
        taskRepo.setCompleted(complete);
        taskRepository.updateTask(taskRepo);


    }

    private void validateData(String id, String title, String description, Boolean completed) throws InvalidDataTaskException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidDataTaskException("No se puede ingresar un Id vacío");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataTaskException("No se puede ingresar un Title vacío");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidDataTaskException("No se puede ingresar una Description vacía");
        }
        if (completed == null) {
            throw new InvalidDataTaskException("No se puede ingresar un boolean vacío");
        }
    }

    public List<Task> showCompletedTasks() throws TaskException {
        return this.taskRepository.listCompletedTasks();
    }

    public List<Task> showPendingTasks() throws TaskException {
        return this.taskRepository.lisPendingTasks();
    }
}
