package org.example.Compulsory;

import java.util.ArrayList;
import java.util.List;
public class Catalog {
    private List<Document> documents;

    public Catalog() {
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

    @Override
    public String toString() {
        return "Catalog{" +
                "documents=" + documents +
                '}';
    }



}
