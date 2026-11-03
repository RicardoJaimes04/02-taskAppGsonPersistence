package com.svens.project.task.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.svens.project.task.model.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//Maneja la persistencia con la libreria Gson
public class TaskPersistence {
    private final static Gson gson = new Gson();
    private final static String FILE_PATH = "taskList.json";

    public static void saveTaskPersistence(List<Task> listTask) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listTask, writer);
            System.out.println("La escritura fue exitosa.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static List<Task> readTaskPersistence() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            return gson.fromJson(reader, listType);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }
}


