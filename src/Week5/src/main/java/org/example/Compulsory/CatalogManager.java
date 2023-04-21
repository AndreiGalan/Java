package org.example.Compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogManager {
    private Catalog catalog;

    public CatalogManager() {
        this.catalog = new Catalog();
    }

    public void add(String id, String name, String path, String url, String[] tags) {
        Document document = new Document(id, name);
        document.setPath(path);
        document.setUrl(url);
        for (int i = 0; i < tags.length; i += 2) {
            document.addTag(tags[i], tags[i + 1]);
        }
        catalog.addDocument(document);
    }

    public void removeDocument(String id) {
        catalog.removeDocument(id);
    }

    public void save(Catalog catalog, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), catalog);
    }

    public static Catalog load(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), Catalog.class);
    }

    public Catalog getCatalog() {
        return catalog;
    }
}