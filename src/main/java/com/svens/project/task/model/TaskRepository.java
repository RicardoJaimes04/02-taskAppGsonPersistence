package com.svens.project.task.model;

import com.svens.project.task.exception.TaskException;
import com.svens.project.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

//Realiza el manejo de datos comunicandose con la clase de persistencia y verifica que todo se realice correctamente
public class TaskRepository {

    private final List<Task> taskList;

    public TaskRepository() {
        this.taskList = TaskPersistence.readTaskPersistence();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void save(Task task) throws TaskException {
        if (taskList.contains(task)) {
            throw new TaskException("La tarea ya existe");
        }
        taskList.add(task);
        TaskPersistence.saveTaskPersistence(taskList);

    }

    public Task findById(String id) {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void remove(String id) throws TaskException {
        Task taskToRemove = findById(id);
        if (taskToRemove == null) {
            throw new TaskException("No se encontró la tarea");
        }
        taskList.remove(taskToRemove);
        TaskPersistence.saveTaskPersistence(taskList);
    }

    public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }

        if (!taskList.contains(task)) {
            throw new TaskException("La tarea que quieres remover no existe");
        }
        taskList.remove(task);
        TaskPersistence.saveTaskPersistence(taskList);
    }


    public List<Task> findAll() throws TaskException {
        if (taskList.isEmpty()) {
            throw new TaskException("La lista está vacía");
        }
        return taskList;
    }

    public int findIndexById(String id) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(task.getId());
        if (index == -1) {
            throw new TaskException("No se encontró la tarea");
        }
        taskList.set(index, task);
        TaskPersistence.saveTaskPersistence(taskList);
    }


    public List<Task> listCompletedTasks() throws TaskException {
        List<Task> taskList1 = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getCompleted()) {
                taskList1.add(task);
            }
        }
        if (taskList1.isEmpty()) {
            throw new TaskException("No hay tareas completadas");
        }
        return taskList1;
    }

    public List<Task> lisPendingTasks() throws TaskException {
        List<Task> taskList1 = new ArrayList<>();
        for (Task task : taskList) {
            if (!task.getCompleted()) {
                taskList1.add(task);
            }
        }
        if (taskList1.isEmpty()) {
            throw new TaskException("No hay tareas pendientes");
        }
        return taskList1;
    }

    public Boolean validateIdIfExist(String id) throws TaskException {
        return findById(id) != null;
    }
}

