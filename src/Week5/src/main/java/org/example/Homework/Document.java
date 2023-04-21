package org.example.Homework;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private String id;
    private String name;
    private String path;
    private Map<String,String> tags;

    public Document() {}

    public Document(String id, String name, String path, String[] tags) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.tags = new HashMap<>();
        for (int i = 0; i < tags.length; i += 2) {
            this.addTag(tags[i], tags[i + 1]);
        }
    }

    public Document(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.tags = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addTag(String key, String value) {
        tags.put(key, value);
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", tags=" + tags +
                '}';
    }

}
