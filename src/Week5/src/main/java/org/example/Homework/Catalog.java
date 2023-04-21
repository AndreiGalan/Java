package org.example.Homework;

import com.github.javafaker.Cat;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String name;
    private List<Document> documents;

    public Catalog() {
        this.documents = new ArrayList<>();
    }

    public Catalog(String name){
        this.name = name;
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document document) {
         documents.add(document);
    }

    public void removeDocument(String id) {
        documents.removeIf(doc -> doc.getId().equals(id));
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "documents=" + documents +
                '}';
    }

}
