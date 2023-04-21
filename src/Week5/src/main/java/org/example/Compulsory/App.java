package org.example.Compulsory;

import java.io.IOException;

public class App 
{
    public static void main(String[] args) {
        CatalogManager manager = new CatalogManager();
        manager.add("1", "Document 1", "/path/to/document1", null,
                new String[]{"title", "Document 1 Title", "author", "Author 1", "year", "2022"});
        manager.add("2", "Document 2", null, "https://example.com/document2",
                new String[]{"title", "Document 2 Title", "author", "Author 2", "year", "2023"});
        System.out.println(manager.getCatalog());
        try {
            manager.save(manager.getCatalog(),"catalog.json");
            manager.load("catalog.json");
            System.out.println(manager.getCatalog());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
