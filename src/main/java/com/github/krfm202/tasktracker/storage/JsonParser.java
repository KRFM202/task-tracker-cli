package com.github.krfm202.tasktracker.storage;

import com.github.krfm202.tasktracker.model.Status;
import com.github.krfm202.tasktracker.model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final String[] keys = {"id", "description", "status", "createdAt", "updatedAt"};

    public JsonParser() {
    }

    public String generateJsonString(List<Task> taskList) {
        JSONArray jArray = new JSONArray();
        for (Task task : taskList) {
            jArray.put(convertToJsonObject(task));
        }
        return jArray.toString();
    }

    public List<Task> parseJsonStringToList(String jsonString) {
        List<Task> taskList = new ArrayList<>();
        if (!jsonString.isEmpty()) {
            JSONArray jArray = new JSONArray(jsonString);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                taskList.add(convertToTask(jObject));
            }
        }
        return taskList;
    }

    private Task convertToTask(JSONObject jObject) {
        return new Task(
                Integer.parseInt(jObject.getString(keys[0])),
                jObject.getString(keys[1]),
                Status.valueOf(jObject.getString(keys[2])),
                LocalDateTime.parse(jObject.getString(keys[3])),
                LocalDateTime.parse(jObject.getString(keys[4]))
        );
    }

    private JSONObject convertToJsonObject(Task task) {
        JSONObject jObject = new JSONObject();
        jObject.put(keys[0], String.valueOf(task.getId()));
        jObject.put(keys[1], task.getDescription());
        jObject.put(keys[2], task.getStatus());
        jObject.put(keys[3], task.getCreatedAt());
        jObject.put(keys[4], task.getUpdatedAt());
        return jObject;
    }
}
